package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.YahooFinanceClient
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Interval
import com.github.dusanzahoransky.stockanalyst.model.enums.Range
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.IndexChartData
import com.github.dusanzahoransky.stockanalyst.model.mongo.IndexInfo
import com.github.dusanzahoransky.stockanalyst.model.yahoo.chart.ChartResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.istatistics.IndexStatisticsResponse
import com.github.dusanzahoransky.stockanalyst.repository.IndexRepo
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percent
import com.github.dusanzahoransky.stockanalyst.util.FormattingUtils.Companion.epochSecToLocalDate
import com.github.dusanzahoransky.stockanalyst.util.FormattingUtils.Companion.localDateToEpochSec
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Period

@Service
class IndexService @Autowired constructor(
    val watchlistRepo: WatchlistRepo,
    val indexRepo: IndexRepo,
    val yahooFinanceClient: YahooFinanceClient
) {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun getWatchlistStocks(watchlist: Watchlist, forceRefresh: Boolean, mockData: Boolean): List<IndexInfo> {
        val watchlistTickers = watchlistRepo.getWatchlist(watchlist)
        return watchlistTickers.mapNotNull { ticker -> findOrLoad(ticker, forceRefresh, mockData) }
    }

    private fun findOrLoad(ticker: StockTicker, forceRefreshCache: Boolean, mockData: Boolean): IndexInfo? {
        var stock = indexRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (!forceRefreshCache && stock != null) {
            log.debug("Retrieving stock info from cache: $ticker")
            return stock
        }

        stock = IndexInfo(symbol = ticker.symbol, exchange = ticker.exchange)
        //load from yahoo

        val stats = yahooFinanceClient.getIndexStatistics(ticker, mockData)
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
        indexRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { indexRepo.delete(it) }
        //store new version
        log.debug("Saving $ticker into DB")
        return indexRepo.insert(stock)
    }

    private fun processChart(chart: ChartResponse, stock: IndexInfo, samplingInterval: Period) {
        val result = chart.chart?.result?.get(0) ?: return
        val closePrices = result.indicators?.quote?.get(0)?.close ?: return
        val timestamps = result.timestamp?.map { epochSecToLocalDate(it) } ?: return

        val chartTo = timestamps.last()
        var currentInterval = timestamps.first { t -> t.dayOfWeek == DayOfWeek.MONDAY }
        val samplingDays = samplingInterval.days
        val chartData = mutableListOf<IndexChartData>()

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
        closePrices: MutableList<Double?>): IndexChartData {

        val timestampIndexAtInterval = timestamps.indexOfFirst { !it.isBefore(currentInterval) }
        val priceAtInterval = closePrices[timestampIndexAtInterval]

        return IndexChartData(localDateToEpochSec(currentInterval), priceAtInterval)
    }


    private fun processStatistics(stats: IndexStatisticsResponse, stock: IndexInfo) {
        val price = stats.price
        val summaryDetail = stats.summaryDetail
        val topHoldings = stats.topHoldings
        val defaultKeyStatistics = stats.defaultKeyStatistics
        val trailingReturns = stats.fundPerformance?.trailingReturns

        stock.companyName = stats.quoteType?.longName
        stock.price = price?.regularMarketPrice?.raw
        stock.currency = summaryDetail?.currency?.let { it -> Currency.valueOf(it) }
        stock.change = percent(price?.regularMarketChangePercent?.raw)
        stock.totalAssets = summaryDetail?.totalAssets?.raw

        stock.yield = percent(summaryDetail?.yield?.raw)

        stock.ytdReturn = percent(summaryDetail?.ytdReturn?.raw)
        stock.threeYearAverageReturn = percent(defaultKeyStatistics?.threeYearAverageReturn?.raw)
        stock.fiveYearAverageReturn = percent(defaultKeyStatistics?.fiveYearAverageReturn?.raw)

        stock.priceToEarnings = topHoldings?.equityHoldings?.priceToEarnings?.raw
        stock.priceToBook = topHoldings?.equityHoldings?.priceToBook?.raw
        stock.priceToCashflow = topHoldings?.equityHoldings?.priceToCashflow?.raw
        stock.priceToSales = topHoldings?.equityHoldings?.priceToSales?.raw

        stock.fiftyTwoWeekLow = summaryDetail?.fiftyTwoWeekLow?.raw
        if (stock.price != null && stock.fiftyTwoWeekLow != null) {
            stock.fiftyTwoAboveLowPercent = ((stock.price!! - stock.fiftyTwoWeekLow!!) / stock.price!!) * 100.0
        }
        stock.fiftyTwoWeekHigh = summaryDetail?.fiftyTwoWeekHigh?.raw
        if (stock.price != null && stock.fiftyTwoWeekHigh != null) {
            stock.fiftyTwoBelowHighPercent = ((stock.fiftyTwoWeekHigh!! - stock.price!!) / stock.price!!) * 100.0
        }

        stock.asOfDate = trailingReturns?.asOfDate?.raw?.let { epochSecToLocalDate(it) }

        stock.oneMonth = percent(trailingReturns?.oneMonth?.raw)
        stock.threeMonth = percent(trailingReturns?.threeMonth?.raw)
        stock.oneYear = percent(trailingReturns?.oneYear?.raw)
        stock.threeYear = percent(trailingReturns?.threeYear?.raw)
        stock.ytd = percent(trailingReturns?.ytd?.raw)
        stock.fiveYear = percent(trailingReturns?.fiveYear?.raw)
        stock.tenYear = percent(trailingReturns?.tenYear?.raw)

        stock.lastBearMkt = percent(trailingReturns?.lastBearMkt?.raw)
        stock.lastBullMkt = percent(trailingReturns?.lastBullMkt?.raw)


        stock.annualHoldingsTurnover = percent(defaultKeyStatistics?.annualHoldingsTurnover?.raw)
        stock.annualReportExpenseRatio = percent(defaultKeyStatistics?.annualReportExpenseRatio?.raw)

        stock.averageDailyVolume3Month = price?.averageDailyVolume3Month?.raw
        stock.averageDailyVolume10Day = price?.averageDailyVolume10Day?.raw

        stock.fundInceptionDate = defaultKeyStatistics?.fundInceptionDate?.raw?.let { epochSecToLocalDate(it) }

    }

}