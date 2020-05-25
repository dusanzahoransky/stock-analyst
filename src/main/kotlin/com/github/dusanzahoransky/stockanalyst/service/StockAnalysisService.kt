package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.dto.StockInfoWithRatios
import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.IndexInfo
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockChartData
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatiosTimeline
import com.github.dusanzahoransky.stockanalyst.model.yahoo.IndicesAveragesCounter
import com.github.dusanzahoransky.stockanalyst.model.yahoo.StocksAveragesCounter
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percentGrowth
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.sum
import com.github.dusanzahoransky.stockanalyst.util.FormattingUtils.Companion.epochSecToLocalDate
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

@Service
class StockAnalysisService {

    val log = LoggerFactory.getLogger(this::class.java)!!

    /**
     * Enhance the stock data with Growth and other ratios that were not provided by Yahoo finance API. It's good to keep this logic separated so the calculation can be changed without a need to fetch data from Yahoo API again.
     */
    fun calcStockStats(stocks: List<StockInfo>): List<StockInfo> {
        for (stock in stocks) {
            calcFinancialStats(stock)
            calcChartData(stock)
        }
        return stocks
    }

    private fun calcFinancialStats(stock: StockInfo) {
        if (stock.price != null && stock.week52Low != null) {
            stock.week52AboveLowPercent = ((stock.price!! - stock.week52Low!!) / stock.price!!) * 100.0
        }
        if (stock.price != null && stock.week52High != null) {
            stock.week52BelowHighPercent = ((stock.week52High!! - stock.price!!) / stock.price!!) * 100.0
        }
        if (stock.targetLowPrice != null && stock.price != null) {
            stock.belowTargetLowPricePercent = ((stock.targetLowPrice!! - stock.price!!) / stock.price!!) * 100.0
        }
        if (stock.targetMedianPrice != null && stock.price != null) {
            stock.belowTargetMedianPricePercent = ((stock.targetMedianPrice!! - stock.price!!) / stock.price!!) * 100.0
        }

        if (stock.totalCashPerShare != null && stock.price != null) {
            stock.totalCashPerSharePercent = (stock.totalCashPerShare!! / stock.price!!) * 100.0
        }
        if (stock.totalLiabilitiesLastQuarter != null && stock.totalShareholdersEquityLastQuarter != null) {
            stock.totalDebtEquity = div(stock.totalLiabilitiesLastQuarter!!.toDouble(), stock.totalShareholdersEquityLastQuarter!!.toDouble())
        }

        stock.netIncomeGrowthLastQuarter = percentGrowth(stock.netIncomeLastQuarter, stock.netIncome2QuartersAgo, "netIncomeGrowthLastQuarter")
        stock.netIncomeGrowthLast2Quarters = percentGrowth(stock.netIncomeLastQuarter, stock.netIncome3QuartersAgo, "netIncomeGrowthLast2Quarters")
        stock.netIncomeGrowthLast3Years = percentGrowth(stock.netIncomeLastYear, stock.netIncome3YearsAgo, "netIncomeGrowthLast3Years")

        stock.grossIncomeGrowthLastQuarter = percentGrowth(stock.grossIncomeLastQuarter, stock.grossIncome2QuartersAgo, "grossIncomeGrowthLastQuarter")
        stock.grossIncomeGrowthLast2Quarters = percentGrowth(stock.grossIncomeLastQuarter, stock.grossIncome3QuartersAgo, "grossIncomeGrowthLast2Quarters")
        stock.grossIncomeGrowthLast3Years = percentGrowth(stock.grossIncomeLastYear, stock.grossIncome3YearsAgo, "grossIncomeGrowthLast3Years")

        stock.revenueGrowthLastQuarter = percentGrowth(stock.revenueLastQuarter, stock.revenue2QuartersAgo, "revenueGrowthLastQuarter")
        stock.revenueGrowthLast2Quarters = percentGrowth(stock.revenueLastQuarter, stock.revenue3QuartersAgo, "revenueGrowthLast2Quarters")
        stock.revenueGrowthLastYear = percentGrowth(stock.revenueLastYear, stock.revenue2YearsAgo, "revenueGrowthLastYear")
        stock.revenueGrowthLast3Years = percentGrowth(stock.revenueLastYear, stock.revenue3YearsAgo, "revenueGrowthLast3Years")

        stock.cashGrowthLastQuarter = percentGrowth(stock.cashLastQuarter, stock.cash2QuartersAgo, "cashGrowthLastQuarter")
        stock.cashGrowthLastYear = percentGrowth(stock.cashLastYear, stock.cash2YearsAgo, "cashGrowthLastYear")
        stock.cashGrowthLast3Years = percentGrowth(stock.cashLastYear, stock.cash3YearsAgo, "cashGrowthLast3Years")

        stock.inventoryGrowthLastQuarter = percentGrowth(stock.inventoryLastQuarter, stock.inventory2QuartersAgo, "inventoryGrowthLastQuarter")
        stock.inventoryGrowthLastYear = percentGrowth(stock.inventoryLastYear, stock.inventory2YearsAgo, "inventoryGrowthLastYear")
        stock.inventoryGrowthLast3Years = percentGrowth(stock.inventoryLastYear, stock.inventory3YearsAgo, "inventoryGrowthLast3Years")

        stock.currentAssetsGrowthLastQuarter = percentGrowth(stock.currentAssetsLastQuarter, stock.currentAssets2QuartersAgo, "currentAssetsGrowthLastQuarter")
        stock.currentAssetsGrowthLastYear = percentGrowth(stock.currentAssetsLastYear, stock.currentAssets2YearsAgo, "currentAssetsGrowthLastYear")
        stock.currentAssetsGrowthLast3Years = percentGrowth(stock.currentAssetsLastYear, stock.currentAssets3YearsAgo, "currentAssetsGrowthLast3Years")

        stock.currentLiabilitiesGrowthLastQuarter = percentGrowth(stock.currentLiabilitiesLastQuarter, stock.currentLiabilities2QuartersAgo, "currentLiabilitiesGrowthLastQuarter")
        stock.currentLiabilitiesGrowthLastYear = percentGrowth(stock.currentLiabilitiesLastYear, stock.currentLiabilities2YearsAgo, "currentLiabilitiesGrowthLastYear")
        stock.currentLiabilitiesGrowthLast3Years = percentGrowth(stock.currentLiabilitiesLastYear, stock.currentLiabilities3YearsAgo, "currentLiabilitiesGrowthLast3Years")

        stock.totalLiabilitiesGrowthLastQuarter = percentGrowth(stock.totalLiabilitiesLastQuarter, stock.totalLiabilities2QuartersAgo, "totalLiabilitiesGrowthLastQuarter")
        stock.totalLiabilitiesGrowthLastYear = percentGrowth(stock.totalLiabilitiesLastYear, stock.totalLiabilities2YearsAgo, "totalLiabilitiesGrowthLastYear")
        stock.totalLiabilitiesGrowthLast3Years = percentGrowth(stock.totalLiabilitiesLastYear, stock.totalLiabilities3YearsAgo, "totalLiabilitiesGrowthLast3Years")

        stock.totalShareholdersEquityGrowthLastQuarter = percentGrowth(stock.totalShareholdersEquityLastQuarter, stock.totalShareholdersEquity2QuartersAgo, "totalShareholdersEquityGrowthLastQuarter")
        stock.totalShareholdersEquityGrowthLastYear = percentGrowth(stock.totalShareholdersEquityLastYear, stock.totalShareholdersEquity2YearsAgo, "totalShareholdersEquityGrowthLastYear")
        stock.totalShareholdersEquityGrowthLast3Years = percentGrowth(stock.totalShareholdersEquityLastYear, stock.totalShareholdersEquity3YearsAgo, "totalShareholdersEquityGrowthLast3Years")

        stock.currentLiabilitiesToEquityLastQuarter = div(stock.currentLiabilitiesLastQuarter?.toDouble(), stock.totalShareholdersEquityLastQuarter?.toDouble())
        val currentLiabilitiesToEquityPreviousQuarter = div(stock.currentLiabilities2QuartersAgo?.toDouble(), stock.totalShareholdersEquity2QuartersAgo?.toDouble())
        stock.currentLiabilitiesToEquityLastYear = div(stock.currentLiabilitiesLastYear?.toDouble(), stock.totalShareholdersEquityLastYear?.toDouble())
        val currentLiabilitiesToEquity2YearsAgo = div(stock.currentLiabilities2YearsAgo?.toDouble(), stock.totalShareholdersEquity2YearsAgo?.toDouble())
        val currentLiabilitiesToEquity3YearsAgo = div(stock.currentLiabilities3YearsAgo?.toDouble(), stock.totalShareholdersEquity3YearsAgo?.toDouble())
        stock.currentLiabilitiesToEquityGrowthLastQuarter = percentGrowth(stock.currentLiabilitiesToEquityLastQuarter, currentLiabilitiesToEquityPreviousQuarter, "currentLiabilitiesToEquityGrowthLastQuarter", 0.01)
        stock.currentLiabilitiesToEquityGrowthLastYear = percentGrowth(stock.currentLiabilitiesToEquityLastYear, currentLiabilitiesToEquity2YearsAgo, "currentLiabilitiesToEquityGrowthLastYear", 0.01)

        stock.currentLiabilitiesToEquityGrowthLast3Years = percentGrowth(stock.currentLiabilitiesToEquityLastYear, currentLiabilitiesToEquity3YearsAgo, "currentLiabilitiesToEquityGrowthLast3Years", 0.01)

        stock.totalLiabilitiesToEquityLastQuarter = div(stock.totalLiabilitiesLastQuarter?.toDouble(), stock.totalShareholdersEquityLastQuarter?.toDouble())
        val totalLiabilitiesToEquityPreviousQuarter = div(stock.totalLiabilities2QuartersAgo?.toDouble(), stock.totalShareholdersEquity2QuartersAgo?.toDouble())
        stock.totalLiabilitiesToEquityLastYear = div(stock.totalLiabilitiesLastYear?.toDouble(), stock.totalShareholdersEquityLastYear?.toDouble())
        val totalLiabilitiesToEquity2YearsAgo = div(stock.totalLiabilities2YearsAgo?.toDouble(), stock.totalShareholdersEquity2YearsAgo?.toDouble())
        val totalLiabilitiesToEquity3YearsAgo = div(stock.totalLiabilities3YearsAgo?.toDouble(), stock.totalShareholdersEquity3YearsAgo?.toDouble())

        stock.totalLiabilitiesToEquityGrowthLastQuarter = percentGrowth(stock.totalLiabilitiesToEquityLastQuarter, totalLiabilitiesToEquityPreviousQuarter, "totalLiabilitiesToEquityGrowthLastQuarter", 0.01)
        stock.totalLiabilitiesToEquityGrowthLastYear = percentGrowth(stock.totalLiabilitiesToEquityLastYear, totalLiabilitiesToEquity2YearsAgo, "totalLiabilitiesToEquityGrowthLastYear", 0.01)

        stock.totalLiabilitiesToEquityGrowthLast3Years = percentGrowth(stock.totalLiabilitiesToEquityLastYear, totalLiabilitiesToEquity3YearsAgo, "totalLiabilitiesToEquityGrowthLast3Years", 0.01)

        stock.stockGrowthLastQuarter = percentGrowth(stock.stockLastQuarter, stock.stock2QuartersAgo, "stockGrowthLastQuarter")
        stock.stockGrowthLastYear = percentGrowth(stock.stockLastYear, stock.stock2YearsAgo, "stockGrowthLastYear")
        stock.stockGrowthLast3Years = percentGrowth(stock.stockLastYear, stock.stock3YearsAgo, "stockGrowthLast3Years")

        stock.stockRepurchasedGrowthLastQuarter = percentGrowth(stock.stockRepurchasedLastQuarter, stock.stockRepurchased2QuartersAgo, "stockRepurchasedGrowthLastQuarter")
        stock.stockRepurchasedGrowthLastYear = percentGrowth(stock.stockRepurchasedLastYear, stock.stockRepurchased2YearsAgo, "stockRepurchasedGrowthLastYear")
        stock.stockRepurchasedGrowthLast3Years = percentGrowth(stock.stockRepurchasedLastYear, stock.stockRepurchased3YearsAgo, "stockRepurchasedGrowthLast3Years")

        stock.epsGrowthLastQuarter = percentGrowth(stock.epsLastQuarter, stock.eps2QuartersAgo, "epsGrowthLastQuarter", 0.2)
        stock.epsGrowthLast2Quarters = percentGrowth(stock.epsLastQuarter, stock.eps3QuartersAgo, "epsGrowthLast2Quarters", 0.2)
        stock.epsGrowthLast3Quarters = percentGrowth(stock.epsLastQuarter, stock.eps4QuartersAgo, "epsGrowthLast3Quarters", 0.2)
        stock.epsGrowthEstimateLastQuarter = percentGrowth(stock.epsCurrentQuarterEstimate, stock.epsLastQuarter, "epsGrowthEstimateLastQuarter", 0.02)

        stock.epsGrowthLastYear = percentGrowth(stock.epsLastYear, stock.eps2YearsAgo, "epsGrowthLastYear", 0.2)
        stock.epsGrowthLast2Years = percentGrowth(stock.epsLastYear, stock.eps3YearsAgo, "epsGrowthLast2Years", 0.2)
        stock.epsGrowthLast3Years = percentGrowth(stock.epsLastYear, stock.eps4YearsAgo, "epsGrowthLast3Years", 0.2)
    }

