package com.github.dusanzahoransky.stockanalyst.model.mongo

import java.time.LocalDate

interface LastRefreshDate {
    fun getLastRefreshDate(): LocalDate
}