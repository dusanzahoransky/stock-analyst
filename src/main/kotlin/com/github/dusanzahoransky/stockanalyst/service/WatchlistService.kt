package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.mongo.Watchlist
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import com.github.dusanzahoransky.stockanalyst.util.PresetWatchlists
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WatchlistService @Autowired constructor(
        val watchlistRepo: WatchlistRepo
) {
    val log = LoggerFactory.getLogger(this::class.java)!!
    val presetWatchlists = PresetWatchlists()

    fun getAll(): List<Watchlist> {
        return watchlistRepo.findAll()
    }

    fun getAllNames(): List<String> {
        return getAll().map { watchlist -> watchlist.name }
    }

    fun getByName(watchlistName: String): Watchlist {
        return watchlistRepo.findById(watchlistName).orElse(null)
    }

    fun save(watchlist: Watchlist): Watchlist {
        return watchlistRepo.save(watchlist)
    }

    fun addTickersToWatchlist(watchlistName: String, tickers: Set<String>): Watchlist {
        val watchlist = watchlistRepo.findById(watchlistName).orElseThrow()
        watchlist.tickers.addAll(tickers)
        return watchlistRepo.save(watchlist)
    }

    fun removeTickersToWatchlist(watchlistName: String, tickers: Set<String>): Watchlist {
        val watchlist = watchlistRepo.findById(watchlistName).orElseThrow()
        watchlist.tickers.removeAll(tickers)
        return watchlistRepo.save(watchlist)
    }

    fun initPresetWatchlists(): List<Watchlist> {
        val createdWatchlists = mutableListOf<Watchlist>()

        createdWatchlists.add(save(presetWatchlists.test()))
        createdWatchlists.add(save(presetWatchlists.testEtfs()))
        createdWatchlists.add(save(presetWatchlists.toCheck()))
        createdWatchlists.add(save(presetWatchlists.etfsAll()))
        createdWatchlists.add(save(presetWatchlists.auEtfsAll()))
        createdWatchlists.add(save(presetWatchlists.auEtfsAu()))
        createdWatchlists.add(save(presetWatchlists.auEtfsUs()))
        createdWatchlists.add(save(presetWatchlists.auEtfsAsia()))
        createdWatchlists.add(save(presetWatchlists.auEtfsBond()))
        createdWatchlists.add(save(presetWatchlists.auEtfsInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.auEtfsWatchlist()))
        createdWatchlists.add(save(presetWatchlists.gbEtfsAll()))
        createdWatchlists.add(save(presetWatchlists.gbEtfsBonds()))
        createdWatchlists.add(save(presetWatchlists.gbEtfsInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.gbEtfsWatchlist()))
        createdWatchlists.add(save(presetWatchlists.euEtfsAll()))
        createdWatchlists.add(save(presetWatchlists.euEtfsBondEtfs()))
        createdWatchlists.add(save(presetWatchlists.euEtfsInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.euEtfsWatchlist()))
        createdWatchlists.add(save(presetWatchlists.etfTrading212InvestedIn()))
        createdWatchlists.add(save(presetWatchlists.invested()))
        createdWatchlists.add(save(presetWatchlists.us()))
        createdWatchlists.add(save(presetWatchlists.usInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.usWatchList()))
        createdWatchlists.add(save(presetWatchlists.eu()))
        createdWatchlists.add(save(presetWatchlists.euInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.euWatchList()))
        createdWatchlists.add(save(presetWatchlists.gb()))
        createdWatchlists.add(save(presetWatchlists.gbInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.gbWatchlist()))
        createdWatchlists.add(save(presetWatchlists.au()))
        createdWatchlists.add(save(presetWatchlists.auWatchlist()))
        createdWatchlists.add(save(presetWatchlists.auInvestedIn()))
        createdWatchlists.add(save(presetWatchlists.chf()))
        createdWatchlists.add(save(presetWatchlists.chfWatchlist()))
        createdWatchlists.add(save(presetWatchlists.airlines()))
        createdWatchlists.add(save(presetWatchlists.tech()))
        createdWatchlists.add(save(presetWatchlists.investedInTech()))
        createdWatchlists.add(save(presetWatchlists.watchListTech()))
        createdWatchlists.add(save(presetWatchlists.nasdaq100()))
        createdWatchlists.add(save(presetWatchlists.dividends()))
        createdWatchlists.add(save(presetWatchlists.allTrading212()))
        createdWatchlists.add(save(presetWatchlists.allTrading212Us()))
        createdWatchlists.add(save(presetWatchlists.allTrading212Eu()))
        createdWatchlists.add(save(presetWatchlists.allTrading212Gb()))

        return createdWatchlists
    }



}