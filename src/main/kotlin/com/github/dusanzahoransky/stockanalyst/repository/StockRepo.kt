package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepo : MongoRepository<StockInfo, String> {
    fun findBySymbolAndExchange(symbol: String, exchange: Exchange): StockInfo?
    fun findBySymbol(symbol: String): List<StockInfo>
}