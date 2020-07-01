package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.Holders
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface HoldersRepo : MongoRepository<Holders, String>, CachingRepo<Holders> {
    override fun findBySymbolAndExchange(symbol: String, exchange: Exchange): Holders?
}