package com.github.dusanzahoransky.stockanalyst.controller


import com.github.dusanzahoransky.stockanalyst.model.mongo.Watchlist
import com.github.dusanzahoransky.stockanalyst.service.WatchlistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("watchlist")
class WatchlistController @Autowired constructor(
        val watchlistService: WatchlistService
) {
    @PutMapping("init")
    @ResponseBody
    fun init(): List<Watchlist> {
        return watchlistService.initPresetWatchlists()
    }

    @GetMapping
    @ResponseBody
    fun list(): List<Watchlist> {
        return watchlistService.getAll()
    }

    @GetMapping("names")
    @ResponseBody
    fun listNames(): List<String> {
        return watchlistService.getAllNames()
    }

    @GetMapping("{watchlistName}")
    @ResponseBody
    fun getByName(@PathVariable watchlistName: String): Watchlist {
        return watchlistService.getByName(watchlistName)
    }

    @PostMapping()
    fun createWatchlist(@RequestBody watchlist: Watchlist): Watchlist {
        return watchlistService.addWatchlist(watchlist)
    }

    @DeleteMapping("{watchlistName}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    fun deleteWatchlist(@PathVariable watchlistName: String) {
        watchlistService.removeWatchlist(watchlistName)
    }

    @PutMapping("{watchlistName}/add")
    @ResponseBody
    fun addTickers(@PathVariable watchlistName: String, @RequestBody tickers: Set<String>): Watchlist {
        return watchlistService.addTickersToWatchlist(watchlistName, tickers)
    }

    @PutMapping("{watchlistName}/remove")
    @ResponseBody
    fun removeTickers(@PathVariable watchlistName: String, @RequestBody tickers: Set<String>): Watchlist {
        return watchlistService.removeTickersFromWatchlist(watchlistName, tickers)
    }
}