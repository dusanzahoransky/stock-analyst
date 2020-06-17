package com.github.dusanzahoransky.stockanalyst.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.github.dusanzahoransky.stockanalyst.model.Ticker
import com.github.dusanzahoransky.stockanalyst.model.ms.keyratios.KetRatiosResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class MorningStartClient @Autowired constructor(
    @Qualifier("morningStarRestTemplate") val restTemplate: RestTemplate
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    companion object {
        var lastCallTime: Long = 0
        const val CALL_THRESHOLD_TIMEOUT = 500L
    }

    fun getKeyRatiosFinancials(ticker: Ticker, mockData: Boolean): KetRatiosResponse {
        if (mockData) {
            val statisticsMock = ClassPathResource("keyratiosGOOGL.json")
            return jacksonObjectMapper().readValue(statisticsMock.inputStream, jacksonTypeRef<KetRatiosResponse>())
        }
        //avoid hitting rate limit 2 calls a second
        if (System.currentTimeMillis() - lastCallTime < CALL_THRESHOLD_TIMEOUT) {
            Thread.sleep(CALL_THRESHOLD_TIMEOUT)
        }
        val response = restTemplate.getForObject(
            "https://morningstar1.p.rapidapi.com/keyratios/financials?Ticker={symbol}&Mic={mic}",
            KetRatiosResponse::class.java,
            mapOf("symbol" to ticker.symbol, "mic" to ticker.getMic())
        ) ?: throw RuntimeException("Failed to load $ticker")

        logger.debug("Financial KeyRatios from Morningstar API: ${jacksonObjectMapper().writeValueAsString(response)}")
        lastCallTime = System.currentTimeMillis()
        return response
    }
}