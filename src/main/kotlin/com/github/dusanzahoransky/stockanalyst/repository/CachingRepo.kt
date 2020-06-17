package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange

interface CachingRepo<T> {
    fun findBySymbolAndExchange(symbol: String, exchange: Exchange): T?
}