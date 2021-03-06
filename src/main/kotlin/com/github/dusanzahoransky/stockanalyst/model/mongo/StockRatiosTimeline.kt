package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.*

/**
 * Simplified data processed from keyRatiosFinancials
 */
@Document
class StockRatiosTimeline(
    @Id var id: String? = null,
    val symbol: String,
    val exchange: Exchange,
    var date: LocalDate = LocalDate.now(),

    //cumulative growth rates

    var periods: SortedMap<LocalDate, Ratios> = TreeMap()
)

data class Ratios(
    var bookValuePerShare: Double? = null,
    var capSpending: Double? = null,
    var dividends: Double? = null,
    var earningsPerShare: Double? = null,
    var freeCashFlow: Double? = null,
    var freeCashFlowPerShare: Double? = null,
    var grossMargin: Double? = null,
    var netIncome: Double? = null,
    var operatingCashFlow: Double? = null,
    var operatingIncome: Double? = null,
    var operatingMargin: Double? = null,
    var payoutRatio: Double? = null,
    var revenue: Double? = null,
    var shares: Double? = null,
    var workingCapital: Double? = null,
    /**
     * merged from yahoo finance chart API
     */
    var price: Double? = null,
    var pe: Double? = null
)