package com.github.dzahoransky.stocks.analyst.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.github.dzahoransky.stocks.analyst.model.AnalysisResult
import com.github.dzahoransky.stocks.analyst.model.StockInfo
import com.github.dzahoransky.stocks.analyst.model.yahoo.AveragePeriodMeasure
import com.github.dzahoransky.stocks.analyst.model.yahoo.Statistics
import java.io.File

fun main() {
    val mapper = jacksonObjectMapper()

    //SCRAPE stocks
//    val stocks = mutableListOf<StockInfo>()
//    StockScrapperService().use {
//        stocks.addAll(it.scrape(listOf(
//            StockTicker("MSFT"),
//            StockTicker("AAPL"),
//            StockTicker("AMZN"),
//            StockTicker("GOOG"),
//            StockTicker("GOOGL"),
//            StockTicker("FB"),
//            StockTicker("INTC"),
//            StockTicker("PEP"),
//            StockTicker("CMCSA"),
//            StockTicker("CSCO"),
//            StockTicker("ADBE"),
//            StockTicker("NFLX"),
//            StockTicker("COST"),
//            StockTicker("NVDA"),
//            StockTicker("AMGN"),
//            StockTicker("PYPL"),
//            StockTicker("GILD"),
//            StockTicker("TXN"),
//            StockTicker("CHTR"),
//            StockTicker("AVGO"),
//            StockTicker("TSLA"),
//            StockTicker("QCOM"),
//            StockTicker("SBUX"),
//            StockTicker("TMUS"),
//            StockTicker("MDLZ"),
//            StockTicker("FISV"),
//            StockTicker("INTU"),
//            StockTicker("VRTX"),
//            StockTicker("REGN"),
//            StockTicker("BIIB"),
//            StockTicker("ADP"),
//            StockTicker("BKNG"),
//            StockTicker("ISRG"),
//            StockTicker("AMD"),
//            StockTicker("WBA"),
//            StockTicker("CSX"),
//            StockTicker("ATVI"),
//            StockTicker("MU"),
//            StockTicker("AMAT"),
//            StockTicker("JD"),
//            StockTicker("ADI"),
//            StockTicker("ILMN"),
//            StockTicker("ADSK"),
//            StockTicker("EXC"),
//            StockTicker("XEL"),
//            StockTicker("MNST"),
//            StockTicker("LRCX"),
//            StockTicker("KHC"),
//            StockTicker("EA"),
//            StockTicker("CTSH"),
//            StockTicker("BIDU"),
//            StockTicker("EBAY"),
//            StockTicker("ROST"),
//            StockTicker("MELI"),
//            StockTicker("VRSK"),
//            StockTicker("ORLY"),
//            StockTicker("MAR"),
//            StockTicker("NTES"),
//            StockTicker("NXPI"),
//            StockTicker("SIRI"),
//            StockTicker("WDAY"),
//            StockTicker("PAYX"),
//            StockTicker("CSGP"),
//            StockTicker("KLAC"),
//            StockTicker("WLTW"),
//            StockTicker("PCAR"),
//            StockTicker("VRSN"),
//            StockTicker("LULU"),
//            StockTicker("XLNX"),
//            StockTicker("CTAS"),
//            StockTicker("CERN"),
//            StockTicker("ANSS"),
//            StockTicker("FAST"),
//            StockTicker("ALXN"),
//            StockTicker("SNPS"),
//            StockTicker("SGEN"),
//            StockTicker("DLTR"),
//            StockTicker("IDXX"),
//            StockTicker("CTXS"),
//            StockTicker("CPRT"),
//            StockTicker("SPLK"),
//            StockTicker("ASML"),
//            StockTicker("CDNS"),
//            StockTicker("MCHP"),
//            StockTicker("INCY"),
//            StockTicker("CHKP"),
//            StockTicker("BMRN"),
//            StockTicker("SWKS"),
//            StockTicker("CDW"),
//            StockTicker("MXIM"),
//            StockTicker("TTWO"),
//            StockTicker("TCOM"),
//            StockTicker("ALGN"),
//            StockTicker("WDC"),
//            StockTicker("NTAP"),
//            StockTicker("ULTA"),
//            StockTicker("FOXA"),
//            StockTicker("LBTYK"),
//            StockTicker("EXPE"),
//            StockTicker("FOX"),
//            StockTicker("UAL"),
//            StockTicker("AAL"),
//            StockTicker("LBTYA")
//        )))
//    }
//    val stocksOutFile = File("src/main/resources/stocks.json")
//    println("Saving scraped stocks into: ${stocksOutFile.absolutePath}")
//    mapper.writeValue(stocksOutFile, stocks)

    //LOAD stocks from JSON file
    val stocks = mapper.readValue(mapper.javaClass.getResourceAsStream("/stocks.json"), jacksonTypeRef<List<StockInfo>>())

    // do the calculations
    val statisticsAverage = StockAnalysisService().statisticsAverage(stocks)

    // result for UI to display
    val result = AnalysisResult(statisticsAverage = statisticsAverage, stocks = stocks)

    val resultOutFile = File("src/main/resources/output.json")
    println("Saving analysis output into: ${resultOutFile.absolutePath}")
    mapper.writeValue(resultOutFile, result)
}

