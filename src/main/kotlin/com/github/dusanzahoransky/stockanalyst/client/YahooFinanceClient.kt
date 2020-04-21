package com.github.dusanzahoransky.stockanalyst.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.yahoo.performance.BalanceSheetResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.statistics.StatisticsResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class YahooFinanceClient @Autowired constructor(
    @Qualifier("yahooFinanceRestTemplate") val restTemplate: RestTemplate
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    companion object {
        var lastCallTime: Long = 0
    }

    fun getStatistics(ticker: StockTicker): StatisticsResponse? {
        val response = restTemplate.getForObject(
            "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-statistics?symbol={ticker}",
            StatisticsResponse::class.java,
            mapOf("region" to "US", "ticker" to ticker.toYahooFormat())
        )

        logger.debug("Statistics from Yahoo API: ${jacksonObjectMapper().writeValueAsString(response)}")
        return response

//        val statisticsMock = ClassPathResource("StatisticsMockWIZZ.json")
//        return jacksonObjectMapper().readValue(statisticsMock.inputStream, jacksonTypeRef<StatisticsResponse>())
    }


    fun getBalanceSheet(ticker: StockTicker): BalanceSheetResponse? {
//        val response = restTemplate.getForObject(
//            "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-balance-sheet?symbol={ticker}",
//            BalanceSheetResponse::class.java,
//            mapOf("region" to "US", "ticker" to ticker.toYahooFormat())
//        )
//
//        logger.debug("BalanceSheet from Yahoo API: ${jacksonObjectMapper().writeValueAsString(response)}")
//        return response

        val balanceSheetMock = ClassPathResource("BalanceSheetMockGOOGL.json")
        return jacksonObjectMapper().readValue(balanceSheetMock.inputStream, jacksonTypeRef<BalanceSheetResponse>())
    }

}