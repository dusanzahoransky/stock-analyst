package com.github.dusanzahoransky.stockanalyst.model.mongo

data class StockChartData(
    /**
     * Epoch sec
     */
    val date: Long,
    var price: Double? = null,
    var epsQuarterly: Double? = null,
    var epsAnnually: Double? = null,
    var peQuarterly: Double? = null,
    var peAnnually: Double? = null
)