package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.ExchangeRateClient
import com.github.dusanzahoransky.stockanalyst.client.YahooFinanceClient
import com.github.dusanzahoransky.stockanalyst.model.Ticker
import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Interval
import com.github.dusanzahoransky.stockanalyst.model.enums.Range
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.*
import com.github.dusanzahoransky.stockanalyst.model.yahoo.analysis.AnalysisResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.chart.ChartResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.financials.FinancialsResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.holders.HoldersResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.statistics.StatisticsResponse
import com.github.dusanzahoransky.stockanalyst.repository.*
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils.Companion.useCache
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.minus
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.multiply
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percent
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.plus
import com.github.dusanzahoransky.stockanalyst.util.FormattingUtils.Companion.epochSecToLocalDate
import com.github.dusanzahoransky.stockanalyst.util.FormattingUtils.Companion.localDateToEpochSec
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Period

@Service
class StockService @Autowired constructor(
    val watchlistRepo: WatchlistRepo,
    val stockRepo: StockRepo,
    val yahooFinanceClient: YahooFinanceClient,
    val exchangeRateClient: ExchangeRateClient,
    val chartRepo: ChartRepo,
    val financialsRepo:FinancialsRepo,
    val analysisRepo:AnalysisRepo,
    val holdersRepo: HoldersRepo,
    val statisticsRepo:StatisticsRepo
) {
    companion object {
        val CHART_SAMPLING_INTERVAL: Period = Period.ofDays(7)
    }

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun getWatchlistStocks(watchlist: Watchlist, forceRefresh: Boolean, mockData: Boolean, forceRefreshDate: LocalDate): List<Stock> {
        val watchlistTickers = watchlistRepo.getWatchlist(watchlist)

        return watchlistTickers.mapNotNull { ticker -> findOrLoad(ticker, forceRefresh, mockData, forceRefreshDate) }
    }

    private fun findOrLoad(ticker: Ticker, forceRefreshCache: Boolean, mockData: Boolean, forceRefreshDate: LocalDate): Stock? {
        val (chart, financials, analysis, statistics, holders) = loadData(ticker, forceRefreshCache, mockData, forceRefreshDate)

        val stock = Stock(symbol = ticker.symbol, exchange = ticker.exchange)

        //load from yahoo
        val exchangeRate = getExchangeRate(financials.response, stock)

        processFinancials(financials.response, stock, exchangeRate)
        processStatistics(statistics.response, stock, exchangeRate)
        processAnalysis(analysis.response, stock)
        processHolders(holders.response, stock)
        processChart(chart.response, stock, CHART_SAMPLING_INTERVAL)

        //delete previous version
        stockRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { stockRepo.delete(it) }
        //store new version
        log.debug("Saving stock $ticker into DB")
        return stockRepo.insert(stock)
    }

    data class YahooData(
        val chart: Chart,
        val financials: Financials,
        val analysis:Analysis,
        val statistics:Statistics,
        val holders:Holders
    )

    private fun loadData(ticker: Ticker, forceRefreshCache: Boolean, mockData: Boolean, forceRefreshDate: LocalDate): YahooData {
        val chart = loadChart(ticker, forceRefreshCache, mockData, forceRefreshDate)
        val financials = loadFinancials(ticker, forceRefreshCache, mockData, forceRefreshDate)
        val analysis = loadAnalysis(ticker, forceRefreshCache, mockData, forceRefreshDate)
        val statistics = loadStatistics(ticker, forceRefreshCache, mockData, forceRefreshDate)
        val holders = loadHolders(ticker, forceRefreshCache, mockData, forceRefreshDate)
        return YahooData(chart, financials, analysis, statistics, holders)
    }

    private fun loadChart(ticker: Ticker, forceRefreshCache: Boolean, mockData: Boolean, forceRefreshDate: LocalDate): Chart {
        val cachedData = chartRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCache(forceRefreshCache, cachedData, forceRefreshDate)) {
            log.debug("Retrieving Chart from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getChart(ticker, Interval.OneDay, Range.TenYears, mockData)

        val data = Chart(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not cache mock data
        if (mockData) {
            return data
        }

        //delete previous version
        chartRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { chartRepo.delete(it) }
        //store new version
        log.debug("Saving Chart $ticker into DB")
        return chartRepo.insert(data)
    }

    private fun loadFinancials(ticker: Ticker, forceRefreshCache: Boolean, mockData: Boolean, forceRefreshDate: LocalDate): Financials {
        val cachedData = financialsRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCache(forceRefreshCache, cachedData, forceRefreshDate)) {
            log.debug("Retrieving Financials from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getFinancials(ticker, mockData)

        val data = Financials(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not cache mock data
        if (mockData) {
            return data
        }

        //delete previous version
        financialsRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { financialsRepo.delete(it) }
        //store new version
        log.debug("Saving Financials $ticker into DB")
        return financialsRepo.insert(data)
    }

    private fun loadAnalysis(ticker: Ticker, forceRefreshCache: Boolean, mockData: Boolean, forceRefreshDate: LocalDate): Analysis {
        val cachedData = analysisRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCache(forceRefreshCache, cachedData, forceRefreshDate)) {
            log.debug("Retrieving Analysis from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getAnalysis(ticker, mockData)

        val data = Analysis(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not cache mock data
        if (mockData) {
            return data
        }

        //delete previous version
        analysisRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { analysisRepo.delete(it) }
        //store new version
        log.debug("Saving Analysis $ticker into DB")
        return analysisRepo.insert(data)
    }

    private fun loadHolders(ticker: Ticker, forceRefreshCache: Boolean, mockData: Boolean, forceRefreshDate: LocalDate): Holders {
        val cachedData = holdersRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCache(forceRefreshCache, cachedData, forceRefreshDate)) {
            log.debug("Retrieving Holders from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getHolders(ticker, mockData)

        val data = Holders(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not cache mock data
        if (mockData) {
            return data
        }

        //delete previous version
        holdersRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { holdersRepo.delete(it) }
        //store new version
        log.debug("Saving Holders $ticker into DB")
        return holdersRepo.insert(data)
    }

    private fun loadStatistics(ticker: Ticker, forceRefreshCache: Boolean, mockData: Boolean, forceRefreshDate: LocalDate): Statistics {
        val cachedData = statisticsRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCache(forceRefreshCache, cachedData, forceRefreshDate)) {
            log.debug("Retrieving Statistics from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getStatistics(ticker, mockData)

        val data = Statistics(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not cache mock data
        if (mockData) {
            return data
        }

        //delete previous version
        statisticsRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { statisticsRepo.delete(it) }
        //store new version
        log.debug("Saving Statistics $ticker into DB")
        return statisticsRepo.insert(data)
    }

    private fun processChart(chart: ChartResponse, stock: Stock, samplingInterval: Period) {
        val result = chart.chart?.result?.getOrNull(0) ?: return
        val closePrices = result.indicators?.quote?.getOrNull(0)?.close ?: return
        val timestamps = result.timestamp?.map { epochSecToLocalDate(it) } ?: return

        val chartTo = timestamps.last()
        var currentInterval = timestamps.first()
        val samplingDays = samplingInterval.days
        val chartData = mutableListOf<StockChartData>()

        while (currentInterval < chartTo) {
            val chartDataPoint = dataAtInterval(currentInterval, timestamps, closePrices)
            chartData.add(chartDataPoint)
            currentInterval = currentInterval.plusDays(samplingDays.toLong())
        }

        //add the latest price as the sampling loop might have finished within the records before
        val chartLastDataPoint = dataAtInterval(chartTo, timestamps, closePrices)
        chartData.add(chartLastDataPoint)

        stock.chartData = chartData
    }

    private fun dataAtInterval(
        currentInterval: LocalDate,
        timestamps: List<LocalDate>,
        closePrices: MutableList<Double?>): StockChartData {

        val timestampEtfAtInterval = timestamps.indexOfFirst { !it.isBefore(currentInterval) }
        val priceAtInterval = closePrices[timestampEtfAtInterval]

        return StockChartData(localDateToEpochSec(currentInterval), priceAtInterval)
    }

    private fun processFinancials(financials: FinancialsResponse, stock: Stock, exchangeRate: Double) {

        val incomeStatementLastQuarter = financials.incomeStatementHistoryQuarterly?.incomeStatementHistory?.getOrNull(0)
        val incomeStatement2QuartersAgo = financials.incomeStatementHistoryQuarterly?.incomeStatementHistory?.getOrNull(1)
        val incomeStatement3QuartersAgo = financials.incomeStatementHistoryQuarterly?.incomeStatementHistory?.getOrNull(2)
        val incomeStatement4QuartersAgo = financials.incomeStatementHistoryQuarterly?.incomeStatementHistory?.getOrNull(3)
        val incomeStatementLastYear = financials.incomeStatementHistory?.incomeStatementHistory?.getOrNull(0)
        val incomeStatement2YearsAgo = financials.incomeStatementHistory?.incomeStatementHistory?.getOrNull(1)
        val incomeStatement3YearsAgo = financials.incomeStatementHistory?.incomeStatementHistory?.getOrNull(2)
        val incomeStatement4YearsAgo = financials.incomeStatementHistory?.incomeStatementHistory?.getOrNull(3)

        val balanceSheetStatements = financials.balanceSheetHistoryQuarterly?.balanceSheetStatements
        val balanceSheetLastQuarter = balanceSheetStatements?.getOrNull(0)
        val balanceSheet2QuartersAgo = balanceSheetStatements?.getOrNull(1)
        val balanceSheet3QuartersAgo = balanceSheetStatements?.getOrNull(2)
        val balanceSheet4QuartersAgo = balanceSheetStatements?.getOrNull(3)
        val balanceSheetLastYear = financials.balanceSheetHistory?.balanceSheetStatements?.getOrNull(0)
        val balanceSheet2YearsAgo = financials.balanceSheetHistory?.balanceSheetStatements?.getOrNull(1)
        val balanceSheet3YearsAgo = financials.balanceSheetHistory?.balanceSheetStatements?.getOrNull(2)
        val balanceSheet4YearsAgo = financials.balanceSheetHistory?.balanceSheetStatements?.getOrNull(3)

        val cashFlowLastQuarter = financials.cashflowStatementHistoryQuarterly?.cashflowStatements?.getOrNull(0)
        val cashFlow2QuartersAgo = financials.cashflowStatementHistoryQuarterly?.cashflowStatements?.getOrNull(1)
        val cashFlow3QuartersAgo = financials.cashflowStatementHistoryQuarterly?.cashflowStatements?.getOrNull(2)
        val cashFlow4QuartersAgo = financials.cashflowStatementHistoryQuarterly?.cashflowStatements?.getOrNull(3)
        val cashFlowLastYear = financials.cashflowStatementHistory?.cashflowStatements?.getOrNull(0)
        val cashFlow2YearsAgo = financials.cashflowStatementHistory?.cashflowStatements?.getOrNull(1)
        val cashFlow3YearsAgo = financials.cashflowStatementHistory?.cashflowStatements?.getOrNull(2)
        val cashFlow4YearsAgo = financials.cashflowStatementHistory?.cashflowStatements?.getOrNull(3)

        val timeSeries = financials.timeSeries
        val earnings = financials.earnings

        stock.revenueLastQuarter = incomeStatementLastQuarter?.totalRevenue?.raw?.toLong()
        stock.revenue2QuartersAgo = incomeStatement2QuartersAgo?.totalRevenue?.raw?.toLong()
        stock.revenue3QuartersAgo = incomeStatement3QuartersAgo?.totalRevenue?.raw?.toLong()
        stock.revenueLastYear = incomeStatementLastYear?.totalRevenue?.raw?.toLong()
        stock.revenue2YearsAgo = incomeStatement2YearsAgo?.totalRevenue?.raw?.toLong()
        stock.revenue4YearsAgo = incomeStatement4YearsAgo?.totalRevenue?.raw?.toLong()

//        stock.grossIncomeLastQuarter = incomeStatementLastQuarter?.grossProfit?.raw?.toLong()
//        stock.grossIncome2QuartersAgo = incomeStatement2QuartersAgo?.grossProfit?.raw?.toLong()
//        stock.grossIncome3QuartersAgo = incomeStatement3QuartersAgo?.grossProfit?.raw?.toLong()
//        stock.grossIncomeLastYear = incomeStatementLastYear?.grossProfit?.raw?.toLong()
//        stock.grossIncome2YearsAgo = incomeStatement2YearsAgo?.grossProfit?.raw?.toLong()
//        stock.grossIncome4YearsAgo = incomeStatement4YearsAgo?.grossProfit?.raw?.toLong()

        stock.ebitLastQuarter = incomeStatementLastQuarter?.ebit?.raw?.toLong()
        stock.ebit2QuartersAgo = incomeStatement2QuartersAgo?.ebit?.raw?.toLong()
        stock.ebit3QuartersAgo = incomeStatement3QuartersAgo?.ebit?.raw?.toLong()
        stock.ebitLastYear = incomeStatementLastYear?.ebit?.raw?.toLong()
        stock.ebit2YearsAgo = incomeStatement2YearsAgo?.ebit?.raw?.toLong()
        stock.ebit4YearsAgo = incomeStatement4YearsAgo?.ebit?.raw?.toLong()

        stock.netIncomeLastQuarter = incomeStatementLastQuarter?.netIncome?.raw?.toLong()
        stock.netIncome2QuartersAgo = incomeStatement2QuartersAgo?.netIncome?.raw?.toLong()
        stock.netIncome3QuartersAgo = incomeStatement3QuartersAgo?.netIncome?.raw?.toLong()
        stock.netIncomeLastYear = incomeStatementLastYear?.netIncome?.raw?.toLong()
        stock.netIncome2YearsAgo = incomeStatement2YearsAgo?.netIncome?.raw?.toLong()
        stock.netIncome4YearsAgo = incomeStatement4YearsAgo?.netIncome?.raw?.toLong()

        stock.freeCashFlowLastQuarter = plus(cashFlowLastQuarter?.totalCashFromOperatingActivities?.raw?.toLong(), cashFlowLastQuarter?.capitalExpenditures?.raw?.toLong())
        stock.freeCashFlow2QuartersAgo = plus(cashFlow2QuartersAgo?.totalCashFromOperatingActivities?.raw?.toLong(), cashFlow2QuartersAgo?.capitalExpenditures?.raw?.toLong())
        stock.freeCashFlow3QuartersAgo = plus(cashFlow3QuartersAgo?.totalCashFromOperatingActivities?.raw?.toLong(), cashFlow3QuartersAgo?.capitalExpenditures?.raw?.toLong())
        stock.freeCashFlowLastYear = plus(cashFlowLastYear?.totalCashFromOperatingActivities?.raw?.toLong(), cashFlowLastYear?.capitalExpenditures?.raw?.toLong())
        stock.freeCashFlow2YearsAgo = plus(cashFlow2YearsAgo?.totalCashFromOperatingActivities?.raw?.toLong(), cashFlow2YearsAgo?.capitalExpenditures?.raw?.toLong())
        stock.freeCashFlow4YearsAgo = plus(cashFlow4YearsAgo?.totalCashFromOperatingActivities?.raw?.toLong(), cashFlow4YearsAgo?.capitalExpenditures?.raw?.toLong())

        stock.cashLastQuarter = balanceSheetLastQuarter?.cash?.raw?.toLong()
        stock.cash2QuartersAgo = balanceSheet2QuartersAgo?.cash?.raw?.toLong()
        stock.cash3QuartersAgo = balanceSheet3QuartersAgo?.cash?.raw?.toLong()
        stock.cashLastYear = balanceSheetLastYear?.cash?.raw?.toLong()
        stock.cash2YearsAgo = balanceSheet2YearsAgo?.cash?.raw?.toLong()
        stock.cash4YearsAgo = balanceSheet4YearsAgo?.cash?.raw?.toLong()

        stock.inventoryLastQuarter = balanceSheetLastQuarter?.inventory?.raw?.toLong()
        stock.inventory2QuartersAgo = balanceSheet2QuartersAgo?.inventory?.raw?.toLong()
        stock.inventory3QuartersAgo = balanceSheet3QuartersAgo?.inventory?.raw?.toLong()
        stock.inventoryLastYear = balanceSheetLastYear?.inventory?.raw?.toLong()
        stock.inventory2YearsAgo = balanceSheet2YearsAgo?.inventory?.raw?.toLong()
        stock.inventory4YearsAgo = balanceSheet4YearsAgo?.inventory?.raw?.toLong()

        stock.currentAssetsLastQuarter = balanceSheetLastQuarter?.totalCurrentAssets?.raw?.toLong()
        stock.currentAssets2QuartersAgo = balanceSheet2QuartersAgo?.totalCurrentAssets?.raw?.toLong()
        stock.currentAssets3QuartersAgo = balanceSheet3QuartersAgo?.totalCurrentAssets?.raw?.toLong()
        stock.currentAssetsLastYear = balanceSheetLastYear?.totalCurrentAssets?.raw?.toLong()
        stock.currentAssets2YearsAgo = balanceSheet2YearsAgo?.totalCurrentAssets?.raw?.toLong()
        stock.currentAssets4YearsAgo = balanceSheet4YearsAgo?.totalCurrentAssets?.raw?.toLong()

        stock.currentLiabilitiesLastQuarter = balanceSheetLastQuarter?.totalCurrentLiabilities?.raw?.toLong()
        stock.currentLiabilities2QuartersAgo = balanceSheet2QuartersAgo?.totalCurrentLiabilities?.raw?.toLong()
        stock.currentLiabilities3QuartersAgo = balanceSheet3QuartersAgo?.totalCurrentLiabilities?.raw?.toLong()
        stock.currentLiabilitiesLastYear = balanceSheetLastYear?.totalCurrentLiabilities?.raw?.toLong()
        stock.currentLiabilities2YearsAgo = balanceSheet2YearsAgo?.totalCurrentLiabilities?.raw?.toLong()
        stock.currentLiabilities4YearsAgo = balanceSheet4YearsAgo?.totalCurrentLiabilities?.raw?.toLong()

        stock.totalLiabilitiesLastQuarter = balanceSheetLastQuarter?.totalLiab?.raw?.toLong()
        stock.totalLiabilities2QuartersAgo = balanceSheet2QuartersAgo?.totalLiab?.raw?.toLong()
        stock.totalLiabilities3QuartersAgo = balanceSheet3QuartersAgo?.totalLiab?.raw?.toLong()
        stock.totalLiabilitiesLastYear = balanceSheetLastYear?.totalLiab?.raw?.toLong()
        stock.totalLiabilities2YearsAgo = balanceSheet2YearsAgo?.totalLiab?.raw?.toLong()
        stock.totalLiabilities4YearsAgo = balanceSheet4YearsAgo?.totalLiab?.raw?.toLong()

        stock.totalShareholdersEquityLastQuarter = balanceSheetLastQuarter?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquity2QuartersAgo = balanceSheet2QuartersAgo?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquity3QuartersAgo = balanceSheet3QuartersAgo?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquityLastYear = balanceSheetLastYear?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquity2YearsAgo = balanceSheet2YearsAgo?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquity4YearsAgo = balanceSheet4YearsAgo?.totalStockholderEquity?.raw?.toLong()

        stock.stockRepurchasedLastQuarter = cashFlowLastQuarter?.repurchaseOfStock?.raw?.toLong()
        stock.stockRepurchased2QuartersAgo = cashFlow2QuartersAgo?.repurchaseOfStock?.raw?.toLong()
        stock.stockRepurchased3QuartersAgo = cashFlow3QuartersAgo?.repurchaseOfStock?.raw?.toLong()
        stock.stockRepurchasedLastYear = cashFlowLastYear?.repurchaseOfStock?.raw?.toLong()
        stock.stockRepurchased2YearsAgo = cashFlow2YearsAgo?.repurchaseOfStock?.raw?.toLong()
        stock.stockRepurchased4YearsAgo = cashFlow4YearsAgo?.repurchaseOfStock?.raw?.toLong()

        stock.stockLastQuarter = balanceSheetLastQuarter?.commonStock?.raw?.toLong()
        stock.stock2QuartersAgo = balanceSheet2QuartersAgo?.commonStock?.raw?.toLong()
        stock.stock3QuartersAgo = balanceSheet3QuartersAgo?.commonStock?.raw?.toLong()
        stock.stockLastYear = balanceSheetLastYear?.commonStock?.raw?.toLong()
        stock.stock2YearsAgo = balanceSheet2YearsAgo?.commonStock?.raw?.toLong()
        stock.stock4YearsAgo = balanceSheet4YearsAgo?.commonStock?.raw?.toLong()

//        stock.epsCurrentQuarterEstimate = multiply(earnings?.earningsChart?.currentQuarterEstimate?.raw?.toDouble(), exchangeRate)
        val epsQuarterly = earnings?.earningsChart?.quarterly?.reversed()
        stock.epsLastQuarter = multiply(epsQuarterly?.getOrNull(0)?.actual?.raw?.toDouble(), exchangeRate)
        stock.eps2QuartersAgo = multiply(epsQuarterly?.getOrNull(1)?.actual?.raw?.toDouble(), exchangeRate)
        stock.eps3QuartersAgo = multiply(epsQuarterly?.getOrNull(2)?.actual?.raw?.toDouble(), exchangeRate)
        stock.eps4QuartersAgo = multiply(epsQuarterly?.getOrNull(3)?.actual?.raw?.toDouble(), exchangeRate)

        if (timeSeries?.annualDilutedEPS != null) {
            for ((index, annualEps) in timeSeries.annualDilutedEPS.withIndex()) {
                when (index) {
                    3 -> stock.epsLastYear = multiply(annualEps?.reportedValue?.raw, exchangeRate)
                    2 -> stock.eps2YearsAgo = multiply(annualEps?.reportedValue?.raw, exchangeRate)
                    1 -> stock.eps3YearsAgo = multiply(annualEps?.reportedValue?.raw, exchangeRate)
                    0 -> stock.eps4YearsAgo = multiply(annualEps?.reportedValue?.raw, exchangeRate)
                }
            }
        }

        stock.quarterEnds = balanceSheetStatements?.map { it.endDate.raw }
        stock.lastReportedQuarter = stock.quarterEnds?.getOrNull(0)?.let { epochSecToLocalDate(it) }
        stock.yearEnds = timeSeries?.timestamp?.reversed()
    }

    private fun processAnalysis(analysis: AnalysisResponse, stock: Stock) {
        stock.growthEstimate5y = percent(analysis.earningsTrend?.trend?.firstOrNull { it.period == "+5y" }?.growth?.raw)
    }

    private fun processHolders(holders: HoldersResponse, stock: Stock) {
        stock.buyPercentInsiderShares = percent(holders.netSharePurchaseActivity?.buyPercentInsiderShares?.raw)
        stock.sellPercentInsiderShares = percent(holders.netSharePurchaseActivity?.sellPercentInsiderShares?.raw)
    }

    private fun processStatistics(stats: StatisticsResponse, stock: Stock, exchangeRate: Double) {
        val financialData = stats.financialData
        val price = stats.price
        val defaultKeyStatistics = stats.defaultKeyStatistics
        val summaryDetail = stats.summaryDetail
        val calendarEvents = stats.calendarEvents

        stock.companyName = stats.quoteType?.shortName
        stock.price = price?.regularMarketPrice?.raw
//        stock.currency = price?.currency?.let { it -> Currency.valueOf(it) }
//        stock.financialCurrency = financialData?.financialCurrency?.let { it -> Currency.valueOf(it) }

        stock.change = percent(price?.regularMarketChangePercent?.raw)
        stock.enterpriseValue = defaultKeyStatistics.enterpriseValue?.raw

        stock.targetLowPrice = financialData.targetLowPrice?.raw.let { multiply(it, exchangeRate) }
        stock.targetMedianPrice = financialData.targetMedianPrice?.raw.let { multiply(it, exchangeRate) }

        stock.totalCashPerShare = financialData.totalCashPerShare?.raw

        stock.trailingPE = summaryDetail.trailingPE?.raw
        stock.forwardPE = summaryDetail.forwardPE?.raw
        stock.priceToSalesTrailing12Months = summaryDetail.priceToSalesTrailing12Months?.raw
        if (exchangeRate == 1.0) {
            stock.priceBook = defaultKeyStatistics.priceToBook?.raw
        } else {
            // P /B = Market Price per Share / BVPS
            //BVPS = (Total Equity − Preferred Equity) / Total Shares Outstanding
            //​	https://www.investopedia.com/terms/b/bvps.asp
        }
        stock.enterpriseValueRevenue = defaultKeyStatistics.enterpriseToRevenue?.raw
        stock.enterpriseValueEBITDA = defaultKeyStatistics.enterpriseToEbitda?.raw

        stock.priceEarningGrowth = defaultKeyStatistics.pegRatio?.raw
        val trailingEps = multiply(defaultKeyStatistics.trailingEps?.raw, exchangeRate)
        if (stock.trailingPE != null && trailingEps != null) {
            stock.trailingPriceEarningGrowth = stock.trailingPE!! / trailingEps
        }

        stock.week52Change = percent(defaultKeyStatistics.get52WeekChange()?.raw)
        stock.week52Low = summaryDetail.fiftyTwoWeekLow?.raw
        stock.week52High = summaryDetail.fiftyTwoWeekHigh?.raw

        stock.heldByInsiders = percent(defaultKeyStatistics.heldPercentInsiders?.raw)
        stock.heldByInstitutions = percent(defaultKeyStatistics.heldPercentInstitutions?.raw)
        stock.shortToFloat = percent(defaultKeyStatistics.shortPercentOfFloat?.raw)

        val sharesShortPriorMonth = defaultKeyStatistics.sharesShortPriorMonth?.raw
        val sharesShort = defaultKeyStatistics.sharesShort?.raw
        if (sharesShortPriorMonth != null && sharesShort != null) {
            stock.sharesShortPrevMonthCompare = percent(sharesShortPriorMonth / sharesShort)
        }

        stock.exDividendDate = calendarEvents.exDividendDate?.fmt
        stock.fiveYearAvgDividendYield = summaryDetail.fiveYearAvgDividendYield?.raw
        stock.trailingAnnualDividendYield = percent(summaryDetail.trailingAnnualDividendYield?.raw)
        stock.payoutRatio = percent(summaryDetail.payoutRatio?.raw)
    }

    private fun getExchangeRate(financials: FinancialsResponse, stock: Stock): Double {
        val currency = financials.price?.currency?.let { it -> Currency.valueOf(it) }
        val financialCurrency = financials.earnings?.financialCurrency?.let { it -> Currency.valueOf(it) }

        val differentCurrencies = currency != financialCurrency

        return if (differentCurrencies && currency != null && financialCurrency != null) {
            exchangeRateClient.getRate(financialCurrency, currency)
        } else {
            1.0
        }
    }

    fun deleteSymbol(symbol: String) {
        stockRepo.findBySymbol(symbol).forEach {
            log.debug("Deleted ${it.companyName}")
            stockRepo.delete(it)
        }
    }

    fun deleteWatchlist(watchlist: Watchlist) {
        val watchlistStocks = watchlistRepo.getWatchlist(watchlist)
        watchlistStocks
            .map { stockRepo.findBySymbolAndExchange(it.symbol, it.exchange) }
            .forEach {
                if (it != null) {
                    log.debug("Deleted ${it.companyName}")
                    stockRepo.delete(it)
                }
            }
    }
}