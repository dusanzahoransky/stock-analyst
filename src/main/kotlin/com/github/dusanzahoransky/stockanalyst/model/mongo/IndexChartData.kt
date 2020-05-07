package com.github.dusanzahoransky.stockanalyst.model.mongo

data class IndexChartData(
    /**
     * Epoch sec
     */
    val date: Long,
    var price: Double? = null
)