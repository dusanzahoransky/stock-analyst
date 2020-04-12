package com.github.dzahoransky.stocks.analyst.model.yahoo

data class PeriodMeasure(
    var period: String,
    var marketCap: Long?,
    var enterpriseValue: Long?,
    var trailingPE: Double?,
    var forwardPE: Double?,
    var priceEarningGrowth: Double?,
    var priceSales: Double?,
    var priceBook: Double?,
    var enterpriseValueRevenue: Double?,
    var enterpriseValueEBITDA: Double?
){
    constructor(): this("", null, null, null, null, null, null, null, null, null)

}
