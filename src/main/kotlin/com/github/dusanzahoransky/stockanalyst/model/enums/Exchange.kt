package com.github.dusanzahoransky.stockanalyst.model.enums

enum class Exchange(
    val fullName: String,
    val yahooFormat: String) {

    ASX("ASX", "AX"),
    NASDAQ("NASDAQ", "US"),
    NYSE("NYSE", "US"),
    FTSE("London Stock Exchange", "L"),
    DAX("Deutsche Börse Xetra", "DE"),
    ENX("Euronext Netherlands", "AS"),
    SIX("SIX Swiss", "SW"),
    PA("Euronext Paris", "PA"),
    MCE("Bolsa de Madrid", "MC")
}