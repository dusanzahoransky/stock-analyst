package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.github.dusanzahoransky.stockanalyst.model.LastRefreshDate
import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.yahoo.holders.HoldersResponse
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

/**
 * Raw data cached from YahooFinance API
 */
@Document
data class Holders(
    @Id var id: String? = null,
    val symbol: String,
    val exchange: Exchange,
    val date: LocalDate = LocalDate.now(),
    var response: HoldersResponse
) : LastRefreshDate {
    override fun getLastRefreshDate(): LocalDate {
        return date
    }
}