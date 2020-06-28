package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.EtfsAveragesCounter
import com.github.dusanzahoransky.stockanalyst.model.StocksAveragesCounter
import com.github.dusanzahoransky.stockanalyst.model.Ticker
import com.github.dusanzahoransky.stockanalyst.model.dto.StockWithRatios
import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.Etf
import com.github.dusanzahoransky.stockanalyst.model.mongo.Stock
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockChartData
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatiosTimeline
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percent
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percentGrowth
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.sum
import com.github.dusanzahoransky.stockanalyst.util.FormattingUtils.Companion.epochSecToLocalDate
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

@Service
class StockAnalysisService {

    val log = LoggerFactory.getLogger(this::class.java)!!

    /**
     * Enhance the stock data with Growth and other ratios that were not provided by Yahoo finance API. It's good to keep this logic separated so the calculation can be changed without a need to fetch data from Yahoo API again.
     */
    fun calcStockStats(stocks: List<Stock>): List<Stock> {
        for (stock in stocks) {
            calcFinancialStats(stock)
            calcChartData(stock)
        }
        return stocks
    }

    private fun calcFinancialStats(stock: Stock) {
        if (stock.price != null && stock.week52Low != null) {
            stock.week52AboveLowPercent = percent((stock.price!! - stock.week52Low!!) / stock.price!!)
        }
        if (stock.price != null && stock.week52High != null) {
            stock.week52BelowHighPercent = percent((stock.week52High!! - stock.price!!) / stock.price!!)
        }
        if (stock.targetLowPrice != null && stock.price != null) {
            stock.belowTargetLowPricePercent = percent((stock.targetLowPrice!! - stock.price!!) / stock.price!!)
        }
        if (stock.targetMedianPrice != null && stock.price != null) {
            stock.belowTargetMedianPricePercent = percent((stock.targetMedianPrice!! - stock.price!!) / stock.price!!)
        }

        if (stock.totalCashPerShare != null && stock.price != null) {
            stock.totalCashPerSharePercent = percent(stock.totalCashPerShare!! / stock.price!!)
        }
        if (stock.totalLiabilitiesLastQuarter != null && stock.totalShareholdersEquityLastQuarter != null) {
            stock.totalDebtEquity = div(stock.totalLiabilitiesLastQuarter!!.toDouble(), stock.totalShareholdersEquityLastQuarter!!.toDouble())
        }

        stock.revenueGrowthLastQuarter = percentGrowth(stock.revenueLastQuarter, stock.revenue2QuartersAgo, "revenueGrowthLastQuarter")
        stock.revenueGrowthLast2Quarters = percentGrowth(stock.revenueLastQuarter, stock.revenue3QuartersAgo, "revenueGrowthLast2Quarters")
        stock.revenueGrowthLastYear = percentGrowth(stock.revenueLastYear, stock.revenue2YearsAgo, "revenueGrowthLastYear")
        stock.revenueGrowthLast4Years = percentGrowth(stock.revenueLastYear, stock.revenue4YearsAgo, "revenueGrowthLast4Years")

//        stock.grossIncomeGrowthLastQuarter = percentGrowth(stock.grossIncomeLastQuarter, stock.grossIncome2QuartersAgo, "grossIncomeGrowthLastQuarter")
//        stock.grossIncomeGrowthLast2Quarters = percentGrowth(stock.grossIncomeLastQuarter, stock.grossIncome3QuartersAgo, "grossIncomeGrowthLast2Quarters")
//        stock.grossIncomeGrowthLastYear = percentGrowth(stock.grossIncomeLastYear, stock.grossIncome2YearsAgo, "grossIncomeGrowthLastYear")
//        stock.grossIncomeGrowthLast4Years = percentGrowth(stock.grossIncomeLastYear, stock.grossIncome4YearsAgo, "grossIncomeGrowthLast4Years")

        stock.ebitGrowthLastQuarter = percentGrowth(stock.ebitLastQuarter, stock.ebit2QuartersAgo, "ebitGrowthLastQuarter")
        stock.ebitGrowthLast2Quarters = percentGrowth(stock.ebitLastQuarter, stock.ebit3QuartersAgo, "ebitGrowthLast2Quarters")
        stock.ebitGrowthLastYear = percentGrowth(stock.ebitLastYear, stock.ebit2YearsAgo, "ebitGrowthLastYear")
        stock.ebitGrowthLast4Years = percentGrowth(stock.ebitLastYear, stock.ebit4YearsAgo, "ebitGrowthLast4Years")

        stock.netIncomeGrowthLastQuarter = percentGrowth(stock.netIncomeLastQuarter, stock.netIncome2QuartersAgo, "netIncomeGrowthLastQuarter")
        stock.netIncomeGrowthLast2Quarters = percentGrowth(stock.netIncomeLastQuarter, stock.netIncome3QuartersAgo, "netIncomeGrowthLast2Quarters")
        stock.netIncomeGrowthLastYear = percentGrowth(stock.netIncomeLastYear, stock.netIncome2YearsAgo, "netIncomeGrowthLastYear")
        stock.netIncomeGrowthLast4Years = percentGrowth(stock.netIncomeLastYear, stock.netIncome4YearsAgo, "netIncomeGrowthLast4Years")

        stock.profitMarginLastQuarter = div(stock.netIncomeLastQuarter, stock.revenueLastQuarter)
        stock.profitMargin2QuartersAgo = div(stock.netIncome2QuartersAgo, stock.revenue2QuartersAgo)
        stock.profitMargin3QuartersAgo = div(stock.netIncome3QuartersAgo, stock.revenue3QuartersAgo)
        stock.profitMarginLastYear = div(stock.netIncomeLastYear, stock.revenueLastYear)
        stock.profitMargin2YearsAgo = div(stock.netIncome2YearsAgo, stock.revenue2YearsAgo)
        stock.profitMargin4YearsAgo = div(stock.netIncome4YearsAgo, stock.revenue4YearsAgo)

        stock.profitMarginGrowthLastQuarter = percentGrowth(stock.profitMarginLastQuarter, stock.profitMargin2QuartersAgo, "profitMarginGrowthLastQuarter", 0.1)
        stock.profitMarginGrowthLast2Quarters = percentGrowth(stock.profitMarginLastQuarter, stock.profitMargin3QuartersAgo, "profitMarginGrowthLast2Quarters", 0.1)
        stock.profitMarginGrowthLastYear = percentGrowth(stock.profitMarginLastYear, stock.profitMargin2YearsAgo, "profitMarginGrowthLastYear", 0.1)
        stock.profitMarginGrowthLast4Years = percentGrowth(stock.profitMarginLastYear, stock.profitMargin4YearsAgo, "profitMarginGrowthLast4Years", 0.1)

        stock.freeCashFlowGrowthLastQuarter = percentGrowth(stock.freeCashFlowLastQuarter, stock.freeCashFlow2QuartersAgo, "freeCashFlowGrowthLastQuarter")
        stock.freeCashFlowGrowthLast2Quarters = percentGrowth(stock.freeCashFlowLastQuarter, stock.freeCashFlow3QuartersAgo, "freeCashFlowGrowthLast2Quarters")
        stock.freeCashFlowGrowthLastYear = percentGrowth(stock.freeCashFlowLastYear, stock.freeCashFlow2YearsAgo, "freeCashFlowGrowthLastYear")
        stock.freeCashFlowGrowthLast4Years = percentGrowth(stock.freeCashFlowLastYear, stock.freeCashFlow4YearsAgo, "freeCashFlowGrowthLast4Years")

        stock.cashGrowthLastQuarter = percentGrowth(stock.cashLastQuarter, stock.cash2QuartersAgo, "cashGrowthLastQuarter")
        stock.cashGrowthLast2Quarters = percentGrowth(stock.cashLastQuarter, stock.cash3QuartersAgo, "cashGrowthLast2Quarters")
        stock.cashGrowthLastYear = percentGrowth(stock.cashLastYear, stock.cash4YearsAgo, "cashGrowthLast4Years")
        stock.cashGrowthLast4Years = percentGrowth(stock.cashLastYear, stock.cash4YearsAgo, "cashGrowthLast4Years")

        stock.inventoryGrowthLastQuarter = percentGrowth(stock.inventoryLastQuarter, stock.inventory2QuartersAgo, "inventoryGrowthLastQuarter")
        stock.inventoryGrowthLast2Quarters = percentGrowth(stock.inventoryLastQuarter, stock.inventory3QuartersAgo, "inventoryGrowthLast2Quarters")
        stock.inventoryGrowthLastYear = percentGrowth(stock.inventoryLastYear, stock.inventory2YearsAgo, "inventoryGrowthLastYear")
        stock.inventoryGrowthLast4Years = percentGrowth(stock.inventoryLastYear, stock.inventory4YearsAgo, "inventoryGrowthLast4Years")

        stock.currentRatioLastQuarter = div(stock.currentAssetsLastQuarter?.toDouble(), stock.currentLiabilitiesLastQuarter?.toDouble())
        stock.currentRatio2QuartersAgo = div(stock.currentAssets2QuartersAgo?.toDouble(), stock.currentLiabilities2QuartersAgo?.toDouble())
        stock.currentRatio3QuartersAgo = div(stock.currentAssets3QuartersAgo?.toDouble(), stock.currentLiabilities3QuartersAgo?.toDouble())
        stock.currentRatioLastYear = div(stock.currentAssetsLastYear?.toDouble(), stock.currentLiabilitiesLastYear?.toDouble())
        stock.currentRatio2YearsAgo = div(stock.currentAssets2YearsAgo?.toDouble(), stock.currentLiabilities2YearsAgo?.toDouble())
        stock.currentRatio4YearsAgo = div(stock.currentAssets4YearsAgo?.toDouble(), stock.currentLiabilities4YearsAgo?.toDouble())

        stock.currentRatioGrowthLastQuarter = percentGrowth(stock.currentRatioLastQuarter, stock.currentRatio2QuartersAgo, "currentRatioGrowthLastQuarter", 0.1)
        stock.currentRatioGrowthLast2Quarters = percentGrowth(stock.currentRatioLastQuarter, stock.currentRatio3QuartersAgo, "currentRatioGrowthLast2Quarters", 0.1)
        stock.currentRatioGrowthLastYear = percentGrowth(stock.currentRatioLastYear, stock.currentRatio2YearsAgo, "currentRatioGrowthLastYear", 0.1)
        stock.currentRatioGrowthLast4Years = percentGrowth(stock.currentRatioLastYear, stock.currentRatio4YearsAgo, "currentRatioGrowthLast4Years", 0.1)

        stock.totalLiabilitiesGrowthLastQuarter = percentGrowth(stock.totalLiabilitiesLastQuarter, stock.totalLiabilities2QuartersAgo, "totalLiabilitiesGrowthLastQuarter")
        stock.totalLiabilitiesGrowthLast2Quarters = percentGrowth(stock.totalLiabilitiesLastQuarter, stock.totalLiabilities3QuartersAgo, "totalLiabilitiesGrowthLast2Quarters")
        stock.totalLiabilitiesGrowthLastYear = percentGrowth(stock.totalLiabilitiesLastYear, stock.totalLiabilities2YearsAgo, "totalLiabilitiesGrowthLastYear")
        stock.totalLiabilitiesGrowthLast4Years = percentGrowth(stock.totalLiabilitiesLastYear, stock.totalLiabilities4YearsAgo, "totalLiabilitiesGrowthLast4Years")

        stock.totalShareholdersEquityGrowthLastQuarter = percentGrowth(stock.totalShareholdersEquityLastQuarter, stock.totalShareholdersEquity2QuartersAgo, "totalShareholdersEquityGrowthLastQuarter")
        stock.totalShareholdersEquityGrowthLast2Quarters = percentGrowth(stock.totalShareholdersEquityLastQuarter, stock.totalShareholdersEquity3QuartersAgo, "totalShareholdersEquityGrowthLast2Quarters")
        stock.totalShareholdersEquityGrowthLastYear = percentGrowth(stock.totalShareholdersEquityLastYear, stock.totalShareholdersEquity2YearsAgo, "totalShareholdersEquityGrowthLastYear")

        stock.totalShareholdersEquityGrowthLast4Years = percentGrowth(stock.totalShareholdersEquityLastYear, stock.totalShareholdersEquity4YearsAgo, "totalShareholdersEquityGrowthLast4Years")


        stock.totalLiabilitiesToEquityLastQuarter = div(stock.totalLiabilitiesLastQuarter?.toDouble(), stock.totalShareholdersEquityLastQuarter?.toDouble())
        stock.totalLiabilitiesToEquity2QuartersAgo = div(stock.totalLiabilities2QuartersAgo?.toDouble(), stock.totalShareholdersEquity2QuartersAgo?.toDouble())
        stock.totalLiabilitiesToEquity3QuartersAgo = div(stock.totalLiabilities3QuartersAgo?.toDouble(), stock.totalShareholdersEquity3QuartersAgo?.toDouble())
        stock.totalLiabilitiesToEquityLastYear = div(stock.totalLiabilitiesLastYear?.toDouble(), stock.totalShareholdersEquityLastYear?.toDouble())
        stock.totalLiabilitiesToEquity2YearsAgo = div(stock.totalLiabilities2YearsAgo?.toDouble(), stock.totalShareholdersEquity2YearsAgo?.toDouble())
        stock.totalLiabilitiesToEquity4YearsAgo = div(stock.totalLiabilities4YearsAgo?.toDouble(), stock.totalShareholdersEquity4YearsAgo?.toDouble())

        stock.totalLiabilitiesToEquityGrowthLastQuarter = percentGrowth(stock.totalLiabilitiesToEquityLastQuarter, stock.totalLiabilitiesToEquity2QuartersAgo, "totalLiabilitiesToEquityGrowthLastQuarter", 0.01)
        stock.totalLiabilitiesToEquityGrowthLast2Quarters = percentGrowth(stock.totalLiabilitiesToEquityLastQuarter, stock.totalLiabilitiesToEquity3QuartersAgo, "totalLiabilitiesToEquityGrowthLast2Quarters", 0.01)
        stock.totalLiabilitiesToEquityGrowthLastYear = percentGrowth(stock.totalLiabilitiesToEquityLastYear, stock.totalLiabilitiesToEquity2YearsAgo, "totalLiabilitiesToEquityGrowthLastYear", 0.01)
        stock.totalLiabilitiesToEquityGrowthLast4Years = percentGrowth(stock.totalLiabilitiesToEquityLastYear, stock.totalLiabilitiesToEquity4YearsAgo, "totalLiabilitiesToEquityGrowthLast4Years", 0.01)

        stock.stockGrowthLastQuarter = percentGrowth(stock.stockLastQuarter, stock.stock2QuartersAgo, "stockGrowthLastQuarter")
        stock.stockGrowthLast2Quarters = percentGrowth(stock.stockLastQuarter, stock.stock3QuartersAgo, "stockGrowthLast2Quarters")
        stock.stockGrowthLastYear = percentGrowth(stock.stockLastYear, stock.stock2YearsAgo, "stockGrowthLastYear")
        stock.stockGrowthLast4Years = percentGrowth(stock.stockLastYear, stock.stock4YearsAgo, "stockGrowthLast4Years")

        stock.stockRepurchasedGrowthLastQuarter = percentGrowth(stock.stockRepurchasedLastQuarter, stock.stockRepurchased2QuartersAgo, "stockRepurchasedGrowthLastQuarter")
        stock.stockRepurchasedGrowthLast2Quarters = percentGrowth(stock.stockRepurchasedLastQuarter, stock.stockRepurchased3QuartersAgo, "stockRepurchasedGrowthLast2Quarters")
        stock.stockRepurchasedGrowthLastYear = percentGrowth(stock.stockRepurchasedLastYear, stock.stockRepurchased2YearsAgo, "stockRepurchasedGrowthLastYear")
        stock.stockRepurchasedGrowthLast4Years = percentGrowth(stock.stockRepurchasedLastYear, stock.stockRepurchased4YearsAgo, "stockRepurchasedGrowthLast4Years")

        stock.epsGrowthLastQuarter = percentGrowth(stock.epsLastQuarter, stock.eps2QuartersAgo, "epsGrowthLastQuarter", 0.2)
        stock.epsGrowthLast2Quarters = percentGrowth(stock.epsLastQuarter, stock.eps3QuartersAgo, "epsGrowthLast2Quarters", 0.2)
        stock.epsGrowthLastYear = percentGrowth(stock.epsLastYear, stock.eps2YearsAgo, "epsGrowthLastYear", 0.2)
        stock.epsGrowthLast4Years = percentGrowth(stock.epsLastYear, stock.eps4YearsAgo, "epsGrowthLast4Years", 0.2)
    }

