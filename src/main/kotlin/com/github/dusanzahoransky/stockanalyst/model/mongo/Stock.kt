package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document("stockInfo")
data class Stock(
    @Id var id: String? = null,
    val date: LocalDate = LocalDate.now(),
    var lastReportedQuarter: LocalDate? = null,
    val symbol: String,
    val exchange: Exchange,
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
    var payoutRatio: Double? = null,

    var netIncomeLastQuarter: Long? = null,
    var netIncome2QuartersAgo: Long? = null,
    var netIncome3QuartersAgo: Long? = null,
    var netIncomeLastYear: Long? = null,
    var netIncome3YearsAgo: Long? = null,
    var netIncomeGrowthLastQuarter: Double? = null,
    var netIncomeGrowthLast2Quarters: Double? = null,
    var netIncomeGrowthLast3Years: Double? = null,

    var grossIncomeLastQuarter: Long? = null,
    var grossIncome2QuartersAgo: Long? = null,
    var grossIncome3QuartersAgo: Long? = null,
    var grossIncomeLastYear: Long? = null,
    var grossIncome3YearsAgo: Long? = null,
    var grossIncomeGrowthLastQuarter: Double? = null,
    var grossIncomeGrowthLast2Quarters: Double? = null,
    var grossIncomeGrowthLast3Years: Double? = null,

    var revenueLastQuarter: Long? = null,
    var revenue2QuartersAgo: Long? = null,
    var revenue3QuartersAgo: Long? = null,
    var revenueLastYear: Long? = null,
    var revenue2YearsAgo: Long? = null,
    var revenue3YearsAgo: Long? = null,
    var revenueGrowthLastQuarter: Double? = null,
    var revenueGrowthLast2Quarters: Double? = null,
    var revenueGrowthLastYear: Double? = null,
    var revenueGrowthLast3Years: Double? = null,

    var cashLastQuarter: Long? = null,
    var cash2QuartersAgo: Long? = null,
    var cashLastYear: Long? = null,
    var cash2YearsAgo: Long? = null,
    var cash3YearsAgo: Long? = null,
    var cashGrowthLastQuarter: Double? = null,
    var cashGrowthLastYear: Double? = null,
    var cashGrowthLast3Years: Double? = null,

    var inventoryLastQuarter: Long? = null,
    var inventory2QuartersAgo: Long? = null,

    var inventoryLastYear: Long? = null,
    var inventory2YearsAgo: Long? = null,
    var inventory3YearsAgo: Long? = null,
    var inventoryGrowthLastQuarter: Double? = null,
    var inventoryGrowthLastYear: Double? = null,
    var inventoryGrowthLast3Years: Double? = null,

    var currentAssetsLastQuarter: Long? = null,
    var currentAssets2QuartersAgo: Long? = null,

    var currentAssetsLastYear: Long? = null,
    var currentAssets2YearsAgo: Long? = null,
    var currentAssets3YearsAgo: Long? = null,
    var currentAssetsGrowthLastQuarter: Double? = null,
    var currentAssetsGrowthLastYear: Double? = null,
    var currentAssetsGrowthLast3Years: Double? = null,

    var currentLiabilitiesLastQuarter: Long? = null,
    var currentLiabilities2QuartersAgo: Long? = null,

    var currentLiabilitiesLastYear: Long? = null,
    var currentLiabilities2YearsAgo: Long? = null,
    var currentLiabilities3YearsAgo: Long? = null,
    var currentLiabilitiesGrowthLastQuarter: Double? = null,
    var currentLiabilitiesGrowthLastYear: Double? = null,
    var currentLiabilitiesGrowthLast3Years: Double? = null,

    var totalLiabilitiesLastQuarter: Long? = null,
    var totalLiabilities2QuartersAgo: Long? = null,

    var totalLiabilitiesLastYear: Long? = null,
    var totalLiabilities2YearsAgo: Long? = null,
    var totalLiabilities3YearsAgo: Long? = null,
    var totalLiabilities4YearsAgo: Long? = null,
    var totalLiabilitiesGrowthLastQuarter: Double? = null,
    var totalLiabilitiesGrowthLastYear: Double? = null,
    var totalLiabilitiesGrowthLast3Years: Double? = null,

    var totalShareholdersEquityLastQuarter: Long? = null,
    var totalShareholdersEquity2QuartersAgo: Long? = null,

    var totalShareholdersEquityLastYear: Long? = null,
    var totalShareholdersEquity2YearsAgo: Long? = null,
    var totalShareholdersEquity3YearsAgo: Long? = null,
    var totalShareholdersEquity4YearsAgo: Long? = null,
    var totalShareholdersEquityGrowthLastQuarter: Double? = null,
    var totalShareholdersEquityGrowthLastYear: Double? = null,
    var totalShareholdersEquityGrowthLast3Years: Double? = null,

    var currentLiabilitiesToEquityLastQuarter: Double? = null,
    var currentLiabilitiesToEquity2QuartersAgo: Double? = null,
    var currentLiabilitiesToEquityLastYear: Double? = null,
    var currentLiabilitiesToEquity2YearsAgo: Double? = null,
    var currentLiabilitiesToEquity3YearsAgo: Double? = null,
    var currentLiabilitiesToEquityGrowthLastQuarter: Double? = null,
    var currentLiabilitiesToEquityGrowthLastYear: Double? = null,
    var currentLiabilitiesToEquityGrowthLast3Years: Double? = null,

    var totalLiabilitiesToEquityLastQuarter: Double? = null,
    var totalLiabilitiesToEquity2QuartersAgo: Double? = null,
    var totalLiabilitiesToEquityLastYear: Double? = null,
    var totalLiabilitiesToEquity2YearsAgo: Double? = null,
    var totalLiabilitiesToEquity3YearsAgo: Double? = null,
    var totalLiabilitiesToEquityGrowthLastQuarter: Double? = null,
    var totalLiabilitiesToEquityGrowthLastYear: Double? = null,
    var totalLiabilitiesToEquityGrowthLast3Years: Double? = null,

    var stockRepurchasedLastQuarter: Long? = null,
    var stockRepurchased2QuartersAgo: Long? = null,
    var stockRepurchasedLastYear: Long? = null,
    var stockRepurchased2YearsAgo: Long? = null,
    var stockRepurchased3YearsAgo: Long? = null,
    var stockRepurchasedGrowthLastQuarter: Double? = null,
    var stockRepurchasedGrowthLastYear: Double? = null,
    var stockRepurchasedGrowthLast3Years: Double? = null,

    var stockLastQuarter: Long? = null,
    var stock2QuartersAgo: Long? = null,
    var stockLastYear: Long? = null,
    var stock2YearsAgo: Long? = null,
    var stock3YearsAgo: Long? = null,
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

    var growthEstimate5y: Double? = null,

    var roic1Y: Double? = null,
    var roic3Y: Double? = null,

    var revenue1Y: Double? = null,
    var revenue3Y: Double? = null,
    var revenue5Y: Double? = null,
    var revenue9Y: Double? = null,

    var eps1Y: Double? = null,
    var eps3Y: Double? = null,
    var eps5Y: Double? = null,
    var eps9Y: Double? = null,

    var bps1Y: Double? = null,
    var bps3Y: Double? = null,
    var bps5Y: Double? = null,
    var bps9Y: Double? = null,

    var cash1Y: Double? = null,
    var cash3Y: Double? = null,
    var cash5Y: Double? = null,
    var cash9Y: Double? = null,

    var pe1Y: Double? = null,
    var pe3Y: Double? = null,
    var pe5Y: Double? = null,
    var pe9Y: Double? = null,

    var rule1GrowthRate: Double? = null,
    var defaultPE: Double? = null,
    var historicalPE: Double? = null,
    var rule1PE: Double? = null,
    var currentEps: Double? = null,
    var futureEPS10Years: Double? = null,
    var futurePrice10Years: Double? = null,

    var stickerPrice15pcGrowth: Double? = null,
    var stickerPrice10pcGrowth: Double? = null,
    var stickerPrice5pcGrowth: Double? = null,

    var belowStickerPrice15pc: Double? = null,
    var belowStickerPrice10pc: Double? = null,
    var belowStickerPrice5pc: Double? = null,

    var quarterEnds: List<Long>? = null,
    var yearEnds: List<Long>? = null,
    var chartData: MutableList<StockChartData>? = mutableListOf()

): LastRefreshDate {
    override fun getLastRefreshDate(): LocalDate {
        return date
    }
}