package com.github.dusanzahoransky.stockanalyst.model.enums

enum class Watchlist {
    AIRLINES,
    USD_DIVIDENDS,
    TEST,
    TEST_INDICES,
    INVESTED,
    EUR,
    AUD,
    AUD_INDICES,
    USD,
    USD_TECH,
    GBP,
    GBP_INDICES,
    CHF,
    INVESTED_IN_EUR,
    INVESTED_IN_GBP,
    INVESTED_INDICES_IN_GBP,
    WATCH_LIST_INDICES_GBP,
    INVESTED_IN_USD,
    WATCH_LIST_USD,
    WATCH_LIST_EUR,
    WATCH_LIST_CHF,
    INVESTED_IN_CHF,
    WATCH_LIST_GBP,
    WATCH_LIST_AUD,
    WATCH_LIST_INDICES_AUD,
    INVESTED_INDICES_IN_AUD,
    INVESTED_IN_AUD,
    NASDAQ_100,
    TRADING_212,
    TRADING_212_US,
    TRADING_212_EUR,
    TRADING_212_GBP;

    fun isIndex(): Boolean {
        return when (this) {
            AUD_INDICES,
            GBP_INDICES,
            INVESTED_INDICES_IN_GBP,
            WATCH_LIST_INDICES_GBP,
            INVESTED_INDICES_IN_AUD,
            WATCH_LIST_INDICES_AUD -> true
            else -> false
        }
    }
}