package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.github.dusanzahoransky.stockanalyst.model.Ticker
import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.*

@Document
data class Stock(

    @Id
    var id: String? = null,
    val symbol: String,
    val exchange: Exchange,

    //last updated timestamp
    val chartLastUpdated: LocalDate = LocalDate.now(),
    val financialsLastUpdated: LocalDate = LocalDate.now(),
    val analysisLastUpdated: LocalDate = LocalDate.now(),
    val statisticsLastUpdated: LocalDate = LocalDate.now(),
    val holdersLastUpdated: LocalDate = LocalDate.now(),
    var krfLastUpdated: LocalDate = LocalDate.now(),

    //latest available data
    var lastReportedQuarter: LocalDate? = null,
    var companyName: String? = null,
    var currency: Currency? = null,
    var financialCurrency: Currency? = null,
    var change: Double? = null,
    var currentPrice: Double? = null,


    //timeline data

    //basic info & ratios without growth
    var enterpriseValue: SortedMap<LocalDate, Double> = TreeMap(),
    var totalCashPerShare: SortedMap<LocalDate, Double> = TreeMap(),
    var totalCashPerSharePercent: SortedMap<LocalDate, Double> = TreeMap(),
    var trailingPE: SortedMap<LocalDate, Double> = TreeMap(),
    var forwardPE: SortedMap<LocalDate, Double> = TreeMap(),
    var priceToSalesTrailing12Months: SortedMap<LocalDate, Double> = TreeMap(),
    var freeCashFlowToPrice: SortedMap<LocalDate, Double> = TreeMap(),
    var freeCashFlowToPriceQ: SortedMap<LocalDate, Double> = TreeMap(),
    var priceBook: SortedMap<LocalDate, Double> = TreeMap(),
    var enterpriseValueRevenue: SortedMap<LocalDate, Double> = TreeMap(),
    var enterpriseValueEBITDA: SortedMap<LocalDate, Double> = TreeMap(),
    var priceEarningGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var trailingPriceEarningGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var week52Change: SortedMap<LocalDate, Double> = TreeMap(),
    var week52Low: SortedMap<LocalDate, Double> = TreeMap(),
    var week52High: SortedMap<LocalDate, Double> = TreeMap(),
    var targetLowPrice: SortedMap<LocalDate, Double> = TreeMap(),
    var belowTargetLowPricePercent: SortedMap<LocalDate, Double> = TreeMap(),
    var targetMedianPrice: SortedMap<LocalDate, Double> = TreeMap(),
    var belowTargetMedianPricePercent: SortedMap<LocalDate, Double> = TreeMap(),
    var heldByInsiders: SortedMap<LocalDate, Double> = TreeMap(),
    var heldByInstitutions: SortedMap<LocalDate, Double> = TreeMap(),
    var buyPercentInsiderShares: SortedMap<LocalDate, Double> = TreeMap(),
    var sellPercentInsiderShares: SortedMap<LocalDate, Double> = TreeMap(),
    var shortToFloat: SortedMap<LocalDate, Double> = TreeMap(),
    var sharesShortPrevMonthCompare: SortedMap<LocalDate, Double> = TreeMap(),
    var exDividendDate: SortedMap<LocalDate, String> = TreeMap(),
    var fiveYearAvgDividendYield: SortedMap<LocalDate, Double> = TreeMap(),
    var trailingAnnualDividendYield: SortedMap<LocalDate, Double> = TreeMap(),
    var payoutRatio: SortedMap<LocalDate, Double> = TreeMap(),

    var revenue: SortedMap<LocalDate, Long> = TreeMap(),
    var revenueQ: SortedMap<LocalDate, Long> = TreeMap(),
    var revenueGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var revenueGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var grossIncome: SortedMap<LocalDate, Long> = TreeMap(),
    var grossIncomeQ: SortedMap<LocalDate, Long> = TreeMap(),
    var grossIncomeGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var grossIncomeGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var ebit: SortedMap<LocalDate, Long> = TreeMap(),
    var ebitQ: SortedMap<LocalDate, Long> = TreeMap(),
    var ebitGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var ebitGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var netIncome: SortedMap<LocalDate, Long> = TreeMap(),
    var netIncomeQ: SortedMap<LocalDate, Long> = TreeMap(),
    var netIncomeGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var netIncomeGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var profitMargin: SortedMap<LocalDate, Double> = TreeMap(),
    var profitMarginQ: SortedMap<LocalDate, Double> = TreeMap(),
    var profitMarginGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var profitMarginGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var totalCashFromOperatingActivities: SortedMap<LocalDate, Long> = TreeMap(),
    var totalCashFromOperatingActivitiesQ: SortedMap<LocalDate, Long> = TreeMap(),
    var totalCashFromOperatingActivitiesGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var totalCashFromOperatingActivitiesGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var capitalExpenditures: SortedMap<LocalDate, Long> = TreeMap(),
    var capitalExpendituresQ: SortedMap<LocalDate, Long> = TreeMap(),
    var capitalExpendituresGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var capitalExpendituresGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var freeCashFlow: SortedMap<LocalDate, Long> = TreeMap(),
    var freeCashFlowQ: SortedMap<LocalDate, Long> = TreeMap(),
    var freeCashFlowGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var freeCashFlowGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var cash: SortedMap<LocalDate, Long> = TreeMap(),
    var cashQ: SortedMap<LocalDate, Long> = TreeMap(),
    var cashGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var cashGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var inventory: SortedMap<LocalDate, Long> = TreeMap(),
    var inventoryQ: SortedMap<LocalDate, Long> = TreeMap(),
    var inventoryGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var inventoryGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var currentAssets: SortedMap<LocalDate, Long> = TreeMap(),
    var currentAssetsQ: SortedMap<LocalDate, Long> = TreeMap(),
    var currentAssetsGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var currentAssetsGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var currentLiabilities: SortedMap<LocalDate, Long> = TreeMap(),
    var currentLiabilitiesQ: SortedMap<LocalDate, Long> = TreeMap(),
    var currentLiabilitiesGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var currentLiabilitiesGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var currentRatio: SortedMap<LocalDate, Double> = TreeMap(),
    var currentRatioQ: SortedMap<LocalDate, Double> = TreeMap(),
    var currentRatioGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var currentRatioGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var totalLiabilities: SortedMap<LocalDate, Long> = TreeMap(),
    var totalLiabilitiesQ: SortedMap<LocalDate, Long> = TreeMap(),
    var totalLiabilitiesGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var totalLiabilitiesGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var totalDebtToEquity: SortedMap<LocalDate, Double> = TreeMap(),
    var totalDebtToEquityQ: SortedMap<LocalDate, Double> = TreeMap(),
    var totalDebtToEquityGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var totalDebtToEquityGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var totalAssets: SortedMap<LocalDate, Long> = TreeMap(),
    var totalAssetsQ: SortedMap<LocalDate, Long> = TreeMap(),
    var totalAssetsGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var totalAssetsGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var totalShareholdersEquity: SortedMap<LocalDate, Long> = TreeMap(),
    var totalShareholdersEquityQ: SortedMap<LocalDate, Long> = TreeMap(),
    var totalShareholdersEquityGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var totalShareholdersEquityGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var totalLiabilitiesToEquity: SortedMap<LocalDate, Double> = TreeMap(),
    var totalLiabilitiesToEquityQ: SortedMap<LocalDate, Double> = TreeMap(),
    var totalLiabilitiesToEquityGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var totalLiabilitiesToEquityGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var stockRepurchased: SortedMap<LocalDate, Long> = TreeMap(),
    var stockRepurchasedQ: SortedMap<LocalDate, Long> = TreeMap(),
    var stockRepurchasedGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var stockRepurchasedGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),

    var eps: SortedMap<LocalDate, Double> = TreeMap(),
    var epsQ: SortedMap<LocalDate, Double> = TreeMap(),
    var epsGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var epsGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),
    var pe: SortedMap<LocalDate, Double> = TreeMap(),
    var peQ: SortedMap<LocalDate, Double> = TreeMap(),
    var peGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var peGrowthQ: SortedMap<LocalDate, Double> = TreeMap(),

    var bookValuePerShare: SortedMap<LocalDate, Double> = TreeMap(),
    var bookValuePerShareGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var capSpending: SortedMap<LocalDate, Double> = TreeMap(),
    var capSpendingGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var dividends: SortedMap<LocalDate, Double> = TreeMap(),
    var dividendsGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var freeCashFlowPerShare: SortedMap<LocalDate, Double> = TreeMap(),
    var freeCashFlowPerShareGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var grossMargin: SortedMap<LocalDate, Double> = TreeMap(),
    var grossMarginGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var operatingCashFlow: SortedMap<LocalDate, Double> = TreeMap(),
    var operatingCashFlowGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var operatingIncome: SortedMap<LocalDate, Double> = TreeMap(),
    var operatingIncomeGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var operatingMargin: SortedMap<LocalDate, Double> = TreeMap(),
    var operatingMarginGrowth: SortedMap<LocalDate, Double> = TreeMap(),

    var growthEstimate5y: SortedMap<LocalDate, Double> = TreeMap(),

    var shares: SortedMap<LocalDate, Double> = TreeMap(),
    var sharesGrowth: SortedMap<LocalDate, Double> = TreeMap(),
    var workingCapital: SortedMap<LocalDate, Double> = TreeMap(),
    var workingCapitalGrowth: SortedMap<LocalDate, Double> = TreeMap(),

    var price: SortedMap<LocalDate, Double> = TreeMap()

) {
    override fun toString(): String{
        return "$symbol:$exchange"
    }
    fun getTicker(): Ticker{
        return Ticker(this.symbol, this.exchange)
    }
}