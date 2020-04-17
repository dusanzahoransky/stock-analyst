package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import com.github.dusanzahoransky.stockanalyst.model.yahoo.PeriodMeasure
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class StockInfo(
    @Id var id: String? = null,
    val date: LocalDate = LocalDate.now(),
    val symbol: String,
    val exchange: Exchange? = null,
    var companyName: String? = null,
    var price: Double? = null,
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

    var quarterlyRevenueGrowth: Double? = null,
    var quarterlyEarningsGrowth: Double? = null,
    var priceEarningGrowth: Double? = null,
    var trailingPriceEarningGrowth: Double? = null,
    //yearly earnings growth

    var week52Change: Double? = null,
    var week52Low: Double? = null,
    var week52AboveLowPercent: Double? = null,
    var week52High: Double? = null,
    var week52BelowHighPercent: Double? = null,

    var exDividendDate: String? = null,
    var fiveYearAvgDividendYield: Double? = null,
    var trailingAnnualDividendYield: Double? = null,

    /*
    % of Shares Held by All Insider
% of Shares Held by Institutions
     */

    /*
    Growth Estimates -
Current Year
Next Year
Past 5 Years (per annum)
Next 5 Years (per annum)
     */

    /*
     * Net Income (Earnings) Growth
     * Debth
     * Debth growth
     * Long term debth
     * Inventory growth
     */

    var periodValuationMeasures: Map<String, PeriodMeasure> = mutableMapOf()
)