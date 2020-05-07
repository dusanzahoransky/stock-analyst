package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.IndexInfo
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface IndexRepo : MongoRepository<IndexInfo, String> {
    fun findBySymbolAndExchange(symbol: String, exchange: Exchange): IndexInfo?
    fun findBySymbol(symbol: String): List<IndexInfo>
}