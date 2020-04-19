package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import com.github.dusanzahoransky.stockanalyst.model.yahoo.StocksAveragesCounter
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.sum
import org.springframework.stereotype.Service

@Service
class StockAnalysisService {

    fun calcStocksAverages(stocks: List<StockInfo>): StockInfo {

        val counter = StocksAveragesCounter(StockInfo(symbol = "AVERAGES"))
        val averages = counter.averages

        for (stock in stocks) {
            if (stock.enterpriseValue != null) {
                averages.enterpriseValue = averages.enterpriseValue.let { sum(it, stock.enterpriseValue) }
                counter.enterpriseValueCount++
            }
            if (stock.trailingPE != null) {
                averages.trailingPE = averages.trailingPE.let { sum(it, stock.trailingPE) }
                counter.trailingPECount++
            }
            if (stock.forwardPE != null) {
                averages.forwardPE = averages.forwardPE.let { sum(it, stock.forwardPE) }
                counter.forwardPECount++
            }
            if (stock.priceEarningGrowth != null) {
                averages.priceEarningGrowth = averages.priceEarningGrowth.let { sum(it, stock.priceEarningGrowth) }
                counter.priceEarningGrowthCount++
            }
            if (stock.priceToSalesTrailing12Months != null) {
                averages.priceToSalesTrailing12Months = averages.priceToSalesTrailing12Months.let { sum(it, stock.priceToSalesTrailing12Months) }
                counter.priceSalesCount++
            }
            if (stock.priceBook != null) {
                averages.priceBook = averages.priceBook.let { sum(it, stock.priceBook) }
                counter.priceBookCount++
            }
            if (stock.enterpriseValueRevenue != null) {
                averages.enterpriseValueRevenue = averages.enterpriseValueRevenue.let { sum(it, stock.enterpriseValueRevenue) }
                counter.enterpriseValueRevenueCount++
            }
            if (stock.enterpriseValueEBITDA != null) {
                averages.enterpriseValueEBITDA = averages.enterpriseValueEBITDA.let { sum(it, stock.enterpriseValueEBITDA) }
                counter.enterpriseValueEBITDACount++
            }
            if (stock.price != null) {
                averages.price = averages.price.let { sum(it, stock.price) }
                counter.priceCount++
            }
            if (stock.change != null) {
                averages.change = averages.change.let { sum(it, stock.change) }
                counter.changeCount++
            }
            if (stock.belowTargetLowPricePercent != null) {
                averages.belowTargetLowPricePercent = averages.belowTargetLowPricePercent.let { sum(it, stock.belowTargetLowPricePercent) }
                counter.belowTargetLowPricePercentCount++
            }
            if (stock.belowTargetMedianPricePercent != null) {
                averages.belowTargetMedianPricePercent = averages.belowTargetMedianPricePercent.let { sum(it, stock.belowTargetMedianPricePercent) }
                counter.belowTargetMedianPricePercentCount++
            }
            if (stock.totalCashPerShare != null) {
                averages.totalCashPerShare = averages.totalCashPerShare.let { sum(it, stock.totalCashPerShare) }
                counter.totalCashPerShareCount++
            }
            if (stock.totalCashPerSharePercent != null) {
                averages.totalCashPerSharePercent = averages.totalCashPerSharePercent.let { sum(it, stock.totalCashPerSharePercent) }
                counter.totalCashPerSharePercentCount++
            }
            if (stock.totalDebtEquity != null) {
                averages.totalDebtEquity = averages.totalDebtEquity.let { sum(it, stock.totalDebtEquity) }
                counter.totalDebtEquityCount++
            }
            if (stock.yoyQuarterlyRevenueGrowthPercent != null) {
                averages.yoyQuarterlyRevenueGrowthPercent = averages.yoyQuarterlyRevenueGrowthPercent.let { sum(it, stock.yoyQuarterlyRevenueGrowthPercent) }
                counter.revenueGrowthCount++
            }
            if (stock.earningsGrowthPercent != null) {
                averages.earningsGrowthPercent = averages.earningsGrowthPercent.let { sum(it, stock.earningsGrowthPercent) }
                counter.earningsGrowthPercentCount++
            }
            if (stock.yoyQuarterlyEarningsGrowthPercent != null) {
                averages.yoyQuarterlyEarningsGrowthPercent = averages.yoyQuarterlyEarningsGrowthPercent.let { sum(it, stock.yoyQuarterlyEarningsGrowthPercent) }
                counter.yoyQuarterlyEarningsGrowthPercentCount++
            }
            if (stock.quarterlyRevenueGrowth != null) {
                averages.quarterlyRevenueGrowth = averages.quarterlyRevenueGrowth.let { sum(it, stock.quarterlyRevenueGrowth) }
                counter.yoyQuarterlyRevenueGrowthPercentCount++
            }
            if (stock.yoyQuarterlyEarningsGrowthPercent != null) {
                averages.yoyQuarterlyEarningsGrowthPercent = averages.yoyQuarterlyEarningsGrowthPercent.let { sum(it, stock.yoyQuarterlyEarningsGrowthPercent) }
                counter.yoyQuarterlyEarningsGrowthPercentCount++
            }
            if (stock.week52Change != null) {
                averages.week52Change = averages.week52Change.let { sum(it, stock.week52Change) }
                counter.week52ChangeCount++
            }
            if (stock.week52Low != null) {
                averages.week52Low = averages.week52Low.let { sum(it, stock.week52Low) }
                counter.week52LowCount++
            }
            if (stock.week52AboveLowPercent != null) {
                averages.week52AboveLowPercent = averages.week52AboveLowPercent.let { sum(it, stock.week52AboveLowPercent) }
                counter.week52AboveLowPercentCount++
            }
            if (stock.week52High != null) {
                averages.week52High = averages.week52High.let { sum(it, stock.week52High) }
                counter.week52HighCount++
            }
            if (stock.week52BelowHighPercent != null) {
                averages.week52BelowHighPercent = averages.week52BelowHighPercent.let { sum(it, stock.week52BelowHighPercent) }
                counter.week52BelowHighPercentCount++
            }
            if (stock.trailingPriceEarningGrowth != null) {
                averages.trailingPriceEarningGrowth = averages.trailingPriceEarningGrowth.let { sum(it, stock.trailingPriceEarningGrowth) }
                counter.trailingPriceEarningGrowthCount++
            }
            if (stock.priceToSalesTrailing12Months != null) {
                averages.priceToSalesTrailing12Months = averages.priceToSalesTrailing12Months.let { sum(it, stock.priceToSalesTrailing12Months) }
                counter.priceToSalesTrailing12MonthsCount++
            }
            if (stock.fiveYearAvgDividendYield != null) {
                averages.fiveYearAvgDividendYield = averages.fiveYearAvgDividendYield.let { sum(it, stock.fiveYearAvgDividendYield) }
                counter.fiveYearAvgDividendYieldCount++
            }
            if (stock.trailingAnnualDividendYield != null) {
                averages.trailingAnnualDividendYield = averages.trailingAnnualDividendYield.let { sum(it, stock.trailingAnnualDividendYield) }
                counter.trailingAnnualDividendYieldCount++
            }
            if (stock.sharesShortPrevMonthCompare != null) {
                averages.sharesShortPrevMonthCompare = averages.sharesShortPrevMonthCompare.let { sum(it, stock.sharesShortPrevMonthCompare) }
                counter.sharesShortPrevMonthCompareCount++
            }
            if (stock.shortToFloat != null) {
                averages.shortToFloat = averages.shortToFloat.let { sum(it, stock.shortToFloat) }
                counter.shortToFloatCount++
            }
        }

        averages.price = averages.price.let { div(it, counter.priceCount) }
        averages.change = averages.change.let { div(it, counter.changeCount) }
        averages.belowTargetMedianPricePercent = averages.belowTargetMedianPricePercent.let { div(it, counter.belowTargetMedianPricePercentCount) }
        averages.belowTargetLowPricePercent = averages.belowTargetLowPricePercent.let { div(it, counter.belowTargetLowPricePercentCount) }
        averages.totalCashPerShare = averages.totalCashPerShare.let { div(it, counter.totalCashPerShareCount) }
        averages.totalCashPerSharePercent = averages.totalCashPerSharePercent.let { div(it, counter.totalCashPerSharePercentCount) }
        averages.totalDebtEquity = averages.totalDebtEquity.let { div(it, counter.totalDebtEquityCount) }
        averages.yoyQuarterlyRevenueGrowthPercent = averages.yoyQuarterlyRevenueGrowthPercent.let { div(it, counter.revenueGrowthCount) }
        averages.earningsGrowthPercent = averages.earningsGrowthPercent.let { div(it, counter.earningsGrowthPercentCount) }
        averages.yoyQuarterlyEarningsGrowthPercent = averages.yoyQuarterlyEarningsGrowthPercent.let { div(it, counter.yoyQuarterlyEarningsGrowthPercentCount) }
        averages.quarterlyRevenueGrowth = averages.quarterlyRevenueGrowth.let { div(it, counter.yoyQuarterlyRevenueGrowthPercentCount) }
        averages.yoyQuarterlyEarningsGrowthPercent = averages.yoyQuarterlyEarningsGrowthPercent.let { div(it, counter.yoyQuarterlyEarningsGrowthPercentCount) }
        averages.week52Change = averages.week52Change.let { div(it, counter.week52ChangeCount) }
        averages.week52Low = averages.week52Low.let { div(it, counter.week52LowCount) }
        averages.week52High = averages.week52High.let { div(it, counter.week52HighCount) }
        averages.week52BelowHighPercent = averages.week52BelowHighPercent.let { div(it, counter.week52BelowHighPercentCount) }
        averages.week52AboveLowPercent = averages.week52AboveLowPercent.let { div(it, counter.week52AboveLowPercentCount) }
        averages.enterpriseValue = averages.enterpriseValue.let { div(it, counter.enterpriseValueCount) }
        averages.trailingPE = averages.trailingPE.let { div(it, counter.trailingPECount) }
        averages.forwardPE = averages.forwardPE.let { div(it, counter.forwardPECount) }
        averages.priceEarningGrowth = averages.priceEarningGrowth.let { div(it, counter.priceEarningGrowthCount) }
        averages.priceToSalesTrailing12Months = averages.priceToSalesTrailing12Months.let { div(it, counter.priceSalesCount) }
        averages.priceBook = averages.priceBook.let { div(it, counter.priceBookCount) }
        averages.enterpriseValueRevenue = averages.enterpriseValueRevenue.let { div(it, counter.enterpriseValueRevenueCount) }
        averages.enterpriseValueEBITDA = averages.enterpriseValueEBITDA.let { div(it, counter.enterpriseValueEBITDACount) }
        averages.trailingPriceEarningGrowth = averages.trailingPriceEarningGrowth.let { div(it, counter.trailingPriceEarningGrowthCount) }
        averages.priceToSalesTrailing12Months = averages.priceToSalesTrailing12Months.let { div(it, counter.priceToSalesTrailing12MonthsCount) }
        averages.fiveYearAvgDividendYield = averages.fiveYearAvgDividendYield.let { div(it, counter.fiveYearAvgDividendYieldCount) }
        averages.trailingAnnualDividendYield = averages.trailingAnnualDividendYield.let { div(it, counter.trailingAnnualDividendYieldCount) }
        averages.sharesShortPrevMonthCompare = averages.sharesShortPrevMonthCompare.let { div(it, counter.sharesShortPrevMonthCompareCount) }
        averages.shortToFloat = averages.shortToFloat.let { div(it, counter.shortToFloatCount) }

        return averages
    }


}