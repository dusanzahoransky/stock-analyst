package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.MorningStartClient
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.KeyRatiosFinancials
import com.github.dusanzahoransky.stockanalyst.model.mongo.Ratios
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatios
import com.github.dusanzahoransky.stockanalyst.repository.KeyRatiosFinancialsRepo
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class KeyRatiosFinancialsService @Autowired constructor(
    val watchlistRepo: WatchlistRepo,
    val krpRepo: KeyRatiosFinancialsRepo,
    /*val ratiosRepo: RatiosRepo,*/
    val morningStarClient: MorningStartClient
) {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun getWatchlistRatios(watchlist: Watchlist, forceRefresh: Boolean, mockData: Boolean): List<StockRatios> {
        val watchlistTickers = watchlistRepo.getWatchlist(watchlist)

        val keyRatiosFinantials = watchlistTickers.mapNotNull { ticker -> findOrLoad(ticker, forceRefresh, mockData) }

        return toStockRatios(keyRatiosFinantials)
    }

    private fun toStockRatios(keyRatiosFinantials: List<KeyRatiosFinancials>): MutableList<StockRatios> {
        val stockRatios = mutableListOf<StockRatios>()
        for (krf in keyRatiosFinantials) {
            val stockRatio = StockRatios(null, krf.symbol, krf.mic)
            for (result in krf.results) {
                val periodDate = LocalDate.parse(result.periodEndDate)
                val firstSection = result.sections[0]
                val ratios = Ratios()
                for (item in firstSection.lineItems) {
                    item.additionalProperties.entries.forEach {
                        when (it.key) {
                            "BOOK VALUE PER SHARE *" -> ratios.bookValuePerShare = it.value as Double
                            "CAP SPENDING" -> ratios.capSpending = it.value as Double
                            "DIVIDENDS" -> ratios.dividends = it.value as Double
                            "EARNINGS PER SHARE" -> ratios.earningsPerShare = it.value as Double
                            "FREE CASH FLOW" -> ratios.freeCashFlow = it.value as Double
                            "FREE CASH FLOW PER SHARE *" -> ratios.freeCashFlowPerShare = it.value as Double
                            "GROSS MARGIN %" -> ratios.grossMargin = it.value as Double
                            "NET INCOME" -> ratios.netIncome = it.value as Double
                            "OPERATING CASH FLOW" -> ratios.operatingCashFlow = it.value as Double
                            "OPERATING INCOME" -> ratios.operatingIncome = it.value as Double
                            "OPERATING MARGIN %" -> ratios.operatingMargin = it.value as Double
                            "PAYOUT RATIO % *" -> ratios.payoutRatio = it.value as Double
                            "REVENUE" -> ratios.revenue = it.value as Double
                            "SHARES" -> ratios.shares = it.value as Double
                            "WORKING CAPITAL" -> ratios.workingCapital = it.value as Double
                        }
                    }

                }
                stockRatio.periods[periodDate] = ratios
            }
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

        //delete previous version
        krpRepo.findBySymbolAndMic(ticker.symbol, ticker.getMic())?.let { krpRepo.delete(it) }
        //store new version
        log.debug("Saving $ticker into DB")
        return krpRepo.insert(krp)
    }
}