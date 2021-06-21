package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.fasterxml.jackson.annotation.JsonIgnore
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
    var chartLastUpdated: LocalDate = LocalDate.now(),
    var financialsLastUpdated: LocalDate = LocalDate.now(),
    var analysisLastUpdated: LocalDate = LocalDate.now(),
    var statisticsLastUpdated: LocalDate = LocalDate.now(),
    var holdersLastUpdated: LocalDate = LocalDate.now(),
    var krfLastUpdated: LocalDate = LocalDate.now(),

    //latest available data
    var lastReportedQuarter: LocalDate? = null,
    var companyName: String? = null,
    var currency: Currency? = null,
    var financialCurrency: Currency? = null,
    var change: Double? = null,
    var currentPrice: Double? = null,
    var currentPriceLocal: Double? = null,

    //timeline data

    //basic info & ratios without growth
    var marketCap: SortedMap<LocalDate, Double?> = TreeMap(),
    var enterpriseValue: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalCashPerShare: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalCashPerShareP: SortedMap<LocalDate, Double?> = TreeMap(),
    var trailingPE: SortedMap<LocalDate, Double?> = TreeMap(),
    var forwardPE: SortedMap<LocalDate, Double?> = TreeMap(),
    var priceToSalesTrailing12Months: SortedMap<LocalDate, Double?> = TreeMap(),
    var currentPriceToFreeCashFlow: SortedMap<LocalDate, Double?> = TreeMap(),
    var priceToFreeCashFlow: SortedMap<LocalDate, Double?> = TreeMap(),

    var priceBook: SortedMap<LocalDate, Double?> = TreeMap(),
    var enterpriseValueRevenue: SortedMap<LocalDate, Double?> = TreeMap(),
    var enterpriseValueEBITDA: SortedMap<LocalDate, Double?> = TreeMap(),
    var acquirersMultiple: SortedMap<LocalDate, Double?> = TreeMap(),
    var priceEarningGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var trailingPriceEarningGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var week52ChangeP: SortedMap<LocalDate, Double?> = TreeMap(),
    var week52Low: SortedMap<LocalDate, Double?> = TreeMap(),
    var week52AboveLowP: SortedMap<LocalDate, Double?> = TreeMap(),
    var week52High: SortedMap<LocalDate, Double?> = TreeMap(),
    var week52BelowHighP: SortedMap<LocalDate, Double?> = TreeMap(),

    var targetLowPrice: SortedMap<LocalDate, Double?> = TreeMap(),
    var belowTargetLowPriceP: SortedMap<LocalDate, Double?> = TreeMap(),
    var targetMedianPrice: SortedMap<LocalDate, Double?> = TreeMap(),
    var belowTargetMedianPriceP: SortedMap<LocalDate, Double?> = TreeMap(),
    var heldByInsidersP: SortedMap<LocalDate, Double?> = TreeMap(),
    var heldByInstitutionsP: SortedMap<LocalDate, Double?> = TreeMap(),
    var buyPercentInsiderShares: SortedMap<LocalDate, Double?> = TreeMap(),
    var sellPercentInsiderShares: SortedMap<LocalDate, Double?> = TreeMap(),
    var shortToFloatP: SortedMap<LocalDate, Double?> = TreeMap(),
    var sharesShortPrevMonthCompareP: SortedMap<LocalDate, Double?> = TreeMap(),

    var exDividendDate: SortedMap<LocalDate, String?> = TreeMap(),
    var fiveYearAvgDividendYield: SortedMap<LocalDate, Double?> = TreeMap(),
    var trailingAnnualDividendYield: SortedMap<LocalDate, Double?> = TreeMap(),
    var payoutRatioP: SortedMap<LocalDate, Double?> = TreeMap(),
    var dividends: SortedMap<LocalDate, Double?> = TreeMap(),
    var dividendsGrowth: SortedMap<LocalDate, Double?> = TreeMap(),

    var epsQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var eps: SortedMap<LocalDate, Double?> = TreeMap(),
    var epsGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var epsGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var bookValuePerShare: SortedMap<LocalDate, Double?> = TreeMap(),
    var bookValuePerShareGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var freeCashFlowPerShare: SortedMap<LocalDate, Double?> = TreeMap(),
    var freeCashFlowPerShareGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var peQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var pe: SortedMap<LocalDate, Double?> = TreeMap(),
    var peGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var peGrowth: SortedMap<LocalDate, Double?> = TreeMap(),

    var revenueQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var revenue: SortedMap<LocalDate, Long?> = TreeMap(),
    var revenueGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var revenueGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var grossIncomeQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var grossIncome: SortedMap<LocalDate, Long?> = TreeMap(),
    var grossIncomeGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var grossIncomeGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var grossMargin: SortedMap<LocalDate, Double?> = TreeMap(),
    var grossMarginGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var ebitQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var ebit: SortedMap<LocalDate, Long?> = TreeMap(),
    var ebitGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var ebitGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var interestExpenseQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var interestExpense: SortedMap<LocalDate, Double?> = TreeMap(),
    var interestExpenseToOperativeIncomePQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var interestExpenseToOperativeIncomeP: SortedMap<LocalDate, Double?> = TreeMap(),
    var interestExpenseToOperativeIncomeGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var interestExpenseToOperativeIncomeGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var operatingIncomeQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var operatingIncome: SortedMap<LocalDate, Double?> = TreeMap(),
    var operatingIncomeGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var operatingIncomeGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var operatingMargin: SortedMap<LocalDate, Double?> = TreeMap(),
    var operatingMarginGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var netIncomeQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var netIncome: SortedMap<LocalDate, Long?> = TreeMap(),
    var netIncomeGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var netIncomeGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var profitMarginPQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var profitMarginP: SortedMap<LocalDate, Double?> = TreeMap(),
    var profitMarginGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var profitMarginGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var capitalExpendituresQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var capitalExpenditures: SortedMap<LocalDate, Long?> = TreeMap(),
    var capitalExpendituresGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var capitalExpendituresGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var operatingCashFlowQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var operatingCashFlow: SortedMap<LocalDate, Long?> = TreeMap(),
    var operatingCashFlowGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var operatingCashFlowGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var freeCashFlowQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var freeCashFlow: SortedMap<LocalDate, Long?> = TreeMap(),
    var freeCashFlowGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var freeCashFlowGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var cashQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var cash: SortedMap<LocalDate, Long?> = TreeMap(),
    var cashGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var cashGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var cashAndCashEquivalentsQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var cashAndCashEquivalents: SortedMap<LocalDate, Long?> = TreeMap(),
    var cashAndCashEquivalentsGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var cashAndCashEquivalentsGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var inventoryQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var inventory: SortedMap<LocalDate, Long?> = TreeMap(),
    var inventoryGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var inventoryGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var currentAssetsQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var currentAssets: SortedMap<LocalDate, Long?> = TreeMap(),
    var currentAssetsGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var currentAssetsGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var currentLiabilitiesQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var currentLiabilities: SortedMap<LocalDate, Long?> = TreeMap(),
    var currentLiabilitiesGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var currentLiabilitiesGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var workingCapitalQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var workingCapital: SortedMap<LocalDate, Double?> = TreeMap(),
    var workingCapitalGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var workingCapitalGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var currentRatioQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var currentRatio: SortedMap<LocalDate, Double?> = TreeMap(),
    var currentRatioGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var currentRatioGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    @Deprecated("use totalDebtQ")
    var totalLiabilitiesQ: SortedMap<LocalDate, Long?> = TreeMap(),
    @Deprecated("use totalDebt")
    var totalLiabilities: SortedMap<LocalDate, Long?> = TreeMap(),
    var totalDebtQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var totalDebt: SortedMap<LocalDate, Long?> = TreeMap(),
    var totalLiabilitiesGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalDebtGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalLiabilitiesGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalDebtGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalDebtToEquityQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalDebtToEquity: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalDebtToEquityGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalDebtToEquityGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var nonCurrentLiabilitiesToIncomeQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var nonCurrentLiabilitiesToIncome: SortedMap<LocalDate, Double?> = TreeMap(),
    var nonCurrentLiabilitiesToIncomeGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var nonCurrentLiabilitiesToIncomeGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalAssetsQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var totalAssets: SortedMap<LocalDate, Long?> = TreeMap(),
    var totalAssetsGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalAssetsGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalShareholdersEquityQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var totalShareholdersEquity: SortedMap<LocalDate, Long?> = TreeMap(),
    var totalShareholdersEquityGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var totalShareholdersEquityGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var retainedEarningsQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var retainedEarnings: SortedMap<LocalDate, Long?> = TreeMap(),
    var retainedEarningsGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var retainedEarningsGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var stockRepurchasedQ: SortedMap<LocalDate, Long?> = TreeMap(),
    var stockRepurchased: SortedMap<LocalDate, Long?> = TreeMap(),
    var stockRepurchasedGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var stockRepurchasedGrowth: SortedMap<LocalDate, Double?> = TreeMap(),

    var currentShares: SortedMap<LocalDate, Double?> = TreeMap(),
    var shares: SortedMap<LocalDate, Double?> = TreeMap(),
    var sharesGrowth: SortedMap<LocalDate, Double?> = TreeMap(),

    var roicPQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var roicP: SortedMap<LocalDate, Double?> = TreeMap(),
    var roicGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var roicGrowth: SortedMap<LocalDate, Double?> = TreeMap(),

    var roePQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var roeP: SortedMap<LocalDate, Double?> = TreeMap(),
    var roeGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var roeGrowth: SortedMap<LocalDate, Double?> = TreeMap(),

    var roaPQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var roaP: SortedMap<LocalDate, Double?> = TreeMap(),
    var roaGrowthQ: SortedMap<LocalDate, Double?> = TreeMap(),
    var roaGrowth: SortedMap<LocalDate, Double?> = TreeMap(),

    var growthEstimate5y: SortedMap<LocalDate, Double?> = TreeMap(),

    //Rule 1 calc
    var roic1Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var roic3Y: SortedMap<LocalDate, Double?> = TreeMap(),

    var revenue1Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var revenue3Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var revenue5Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var revenue9Y: SortedMap<LocalDate, Double?> = TreeMap(),

    var eps1Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var eps3Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var eps5Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var eps9Y: SortedMap<LocalDate, Double?> = TreeMap(),

    var bps1Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var bps3Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var bps5Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var bps9Y: SortedMap<LocalDate, Double?> = TreeMap(),

    var cash1Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var cash3Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var cash5Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var cash9Y: SortedMap<LocalDate, Double?> = TreeMap(),

    var pe1Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var pe3Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var pe5Y: SortedMap<LocalDate, Double?> = TreeMap(),
    var pe9Y: SortedMap<LocalDate, Double?> = TreeMap(),

    var rule1GrowthRate: SortedMap<LocalDate, Double?> = TreeMap(),
    var defaultPE: SortedMap<LocalDate, Double?> = TreeMap(),
    var historicalPE: SortedMap<LocalDate, Double?> = TreeMap(),
    var rule1PE: SortedMap<LocalDate, Double?> = TreeMap(),
    var currentEps: SortedMap<LocalDate, Double?> = TreeMap(),
    var futureEPS10Years: SortedMap<LocalDate, Double?> = TreeMap(),
    var futurePrice10Years: SortedMap<LocalDate, Double?> = TreeMap(),

    var stickerPrice15pcGrowth: SortedMap<LocalDate, Double?> = TreeMap(),
    var stickerPrice5pcGrowth: SortedMap<LocalDate, Double?> = TreeMap(),

    var belowStickerPrice15P: SortedMap<LocalDate, Double?> = TreeMap(),
    var belowStickerPrice5P: SortedMap<LocalDate, Double?> = TreeMap(),

    var price: SortedMap<LocalDate, Double?> = TreeMap()

) {
    @JsonIgnore
    override fun toString(): String {
        return "$symbol:$exchange"
    }

    @JsonIgnore
    fun getTicker(): Ticker {
        return Ticker(this.symbol, this.exchange)
    }
}