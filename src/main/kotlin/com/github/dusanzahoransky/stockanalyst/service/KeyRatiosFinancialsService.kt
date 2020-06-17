package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.MorningStartClient
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.mongo.KeyRatiosFinancials
import com.github.dusanzahoransky.stockanalyst.repository.KeyRatiosFinancialsRepo
import com.github.dusanzahoransky.stockanalyst.util.CacheUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class KeyRatiosFinancialsService @Autowired constructor(
    val krpRepo: KeyRatiosFinancialsRepo,
    val morningStarClient: MorningStartClient
){

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun findOrLoad(ticker: StockTicker, forceRefreshCache: Boolean, mockData: Boolean, forceRefreshDate: LocalDate): KeyRatiosFinancials? {
        var krp = krpRepo.findBySymbolAndMic(ticker.symbol, ticker.getMic())

        //retrieve from cache
        if (CacheUtils.useCache(forceRefreshCache, krp, forceRefreshDate)) {
            log.debug("Retrieving stock info from cache: $ticker")
            return krp
        }

        val krpResponse = morningStarClient.getKeyRatiosFinancials(ticker, mockData)

        krp = KeyRatiosFinancials(null, ticker.symbol, ticker.getMic(), LocalDate.now(), krpResponse.results)

        //do not cache mock data
        if (mockData) {
            return krp
        }

        //delete previous version
        krpRepo.findBySymbolAndMic(ticker.symbol, ticker.getMic())?.let { krpRepo.delete(it) }
        //store new version
        log.debug("Saving $ticker into DB")
        return krpRepo.insert(krp)
    }
}