package com.github.dusanzahoransky.stockanalyst.model.mongo

data class ChartData (
    /**
     * Epoch sec
     */
    val date: Long,
    var price: Double? = null,
    var eps: Double? = null
)