class StockAnalysisService {

    fun statisticsAverage(stocks: List<StockInfo>): Statistics {

        val averages = mutableListOf<AveragePeriodMeasure>()

        for (stock in stocks) {
            for (i in stock.statistics.periodValuationMeasures.indices) {
                val measure = stock.statistics.periodValuationMeasures[i]
                if (averages.size <= i) {
                    averages.add(AveragePeriodMeasure(measure.copy()))
                } else {
                    if (measure.marketCap != null) {
                        averages[i].periodMeasure.marketCap = sum(averages[i].periodMeasure.marketCap, measure.marketCap)
                        averages[i].marketCapCount++
                    }
                    if (measure.enterpriseValue != null) {
                        averages[i].periodMeasure.enterpriseValue = sum(averages[i].periodMeasure.enterpriseValue, measure.enterpriseValue)
                        averages[i].enterpriseValueCount++
                    }
                    if (measure.trailingPE != null) {
                        averages[i].periodMeasure.trailingPE = sum(averages[i].periodMeasure.trailingPE, measure.trailingPE)
                        averages[i].trailingPECount++
                    }
                    if (measure.forwardPE != null) {
                        averages[i].periodMeasure.forwardPE = sum(averages[i].periodMeasure.forwardPE, measure.forwardPE)
                        averages[i].forwardPECount++
                    }
                    if (measure.priceEarningGrowth != null) {
                        averages[i].periodMeasure.priceEarningGrowth = sum(averages[i].periodMeasure.priceEarningGrowth, measure.priceEarningGrowth)
                        averages[i].priceEarningGrowthCount++
                    }
                    if (measure.priceSales != null) {
                        averages[i].periodMeasure.priceSales = sum(averages[i].periodMeasure.priceSales, measure.priceSales)
                        averages[i].priceSalesCount++
                    }
                    if (measure.priceBook != null) {
                        averages[i].periodMeasure.priceBook = sum(averages[i].periodMeasure.priceBook, measure.priceBook)
                        averages[i].priceBookCount++
                    }
                    if (measure.enterpriseValueRevenue != null) {
                        averages[i].periodMeasure.enterpriseValueRevenue = sum(averages[i].periodMeasure.enterpriseValueRevenue, measure.enterpriseValueRevenue)
                        averages[i].enterpriseValueRevenueCount++
                    }
                    if (measure.enterpriseValueEBITDA != null) {
                        averages[i].periodMeasure.enterpriseValueEBITDA = sum(averages[i].periodMeasure.enterpriseValueEBITDA, measure.enterpriseValueEBITDA)
                        averages[i].enterpriseValueEBITDACount++
                    }
                }
            }
        }

        for (average in averages) {
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

        return Statistics(averages.map { avg -> avg.periodMeasure })
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