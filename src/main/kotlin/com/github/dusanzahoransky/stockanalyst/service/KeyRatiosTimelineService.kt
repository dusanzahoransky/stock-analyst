package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.MorningStartClient
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.KeyRatiosFinancials
import com.github.dusanzahoransky.stockanalyst.model.mongo.Ratios
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatiosTimeline
import com.github.dusanzahoransky.stockanalyst.repository.KeyRatiosFinancialsRepo
import com.github.dusanzahoransky.stockanalyst.repository.StockRatiosTimelineRepo
import com.github.dusanzahoransky.stockanalyst.repository.StockRepo
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class KeyRatiosTimelineService @Autowired constructor(
    val stockRepo: StockRepo,
    val watchlistRepo: WatchlistRepo,
    val krpRepo: KeyRatiosFinancialsRepo,
    val ratiosTimelineRepo: StockRatiosTimelineRepo,
    val morningStarClient: MorningStartClient,
    val stockAnalysisService: StockAnalysisService
) {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun getWatchlistKeyRatios(watchlist: Watchlist, forceRefresh: Boolean, mockData: Boolean): List<StockRatiosTimeline> {
        val watchlistTickers = watchlistRepo.getWatchlist(watchlist)

        val keyRatiosFinantials = watchlistTickers.mapNotNull { ticker -> findOrLoad(ticker, forceRefresh, mockData) }

        var stockRatiosList = toStockRatios(keyRatiosFinantials)

        stockRatiosList = mergeWithDb(stockRatiosList)

        return stockRatiosList
    }

    fun mergeWithDb(stockRatiosTimelineList: List<StockRatiosTimeline>): List<StockRatiosTimeline>  {
        val mergedList = mutableListOf<StockRatiosTimeline>()
        for(stockRatios in stockRatiosTimelineList){
            val stockRatiosDb = ratiosTimelineRepo.findBySymbolAndMic(stockRatios.symbol, stockRatios.mic)
            if(stockRatiosDb == null){
                log.debug("Saving new StockRatios ${stockRatios.symbol} ${stockRatios.mic} into DB")
                ratiosTimelineRepo.save(stockRatios)
                mergedList.add(stockRatios)
            } else{
                log.debug("Merging StockRatios ${stockRatios.symbol} ${stockRatios.mic} with existing DB record")
                stockRatiosDb.periods.putAll(stockRatios.periods)
                stockRatiosDb.date = stockRatios.date
                ratiosTimelineRepo.save(stockRatiosDb)
                mergedList.add(stockRatiosDb)
            }
        }
        return mergedList
    }

    private fun toStockRatios(keyRatiosFinantials: List<KeyRatiosFinancials>): List<StockRatiosTimeline> {
        val stockRatios = mutableListOf<StockRatiosTimeline>()
        for (krf in keyRatiosFinantials) {
            val stockRatio = StockRatiosTimeline(null, krf.symbol, krf.mic, Exchange.fromMic(krf.mic))
            val stockInfo = stockRepo.findBySymbolAndExchange(stockRatio.symbol, stockRatio.exchange)
            for (result in krf.results) {
                val periodDate = LocalDate.parse(result.periodEndDate)
                val firstSection = result.sections[0]
                val ratios = Ratios()
                for (item in firstSection.lineItems) {
                        when (item.label) {
                            "BOOK VALUE PER SHARE *" -> ratios.bookValuePerShare = item.value
                            "CAP SPENDING" -> ratios.capSpending = item.value
                            "DIVIDENDS" -> ratios.dividends = item.value
                            "EARNINGS PER SHARE" -> ratios.earningsPerShare = item.value
                            "FREE CASH FLOW" -> ratios.freeCashFlow = item.value
                            "FREE CASH FLOW PER SHARE *" -> ratios.freeCashFlowPerShare = item.value
                            "GROSS MARGIN %" -> ratios.grossMargin = item.value
                            "NET INCOME" -> ratios.netIncome = item.value
                            "OPERATING CASH FLOW" -> ratios.operatingCashFlow = item.value
                            "OPERATING INCOME" -> ratios.operatingIncome = item.value
                            "OPERATING MARGIN %" -> ratios.operatingMargin = item.value
                            "PAYOUT RATIO % *" -> ratios.payoutRatio = item.value
                            "REVENUE" -> ratios.revenue = item.value
                            "SHARES" -> ratios.shares = item.value
                            "WORKING CAPITAL" -> ratios.workingCapital = item.value
                        }

                }
                if(stockInfo?.chartData != null){
                    ratios.price = stockAnalysisService.chartDataFirstBefore(periodDate, stockInfo.chartData!!)?.price
                    ratios.pe = div(ratios.price, ratios.earningsPerShare)
                }
                stockRatio.periods[periodDate] = ratios
            }
            stockRatio.periods = stockRatio.periods.toSortedMap(compareBy { it })
            stockRatios.add(stockRatio)
        }
        return stockRatios
    }

    private fun findOrLoad(ticker: StockTicker, forceRefreshCache: Boolean, mockData: Boolean): KeyRatiosFinancials? {
        var krp = krpRepo.findBySymbolAndMic(ticker.symbol, ticker.getMic())

        //retrieve from cache
        if (!forceRefreshCache && krp != null) {
            log.debug("Retrieving stock info from cache: $ticker")
            return krp
        }

        val krpResponse = morningStarClient.getKeyRatiosFinancials(ticker, mockData)
        if (krpResponse == null) {
            log.error("Failed to retrieve stock Key Ratios Financials from MorningStar $ticker")
            return null
        }
        krp = KeyRatiosFinancials(null, ticker.symbol, ticker.getMic(), LocalDate.now(), krpResponse.results)

        //do not cache mock data
        if(mockData) {
            return krp
        }

        //delete previous version
        krpRepo.findBySymbolAndMic(ticker.symbol, ticker.getMic())?.let { krpRepo.delete(it) }
        //store new version
        log.debug("Saving $ticker into DB")
        return krpRepo.insert(krp)
    }
}