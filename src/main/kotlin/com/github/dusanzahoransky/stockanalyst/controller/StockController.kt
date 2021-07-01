package com.github.dusanzahoransky.stockanalyst.controller

import com.github.dusanzahoransky.stockanalyst.model.dto.AnalysisResult
import com.github.dusanzahoransky.stockanalyst.model.dto.EtfsAnalysisResult
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.Stock
import com.github.dusanzahoransky.stockanalyst.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("stocks")
class StockController @Autowired constructor(
    val stockAnalysisService: StockAnalysisService,
    val stockService: StockService,
    val indexService: EtfService
) {

    @GetMapping("watchlist")
    @ResponseBody
    fun loadWatchlist(
        @RequestParam(value = "watchlist") watchlist: String,
        @RequestParam(value = "refreshDynamicData", required = false) refreshDynamicData: Boolean = false,
        @RequestParam(value = "refreshFinancials", required = false) refreshFinancials: Boolean = false,
        @RequestParam(value = "mockData", required = false) mockData: Boolean = false,
        @RequestParam(value = "refreshOlderThan", required = false) refreshOlderThan: String?
    ): List<Stock> {

        val forceRefreshLocalDate = if (refreshOlderThan != null) LocalDate.parse(refreshOlderThan) else LocalDate.now()

        return stockService.getWatchlistStocks(watchlist, refreshDynamicData, refreshFinancials, mockData, forceRefreshLocalDate)
    }

    @GetMapping("etfWatchlist")
    @ResponseBody
    fun loadEtfsWatchlist(
        @RequestParam(value = "watchlist") watchlist: String,
        @RequestParam(value = "refreshDynamicData", required = false) refreshDynamicData: Boolean = false,
        @RequestParam(value = "mockData", required = false) mockData: Boolean = false,
        @RequestParam(value = "refreshOlderThan", required = false) refreshOlderThan: String?
    ): EtfsAnalysisResult {
        val forceRefreshLocalDate = if (refreshOlderThan != null) LocalDate.parse(refreshOlderThan) else LocalDate.now()

        val indices = indexService.getWatchlistEtfs(watchlist, mockData, refreshDynamicData, forceRefreshLocalDate)
        val averages = stockAnalysisService.calcEtfsAverages(indices)
        return EtfsAnalysisResult(averages, indices)
    }
}