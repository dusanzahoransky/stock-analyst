package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.ExchangeRateClient
import com.github.dusanzahoransky.stockanalyst.client.MorningStartClient
import com.github.dusanzahoransky.stockanalyst.client.YahooFinanceClient
import com.github.dusanzahoransky.stockanalyst.model.Ticker
import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Interval
import com.github.dusanzahoransky.stockanalyst.model.enums.Range
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.*
import com.github.dusanzahoransky.stockanalyst.model.ms.keyratios.Result
import com.github.dusanzahoransky.stockanalyst.model.yahoo.analysis.AnalysisResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.chart.ChartResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.financials.FinancialsResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.holders.HoldersResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.statistics.StatisticsResponse
import com.github.dusanzahoransky.stockanalyst.repository.*
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils.CacheContext
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils.Companion.useCacheDynamicData
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils.Companion.useCacheFinancialsData
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.multiply
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percent
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percentGrowth
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.plus
import com.github.dusanzahoransky.stockanalyst.util.FormattingUtils.Companion.epochSecToLocalDate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class StockService @Autowired constructor(
    val watchlistRepo: WatchlistRepo,
    val stockRepo: StockRepo,
    val yahooFinanceClient: YahooFinanceClient,
    val exchangeRateClient: ExchangeRateClient,
    val morningStarClient: MorningStartClient,
    val chartRepo: ChartRepo,
    val financialsRepo: FinancialsRepo,
    val analysisRepo: AnalysisRepo,
    val holdersRepo: HoldersRepo,
    val statisticsRepo: StatisticsRepo,
    val krfRepo: KeyRatiosFinancialsRepo
) {
    //TODO
//    companion object {
//        val CHART_SAMPLING_INTERVAL: Period = Period.ofDays(7)
//    }

    val log = LoggerFactory.getLogger(this::class.java)!!


    fun getWatchlistStocks(watchlist: Watchlist, refreshDynamicData: Boolean, refreshFinancials: Boolean, mockData: Boolean, refreshOlderThan: LocalDate): List<Stock> {
        val watchlistTickers = watchlistRepo.getWatchlistTickers(watchlist)

        val cacheCtx = CacheContext(refreshDynamicData, refreshFinancials, mockData, refreshOlderThan);

        return watchlistTickers.mapNotNull { ticker ->
            findOrLoadStock(ticker, cacheCtx)
        }
    }

    private fun findOrLoadStock(ticker: Ticker, cacheCtx: CacheContext): Stock? {
        val partialData = loadStock(ticker, cacheCtx)

        var stock = stockRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        if (stock == null) {
            stock = Stock(symbol = ticker.symbol, exchange = ticker.exchange)

            processFinancials(partialData.financials.response, stock)
            processStatistics(partialData.statistics.response, stock)
            processAnalysis(partialData.analysis.response, stock)
            processHolders(partialData.holders.response, stock)
            processChart(partialData.chart.response, stock)
            processKrf(partialData.krf.results, stock)
        } else {

            if (stock.financialsLastUpdated.isBefore(partialData.financials.getLastRefreshDate())) {
                processFinancials(partialData.financials.response, stock)
            }
            if (stock.statisticsLastUpdated.isBefore(partialData.statistics.getLastRefreshDate())) {
                processStatistics(partialData.statistics.response, stock)
            }
            if (stock.analysisLastUpdated.isBefore(partialData.analysis.getLastRefreshDate())) {
                processAnalysis(partialData.analysis.response, stock)
            }
            if (stock.holdersLastUpdated.isBefore(partialData.holders.getLastRefreshDate())) {
                processHolders(partialData.holders.response, stock)
            }
            if (stock.chartLastUpdated.isBefore(partialData.chart.getLastRefreshDate())) {
                processChart(partialData.chart.response, stock)
            }
            if (stock.chartLastUpdated.isBefore(partialData.krf.getLastRefreshDate())) {
                processKrf(partialData.krf.results, stock)
            }
        }

        calculateCombinedParts(stock)
        calculateGrowth(stock)

        //do not save mock data
        if (cacheCtx.mockData) {
            return stock
        }
        log.debug("Saving ${if (stock.id == null) "new" else "updated"} stock $ticker")
        return stockRepo.save(stock)
    }


    data class PartialStockData(
        val chart: Chart,
        val financials: Financials,
        val analysis: Analysis,
        val statistics: Statistics,
        val holders: Holders,
        val krf: KeyRatiosFinancials
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
        val krf = loadKeyRatiosFinancials(ticker, cacheCtx)
        return PartialStockData(chart, financials, analysis, statistics, holders, krf)
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

    fun loadKeyRatiosFinancials(ticker: Ticker, cacheCtx: CacheContext): KeyRatiosFinancials {
        val cachedData = krfRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCacheDynamicData(cacheCtx, cachedData)) {
            log.debug("Retrieving Analysis from cache: $ticker")
            return cachedData!!
        }

        val krfResponse = morningStarClient.getKeyRatiosFinancials(ticker, cacheCtx.mockData)

        val krf = KeyRatiosFinancials(null, ticker.symbol, ticker.exchange, LocalDate.now(), krfResponse.results)

        //do not cache mock data
        if (cacheCtx.mockData) {
            return krf
        }

        //delete previous version
        krfRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { krfRepo.delete(it) }
        //store new version
        log.debug("Saving KeyRatiosFinancials $ticker into DB")
        return krfRepo.insert(krf)
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

    private fun processKrf(results: List<Result>, stock: Stock): List<StockRatiosTimeline> {
        val stockRatios = mutableListOf<StockRatiosTimeline>()

        for (result in results) {
            val periodDate = LocalDate.parse(result.periodEndDate)
            val firstSection = result.sections[0]

            for (item in firstSection.lineItems) {
                when (item.label) {
                    "BOOK VALUE PER SHARE *" -> item.value?.let { stock.bookValuePerShare[periodDate] = it }
                    "CAP SPENDING" -> item.value?.let { stock.capSpending[periodDate] = it }
                    "DIVIDENDS" -> item.value?.let { stock.dividends[periodDate] = it }
                    "EARNINGS PER SHARE" -> item.value?.let { stock.eps[periodDate] = it }
                    "FREE CASH FLOW" -> item.value?.let { stock.freeCashFlow[periodDate] = it.toLong() }
                    "FREE CASH FLOW PER SHARE *" -> item.value?.let { stock.freeCashFlowPerShare[periodDate] = it }
                    "GROSS MARGIN %" -> item.value?.let { stock.grossMargin[periodDate] = it }
                    "NET INCOME" -> item.value?.let { stock.netIncome[periodDate] = it.toLong() }
                    "OPERATING CASH FLOW" -> item.value?.let { stock.operatingCashFlow[periodDate] = it }
                    "OPERATING INCOME" -> item.value?.let { stock.operatingIncome[periodDate] = it }
                    "OPERATING MARGIN %" -> item.value?.let { stock.operatingMargin[periodDate] = it }
                    "PAYOUT RATIO % *" -> item.value?.let { stock.payoutRatio[periodDate] = it }
                    "REVENUE" -> item.value?.let { stock.revenue[periodDate] = it.toLong() }
                    "SHARES" -> item.value?.let { stock.shares[periodDate] = it }
                    "WORKING CAPITAL" -> item.value?.let { stock.workingCapital[periodDate] = it }
                }

            }

            if(stock.revenue[periodDate] != null && stock.netIncome[periodDate] != null){
                stock.profitMargin[periodDate] = div(stock.netIncome[periodDate], stock.revenue[periodDate])
            }
        }

        return stockRatios
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

        financials.price?.currency?.let { it -> Currency.valueOf(it) }?.let { stock.currency = it }
        earnings?.financialCurrency?.let { it -> Currency.valueOf(it) }?.let { stock.financialCurrency = it }

        val exchangeRate = getExchangeRate(stock.currency, stock.financialCurrency, stock)

        val yearEnds = timeSeries?.timestamp
            ?.reversed()
            ?.map { epochSecToLocalDate(it) }
        val quarterEnds = balSheetQuarterly
            ?.map { it.endDate.raw }
            ?.map { epochSecToLocalDate(it) }

        stock.lastReportedQuarter = quarterEnds?.getOrNull(0)

        val epsQuarterly = earnings?.earningsChart?.quarterly?.reversed()
        val annualDilutedEPS = timeSeries?.annualDilutedEPS?.reversed()


        if (quarterEnds != null) {
            for (i in quarterEnds.indices) {
                val quarter = quarterEnds[i]
                val revenue = incomeStmQuarterly[i]?.totalRevenue?.raw
                addEntry(stock.revenueQ, revenue, quarter)
                addEntry(stock.grossIncomeQ, incomeStmQuarterly[i]?.grossProfit?.raw, quarter)
                addEntry(stock.ebitQ, incomeStmQuarterly[i]?.ebit?.raw, quarter)
                val netIncome = incomeStmQuarterly[i]?.netIncome?.raw
                addEntry(stock.netIncomeQ, netIncome, quarter)
                if(revenue != null && netIncome != null){
                    stock.profitMarginQ[quarter] = div(netIncome, revenue)
                }
                addEntry(stock.totalCashFromOperatingActivitiesQ, cashFlowQuarterly[i]?.totalCashFromOperatingActivities?.raw, quarter)
                addEntry(stock.capitalExpendituresQ, cashFlowQuarterly[i]?.capitalExpenditures?.raw, quarter)
                addEntry(stock.stockRepurchasedQ, cashFlowQuarterly[i]?.repurchaseOfStock?.raw, quarter)

                addEntry(stock.cashQ, balSheetQuarterly[i]?.cash?.raw, quarter)
                addEntry(stock.inventoryQ, balSheetQuarterly[i]?.inventory?.raw, quarter)
                val currentAssets = balSheetQuarterly[i]?.totalCurrentAssets?.raw
                addEntry(stock.currentAssetsQ, currentAssets, quarter)
                val currentLiabilities = balSheetQuarterly[i]?.totalCurrentLiabilities?.raw
                addEntry(stock.currentLiabilitiesQ, currentLiabilities, quarter)
                addEntry(stock.currentRatioQ, div(currentAssets, currentLiabilities), quarter)
                addEntry(stock.totalAssetsQ, balSheetQuarterly[i]?.totalAssets?.raw, quarter)
                val totalLiabilitiesQ = balSheetQuarterly[i]?.totalLiab?.raw
                addEntry(stock.totalLiabilitiesQ, totalLiabilitiesQ, quarter)
                val totalShareholdersEquityQ = balSheetQuarterly[i]?.totalStockholderEquity?.raw
                addEntry(stock.totalShareholdersEquityQ, totalShareholdersEquityQ, quarter)
                addEntry(stock.totalDebtToEquityQ, div(totalLiabilitiesQ, totalShareholdersEquityQ), quarter)

                addEntry(stock.freeCashFlowQ, plus(cashFlowQuarterly[i]?.totalCashFromOperatingActivities?.raw, cashFlowQuarterly[i]?.capitalExpenditures?.raw), quarter)

                addEntry(stock.epsQ, multiply(epsQuarterly?.get(i)?.actual?.raw?.toDouble(), exchangeRate), quarter)

            }
        }

        if (yearEnds != null) {
            for (i in yearEnds.indices) {
                val year = yearEnds[i]
                addEntry(stock.revenue, incomeStm[i]?.totalRevenue?.raw, year)
                addEntry(stock.revenue, incomeStm[i]?.totalRevenue?.raw, year)
                addEntry(stock.grossIncome, incomeStm[i]?.grossProfit?.raw, year)
                addEntry(stock.ebit, incomeStm[i]?.ebit?.raw, year)
                addEntry(stock.netIncome, incomeStm[i]?.netIncome?.raw, year)

                addEntry(stock.totalCashFromOperatingActivities, cashFlow[i]?.totalCashFromOperatingActivities?.raw, year)
                addEntry(stock.capitalExpenditures, cashFlow[i]?.capitalExpenditures?.raw, year)
                addEntry(stock.stockRepurchased, cashFlow[i]?.repurchaseOfStock?.raw, year)

                addEntry(stock.cash, balSheet[i]?.cash?.raw, year)
                addEntry(stock.inventory, balSheet[i]?.inventory?.raw, year)
                val currentAssets = balSheet[i]?.totalCurrentAssets?.raw
                addEntry(stock.currentAssets, currentAssets, year)
                val currentLiabilities = balSheet[i]?.totalCurrentLiabilities?.raw
                addEntry(stock.currentLiabilities, currentLiabilities, year)
                addEntry(stock.currentRatio, div(currentAssets, currentLiabilities), year)
                addEntry(stock.totalAssets, balSheet[i]?.totalAssets?.raw, year)
                val totalLiabilities = balSheet[i]?.totalLiab?.raw
                addEntry(stock.totalLiabilities, totalLiabilities, year)
                val totalShareholdersEquity = balSheet[i]?.totalStockholderEquity?.raw
                addEntry(stock.totalShareholdersEquity, totalShareholdersEquity, year)
                addEntry(stock.totalDebtToEquity, div(totalLiabilities, totalShareholdersEquity), year)

                addEntry(stock.freeCashFlow, plus(cashFlow[i]?.totalCashFromOperatingActivities?.raw, cashFlow[i]?.capitalExpenditures?.raw), year)

                addEntry(stock.eps, multiply(annualDilutedEPS?.get(i)?.reportedValue?.raw?.toDouble(), exchangeRate), year)
            }
        }

    }


    private fun <T> addEntry(statTimelineMap: SortedMap<LocalDate, T>, valueAtDate: T?, date: LocalDate? = LocalDate.now()) {
        if (valueAtDate != null) {
            statTimelineMap[date] = valueAtDate
        }
    }

    private fun processAnalysis(analysis: AnalysisResponse, stock: Stock) {
        log.debug("processAnalysis $stock")
        addEntry(stock.growthEstimate5y, analysis.earningsTrend?.trend?.firstOrNull { it.period == "+5y" }?.growth?.raw)
    }

    private fun processHolders(holders: HoldersResponse, stock: Stock) {
        log.debug("processHolders $stock")
        addEntry(stock.buyPercentInsiderShares, holders.netSharePurchaseActivity?.buyPercentInsiderShares?.raw)
        addEntry(stock.sellPercentInsiderShares, holders.netSharePurchaseActivity?.sellPercentInsiderShares?.raw)
    }

    private fun processStatistics(stats: StatisticsResponse, stock: Stock) {
        log.debug("processStatistics $stock")
        val exchangeRate = getExchangeRate(stock.currency, stock.financialCurrency, stock)
        val financialData = stats.financialData
        val price = stats.price
        val defaultKeyStatistics = stats.defaultKeyStatistics
        val summaryDetail = stats.summaryDetail
        val calendarEvents = stats.calendarEvents

        price?.currency?.let { it -> Currency.valueOf(it) }?.let { stock.currency = it }
        financialData?.financialCurrency?.let { it -> Currency.valueOf(it) }?.let { stock.financialCurrency = it }

        stock.companyName = stats.quoteType?.shortName
        stock.currentPrice = price?.regularMarketPrice?.raw
        stock.change = percent(price?.regularMarketChangePercent?.raw)

        val today = LocalDate.now()
        addEntry(stock.enterpriseValue, defaultKeyStatistics.enterpriseValue?.raw)

        addEntry(stock.targetLowPrice, financialData.targetLowPrice?.raw)
        addEntry(stock.targetMedianPrice, financialData.targetMedianPrice?.raw)

        val totalCashPerShare = financialData.totalCashPerShare?.raw
        addEntry(stock.totalCashPerShare, totalCashPerShare)
        addEntry(stock.totalCashPerSharePercent, percent(div(totalCashPerShare, stock.currentPrice)))

        addEntry(stock.trailingPE, summaryDetail.trailingPE?.raw)
        addEntry(stock.forwardPE, summaryDetail.forwardPE?.raw)
        addEntry(stock.priceToSalesTrailing12Months, summaryDetail.priceToSalesTrailing12Months?.raw)
        if (exchangeRate == 1.0) {
            addEntry(stock.priceBook, defaultKeyStatistics.priceToBook?.raw)
        } else {
            // P /B = Market Price per Share / BVPS
            //BVPS = (Total Equity − Preferred Equity) / Total Shares Outstanding
            //​	https://www.investopedia.com/terms/b/bvps.asp
        }
        addEntry(stock.enterpriseValueRevenue, defaultKeyStatistics.enterpriseToRevenue?.raw)
        addEntry(stock.enterpriseValueEBITDA, defaultKeyStatistics.enterpriseToEbitda?.raw)

        addEntry(stock.priceEarningGrowth, defaultKeyStatistics.pegRatio?.raw)

        addEntry(stock.week52Change, percent(defaultKeyStatistics?.get52WeekChange()?.raw))
        addEntry(stock.week52Low, summaryDetail.fiftyTwoWeekLow?.raw)
        addEntry(stock.week52High, summaryDetail.fiftyTwoWeekHigh?.raw)

        addEntry(stock.heldByInsiders, percent(defaultKeyStatistics.heldPercentInsiders?.raw))
        addEntry(stock.heldByInstitutions, percent(defaultKeyStatistics.heldPercentInstitutions?.raw))
        addEntry(stock.shortToFloat, percent(defaultKeyStatistics.shortPercentOfFloat?.raw))

        val sharesShortPriorMonth = defaultKeyStatistics.sharesShortPriorMonth?.raw
        val sharesShort = defaultKeyStatistics.sharesShort?.raw
        if (sharesShortPriorMonth != null && sharesShort != null) {
            addEntry(stock.sharesShortPrevMonthCompare, percent(sharesShortPriorMonth / sharesShort))
        }

        addEntry(stock.exDividendDate, calendarEvents.exDividendDate?.fmt)
        addEntry(stock.fiveYearAvgDividendYield, summaryDetail.fiveYearAvgDividendYield?.raw)
        addEntry(stock.trailingAnnualDividendYield, percent(summaryDetail.trailingAnnualDividendYield?.raw))
        addEntry(stock.payoutRatio, percent(summaryDetail.payoutRatio?.raw))

        addEntry(stock.shares, defaultKeyStatistics.sharesOutstanding?.raw)
    }


    /**
     * Calculate fields which come from multiple partial responses, this has processed last to have data already added on the stock
     */
    private fun calculateCombinedParts(stock: Stock) {
        log.debug("calculateCombinedParts $stock")
        stock.freeCashFlowQ.forEach { (date, cashFlow) ->
            addEntry(stock.freeCashFlowToPriceQ, div(cashFlow.toDouble(), stock.price[date]), date)
        }
        stock.freeCashFlow.forEach { (date, cashFlow) ->
            addEntry(stock.freeCashFlowToPrice, div(cashFlow.toDouble(), stock.price[date]), date)
        }
        stock.netIncome.forEach{ (date, income) ->
            addEntry(stock.pe, div(income.toDouble(), stock.price[date]), date)
        }
        stock.netIncomeQ.forEach{ (date, income) ->
            addEntry(stock.peQ, div(income.toDouble(), stock.price[date]), date)
        }


    }


    private fun calculateGrowth(stock: Stock) {
        log.debug("calculateGrowth $stock")

        stock.revenueGrowthQ = calcGrowth(stock.revenueQ, "revenueGrowthQ", 1000.0)
        stock.grossIncomeGrowthQ = calcGrowth(stock.grossIncomeQ, "grossIncomeGrowthQ", 100.0)
        stock.ebitGrowthQ = calcGrowth(stock.ebitQ, "ebitGrowthQ", 100.0)
        stock.netIncomeGrowthQ = calcGrowth(stock.netIncomeQ, "netIncomeGrowthQ", 100.0)
        stock.profitMarginGrowthQ = calcGrowth(stock.profitMarginQ, "profitMarginGrowthQ", 100.0)
        stock.totalCashFromOperatingActivitiesGrowthQ = calcGrowth(stock.totalCashFromOperatingActivitiesQ, "totalCashFromOperatingActivitiesGrowthQ", 100.0)
        stock.capitalExpendituresGrowthQ = calcGrowth(stock.capitalExpendituresQ, "capitalExpendituresGrowthQ", 100.0)
        stock.freeCashFlowGrowthQ = calcGrowth(stock.freeCashFlowQ, "freeCashFlowGrowthQ", 100.0)
        stock.cashGrowthQ = calcGrowth(stock.cashQ, "cashGrowthQ", 10.0)
        stock.inventoryGrowthQ = calcGrowth(stock.inventoryQ, "inventoryGrowthQ", 1.0)
        stock.currentAssetsGrowthQ = calcGrowth(stock.currentAssetsQ, "currentAssetsGrowthQ", 100.0)
        stock.currentLiabilitiesGrowthQ = calcGrowth(stock.currentLiabilitiesQ, "currentLiabilitiesGrowthQ", 100.0)
        stock.currentRatioGrowthQ = calcGrowth(stock.currentRatioQ, "currentRatioGrowthQ", 0.01)
        stock.totalLiabilitiesGrowthQ = calcGrowth(stock.totalLiabilitiesQ, "totalLiabilitiesGrowthQ", 100.0)
        stock.totalDebtToEquityGrowthQ = calcGrowth(stock.totalDebtToEquityQ, "totalDebtToEquityGrowthQ", 100.0)
        stock.totalAssetsGrowthQ = calcGrowth(stock.totalAssetsQ, "totalAssetsGrowthQ", 100.0)
        stock.totalShareholdersEquityGrowthQ = calcGrowth(stock.totalShareholdersEquityQ, "totalShareholdersEquityGrowthQ", 100.0)
        stock.totalLiabilitiesToEquityGrowthQ = calcGrowth(stock.totalLiabilitiesToEquityQ, "totalLiabilitiesToEquityGrowthQ", 100.0)
        stock.stockRepurchasedGrowthQ = calcGrowth(stock.stockRepurchasedQ, "stockRepurchasedGrowthQ", 10.0)
        stock.epsGrowthQ = calcGrowth(stock.epsQ, "epsGrowthQ", 0.01)
        stock.peGrowthQ = calcGrowth(stock.peQ, "peGrowthQ", 0.01)

        stock.revenueGrowth = calcGrowth(stock.revenue, "revenue", 1000.0)
        stock.grossIncomeGrowth = calcGrowth(stock.grossIncome, "grossIncome", 100.0)
        stock.ebitGrowth = calcGrowth(stock.ebit, "ebit", 100.0)
        stock.netIncomeGrowth = calcGrowth(stock.netIncome, "netIncome", 100.0)
        stock.profitMarginGrowth = calcGrowth(stock.profitMargin, "profitMargin", 100.0)
        stock.totalCashFromOperatingActivitiesGrowth = calcGrowth(stock.totalCashFromOperatingActivities, "totalCashFromOperatingActivities", 100.0)
        stock.capitalExpendituresGrowth = calcGrowth(stock.capitalExpenditures, "capitalExpenditures", 100.0)
        stock.freeCashFlowGrowth = calcGrowth(stock.freeCashFlow, "freeCashFlow", 100.0)
        stock.cashGrowth = calcGrowth(stock.cash, "cash", 10.0)
        stock.inventoryGrowth = calcGrowth(stock.inventory, "inventory", 1.0)
        stock.currentAssetsGrowth = calcGrowth(stock.currentAssets, "currentAssets", 100.0)
        stock.currentLiabilitiesGrowth = calcGrowth(stock.currentLiabilities, "currentLiabilities", 100.0)
        stock.currentRatioGrowth = calcGrowth(stock.currentRatio, "currentRatio", 0.01)
        stock.totalLiabilitiesGrowth = calcGrowth(stock.totalLiabilities, "totalLiabilities", 100.0)
        stock.totalDebtToEquityGrowth = calcGrowth(stock.totalDebtToEquity, "totalDebtToEquity", 0.01)
        stock.totalAssetsGrowth = calcGrowth(stock.totalAssets, "totalAssets", 100.0)
        stock.totalShareholdersEquityGrowth = calcGrowth(stock.totalShareholdersEquity, "totalShareholdersEquity", 0.01)
        stock.totalLiabilitiesToEquityGrowth = calcGrowth(stock.totalLiabilitiesToEquity, "totalLiabilitiesToEquity", 0.01)
        stock.stockRepurchasedGrowth = calcGrowth(stock.stockRepurchased, "stockRepurchased", 10.0)
        stock.epsGrowth = calcGrowth(stock.eps, "eps", 0.01)
        stock.peGrowth = calcGrowth(stock.pe, "pe", 0.01)
        stock.bookValuePerShareGrowth = calcGrowth(stock.bookValuePerShare, "bookValuePerShare", 0.01)
        stock.capSpendingGrowth = calcGrowth(stock.capSpending, "capSpending", 100.0)
        stock.dividendsGrowth = calcGrowth(stock.dividends, "dividends", 0.01)
        stock.freeCashFlowPerShareGrowth = calcGrowth(stock.freeCashFlowPerShare, "freeCashFlowPerShare", 0.01)
        stock.grossMarginGrowth = calcGrowth(stock.grossMargin, "grossMargin", 0.01)
        stock.operatingCashFlowGrowth = calcGrowth(stock.operatingCashFlow, "operatingCashFlow", 100.0)
        stock.operatingIncomeGrowth = calcGrowth(stock.operatingIncome, "operatingIncome", 100.0)
        stock.operatingMarginGrowth = calcGrowth(stock.operatingMargin, "operatingMargin", 0.01)
        stock.sharesGrowth = calcGrowth(stock.shares, "shares", 100.0)
        stock.workingCapitalGrowth = calcGrowth(stock.workingCapital, "workingCapital", 100.0)
    }

    private fun <T: Number> calcGrowth(statPeriods: SortedMap<LocalDate, T>, statName: String, significanceThreshold : Double): SortedMap<LocalDate, Double> {
        if (statPeriods.size < 2){
            return sortedMapOf()
        }
        val periodicalGrowth = TreeMap<LocalDate, Double>()
        var previousValue: T? = null
        for (indexedPeriod in statPeriods.entries.withIndex()){
            val currentValue = indexedPeriod.value.value
            if(indexedPeriod.index != 0){
                val growthAtDate = indexedPeriod.value.key
                val value = percentGrowth(currentValue, previousValue, statName, significanceThreshold)
                if(value != null) {
                    periodicalGrowth[growthAtDate] = value
                }
            }
            previousValue = currentValue
        }
        return periodicalGrowth
    }


    private fun getExchangeRate(currency: Currency?, financialCurrency: Currency?, stock: Stock): Double {
        val differentCurrencies = currency != financialCurrency

        return if (differentCurrencies && currency != null && financialCurrency != null) {
            exchangeRateClient.getRate(financialCurrency, currency)
        } else {
            1.0
        }
    }
}