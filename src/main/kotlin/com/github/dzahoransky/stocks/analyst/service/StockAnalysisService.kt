package com.github.dzahoransky.stocks.analyst.service

import TickerRepo
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import com.github.dzahoransky.stocks.analyst.model.AnalysisResult
import com.github.dzahoransky.stocks.analyst.model.StockInfo
import com.github.dzahoransky.stocks.analyst.model.yahoo.AveragePeriodMeasure
import com.github.dzahoransky.stocks.analyst.model.yahoo.AverageStatistics
import com.github.dzahoransky.stocks.analyst.model.yahoo.PeriodMeasure
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

        val averageStatistics = AverageStatistics(Statistics("AVERAGES"))

        for (stock in stocks) {
            for (measure in stock.statistics.periodValuationMeasures.values) {
                val averageForPeriod = averageStatistics.periodValuationMeasures.get(measure.period)
                if (averageForPeriod == null) {
                    averageStatistics.periodValuationMeasures.put(measure.period, AveragePeriodMeasure(measure.copy()))
                } else {
                    addToPeriodAverage(averageForPeriod, measure)
                }
            }

            if(stock.statistics.price != null){
                averageStatistics.statistics.price = sum(averageStatistics.statistics.price, stock.statistics.price)
                averageStatistics.priceCount++
            }
            if(stock.statistics.totalCashPerShare != null){
                averageStatistics.statistics.totalCashPerShare = sum(averageStatistics.statistics.totalCashPerShare, stock.statistics.totalCashPerShare)
                averageStatistics.totalCashPerShareCount++
            }
            if(stock.statistics.totalDebtEquity != null){
                averageStatistics.statistics.totalDebtEquity = sum(averageStatistics.statistics.totalDebtEquity, stock.statistics.totalDebtEquity)
                averageStatistics.totalDebtEquityCount++
            }
            if(stock.statistics.quarterlyRevenueGrowth != null){
                averageStatistics.statistics.quarterlyRevenueGrowth = sum(averageStatistics.statistics.quarterlyRevenueGrowth, stock.statistics.quarterlyRevenueGrowth)
                averageStatistics.quarterlyRevenueGrowthCount++
            }
            if(stock.statistics.quarterlyEarningsGrowth != null){
                averageStatistics.statistics.quarterlyEarningsGrowth = sum(averageStatistics.statistics.quarterlyEarningsGrowth, stock.statistics.quarterlyEarningsGrowth)
                averageStatistics.quarterlyEarningsGrowthCount++
            }
            if(stock.statistics.dilutedEarningPerShare != null){
                averageStatistics.statistics.dilutedEarningPerShare = sum(averageStatistics.statistics.dilutedEarningPerShare, stock.statistics.dilutedEarningPerShare)
                averageStatistics.dilutedEarningPerShareCount++
            }
            if(stock.statistics.week52Change != null){
                averageStatistics.statistics.week52Change = sum(averageStatistics.statistics.week52Change, stock.statistics.week52Change)
                averageStatistics.week52ChangeCount++
            }
            if(stock.statistics.week52Low != null){
                averageStatistics.statistics.week52Low = sum(averageStatistics.statistics.week52Low, stock.statistics.week52Low)
                averageStatistics.week52LowCount++
            }
            if(stock.statistics.week52High != null){
                averageStatistics.statistics.week52High = sum(averageStatistics.statistics.week52High, stock.statistics.week52High)
                averageStatistics.week52HighCount++
            }
            if(stock.statistics.dilutedEarningPerShare != null){
                averageStatistics.statistics.dilutedEarningPerShare = sum(averageStatistics.statistics.dilutedEarningPerShare, stock.statistics.dilutedEarningPerShare)
                averageStatistics.dilutedEarningPerShareCount++
            }
        }

        averageStatistics.statistics.price = div(averageStatistics.statistics.price, averageStatistics.priceCount)
        averageStatistics.statistics.totalCashPerShare = div(averageStatistics.statistics.totalCashPerShare, averageStatistics.totalCashPerShareCount)
        averageStatistics.statistics.totalDebtEquity = div(averageStatistics.statistics.totalDebtEquity, averageStatistics.totalDebtEquityCount)
        averageStatistics.statistics.quarterlyRevenueGrowth = div(averageStatistics.statistics.quarterlyRevenueGrowth, averageStatistics.quarterlyRevenueGrowthCount)
        averageStatistics.statistics.quarterlyEarningsGrowth = div(averageStatistics.statistics.quarterlyEarningsGrowth, averageStatistics.quarterlyEarningsGrowthCount)
        averageStatistics.statistics.dilutedEarningPerShare = div(averageStatistics.statistics.dilutedEarningPerShare, averageStatistics.dilutedEarningPerShareCount)
        averageStatistics.statistics.week52Change = div(averageStatistics.statistics.week52Change, averageStatistics.week52ChangeCount)
        averageStatistics.statistics.week52Low = div(averageStatistics.statistics.week52Low, averageStatistics.week52LowCount)
        averageStatistics.statistics.week52High = div(averageStatistics.statistics.week52High, averageStatistics.week52HighCount)

        for (average in averageStatistics.periodValuationMeasures.values) {
            average.periodMeasure.marketCap = div(average.periodMeasure.marketCap, average.marketCapCount)
            average.periodMeasure.enterpriseValue = div(average.periodMeasure.enterpriseValue, average.enterpriseValueCount)
            average.periodMeasure.trailingPE = div(average.periodMeasure.trailingPE, average.trailingPECount)
            average.periodMeasure.forwardPE = div(average.periodMeasure.forwardPE, average.forwardPECount)
            average.periodMeasure.priceEarningGrowth = div(average.periodMeasure.priceEarningGrowth, average.priceEarningGrowthCount)
            average.periodMeasure.priceSales = div(average.periodMeasure.priceSales, average.priceSalesCount)
            average.periodMeasure.priceBook = div(average.periodMeasure.priceBook, average.priceBookCount)
            average.periodMeasure.enterpriseValueRevenue = div(average.periodMeasure.enterpriseValueRevenue, average.enterpriseValueRevenueCount)
            average.periodMeasure.enterpriseValueEBITDA = div(average.periodMeasure.enterpriseValueEBITDA, average.enterpriseValueEBITDACount)
        }

        averageStatistics.statistics.periodValuationMeasures = averageStatistics.periodValuationMeasures.entries
            .map { it.key to it.value.periodMeasure }.toMap() as MutableMap<String, PeriodMeasure> //unwrap AveragePeriodMeasure back to PeriodMeasure

        return averageStatistics.statistics
    }

    private fun addToPeriodAverage(averageForPeriod: AveragePeriodMeasure, measure: PeriodMeasure) {
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