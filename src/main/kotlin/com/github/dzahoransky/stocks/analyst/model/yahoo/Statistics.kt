package com.github.dzahoransky.stocks.analyst.model.yahoo

data class Statistics(
    var companyName: String? = "",
    var price: Double? = 0.0,
    var change: String? = "",
    var periodValuationMeasures: MutableMap<String, PeriodMeasure> = mutableMapOf(),
    var totalCashPerShare: Double? = 0.0,
    var totalDebtEquity: Double? = 0.0,
    var quarterlyRevenueGrowth: Double? = 0.0,
    var quarterlyEarningsGrowth: Double? = 0.0,
    var dilutedEarningPerShare: Double? = 0.0,
    var week52Change: Double? = 0.0,
    var week52Low: Double? = 0.0,
    var week52High: Double? = 0.0)