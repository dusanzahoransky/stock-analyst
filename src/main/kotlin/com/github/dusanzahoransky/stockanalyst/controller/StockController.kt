package com.github.dusanzahoransky.stockanalyst.controller

import com.github.dusanzahoransky.stockanalyst.model.dto.AnalysisResult
import com.github.dusanzahoransky.stockanalyst.model.dto.IndicesAnalysisResult
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("stocks")
class StockController @Autowired constructor(
    val keyRatiosAnalysisService: KeyRatiosAnalysisService,
    val keyRatiosTimelineService: KeyRatiosTimelineService,
    val stockAnalysisService: StockAnalysisService,
    val stockService: StockService,
    val indexService: IndexService
) {

    @GetMapping("watchlist")
    @ResponseBody
    fun loadWatchlist(
        @RequestParam(value = "watchlist") watchlist: Watchlist,
        @RequestParam(value = "forceRefresh", required = false) forceRefresh: Boolean = false,
        @RequestParam(value = "forceRefreshRatios", required = false) forceRefreshRatios: Boolean = false,
        @RequestParam(value = "mockData", required = false) mockData: Boolean = false
    ): AnalysisResult {
        val stocks = stockService.getWatchlistStocks(watchlist, forceRefresh, mockData)
        stockAnalysisService.calcStockStats(stocks)

        val ratios = keyRatiosTimelineService.getWatchlistKeyRatios(watchlist, forceRefreshRatios, mockData)
        keyRatiosAnalysisService.calcRule1(stocks, ratios)

        val averages = stockAnalysisService.calcStocksAverages(stocks)

        return AnalysisResult(averages, stockAnalysisService.combineWithRatios(stocks, ratios))
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

    //TODO
//    @GetMapping("symbols")
//    @ResponseBody
//    fun loadSymbol(
//        @RequestParam(value = "symbol") symbols: Array<String>,
//        @RequestParam(value = "forceRefresh", required = false) forceRefresh: Boolean = false,
//        @RequestParam(value = "mockData", required = false) mockData: Boolean = false
//    ): AnalysisResult {
//        val stocks = stockService.getStocks(symbols, forceRefresh, mockData)
//        val averages = stockAnalysisService.calcStocksAverages(stocks)
//        return AnalysisResult(averages, stocks)
//    }

    @DeleteMapping("symbol")
    @ResponseBody
    fun deleteSymbol(
        @RequestParam(value = "symbol") symbol: String
    ) {
        stockService.deleteSymbol(symbol)
    }

    @DeleteMapping("watchlist")
    @ResponseBody
    fun watchlist(
        @RequestParam(value = "watchlist") watchlist: Watchlist
    ) {
        stockService.deleteWatchlist(watchlist)
    }
}