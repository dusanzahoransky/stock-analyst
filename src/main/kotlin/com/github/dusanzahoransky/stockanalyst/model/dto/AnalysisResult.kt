package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo

data class AnalysisResult(
    var averages: StockInfo,
    var stocks: List<StockInfo>
)