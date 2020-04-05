package com.github.dzahoransky.stocks.analyst.model

data class StockTicker(
    val symbol: String,
    val exchange: String = "NASDAQ"
)