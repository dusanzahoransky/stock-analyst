package com.github.dzahoransky.stocks.analyst.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.github.dzahoransky.stocks.analyst.model.StockInfo
import com.github.dzahoransky.stocks.analyst.service.StockScrapperService
import org.slf4j.LoggerFactory
import java.io.File
import java.time.LocalDate

class StockRepo(private val mapper: ObjectMapper) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun store(stocks: MutableList<StockInfo>, stockListLabel: String): String {
        val outputFileName = "Stocks-$stockListLabel-${LocalDate.now()}"
        val stocksOutFile = File("src/main/resources/$outputFileName.json")

        logger.debug("Saving stocks to: ${stocksOutFile.absolutePath}")
        mapper.writeValue(stocksOutFile, stocks)
        return stocksOutFile.absolutePath
    }

    fun findById(filePath: String): List<StockInfo> {
        return mapper.readValue(File(filePath), jacksonTypeRef<List<StockInfo>>())
    }
}