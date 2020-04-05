package com.github.dzahoransky.stocks.analyst.model

import com.github.dzahoransky.stocks.analyst.model.yahoo.Statistics

data class StockInfo (
    val ticker: StockTicker,
    val statistics: Statistics
)