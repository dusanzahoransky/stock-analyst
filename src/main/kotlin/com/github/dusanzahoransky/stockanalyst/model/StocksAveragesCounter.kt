package com.github.dusanzahoransky.stockanalyst.model

import com.github.dusanzahoransky.stockanalyst.model.mongo.Stock

data class StocksAveragesCounter(
    var averages: Stock,

    var changeCount: Int = 0,
    var enterpriseValueCount: Int = 0,

    var totalCashPerShareCount: Int = 0,
    var totalCashPerSharePercentCount: Int = 0,
    var totalDebtEquityCount: Int = 0,

    var trailingPECount: Int = 0,
    var forwardPECount: Int = 0,
    var priceToSalesTrailing12MonthsCount: Int = 0,
    var priceBookCount: Int = 0,
    var enterpriseValueRevenueCount: Int = 0,
    var enterpriseValueEBITDACount: Int = 0,

    var priceEarningGrowthCount: Int = 0,
    var trailingPriceEarningGrowthCount: Int = 0,


    var week52ChangeCount: Int = 0,
    var week52LowCount: Int = 0,
    var week52AboveLowPercentCount: Int = 0,
    var week52HighCount: Int = 0,
    var week52BelowHighPercentCount: Int = 0,


    var targetLowPriceCount: Int = 0,
    var belowTargetLowPricePercentCount: Int = 0,
    var targetMedianPriceCount: Int = 0,
    var belowTargetMedianPricePercentCount: Int = 0,


    var heldByInsidersCount: Int = 0,
    var heldByInstitutionsCount: Int = 0,
    var shortToFloatCount: Int = 0,
    var sharesShortPrevMonthCompareCount: Int = 0,


    var exDividendDateCount: Int = 0,
    var fiveYearAvgDividendYieldCount: Int = 0,
    var trailingAnnualDividendYieldCount: Int = 0,
    var payoutRatioCount: Int = 0,


    var revenueLastQuarterCount: Int = 0,
    var revenue2QuartersAgoCount: Int = 0,
    var revenue3QuartersAgoCount: Int = 0,
    var revenueLastYearCount: Int = 0,
    var revenue2YearsAgoCount: Int = 0,
    var revenue4YearsAgoCount: Int = 0,

    var revenueGrowthLastQuarterCount: Int = 0,
    var revenueGrowthLast2QuartersCount: Int = 0,
    var revenueGrowthLastYearCount: Int = 0,
    var revenueGrowthLast4YearsCount: Int = 0,


    var grossIncomeLastQuarterCount: Int = 0,
    var grossIncome2QuartersAgoCount: Int = 0,
    var grossIncome3QuartersAgoCount: Int = 0,
    var grossIncomeLastYearCount: Int = 0,
    var grossIncome2YearsAgoCount: Int = 0,
    var grossIncome4YearsAgoCount: Int = 0,

    var grossIncomeGrowthLastQuarterCount: Int = 0,
    var grossIncomeGrowthLast2QuartersCount: Int = 0,
    var grossIncomeGrowthLastYearCount: Int = 0,
    var grossIncomeGrowthLast4YearsCount: Int = 0,


    var ebitLastQuarterCount: Int = 0,
    var ebit2QuartersAgoCount: Int = 0,
    var ebit3QuartersAgoCount: Int = 0,
    var ebitLastYearCount: Int = 0,
    var ebit2YearsAgoCount: Int = 0,
    var ebit4YearsAgoCount: Int = 0,

    var ebitGrowthLastQuarterCount: Int = 0,
    var ebitGrowthLast2QuartersCount: Int = 0,
    var ebitGrowthLastYearCount: Int = 0,
    var ebitGrowthLast4YearsCount: Int = 0,


    var netIncomeLastQuarterCount: Int = 0,
    var netIncome2QuartersAgoCount: Int = 0,
    var netIncome3QuartersAgoCount: Int = 0,
    var netIncomeLastYearCount: Int = 0,
    var netIncome2YearsAgoCount: Int = 0,
    var netIncome4YearsAgoCount: Int = 0,

    var netIncomeGrowthLastQuarterCount: Int = 0,
    var netIncomeGrowthLast2QuartersCount: Int = 0,
    var netIncomeGrowthLastYearCount: Int = 0,
    var netIncomeGrowthLast4YearsCount: Int = 0,


    var freeCashFlowLastQuarterCount: Int = 0,
    var freeCashFlow2QuartersAgoCount: Int = 0,
    var freeCashFlow3QuartersAgoCount: Int = 0,
    var freeCashFlowLastYearCount: Int = 0,
    var freeCashFlow2YearsAgoCount: Int = 0,
    var freeCashFlow4YearsAgoCount: Int = 0,

    var freeCashFlowGrowthLastQuarterCount: Int = 0,
    var freeCashFlowGrowthLast2QuartersCount: Int = 0,
    var freeCashFlowGrowthLastYearCount: Int = 0,
    var freeCashFlowGrowthLast4YearsCount: Int = 0,


    var cashLastQuarterCount: Int = 0,
    var cash2QuartersAgoCount: Int = 0,
    var cash3QuartersAgoCount: Int = 0,
    var cashLastYearCount: Int = 0,
    var cash2YearsAgoCount: Int = 0,
    var cash4YearsAgoCount: Int = 0,

    var cashGrowthLastQuarterCount: Int = 0,
    var cashGrowthLast2QuartersCount: Int = 0,
    var cashGrowthLastYearCount: Int = 0,
    var cashGrowthLast4YearsCount: Int = 0,

    var inventoryLastQuarterCount: Int = 0,
    var inventory2QuartersAgoCount: Int = 0,
    var inventory3QuartersAgoCount: Int = 0,
    var inventoryLastYearCount: Int = 0,
    var inventory2YearsAgoCount: Int = 0,
    var inventory4YearsAgoCount: Int = 0,

    var inventoryGrowthLastQuarterCount: Int = 0,
    var inventoryGrowthLast2QuartersCount: Int = 0,
    var inventoryGrowthLastYearCount: Int = 0,
    var inventoryGrowthLast4YearsCount: Int = 0,


    var totalLiabilitiesLastQuarterCount: Int = 0,
    var totalLiabilities2QuartersAgoCount: Int = 0,
    var totalLiabilities3QuartersAgoCount: Int = 0,
    var totalLiabilitiesLastYearCount: Int = 0,
    var totalLiabilities2YearsAgoCount: Int = 0,
    var totalLiabilities3YearsAgoCount: Int = 0,
    var totalLiabilities4YearsAgoCount: Int = 0,

    var totalLiabilitiesGrowthLastQuarterCount: Int = 0,
    var totalLiabilitiesGrowthLast2QuartersCount: Int = 0,
    var totalLiabilitiesGrowthLastYearCount: Int = 0,
    var totalLiabilitiesGrowthLast4YearsCount: Int = 0,


    var totalShareholdersEquityLastQuarterCount: Int = 0,
    var totalShareholdersEquity2QuartersAgoCount: Int = 0,
    var totalShareholdersEquity3QuartersAgoCount: Int = 0,
    var totalShareholdersEquityLastYearCount: Int = 0,
    var totalShareholdersEquity2YearsAgoCount: Int = 0,
    var totalShareholdersEquity4YearsAgoCount: Int = 0,

    var totalShareholdersEquityGrowthLastQuarterCount: Int = 0,
    var totalShareholdersEquityGrowthLast2QuartersCount: Int = 0,
    var totalShareholdersEquityGrowthLastYearCount: Int = 0,
    var totalShareholdersEquityGrowthLast4YearsCount: Int = 0,


    var totalLiabilitiesToEquityLastQuarterCount: Int = 0,
    var totalLiabilitiesToEquity2QuartersAgoCount: Int = 0,
    var totalLiabilitiesToEquity3QuartersAgoCount: Int = 0,
    var totalLiabilitiesToEquityLastYearCount: Int = 0,
    var totalLiabilitiesToEquity2YearsAgoCount: Int = 0,
    var totalLiabilitiesToEquity4YearsAgoCount: Int = 0,

    var totalLiabilitiesToEquityGrowthLastQuarterCount: Int = 0,
    var totalLiabilitiesToEquityGrowthLast2QuartersCount: Int = 0,
    var totalLiabilitiesToEquityGrowthLastYearCount: Int = 0,
    var totalLiabilitiesToEquityGrowthLast4YearsCount: Int = 0,


    var stockRepurchasedLastQuarterCount: Int = 0,
    var stockRepurchased2QuartersAgoCount: Int = 0,
    var stockRepurchased3QuartersAgoCount: Int = 0,
    var stockRepurchasedLastYearCount: Int = 0,
    var stockRepurchased2YearsAgoCount: Int = 0,
    var stockRepurchased4YearsAgoCount: Int = 0,

    var stockRepurchasedGrowthLastQuarterCount: Int = 0,
    var stockRepurchasedGrowthLast2QuartersCount: Int = 0,
    var stockRepurchasedGrowthLastYearCount: Int = 0,
    var stockRepurchasedGrowthLast4YearsCount: Int = 0,


    var stockLastQuarterCount: Int = 0,
    var stock2QuartersAgoCount: Int = 0,
    var stock3QuartersAgoCount: Int = 0,
    var stockLastYearCount: Int = 0,
    var stock2YearsAgoCount: Int = 0,
    var stock4YearsAgoCount: Int = 0,

    var stockGrowthLastQuarterCount: Int = 0,
    var stockGrowthLast2QuartersCount: Int = 0,
    var stockGrowthLastYearCount: Int = 0,
    var stockGrowthLast4YearsCount: Int = 0,


    var epsCurrentQuarterEstimateCount: Int = 0,
    var epsLastQuarterCount: Int = 0,
    var eps2QuartersAgoCount: Int = 0,
    var eps3QuartersAgoCount: Int = 0,
    var eps4QuartersAgoCount: Int = 0,
    var epsLastYearCount: Int = 0,
    var eps2YearsAgoCount: Int = 0,
    var eps3YearsAgoCount: Int = 0,
    var eps4YearsAgoCount: Int = 0,

    var epsGrowthLastQuarterCount: Int = 0,
    var epsGrowthLast2QuartersCount: Int = 0,
    var epsGrowthLastYearCount: Int = 0,
    var epsGrowthLast4YearsCount: Int = 0,

    var priceLastQuarterCount: Int = 0,
    var price2QuartersAgoCount: Int = 0,
    var price3QuartersAgoCount: Int = 0,
    var price4QuartersAgoCount: Int = 0,

    var priceGrowthLastQuarterCount: Int = 0,
    var priceGrowthLast2QuartersCount: Int = 0,
    var priceGrowthLast3QuartersCount: Int = 0,

    var peLastQuarterCount: Int = 0,
    var pe2QuartersAgoCount: Int = 0,
    var pe3QuartersAgoCount: Int = 0,
    var pe4QuartersAgoCount: Int = 0,

    var peGrowthLastQuarterCount: Int = 0,
    var peGrowthLast2QuartersCount: Int = 0,
    var peGrowthLast3QuartersCount: Int = 0,

    var growthEstimate5yCount: Int = 0,

    var roic1YCount: Int = 0,
    var roic3YCount: Int = 0,

    var revenue1YCount: Int = 0,
    var revenue3YCount: Int = 0,
    var revenue5YCount: Int = 0,
    var revenue9YCount: Int = 0,

    var eps1YCount: Int = 0,
    var eps3YCount: Int = 0,
    var eps5YCount: Int = 0,
    var eps9YCount: Int = 0,

    var bps1YCount: Int = 0,
    var bps3YCount: Int = 0,
    var bps5YCount: Int = 0,
    var bps9YCount: Int = 0,

    var cash1YCount: Int = 0,
    var cash3YCount: Int = 0,
    var cash5YCount: Int = 0,
    var cash9YCount: Int = 0,

    var pe1YCount: Int = 0,
    var pe3YCount: Int = 0,
    var pe5YCount: Int = 0,
    var pe9YCount: Int = 0,

    var rule1GrowthRateCount: Int = 0,
    var defaultPECount: Int = 0,
    var historicalPECount: Int = 0,
    var rule1PECount: Int = 0,
    var currentEpsCount: Int = 0,
    var futureEPS10YearsCount: Int = 0,
    var futurePrice10YearsCount: Int = 0,

    var stickerPrice15pcGrowthCount: Int = 0,
    var stickerPrice10pcGrowthCount: Int = 0,
    var stickerPrice5pcGrowthCount: Int = 0,

    var belowStickerPrice15pcCount: Int = 0,
    var belowStickerPrice10pcCount: Int = 0,
    var belowStickerPrice5pcCount: Int = 0
)