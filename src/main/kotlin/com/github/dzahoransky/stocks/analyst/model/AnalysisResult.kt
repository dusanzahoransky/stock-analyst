package com.github.dzahoransky.stocks.analyst.model

import com.github.dzahoransky.stocks.analyst.model.yahoo.Statistics

data class AnalysisResult(
    var statisticsAllPeriods: List<String>,
    var statisticsAverage: Statistics,
    var stocks: List<StockInfo>
)