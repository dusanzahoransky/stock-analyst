package com.github.dusanzahoransky.stockanalyst.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
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
    }

    fun getKeyRatiosFinancials(ticker: StockTicker, mockData: Boolean): KetRatiosResponse? {
        if (mockData) {
            val statisticsMock = ClassPathResource("keyratiosMSFT.json")
            return jacksonObjectMapper().readValue(statisticsMock.inputStream, jacksonTypeRef<KetRatiosResponse>())
        }
        return try {
            val response = restTemplate.getForObject(

                "https://morningstar1.p.rapidapi.com/keyratios/financials?Ticker={symbol}&Mic={mic}",
                KetRatiosResponse::class.java,
                mapOf("symbol" to ticker.symbol, "mic" to ticker.getMic())
            )

            logger.debug("Financial KeyRatios from Morningstar API: ${jacksonObjectMapper().writeValueAsString(response)}")
            return response
        } catch (e: Exception) {
            logger.error(e.message, e)
            null
        }
    }
}