    private fun calcChartData(stock: StockInfo) {

        val chartData = stock.chartData ?: return

        if (stock.quarterEnds != null) {
            for ((index, quarter) in stock.quarterEnds!!.withIndex()) {
                when (index) {
                    0 -> {
                        if (stock.epsLastQuarter != null) {
                            val chartDataAt = chartDataFirstBefore(epochSecToLocalDate(quarter), chartData)
                            chartDataAt?.epsQuarterly = stock.epsLastQuarter
                            stock.priceLastQuarter = chartDataAt?.price
                            stock.peLastQuarter = div(stock.priceLastQuarter, CalcUtils.multiply(stock.epsLastQuarter, 4.0))
                        }
                    }
                    1 -> {
                        if (stock.eps2QuartersAgo != null) {
                            val chartDataAt = chartDataFirstBefore(epochSecToLocalDate(quarter), chartData)
                            chartDataAt?.epsQuarterly = stock.eps2QuartersAgo
                            stock.price2QuartersAgo = chartDataAt?.price
                            stock.pe2QuartersAgo = div(stock.price2QuartersAgo, CalcUtils.multiply(stock.eps2QuartersAgo, 4.0))
                        }
                    }
                    2 -> {
                        if (stock.eps3QuartersAgo != null) {
                            val chartDataAt = chartDataFirstBefore(epochSecToLocalDate(quarter), chartData)
                            chartDataAt?.epsQuarterly = stock.eps3QuartersAgo
                            stock.price3QuartersAgo = chartDataAt?.price
                            stock.pe3QuartersAgo = div(stock.price3QuartersAgo, CalcUtils.multiply(stock.eps3QuartersAgo, 4.0))
                        }
                    }
                    3 -> {
                        if (stock.eps4QuartersAgo != null) {
                            val chartDataAt = chartDataFirstBefore(epochSecToLocalDate(quarter), chartData)
                            chartDataAt?.epsQuarterly = stock.eps4QuartersAgo
                            stock.price4QuartersAgo = chartDataAt?.price
                            stock.pe4QuartersAgo = div(stock.price4QuartersAgo, CalcUtils.multiply(stock.eps4QuartersAgo, 4.0))
                        }
                    }
                }
            }
        }

        if (stock.yearEnds != null) {
            for ((index, year) in stock.yearEnds!!.withIndex()) {
                val eps = when (index) {
                    0 -> stock.epsLastYear
                    1 -> stock.eps2YearsAgo
                    2 -> stock.eps3YearsAgo
                    3 -> stock.eps4YearsAgo
                    else -> null
                }
                if (eps != null) {
                    val chartDataAt = chartDataFirstBefore(epochSecToLocalDate(year), chartData)
                    chartDataAt?.epsAnnually = eps
                }
            }
        }

        stock.priceGrowthLastQuarter = percentGrowth(stock.priceLastQuarter, stock.price2QuartersAgo, "priceGrowthLastQuarter")
        stock.priceGrowthLast2Quarters = percentGrowth(stock.priceLastQuarter, stock.price3QuartersAgo, "priceGrowthLast2Quarters")
        stock.priceGrowthLast3Quarters = percentGrowth(stock.priceLastQuarter, stock.price4QuartersAgo, "priceGrowthLast3Quarters")

        stock.peGrowthLastQuarter = percentGrowth(stock.peLastQuarter, stock.pe2QuartersAgo, "peGrowthLastQuarter", 0.01)
        stock.peGrowthLast2Quarters = percentGrowth(stock.peLastQuarter, stock.pe3QuartersAgo, "peGrowthLast2Quarters", 0.01)
        stock.peGrowthLast3Quarters = percentGrowth(stock.peLastQuarter, stock.pe4QuartersAgo, "peGrowthLast3Quarters", 0.01)

        stock.chartData = chartData
    }

