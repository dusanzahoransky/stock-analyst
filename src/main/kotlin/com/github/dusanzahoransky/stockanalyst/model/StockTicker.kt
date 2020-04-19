package com.github.dusanzahoransky.stockanalyst.model

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange

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