package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.Analysis
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AnalysisRepo : MongoRepository<Analysis, String>, CachingRepo<Analysis> {
    override fun findBySymbolAndExchange(symbol: String, exchange: Exchange): Analysis?
}