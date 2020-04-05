package com.github.dzahoransky.stocks.analyst.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.dzahoransky.stocks.analyst.model.StockTicker

fun main() {
    StockScrapperService().use {
        val stocks = it.scrape(listOf(StockTicker("MSFT"), StockTicker("GOOGL")))
        val jsonOut = ObjectMapper().registerModule(KotlinModule()).writeValueAsString(stocks)
        println(jsonOut)
    }
}

class StockAnalysisService {
}