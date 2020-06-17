package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.mongo.EtfInfo

data class EtfsAnalysisResult(
    var averages: EtfInfo,
    var stocks: List<EtfInfo>
)