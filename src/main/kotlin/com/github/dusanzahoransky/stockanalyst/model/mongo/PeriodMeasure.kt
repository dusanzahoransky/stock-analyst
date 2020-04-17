package com.github.dusanzahoransky.stockanalyst.model.yahoo

data class PeriodMeasure(
    var period: String,
    var marketCap: Long? = null,
    var enterpriseValue: Long? = null,
    var trailingPE: Double? = null,
    var forwardPE: Double? = null,
    var priceEarningGrowth: Double? = null,
    var priceSales: Double? = null,
    var priceBook: Double? = null,
    var enterpriseValueRevenue: Double? = null,
    var enterpriseValueEBITDA: Double? = null
)
