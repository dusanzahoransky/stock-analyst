package com.github.dusanzahoransky.stockanalyst.model

import java.time.LocalDate

interface LastRefreshDate {
    fun getLastRefreshDate(): LocalDate
}