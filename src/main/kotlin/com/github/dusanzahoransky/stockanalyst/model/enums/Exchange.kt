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

    fun toRegion(): String{
        return when(this){
            NASDAQ, NYSE -> "US"
            ASX -> "AX"
            DAX -> "DE"
            SIX -> "SW"
            FTSE -> "L"
            else -> throw IllegalArgumentException("Undefined $this toRegion conversion")
        }
    }
}