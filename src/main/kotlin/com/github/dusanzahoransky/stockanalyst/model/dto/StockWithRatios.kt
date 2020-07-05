package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.mongo.StockDto
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatiosTimeline

class StockWithRatios(
    var stock: StockDto,
    var stockRatiosTimeline: StockRatiosTimeline
)