package com.github.dusanzahoransky.stockanalyst.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.exchangerates.RatesResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ExchangeRateClient @Autowired constructor(
    @Qualifier("exchangeRatesRestTemplate") val restTemplate: RestTemplate
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getRate(fromCurrency: Currency, toCurrency: Currency): Double {

        //TODO use a different API to support more currencies
        if (fromCurrency == Currency.TWD || toCurrency == Currency.TWD) {
            return 1.0
        }

        val base = if (fromCurrency == Currency.GBp) Currency.GBP else fromCurrency
        val symbols = if (toCurrency == Currency.GBp) Currency.GBP else toCurrency

        val response = restTemplate.getForObject(
            "https://api.exchangeratesapi.io/latest?base={base}&symbols={symbols}",
            RatesResponse::class.java,
            mapOf("base" to base, "symbols" to symbols)
        )

        logger.debug("Response: ${jacksonObjectMapper().writeValueAsString(response)}")

        var rate = response?.rates?.values?.get(symbols.name)
            ?: throw IllegalArgumentException("Invalid rate conversion $fromCurrency -> $toCurrency")

        if (fromCurrency == Currency.GBp) {
            rate /= 100.0
        }
        if (toCurrency == Currency.GBp) {
            rate *= 100.0
        }

        return rate
    }

}