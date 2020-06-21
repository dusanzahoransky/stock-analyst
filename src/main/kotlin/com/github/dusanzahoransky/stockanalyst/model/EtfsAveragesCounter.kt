package com.github.dusanzahoransky.stockanalyst.model

import com.github.dusanzahoransky.stockanalyst.model.mongo.Etf

data class EtfsAveragesCounter(
    var averages: Etf,
    var changeCount: Int = 0,
    var totalAssetsCount: Int = 0,

    var yieldCount: Int = 0,

    var ytdReturnCount: Int = 0,
    var threeYearAverageReturnCount: Int = 0,
    var fiveYearAverageReturnCount: Int = 0,

    var priceToEarningsCount: Int = 0,
    var priceToBookCount: Int = 0,
    var priceToCashflowCount: Int = 0,
    var priceToSalesCount: Int = 0,

    var fiftyTwoWeekLowCount: Int = 0,
    var fiftyTwoWeekHighCount: Int = 0,
    var fiftyTwoAboveLowPercentCount: Int = 0,
    var fiftyTwoBelowHighPercentCount: Int = 0,

    var asOfDateCount: Int = 0,

    var oneMonthCount: Int = 0,
    var threeMonthCount: Int = 0,
    var oneYearCount: Int = 0,
    var threeYearCount: Int = 0,
    var ytdCount: Int = 0,
    var fiveYearCount: Int = 0,
    var tenYearCount: Int = 0,

    var lastBearMktCount: Int = 0,
    var lastBullMktCount: Int = 0,


    var annualHoldingsTurnoverCount: Int = 0,
    var annualReportExpenseRatioCount: Int = 0,

    var averageDailyVolume3MonthCount: Int = 0,
    var averageDailyVolume10DayCount: Int = 0,

    var fundInceptionDateCount: Int = 0

)