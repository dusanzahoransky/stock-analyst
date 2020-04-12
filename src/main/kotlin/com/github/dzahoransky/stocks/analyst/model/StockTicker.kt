package com.github.dzahoransky.stocks.analyst.model

data class StockTicker(
    val symbol: String,
    val exchange: Exchange
) {
    companion object {
        fun fromString(ticker: String): StockTicker {
            val ticketParts = ticker.split(":")
            return StockTicker(ticketParts[0], Exchange.valueOf(ticketParts[1]))
        }
    }
}