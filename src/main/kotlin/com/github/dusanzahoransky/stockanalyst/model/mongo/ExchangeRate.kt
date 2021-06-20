package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.WatchlistTag
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

/**
 * Cache data from exchange rates API
 */
@Document
data class ExchangeRate(
        val lastUpdated: LocalDateTime = LocalDateTime.now(),
        val base: Currency = Currency.USD,
        @Id val target: Currency,
        val rate: Double
)