package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.EtfAverages
import com.github.dusanzahoransky.stockanalyst.model.EtfsAveragesCounter
import com.github.dusanzahoransky.stockanalyst.model.mongo.Etf
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.sum
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

@Service
class StockAnalysisService {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun calcEtfsAverages(etfs: List<Etf>): EtfAverages {
        val counter = EtfsAveragesCounter()
        val averages = counter.averages

        val averagesFields = EtfAverages::class.memberProperties
            .filterIsInstance<KMutableProperty<*>>()
        val averagesFieldNames = averagesFields
            .map { it.name }
            .toSet()

        val etfNumericFields = Etf::class.memberProperties
            .filterIsInstance<KMutableProperty<*>>()
            .filter { averagesFieldNames.contains(it.name) }

        calcAverages(counter, etfs, etfNumericFields, averages, averagesFields)

        return averages
    }


    private fun calcAverages(
        counter: Any, etfs: List<Etf>, etfFields: List<KMutableProperty<*>>,
        averages: EtfAverages, averagesFields: List<KMutableProperty<*>>
    ) {
        val counterFields = counter::class.memberProperties
            .filterIsInstance<KMutableProperty<*>>()

        for (etf in etfs) {

            /**
            Iterate though all stats using reflection to sum all values in order to calculate averages using averages object holding values and counter with xxxCount fields accumulating number of non null values, such as:
            if (etf.enterpriseValue != null) {
            averages.enterpriseValue = sum(averages.enterpriseValue, etf.enterpriseValue)
            counter.enterpriseValueCount++
            }
             */

            for (etfField in etfFields) {
                etfField.isAccessible = true
                @Suppress("UNCHECKED_CAST")
                val fieldValue = etfField.getter.call(etf) as SortedMap<LocalDate, Number>
                val lastEntryValue = fieldValue.values.lastOrNull()
                if (lastEntryValue != null && lastEntryValue != 0.0) {  //some fields default to 0.0 instead of null when missing values

                    val averagesField = averagesFields.first { it.name.equals(etfField.name) }
                    val sumValue = averagesField.getter.call(averages) as Number?
                    averagesField.setter.call(averages, sum(lastEntryValue, sumValue))

                    val counterFieldName = etfField.name + "Count"
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
        for (etfField in etfFields) {
            etfField.isAccessible = true
            val averagesField = averagesFields.first { it.name.equals(etfField.name) }
            val sumValue = averagesField.getter.call(averages) as Number?
            val counterFieldName = etfField.name + "Count"
            val counterField = counterFields
                .firstOrNull { counterField -> counterField.name == counterFieldName }
            if (counterField == null) {
                log.debug("Missing counter $counterFieldName")
            } else {
                counterField.isAccessible = true
                val counterValue = counterField.getter.call(counter) as Int
                averagesField.setter.call(averages, div(sumValue, counterValue))
            }
        }
    }
}