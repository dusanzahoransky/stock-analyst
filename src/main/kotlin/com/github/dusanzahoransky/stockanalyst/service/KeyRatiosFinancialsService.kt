package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.ExchangeRateClient
import com.github.dusanzahoransky.stockanalyst.client.MorningStartClient
import com.github.dusanzahoransky.stockanalyst.client.YahooFinanceClient
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.KeyRatiosFinancials
import com.github.dusanzahoransky.stockanalyst.repository.KeyRatiosFinancialsRepo
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class KeyRatiosFinancialsService @Autowired constructor(
    val watchlistRepo: WatchlistRepo,
    val krpRepo: KeyRatiosFinancialsRepo,
    val morningStarClient: MorningStartClient
) {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun getWatchlistKRF(watchlist: Watchlist, forceRefresh: Boolean, mockData: Boolean): List<KeyRatiosFinancials> {
        val watchlistTickers = watchlistRepo.getWatchlist(watchlist)

        return watchlistTickers.mapNotNull { ticker -> findOrLoad(ticker, forceRefresh, mockData) }
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
        krp = KeyRatiosFinancials(null, krpResponse.results)

        //delete previous version
        krpRepo.findBySymbolAndMic(ticker.symbol, ticker.getMic())?.let { krpRepo.delete(it) }
        //store new version
        log.debug("Saving $ticker into DB")
        return krpRepo.insert(krp)
    }
}