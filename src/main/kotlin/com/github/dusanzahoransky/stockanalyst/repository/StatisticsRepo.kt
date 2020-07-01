package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.*
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StatisticsRepo : MongoRepository<Statistics, String>, CachingRepo<Statistics> {
    override fun findBySymbolAndExchange(symbol: String, exchange: Exchange): Statistics?
}