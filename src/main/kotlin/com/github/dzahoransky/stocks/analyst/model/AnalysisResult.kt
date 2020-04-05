package com.github.dzahoransky.stocks.analyst.model

import com.github.dzahoransky.stocks.analyst.model.yahoo.Statistics

data class AnalysisResult (
    val stocks: StockInfo,
    val statisticsAverage: Statistics
)