package com.github.dusanzahoransky.stockanalyst.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.io.File
import java.time.LocalDate

@Repository
interface StockRepo:MongoRepository<StockInfo, String> {
    fun findBySymbolAndExchange(symbol: String, exchange: Exchange): StockInfo?
    fun findBySymbol(symbol: String): List<StockInfo>
}