package com.github.dusanzahoransky.stockanalyst.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.abs
import kotlin.math.pow

class CalcUtils {

    companion object {

        private val log: Logger = LoggerFactory.getLogger(CalcUtils::class.java)

        fun cumulativeGrowthRate(currentValue: Double?, previousValue: Double?, numberOfYears: Int, statName: String, signThreshold: Double = 100.0): Double? {
            if (currentValue == null || previousValue == null || previousValue == 0.0) {
                return null
            }
            if (abs(previousValue) < signThreshold) {   //the previous value is too small to get any meaningful result
                log.debug("Skipping $statName cumulative growth rate calculation, value $previousValue is too insignificant")
                return null
            }
            if ((previousValue > 0.0 && signThreshold < 0.0) || (previousValue < 0.0 && signThreshold > 0.0)) {
                return null
            }
            val currDivPrev = currentValue / previousValue
            if (currDivPrev < 0) {    //imaginary number, can't calculate
                return null
            }
            return (currDivPrev.pow(1.0 / numberOfYears) - 1) * 100
        }

        fun <N : Number> percentGrowth(currentValue: N?, previousValue: N?, statName: String = "", signThreshold: Double = 100.0): Double? {
            if (currentValue == null || previousValue == null) {
                return null
            }
            val currValueD = currentValue.toDouble()
            val prevValueD = previousValue.toDouble()
            if (abs(prevValueD) < signThreshold) {   //the previous value is too small to get any meaningful result
                log.debug("Skipping $statName percentGrowth calculation, value $prevValueD is too insignificant")
                return null
            }
            if ((prevValueD > 0.0 && currValueD < 0.0) || (prevValueD < 0.0 && currValueD > 0.0)) {
                return null
            }
            return percent((currValueD - prevValueD) / prevValueD)
        }

        /**
         * Null-safe sum of 2 nullable numbers
         */
        @Suppress("UNCHECKED_CAST")
        fun <N : Number> sum(value1: N?, value2: N?): N? {
            return if (value1 == null) {
                value2
            } else if (value2 == null) {
                value1
            } else {
                if (value1 is Double && value2 is Double)
                    (value1 + value2) as N
                else if (value1 is Long && value2 is Long)
                    (value1 + value2) as N
                else
                    throw IllegalArgumentException("Unsupported sum argument types ${value1.javaClass}, ${value2.javaClass}")
            }
        }

        /**
         * Null-safe division of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        fun <N : Number> div(value1: N?, value2: N?): Double? {
            return if (value1 == null || value2 == null) {
                null
            } else {
                if (value1 is Double && value2 is Double)
                    value1 / value2
                else if (value1 is Long && value2 is Long)
                    value1.toDouble() / value2.toDouble()
                else
                    throw IllegalArgumentException("Unsupported div argument types ${value1.javaClass}, ${value2.javaClass}")
            }
        }

        /**
         * Null-safe minus of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        fun <N : Number> minus(value1: N?, value2: N?): N? {
            return if (value1 == null || value2 == null) {
                null
            } else {
                if (value1 is Double && value2 is Double)
                    (value1 - value2) as N
                else if (value1 is Long && value2 is Long)
                    (value1 - value2) as N
                else
                    throw IllegalArgumentException("Unsupported minus argument types " + value1.javaClass + "," + value2.javaClass)
            }
        }

        /**
         * Null-safe division of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        fun <N : Number> plus(value1: N?, value2: N?): N? {
            return if (value1 == null || value2 == null) {
                null
            } else {
                if (value1 is Double && value2 is Double)
                    (value1 + value2) as N
                else if (value1 is Long && value2 is Long)
                    (value1 + value2) as N
                else
                    throw IllegalArgumentException("Unsupported plus argument types " + value1.javaClass + "," + value2.javaClass)
            }
        }


        /**
         * Null-safe multiply of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        fun average(vararg pe: Double?): Double? {
            val historicalPEs = pe.filterNotNull()
            if (historicalPEs.isEmpty()) {
                return null
            }
            return historicalPEs.sum() / historicalPEs.size
        }

        /**
         * Null-safe multiply of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        fun <N : Number> multiply(value1: N?, value2: N?): N? {
            return if (value1 == null || value2 == null) {
                return null
            } else {
                if (value1 is Double && value2 is Double)
                    (value1 * value2) as N
                else if (value1 is Long && value2 is Long)
                    (value1 * value2) as N
                else
                    throw IllegalArgumentException("Unsupported div argument types ${value1.javaClass}, ${value2.javaClass}")
            }
        }

        /**
         * Null-safe percentage multiplier of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        fun <N : Number> percent(value1: N?): N? {
            return if (value1 == null) {
                null
            } else {
                if (value1 is Double)
                    (value1 * 100) as N
                else if (value1 is Long)
                    (value1 * 100) as N
                else
                    throw IllegalArgumentException("Unsupported div argument types ${value1.javaClass}}")
            }
        }

        /**
         * Null-safe division of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        fun <N : Number> div(value1: N?, size: Int): N? {
            return if (value1 == null) null else {
                if (value1 is Double)
                    (value1 / size) as N
                else if (value1 is Long)
                    (value1 / size) as N
                else
                    throw IllegalArgumentException("Unsupported div argument types ${value1.javaClass}")
            }
        }

        /**
         * Null-safe Math.min
         */
        fun min(value1: Double?, value2: Double?): Double? {
            return when {
                value1 == null -> value2
                value2 == null -> value1
                else -> kotlin.math.min(value1, value2)
            }
        }

        /**
         * Null-safe Math.min skipping negative values
         */
        fun minIfPositive(value1: Double?, value2: Double?): Double? {
            return when {
                value1 == null || value1 < 0.0 -> value2
                value2 == null || value2 < 0.0 -> value1
                else -> kotlin.math.min(value1, value2)
            }
        }
    }

}