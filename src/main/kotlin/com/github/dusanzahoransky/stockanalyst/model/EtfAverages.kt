package com.github.dusanzahoransky.stockanalyst.model

import java.time.LocalDate
import java.util.*

data class EtfAverages(
    var totalAssets: Long? = null,

    var yield: Double? = null,

    var ytdReturn: Double? = null,
    var threeYearAverageReturn: Double? = null,
    var fiveYearAverageReturn: Double? = null,

    var priceToEarnings: Double? = null,
    var priceToBook: Double? = null,
    var priceToCashflow: Double? = null,
    var priceToSales: Double? = null,

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
)