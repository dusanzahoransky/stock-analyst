package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.mongo.StockDto

data class AnalysisResult(
    var averages: StockDto,
    var stocks: List<StockWithRatios>
)