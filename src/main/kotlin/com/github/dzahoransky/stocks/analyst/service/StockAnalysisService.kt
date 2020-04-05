package com.github.dzahoransky.stocks.analyst.service

import TickerRepo
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import com.github.dzahoransky.stocks.analyst.model.AnalysisResult
import com.github.dzahoransky.stocks.analyst.model.StockInfo
import com.github.dzahoransky.stocks.analyst.model.yahoo.AveragePeriodMeasure
import com.github.dzahoransky.stocks.analyst.model.yahoo.Statistics
import java.io.File

fun main() {
    val mapper = jacksonObjectMapper().registerModule(ParameterNamesModule())
        .registerModule(Jdk8Module())
        .registerModule(JavaTimeModule())

    //SCRAPE stocks
    val tickerRepo = TickerRepo()
    val stocks = mutableListOf<StockInfo>()
    StockScrapperService().use {
        stocks.addAll(it.scrape(
//            tickerRepo.test()
            tickerRepo.nasdaq100()
        ))
    }
    val stocksOutFile = File("src/main/resources/stocks.json")
    println("Saving scraped stocks into: ${stocksOutFile.absolutePath}")
    mapper.writeValue(stocksOutFile, stocks)

    //LOAD stocks from JSON file

















//    val stocks = mapper.readValue(File("src/main/resources/stocks.json"), jacksonTypeRef<List<StockInfo>>())

    // do the calculations
    val statisticsAverage = StockAnalysisService().statisticsAverage(stocks)
    val allPeriods = StockAnalysisService().allPeriods(statisticsAverage)

    // result for UI to display
    val result = AnalysisResult(allPeriods, statisticsAverage, stocks)

    val resultOutFile = File("src/main/resources/output.json")
    println("Saving analysis output into: ${resultOutFile.absolutePath}")
    mapper.writeValue(resultOutFile, result)
}

class StockAnalysisService {

    fun allPeriods(statisticsAverage: Statistics): List<String> {
        return statisticsAverage.periodValuationMeasures.keys.toList().sorted().reversed()
    }

    fun statisticsAverage(stocks: List<StockInfo>): Statistics {

        val averages = mutableMapOf<String, AveragePeriodMeasure>()

        for (stock in stocks) {
            for (measure in stock.statistics.periodValuationMeasures.values) {
                val averageForPeriod = averages.get(measure.period)
                if (averageForPeriod == null) {
                    averages.put(measure.period, AveragePeriodMeasure(measure.copy()))
                } else {
                    if (measure.marketCap != null) {
                        averageForPeriod.periodMeasure.marketCap = sum(averageForPeriod.periodMeasure.marketCap, measure.marketCap)
                        averageForPeriod.marketCapCount++
                    }
                    if (measure.enterpriseValue != null) {
                        averageForPeriod.periodMeasure.enterpriseValue = sum(averageForPeriod.periodMeasure.enterpriseValue, measure.enterpriseValue)
                        averageForPeriod.enterpriseValueCount++
                    }
                    if (measure.trailingPE != null) {
                        averageForPeriod.periodMeasure.trailingPE = sum(averageForPeriod.periodMeasure.trailingPE, measure.trailingPE)
                        averageForPeriod.trailingPECount++
                    }
                    if (measure.forwardPE != null) {
                        averageForPeriod.periodMeasure.forwardPE = sum(averageForPeriod.periodMeasure.forwardPE, measure.forwardPE)
                        averageForPeriod.forwardPECount++
                    }
                    if (measure.priceEarningGrowth != null) {
                        averageForPeriod.periodMeasure.priceEarningGrowth = sum(averageForPeriod.periodMeasure.priceEarningGrowth, measure.priceEarningGrowth)
                        averageForPeriod.priceEarningGrowthCount++
                    }
                    if (measure.priceSales != null) {
                        averageForPeriod.periodMeasure.priceSales = sum(averageForPeriod.periodMeasure.priceSales, measure.priceSales)
                        averageForPeriod.priceSalesCount++
                    }
                    if (measure.priceBook != null) {
                        averageForPeriod.periodMeasure.priceBook = sum(averageForPeriod.periodMeasure.priceBook, measure.priceBook)
                        averageForPeriod.priceBookCount++
                    }
                    if (measure.enterpriseValueRevenue != null) {
                        averageForPeriod.periodMeasure.enterpriseValueRevenue = sum(averageForPeriod.periodMeasure.enterpriseValueRevenue, measure.enterpriseValueRevenue)
                        averageForPeriod.enterpriseValueRevenueCount++
                    }
                    if (measure.enterpriseValueEBITDA != null) {
                        averageForPeriod.periodMeasure.enterpriseValueEBITDA = sum(averageForPeriod.periodMeasure.enterpriseValueEBITDA, measure.enterpriseValueEBITDA)
                        averageForPeriod.enterpriseValueEBITDACount++
                    }
                }
            }
        }

        for (average in averages.values) {
            average.periodMeasure.marketCap = div(average.periodMeasure.marketCap, stocks.size)
            average.periodMeasure.enterpriseValue = div(average.periodMeasure.enterpriseValue, stocks.size)
            average.periodMeasure.trailingPE = div(average.periodMeasure.trailingPE, stocks.size)
            average.periodMeasure.forwardPE = div(average.periodMeasure.forwardPE, stocks.size)
            average.periodMeasure.priceEarningGrowth = div(average.periodMeasure.priceEarningGrowth, stocks.size)
            average.periodMeasure.priceSales = div(average.periodMeasure.priceSales, stocks.size)
            average.periodMeasure.priceBook = div(average.periodMeasure.priceBook, stocks.size)
            average.periodMeasure.enterpriseValueRevenue = div(average.periodMeasure.enterpriseValueRevenue, stocks.size)
            average.periodMeasure.enterpriseValueEBITDA = div(average.periodMeasure.enterpriseValueEBITDA, stocks.size)
        }


        return Statistics(averages.entries.map { it.key to it.value.periodMeasure }.toMap())    //unwrap AveragePeriodMeasure back to PeriodMeasure
    }

    /**
     * Null-safe sum of 2 nullable Longs
     */
    private fun sum(value1: Long?, value2: Long?): Long? {
        return if (value1 == null) value2 else if (value2 == null) null else value1 + value2
    }

    /**
     * Null-safe sum of 2 nullable Floats
     */
    private fun sum(value1: Double?, value2: Double?): Double? {
        return if (value1 == null) value2 else if (value2 == null) null else value1 + value2
    }

    /**
     * Null-safe division of a nullable Long
     */
    private fun div(value1: Long?, size: Int): Long? {
        return if (value1 == null) value1 else value1 / size
    }

    /**
     * Null-safe division of a nullable Double
     */
    private fun div(value1: Double?, size: Int): Double? {
        return if (value1 == null) value1 else value1 / size
    }
}