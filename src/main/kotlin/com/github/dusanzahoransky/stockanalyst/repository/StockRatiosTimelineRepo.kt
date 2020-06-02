package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Mic
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatiosTimeline
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRatiosTimelineRepo: MongoRepository<StockRatiosTimeline, String> {
    fun findBySymbolAndMic(symbol: String, mic: Mic): StockRatiosTimeline?
}