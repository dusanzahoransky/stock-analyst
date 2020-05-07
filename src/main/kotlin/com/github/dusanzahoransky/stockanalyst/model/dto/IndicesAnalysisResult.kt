package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.mongo.IndexInfo

data class IndicesAnalysisResult(
    var averages: IndexInfo,
    var stocks: List<IndexInfo>
)