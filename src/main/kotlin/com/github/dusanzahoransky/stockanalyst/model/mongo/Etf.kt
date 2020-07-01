package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.github.dusanzahoransky.stockanalyst.model.LastRefreshDate
import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document("etfInfo")
data class Etf(
    @Id var id: String? = null,
    val date: LocalDate = LocalDate.now(),
    var asOfDate: LocalDate? = null,
    val symbol: String,
    val exchange: Exchange? = null,
    var companyName: String? = null,
    var price: Double? = null,
    var currency: Currency? = null,
    var change: Double? = null,
    var totalAssets: Long? = null,

    var yield: Double? = null,

    var ytdReturn: Double? = null,
    var threeYearAverageReturn: Double? = null,
    var fiveYearAverageReturn: Double? = null,

    var priceToEarnings: Double? = null,
    var priceToBook: Double? = null,
    var priceToCashflow: Double? = null,
    var priceToSales: Double? = null,

    var fiftyTwoWeekLow: Double? = null,
    var fiftyTwoWeekHigh: Double? = null,
    var fiftyTwoAboveLowPercent: Double? = null,
    var fiftyTwoBelowHighPercent: Double? = null,

    var oneMonth: Double? = null,
    var threeMonth: Double? = null,
    var ytd: Double? = null,
    var oneYear: Double? = null,
    var threeYear: Double? = null,
    var fiveYear: Double? = null,
    var tenYear: Double? = null,

    var lastBearMkt: Double? = null,
    var lastBullMkt: Double? = null,


    var annualHoldingsTurnover: Double? = null,
    var annualReportExpenseRatio: Double? = null,

    var averageDailyVolume3Month: Long? = null,
    var averageDailyVolume10Day: Long? = null,

    var fundInceptionDate: LocalDate? = null,

    var chartData: MutableList<EtfChartData>? = mutableListOf()

    /*
    risk
     */

): LastRefreshDate {
    override fun getLastRefreshDate(): LocalDate {
        return date
    }
}

data class EtfChartData(
    /**
     * Epoch sec
     */
    val date: Long,
    var price: Double? = null
)