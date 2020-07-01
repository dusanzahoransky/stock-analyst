package com.github.dusanzahoransky.stockanalyst.client

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration


@Configuration
class ExchangeRatesRestTemplateConfig {

    @Bean
    fun exchangeRatesRestTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder
            .setReadTimeout(Duration.ofSeconds(30))
            .setConnectTimeout(Duration.ofSeconds(30))
            .additionalInterceptors(ClientLoggingInterceptor()).build()
    }
}