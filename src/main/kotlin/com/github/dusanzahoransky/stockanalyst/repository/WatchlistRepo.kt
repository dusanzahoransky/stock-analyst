package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.mongo.Watchlist
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface WatchlistRepo : MongoRepository<Watchlist, String> {

}
