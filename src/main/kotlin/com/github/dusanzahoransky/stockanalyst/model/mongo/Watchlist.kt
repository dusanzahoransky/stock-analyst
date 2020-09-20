package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.github.dusanzahoransky.stockanalyst.model.enums.WatchlistTag
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Raw data cached from MorningStar API
 */
@Document
data class Watchlist(
        @Id var name: String,
        var isEtf: Boolean = false,
        var isGroup: Boolean = false,
        var tags: Set<WatchlistTag> = setOf(),
        var tickers: MutableSet<String> = mutableSetOf()
)