package com.github.dusanzahoransky.stockanalyst.model.enums

enum class Mic(
    val fullName: String,
    val value: String) {

    ASX("ASX", "XASX"),
    NASDAQ("NASDAQ", "XNAS"),
    NYSE("NYSE", "XNYS"),
    FTSE("London Stock Exchange", "XLON"),
    DAX("Deutsche Börse Xetra", "XETR"),
    ENX("Euronext Netherlands", "XBRU"),
    SIX("SIX Swiss", "XSWX"),
    PA("Euronext Paris", "XPAR"),
    MCE("Bolsa de Madrid", "XMAD");
}