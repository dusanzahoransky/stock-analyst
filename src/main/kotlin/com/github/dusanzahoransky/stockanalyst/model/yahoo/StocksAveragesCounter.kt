package com.github.dusanzahoransky.stockanalyst.model.yahoo

import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo

data class StocksAveragesCounter(
    var averages: StockInfo,
    var priceCount: Int = 0,
    var changeCount: Int = 0,
    var belowTargetLowPricePercentCount: Int = 0,
    var belowTargetMedianPricePercentCount: Int = 0,
    var totalCashPerShareCount: Int = 0,
    var totalCashPerSharePercentCount: Int = 0,
    var totalDebtEquityCount: Int = 0,
    var revenueGrowthCount: Int = 0,
    var earningsGrowthPercentCount: Int = 0,
    var yoyQuarterlyRevenueGrowthPercentCount: Int = 0,
    var yoyQuarterlyEarningsGrowthPercentCount: Int = 0,
    var dilutedEarningPerShareCount: Int = 0,
    var week52ChangeCount: Int = 0,
    var week52LowCount: Int = 0,
    var week52HighCount: Int = 0,
    var week52AboveLowPercentCount: Int = 0,
    var week52BelowHighPercentCount: Int = 0,
    var marketCapCount: Int = 0,
    var enterpriseValueCount: Int = 0,
    var trailingPECount: Int = 0,
    var forwardPECount: Int = 0,
    var priceEarningGrowthCount: Int = 0,
    var priceSalesCount: Int = 0,
    var priceBookCount: Int = 0,
    var enterpriseValueRevenueCount: Int = 0,
    var enterpriseValueEBITDACount: Int = 0,
    var trailingPriceEarningGrowthCount: Int = 0,
    var priceToSalesTrailing12MonthsCount: Int = 0,
    var fiveYearAvgDividendYieldCount: Int = 0,
    var trailingAnnualDividendYieldCount: Int = 0,
    var shortToFloatCount: Int = 0,
    var sharesShortPrevMonthCompareCount: Int = 0
)