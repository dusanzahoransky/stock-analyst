package com.github.dusanzahoransky.stockanalyst.controller

import com.github.dusanzahoransky.stockanalyst.model.dto.AnalysisResult
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.service.StockAnalysisService
import com.github.dusanzahoransky.stockanalyst.service.StockService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping( "stocks")
class StockController @Autowired constructor(
    val stockAnalysisService: StockAnalysisService,
    val stockService: StockService) {

    @GetMapping("watchlist")
    @ResponseBody
    fun loadWatchlist(
        @RequestParam(value = "watchlist") watchlist: Watchlist,
        @RequestParam(value = "forceRefresh", required = false) forceRefresh: Boolean = false) : AnalysisResult{
        val stocks = stockService.getWatchlistStocks(watchlist, forceRefresh)
        val averages = stockAnalysisService.calcStocksAverages(stocks)
        return AnalysisResult(averages, stocks)
    }

    @DeleteMapping("symbol")
    @ResponseBody
    fun deleteSymbol(
        @RequestParam(value = "symbol") symbol: String
    ) {
        val stocks = stockService.deleteSymbol(symbol)
    }

    @DeleteMapping("watchlist")
    @ResponseBody
    fun watchlist(
        @RequestParam(value = "watchlist") watchlist: Watchlist
    ) {
        val stocks = stockService.deleteWatchlist(watchlist)
    }
}