package com.github.dzahoransky.stocks.analyst.model

import com.github.dzahoransky.stocks.analyst.model.yahoo.Statistics

data class StockInfo(
    var companyName: String?,
    var ticker: StockTicker,
    var statistics: Statistics
)