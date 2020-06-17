package com.github.dusanzahoransky.stockanalyst.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class FormattingUtils {
    companion object {

        fun localDateToEpochSec(currentInterval: LocalDate) =
            currentInterval.atStartOfDay(ZoneId.of("UTC")).toEpochSecond()

        fun epochSecToLocalDate(epochSeconds: Long) =
            Instant.ofEpochSecond(epochSeconds).atZone(ZoneId.of("UTC")).toLocalDate()
    }
}