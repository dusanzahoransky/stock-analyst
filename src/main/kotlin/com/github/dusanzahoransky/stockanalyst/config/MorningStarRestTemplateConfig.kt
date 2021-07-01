package com.github.dusanzahoransky.stockanalyst.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.dusanzahoransky.stockanalyst.client.ClientLoggingInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class MorningStarRestTemplateConfig(val mapper: ObjectMapper) {

    @Value("\${morningstar.client.xRapidapiKey}")
    lateinit var xRapidapiKey: String

    @Value("\${morningstar.client.xRapidapiHost}")
    lateinit var xRapidapiHost: String

    @Bean
    fun morningStarRestTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder
                .setReadTimeout(Duration.ofSeconds(30))
                .setConnectTimeout(Duration.ofSeconds(30))
                .additionalInterceptors(
                    ClientLoggingInterceptor(mapper),
                        ClientHttpRequestInterceptor { request, body, execution ->
                            request.headers.set("x-rapidapi-host", xRapidapiHost)
                            request.headers.set("x-rapidapi-key", xRapidapiKey)
                            request.headers.set("Accept", "application/json")
                            execution.execute(request, body)
                        }
                ).build()
    }
}