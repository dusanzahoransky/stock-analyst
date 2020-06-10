package com.github.dusanzahoransky.stockanalyst.client

import org.slf4j.LoggerFactory
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse

class ClientLoggingInterceptor : ClientHttpRequestInterceptor {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        logger.debug("Calling: ${request.method} ${request.uri} $body")
        val response = execution.execute(request, body)
        val remainingQuota = response.headers.getOrEmpty("X-RateLimit-requests-Remaining")
        logger.debug("Response [remaining quota: $remainingQuota]: ${response.statusCode}")
        return response
    }
}