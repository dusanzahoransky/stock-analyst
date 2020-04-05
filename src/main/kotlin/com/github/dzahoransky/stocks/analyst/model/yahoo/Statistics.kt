package com.github.dzahoransky.stocks.analyst.model.yahoo

data class Statistics(
    var periodValuationMeasures: Map<String, PeriodMeasure>
)