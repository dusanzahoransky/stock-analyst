package com.github.dusanzahoransky.stockanalyst.model.enums

enum class Exchange {
    ASX,
    NASDAQ,
    NYSE,
    /**
     * British exchange
     */
    FTSE,
    /**
     * German exchange
     */
    DAX,

    /**
     * Swiss exchange
     */
    SIX,
    /**
     * French exchange
     */
    PA,
    ;

    fun toYahooFormat(): String{
        return when(this){
            NASDAQ, NYSE -> "US"
            ASX -> "AX"
            DAX -> "DE"
            SIX -> "SW"
            PA -> "PA"
            FTSE -> "L"
        }
    }
}