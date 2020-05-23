package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Mic
import com.github.dusanzahoransky.stockanalyst.model.mongo.KeyRatiosFinancials
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatios
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRatiosRepo: MongoRepository<StockRatios, String> {
    fun findBySymbolAndMic(symbol: String, mic: Mic): KeyRatiosFinancials?
}