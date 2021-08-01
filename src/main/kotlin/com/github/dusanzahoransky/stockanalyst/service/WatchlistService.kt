package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.Ticker
import com.github.dusanzahoransky.stockanalyst.model.mongo.Watchlist
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import com.github.dusanzahoransky.stockanalyst.util.PresetWatchlists
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WatchlistService @Autowired constructor(
        val watchlistRepo: WatchlistRepo
) {
    val presetWatchlists = PresetWatchlists()

    fun getAll(): List<Watchlist> {
        return watchlistRepo.findAll().sortedBy { w -> w.name }
    }

    fun getAllNames(): List<String> {
        return getAll().map { watchlist -> watchlist.name }.sorted()
    }

    fun getByName(watchlistName: String): Watchlist {
        return watchlistRepo.findById(watchlistName).orElse(null)
    }

    fun removeWatchlist(watchlistName: String) {
        watchlistRepo.deleteById(watchlistName)
    }

    fun addWatchlist(watchlist: Watchlist): Watchlist {
        val existingWatchlist = watchlistRepo.findById(watchlist.name)
        if( existingWatchlist.isEmpty){
            watchlistRepo.save(watchlist)
        }
        return watchlist
    }

    fun save(watchlist: Watchlist): Watchlist {
        return watchlistRepo.save(watchlist)
    }

    fun addTickersToWatchlist(watchlistName: String, tickers: Set<String>): Watchlist {
        val watchlist = watchlistRepo.findById(watchlistName).orElseThrow()
        tickers.forEach { validate(it) }
        watchlist.tickers.addAll(tickers)
        return watchlistRepo.save(watchlist)
    }

    fun removeTickersFromWatchlist(watchlistName: String, tickers: Set<String>): Watchlist {
        val watchlist = watchlistRepo.findById(watchlistName).orElseThrow()
        watchlist.tickers.removeAll(tickers)
        return watchlistRepo.save(watchlist)
    }

    private fun validate(it: String) {
        Ticker.fromString(it)
    }

    fun removeTickersToWatchlist(watchlistName: String, tickers: Set<String>): Watchlist {
        val watchlist = watchlistRepo.findById(watchlistName).orElseThrow()
        watchlist.tickers.removeAll(tickers)
        return watchlistRepo.save(watchlist)
    }

    @Deprecated("use dynamic watchlists")
    fun initPresetWatchlists(): List<Watchlist> {
        val createdWatchlists = mutableListOf<Watchlist>()

        watchlistRepo.deleteAll()

        createdWatchlists.add(save(presetWatchlists.toCheck()))
        createdWatchlists.add(save(presetWatchlists.auEtfsAu()))
        createdWatchlists.add(save(presetWatchlists.auEtfsUs()))
        createdWatchlists.add(save(presetWatchlists.auEtfsAsia()))
        createdWatchlists.add(save(presetWatchlists.auEtfsBond()))
        createdWatchlists.add(save(presetWatchlists.auEtfsInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.auEtfsWatchlist()))
        createdWatchlists.add(save(presetWatchlists.gbEtfsBonds()))
        createdWatchlists.add(save(presetWatchlists.gbEtfsInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.gbEtfsWatchlist()))
        createdWatchlists.add(save(presetWatchlists.euEtfsBondEtfs()))
        createdWatchlists.add(save(presetWatchlists.euEtfsInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.euEtfsWatchlist()))
        createdWatchlists.add(save(presetWatchlists.usInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.usWatchList()))
        createdWatchlists.add(save(presetWatchlists.euInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.euWatchList()))
        createdWatchlists.add(save(presetWatchlists.gbInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.gbWatchlist()))
        createdWatchlists.add(save(presetWatchlists.auWatchlist()))
        createdWatchlists.add(save(presetWatchlists.auInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.airlines()))
        createdWatchlists.add(save(presetWatchlists.tech()))
        createdWatchlists.add(save(presetWatchlists.investedInTech()))
        createdWatchlists.add(save(presetWatchlists.watchListTech()))
        createdWatchlists.add(save(presetWatchlists.nasdaq100()))
        createdWatchlists.add(save(presetWatchlists.dividends()))
        createdWatchlists.add(save(presetWatchlists.allTrading212Us()))
        createdWatchlists.add(save(presetWatchlists.allTrading212Eu()))
        createdWatchlists.add(save(presetWatchlists.allTrading212Gb()))

        return createdWatchlists
    }



}