package com.github.dusanzahoransky.stockanalyst.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.*
import java.time.LocalDate

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
interface LastRefreshDate {
    fun getLastRefreshDate(): LocalDate
}