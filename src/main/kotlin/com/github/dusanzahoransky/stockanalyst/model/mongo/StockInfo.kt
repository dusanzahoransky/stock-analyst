package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class StockInfo(
    @Id var id: String? = null,
    val date: LocalDate = LocalDate.now(),
    var lastReportedQuarter: LocalDate? = null,
    val symbol: String,
    val exchange: Exchange? = null,
    var companyName: String? = null,
    var price: Double? = null,
    var currency: Currency? = null,
    var financialCurrency: Currency? = null,
    var change: Double? = null,
    var enterpriseValue: Double? = null,

    var totalCashPerShare: Double? = null,
    var totalCashPerSharePercent: Double? = null,
    var totalDebtEquity: Double? = null,

    var trailingPE: Double? = null,
    var forwardPE: Double? = null,
    var priceToSalesTrailing12Months: Double? = null,
    var priceBook: Double? = null,
    var enterpriseValueRevenue: Double? = null,
    var enterpriseValueEBITDA: Double? = null,

    var yoyQuarterlyRevenueGrowthPercent: Double? = null,

    var priceEarningGrowth: Double? = null,
    var trailingPriceEarningGrowth: Double? = null,

    var week52Change: Double? = null,
    var week52Low: Double? = null,
    var week52AboveLowPercent: Double? = null,
    var week52High: Double? = null,
    var week52BelowHighPercent: Double? = null,

    var targetLowPrice: Double? = null,
    var belowTargetLowPricePercent: Double? = null,
    var targetMedianPrice: Double? = null,
    var belowTargetMedianPricePercent: Double? = null,

    var heldByInsiders: Double? = null,
    var heldByInstitutions: Double? = null,
    var shortToFloat: Double? = null,
    var sharesShortPrevMonthCompare: Double? = null,

    var exDividendDate: String? = null,
    var fiveYearAvgDividendYield: Double? = null,
    var trailingAnnualDividendYield: Double? = null,

    var netIncomeLastQuarter: Long? = null,
    var netIncome2QuartersAgo: Long? = null,
    var netIncome3QuartersAgo: Long? = null,
    var netIncomeLastYear: Long? = null,
    var netIncome3YearsAgo: Long? = null,
    var netIncomeGrowthLastQuarter: Double? = null,
    var netIncomeGrowthLast2Quarters: Double? = null,
    var netIncomeGrowthLast3Years: Double? = null,

    var revenueLastQuarter: Long? = null,
    var revenue2QuartersAgo: Long? = null,
    var revenue3QuartersAgo: Long? = null,
    var revenueLastYear: Long? = null,
    var revenueGrowthLastQuarter: Double? = null,
    var revenueGrowthLast2Quarters: Double? = null,
    var revenueGrowthLastYear: Double? = null,
    var revenueGrowthLast3Years: Double? = null,

    var cashLastQuarter: Long? = null,
    var cashLastYear: Long? = null,
    var cashGrowthLastQuarter: Double? = null,
    var cashGrowthLastYear: Double? = null,
    var cashGrowthLast3Years: Double? = null,

    var inventoryLastQuarter: Long? = null,
    var inventoryLastYear: Long? = null,
    var inventoryGrowthLastQuarter: Double? = null,
    var inventoryGrowthLastYear: Double? = null,
    var inventoryGrowthLast3Years: Double? = null,

    var currentLiabilitiesLastQuarter: Long? = null,
    var currentLiabilitiesLastYear: Long? = null,
    var currentLiabilitiesGrowthLastQuarter: Double? = null,
    var currentLiabilitiesGrowthLastYear: Double? = null,
    var currentLiabilitiesGrowthLast3Years: Double? = null,

    var totalLiabilitiesLastQuarter: Long? = null,
    var totalLiabilitiesLastYear: Long? = null,
    var totalLiabilitiesGrowthLastQuarter: Double? = null,
    var totalLiabilitiesGrowthLastYear: Double? = null,
    var totalLiabilitiesGrowthLast3Years: Double? = null,

    var totalShareholdersEquityLastQuarter: Long? = null,
    var totalShareholdersEquityLastYear: Long? = null,
    var totalShareholdersEquityGrowthLastQuarter: Double? = null,
    var totalShareholdersEquityGrowthLastYear: Double? = null,
    var totalShareholdersEquityGrowthLast3Years: Double? = null,

    var currentLiabilitiesToEquityLastQuarter: Double? = null,
    var currentLiabilitiesToEquityLastYear: Double? = null,
    var currentLiabilitiesToEquityGrowthLastQuarter: Double? = null,
    var currentLiabilitiesToEquityGrowthLastYear: Double? = null,
    var currentLiabilitiesToEquityGrowthLast3Years: Double? = null,

    var totalLiabilitiesToEquityLastQuarter: Double? = null,
    var totalLiabilitiesToEquityLastYear: Double? = null,
    var totalLiabilitiesToEquityGrowthLastQuarter: Double? = null,
    var totalLiabilitiesToEquityGrowthLastYear: Double? = null,
    var totalLiabilitiesToEquityGrowthLast3Years: Double? = null,

    var stockRepurchasedLastQuarter: Long? = null,
    var stockRepurchasedLastYear: Long? = null,
    var stockRepurchasedGrowthLastQuarter: Double? = null,
    var stockRepurchasedGrowthLastYear: Double? = null,
    var stockRepurchasedGrowthLast3Years: Double? = null,

    var stockLastQuarter: Long? = null,
    var stockLastYear: Long? = null,
    var stockGrowthLastQuarter: Double? = null,
    var stockGrowthLastYear: Double? = null,
    var stockGrowthLast3Years: Double? = null,

    var epsCurrentQuarterEstimate: Double? = null,
    var epsLastQuarter: Double? = null,
    var eps2QuartersAgo: Double? = null,
    var eps3QuartersAgo: Double? = null,
    var eps4QuartersAgo: Double? = null,
    var epsLastYear: Double? = null,
    var eps2YearsAgo: Double? = null,
    var eps3YearsAgo: Double? = null,
    var eps4YearsAgo: Double? = null,
    var epsGrowthLastQuarter: Double? = null,
    var epsGrowthLast2Quarters: Double? = null,
    var epsGrowthLast3Quarters: Double? = null,
    var epsGrowthEstimateLastQuarter: Double? = null,
    var epsGrowthLastYear: Double? = null,
    var epsGrowthLast2Years: Double? = null,
    var epsGrowthLast3Years: Double? = null,

    var priceLastQuarter: Double? = null,
    var price2QuartersAgo: Double? = null,
    var price3QuartersAgo: Double? = null,
    var price4QuartersAgo: Double? = null,
    var priceGrowthLastQuarter: Double? = null,
    var priceGrowthLast2Quarters: Double? = null,
    var priceGrowthLast3Quarters: Double? = null,

    var peLastQuarter: Double? = null,
    var pe2QuartersAgo: Double? = null,
    var pe3QuartersAgo: Double? = null,
    var pe4QuartersAgo: Double? = null,
    var peGrowthLastQuarter: Double? = null,
    var peGrowthLast2Quarters: Double? = null,
    var peGrowthLast3Quarters: Double? = null,

    var quarterEnds: List<Long>? = null,
    var yearEnds: List<Long>? = null,
    var chartData: MutableList<ChartData>? = mutableListOf()
    /*
    Growth Estimates -
Current Year
Next Year
Past 5 Years (per annum)
Next 5 Years (per annum)
     */

)