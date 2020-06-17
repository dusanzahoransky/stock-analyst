package com.github.dusanzahoransky.stockanalyst.controller

import com.github.dusanzahoransky.stockanalyst.model.dto.AnalysisResult
import com.github.dusanzahoransky.stockanalyst.model.dto.EtfsAnalysisResult
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("stocks")
class StockController @Autowired constructor(
    val keyRatiosAnalysisService: KeyRatiosAnalysisService,
    val keyRatiosTimelineService: KeyRatiosTimelineService,
    val stockAnalysisService: StockAnalysisService,
    val stockService: StockService,
    val indexService: EtfService
) {

    @GetMapping("watchlist")
    @ResponseBody
    fun loadWatchlist(
        @RequestParam(value = "watchlist") watchlist: Watchlist,
        @RequestParam(value = "forceRefresh", required = false) forceRefresh: Boolean = false,
        @RequestParam(value = "forceRefreshRatios", required = false) forceRefreshRatios: Boolean = false,
        @RequestParam(value = "mockData", required = false) mockData: Boolean = false,
        @RequestParam(value = "forceRefreshDate", required = false) forceRefreshDate: String?
    ): AnalysisResult {

        val forceRefreshLocalDate = if(forceRefreshDate != null) LocalDate.parse(forceRefreshDate) else LocalDate.now()

        val stocks = stockService.getWatchlistStocks(watchlist, forceRefresh, mockData, forceRefreshLocalDate)
        stockAnalysisService.calcStockStats(stocks)

        val ratios = keyRatiosTimelineService.getWatchlistKeyRatios(watchlist, forceRefreshRatios, mockData, forceRefreshLocalDate)
        keyRatiosAnalysisService.calcRule1(stocks, ratios)

        val averages = stockAnalysisService.calcStocksAverages(stocks)

        return AnalysisResult(averages, stockAnalysisService.combineWithRatios(stocks, ratios))
    }

    @GetMapping("indicesWatchlist")
    @ResponseBody
    fun loadEtfsWatchlist(
        @RequestParam(value = "watchlist") watchlist: Watchlist,
        @RequestParam(value = "forceRefresh", required = false) forceRefresh: Boolean = false,
        @RequestParam(value = "mockData", required = false) mockData: Boolean = false
    ): EtfsAnalysisResult {
        val indices = indexService.getWatchlistStocks(watchlist, forceRefresh, mockData)
        val averages = stockAnalysisService.calcEtfsAverages(indices)
        return EtfsAnalysisResult(averages, indices)
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