    private fun calcChartData(stock: Stock) {

        val chartData = stock.chartData ?: return

        if (stock.quarterEnds != null) {
            for ((index, quarter) in stock.quarterEnds!!.withIndex()) {
                val chartDataAt = chartDataFirstBefore(epochSecToLocalDate(quarter), chartData)
                when (index) {
                    0 -> {
//                        stock.priceLastQuarter = chartDataAt?.price
                        if (stock.epsLastQuarter != null) {
                            chartDataAt?.epsQuarterly = stock.epsLastQuarter
                            stock.peLastQuarter = div(chartDataAt?.price, CalcUtils.multiply(stock.epsLastQuarter, 4.0))
                        }
                    }
                    1 -> {
//                        stock.price2QuartersAgo = chartDataAt?.price
                        if (stock.eps2QuartersAgo != null) {
                            chartDataAt?.epsQuarterly = stock.eps2QuartersAgo
                            stock.pe2QuartersAgo = div(chartDataAt?.price, CalcUtils.multiply(stock.eps2QuartersAgo, 4.0))
                        }
                    }
                    2 -> {
//                        stock.price3QuartersAgo = chartDataAt?.price
                        if (stock.eps3QuartersAgo != null) {
                            chartDataAt?.epsQuarterly = stock.eps3QuartersAgo
                            stock.pe3QuartersAgo = div(chartDataAt?.price, CalcUtils.multiply(stock.eps3QuartersAgo, 4.0))
                        }
                    }
                    3 -> {
//                        stock.price4QuartersAgo = chartDataAt?.price
                        if (stock.eps4QuartersAgo != null) {
                            chartDataAt?.epsQuarterly = stock.eps4QuartersAgo
                            stock.pe4QuartersAgo = div(chartDataAt?.price, CalcUtils.multiply(stock.eps4QuartersAgo, 4.0))
                        }
                    }
                }
            }
        }

        if (stock.yearEnds != null) {
            for ((index, year) in stock.yearEnds!!.withIndex()) {
                val chartDataAt = chartDataFirstBefore(epochSecToLocalDate(year), chartData)

                when (index) {
                    0 -> {
//                        stock.priceLastYear = chartDataAt?.price
                        if (stock.epsLastYear != null) {
                            chartDataAt?.epsAnnually = stock.epsLastYear
                            stock.peLastYear = div(chartDataAt?.price, stock.epsLastYear)
                        }
                    }
                    1 -> {
//                        stock.price2YearsAgo = chartDataAt?.price
                        if (stock.eps2YearsAgo != null) {
                            chartDataAt?.epsAnnually = stock.eps2YearsAgo
                            stock.pe2YearsAgo = div(chartDataAt?.price, stock.eps2YearsAgo)
                        }
                    }
                    2 -> {
//                        stock.price3YearsAgo = chartDataAt?.price
                        if (stock.eps3YearsAgo != null) {
                            chartDataAt?.epsAnnually = stock.eps3YearsAgo
                            stock.pe3YearsAgo = div(chartDataAt?.price, stock.eps3YearsAgo)
                        }
                    }
                    3 -> {
//                        stock.price4YearsAgo = chartDataAt?.price
                        if (stock.eps4YearsAgo != null) {
                            chartDataAt?.epsAnnually = stock.eps4YearsAgo
                            stock.pe4YearsAgo = div(chartDataAt?.price, stock.eps4YearsAgo)
                        }
                    }
                }
            }
        }

//        stock.priceGrowthLastQuarter = percentGrowth(stock.priceLastQuarter, stock.price2QuartersAgo, "priceGrowthLastQuarter")
//        stock.priceGrowthLast2Quarters = percentGrowth(stock.priceLastQuarter, stock.price3QuartersAgo, "priceGrowthLast2Quarters")
//        stock.priceGrowthLastYear = percentGrowth(stock.priceLastYear, stock.price2YearsAgo, "priceGrowthLastYear")
//        stock.priceGrowthLast4Years = percentGrowth(stock.priceLastYear, stock.price4YearsAgo, "priceGrowthLast4Years")

        stock.peGrowthLastQuarter = percentGrowth(stock.peLastQuarter, stock.pe2QuartersAgo, "peGrowthLastQuarter", 0.01)
        stock.peGrowthLast2Quarters = percentGrowth(stock.peLastQuarter, stock.pe3QuartersAgo, "peGrowthLast2Quarters", 0.01)
        stock.peGrowthLastYear = percentGrowth(stock.peLastYear, stock.pe2YearsAgo, "peGrowthLastYear", 0.01)
        stock.peGrowthLast4Years = percentGrowth(stock.peLastYear, stock.pe4YearsAgo, "peGrowthLast4Years", 0.01)

        stock.chartData = chartData
    }

