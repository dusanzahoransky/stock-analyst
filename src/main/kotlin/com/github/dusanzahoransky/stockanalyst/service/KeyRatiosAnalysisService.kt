package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.mongo.Ratios
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class KeyRatiosAnalysisService {


    val log = LoggerFactory.getLogger(this::class.java)!!


    private fun periodYearsBefore(periods: SortedMap<LocalDate, Ratios>, yearsBeforePresent: Int): Ratios? {
        val present = periods.firstKey()
        if (yearsBeforePresent == 0) {
            return periods[present]
        }
        val yearsBeforeKey = periods.keys.firstOrNull { ChronoUnit.YEARS.between(it, present) == yearsBeforePresent.toLong() }

        return yearsBeforeKey.let { periods[yearsBeforeKey] }
    }

}