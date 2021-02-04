package com.github.dusanzahoransky.stockanalyst.model

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange

data class Ticker(
    val symbol: String,
    val exchange: Exchange
) {
    companion object {
        /**
         * "CBA:ASX"
         */
        fun fromString(ticker: String): Ticker {
            val ticketParts = ticker.split(":")
            return Ticker(ticketParts[0], Exchange.valueOf(ticketParts[1]))
        }
    }

    fun toYahooFormat(): String {
        return when (exchange) {
            Exchange.NYSE, Exchange.NASDAQ -> symbol
            else -> "$symbol.${exchange.yahooFormat}"
        }
    }

    fun getMic(): String {
        return when (exchange) {
            Exchange.ASX -> "XASX"
            Exchange.NASDAQ -> "XNAS"
            Exchange.NYSE -> "XNYS"
            Exchange.FTSE -> "XLON"
            Exchange.DAX -> "XETR"
            Exchange.ENX -> "XMEX"
            Exchange.SIX -> "XSWX"
            Exchange.PA -> "XPAR"
            Exchange.MCE -> "XMAD"
            Exchange.PNK -> "PINX"
            Exchange.T -> "XTKS"
        }
    }
}