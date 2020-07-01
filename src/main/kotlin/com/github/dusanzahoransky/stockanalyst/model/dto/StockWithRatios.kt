package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.mongo.Stock
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatiosTimeline

class StockWithRatios(
    var stock: Stock,
    var stockRatiosTimeline: StockRatiosTimeline
)