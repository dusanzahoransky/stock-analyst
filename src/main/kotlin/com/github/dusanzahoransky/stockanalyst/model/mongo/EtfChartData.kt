package com.github.dusanzahoransky.stockanalyst.model.mongo

data class EtfChartData(
    /**
     * Epoch sec
     */
    val date: Long,
    var price: Double? = null
)