package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import com.github.dusanzahoransky.stockanalyst.model.yahoo.StocksAveragesCounter
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.sum
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

@Service
class StockAnalysisService {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun calcStocksAverages(stocks: List<StockInfo>): StockInfo {

        val counter = StocksAveragesCounter(StockInfo(symbol = "Avg."))
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

        return averages
    }


}