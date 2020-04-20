package com.github.dusanzahoransky.stockanalyst.model.exchangerates

data class RatesResponse (
    val rates: Rates,
    val base: String
)