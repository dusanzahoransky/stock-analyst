package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.Ticker
import com.github.dusanzahoransky.stockanalyst.model.enums.WatchlistGroup
import com.github.dusanzahoransky.stockanalyst.model.enums.WatchlistTag
import com.github.dusanzahoransky.stockanalyst.model.mongo.Watchlist
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import com.github.dusanzahoransky.stockanalyst.util.PresetWatchlists
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class WatchlistService @Autowired constructor(
        val watchlistRepo: WatchlistRepo
) {
    val log = LoggerFactory.getLogger(this::class.java)!!
    val presetWatchlists = PresetWatchlists()

    fun loadWatchListOrGroup(watchlistName: String): Watchlist{
        if(isGroup(watchlistName)){
            log.debug("Loading watchlist group $watchlistName")
            val groupWatchlists = when(WatchlistGroup.valueOf(watchlistName)) {
                WatchlistGroup.ALL_INVESTED -> loadWatchListOrGroup(setOf(WatchlistTag.INVESTED_IN))
                WatchlistGroup.ETF_ALL -> loadWatchListOrGroup(setOf(WatchlistTag.ETF))
                WatchlistGroup.AU_ETF_ALL -> loadWatchListOrGroup(setOf(WatchlistTag.AU, WatchlistTag.ETF))
                WatchlistGroup.GB_ETF_ALL -> loadWatchListOrGroup(setOf(WatchlistTag.GB, WatchlistTag.ETF))
                WatchlistGroup.EU_ETF_ALL -> loadWatchListOrGroup(setOf(WatchlistTag.EU, WatchlistTag.ETF))
                WatchlistGroup.ETF_TRADING_212_INVESTED_IN -> loadWatchListOrGroup(setOf(WatchlistTag.TRADING_212, WatchlistTag.ETF, WatchlistTag.INVESTED_IN))
                WatchlistGroup.US_ALL -> loadWatchListOrGroup(setOf(WatchlistTag.US, WatchlistTag.STOCK))
                WatchlistGroup.EU_ALL -> loadWatchListOrGroup(setOf(WatchlistTag.EU, WatchlistTag.STOCK))
                WatchlistGroup.GB_ALL -> loadWatchListOrGroup(setOf(WatchlistTag.GB, WatchlistTag.STOCK))
                WatchlistGroup.AU_ALL -> loadWatchListOrGroup(setOf(WatchlistTag.AU, WatchlistTag.STOCK))
                WatchlistGroup.TRADING_212 -> loadWatchListOrGroup(setOf(WatchlistTag.TRADING_212))
            }
            val watchlistGroup = Watchlist(watchlistName)
            watchlistGroup.isGroup = true
            watchlistGroup.tickers = groupWatchlists.flatMap { watchlist -> watchlist.tickers }.toMutableSet()
            watchlistGroup.isEtf = groupWatchlists.all { watchlist -> watchlist.isEtf }
            return watchlistGroup
        }
        log.debug("Loading watchlist $watchlistName")
        return getByName(watchlistName)
    }
    fun loadWatchListOrGroup(tags: Set<WatchlistTag>): List<Watchlist>{
        return getAll().filter { watchlist -> watchlist.tags.containsAll(tags)}
    }

    fun isGroup(watchlistName: String): Boolean{
        return WatchlistGroup.values().any { group -> group.name == watchlistName }
    }

    fun getAll(): List<Watchlist> {
        return watchlistRepo.findAll()
    }

    fun getAllNames(includeGroups: Boolean): List<String> {
        val watchlists = getAll().map { watchlist -> watchlist.name }.toMutableList()
        if(includeGroups) {
            watchlists.addAll(WatchlistGroup.values().map { it.name })
        }
        return watchlists
    }

    fun getByName(watchlistName: String): Watchlist {
        return watchlistRepo.findById(watchlistName).orElse(null)
    }

    fun removeWatchlist(watchlistName: String) {
        watchlistRepo.deleteById(watchlistName)
    }

    fun save(watchlist: Watchlist): Watchlist {
        if(isGroup(watchlist.name)){
            throw IllegalArgumentException("Watchlist group $watchlist can not be persisted, only single watchlists can")
        }
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