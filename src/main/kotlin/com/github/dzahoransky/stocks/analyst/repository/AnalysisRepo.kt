package com.github.dzahoransky.stocks.analyst.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.dzahoransky.stocks.analyst.model.AnalysisResult
import org.slf4j.LoggerFactory
import java.io.File
import java.time.LocalDate

class AnalysisRepo(private val mapper: ObjectMapper) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun store(analysisResult: AnalysisResult, stockListLabel: String): String {
        val outputFileName = "Result-$stockListLabel-${LocalDate.now()}"
        val outFile = File("src/main/resources/$outputFileName.json")

        logger.debug("Saving analysis result to: ${outFile.absolutePath}")
        mapper.writeValue(outFile, analysisResult)
        return outFile.absolutePath
    }
}