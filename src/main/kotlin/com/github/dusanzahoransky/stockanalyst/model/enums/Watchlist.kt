package com.github.dusanzahoransky.stockanalyst.model.enums

enum class Watchlist {
    //Test
    TEST,
    TEST_ETF,
    TO_CHECK,

    //All
    ALL_INVESTED,

    //ETFs
    ETF_ALL,

    //Australia
    AU_ETF_ALL,
    AU_ETF_AU,
    AU_ETF_US,
    AU_ETF_ASIA,
    AU_ETF_BOND,
    AU_ETF_INVESTED_IN,
    AU_ETF_WATCHLIST,

    //Great Britain
    GB_ETF_ALL,
    GB_ETF_BOND,
    GB_ETF_INVESTED_IN,
    GB_ETF_WATCHLIST,

    //European Union
    EU_ETF_ALL,
    EU_ETF_BOND,
    EU_ETF_INVESTED_IN,
    EU_ETF_WATCHLIST,

    ETF_TRADING_212_INVESTED_IN,

    //Stocks
    US_ALL,
    TECH,
    US_INVESTED_IN,
    US_WATCHLIST,
    NASDAQ_100,
    DIVIDENDS,

    EU_ALL,
    EU_INVESTED_IN,
    EU_WATCHLIST,

    GB_ALL,
    GB_INVESTED_IN,
    GB_WATCHLIST,

    AU_ALL,
    AU_WATCHLIST,
    AU_INVESTED_IN,

    CHF_ALL,
    CHF_WATCHLIST,

    AIRLINES,

    TRADING_212,
    TRADING_212_US,
    TRADING_212_EU,
    TRADING_212_GB;

    fun isEtf(): Boolean {
        return when (this) {
            ETF_ALL,
            GB_ETF_ALL,
            AU_ETF_ALL,
            AU_ETF_AU,
            AU_ETF_US,
            AU_ETF_ASIA,
            AU_ETF_BOND,
            AU_ETF_INVESTED_IN,
            AU_ETF_WATCHLIST,
            GB_ETF_BOND,
            GB_ETF_INVESTED_IN,
            GB_ETF_WATCHLIST,
            EU_ETF_ALL,
            EU_ETF_BOND,
            EU_ETF_INVESTED_IN,
            EU_ETF_WATCHLIST -> true
            else -> false
        }
    }
}