    fun chartDataFirstBefore(date: LocalDate, chartData: List<StockChartData>): StockChartData? {
        return chartData.firstOrNull {
            val chartDate = epochSecToLocalDate(it.date)
            chartDate.isBefore(date) && ChronoUnit.DAYS.between(chartDate, date) <= StockService.CHART_SAMPLING_INTERVAL.days
        }
    }

    fun calcStocksAverages(stocks: List<Stock>): Stock {

        val counter = StocksAveragesCounter(Stock(symbol = "Avg.", exchange = Exchange.NASDAQ))
        val averages = counter.averages

        val stockNumericFields = Stock::class.memberProperties
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


    fun calcEtfsAverages(indices: List<Etf>): Etf {
        val counter = EtfsAveragesCounter(Etf(symbol = "Avg."))
        val averages = counter.averages

        val indexNumericFields = Etf::class.memberProperties
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
            Iterate though all stats using reflection to sum all values in order to calculate averages using averages object holding values and counter with xxxCount fields accumulating number of non null values, such as:
            if (stock.enterpriseValue != null) {
            averages.enterpriseValue = sum(averages.enterpriseValue, stock.enterpriseValue)
            counter.enterpriseValueCount++
            }
             */

            for (stockField in stockNumericFields) {
                val fieldValue = stockField.getter.call(stock) as Number?
                if (fieldValue != null && fieldValue != 0.0) {  //some fields default to 0.0 instead of null when missing values
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

    fun combineWithRatios(stocks: List<Stock>, ratios: List<StockRatiosTimeline>): List<StockWithRatios> {
        val stockInfoWithRatios = mutableListOf<StockWithRatios>()
        for (stock in stocks) {
            val ratio = ratios
                .firstOrNull { Ticker(it.symbol, it.exchange) == Ticker(stock.symbol, stock.exchange) }
            if (ratio == null) {
                log.error("Missing ratios for stock ${stock.symbol} ${stock.exchange}")
                continue
            }
            stockInfoWithRatios.add(StockWithRatios(stock, ratio))
        }
        return stockInfoWithRatios
    }
}