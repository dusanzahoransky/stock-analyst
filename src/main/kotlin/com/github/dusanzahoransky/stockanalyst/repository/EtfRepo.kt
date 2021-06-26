package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.Etf
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EtfRepo : MongoRepository<Etf, String> {
    fun findBySymbolAndExchange(symbol: String, exchange: Exchange): Etf?
}