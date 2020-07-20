package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.EtfsAveragesCounter
import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.mongo.Etf
import com.github.dusanzahoransky.stockanalyst.model.mongo.Stock
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.sum
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

@Service
class StockAnalysisService {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun calcStocksAverages(stocks: List<Stock>): Stock {

        val counter = mutableMapOf<String, Int>()
        val averages = Stock(symbol = "AVERAGES", exchange = Exchange.NASDAQ)

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
                stockField.isAccessible = true
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
                        counterField.isAccessible = true
                        counterField.setter.call(counter, counterField.getter.call(counter) as Int + 1)
                    }
                }
            }
        }

        //divide the calculated sums by counters to get averages
        for (stockField in stockNumericFields) {
            stockField.isAccessible = true
            val sumValue = stockField.getter.call(averages) as Number?
            val counterFieldName = stockField.name + "Count"
            val counterField = counterFields
                .firstOrNull { counterField -> counterField.name == counterFieldName }
            if (counterField == null) {
                log.debug("Missing counter $counterFieldName")
            } else {
                counterField.isAccessible = true
                val counterValue = counterField.getter.call(counter) as Int
                stockField.setter.call(averages, div(sumValue, counterValue))
            }
        }
    }
}