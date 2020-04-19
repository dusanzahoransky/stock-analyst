package com.github.dusanzahoransky.stockanalyst.client

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.web.client.RestTemplate
import java.time.Duration


@Configuration
class YahooFinanceRestTemplate {

    @Value("\${yahoo.client.xRapidapiKey}")
    lateinit var xRapidapiKey: String
    @Value("\${yahoo.client.xRapidapiHost}")
    lateinit var xRapidapiHost: String

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        //avoid hitting rate limit 2 calls a second
        if (System.currentTimeMillis() - YahooFinanceClient.lastCallTime < 500) {
            Thread.sleep(500)
        }
        YahooFinanceClient.lastCallTime = System.currentTimeMillis()

        return builder
            .setReadTimeout(Duration.ofSeconds(30))
            .setConnectTimeout(Duration.ofSeconds(30))
            .additionalInterceptors(
                ClientHttpRequestInterceptor { request, body, execution ->
                    logger.debug("Calling: ${request.method} ${request.uri} $body")
                    val response = execution.execute(request, body)
                    logger.debug("Response: ${response.statusCode}")
                    response
                },
                ClientHttpRequestInterceptor { request, body, execution ->
                    request.headers.set("x-rapidapi-host", xRapidapiHost)
                    request.headers.set("x-rapidapi-key", xRapidapiKey)
                    request.headers.set("Accept", "application/json")
                    execution.execute(request, body)
                }
            ).build()
    }
}