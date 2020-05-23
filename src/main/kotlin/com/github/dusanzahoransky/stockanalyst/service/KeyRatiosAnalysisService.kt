package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.dto.Rule1Result
import com.github.dusanzahoransky.stockanalyst.model.mongo.KeyRatiosFinancials
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatios
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class KeyRatiosAnalysisService {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun calcRule1(krsList: List<StockRatios>): Rule1Result {
        for(krl in krsList){

        }
        return Rule1Result(krsList)
    }
}