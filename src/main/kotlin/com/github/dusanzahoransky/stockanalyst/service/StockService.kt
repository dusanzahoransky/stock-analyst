package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.YahooFinanceClient
import com.github.dusanzahoransky.stockanalyst.model.Ticker
import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Interval
import com.github.dusanzahoransky.stockanalyst.model.enums.Range
import com.github.dusanzahoransky.stockanalyst.model.mongo.*
import com.github.dusanzahoransky.stockanalyst.model.yahoo.analysis.AnalysisResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.chart.ChartResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.financials.FinancialsResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.holders.HoldersResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.statistics.StatisticsResponse
import com.github.dusanzahoransky.stockanalyst.repository.*
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils.CacheContext
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils.Companion.useCacheDynamicData
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils.Companion.useCacheFinancialsData
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.average
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.cumulativeGrowthRate
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.min
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.minus
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.multiply
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percent
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percentGrowth
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.plus
import com.github.dusanzahoransky.stockanalyst.util.FormattingUtils.Companion.epochSecToLocalDate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.pow

@Service
class StockService @Autowired constructor(
    val watchlistRepo: WatchlistRepo,
    val stockRepo: StockRepo,
    val yahooFinanceClient: YahooFinanceClient,
    val exchangeRateService: ExchangeRateService,
    val chartRepo: ChartRepo,
    val financialsRepo: FinancialsRepo,
    val analysisRepo: AnalysisRepo,
    val holdersRepo: HoldersRepo,
    val statisticsRepo: StatisticsRepo,
) {

    companion object {
        const val MILLION = 1000000.0
        const val THOUSAND = 1000.0
    }

    val log = LoggerFactory.getLogger(this::class.java)!!


    fun getWatchlistStocks(watchlistName: String, refreshDynamicData: Boolean, refreshFinancials: Boolean, mockData: Boolean, refreshOlderThan: LocalDate): List<Stock> {
        val watchlist = watchlistRepo.findById(watchlistName).orElseThrow()

        val cacheCtx = CacheContext(refreshDynamicData, refreshFinancials, mockData, refreshOlderThan)

        return watchlist.tickers.map { ticker ->
            findOrLoadStock(Ticker.fromString(ticker), cacheCtx)
        }
    }

    private fun findOrLoadStock(ticker: Ticker, cacheCtx: CacheContext): Stock {
        val partialData = loadStock(ticker, cacheCtx)

        var stock = stockRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        if (stock == null) {
            stock = Stock(symbol = ticker.symbol, exchange = ticker.exchange)

            processFinancials(partialData.financials.response, stock)
            processStatistics(partialData.statistics.response, stock)
            processAnalysis(partialData.analysis.response, stock)
            processHolders(partialData.holders.response, stock)
            processChart(partialData.chart.response, stock)
        } else {

            if (stock.financialsLastUpdated.isBefore(partialData.financials.getLastRefreshDate())) {
                processFinancials(partialData.financials.response, stock)
                stock.financialsLastUpdated = partialData.financials.getLastRefreshDate()
            }
            if (stock.statisticsLastUpdated.isBefore(partialData.statistics.getLastRefreshDate())) {
                processStatistics(partialData.statistics.response, stock)
                stock.statisticsLastUpdated = partialData.statistics.getLastRefreshDate()
            }
            if (stock.analysisLastUpdated.isBefore(partialData.analysis.getLastRefreshDate())) {
                processAnalysis(partialData.analysis.response, stock)
                stock.analysisLastUpdated = partialData.analysis.getLastRefreshDate()
            }
            if (stock.holdersLastUpdated.isBefore(partialData.holders.getLastRefreshDate())) {
                processHolders(partialData.holders.response, stock)
                stock.holdersLastUpdated = partialData.holders.getLastRefreshDate()
            }
            if (stock.chartLastUpdated.isBefore(partialData.chart.getLastRefreshDate())) {
                processChart(partialData.chart.response, stock)
                stock.chartLastUpdated = partialData.chart.getLastRefreshDate()
            }
        }

        calculateCombinedParts(stock)
        calculateGrowth(stock)
        calculateRule1(stock)
        calculateAcquirersMultiple(stock)

        //do not save mock data
        if (cacheCtx.mockData) {
            return stock
        }
        log.debug("Saving ${if (stock.id == null) "new" else "updated"} stock $ticker")
        stockRepo.save(stock)

        reduceData(stock)   //reduce large data sets being sent to UI
        return stock
    }

    private fun calculateAcquirersMultiple(stock: Stock) {
        val exchangeRate =  exchangeRateService.getRate(stock.currency, stock.financialCurrency)
        val evLocalCurrency = toLocalCurrency(getCurrentYear(stock.enterpriseValue), exchangeRate)
        addEntry(stock.acquirersMultiple, div(evLocalCurrency, getCurrentYear(stock.operatingIncome)))
    }

    private fun reduceData(stock: Stock) {
        val dayOfWeekForSampling = stock.price.lastKey().dayOfWeek
        stock.price = stock.price.filterKeys { key -> key.dayOfWeek == dayOfWeekForSampling}.toSortedMap()
    }


    data class PartialStockData(
        val chart: Chart,
        val financials: Financials,
        val analysis: Analysis,
        val statistics: Statistics,
        val holders: Holders
    )

    /**
     * Load partial stock data from various financial service endpoints and merge them together
     */
    private fun loadStock(ticker: Ticker, cacheCtx: CacheContext): PartialStockData {
        val chart = loadChart(ticker, cacheCtx)
        val financials = loadFinancials(ticker, cacheCtx)
        val analysis = loadAnalysis(ticker, cacheCtx)
        val statistics = loadStatistics(ticker, cacheCtx)
        val holders = loadHolders(ticker, cacheCtx)
         return PartialStockData(chart, financials, analysis, statistics, holders)
    }

    private fun loadChart(ticker: Ticker, cacheCtx: CacheContext): Chart {
        val cachedData = chartRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCacheDynamicData(cacheCtx, cachedData)) {
            log.debug("Retrieving Chart from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getChart(ticker, Interval.OneDay, Range.TenYears, cacheCtx.mockData)

        val data = Chart(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not save mock data
        if (cacheCtx.mockData) {
            return data
        }

        //delete previous version
        chartRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { chartRepo.delete(it) }
        //store new version
        log.debug("Saving Chart $ticker into DB")
        return chartRepo.insert(data)
    }

    private fun loadFinancials(ticker: Ticker, cacheCtx: CacheContext): Financials {
        val cachedData = financialsRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCacheFinancialsData(cacheCtx, cachedData)) {
            log.debug("Retrieving Financials from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getFinancials(ticker, cacheCtx.mockData)

        val data = Financials(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not save mock data
        if (cacheCtx.mockData) {
            return data
        }

        //delete previous version
        financialsRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { financialsRepo.delete(it) }
        //store new version
        log.debug("Saving Financials $ticker into DB")
        return financialsRepo.insert(data)
    }

    private fun loadAnalysis(ticker: Ticker, cacheCtx: CacheContext): Analysis {
        val cachedData = analysisRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCacheDynamicData(cacheCtx, cachedData)) {
            log.debug("Retrieving Analysis from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getAnalysis(ticker, cacheCtx.mockData)

        val data = Analysis(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not save mock data
        if (cacheCtx.mockData) {
            return data
        }

        //delete previous version
        analysisRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { analysisRepo.delete(it) }
        //store new version
        log.debug("Saving Analysis $ticker into DB")
        return analysisRepo.insert(data)
    }

    private fun loadHolders(ticker: Ticker, cacheCtx: CacheContext): Holders {
        val cachedData = holdersRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCacheDynamicData(cacheCtx, cachedData)) {
            log.debug("Retrieving Holders from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getHolders(ticker, cacheCtx.mockData)

        val data = Holders(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not save mock data
        if (cacheCtx.mockData) {
            return data
        }

        //delete previous version
        holdersRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { holdersRepo.delete(it) }
        //store new version
        log.debug("Saving Holders $ticker into DB")
        return holdersRepo.insert(data)
    }

    private fun loadStatistics(ticker: Ticker, cacheCtx: CacheContext): Statistics {
        val cachedData = statisticsRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCacheDynamicData(cacheCtx, cachedData)) {
            log.debug("Retrieving Statistics from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getStatistics(ticker, cacheCtx.mockData)

        val data = Statistics(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not save mock data
        if (cacheCtx.mockData) {
            return data
        }

        //delete previous version
        statisticsRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { statisticsRepo.delete(it) }
        //store new version
        log.debug("Saving Statistics $ticker into DB")
        return statisticsRepo.insert(data)
    }

    private fun processChart(chart: ChartResponse, stock: Stock) {
        log.debug("processChart $stock")
        val result = chart.chart?.result?.getOrNull(0) ?: return
        val closePrices = result.indicators?.quote?.getOrNull(0)?.close ?: return
        val timestamps = result.timestamp?.map { epochSecToLocalDate(it) } ?: return

        if (closePrices.size != timestamps.size) {
            throw IllegalStateException("Inconsistent chart data ${closePrices.size}, ${timestamps.size}")
        }

        for (i in timestamps.indices) {
            closePrices[i]?.let { stock.price[timestamps[i]] = it }
        }
    }

    private fun processFinancials(financials: FinancialsResponse, stock: Stock) {
        log.debug("processFinancials $stock")

        val incomeStmQuarterly = financials.incomeStatementHistoryQuarterly.incomeStatementHistory
        val incomeStm = financials.incomeStatementHistory.incomeStatementHistory

        val balSheetQuarterly = financials.balanceSheetHistoryQuarterly.balanceSheetStatements
        val balSheet = financials.balanceSheetHistory.balanceSheetStatements

        val cashFlowQuarterly = financials.cashflowStatementHistoryQuarterly.cashflowStatements
        val cashFlow = financials.cashflowStatementHistory.cashflowStatements

        val timeSeries = financials.timeSeries
        val earnings = financials.earnings

        stock.currency = financials.price?.currency?.let { Currency.valueOf(it) }
        stock.financialCurrency = earnings?.financialCurrency?.let { Currency.valueOf(it) }

        val exchangeRate =  exchangeRateService.getRate(stock.currency, stock.financialCurrency)

        val yearEnds = timeSeries?.timestamp
            ?.reversed()
            ?.map { epochSecToLocalDate(it) }
        val quarterEnds = balSheetQuarterly
            ?.map { it.endDate.raw }
            ?.map { epochSecToLocalDate(it) }

        stock.lastReportedQuarter = quarterEnds?.getOrNull(0)
        stock.lastReportedYear = yearEnds?.getOrNull(0)

        val epsQuarterly = earnings?.earningsChart?.quarterly?.reversed()

        if (quarterEnds != null) {
            for (i in quarterEnds.indices) {
                val quarter = quarterEnds[i]
                val incomeStatementQ = incomeStmQuarterly.getOrNull(i)
                val cashflowStatementQ = cashFlowQuarterly.getOrNull(i)
                val balanceSheetQ = balSheetQuarterly.getOrNull(i)

                val revenueQ = incomeStatementQ?.totalRevenue?.raw
                addEntry(stock.revenueQ, revenueQ, quarter)
                val grossProfitQ = incomeStatementQ?.grossProfit?.raw
                addEntry(stock.grossIncomeQ, grossProfitQ, quarter)
                addEntry(stock.grossMarginQ, percent(div(grossProfitQ, revenueQ)), quarter)
                addEntry(stock.ebitQ, incomeStatementQ?.ebit?.raw, quarter)
                val netIncomeQ = incomeStatementQ?.netIncome?.raw
                addEntry(stock.netIncomeQ, netIncomeQ, quarter)
                addEntry(stock.profitMarginPQ, percent(div(netIncomeQ, revenueQ)), quarter)
                val operatingIncomeQ = incomeStatementQ?.operatingIncome?.raw?.toDouble()
                addEntry(stock.operatingIncomeQ, operatingIncomeQ, quarter)
                addEntry(stock.operatingMarginQ, percent(div(operatingIncomeQ, revenueQ)), quarter)
                val interestExpenseQ = incomeStatementQ?.interestExpense?.raw?.toDouble()?.let { it * -1.0 }   //interest expense comes as negative number from Yahoo API
                addEntry(stock.interestExpenseQ, interestExpenseQ, quarter)
                addEntry(stock.interestExpenseToOperativeIncomePQ, percent(div(interestExpenseQ, operatingIncomeQ)), quarter)

                addEntry(stock.operatingCashFlowQ, cashflowStatementQ?.totalCashFromOperatingActivities?.raw, quarter)
                addEntry(stock.capitalExpendituresQ, cashflowStatementQ?.capitalExpenditures?.raw, quarter)
                addEntry(stock.stockRepurchasedQ, cashflowStatementQ?.repurchaseOfStock?.raw, quarter)


                addEntry(stock.cashQ, balanceSheetQ?.cash?.raw, quarter)
                addEntry(stock.cashAndCashEquivalentsQ, plus(balanceSheetQ?.cash?.raw, balanceSheetQ?.shortTermInvestments?.raw), quarter)
                addEntry(stock.inventoryQ, balanceSheetQ?.inventory?.raw, quarter)
                val currentAssetsQ = balanceSheetQ?.totalCurrentAssets?.raw
                addEntry(stock.currentAssetsQ, currentAssetsQ, quarter)
                val currentLiabilitiesQ = balanceSheetQ?.totalCurrentLiabilities?.raw
                addEntry(stock.currentLiabilitiesQ, currentLiabilitiesQ, quarter)
                addEntry(stock.workingCapitalQ, minus(currentAssetsQ, currentLiabilitiesQ)?.toDouble(), quarter)
                val inventoryQ = balanceSheetQ?.inventory?.raw
                addEntry(stock.currentRatioQ, div(minus(currentAssetsQ, inventoryQ), currentLiabilitiesQ), quarter)
                addEntry(stock.totalAssetsQ, balanceSheetQ?.totalAssets?.raw, quarter)
                val totalLiabilitiesQ = balanceSheetQ?.totalLiab?.raw
                addEntry(stock.totalLiabilitiesQ, totalLiabilitiesQ, quarter)
                addEntry(stock.totalDebtQ, plus(balanceSheetQ?.longTermDebt?.raw, balanceSheetQ?.shortLongTermDebt?.raw), quarter)
                val totalShareholdersEquityQ = balanceSheetQ?.totalStockholderEquity?.raw
                addEntry(stock.totalShareholdersEquityQ, totalShareholdersEquityQ, quarter)
                addEntry(stock.retainedEarningsQ, balanceSheetQ?.retainedEarnings?.raw, quarter)
                addEntry(stock.totalDebtToEquityQ, div(totalLiabilitiesQ, totalShareholdersEquityQ), quarter)
                addEntry(stock.nonCurrentLiabilitiesToIncomeQ, div(minus(totalLiabilitiesQ, currentLiabilitiesQ), multiply(netIncomeQ, 4)), quarter)
                addEntry(stock.bookValueQ, balanceSheetQ?.netTangibleAssets?.raw, quarter)

                addEntry(stock.freeCashFlowQ, plus(cashflowStatementQ?.totalCashFromOperatingActivities?.raw, cashflowStatementQ?.capitalExpenditures?.raw), quarter)

                addEntry(stock.epsQ, fromLocalCurrency(epsQuarterly?.getOrNull(i)?.actual?.raw?.toDouble(), exchangeRate), quarter)
            }
        }

        val annualDilutedEPSTimeSeries = timeSeries?.annualDilutedEPS?.reversed()
        val sharesTimeSeries = timeSeries?.annualDilutedAverageShares?.reversed()

        if (yearEnds != null) {
            for (i in yearEnds.indices) {
                val year = yearEnds[i]
                val yearIncomeStm = incomeStm.getOrNull(i)
                val yearCashFlow = cashFlow.getOrNull(i)
                val yearBalSheet = balSheet.getOrNull(i)

                val revenue = yearIncomeStm?.totalRevenue?.raw
                addEntry(stock.revenue, revenue, year)
                val grossProfit = yearIncomeStm?.grossProfit?.raw
                addEntry(stock.grossIncome, grossProfit, year)
                addEntry(stock.grossMargin, percent(div(grossProfit, revenue)), year)
                addEntry(stock.ebit, yearIncomeStm?.ebit?.raw, year)
                val netIncome = yearIncomeStm?.netIncome?.raw
                addEntry(stock.netIncome, netIncome, year)
                addEntry(stock.profitMarginP, percent(div(netIncome, revenue)), year)
                val operatingIncome = yearIncomeStm?.operatingIncome?.raw?.toDouble()
                addEntry(stock.operatingIncome, operatingIncome, year)
                val interestExpense = yearIncomeStm?.interestExpense?.raw?.toDouble()?.let { it * -1.0 }  //interest expense comes as negative number from Yahoo API
                addEntry(stock.interestExpense, interestExpense, year)
                addEntry(stock.interestExpenseToOperativeIncomeP, percent(div(interestExpense, operatingIncome)), year)

                addEntry(stock.operatingCashFlow, yearCashFlow?.totalCashFromOperatingActivities?.raw, year)
                addEntry(stock.capitalExpenditures, yearCashFlow?.capitalExpenditures?.raw, year)
                addEntry(stock.stockRepurchased, yearCashFlow?.repurchaseOfStock?.raw, year)

                addEntry(stock.cash, yearBalSheet?.cash?.raw, year)
                addEntry(stock.cashAndCashEquivalents, plus(yearBalSheet?.cash?.raw, yearBalSheet?.shortTermInvestments?.raw), year)
                addEntry(stock.inventory, yearBalSheet?.inventory?.raw, year)
                val currentAssets = yearBalSheet?.totalCurrentAssets?.raw
                addEntry(stock.currentAssets, currentAssets, year)
                val currentLiabilities = yearBalSheet?.totalCurrentLiabilities?.raw
                addEntry(stock.currentLiabilities, currentLiabilities, year)
                addEntry(stock.workingCapital, minus(currentAssets, currentLiabilities)?.toDouble(), year)
                val inventory = yearBalSheet?.inventory?.raw
                addEntry(stock.currentRatio, div(minus(currentAssets, inventory), currentLiabilities), year)
                addEntry(stock.totalAssets, yearBalSheet?.totalAssets?.raw, year)
                val totalLiabilities = yearBalSheet?.totalLiab?.raw
                addEntry(stock.totalLiabilities, totalLiabilities, year)
                addEntry(stock.totalDebt, plus(yearBalSheet?.longTermDebt?.raw, yearBalSheet?.shortLongTermDebt?.raw), year)
                val totalShareholdersEquity = yearBalSheet?.totalStockholderEquity?.raw
                addEntry(stock.totalShareholdersEquity, totalShareholdersEquity, year)
                addEntry(stock.retainedEarnings, yearBalSheet?.retainedEarnings?.raw, year)
                addEntry(stock.totalDebtToEquity, div(totalLiabilities, totalShareholdersEquity), year)
                addEntry(stock.nonCurrentLiabilitiesToIncome, div(minus(totalLiabilities, currentLiabilities), netIncome), year)

                val bookValue = yearBalSheet?.netTangibleAssets?.raw
                addEntry(stock.bookValue, bookValue, year)

                val freeCashFlow = plus(yearCashFlow?.totalCashFromOperatingActivities?.raw, yearCashFlow?.capitalExpenditures?.raw)
                addEntry(stock.freeCashFlow, freeCashFlow, year)

                addEntry(stock.eps, fromLocalCurrency(annualDilutedEPSTimeSeries?.getOrNull(i)?.reportedValue?.raw?.toDouble(), exchangeRate), year)
                val shares = sharesTimeSeries?.getOrNull(i)?.reportedValue?.raw?.toDouble()
                addEntry(stock.shares, shares, year)
                addEntry(stock.freeCashFlowPerShare, fromLocalCurrency(div(freeCashFlow , shares), exchangeRate), year)
                addEntry(stock.bookValuePerShare, fromLocalCurrency(div(bookValue , shares), exchangeRate), year)
            }
        }
    }

    private fun <T> addEntry(statTimelineMap: SortedMap<LocalDate, T>, valueAtDate: T?, date: LocalDate? = LocalDate.now(), statName: String = "") {
        var value = valueAtDate
        if (valueAtDate is Double) {
            @Suppress("UNCHECKED_CAST")
            value = round(valueAtDate) as T
        }
        val previousValue = statTimelineMap[date]
        if (value != null) {
            if (previousValue != null && previousValue != value) {
                log.debug("Replacing $statName $previousValue -> $value")
            }
            statTimelineMap[date] = value
        } else if (previousValue == null) {
            statTimelineMap[date] = null
        }
    }

    private fun round(valueAtDate: Double): Double {
        return if(valueAtDate.isFinite() && !valueAtDate.isNaN()){
            BigDecimal.valueOf(valueAtDate).setScale(3, RoundingMode.HALF_UP).toDouble()
        } else {
            valueAtDate
        }
    }

    private fun processAnalysis(analysis: AnalysisResponse, stock: Stock) {
        log.debug("processAnalysis $stock")
        addEntry(stock.growthEstimate5y, percent(analysis.earningsTrend?.trend?.firstOrNull { it.period == "+5y" }?.growth?.raw))
    }

    private fun processHolders(holders: HoldersResponse, stock: Stock) {
        log.debug("processHolders $stock")
        addEntry(stock.buyPercentInsiderShares, holders.netSharePurchaseActivity?.buyPercentInsiderShares?.raw)
        addEntry(stock.sellPercentInsiderShares, holders.netSharePurchaseActivity?.sellPercentInsiderShares?.raw)
    }

    private fun processStatistics(stats: StatisticsResponse, stock: Stock) {
        log.debug("processStatistics $stock")
        val financialData = stats.financialData
        val price = stats.price
        val defaultKeyStatistics = stats.defaultKeyStatistics
        val summaryDetail = stats.summaryDetail
        val calendarEvents = stats.calendarEvents

        stock.currency = price?.currency?.let { Currency.valueOf(it) }
        stock.financialCurrency = financialData?.financialCurrency?.let { Currency.valueOf(it) }

        val exchangeRate =  exchangeRateService.getRate(stock.currency, stock.financialCurrency)

        stock.companyName = stats.quoteType?.shortName
        val currentPrice = price?.regularMarketPrice?.raw
        stock.currentPrice = currentPrice
        stock.currentPriceLocal = toLocalCurrency(currentPrice, exchangeRate)
        stock.change = percent(price?.regularMarketChangePercent?.raw)

        addEntry(stock.currentShares, defaultKeyStatistics.sharesOutstanding?.raw)

        val marketCap = stats.summaryDetail.marketCap?.raw
        addEntry(stock.marketCap, marketCap)

        //yahoo finance bug, it calculates it wrongly without conversion as USd market cap + cash in local currency - debt in local currency
        val totalDebt = fromLocalCurrency(getCurrentQuarter(stock.totalDebtQ), exchangeRate)
        val cash = fromLocalCurrency(getCurrentQuarter(stock.cashAndCashEquivalentsQ), exchangeRate)
        val ev = minus(plus(marketCap, totalDebt), cash)
        val evLocalCurrency = toLocalCurrency(ev, exchangeRate)
        addEntry(stock.enterpriseValue, ev)

        val shares = getCurrentYear(stock.currentShares)
        val cashPerShare = div(cash, shares)
        addEntry(stock.totalCashPerShare, cashPerShare)
        addEntry(stock.totalCashPerShareP, percent(div(cashPerShare, stock.currentPrice)))

        addEntry(stock.trailingPE, summaryDetail.trailingPE?.raw)
        addEntry(stock.forwardPE, summaryDetail.forwardPE?.raw)
        addEntry(stock.priceToSalesTrailing12Months, toLocalCurrency(summaryDetail.priceToSalesTrailing12Months?.raw, exchangeRate))
        addEntry(stock.priceBook, toLocalCurrency(defaultKeyStatistics.priceToBook?.raw, exchangeRate))

        addEntry(stock.enterpriseValueRevenue, div(evLocalCurrency, getCurrentYear(stock.revenue)))
        addEntry(stock.enterpriseValueEBIT, div(evLocalCurrency,  getCurrentYear(stock.ebit)))

        addEntry(stock.priceEarningGrowth, defaultKeyStatistics.pegRatio?.raw)

        addEntry(stock.heldByInsidersP, percent(defaultKeyStatistics.heldPercentInsiders?.raw))
        addEntry(stock.heldByInstitutionsP, percent(defaultKeyStatistics.heldPercentInstitutions?.raw))
        addEntry(stock.shortToFloatP, percent(defaultKeyStatistics.shortPercentOfFloat?.raw))

        val sharesShortPriorMonth = defaultKeyStatistics.sharesShortPriorMonth?.raw
        val sharesShort = defaultKeyStatistics.sharesShort?.raw
        addEntry(stock.sharesShortPrevMonthCompareP, percent(div(sharesShortPriorMonth, sharesShort)))

        addEntry(stock.exDividendDate, calendarEvents.exDividendDate?.fmt)
        addEntry(stock.fiveYearAvgDividendYield, summaryDetail.fiveYearAvgDividendYield?.raw)
        addEntry(stock.trailingAnnualDividendYield, percent(summaryDetail.trailingAnnualDividendYield?.raw))
        addEntry(stock.payoutRatioP, percent(summaryDetail.payoutRatio?.raw))
    }

    private fun <N : Number> toLocalCurrency(value: N?, exchangeRate: Double): Double? {
        return if (value == null) {
            null
        } else {
            if (value is Double )
                value * exchangeRate
            else if (value is Long)
                value * exchangeRate
            else
                throw IllegalArgumentException("Unsupported toLocalCurrency argument type " + value.javaClass)
        }
    }

    private fun <N : Number> fromLocalCurrency(value: N?, exchangeRate: Double): Double? {
        return if (value == null) {
            null
        } else {
            if (value is Double )
                value / exchangeRate
            else if (value is Long)
                value / exchangeRate
            else
                throw IllegalArgumentException("Unsupported fromLocalCurrency argument type " + value.javaClass)
        }
    }

    /**
     * Calculate fields which come from multiple partial responses, this has processed last to have data already added on the stock
     */
    private fun calculateCombinedParts(stock: Stock) {
        log.debug("calculateCombinedParts $stock")

        val exchangeRate =  exchangeRateService.getRate(stock.currency, stock.financialCurrency)

        val cashFlow = fromLocalCurrency(getCurrentQuarter(stock.freeCashFlowQ), exchangeRate)
        addEntry(stock.currentPriceToFreeCashFlow, div(
            div(getCurrentQuarter(stock.price), 4.0),
            div(cashFlow, getCurrentYear(stock.shares))
        ))

        stock.freeCashFlow
            .forEach { (date, cashFlow) ->
            addEntry(stock.priceToFreeCashFlow, div(
                priceAt(stock.price, date),
                div(fromLocalCurrency(cashFlow, exchangeRate), stock.shares[date])
            ), date)
        }
        stock.eps.forEach { (date, eps) ->
            addEntry(stock.pe, div(priceAt(stock.price, date), eps), date)
        }
        stock.epsQ.forEach { (date, epsQ) ->
            addEntry(stock.peQ, div(priceAt(stock.price, date), multiply(epsQ, 4.0)), date)
        }


    }

    private fun priceAt(stockPrices: SortedMap<LocalDate, Double?>, atDate: LocalDate): Double? {
        for (i in 0L..7L) {
            val date = atDate.minusDays(i)
            val priceAtDate = stockPrices[date]
            if (priceAtDate != null) {
                return priceAtDate
            }
        }
        return null
    }


    private fun calculateGrowth(stock: Stock) {
        log.debug("calculateGrowth $stock")

        stock.revenueGrowthQ = calcGrowth(stock.revenueQ, "revenueGrowthQ", 1000.0)
        stock.grossIncomeGrowthQ = calcGrowth(stock.grossIncomeQ, "grossIncomeGrowthQ", 100.0)
        stock.ebitGrowthQ = calcGrowth(stock.ebitQ, "ebitGrowthQ", 100.0)
        stock.operatingIncomeGrowthQ = calcGrowth(stock.operatingIncomeQ, "operatingIncomeGrowthQ", 100.0)
        stock.netIncomeGrowthQ = calcGrowth(stock.netIncomeQ, "netIncomeGrowthQ", 100.0)
        stock.profitMarginGrowthQ = calcGrowth(stock.profitMarginPQ, "profitMarginGrowthQ", 0.01)
        stock.grossMarginGrowthQ = calcGrowth(stock.grossMarginQ, "grossMarginGrowthQ", 0.01)
        stock.operatingMarginGrowthQ = calcGrowth(stock.operatingMarginQ, "operatingMarginGrowthQ", 0.01)
        stock.interestExpenseToOperativeIncomeGrowthQ = calcGrowth(stock.interestExpenseToOperativeIncomePQ, "interestExpenseToOperativeIncomeGrowthQ", 0.01)
        stock.operatingCashFlowGrowthQ = calcGrowth(stock.operatingCashFlowQ, "totalCashFromOperatingActivitiesGrowthQ", 100.0)
        stock.capitalExpendituresGrowthQ = calcGrowth(stock.capitalExpendituresQ, "capitalExpendituresGrowthQ", 100.0)
        stock.freeCashFlowGrowthQ = calcGrowth(stock.freeCashFlowQ, "freeCashFlowGrowthQ", 100.0)
        stock.cashGrowthQ = calcGrowth(stock.cashQ, "cashGrowthQ", 10.0)
        stock.cashAndCashEquivalentsGrowthQ = calcGrowth(stock.cashAndCashEquivalentsQ, "cashAndCashEquivalentsGrowthQ", 10.0)
        stock.inventoryGrowthQ = calcGrowth(stock.inventoryQ, "inventoryGrowthQ", 1.0)
        stock.currentAssetsGrowthQ = calcGrowth(stock.currentAssetsQ, "currentAssetsGrowthQ", 100.0)
        stock.currentLiabilitiesGrowthQ = calcGrowth(stock.currentLiabilitiesQ, "currentLiabilitiesGrowthQ", 100.0)
        stock.currentRatioGrowthQ = calcGrowth(stock.currentRatioQ, "currentRatioGrowthQ", 0.01)
        stock.totalLiabilitiesGrowthQ = calcGrowth(stock.totalLiabilitiesQ, "totalLiabilitiesGrowthQ", 100.0)
        stock.totalDebtGrowthQ = calcGrowth(stock.totalDebtQ, "totalLiabilitiesGrowthQ", 100.0)
        stock.totalDebtToEquityGrowthQ = calcGrowth(stock.totalDebtToEquityQ, "totalDebtToEquityGrowthQ", 0.01)
        stock.nonCurrentLiabilitiesToIncomeGrowthQ = calcGrowth(stock.nonCurrentLiabilitiesToIncomeQ, "nonCurrentLiabilitiesToIncomeGrowthQ", 0.01)
        stock.totalAssetsGrowthQ = calcGrowth(stock.totalAssetsQ, "totalAssetsGrowthQ", 100.0)
        stock.totalShareholdersEquityGrowthQ = calcGrowth(stock.totalShareholdersEquityQ, "totalShareholdersEquityGrowthQ", 10.0)
        stock.retainedEarningsGrowthQ = calcGrowth(stock.retainedEarningsQ, "retainedEarningsGrowthQ", 1.0)
        stock.stockRepurchasedGrowthQ = calcGrowth(stock.stockRepurchasedQ, "stockRepurchasedGrowthQ", 10.0)
        stock.epsGrowthQ = calcGrowth(stock.epsQ, "epsGrowthQ", 0.01)
        stock.peGrowthQ = calcGrowth(stock.peQ, "peGrowthQ", 0.01)
        stock.workingCapitalGrowthQ = calcGrowth(stock.workingCapitalQ, "workingCapitalQ", 100.0)
        stock.roicGrowthQ = calcGrowth(stock.roicPQ, "roicPQ", 0.01)
        stock.roaGrowthQ = calcGrowth(stock.roaPQ, "roaPQ", 0.01)
        stock.roeGrowthQ = calcGrowth(stock.roePQ, "roePQ", 0.01)

        stock.revenueGrowth = calcGrowth(stock.revenue, "revenue", 1000.0)
        stock.grossIncomeGrowth = calcGrowth(stock.grossIncome, "grossIncome", 100.0)
        stock.ebitGrowth = calcGrowth(stock.ebit, "ebit", 100.0)
        stock.netIncomeGrowth = calcGrowth(stock.netIncome, "netIncome", 100.0)
        stock.profitMarginGrowth = calcGrowth(stock.profitMarginP, "profitMargin", 0.01)
        stock.interestExpenseToOperativeIncomeGrowth = calcGrowth(stock.interestExpenseToOperativeIncomeP, "interestExpenseToOperativeIncomeGrowth", 0.01)
        stock.operatingCashFlowGrowth = calcGrowth(stock.operatingCashFlow, "totalCashFromOperatingActivities", 100.0)
        stock.capitalExpendituresGrowth = calcGrowth(stock.capitalExpenditures, "capitalExpenditures", 100.0)
        stock.freeCashFlowGrowth = calcGrowth(stock.freeCashFlow, "freeCashFlow", 100.0)
        stock.cashGrowth = calcGrowth(stock.cash, "cash", 10.0)
        stock.cashAndCashEquivalentsGrowth = calcGrowth(stock.cashAndCashEquivalents, "cashAndCashEquivalents", 10.0)
        stock.inventoryGrowth = calcGrowth(stock.inventory, "inventory", 1.0)
        stock.currentAssetsGrowth = calcGrowth(stock.currentAssets, "currentAssets", 100.0)
        stock.currentLiabilitiesGrowth = calcGrowth(stock.currentLiabilities, "currentLiabilities", 100.0)
        stock.currentRatioGrowth = calcGrowth(stock.currentRatio, "currentRatio", 0.01)
        stock.totalLiabilitiesGrowth = calcGrowth(stock.totalLiabilities, "totalLiabilities", 100.0)
        stock.totalDebtGrowth = calcGrowth(stock.totalDebt, "totalLiabilities", 100.0)
        stock.totalDebtToEquityGrowth = calcGrowth(stock.totalDebtToEquity, "totalDebtToEquity", 0.01)
        stock.nonCurrentLiabilitiesToIncomeGrowth = calcGrowth(stock.nonCurrentLiabilitiesToIncome, "nonCurrentLiabilitiesToIncomeGrowth", 0.01)
        stock.totalAssetsGrowth = calcGrowth(stock.totalAssets, "totalAssets", 100.0)
        stock.totalShareholdersEquityGrowth = calcGrowth(stock.totalShareholdersEquity, "totalShareholdersEquity", 10.0)
        stock.retainedEarningsGrowth = calcGrowth(stock.retainedEarnings, "retainedEarningsGrowth", 1.0)
        stock.stockRepurchasedGrowth = calcGrowth(stock.stockRepurchased, "stockRepurchased", 10.0)
        stock.epsGrowth = calcGrowth(stock.eps, "eps", 0.01)
        stock.peGrowth = calcGrowth(stock.pe, "pe", 0.01)
        stock.bookValuePerShareGrowth = calcGrowth(stock.bookValuePerShare, "bookValuePerShare", 0.01)
        stock.freeCashFlowPerShareGrowth = calcGrowth(stock.freeCashFlowPerShare, "freeCashFlowPerShare", 0.01)
        stock.grossMarginGrowth = calcGrowth(stock.grossMargin, "grossMargin", 0.01)
        stock.operatingCashFlowGrowth = calcGrowth(stock.operatingCashFlow, "operatingCashFlow", 100.0)
        stock.operatingIncomeGrowth = calcGrowth(stock.operatingIncome, "operatingIncome", 100.0)
        stock.operatingMarginGrowth = calcGrowth(stock.operatingMargin, "operatingMargin", 0.01)
        stock.sharesGrowth = calcGrowth(stock.shares, "shares", 100.0)
        stock.workingCapitalGrowth = calcGrowth(stock.workingCapital, "workingCapital", 100.0)
        stock.roicGrowth = calcGrowth(stock.roicP, "roicP", 0.01)
        stock.roaGrowth = calcGrowth(stock.roaP, "roaP", 0.01)
        stock.roeGrowth = calcGrowth(stock.roeP, "roeP", 0.01)
    }

    private fun <T : Number> calcGrowth(statPeriods: SortedMap<LocalDate, T?>, statName: String, significanceThreshold: Double): SortedMap<LocalDate, Double?> {
        if (statPeriods.size < 2) {
            return sortedMapOf()
        }
        val periodicalGrowth = TreeMap<LocalDate, Double?>()
        var previousValue: T? = null
        for (indexedPeriod in statPeriods.entries.withIndex()) {
            val currentValue = indexedPeriod.value.value
            if (indexedPeriod.index != 0) {
                val growthAtDate = indexedPeriod.value.key
                val value = percentGrowth(currentValue, previousValue, statName, significanceThreshold)
                periodicalGrowth[growthAtDate] = if(value == null) null else round(value)
            }
            previousValue = currentValue
        }
        return periodicalGrowth
    }

    fun calculateRule1(stock: Stock) {
        log.debug("calculateRule1 $stock")

        addEntry(stock.revenue1Y, cumulativeGrowthRate(getCurrentYear(stock.revenue), getYearsBefore(stock.revenue, 1), 1, "revenue1Y", MILLION))
        addEntry(stock.revenue3Y, cumulativeGrowthRate(getCurrentYear(stock.revenue), getYearsBefore(stock.revenue, 3), 3, "revenue3Y", MILLION))
        addEntry(stock.revenue5Y, cumulativeGrowthRate(getCurrentYear(stock.revenue), getYearsBefore(stock.revenue, 5), 5, "revenue5Y", MILLION))

        addEntry(stock.eps1Y, cumulativeGrowthRate(getCurrentYear(stock.eps), getYearsBefore(stock.eps, 1), 1, "eps1Y", 0.1))
        addEntry(stock.eps3Y, cumulativeGrowthRate(getCurrentYear(stock.eps), getYearsBefore(stock.eps, 3), 3, "eps3Y", 0.1))
        addEntry(stock.eps5Y, cumulativeGrowthRate(getCurrentYear(stock.eps), getYearsBefore(stock.eps, 5), 5, "eps5Y", 0.1))

        addEntry(stock.bps1Y, cumulativeGrowthRate(getCurrentYear(stock.bookValuePerShare), getYearsBefore(stock.bookValuePerShare, 1), 1, "bps1Y", 0.1))
        addEntry(stock.bps3Y, cumulativeGrowthRate(getCurrentYear(stock.bookValuePerShare), getYearsBefore(stock.bookValuePerShare, 3), 3, "bps3Y", 0.1))
        addEntry(stock.bps5Y, cumulativeGrowthRate(getCurrentYear(stock.bookValuePerShare), getYearsBefore(stock.bookValuePerShare, 5), 5, "bps5Y", 0.1))

        addEntry(stock.cash1Y, cumulativeGrowthRate(getCurrentYear(stock.freeCashFlow), getYearsBefore(stock.freeCashFlow, 1), 1, "cash1Y", THOUSAND))
        addEntry(stock.cash3Y, cumulativeGrowthRate(getCurrentYear(stock.freeCashFlow), getYearsBefore(stock.freeCashFlow, 3), 3, "cash3Y", THOUSAND))
        addEntry(stock.cash5Y, cumulativeGrowthRate(getCurrentYear(stock.freeCashFlow), getYearsBefore(stock.freeCashFlow, 5), 5, "cash5Y", THOUSAND))

        addEntry(stock.pe1Y, cumulativeGrowthRate(getCurrentYear(stock.pe), getYearsBefore(stock.pe, 1), 1, "pe1Y", 0.1))
        addEntry(stock.pe3Y, cumulativeGrowthRate(getCurrentYear(stock.pe), getYearsBefore(stock.pe, 3), 3, "pe3Y", 0.1))
        addEntry(stock.pe5Y, cumulativeGrowthRate(getCurrentYear(stock.pe), getYearsBefore(stock.pe, 5), 5, "pe5Y", 0.1))

        //multiple slightly different ways how to calculate, common one is (net income - dividends) / (total assets - current liabilities - cash and cash equivalents)

        for (netIncomeEntry in stock.netIncomeQ.entries) {
            val quarter = netIncomeEntry.key
            val netIncome = multiply(netIncomeEntry.value, 4)
            val totalAssets = stock.totalAssetsQ[quarter]
            val cash = stock.cashAndCashEquivalentsQ[quarter]

            addEntry(stock.roicPQ, percent(
                div(
                    netIncome,
                    minus(totalAssets, cash)
                )
            ), quarter)
        }

        for (netIncomeEntry in stock.netIncome.entries) {
            val year = netIncomeEntry.key
            val netIncome = netIncomeEntry.value
            val totalAssets = stock.totalAssets[year]
            val cash = stock.cashAndCashEquivalents[year]

            addEntry(stock.roicP, percent(
                div(
                    netIncome,
                    minus(totalAssets, cash)
                )
            ), year)
        }

        for (netIncomeEntry in stock.netIncomeQ.entries) {
            val quarter = netIncomeEntry.key
            val netIncome = multiply(netIncomeEntry.value, 4)
            val equity = stock.totalShareholdersEquityQ[quarter]

            addEntry(stock.roePQ, percent(
                div(
                    netIncome,
                    equity
                )
            ), quarter)
        }

        for (netIncomeEntry in stock.netIncome.entries) {
            val year = netIncomeEntry.key
            val netIncome = netIncomeEntry.value
            val equity = stock.totalShareholdersEquity[year]

            addEntry(stock.roeP, percent(
                div(
                    netIncome,
                    equity
                )
            ), year)
        }

        for (netIncomeEntry in stock.netIncomeQ.entries) {
            val quarter = netIncomeEntry.key
            val netIncome = multiply(netIncomeEntry.value, 4)
            val equity = stock.totalAssetsQ[quarter]

            addEntry(stock.roaPQ, percent(
                div(
                    netIncome,
                    equity
                )
            ), quarter)
        }

        for (netIncomeEntry in stock.netIncome.entries) {
            val year = netIncomeEntry.key
            val netIncome = netIncomeEntry.value
            val equity = stock.totalAssets[year]

            addEntry(stock.roaP, percent(
                div(
                    netIncome,
                    equity
                )
            ), year)
        }

        addEntry(stock.roic1Y, cumulativeGrowthRate(getCurrentYear(stock.roicP), getYearsBefore(stock.roicP, 1), 1, "roic1Y", 0.001))
        addEntry(stock.roic3Y, cumulativeGrowthRate(getCurrentYear(stock.roicP), getYearsBefore(stock.roicP, 3), 3, "roic3Y", 0.001))

        val estimatedEpsGrowthRate = getCurrentYear(stock.bps5Y) ?: getCurrentYear(stock.bps3Y)
        addEntry(stock.rule1GrowthRate, min(estimatedEpsGrowthRate, getCurrentYear(stock.growthEstimate5y)))
        addEntry(stock.defaultPE, multiply(getCurrentYear(stock.rule1GrowthRate), 2.0))
        addEntry(stock.historicalPE, average(*stock.pe.values.toTypedArray()))
        addEntry(stock.rule1PE, average(getCurrentYear(stock.historicalPE), getCurrentYear(stock.defaultPE)))
        addEntry(stock.currentEps, getCurrentYear(stock.eps) ?: multiply(getCurrentYear(stock.epsQ), 4.0))
        val numberOfYears = 10.0
        val epsGrowthEstimate = plus(div(getCurrentYear(stock.rule1GrowthRate), 100), 1.0)
        addEntry(stock.futureEPS10Years, multiply(getCurrentYear(stock.currentEps), epsGrowthEstimate?.pow(numberOfYears)))

        val rule1pe = getCurrentYear(stock.rule1PE)
        val futureEps10Years = getCurrentYear(stock.futureEPS10Years)
        if (rule1pe != null && rule1pe > 0.0 && futureEps10Years != null && futureEps10Years > 0.0) {
            val futurePrice10Years = multiply(rule1pe, futureEps10Years)
            addEntry(stock.futurePrice10Years, futurePrice10Years)

            val stickerPrice15pcGrowth = div(futurePrice10Years, (1 + 0.15).pow(numberOfYears))
            addEntry(stock.stickerPrice15pcGrowth, stickerPrice15pcGrowth)
            val stickerPrice5pcGrowth = div(futurePrice10Years, (1 + 0.05).pow(numberOfYears))
            addEntry(stock.stickerPrice5pcGrowth, stickerPrice5pcGrowth)

            addEntry(stock.belowStickerPrice5P, percent(div(minus(stickerPrice5pcGrowth, stock.currentPrice), stock.currentPrice)))
        }
    }

    private fun <T : Number> getYearsBefore(stat: SortedMap<LocalDate, T?>, yearsBefore: Int, date: LocalDate = LocalDate.now()): T? {
        val yearBefore = date.minusYears(yearsBefore.toLong())
        val statCloseToDateFirst = stat.keys.sortedBy { statDate ->
            val daysBetween = ChronoUnit.DAYS.between(statDate, yearBefore)
            if (daysBetween > 0) daysBetween else Long.MAX_VALUE
        }
        val closestPeriod = statCloseToDateFirst.firstOrNull() ?: return null
        return if (olderThanOneYearFrom(closestPeriod, yearBefore)) null else stat[closestPeriod]
    }

    private fun <T : Number> getCurrentYear(stat: SortedMap<LocalDate, T?>): T? {
        val lastEntry = stat.entries.lastOrNull() ?: return null
        return if (olderThanOneYearFromNow(lastEntry.key)) null else lastEntry.value
    }

    private fun <T : Number> getCurrentQuarter(stat: SortedMap<LocalDate, T?>): T? {
        val lastEntry = stat.entries.lastOrNull() ?: return null
        return if (olderThanOneQuarterFromNow(lastEntry.key)) null else lastEntry.value
    }

    private fun olderThanOneQuarterFromNow(timelineEntry: LocalDate): Boolean {
        return olderThanOneQuarterFrom(timelineEntry, LocalDate.now())
    }

    private fun olderThanOneQuarterFrom(timelineEntry: LocalDate, date: LocalDate): Boolean {
        if (ChronoUnit.MONTHS.between(timelineEntry, date) > 4) {   //accept up to 5 months in between, as the current quarter report might not be published yet
            return true
        }
        return false
    }

    private fun olderThanOneYearFromNow(timelineEntry: LocalDate): Boolean {
        return olderThanOneYearFrom(timelineEntry, LocalDate.now())
    }

    private fun olderThanOneYearFrom(timelineEntry: LocalDate, date: LocalDate): Boolean {
        if (ChronoUnit.MONTHS.between(date, timelineEntry) > 15) { //accept up to 15 months in between, as the current year report might not be published yet
            return true
        }
        return false
    }

}