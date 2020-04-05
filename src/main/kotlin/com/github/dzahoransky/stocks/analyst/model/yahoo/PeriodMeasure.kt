package com.github.dzahoransky.stocks.analyst.model.yahoo

data class PeriodMeasure(
    val period: String,
    val marketCap: String,
    val enterpriseValue: String,
    val trailingPE: Float,
    val forwardPE: Float,
    val priceEarningGrowth: Float,
    val priceSales: Float,
    val priceBook: Float,
    val enterpriseValueRevenue: Float,
    val enterpriseValueEBITDA: Float
)
