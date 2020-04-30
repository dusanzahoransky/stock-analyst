package com.github.dusanzahoransky.stockanalyst.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CalcUtils {

    companion object {

        private val log: Logger = LoggerFactory.getLogger(CalcUtils::class.java)

        fun <N : Number> percentGrowth(currentValue: N?, previousValue: N?, statName: String, signThreshold: Double = 100.0): Double? {
            if (currentValue == null || previousValue == null) {
                return null
            }
            val currValueD = currentValue.toDouble()
            val prevValueD = previousValue.toDouble()
            if (prevValueD < signThreshold) {   //the previous value is too small to get any meaningful result
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
                    throw IllegalArgumentException("Unsupported sum argument types")
            }
        }

        /**
         * Null-safe division of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        fun <N : Number> div(value1: N?, value2: N?): N? {
            return if (value1 == null || value2 == null) {
                null
            } else {
                if (value1 is Double && value2 is Double)
                    (value1 / value2) as N
                else if (value1 is Long && value2 is Long)
                    (value1 / value2) as N
                else
                    throw IllegalArgumentException("Unsupported div argument types")
            }
        }

        /**
         * Null-safe division of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        fun <N : Number> minus(value1: N?, value2: N?): N? {
            return if (value1 == null) {
                if (value2 is Double)
                    -value2 as N
                else if (value2 is Long)
                    -value2 as N
                else
                    throw IllegalArgumentException("Unsupported minus argument types")
            } else if (value2 == null) {
                if (value1 is Double)
                    -value1 as N
                else if (value1 is Long)
                    -value1 as N
                else
                    throw IllegalArgumentException("Unsupported minus argument types")
            } else {
                if (value1 is Double && value2 is Double)
                    (value1 - value2) as N
                else if (value1 is Long && value2 is Long)
                    (value1 - value2) as N
                else
                    throw IllegalArgumentException("Unsupported minus argument types")
            }
        }


        /**
         * Null-safe multiply of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        public fun <N : Number> multiply(value1: N?, value2: N?): N? {
            return if (value1 == null || value2 == 0) {
                return null
            } else {
                if (value1 is Double && value2 is Double)
                    (value1 * value2) as N
                else if (value1 is Long && value2 is Long)
                    (value1 * value2) as N
                else
                    throw IllegalArgumentException("Unsupported div argument types")
            }
        }

        /**
         * Null-safe percentage multiplier of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        public fun <N : Number> percent(value1: N?): N? {
            return if (value1 == null) {
                null
            } else {
                if (value1 is Double)
                    (value1 * 100) as N
                else if (value1 is Long)
                    (value1 * 100) as N
                else
                    throw IllegalArgumentException("Unsupported div argument types")
            }
        }

        /**
         * Null-safe division of nullable Numbers
         */
        @Suppress("UNCHECKED_CAST")
        public fun <N : Number> div(value1: N?, size: Int): N? {
            return if (value1 == null) value1 else {
                if (value1 is Double)
                    (value1 / size) as N
                else if (value1 is Long)
                    (value1 / size) as N
                else
                    throw IllegalArgumentException("Unsupported div argument types")
            }
        }


    }

}