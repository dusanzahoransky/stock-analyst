package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.YahooFinanceClient
import com.github.dusanzahoransky.stockanalyst.model.Ticker
import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Interval
import com.github.dusanzahoransky.stockanalyst.model.enums.Range
import com.github.dusanzahoransky.stockanalyst.model.mongo.Chart
import com.github.dusanzahoransky.stockanalyst.model.mongo.Etf
import com.github.dusanzahoransky.stockanalyst.model.mongo.EtfStatistics
import com.github.dusanzahoransky.stockanalyst.model.yahoo.chart.ChartResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.etfstatistics.EtfStatisticsResponse
import com.github.dusanzahoransky.stockanalyst.repository.ChartRepo
import com.github.dusanzahoransky.stockanalyst.repository.EtfRepo
import com.github.dusanzahoransky.stockanalyst.repository.EtfStatisticsRepo
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils.CacheContext
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percent
import com.github.dusanzahoransky.stockanalyst.util.FormattingUtils.Companion.epochSecToLocalDate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.util.*

@Service
class EtfService @Autowired constructor(
    val watchlistRepo: WatchlistRepo,
    val etfRepo: EtfRepo,
    val yahooFinanceClient: YahooFinanceClient,
    val etfStatisticsRepo: EtfStatisticsRepo,
    val chartRepo: ChartRepo
) {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun getWatchlistEtfs(watchlistName: String, mockData: Boolean, refreshDynamicData: Boolean, refreshOlderThan: LocalDate): List<Etf> {
        val watchlist = watchlistRepo.findById(watchlistName).orElseThrow()

        val cacheCtx = CacheContext(refreshDynamicData = refreshDynamicData, mockData = mockData, refreshOlderThan = refreshOlderThan)

        return watchlist.tickers.map { ticker ->
            findOrLoadEtf(Ticker.fromString(ticker), cacheCtx)
        }
    }

    private fun findOrLoadEtf(ticker: Ticker, cacheCtx: CacheContext): Etf {
        val (etfStatistics, chart) = loadEtf(ticker, cacheCtx)

        var etf = etfRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        if(etf == null) {
            etf= Etf(symbol = ticker.symbol, exchange = ticker.exchange)

            processStatistics(etfStatistics.response, etf)
            processChart(chart.response, etf)

        } else {
            if (etf.statisticsLastUpdated.isBefore(etfStatistics.getLastRefreshDate())) {
                processStatistics(etfStatistics.response, etf)
                etf.statisticsLastUpdated = etfStatistics.getLastRefreshDate()
            }
            if (etf.chartLastUpdated.isBefore(chart.getLastRefreshDate())) {
                processChart(chart.response, etf)
                etf.chartLastUpdated = chart.getLastRefreshDate()
            }
        }

        //do not save mock data
        if (cacheCtx.mockData) {
            return etf
        }
        log.debug("Saving ${if (etf.id == null) "new" else "updated"} stock $ticker")
        etfRepo.save(etf)

        reduceData(etf)   //reduce large data sets being sent to UI
        return etf
    }

    private fun reduceData(etf: Etf) {
        val dayOfWeekForSampling = etf.price.lastKey().dayOfWeek
        etf.price = etf.price.filterKeys { key -> key.dayOfWeek == dayOfWeekForSampling}.toSortedMap()
    }

    data class PartialEtfData(
        val etfStatistics: EtfStatistics,
        val chart: Chart
    )

    private fun loadEtf(ticker: Ticker, cacheCtx: CacheContext): PartialEtfData {
        val chart = loadChart(ticker, cacheCtx)
        val etfStatistics = loadEtfStatistics(ticker, cacheCtx)
        return PartialEtfData(etfStatistics, chart)
    }

    private fun loadChart(ticker: Ticker, cacheCtx: CacheContext): Chart {
        val cachedData = chartRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (CacheUtils.useCacheDynamicData(cacheCtx, cachedData)) {
            log.debug("Retrieving Chart from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getChart(ticker, Interval.OneDay, Range.TenYears, cacheCtx.mockData)

        val data = Chart(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not cache mock data
        if (cacheCtx.mockData) {
            return data
        }

        //delete previous version
        chartRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { chartRepo.delete(it) }
        //store new version
        log.debug("Saving Chart $ticker into DB")
        return chartRepo.insert(data)
    }

    private fun loadEtfStatistics(ticker: Ticker, cacheCtx: CacheContext): EtfStatistics {
        val cachedData = etfStatisticsRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (CacheUtils.useCacheDynamicData(cacheCtx, cachedData)) {
            log.debug("Retrieving EtfStatistics from cache: $ticker")
            return cachedData!!
        }

        val response = yahooFinanceClient.getEtfStatistics(ticker, cacheCtx.mockData)

        val data = EtfStatistics(null, ticker.symbol, ticker.exchange, LocalDate.now(), response)

        //do not cache mock data
        if (cacheCtx.mockData) {
            return data
        }

        //delete previous version
        etfStatisticsRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { etfStatisticsRepo.delete(it) }
        //store new version
        log.debug("Saving EtfStatistics $ticker into DB")
        return etfStatisticsRepo.insert(data)
    }

    private fun processChart(chart: ChartResponse, stock: Etf) {
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

    private fun processStatistics(stats: EtfStatisticsResponse, stock: Etf) {
        val price = stats.price
        val summaryDetail = stats.summaryDetail
        val topHoldings = stats.topHoldings
        val defaultKeyStatistics = stats.defaultKeyStatistics
        val trailingReturns = stats.fundPerformance?.trailingReturns

        stock.companyName = stats.quoteType?.longName
        stock.currency = summaryDetail?.currency?.let { it -> Currency.valueOf(it) }

        stock.asOfDate = trailingReturns?.asOfDate?.raw?.let { epochSecToLocalDate(it) }
        stock.fundInceptionDate = defaultKeyStatistics?.fundInceptionDate?.raw?.let { epochSecToLocalDate(it) }

        val entryDate = stock.asOfDate ?: LocalDate.now()

        addEntry(stock.totalAssets, summaryDetail?.totalAssets?.raw, entryDate)

        addEntry(stock.yield, percent(summaryDetail?.yield?.raw), entryDate)

        addEntry(stock.ytdReturn, percent(trailingReturns?.ytd?.raw), entryDate)
        addEntry(stock.threeYearAverageReturn, percent(defaultKeyStatistics?.threeYearAverageReturn?.raw), entryDate)
        addEntry(stock.fiveYearAverageReturn, percent(defaultKeyStatistics?.fiveYearAverageReturn?.raw), entryDate)

        addEntry(stock.priceToEarnings, topHoldings?.equityHoldings?.priceToEarnings?.raw, entryDate)
        addEntry(stock.priceToBook, topHoldings?.equityHoldings?.priceToBook?.raw, entryDate)
        addEntry(stock.priceToCashflow, topHoldings?.equityHoldings?.priceToCashflow?.raw, entryDate)
        addEntry(stock.priceToSales, topHoldings?.equityHoldings?.priceToSales?.raw, entryDate)

        addEntry(stock.oneMonth, percent(trailingReturns?.oneMonth?.raw), entryDate)
        addEntry(stock.threeMonth, percent(trailingReturns?.threeMonth?.raw), entryDate)
        addEntry(stock.oneYear, percent(trailingReturns?.oneYear?.raw), entryDate)
        addEntry(stock.threeYear, percent(trailingReturns?.threeYear?.raw), entryDate)
        addEntry(stock.fiveYear, percent(trailingReturns?.fiveYear?.raw), entryDate)
        addEntry(stock.tenYear, percent(trailingReturns?.tenYear?.raw), entryDate)

        addEntry(stock.lastBearMkt, percent(trailingReturns?.lastBearMkt?.raw), entryDate)
        addEntry(stock.lastBullMkt, percent(trailingReturns?.lastBullMkt?.raw), entryDate)

        addEntry(stock.annualHoldingsTurnover, percent(defaultKeyStatistics?.annualHoldingsTurnover?.raw), entryDate)
        addEntry(stock.annualReportExpenseRatio, percent(defaultKeyStatistics?.annualReportExpenseRatio?.raw), entryDate)

        addEntry(stock.averageDailyVolume3Month, price?.averageDailyVolume3Month?.raw, entryDate)
        addEntry(stock.averageDailyVolume10Day, price?.averageDailyVolume10Day?.raw, entryDate)
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

}