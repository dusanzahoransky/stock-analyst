package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.*

@Document("etfInfo")
data class Etf(
    @Id var id: String? = null,
    val symbol: String,
    val exchange: Exchange? = null,

    //last updated timestamp
    var chartLastUpdated: LocalDate = LocalDate.now(),
    var statisticsLastUpdated: LocalDate = LocalDate.now(),

    var asOfDate: LocalDate? = null,

    var companyName: String? = null,
    var currency: Currency? = null,
    var totalAssets: SortedMap<LocalDate, Long?> = TreeMap(),

    var yield: SortedMap<LocalDate, Double?> = TreeMap(),

    var ytdReturn: SortedMap<LocalDate, Double?> = TreeMap(),
    var threeYearAverageReturn: SortedMap<LocalDate, Double?> = TreeMap(),
    var fiveYearAverageReturn: SortedMap<LocalDate, Double?> = TreeMap(),

    var priceToEarnings: SortedMap<LocalDate, Double?> = TreeMap(),
    var priceToBook: SortedMap<LocalDate, Double?> = TreeMap(),
    var priceToCashflow: SortedMap<LocalDate, Double?> = TreeMap(),
    var priceToSales: SortedMap<LocalDate, Double?> = TreeMap(),

    var oneMonth: SortedMap<LocalDate, Double?> = TreeMap(),
    var threeMonth: SortedMap<LocalDate, Double?> = TreeMap(),
    var ytd: SortedMap<LocalDate, Double?> = TreeMap(),
    var oneYear: SortedMap<LocalDate, Double?> = TreeMap(),
    var threeYear: SortedMap<LocalDate, Double?> = TreeMap(),
    var fiveYear: SortedMap<LocalDate, Double?> = TreeMap(),
    var tenYear: SortedMap<LocalDate, Double?> = TreeMap(),

    var lastBearMkt: SortedMap<LocalDate, Double?> = TreeMap(),
    var lastBullMkt: SortedMap<LocalDate, Double?> = TreeMap(),


    var annualHoldingsTurnover: SortedMap<LocalDate, Double?> = TreeMap(),
    var annualReportExpenseRatio: SortedMap<LocalDate, Double?> = TreeMap(),

    var averageDailyVolume3Month: SortedMap<LocalDate, Long?> = TreeMap(),
    var averageDailyVolume10Day: SortedMap<LocalDate, Long?> = TreeMap(),

    var fundInceptionDate: LocalDate? = null,

    var price: SortedMap<LocalDate, Double?> = TreeMap()
)