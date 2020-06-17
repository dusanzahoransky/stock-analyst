package com.github.dusanzahoransky.stockanalyst.controller

import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatiosTimeline
import com.github.dusanzahoransky.stockanalyst.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("keyratios/financials")
class KeyRatiosFinancialsController @Autowired constructor(
    val keyRatiosTimelineService: KeyRatiosTimelineService
) {

    @GetMapping("watchlist")
    @ResponseBody
    fun loadWatchlist(
        @RequestParam(value = "watchlist") watchlist: Watchlist,
        @RequestParam(value = "forceRefresh", required = false) forceRefresh: Boolean = false,
        @RequestParam(value = "mockData", required = false) mockData: Boolean = false,
        @RequestParam(value = "forceRefreshDate", required = false) forceRefreshDate: String?
    ): List<StockRatiosTimeline> {
        val forceRefreshLocalDate = if(forceRefreshDate != null) LocalDate.parse(forceRefreshDate) else LocalDate.now()
        return keyRatiosTimelineService.getWatchlistKeyRatios(watchlist, forceRefresh, mockData, forceRefreshLocalDate)
    }

}