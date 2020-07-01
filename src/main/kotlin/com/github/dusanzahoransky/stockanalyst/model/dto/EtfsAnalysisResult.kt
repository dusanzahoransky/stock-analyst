package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.mongo.Etf

data class EtfsAnalysisResult(
    var averages: Etf,
    var stocks: List<Etf>
)