    fun chartDataFirstBefore(date: LocalDate, chartData: List<StockChartData>): StockChartData? {
        return chartData.firstOrNull {
            val chartDate = epochSecToLocalDate(it.date)
            chartDate.isBefore(date) && ChronoUnit.DAYS.between(chartDate, date) <= StockService.CHART_SAMPLING_INTERVAL.days
        }
    }

    fun calcStocksAverages(stocks: List<StockInfo>): StockInfo {

        val counter = StocksAveragesCounter(StockInfo(symbol = "Avg.", exchange = Exchange.NASDAQ))
        val averages = counter.averages

        val stockNumericFields = StockInfo::class.memberProperties
            .filterIsInstance<KMutableProperty<*>>()
            .filter { f ->
                when (f.name) {
                    "id", "price", "date", "lastReportedQuarter", "symbol", "exchange", "companyName", "currency",
                    "financialCurrency", "quarterEnds", "yearEnds", "chartData", "exDividendDate"
                    -> false
                    else -> true
                }
            }
        calcAverages(counter, stocks, stockNumericFields, averages)

        return averages
    }


    fun calcIndicesAverages(indices: List<IndexInfo>): IndexInfo {
        val counter = IndicesAveragesCounter(IndexInfo(symbol = "Avg."))
        val averages = counter.averages

        val indexNumericFields = IndexInfo::class.memberProperties
            .filterIsInstance<KMutableProperty<*>>()
            .filter { f ->
                when (f.name) {
                    "id", "price", "date", "lastReportedQuarter", "symbol", "exchange", "companyName", "currency", "asOfDate", "chartData", "fundInceptionDate"
                    -> false
                    else -> true
                }
            }
        calcAverages(counter, indices, indexNumericFields, averages)

        return averages
    }


