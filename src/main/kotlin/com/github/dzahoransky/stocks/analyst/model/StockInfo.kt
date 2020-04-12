package com.github.dzahoransky.stocks.analyst.model

import com.github.dzahoransky.stocks.analyst.model.yahoo.Statistics
import java.time.ZonedDateTime

data class StockInfo(
    var id: String? = null,
    var timestamp: ZonedDateTime,
    val symbol: String,
    val exchange: Exchange?,
    var companyName: String?,
    var statistics: Statistics
)