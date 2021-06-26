package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.EtfAverages
import com.github.dusanzahoransky.stockanalyst.model.mongo.Etf

data class EtfsAnalysisResult(
    var averages: EtfAverages,
    var etfs: List<Etf>
)