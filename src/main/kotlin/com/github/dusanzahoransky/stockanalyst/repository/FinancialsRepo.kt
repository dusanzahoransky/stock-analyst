package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.Financials
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface FinancialsRepo : MongoRepository<Financials, String>, CachingRepo<Financials> {
    override fun findBySymbolAndExchange(symbol: String, exchange: Exchange): Financials?
}