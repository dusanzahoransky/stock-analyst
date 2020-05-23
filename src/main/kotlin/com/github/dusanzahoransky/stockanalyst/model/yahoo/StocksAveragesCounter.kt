package com.github.dusanzahoransky.stockanalyst.model.yahoo

import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo

data class StocksAveragesCounter(
    var averages: StockInfo,

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

    var yoyQuarterlyRevenueGrowthPercentCount: Int = 0,

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

    var netIncomeLastQuarterCount: Int = 0,
    var netIncome2QuartersAgoCount: Int = 0,
    var netIncome3QuartersAgoCount: Int = 0,
    var netIncomeLastYearCount: Int = 0,
    var netIncome2YearsAgoCount: Int = 0,
    var netIncome3YearsAgoCount: Int = 0,
    var netIncomeGrowthLastQuarterCount: Int = 0,
    var netIncomeGrowthLast2QuartersCount: Int = 0,
    var netIncomeGrowthLastYearCount: Int = 0,
    var netIncomeGrowthLast3YearsCount: Int = 0,

    var revenueLastQuarterCount: Int = 0,
    var revenue2QuartersAgoCount: Int = 0,
    var revenue3QuartersAgoCount: Int = 0,
    var revenueGrowthLastQuarterCount: Int = 0,
    var revenueGrowthLast2QuartersCount: Int = 0,
    var revenueLastYearCount: Int = 0,
    var revenue2YearsAgoCount: Int = 0,
    var revenueGrowthLastYearCount: Int = 0,
    var revenueGrowthLast3YearsCount: Int = 0,

    var cashLastQuarterCount: Int = 0,
    var cashGrowthLastQuarterCount: Int = 0,
    var cashGrowthYOYCount: Int = 0,
    var cashLastYearCount: Int = 0,
    var cash2YearsAgoCount: Int = 0,
    var cashGrowthLastYearCount: Int = 0,
    var cashGrowthLast3YearsCount: Int = 0,

    var inventoryLastQuarterCount: Int = 0,
    var inventoryGrowthLastQuarterCount: Int = 0,
    var inventoryGrowthYOYCount: Int = 0,
    var inventoryLastYearCount: Int = 0,
    var inventory2YearsAgoCount: Int = 0,
    var inventoryGrowthLastYearCount: Int = 0,
    var inventoryGrowthLast3YearsCount: Int = 0,

    var currentLiabilitiesLastQuarterCount: Int = 0,
    var currentLiabilitiesGrowthLastQuarterCount: Int = 0,
    var currentLiabilitiesGrowthYOYCount: Int = 0,
    var currentLiabilitiesLastYearCount: Int = 0,
    var currentLiabilities2YearsAgoCount: Int = 0,
    var currentLiabilitiesGrowthLastYearCount: Int = 0,
    var currentLiabilitiesGrowthLast3YearsCount: Int = 0,

    var totalLiabilitiesLastQuarterCount: Int = 0,
    var totalLiabilitiesGrowthLastQuarterCount: Int = 0,
    var totalLiabilitiesGrowthYOYCount: Int = 0,
    var totalLiabilitiesLastYearCount: Int = 0,
    var totalLiabilities2YearsAgoCount: Int = 0,
    var totalLiabilitiesGrowthLastYearCount: Int = 0,
    var totalLiabilitiesGrowthLast3YearsCount: Int = 0,

    var totalShareholdersEquityLastQuarterCount: Int = 0,
    var totalShareholdersEquityGrowthLastQuarterCount: Int = 0,
    var totalShareholdersEquityGrowthYOYCount: Int = 0,
    var totalShareholdersEquityLastYearCount: Int = 0,
    var totalShareholdersEquity2YearsAgoCount: Int = 0,
    var totalShareholdersEquityGrowthLastYearCount: Int = 0,
    var totalShareholdersEquityGrowthLast3YearsCount: Int = 0,

    var currentLiabilitiesToEquityLastQuarterCount: Int = 0,
    var currentLiabilitiesToEquityLastYearCount: Int = 0,
    var currentLiabilitiesToEquity2YearsAgoCount: Int = 0,
    var currentLiabilitiesToEquityGrowthLastQuarterCount: Int = 0,
    var currentLiabilitiesToEquityGrowthYOYCount: Int = 0,
    var currentLiabilitiesToEquityGrowthLastYearCount: Int = 0,
    var currentLiabilitiesToEquityGrowthLast3YearsCount: Int = 0,

    var totalLiabilitiesToEquityLastQuarterCount: Int = 0,
    var totalLiabilitiesToEquityLastYearCount: Int = 0,
    var totalLiabilitiesToEquity2YearsAgoCount: Int = 0,
    var totalLiabilitiesToEquityGrowthLastQuarterCount: Int = 0,
    var totalLiabilitiesToEquityGrowthYOYCount: Int = 0,
    var totalLiabilitiesToEquityGrowthLastYearCount: Int = 0,
    var totalLiabilitiesToEquityGrowthLast3YearsCount: Int = 0,

    var stockRepurchasedLastQuarterCount: Int = 0,
    var stockRepurchasedGrowthLastQuarterCount: Int = 0,
    var stockRepurchasedGrowthYOYCount: Int = 0,
    var stockRepurchasedLastYearCount: Int = 0,
    var stockRepurchased2YearsAgoCount: Int = 0,
    var stockRepurchasedGrowthLastYearCount: Int = 0,
    var stockRepurchasedGrowthLast3YearsCount: Int = 0,

    var stockLastQuarterCount: Int = 0,
    var stockGrowthLastQuarterCount: Int = 0,
    var stockGrowthYOYCount: Int = 0,
    var stockLastYearCount: Int = 0,
    var stock2YearsAgoCount: Int = 0,
    var stockGrowthLastYearCount: Int = 0,
    var stockGrowthLast3YearsCount: Int = 0,

    var epsCurrentQuarterEstimateCount: Int = 0,
    var epsLastQuarterCount: Int = 0,
    var eps2QuartersAgoCount: Int = 0,
    var eps3QuartersAgoCount: Int = 0,
    var eps4QuartersAgoCount: Int = 0,
    var epsGrowthLastQuarterCount: Int = 0,
    var epsGrowthLast2QuartersCount: Int = 0,
    var epsGrowthLast3QuartersCount: Int = 0,
    var epsGrowthEstimateLastQuarterCount: Int = 0,
    var epsGrowthYOYCount: Int = 0,

    var epsLastYearCount: Int = 0,
    var eps2YearsAgoCount: Int = 0,
    var eps3YearsAgoCount: Int = 0,
    var eps4YearsAgoCount: Int = 0,
    var epsGrowthLastYearCount: Int = 0,
    var epsGrowthLast2YearsCount: Int = 0,
    var epsGrowthLast3YearsCount: Int = 0,

    var priceLastQuarterCount: Int = 0,
    var price2QuartersAgoCount: Int = 0,
    var price3QuartersAgoCount: Int = 0,
    var price4QuartersAgoCount: Int = 0,
    var price2YearsAgoCount: Int = 0,

    var peLastQuarterCount: Int = 0,
    var pe2QuartersAgoCount: Int = 0,
    var pe3QuartersAgoCount: Int = 0,
    var pe4QuartersAgoCount: Int = 0,
    var pe2YearsAgoCount: Int = 0,

    var priceGrowthLastQuarterCount: Int = 0,
    var priceGrowthLast2QuartersCount: Int = 0,
    var priceGrowthLast3QuartersCount: Int = 0,
    var priceGrowthYOYCount: Int = 0,

    var peGrowthLastQuarterCount: Int = 0,
    var peGrowthLast2QuartersCount: Int = 0,
    var peGrowthLast3QuartersCount: Int = 0,
    var peGrowthYOYCount: Int = 0,

    var currentAssetsGrowthLast3YearsCount: Int = 0,
    var currentAssetsGrowthLastQuarterCount: Int = 0,
    var currentAssetsGrowthLastYearCount: Int = 0,
    var currentAssetsLastQuarterCount: Int = 0,
    var currentAssetsLastYearCount: Int = 0,
    var grossIncome2QuartersAgoCount: Int = 0,
    var grossIncome3QuartersAgoCount: Int = 0,
    var grossIncome3YearsAgoCount: Int = 0,
    var grossIncomeGrowthLast2QuartersCount: Int = 0,
    var grossIncomeGrowthLast3YearsCount: Int = 0,
    var grossIncomeGrowthLastQuarterCount: Int = 0,
    var grossIncomeLastQuarterCount: Int = 0,
    var grossIncomeLastYearCount: Int = 0,
    var payoutRatioCount: Int = 0,

    var cash2QuartersAgoCount: Int = 0,
    var cash3YearsAgoCount: Int = 0,
    var currentAssets2QuartersAgoCount: Int = 0,
    var currentAssets2YearsAgoCount: Int = 0,
    var currentAssets3YearsAgoCount: Int = 0,
    var currentLiabilities2QuartersAgoCount: Int = 0,
    var currentLiabilities3YearsAgoCount: Int = 0,
    var currentLiabilitiesToEquity2QuartersAgoCount: Int = 0,
    var currentLiabilitiesToEquity3YearsAgoCount: Int = 0,
    var inventory2QuartersAgoCount: Int = 0,
    var inventory3YearsAgoCount: Int = 0,
    var revenue3YearsAgoCount: Int = 0,
    var stock2QuartersAgoCount: Int = 0,
    var stock3YearsAgoCount: Int = 0,
    var stockRepurchased2QuartersAgoCount: Int = 0,
    var stockRepurchased3YearsAgoCount: Int = 0,
    var totalLiabilities2QuartersAgoCount: Int = 0,
    var totalLiabilities3YearsAgoCount: Int = 0,
    var totalLiabilitiesToEquity2QuartersAgoCount: Int = 0,
    var totalLiabilitiesToEquity3YearsAgoCount: Int = 0,
    var totalShareholdersEquity2QuartersAgoCount: Int = 0,
    var totalShareholdersEquity3YearsAgoCount: Int = 0
)