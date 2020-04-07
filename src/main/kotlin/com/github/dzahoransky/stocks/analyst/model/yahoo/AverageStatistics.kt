package com.github.dzahoransky.stocks.analyst.model.yahoo

data class AverageStatistics(
    var statistics: Statistics,
    var priceCount: Int = 0,
    var periodValuationMeasures: MutableMap<String, AveragePeriodMeasure> = mutableMapOf(),
    var totalCashPerShareCount: Int = 0,
    var totalDebtEquityCount: Int = 0,
    var quarterlyRevenueGrowthCount: Int = 0,
    var quarterlyEarningsGrowthCount: Int = 0,
    var dilutedEarningPerShareCount: Int = 0,
    var week52ChangeCount: Int = 0,
    var week52LowCount: Int = 0,
    var week52HighCount: Int = 0)