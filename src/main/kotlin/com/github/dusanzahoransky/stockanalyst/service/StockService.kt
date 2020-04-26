package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.ExchangeRateClient
import com.github.dusanzahoransky.stockanalyst.client.YahooFinanceClient
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Interval
import com.github.dusanzahoransky.stockanalyst.model.enums.Range
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.ChartData
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import com.github.dusanzahoransky.stockanalyst.model.yahoo.chart.ChartResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.financials.FinancialsResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.statistics.StatisticsResponse
import com.github.dusanzahoransky.stockanalyst.repository.StockRepo
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.multiply
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percent
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percentGrowth
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.time.temporal.ChronoUnit

@Service
class StockService @Autowired constructor(
    val watchlistRepo: WatchlistRepo,
    val stockRepo: StockRepo,
    val yahooFinanceClient: YahooFinanceClient,
    val exchangeRateClient: ExchangeRateClient
) {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun getWatchlistStocks(watchlist: Watchlist, forceRefresh: Boolean, mockData: Boolean): List<StockInfo> {
        val watchlistTickers = watchlistRepo.getWatchlist(watchlist)
        return watchlistTickers.mapNotNull { ticker -> findOrLoad(ticker, forceRefresh, mockData) }
    }

    private fun findOrLoad(ticker: StockTicker, forceRefreshCache: Boolean, mockData: Boolean): StockInfo? {
        var stock = stockRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (!forceRefreshCache && stock != null) {
            log.debug("Retrieving stock info from cache: $ticker")
            return stock
        }

        stock = StockInfo(symbol = ticker.symbol, exchange = ticker.exchange)
        //load from yahoo
        val financials = yahooFinanceClient.getFinancials(ticker, mockData)
        if (financials == null) {
            log.error("Failed to retrieve stock Financials from Yahoo $ticker")
            return null
        }
        processFinancials(financials, stock)

        val stats = yahooFinanceClient.getStatistics(ticker, mockData)
        if (stats == null) {
            log.error("Failed to retrieve stock Statistics from Yahoo $ticker")
            return null
        }
        processStatistics(stats, stock)

        val chart = yahooFinanceClient.getChart(ticker, Interval.OneDay, Range.FiveYears, mockData)
        if (chart == null) {
            log.error("Failed to retrieve stock Chart from Yahoo $ticker")
            return null
        }
        processChart(chart, stock, Period.ofDays(7))

        //delete previous version
        stockRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { stockRepo.delete(it) }
        //store new version
        log.debug("Saving $ticker into DB")
        return stockRepo.insert(stock)
    }

    private fun processChart(chart: ChartResponse, stock: StockInfo, samplingInterval: Period) {
        val result = chart.chart?.result?.get(0) ?: return
        val closePrices = result.indicators?.quote?.get(0)?.close ?: return
        val timestamps = result.timestamp?.map { epochSecToLocalDate(it) } ?: return

        val epsSeries = mutableMapOf<LocalDate, Double?>()
        if (stock.quarterEnds != null) {
            for ((index, quarter) in stock.quarterEnds!!.withIndex()) {
                val eps = when (index) {
                    0 -> stock.epsLastQuarter
                    1 -> stock.eps2QuartersAgo
                    2 -> stock.eps3QuartersAgo
                    3 -> stock.eps4QuartersAgo
                    else -> null
                }
                if(eps != null) {
                    epsSeries[epochSecToLocalDate(quarter)] = eps
                }
            }
        }
        if (stock.yearEnds != null) {
            for ((index, year) in stock.yearEnds!!.withIndex()) {
                val eps = when (index) {
                    0 -> div(stock.epsLastYear, 4.0)    // /4 to convert year to quarter EPS
                    1 -> div(stock.eps2YearsAgo, 4.0)
                    2 -> div(stock.eps3YearsAgo, 4.0)
                    3 -> div(stock.eps4YearsAgo, 4.0)
                    else -> null
                }
                if(eps != null) {
                    epsSeries[epochSecToLocalDate(year)] = eps
                }
            }
        }

        val chartTo = timestamps.last()
        var currentInterval = timestamps.first()
        val samplingDays = samplingInterval.days
        val chartData = mutableListOf<ChartData>()

        while (currentInterval < chartTo) {
            val chartDataPoint = dataAtInterval(currentInterval, timestamps, closePrices)

            for ((date, eps) in epsSeries) {
                val daysBetweenQuarterAndSamplingInterval = ChronoUnit.DAYS.between(currentInterval, date)
                if (daysBetweenQuarterAndSamplingInterval in 1..samplingDays) {
                    chartDataPoint.eps = eps
                }
            }
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
        closePrices: MutableList<Double>): ChartData {

        val timestampIndexAtInterval = timestamps.indexOfFirst { !it.isBefore(currentInterval) }
        val priceAtInterval = closePrices[timestampIndexAtInterval]

        return ChartData(localDateToEpochSec(currentInterval), priceAtInterval)
    }

    private fun localDateToEpochSec(currentInterval: LocalDate) =
        currentInterval.atStartOfDay(ZoneId.of("UTC")).toEpochSecond()

    private fun epochSecToLocalDate(epochSeconds: Long) =
        Instant.ofEpochSecond(epochSeconds).atZone(ZoneId.of("UTC")).toLocalDate()

    private fun processFinancials(financials: FinancialsResponse, stock: StockInfo) {

        val incomeStatementLastQuarter = financials.incomeStatementHistoryQuarterly?.incomeStatementHistory?.getOrNull(0)
        val incomeStatementPreviousQuarter = financials.incomeStatementHistoryQuarterly?.incomeStatementHistory?.getOrNull(1)
        val incomeStatementLastYear = financials.incomeStatementHistory?.incomeStatementHistory?.getOrNull(0)
        val incomeStatement2YearsAgo = financials.incomeStatementHistory?.incomeStatementHistory?.getOrNull(1)
        val incomeStatement3YearsAgo = financials.incomeStatementHistory?.incomeStatementHistory?.getOrNull(2)

        val balanceSheetStatements = financials.balanceSheetHistoryQuarterly?.balanceSheetStatements
        val balanceSheetLastQuarter = balanceSheetStatements?.getOrNull(0)
        val balanceSheetPreviousQuarter = balanceSheetStatements?.getOrNull(1)
        val balanceSheetLastYear = financials.balanceSheetHistory?.balanceSheetStatements?.getOrNull(0)
        val balanceSheet2YearsAgo = financials.balanceSheetHistory?.balanceSheetStatements?.getOrNull(1)
        val balanceSheet3YearsAgo = financials.balanceSheetHistory?.balanceSheetStatements?.getOrNull(2)

        val timeSeries = financials.timeSeries

        val earnings = financials.earnings

        val cashFlowLastQuarter = financials.cashflowStatementHistoryQuarterly?.cashflowStatements?.getOrNull(0)
        val cashFlowPreviousQuarter = financials.cashflowStatementHistoryQuarterly?.cashflowStatements?.getOrNull(1)
        val cashFlowLastYear = financials.cashflowStatementHistory?.cashflowStatements?.getOrNull(0)
        val cashFlow2YearsAgo = financials.cashflowStatementHistory?.cashflowStatements?.getOrNull(1)
        val cashFlow3YearsAgo = financials.cashflowStatementHistory?.cashflowStatements?.getOrNull(2)

        stock.netIncomeLastQuarter = incomeStatementLastQuarter?.netIncome?.raw?.toLong()
        stock.netIncome2QuartersAgo = incomeStatementPreviousQuarter?.netIncome?.raw?.toLong()
        stock.netIncomeLastYear = incomeStatementLastYear?.netIncome?.raw?.toLong()
        stock.netIncome2YearAgo = incomeStatement2YearsAgo?.netIncome?.raw?.toLong()
        stock.netIncome3YearAgo = incomeStatement3YearsAgo?.netIncome?.raw?.toLong()
        stock.netIncomeGrowthLastQuarter = percentGrowth(stock.netIncomeLastQuarter, incomeStatementPreviousQuarter?.netIncome?.raw)
        stock.netIncomeGrowthLastYear = percentGrowth(stock.netIncomeLastYear, incomeStatement2YearsAgo?.netIncome?.raw)
        stock.netIncomeGrowthLast3Years = percentGrowth(stock.netIncomeLastYear, incomeStatement3YearsAgo?.netIncome?.raw)

        stock.revenueLastQuarter = incomeStatementLastQuarter?.totalRevenue?.raw?.toLong()
        stock.revenueLastYear = incomeStatementLastYear?.totalRevenue?.raw?.toLong()
        stock.revenueGrowthLastQuarter = percentGrowth(stock.revenueLastQuarter, incomeStatementPreviousQuarter?.totalRevenue?.raw)
        stock.revenueGrowthLastYear = percentGrowth(stock.revenueLastYear, incomeStatement2YearsAgo?.totalRevenue?.raw)
        stock.revenueGrowthLast3Years = percentGrowth(stock.revenueLastYear, incomeStatement3YearsAgo?.totalRevenue?.raw)

        stock.cashLastQuarter = balanceSheetLastQuarter?.cash?.raw?.toLong()
        stock.cashLastYear = balanceSheetLastYear?.cash?.raw?.toLong()
        stock.cashGrowthLastQuarter = percentGrowth(stock.cashLastQuarter, balanceSheetPreviousQuarter?.cash?.raw)
        stock.cashGrowthLastYear = percentGrowth(stock.cashLastYear, balanceSheet2YearsAgo?.cash?.raw)
        stock.cashGrowthLast3Years = percentGrowth(stock.cashLastYear, balanceSheet3YearsAgo?.cash?.raw)

        stock.inventoryLastQuarter = balanceSheetLastQuarter?.inventory?.raw?.toLong()
        stock.inventoryLastYear = balanceSheetLastYear?.inventory?.raw?.toLong()
        stock.inventoryGrowthLastQuarter = percentGrowth(stock.inventoryLastQuarter, balanceSheetPreviousQuarter?.inventory?.raw)
        stock.inventoryGrowthLastYear = percentGrowth(stock.inventoryLastYear, balanceSheet2YearsAgo?.inventory?.raw)
        stock.inventoryGrowthLast3Years = percentGrowth(stock.inventoryLastYear, balanceSheet3YearsAgo?.inventory?.raw)

        stock.currentLiabilitiesLastQuarter = balanceSheetLastQuarter?.totalCurrentLiabilities?.raw?.toLong()
        stock.currentLiabilitiesLastYear = balanceSheetLastYear?.totalCurrentLiabilities?.raw?.toLong()
        stock.currentLiabilitiesGrowthLastQuarter = percentGrowth(stock.currentLiabilitiesLastQuarter, balanceSheetPreviousQuarter?.totalCurrentLiabilities?.raw)
        stock.currentLiabilitiesGrowthLastYear = percentGrowth(stock.currentLiabilitiesLastYear, balanceSheet2YearsAgo?.totalCurrentLiabilities?.raw)
        stock.currentLiabilitiesGrowthLast3Years = percentGrowth(stock.currentLiabilitiesLastYear, balanceSheet3YearsAgo?.totalCurrentLiabilities?.raw)

        stock.totalLiabilitiesLastQuarter = balanceSheetLastQuarter?.totalLiab?.raw?.toLong()
        stock.totalLiabilitiesLastYear = balanceSheetLastYear?.totalLiab?.raw?.toLong()
        stock.totalLiabilitiesGrowthLastQuarter = percentGrowth(stock.totalLiabilitiesLastQuarter, balanceSheetPreviousQuarter?.totalLiab?.raw)
        stock.totalLiabilitiesGrowthLastYear = percentGrowth(stock.totalLiabilitiesLastYear, balanceSheet2YearsAgo?.totalLiab?.raw)
        stock.totalLiabilitiesGrowthLast3Years = percentGrowth(stock.totalLiabilitiesLastYear, balanceSheet3YearsAgo?.totalLiab?.raw)

        stock.totalShareholdersEquityLastQuarter = balanceSheetLastQuarter?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquityLastYear = balanceSheetLastYear?.totalStockholderEquity?.raw?.toLong()
        stock.totalShareholdersEquityGrowthLastQuarter = percentGrowth(stock.totalShareholdersEquityLastQuarter, balanceSheetPreviousQuarter?.totalStockholderEquity?.raw)
        stock.totalShareholdersEquityGrowthLastYear = percentGrowth(stock.totalShareholdersEquityLastYear, balanceSheet2YearsAgo?.totalStockholderEquity?.raw)
        stock.totalShareholdersEquityGrowthLast3Years = percentGrowth(stock.totalShareholdersEquityLastYear, balanceSheet3YearsAgo?.totalStockholderEquity?.raw)

        stock.currentLiabilitiesToEquityLastQuarter = div(stock.currentLiabilitiesLastQuarter?.toDouble(), stock.totalShareholdersEquityLastQuarter?.toDouble())
        val currentLiabilitiesToEquityPreviousQuarter = div(balanceSheetPreviousQuarter?.totalCurrentLiabilities?.raw?.toDouble(),
            balanceSheetPreviousQuarter?.totalStockholderEquity?.raw?.toDouble())
        stock.currentLiabilitiesToEquityGrowthLastQuarter = percentGrowth(stock.currentLiabilitiesToEquityLastQuarter, currentLiabilitiesToEquityPreviousQuarter)
        val currentLiabilitiesToEquity2YearsAgo = div(balanceSheet2YearsAgo?.totalCurrentLiabilities?.raw?.toDouble(),
            balanceSheet2YearsAgo?.totalStockholderEquity?.raw?.toDouble())
        stock.currentLiabilitiesToEquityLastYear = div(stock.currentLiabilitiesLastYear?.toDouble(), stock.totalShareholdersEquityLastYear?.toDouble())
        stock.currentLiabilitiesToEquityGrowthLastYear = percentGrowth(stock.currentLiabilitiesToEquityLastYear, currentLiabilitiesToEquity2YearsAgo)
        val currentLiabilitiesToEquity3YearsAgo = div(balanceSheet3YearsAgo?.totalCurrentLiabilities?.raw?.toDouble(),
            balanceSheet3YearsAgo?.totalStockholderEquity?.raw?.toDouble())
        stock.currentLiabilitiesToEquityGrowthLast3Years = percentGrowth(stock.currentLiabilitiesToEquityLastYear, currentLiabilitiesToEquity3YearsAgo)

        stock.totalLiabilitiesToEquityLastQuarter = div(stock.totalLiabilitiesLastQuarter?.toDouble(), stock.totalShareholdersEquityLastQuarter?.toDouble())
        val totalLiabilitiesToEquityPreviousQuarter = div(balanceSheetPreviousQuarter?.totalCurrentLiabilities?.raw?.toDouble(),
            balanceSheetPreviousQuarter?.totalStockholderEquity?.raw?.toDouble())
        stock.totalLiabilitiesToEquityGrowthLastQuarter = percentGrowth(stock.totalLiabilitiesToEquityLastQuarter, totalLiabilitiesToEquityPreviousQuarter)
        val totalLiabilitiesToEquity2YearsAgo = div(balanceSheet2YearsAgo?.totalCurrentLiabilities?.raw?.toDouble(),
            balanceSheet2YearsAgo?.totalStockholderEquity?.raw?.toDouble())
        stock.totalLiabilitiesToEquityLastYear = div(stock.totalLiabilitiesLastYear?.toDouble(), stock.totalShareholdersEquityLastYear?.toDouble())
        stock.totalLiabilitiesToEquityGrowthLastYear = percentGrowth(stock.totalLiabilitiesToEquityLastYear, totalLiabilitiesToEquity2YearsAgo)
        val totalLiabilitiesToEquity3YearsAgo = div(balanceSheet3YearsAgo?.totalCurrentLiabilities?.raw?.toDouble(),
            balanceSheet3YearsAgo?.totalStockholderEquity?.raw?.toDouble())
        stock.totalLiabilitiesToEquityGrowthLast3Years = percentGrowth(stock.totalLiabilitiesToEquityLastYear, totalLiabilitiesToEquity3YearsAgo)

        stock.stockRepurchasedLastQuarter = cashFlowLastQuarter?.repurchaseOfStock?.raw?.toLong()
        stock.stockRepurchasedLastYear = cashFlowLastYear?.repurchaseOfStock?.raw?.toLong()
        stock.stockRepurchasedGrowthLastQuarter = percentGrowth(stock.stockRepurchasedLastQuarter, cashFlowPreviousQuarter?.repurchaseOfStock?.raw)
        stock.stockRepurchasedGrowthLastYear = percentGrowth(stock.stockRepurchasedLastYear, cashFlow2YearsAgo?.repurchaseOfStock?.raw)
        stock.stockRepurchasedGrowthLast3Years = percentGrowth(stock.stockRepurchasedLastYear, cashFlow3YearsAgo?.repurchaseOfStock?.raw)

        stock.stockLastQuarter = balanceSheetLastQuarter?.commonStock?.raw?.toLong()
        stock.stockLastYear = balanceSheetLastYear?.commonStock?.raw?.toLong()
        stock.stockGrowthLastQuarter = percentGrowth(stock.stockLastQuarter, balanceSheetPreviousQuarter?.commonStock?.raw)
        stock.stockGrowthLastYear = percentGrowth(stock.stockLastYear, balanceSheet2YearsAgo?.commonStock?.raw)
        stock.stockGrowthLast3Years = percentGrowth(stock.stockLastYear, balanceSheet3YearsAgo?.commonStock?.raw)

        stock.epsCurrentQuarterEstimate = earnings?.earningsChart?.currentQuarterEstimate?.raw?.toDouble()
        val epsQuarterly = earnings?.earningsChart?.quarterly?.reversed()
        stock.epsLastQuarter = epsQuarterly?.getOrNull(0)?.actual?.raw?.toDouble()
        stock.eps2QuartersAgo = epsQuarterly?.getOrNull(1)?.actual?.raw?.toDouble()
        stock.eps3QuartersAgo = epsQuarterly?.getOrNull(2)?.actual?.raw?.toDouble()
        stock.eps4QuartersAgo = epsQuarterly?.getOrNull(3)?.actual?.raw?.toDouble()
        stock.epsGrowthLastQuarter = percentGrowth(stock.epsLastQuarter, stock.eps2QuartersAgo, 0.01)
        stock.epsGrowthLast2Quarters = percentGrowth(stock.epsLastQuarter, stock.eps3QuartersAgo, 0.01)
        stock.epsGrowthLast3Quarters = percentGrowth(stock.epsLastQuarter, stock.eps4QuartersAgo, 0.01)
        stock.epsGrowthEstimateLastQuarter = percentGrowth(stock.epsCurrentQuarterEstimate, stock.epsLastQuarter, 0.01)

        if(timeSeries.annualDilutedEPS != null) {
            for ((index, annualEps) in timeSeries.annualDilutedEPS.withIndex()) {
                when (index) {
                    3 -> stock.epsLastYear = annualEps?.reportedValue?.raw
                    2 -> stock.eps2YearsAgo = annualEps?.reportedValue?.raw
                    1 -> stock.eps3YearsAgo = annualEps?.reportedValue?.raw
                    0 -> stock.eps4YearsAgo = annualEps?.reportedValue?.raw
                }
            }
        }
        stock.epsGrowthLastYear = percentGrowth(stock.epsLastYear, stock.eps2YearsAgo, 0.01)
        stock.epsGrowthLast2Years = percentGrowth(stock.epsLastYear, stock.eps3YearsAgo, 0.01)
        stock.epsGrowthLast3Years = percentGrowth(stock.epsLastYear, stock.eps4YearsAgo, 0.01)

        stock.quarterEnds = balanceSheetStatements?.map { it.endDate.raw }
        stock.yearEnds = timeSeries?.timestamp?.reversed()
    }

    private fun processStatistics(stats: StatisticsResponse, stock: StockInfo) {
        val financialData = stats.financialData
        val price = stats.price
        val defaultKeyStatistics = stats.defaultKeyStatistics
        val summaryDetail = stats.summaryDetail
        val calendarEvents = stats.calendarEvents

        stock.companyName = stats.quoteType?.shortName
        stock.price = price?.regularMarketPrice?.raw
        stock.currency = price?.currency?.let { it -> Currency.valueOf(it) }
        stock.financialCurrency = financialData?.financialCurrency?.let { it -> Currency.valueOf(it) }
        val differentCurrencies = stock.currency != stock.financialCurrency
        val financialToStockRate = if (differentCurrencies && stock.currency != null && stock.financialCurrency != null) {
            exchangeRateClient.getRate(stock.financialCurrency!!, stock.currency!!)
        } else {
            1.0
        }

        stock.change = percent(price?.regularMarketChangePercent?.raw)
        stock.enterpriseValue = defaultKeyStatistics.enterpriseValue?.raw

        stock.targetLowPrice = financialData.targetLowPrice?.raw.let { multiply(it, financialToStockRate) }
        stock.targetMedianPrice = financialData.targetMedianPrice?.raw.let { multiply(it, financialToStockRate) }

        if (stock.targetLowPrice != null && stock.price != null) {
            stock.belowTargetLowPricePercent = ((stock.targetLowPrice!! - stock.price!!) / stock.price!!) * 100.0
        }
        if (stock.targetMedianPrice != null && stock.price != null) {
            stock.belowTargetMedianPricePercent = ((stock.targetMedianPrice!! - stock.price!!) / stock.price!!) * 100.0
        }

        stock.totalCashPerShare = financialData.totalCashPerShare?.raw
        if (stock.totalCashPerShare != null && stock.price != null) {
            stock.totalCashPerSharePercent = (stock.totalCashPerShare!! / stock.price!!) * 100.0
        }
        if (stock.totalLiabilitiesLastQuarter != null && stock.totalShareholdersEquityLastQuarter != null) {
            stock.totalDebtEquity = div(stock.totalLiabilitiesLastQuarter!!.toDouble(), stock.totalShareholdersEquityLastQuarter!!.toDouble())
        }

        stock.trailingPE = summaryDetail.trailingPE?.raw
        stock.forwardPE = summaryDetail.forwardPE?.raw
        stock.priceToSalesTrailing12Months = summaryDetail.priceToSalesTrailing12Months?.raw
        if (!differentCurrencies) {
            stock.priceBook = defaultKeyStatistics.priceToBook?.raw
        } else {
            // P /B = Market Price per Share / BVPS
            //BVPS = (Total Equity − Preferred Equity) / Total Shares Outstanding
            //​	https://www.investopedia.com/terms/b/bvps.asp
        }
        stock.enterpriseValueRevenue = defaultKeyStatistics.enterpriseToRevenue?.raw
        stock.enterpriseValueEBITDA = defaultKeyStatistics.enterpriseToEbitda?.raw

        stock.yoyQuarterlyRevenueGrowthPercent = percent(financialData.revenueGrowth?.raw)
        if (!differentCurrencies) {
            stock.priceEarningGrowth = defaultKeyStatistics.pegRatio?.raw
            val trailingEps = defaultKeyStatistics.trailingEps?.raw
            if (stock.trailingPE != null && trailingEps != null) {
                stock.trailingPriceEarningGrowth = stock.trailingPE!! / trailingEps
            }
        }

        stock.week52Change = percent(defaultKeyStatistics.get52WeekChange()?.raw)
        stock.week52Low = summaryDetail.fiftyTwoWeekLow?.raw
        if (stock.price != null && stock.week52Low != null) {
            stock.week52AboveLowPercent = ((stock.price!! - stock.week52Low!!) / stock.price!!) * 100.0
        }
        stock.week52High = summaryDetail.fiftyTwoWeekHigh?.raw
        if (stock.price != null && stock.week52High != null) {
            stock.week52BelowHighPercent = ((stock.week52High!! - stock.price!!) / stock.price!!) * 100.0
        }

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