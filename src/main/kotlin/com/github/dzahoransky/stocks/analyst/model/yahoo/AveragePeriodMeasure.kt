package com.github.dzahoransky.stocks.analyst.model.yahoo

data class AveragePeriodMeasure(
    var periodMeasure: PeriodMeasure,
    var periodCount: Int = 0,
    var marketCapCount: Int = 0,
    var enterpriseValueCount: Int = 0,
    var trailingPECount: Int = 0,
    var forwardPECount: Int = 0,
    var priceEarningGrowthCount: Int = 0,
    var priceSalesCount: Int = 0,
    var priceBookCount: Int = 0,
    var enterpriseValueRevenueCount: Int = 0,
    var enterpriseValueEBITDACount: Int = 0

)

