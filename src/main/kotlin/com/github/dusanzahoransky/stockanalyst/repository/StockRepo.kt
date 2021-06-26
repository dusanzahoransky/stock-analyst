package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.Stock
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepo : MongoRepository<Stock, String> {
    fun findBySymbolAndExchange(symbol: String, exchange: Exchange): Stock?
}