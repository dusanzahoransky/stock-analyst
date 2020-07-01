package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.Analysis
import com.github.dusanzahoransky.stockanalyst.model.mongo.Chart
import com.github.dusanzahoransky.stockanalyst.model.mongo.EtfStatistics
import com.github.dusanzahoransky.stockanalyst.model.mongo.KeyRatiosFinancials
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ChartRepo : MongoRepository<Chart, String>, CachingRepo<Chart> {
    override fun findBySymbolAndExchange(symbol: String, exchange: Exchange): Chart?
}