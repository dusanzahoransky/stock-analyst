package com.github.dusanzahoransky.stockanalyst.model

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.enums.Mic

data class StockTicker(
    val symbol: String,
    val exchange: Exchange
) {
    companion object {
        /**
         * "CBA:ASX"
         */
        fun fromString(ticker: String): StockTicker {
            val ticketParts = ticker.split(":")
            return StockTicker(ticketParts[0], Exchange.valueOf(ticketParts[1]))
        }
        fun fromSymbolAndMic(symbol: String, mic: Mic): StockTicker{
            return StockTicker(symbol, Exchange.valueOf(mic.name))
        }
    }

    fun toYahooFormat(): String {
        return when (exchange) {
            Exchange.NYSE, Exchange.NASDAQ -> symbol
            else -> "$symbol.${exchange.yahooFormat}"
        }
    }

    fun getMic(): Mic {
        return when (exchange) {
            Exchange.ASX -> Mic.ASX
            Exchange.NASDAQ -> Mic.NASDAQ
            Exchange.NYSE -> Mic.NYSE
            Exchange.FTSE -> Mic.FTSE
            Exchange.DAX -> Mic.DAX
            Exchange.ENX -> Mic.ENX
            Exchange.SIX -> Mic.SIX
            Exchange.PA -> Mic.PA
            Exchange.MCE -> Mic.MCE
        }
    }
}