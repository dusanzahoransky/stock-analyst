package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatios

data class Rule1Result(
    var ratios: List<StockRatios>
)