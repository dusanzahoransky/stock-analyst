package com.github.dusanzahoransky.stockanalyst.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.enums.Interval
import com.github.dusanzahoransky.stockanalyst.model.enums.Range
import com.github.dusanzahoransky.stockanalyst.model.yahoo.balancesheet.BalanceSheetResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.chart.ChartResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.financials.FinancialsResponse
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

    fun getStatistics(ticker: StockTicker, mockData: Boolean): StatisticsResponse? {
        if (mockData) {
            val statisticsMock = ClassPathResource("StatisticsMockGOOGL.json")
            return jacksonObjectMapper().readValue(statisticsMock.inputStream, jacksonTypeRef<StatisticsResponse>())
        }
        val response = restTemplate.getForObject(
            "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-statistics?symbol={ticker}",
            StatisticsResponse::class.java,
            mapOf("region" to "US", "ticker" to ticker.toYahooFormat())
        )

        logger.debug("Statistics from Yahoo API: ${jacksonObjectMapper().writeValueAsString(response)}")
        return response
    }


    fun getBalanceSheet(ticker: StockTicker, mockData: Boolean): BalanceSheetResponse? {
        if (mockData) {
            val balanceSheetMock = ClassPathResource("BalanceSheetMockGOOGL.json")
            return jacksonObjectMapper().readValue(balanceSheetMock.inputStream, jacksonTypeRef<BalanceSheetResponse>())
        }
        val response = restTemplate.getForObject(
            "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-balance-sheet?symbol={ticker}",
            BalanceSheetResponse::class.java,
            mapOf("region" to "US", "ticker" to ticker.toYahooFormat())
        )

        logger.debug("BalanceSheet from Yahoo API: ${jacksonObjectMapper().writeValueAsString(response)}")
        return response
    }

    fun getFinancials(ticker: StockTicker, mockData: Boolean): FinancialsResponse? {
        if (mockData) {
            val balanceSheetMock = ClassPathResource("FinancialsMockGOOGL.json")
            return jacksonObjectMapper().readValue(balanceSheetMock.inputStream, jacksonTypeRef<FinancialsResponse>())
        }
        val response = restTemplate.getForObject(
            "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-financials?symbol={ticker}",
            FinancialsResponse::class.java,
            mapOf("region" to "US", "ticker" to ticker.toYahooFormat())
        )

        logger.debug("Financials from Yahoo API: ${jacksonObjectMapper().writeValueAsString(response)}")
        return response
    }

    fun getChart(ticker: StockTicker, interval: Interval, range: Range, mockData: Boolean): ChartResponse? {
        if (mockData) {
            val balanceSheetMock = ClassPathResource("ChartMockCBA.json")
            return jacksonObjectMapper().readValue(balanceSheetMock.inputStream, jacksonTypeRef<ChartResponse>())
        }
        val response = restTemplate.getForObject(
            "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-chart?symbol={ticker}&interval={interval}&range={range}",
            ChartResponse::class.java,
            mapOf(
                "region" to "US",
                "ticker" to ticker.toYahooFormat(),
                "interval" to interval.value,
                "range" to range.value
            )
        )

        logger.debug("Chart from Yahoo API: ${jacksonObjectMapper().writeValueAsString(response)}")
        return response
    }

}