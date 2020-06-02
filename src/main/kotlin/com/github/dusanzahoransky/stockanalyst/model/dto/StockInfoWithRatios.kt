package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatiosTimeline

class StockInfoWithRatios (
    var stockInfo: StockInfo,
    var stockRatiosTimeline: StockRatiosTimeline
)