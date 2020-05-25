package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatiosTimeline
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class KeyRatiosAnalysisService {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun calcRule1(krsList: List<StockRatiosTimeline>): List<StockRatiosTimeline> {
        for (krl in krsList) {
            //TODO
        }
        return krsList
    }
}