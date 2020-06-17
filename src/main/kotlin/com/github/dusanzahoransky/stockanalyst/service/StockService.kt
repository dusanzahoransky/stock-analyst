package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.ExchangeRateClient
import com.github.dusanzahoransky.stockanalyst.client.YahooFinanceClient
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Interval
import com.github.dusanzahoransky.stockanalyst.model.enums.Range
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockChartData
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import com.github.dusanzahoransky.stockanalyst.model.yahoo.analysis.AnalysisResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.chart.ChartResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.financials.FinancialsResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.statistics.StatisticsResponse
import com.github.dusanzahoransky.stockanalyst.repository.StockRepo
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils.Companion.useCache
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.multiply
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percent
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
    val exchangeRateClient: ExchangeRateClient
) {
    companion object {
        val CHART_SAMPLING_INTERVAL: Period = Period.ofDays(7)
    }

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun getWatchlistStocks(watchlist: Watchlist, forceRefresh: Boolean, mockData: Boolean, forceRefreshDate: LocalDate): List<StockInfo> {
        val watchlistTickers = watchlistRepo.getWatchlist(watchlist)

        return watchlistTickers.mapNotNull { ticker -> findOrLoad(ticker, forceRefresh, mockData, forceRefreshDate) }
    }

    private fun findOrLoad(ticker: StockTicker, forceRefreshCache: Boolean, mockData: Boolean, forceRefreshDate: LocalDate): StockInfo? {
        var stock = stockRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (useCache(forceRefreshCache, stock, forceRefreshDate)) {
            log.debug("Retrieving stock info from cache: $ticker")
            return stock
        }

        stock = StockInfo(symbol = ticker.symbol, exchange = ticker.exchange)
        //load from yahoo
        val financials = yahooFinanceClient.getFinancials(ticker, mockData)
        val exchangeRate = getExchangeRate(financials, stock)

        processFinancials(financials, stock, exchangeRate)

        val stats = yahooFinanceClient.getStatistics(ticker, mockData)
        processStatistics(stats, stock, exchangeRate)

        val analysis = yahooFinanceClient.getAnalysis(ticker, mockData)
        processAnalysis(analysis, stock)

        val chart = yahooFinanceClient.getChart(ticker, Interval.OneDay, Range.TenYears, mockData)
        processChart(chart, stock, CHART_SAMPLING_INTERVAL)

        //do not cache mock data
        if (mockData) {
            return stock
        }

        //delete previous version
        stockRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { stockRepo.delete(it) }
        //store new version
        log.debug("Saving $ticker into DB")
        return stockRepo.insert(stock)
    }

    private fun processChart(chart: ChartResponse, stock: StockInfo, samplingInterval: Period) {
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

    private fun processFinancials(financials: FinancialsResponse, stock: StockInfo, exchangeRate: Double) {

        val incomeStatementLastQuarter = financials.incomeStatementHistoryQuarterly?.incomeStatementHistory?.getOrNull(0)
        val incomeStatement2QuartersAgo = financials.incomeStatementHistoryQuarterly?.incomeStatementHistory?.getOrNull(1)
        val incomeStatement3QuartersAgo = financials.incomeStatementHistoryQuarterly?.incomeStatementHistory?.getOrNull(2)
        val incomeStatementLastYear = financials.incomeStatementHistory?.incomeStatementHistory?.getOrNull(0)
        val incomeStatement2YearsAgo = financials.incomeStatementHistory?.incomeStatementHistory?.getOrNull(1)
        val incomeStatement3YearsAgo = financials.incomeStatementHistory?.incomeStatementHistory?.getOrNull(2)

        val balanceSheetStatements = financials.balanceSheetHistoryQuarterly?.balanceSheetStatements
        val balanceSheetLastQuarter = balanceSheetStatements?.getOrNull(0)
        val balanceSheet2QuartersAgo = balanceSheetStatements?.getOrNull(1)
        val balanceSheetLastYear = financials.balanceSheetHistory?.balanceSheetStatements?.getOrNull(0)
        val balanceSheet2YearsAgo = financials.balanceSheetHistory?.balanceSheetStatements?.getOrNull(1)
        val balanceSheet3YearsAgo = financials.balanceSheetHistory?.balanceSheetStatements?.getOrNull(2)
        val balanceSheet4YearsAgo = financials.balanceSheetHistory?.balanceSheetStatements?.getOrNull(3)

        val timeSeries = financials.timeSeries

        val earnings = financials.earnings

        val cashFlowLastQuarter = financials.cashflowStatementHistoryQuarterly?.cashflowStatements?.getOrNull(0)
        val cashFlow2QuartersAgo = financials.cashflowStatementHistoryQuarterly?.cashflowStatements?.getOrNull(1)
        val cashFlowLastYear = financials.cashflowStatementHistory?.cashflowStatements?.getOrNull(0)
        val cashFlow2YearsAgo = financials.cashflowStatementHistory?.cashflowStatements?.getOrNull(1)
        val cashFlow3YearsAgo = financials.cashflowStatementHistory?.cashflowStatements?.getOrNull(2)

        stock.netIncomeLastQuarter = incomeStatementLastQuarter?.netIncome?.raw?.toLong()
        stock.netIncome2QuartersAgo = incomeStatement2QuartersAgo?.netIncome?.raw?.toLong()
        stock.netIncome3QuartersAgo = incomeStatement3QuartersAgo?.netIncome?.raw?.toLong()
        stock.netIncomeLastYear = incomeStatementLastYear?.netIncome?.raw?.toLong()
        stock.netIncome3YearsAgo = incomeStatement3YearsAgo?.netIncome?.raw?.toLong()

        stock.grossIncomeLastQuarter = incomeStatementLastQuarter?.grossProfit?.raw?.toLong()
        stock.grossIncome2QuartersAgo = incomeStatement2QuartersAgo?.grossProfit?.raw?.toLong()
        stock.grossIncome3QuartersAgo = incomeStatement3QuartersAgo?.grossProfit?.raw?.toLong()
        stock.grossIncomeLastYear = incomeStatementLastYear?.grossProfit?.raw?.toLong()
        stock.grossIncome3YearsAgo = incomeStatement3YearsAgo?.grossProfit?.raw?.toLong()

        stock.revenueLastQuarter = incomeStatementLastQuarter?.totalRevenue?.raw?.toLong()
        stock.revenue2QuartersAgo = incomeStatement2QuartersAgo?.totalRevenue?.raw?.toLong()
        stock.revenue3QuartersAgo = incomeStatement3QuartersAgo?.totalRevenue?.raw?.toLong()
        stock.revenueLastYear = incomeStatementLastYear?.totalRevenue?.raw?.toLong()
        stock.revenue2YearsAgo = incomeStatement2YearsAgo?.totalRevenue?.raw?.toLong()
        stock.revenue3YearsAgo = incomeStatement3YearsAgo?.totalRevenue?.raw?.toLong()

        stock.cashLastQuarter = balanceSheetLastQuarter?.cash?.raw?.toLong()
        stock.cash2QuartersAgo = balanceSheet2YearsAgo?.cash?.raw?.toLong()
        stock.cashLastYear = balanceSheetLastYear?.cash?.raw?.toLong()
        stock.cash2YearsAgo = balanceSheet2YearsAgo?.cash?.raw?.toLong()
        stock.cash3YearsAgo = balanceSheet3YearsAgo?.cash?.raw?.toLong()

        stock.inventoryLastQuarter = balanceSheetLastQuarter?.inventory?.raw?.toLong()
        stock.inventory2QuartersAgo = balanceSheet2QuartersAgo?.inventory?.raw?.toLong()
        stock.inventoryLastYear = balanceSheetLastYear?.inventory?.raw?.toLong()
        stock.inventory2YearsAgo = balanceSheet2YearsAgo?.inventory?.raw?.toLong()
        stock.inventory3YearsAgo = balanceSheet3YearsAgo?.inventory?.raw?.toLong()

        stock.currentAssetsLastQuarter = balanceSheetLastQuarter?.totalCurrentAssets?.raw?.toLong()
        stock.currentAssets2QuartersAgo = balanceSheet2QuartersAgo?.totalCurrentAssets?.raw?.toLong()
        stock.currentAssetsLastYear = balanceSheetLastYear?.totalCurrentAssets?.raw?.toLong()
        stock.currentAssets2YearsAgo = balanceSheet2YearsAgo?.totalCurrentAssets?.raw?.toLong()
        stock.currentAssets3YearsAgo = balanceSheet3YearsAgo?.totalCurrentAssets?.raw?.toLong()

        stock.currentLiabilitiesLastQuarter = balanceSheetLastQuarter?.totalCurrentLiabilities?.raw?.toLong()
        stock.currentLiabilities2QuartersAgo = balanceSheet2QuartersAgo?.totalCurrentLiabilities?.raw?.toLong()
        stock.currentLiabilitiesLastYear = balanceSheetLastYear?.totalCurrentLiabilities?.raw?.toLong()
        stock.currentLiabilities2YearsAgo = balanceSheet2YearsAgo?.totalCurrentLiabilities?.raw?.toLong()
        stock.currentLiabilities3YearsAgo = balanceSheet3YearsAgo?.totalCurrentLiabilities?.raw?.toLong()

        stock.totalLiabilitiesLastQuarter = balanceSheetLastQuarter?.totalLiab?.raw?.toLong()
        stock.totalLiabilities2QuartersAgo = balanceSheet2QuartersAgo?.totalLiab?.raw?.toLong()
        stock.totalLiabilitiesLastYear = balanceSheetLastYear?.totalLiab?.raw?.toLong()
        stock.totalLiabilities2YearsAgo = balanceSheet2YearsAgo?.totalLiab?.raw?.toLong()
        stock.totalLiabilities3YearsAgo = balanceSheet3YearsAgo?.totalLiab?.raw?.toLong()
        stock.totalLiabilities4YearsAgo = balanceSheet4YearsAgo?.totalLiab?.raw?.toLong()

        stock.totalShareholdersEquityLastQuarter = balanceSheetLastQuarter?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquity2QuartersAgo = balanceSheet2QuartersAgo?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquityLastYear = balanceSheetLastYear?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquity2YearsAgo = balanceSheet2YearsAgo?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquity3YearsAgo = balanceSheet3YearsAgo?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquity4YearsAgo = balanceSheet4YearsAgo?.totalStockholderEquity?.raw?.toLong()

        stock.stockRepurchasedLastQuarter = cashFlowLastQuarter?.repurchaseOfStock?.raw?.toLong()
        stock.stockRepurchased2QuartersAgo = cashFlow2QuartersAgo?.repurchaseOfStock?.raw?.toLong()
        stock.stockRepurchasedLastYear = cashFlowLastYear?.repurchaseOfStock?.raw?.toLong()
        stock.stockRepurchased2YearsAgo = cashFlow2YearsAgo?.repurchaseOfStock?.raw?.toLong()
        stock.stockRepurchased3YearsAgo = cashFlow3YearsAgo?.repurchaseOfStock?.raw?.toLong()

        stock.stockLastQuarter = balanceSheetLastQuarter?.commonStock?.raw?.toLong()
        stock.stock2QuartersAgo = balanceSheet2QuartersAgo?.commonStock?.raw?.toLong()
        stock.stockLastYear = balanceSheetLastYear?.commonStock?.raw?.toLong()
        stock.stock2YearsAgo = balanceSheet2YearsAgo?.commonStock?.raw?.toLong()
        stock.stock3YearsAgo = balanceSheet3YearsAgo?.commonStock?.raw?.toLong()

        stock.epsCurrentQuarterEstimate = multiply(earnings?.earningsChart?.currentQuarterEstimate?.raw?.toDouble(), exchangeRate)
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

    private fun processAnalysis(analysis: AnalysisResponse, stock: StockInfo) {
        stock.growthEstimate5y = percent(analysis.earningsTrend?.trend?.firstOrNull { it.period == "+5y" }?.growth?.raw)
    }

    private fun processStatistics(stats: StatisticsResponse, stock: StockInfo, exchangeRate: Double) {
        val financialData = stats.financialData
        val price = stats.price
        val defaultKeyStatistics = stats.defaultKeyStatistics
        val summaryDetail = stats.summaryDetail
        val calendarEvents = stats.calendarEvents

        stock.companyName = stats.quoteType?.shortName
        stock.price = price?.regularMarketPrice?.raw
        stock.currency = price?.currency?.let { it -> Currency.valueOf(it) }
        stock.financialCurrency = financialData?.financialCurrency?.let { it -> Currency.valueOf(it) }

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

        stock.yoyQuarterlyRevenueGrowthPercent = percent(financialData.revenueGrowth?.raw)

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

    private fun getExchangeRate(financials: FinancialsResponse, stock: StockInfo): Double {
        stock.currency = financials.price?.currency?.let { it -> Currency.valueOf(it) }
        stock.financialCurrency = financials.earnings?.financialCurrency?.let { it -> Currency.valueOf(it) }

        val differentCurrencies = stock.currency != stock.financialCurrency

        return if (differentCurrencies && stock.currency != null && stock.financialCurrency != null) {
            exchangeRateClient.getRate(stock.financialCurrency!!, stock.currency!!)
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