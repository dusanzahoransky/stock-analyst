package com.github.dusanzahoransky.stockanalyst.controller

import com.github.dusanzahoransky.stockanalyst.model.dto.AnalysisResult
import com.github.dusanzahoransky.stockanalyst.model.dto.IndicesAnalysisResult
import com.github.dusanzahoransky.stockanalyst.model.dto.Rule1Result
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.KeyRatiosFinancials
import com.github.dusanzahoransky.stockanalyst.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("keyratios/financials")
class KeyRatiosFinancialsController @Autowired constructor(
    val keyRatiosFinancialsService: KeyRatiosFinancialsService,
    val keyRatiosAnalysisService: KeyRatiosAnalysisService
) {

    @GetMapping("watchlist")
    @ResponseBody
    fun loadWatchlist(
        @RequestParam(value = "watchlist") watchlist: Watchlist,
        @RequestParam(value = "forceRefresh", required = false) forceRefresh: Boolean = false,
        @RequestParam(value = "mockData", required = false) mockData: Boolean = false
    ): Rule1Result {
        val krf = keyRatiosFinancialsService.getWatchlistRatios(watchlist, forceRefresh, mockData)
        return keyRatiosAnalysisService.calcRule1(krf)
    }

}