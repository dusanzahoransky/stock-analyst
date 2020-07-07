package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.mongo.Stock

data class AnalysisResult(
    var averages: Stock,
    var stocks: List<Stock>
)