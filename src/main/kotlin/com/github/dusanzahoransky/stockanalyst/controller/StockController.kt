package com.github.dusanzahoransky.stockanalyst.controller

import com.github.dusanzahoransky.stockanalyst.model.dto.AnalysisResult
import com.github.dusanzahoransky.stockanalyst.model.dto.IndicesAnalysisResult
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.service.IndexService
import com.github.dusanzahoransky.stockanalyst.service.StockAnalysisService
import com.github.dusanzahoransky.stockanalyst.service.StockService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("stocks")
class StockController @Autowired constructor(
    val stockAnalysisService: StockAnalysisService,
    val stockService: StockService,
    val indexService: IndexService
) {

    @GetMapping("watchlist")
    @ResponseBody
    fun loadWatchlist(
        @RequestParam(value = "watchlist") watchlist: Watchlist,
        @RequestParam(value = "forceRefresh", required = false) forceRefresh: Boolean = false,
        @RequestParam(value = "mockData", required = false) mockData: Boolean = false
    ): AnalysisResult {
        val stocks = stockService.getWatchlistStocks(watchlist, forceRefresh, mockData)
        val averages = stockAnalysisService.calcStocksAverages(stocks)
        return AnalysisResult(averages, stocks)
    }

    @GetMapping("indicesWatchlist")
    @ResponseBody
    fun loadIndicesWatchlist(
        @RequestParam(value = "watchlist") watchlist: Watchlist,
        @RequestParam(value = "forceRefresh", required = false) forceRefresh: Boolean = false,
        @RequestParam(value = "mockData", required = false) mockData: Boolean = false
    ): IndicesAnalysisResult {
        val indices = indexService.getWatchlistStocks(watchlist, forceRefresh, mockData)
        val averages = stockAnalysisService.calcIndicesAverages(indices)
        return IndicesAnalysisResult(averages, indices)
    }

    @GetMapping("symbols")
    @ResponseBody
    fun loadSymbol(
        @RequestParam(value = "symbol") symbols: Array<String>,
        @RequestParam(value = "forceRefresh", required = false) forceRefresh: Boolean = false,
        @RequestParam(value = "mockData", required = false) mockData: Boolean = false
    ): AnalysisResult {
        val stocks = stockService.getStocks(symbols, forceRefresh, mockData)
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