    private fun calcAverages(counter: Any, stocks: List<Any>, stockNumericFields: List<KMutableProperty<*>>, averages: Any) {
        val counterFields = counter::class.memberProperties
            .filterIsInstance<KMutableProperty<*>>()

        for (stock in stocks) {

            /**
            Iterate though all stats using reflection to sum all values in order to calculate averages, such as:
            if (stock.enterpriseValue != null) {
            averages.enterpriseValue = sum(averages.enterpriseValue, stock.enterpriseValue)
            counter.enterpriseValueCount++
            }
             */

            for (stockField in stockNumericFields) {
                val fieldValue = stockField.getter.call(stock) as Number?
                if (fieldValue != null) {
                    val sumValue = stockField.getter.call(averages) as Number?
                    stockField.setter.call(averages, sum(fieldValue, sumValue))
                    val counterFieldName = stockField.name + "Count"
                    val counterField = counterFields
                        .firstOrNull { counterField -> counterField.name == counterFieldName }
                    if (counterField == null) {
                        log.debug("Missing counter $counterFieldName")
                    } else {
                        counterField.setter.call(counter, counterField.getter.call(counter) as Int + 1)
                    }
                }
            }
        }

        //divide the calculated sums by counters to get averages
        for (stockField in stockNumericFields) {
            val sumValue = stockField.getter.call(averages) as Number?
            val counterFieldName = stockField.name + "Count"
            val counterField = counterFields
                .firstOrNull { counterField -> counterField.name == counterFieldName }
            if (counterField == null) {
                log.debug("Missing counter $counterFieldName")
            } else {
                val counterValue = counterField.getter.call(counter) as Int
                stockField.setter.call(averages, div(sumValue, counterValue))
            }
        }
    }

    fun combineWithRatios(stocks: List<StockInfo>, ratios: List<StockRatiosTimeline>): List<StockInfoWithRatios> {
        val stockInfoWithRatios = mutableListOf<StockInfoWithRatios>()
        for (stock in stocks){
            val ratio =  ratios
                .first { StockTicker.fromSymbolAndMic(it.symbol, it.mic) == StockTicker(stock.symbol, stock.exchange) }
            stockInfoWithRatios.add(StockInfoWithRatios(stock, ratio))
        }
        return stockInfoWithRatios
    }
}