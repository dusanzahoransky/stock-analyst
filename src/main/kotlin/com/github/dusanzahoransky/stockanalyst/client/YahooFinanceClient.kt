package com.github.dusanzahoransky.stockanalyst.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.yahoo.statistics.StatisticsResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class YahooFinanceClient @Autowired constructor(
    val restTemplate: RestTemplate
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    companion object {
        var lastCallTime: Long = 0
    }

    fun getStatistics(ticker: StockTicker): StatisticsResponse? {
        val response = restTemplate.getForObject(
            "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-statistics?region={region}&symbol={ticker}",
            StatisticsResponse::class.java,
            mapOf("region" to ticker.exchange.toRegion(), "ticker" to ticker.symbol)
        )

        logger.debug("Retrieved from Yahoo API: ${jacksonObjectMapper().writeValueAsString(response)}")
        return response

//        val statisticsMock = ClassPathResource("StatisticsMock3.json")
//        return jacksonObjectMapper().readValue(statisticsMock.inputStream, jacksonTypeRef<StatisticsResponse>())
    }

}