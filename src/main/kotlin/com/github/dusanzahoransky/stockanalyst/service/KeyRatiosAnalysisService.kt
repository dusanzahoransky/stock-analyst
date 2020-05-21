package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.dto.Rule1Result
import com.github.dusanzahoransky.stockanalyst.model.mongo.KeyRatiosFinancials
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import com.github.dusanzahoransky.stockanalyst.model.yahoo.StocksAveragesCounter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

@Service
class KeyRatiosAnalysisService {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun calcRule1(stocks: List<KeyRatiosFinancials>): Rule1Result {
        return Rule1Result(listOf())
    }
}