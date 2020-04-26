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
                averages.enterpriseValue = sum(averages.enterpriseValue, stock.enterpriseValue)
                counter.enterpriseValueCount++
            }
            if (stock.trailingPE != null) {
                averages.trailingPE = sum(averages.trailingPE, stock.trailingPE)
                counter.trailingPECount++
            }
            if (stock.forwardPE != null) {
                averages.forwardPE = sum(averages.forwardPE, stock.forwardPE)
                counter.forwardPECount++
            }
            if (stock.priceEarningGrowth != null) {
                averages.priceEarningGrowth = sum(averages.priceEarningGrowth, stock.priceEarningGrowth)
                counter.priceEarningGrowthCount++
            }
            if (stock.priceToSalesTrailing12Months != null) {
                averages.priceToSalesTrailing12Months = sum(averages.priceToSalesTrailing12Months, stock.priceToSalesTrailing12Months)
                counter.priceSalesCount++
            }
            if (stock.priceBook != null) {
                averages.priceBook = sum(averages.priceBook, stock.priceBook)
                counter.priceBookCount++
            }
            if (stock.enterpriseValueRevenue != null) {
                averages.enterpriseValueRevenue = sum(averages.enterpriseValueRevenue, stock.enterpriseValueRevenue)
                counter.enterpriseValueRevenueCount++
            }
            if (stock.enterpriseValueEBITDA != null) {
                averages.enterpriseValueEBITDA = sum(averages.enterpriseValueEBITDA, stock.enterpriseValueEBITDA)
                counter.enterpriseValueEBITDACount++
            }
            if (stock.price != null) {
                averages.price = sum(averages.price, stock.price)
                counter.priceCount++
            }
            if (stock.change != null) {
                averages.change = sum(averages.change, stock.change)
                counter.changeCount++
            }
            if (stock.belowTargetLowPricePercent != null) {
                averages.belowTargetLowPricePercent = sum(averages.belowTargetLowPricePercent, stock.belowTargetLowPricePercent)
                counter.belowTargetLowPricePercentCount++
            }
            if (stock.belowTargetMedianPricePercent != null) {
                averages.belowTargetMedianPricePercent = sum(averages.belowTargetMedianPricePercent, stock.belowTargetMedianPricePercent)
                counter.belowTargetMedianPricePercentCount++
            }
            if (stock.totalCashPerShare != null) {
                averages.totalCashPerShare = sum(averages.totalCashPerShare, stock.totalCashPerShare)
                counter.totalCashPerShareCount++
            }
            if (stock.totalCashPerSharePercent != null) {
                averages.totalCashPerSharePercent = sum(averages.totalCashPerSharePercent, stock.totalCashPerSharePercent)
                counter.totalCashPerSharePercentCount++
            }
            if (stock.totalDebtEquity != null) {
                averages.totalDebtEquity = sum(averages.totalDebtEquity, stock.totalDebtEquity)
                counter.totalDebtEquityCount++
            }
            if (stock.yoyQuarterlyRevenueGrowthPercent != null) {
                averages.yoyQuarterlyRevenueGrowthPercent = sum(averages.yoyQuarterlyRevenueGrowthPercent, stock.yoyQuarterlyRevenueGrowthPercent)
                counter.yoyQuarterlyRevenueGrowthPercentCount++
            }
            if (stock.week52Change != null) {
                averages.week52Change = sum(averages.week52Change, stock.week52Change)
                counter.week52ChangeCount++
            }
            if (stock.week52Low != null) {
                averages.week52Low = sum(averages.week52Low, stock.week52Low)
                counter.week52LowCount++
            }
            if (stock.week52AboveLowPercent != null) {
                averages.week52AboveLowPercent = sum(averages.week52AboveLowPercent, stock.week52AboveLowPercent)
                counter.week52AboveLowPercentCount++
            }
            if (stock.week52High != null) {
                averages.week52High = sum(averages.week52High, stock.week52High)
                counter.week52HighCount++
            }
            if (stock.week52BelowHighPercent != null) {
                averages.week52BelowHighPercent = sum(averages.week52BelowHighPercent, stock.week52BelowHighPercent)
                counter.week52BelowHighPercentCount++
            }
            if (stock.trailingPriceEarningGrowth != null) {
                averages.trailingPriceEarningGrowth = sum(averages.trailingPriceEarningGrowth, stock.trailingPriceEarningGrowth)
                counter.trailingPriceEarningGrowthCount++
            }
            if (stock.priceToSalesTrailing12Months != null) {
                averages.priceToSalesTrailing12Months = sum(averages.priceToSalesTrailing12Months, stock.priceToSalesTrailing12Months)
                counter.priceToSalesTrailing12MonthsCount++
            }
            if (stock.fiveYearAvgDividendYield != null) {
                averages.fiveYearAvgDividendYield = sum(averages.fiveYearAvgDividendYield, stock.fiveYearAvgDividendYield)
                counter.fiveYearAvgDividendYieldCount++
            }
            if (stock.trailingAnnualDividendYield != null) {
                averages.trailingAnnualDividendYield = sum(averages.trailingAnnualDividendYield, stock.trailingAnnualDividendYield)
                counter.trailingAnnualDividendYieldCount++
            }
            if (stock.sharesShortPrevMonthCompare != null) {
                averages.sharesShortPrevMonthCompare = sum(averages.sharesShortPrevMonthCompare, stock.sharesShortPrevMonthCompare)
                counter.sharesShortPrevMonthCompareCount++
            }
            if (stock.shortToFloat != null) {
                averages.shortToFloat = sum(averages.shortToFloat, stock.shortToFloat)
                counter.shortToFloatCount++
            }

            if (stock.netIncomeLastQuarter != null) {
                averages.netIncomeLastQuarter = sum(averages.netIncomeLastQuarter, stock.netIncomeLastQuarter)
                counter.netIncomeLastQuarterCount++

            }
            if (stock.netIncome2QuartersAgo != null) {
                averages.netIncome2QuartersAgo = sum(averages.netIncome2QuartersAgo, stock.netIncome2QuartersAgo)
                counter.netIncome2QuartersAgoCount++

            }
            if (stock.netIncomeGrowthLastQuarter != null) {
                averages.netIncomeGrowthLastQuarter = sum(averages.netIncomeGrowthLastQuarter, stock.netIncomeGrowthLastQuarter)
                counter.netIncomeGrowthLastQuarterCount++

            }
            if (stock.netIncomeLastYear != null) {
                averages.netIncomeLastYear = sum(averages.netIncomeLastYear, stock.netIncomeLastYear)
                counter.netIncomeLastYearCount++

            }
            if (stock.netIncome2YearAgo != null) {
                averages.netIncome2YearAgo = sum(averages.netIncome2YearAgo, stock.netIncome2YearAgo)
                counter.netIncome2YearAgoCount++

            }
            if (stock.netIncome3YearAgo != null) {
                averages.netIncome3YearAgo = sum(averages.netIncome3YearAgo, stock.netIncome3YearAgo)
                counter.netIncome3YearAgoCount++

            }
            if (stock.netIncomeGrowthLastYear != null) {
                averages.netIncomeGrowthLastYear = sum(averages.netIncomeGrowthLastYear, stock.netIncomeGrowthLastYear)
                counter.netIncomeGrowthLastYearCount++

            }
            if (stock.netIncomeGrowthLast3Years != null) {
                averages.netIncomeGrowthLast3Years = sum(averages.netIncomeGrowthLast3Years, stock.netIncomeGrowthLast3Years)
                counter.netIncomeGrowthLast3YearsCount++

            }

            if (stock.revenueLastQuarter != null) {
                averages.revenueLastQuarter = sum(averages.revenueLastQuarter, stock.revenueLastQuarter)
                counter.revenueLastQuarterCount++

            }
            if (stock.revenueGrowthLastQuarter != null) {
                averages.revenueGrowthLastQuarter = sum(averages.revenueGrowthLastQuarter, stock.revenueGrowthLastQuarter)
                counter.revenueGrowthLastQuarterCount++

            }
            if (stock.revenueLastYear != null) {
                averages.revenueLastYear = sum(averages.revenueLastYear, stock.revenueLastYear)
                counter.revenueLastYearCount++

            }
            if (stock.revenueGrowthLastYear != null) {
                averages.revenueGrowthLastYear = sum(averages.revenueGrowthLastYear, stock.revenueGrowthLastYear)
                counter.revenueGrowthLastYearCount++

            }
            if (stock.revenueGrowthLast3Years != null) {
                averages.revenueGrowthLast3Years = sum(averages.revenueGrowthLast3Years, stock.revenueGrowthLast3Years)
                counter.revenueGrowthLast3YearsCount++

            }

            if (stock.cashLastQuarter != null) {
                averages.cashLastQuarter = sum(averages.cashLastQuarter, stock.cashLastQuarter)
                counter.cashLastQuarterCount++

            }
            if (stock.cashGrowthLastQuarter != null) {
                averages.cashGrowthLastQuarter = sum(averages.cashGrowthLastQuarter, stock.cashGrowthLastQuarter)
                counter.cashGrowthLastQuarterCount++

            }
            if (stock.cashLastYear != null) {
                averages.cashLastYear = sum(averages.cashLastYear, stock.cashLastYear)
                counter.cashLastYearCount++

            }
            if (stock.cashGrowthLastYear != null) {
                averages.cashGrowthLastYear = sum(averages.cashGrowthLastYear, stock.cashGrowthLastYear)
                counter.cashGrowthLastYearCount++

            }
            if (stock.cashGrowthLast3Years != null) {
                averages.cashGrowthLast3Years = sum(averages.cashGrowthLast3Years, stock.cashGrowthLast3Years)
                counter.cashGrowthLast3YearsCount++

            }

            if (stock.inventoryLastQuarter != null) {
                averages.inventoryLastQuarter = sum(averages.inventoryLastQuarter, stock.inventoryLastQuarter)
                counter.inventoryLastQuarterCount++

            }
            if (stock.inventoryGrowthLastQuarter != null) {
                averages.inventoryGrowthLastQuarter = sum(averages.inventoryGrowthLastQuarter, stock.inventoryGrowthLastQuarter)
                counter.inventoryGrowthLastQuarterCount++

            }
            if (stock.inventoryLastYear != null) {
                averages.inventoryLastYear = sum(averages.inventoryLastYear, stock.inventoryLastYear)
                counter.inventoryLastYearCount++

            }
            if (stock.inventoryGrowthLastYear != null) {
                averages.inventoryGrowthLastYear = sum(averages.inventoryGrowthLastYear, stock.inventoryGrowthLastYear)
                counter.inventoryGrowthLastYearCount++

            }
            if (stock.inventoryGrowthLast3Years != null) {
                averages.inventoryGrowthLast3Years = sum(averages.inventoryGrowthLast3Years, stock.inventoryGrowthLast3Years)
                counter.inventoryGrowthLast3YearsCount++

            }

            if (stock.currentLiabilitiesLastQuarter != null) {
                averages.currentLiabilitiesLastQuarter = sum(averages.currentLiabilitiesLastQuarter, stock.currentLiabilitiesLastQuarter)
                counter.currentLiabilitiesLastQuarterCount++

            }
            if (stock.currentLiabilitiesGrowthLastQuarter != null) {
                averages.currentLiabilitiesGrowthLastQuarter = sum(averages.currentLiabilitiesGrowthLastQuarter, stock.currentLiabilitiesGrowthLastQuarter)
                counter.currentLiabilitiesGrowthLastQuarterCount++

            }
            if (stock.currentLiabilitiesLastYear != null) {
                averages.currentLiabilitiesLastYear = sum(averages.currentLiabilitiesLastYear, stock.currentLiabilitiesLastYear)
                counter.currentLiabilitiesLastYearCount++

            }
            if (stock.currentLiabilitiesGrowthLastYear != null) {
                averages.currentLiabilitiesGrowthLastYear = sum(averages.currentLiabilitiesGrowthLastYear, stock.currentLiabilitiesGrowthLastYear)
                counter.currentLiabilitiesGrowthLastYearCount++

            }
            if (stock.currentLiabilitiesGrowthLast3Years != null) {
                averages.currentLiabilitiesGrowthLast3Years = sum(averages.currentLiabilitiesGrowthLast3Years, stock.currentLiabilitiesGrowthLast3Years)
                counter.currentLiabilitiesGrowthLast3YearsCount++

            }

            if (stock.totalLiabilitiesLastQuarter != null) {
                averages.totalLiabilitiesLastQuarter = sum(averages.totalLiabilitiesLastQuarter, stock.totalLiabilitiesLastQuarter)
                counter.totalLiabilitiesLastQuarterCount++

            }
            if (stock.totalLiabilitiesGrowthLastQuarter != null) {
                averages.totalLiabilitiesGrowthLastQuarter = sum(averages.totalLiabilitiesGrowthLastQuarter, stock.totalLiabilitiesGrowthLastQuarter)
                counter.totalLiabilitiesGrowthLastQuarterCount++

            }
            if (stock.totalLiabilitiesLastYear != null) {
                averages.totalLiabilitiesLastYear = sum(averages.totalLiabilitiesLastYear, stock.totalLiabilitiesLastYear)
                counter.totalLiabilitiesLastYearCount++

            }
            if (stock.totalLiabilitiesGrowthLastYear != null) {
                averages.totalLiabilitiesGrowthLastYear = sum(averages.totalLiabilitiesGrowthLastYear, stock.totalLiabilitiesGrowthLastYear)
                counter.totalLiabilitiesGrowthLastYearCount++

            }
            if (stock.totalLiabilitiesGrowthLast3Years != null) {
                averages.totalLiabilitiesGrowthLast3Years = sum(averages.totalLiabilitiesGrowthLast3Years, stock.totalLiabilitiesGrowthLast3Years)
                counter.totalLiabilitiesGrowthLast3YearsCount++

            }


            if (stock.totalShareholdersEquityLastQuarter != null) {
                averages.totalShareholdersEquityLastQuarter = sum(averages.totalShareholdersEquityLastQuarter, stock.totalShareholdersEquityLastQuarter)
                counter.totalShareholdersEquityLastQuarterCount++

            }
            if (stock.totalShareholdersEquityGrowthLastQuarter != null) {
                averages.totalShareholdersEquityGrowthLastQuarter = sum(averages.totalShareholdersEquityGrowthLastQuarter, stock.totalShareholdersEquityGrowthLastQuarter)
                counter.totalShareholdersEquityGrowthLastQuarterCount++

            }
            if (stock.totalShareholdersEquityLastYear != null) {
                averages.totalShareholdersEquityLastYear = sum(averages.totalShareholdersEquityLastYear, stock.totalShareholdersEquityLastYear)
                counter.totalShareholdersEquityLastYearCount++

            }
            if (stock.totalShareholdersEquityGrowthLastYear != null) {
                averages.totalShareholdersEquityGrowthLastYear = sum(averages.totalShareholdersEquityGrowthLastYear, stock.totalShareholdersEquityGrowthLastYear)
                counter.totalShareholdersEquityGrowthLastYearCount++

            }
            if (stock.totalShareholdersEquityGrowthLast3Years != null) {
                averages.totalShareholdersEquityGrowthLast3Years = sum(averages.totalShareholdersEquityGrowthLast3Years, stock.totalShareholdersEquityGrowthLast3Years)
                counter.totalShareholdersEquityGrowthLast3YearsCount++

            }


            if (stock.currentLiabilitiesToEquityLastQuarter != null) {
                averages.currentLiabilitiesToEquityLastQuarter = sum(averages.currentLiabilitiesToEquityLastQuarter, stock.currentLiabilitiesToEquityLastQuarter)
                counter.currentLiabilitiesToEquityLastQuarterCount++

            }
            if (stock.currentLiabilitiesToEquityLastYear != null) {
                averages.currentLiabilitiesToEquityLastYear = sum(averages.currentLiabilitiesToEquityLastYear, stock.currentLiabilitiesToEquityLastYear)
                counter.currentLiabilitiesToEquityLastYearCount++

            }
            if (stock.currentLiabilitiesToEquityGrowthLastQuarter != null) {
                averages.currentLiabilitiesToEquityGrowthLastQuarter = sum(averages.currentLiabilitiesToEquityGrowthLastQuarter, stock.currentLiabilitiesToEquityGrowthLastQuarter)
                counter.currentLiabilitiesToEquityGrowthLastQuarterCount++

            }
            if (stock.currentLiabilitiesToEquityGrowthLastYear != null) {
                averages.currentLiabilitiesToEquityGrowthLastYear = sum(averages.currentLiabilitiesToEquityGrowthLastYear, stock.currentLiabilitiesToEquityGrowthLastYear)
                counter.currentLiabilitiesToEquityGrowthLastYearCount++

            }
            if (stock.currentLiabilitiesToEquityGrowthLast3Years != null) {
                averages.currentLiabilitiesToEquityGrowthLast3Years = sum(averages.currentLiabilitiesToEquityGrowthLast3Years, stock.currentLiabilitiesToEquityGrowthLast3Years)
                counter.currentLiabilitiesToEquityGrowthLast3YearsCount++

            }

            if (stock.totalLiabilitiesToEquityLastQuarter != null) {
                averages.totalLiabilitiesToEquityLastQuarter = sum(averages.totalLiabilitiesToEquityLastQuarter, stock.totalLiabilitiesToEquityLastQuarter)
                counter.totalLiabilitiesToEquityLastQuarterCount++

            }
            if (stock.totalLiabilitiesToEquityLastYear != null) {
                averages.totalLiabilitiesToEquityLastYear = sum(averages.totalLiabilitiesToEquityLastYear, stock.totalLiabilitiesToEquityLastYear)
                counter.totalLiabilitiesToEquityLastYearCount++

            }
            if (stock.totalLiabilitiesToEquityGrowthLastQuarter != null) {
                averages.totalLiabilitiesToEquityGrowthLastQuarter = sum(averages.totalLiabilitiesToEquityGrowthLastQuarter, stock.totalLiabilitiesToEquityGrowthLastQuarter)
                counter.totalLiabilitiesToEquityGrowthLastQuarterCount++

            }
            if (stock.totalLiabilitiesToEquityGrowthLastYear != null) {
                averages.totalLiabilitiesToEquityGrowthLastYear = sum(averages.totalLiabilitiesToEquityGrowthLastYear, stock.totalLiabilitiesToEquityGrowthLastYear)
                counter.totalLiabilitiesToEquityGrowthLastYearCount++

            }
            if (stock.totalLiabilitiesToEquityGrowthLast3Years != null) {
                averages.totalLiabilitiesToEquityGrowthLast3Years = sum(averages.totalLiabilitiesToEquityGrowthLast3Years, stock.totalLiabilitiesToEquityGrowthLast3Years)
                counter.totalLiabilitiesToEquityGrowthLast3YearsCount++

            }

            if (stock.stockRepurchasedLastQuarter != null) {
                averages.stockRepurchasedLastQuarter = sum(averages.stockRepurchasedLastQuarter, stock.stockRepurchasedLastQuarter)
                counter.stockRepurchasedLastQuarterCount++

            }
            if (stock.stockRepurchasedGrowthLastQuarter != null) {
                averages.stockRepurchasedGrowthLastQuarter = sum(averages.stockRepurchasedGrowthLastQuarter, stock.stockRepurchasedGrowthLastQuarter)
                counter.stockRepurchasedGrowthLastQuarterCount++

            }
            if (stock.stockRepurchasedLastYear != null) {
                averages.stockRepurchasedLastYear = sum(averages.stockRepurchasedLastYear, stock.stockRepurchasedLastYear)
                counter.stockRepurchasedLastYearCount++

            }
            if (stock.stockRepurchasedGrowthLastYear != null) {
                averages.stockRepurchasedGrowthLastYear = sum(averages.stockRepurchasedGrowthLastYear, stock.stockRepurchasedGrowthLastYear)
                counter.stockRepurchasedGrowthLastYearCount++

            }
            if (stock.stockRepurchasedGrowthLast3Years != null) {
                averages.stockRepurchasedGrowthLast3Years = sum(averages.stockRepurchasedGrowthLast3Years, stock.stockRepurchasedGrowthLast3Years)
                counter.stockRepurchasedGrowthLast3YearsCount++

            }

            if (stock.stockLastQuarter != null) {
                averages.stockLastQuarter = sum(averages.stockLastQuarter, stock.stockLastQuarter)
                counter.stockLastQuarterCount++

            }
            if (stock.stockGrowthLastQuarter != null) {
                averages.stockGrowthLastQuarter = sum(averages.stockGrowthLastQuarter, stock.stockGrowthLastQuarter)
                counter.stockGrowthLastQuarterCount++

            }
            if (stock.stockLastYear != null) {
                averages.stockLastYear = sum(averages.stockLastYear, stock.stockLastYear)
                counter.stockLastYearCount++

            }
            if (stock.stockGrowthLastYear != null) {
                averages.stockGrowthLastYear = sum(averages.stockGrowthLastYear, stock.stockGrowthLastYear)
                counter.stockGrowthLastYearCount++

            }
            if (stock.stockGrowthLast3Years != null) {
                averages.stockGrowthLast3Years = sum(averages.stockGrowthLast3Years, stock.stockGrowthLast3Years)
                counter.stockGrowthLast3YearsCount++

            }
        }

        averages.price = div(averages.price, counter.priceCount)
        averages.change = div(averages.change, counter.changeCount)
        averages.belowTargetMedianPricePercent = div(averages.belowTargetMedianPricePercent, counter.belowTargetMedianPricePercentCount)
        averages.belowTargetLowPricePercent = div(averages.belowTargetLowPricePercent, counter.belowTargetLowPricePercentCount)
        averages.totalCashPerShare = div(averages.totalCashPerShare, counter.totalCashPerShareCount)
        averages.totalCashPerSharePercent = div(averages.totalCashPerSharePercent, counter.totalCashPerSharePercentCount)
        averages.totalDebtEquity = div(averages.totalDebtEquity, counter.totalDebtEquityCount)
        averages.yoyQuarterlyRevenueGrowthPercent = div(averages.yoyQuarterlyRevenueGrowthPercent, counter.yoyQuarterlyRevenueGrowthPercentCount)
        averages.week52Change = div(averages.week52Change, counter.week52ChangeCount)
        averages.week52Low = div(averages.week52Low, counter.week52LowCount)
        averages.week52High = div(averages.week52High, counter.week52HighCount)
        averages.week52BelowHighPercent = div(averages.week52BelowHighPercent, counter.week52BelowHighPercentCount)
        averages.week52AboveLowPercent = div(averages.week52AboveLowPercent, counter.week52AboveLowPercentCount)
        averages.enterpriseValue = div(averages.enterpriseValue, counter.enterpriseValueCount)
        averages.trailingPE = div(averages.trailingPE, counter.trailingPECount)
        averages.forwardPE = div(averages.forwardPE, counter.forwardPECount)
        averages.priceEarningGrowth = div(averages.priceEarningGrowth, counter.priceEarningGrowthCount)
        averages.priceToSalesTrailing12Months = div(averages.priceToSalesTrailing12Months, counter.priceSalesCount)
        averages.priceBook = div(averages.priceBook, counter.priceBookCount)
        averages.enterpriseValueRevenue = div(averages.enterpriseValueRevenue, counter.enterpriseValueRevenueCount)
        averages.enterpriseValueEBITDA = div(averages.enterpriseValueEBITDA, counter.enterpriseValueEBITDACount)
        averages.trailingPriceEarningGrowth = div(averages.trailingPriceEarningGrowth, counter.trailingPriceEarningGrowthCount)
        averages.priceToSalesTrailing12Months = div(averages.priceToSalesTrailing12Months, counter.priceToSalesTrailing12MonthsCount)
        averages.fiveYearAvgDividendYield = div(averages.fiveYearAvgDividendYield, counter.fiveYearAvgDividendYieldCount)
        averages.trailingAnnualDividendYield = div(averages.trailingAnnualDividendYield, counter.trailingAnnualDividendYieldCount)
        averages.sharesShortPrevMonthCompare = div(averages.sharesShortPrevMonthCompare, counter.sharesShortPrevMonthCompareCount)
        averages.shortToFloat = div(averages.shortToFloat, counter.shortToFloatCount)
        averages.netIncomeLastQuarter = div(averages.netIncomeLastQuarter, counter.netIncomeLastQuarterCount)
        averages.netIncome2QuartersAgo = div(averages.netIncome2QuartersAgo, counter.netIncome2QuartersAgoCount)
        averages.netIncomeGrowthLastQuarter = div(averages.netIncomeGrowthLastQuarter, counter.netIncomeGrowthLastQuarterCount)
        averages.netIncomeLastYear = div(averages.netIncomeLastYear, counter.netIncomeLastYearCount)
        averages.netIncome2YearAgo = div(averages.netIncome2YearAgo, counter.netIncome2YearAgoCount)
        averages.netIncome3YearAgo = div(averages.netIncome3YearAgo, counter.netIncome3YearAgoCount)
        averages.netIncomeGrowthLastYear = div(averages.netIncomeGrowthLastYear, counter.netIncomeGrowthLastYearCount)
        averages.netIncomeGrowthLast3Years = div(averages.netIncomeGrowthLast3Years, counter.netIncomeGrowthLast3YearsCount)
        averages.revenueLastQuarter = div(averages.revenueLastQuarter, counter.revenueLastQuarterCount)
        averages.revenueGrowthLastQuarter = div(averages.revenueGrowthLastQuarter, counter.revenueGrowthLastQuarterCount)
        averages.revenueLastYear = div(averages.revenueLastYear, counter.revenueLastYearCount)
        averages.revenueGrowthLastYear = div(averages.revenueGrowthLastYear, counter.revenueGrowthLastYearCount)
        averages.revenueGrowthLast3Years = div(averages.revenueGrowthLast3Years, counter.revenueGrowthLast3YearsCount)
        averages.cashLastQuarter = div(averages.cashLastQuarter, counter.cashLastQuarterCount)
        averages.cashGrowthLastQuarter = div(averages.cashGrowthLastQuarter, counter.cashGrowthLastQuarterCount)
        averages.cashLastYear = div(averages.cashLastYear, counter.cashLastYearCount)
        averages.cashGrowthLastYear = div(averages.cashGrowthLastYear, counter.cashGrowthLastYearCount)
        averages.cashGrowthLast3Years = div(averages.cashGrowthLast3Years, counter.cashGrowthLast3YearsCount)
        averages.inventoryLastQuarter = div(averages.inventoryLastQuarter, counter.inventoryLastQuarterCount)
        averages.inventoryGrowthLastQuarter = div(averages.inventoryGrowthLastQuarter, counter.inventoryGrowthLastQuarterCount)
        averages.inventoryLastYear = div(averages.inventoryLastYear, counter.inventoryLastYearCount)
        averages.inventoryGrowthLastYear = div(averages.inventoryGrowthLastYear, counter.inventoryGrowthLastYearCount)
        averages.inventoryGrowthLast3Years = div(averages.inventoryGrowthLast3Years, counter.inventoryGrowthLast3YearsCount)
        averages.currentLiabilitiesLastQuarter = div(averages.currentLiabilitiesLastQuarter, counter.currentLiabilitiesLastQuarterCount)
        averages.currentLiabilitiesGrowthLastQuarter = div(averages.currentLiabilitiesGrowthLastQuarter, counter.currentLiabilitiesGrowthLastQuarterCount)
        averages.currentLiabilitiesLastYear = div(averages.currentLiabilitiesLastYear, counter.currentLiabilitiesLastYearCount)
        averages.currentLiabilitiesGrowthLastYear = div(averages.currentLiabilitiesGrowthLastYear, counter.currentLiabilitiesGrowthLastYearCount)
        averages.currentLiabilitiesGrowthLast3Years = div(averages.currentLiabilitiesGrowthLast3Years, counter.currentLiabilitiesGrowthLast3YearsCount)
        averages.totalLiabilitiesLastQuarter = div(averages.totalLiabilitiesLastQuarter, counter.totalLiabilitiesLastQuarterCount)
        averages.totalLiabilitiesGrowthLastQuarter = div(averages.totalLiabilitiesGrowthLastQuarter, counter.totalLiabilitiesGrowthLastQuarterCount)
        averages.totalLiabilitiesLastYear = div(averages.totalLiabilitiesLastYear, counter.totalLiabilitiesLastYearCount)
        averages.totalLiabilitiesGrowthLastYear = div(averages.totalLiabilitiesGrowthLastYear, counter.totalLiabilitiesGrowthLastYearCount)
        averages.totalLiabilitiesGrowthLast3Years = div(averages.totalLiabilitiesGrowthLast3Years, counter.totalLiabilitiesGrowthLast3YearsCount)
        averages.totalShareholdersEquityLastQuarter = div(averages.totalShareholdersEquityLastQuarter, counter.totalShareholdersEquityLastQuarterCount)
        averages.totalShareholdersEquityGrowthLastQuarter = div(averages.totalShareholdersEquityGrowthLastQuarter, counter.totalShareholdersEquityGrowthLastQuarterCount)
        averages.totalShareholdersEquityLastYear = div(averages.totalShareholdersEquityLastYear, counter.totalShareholdersEquityLastYearCount)
        averages.totalShareholdersEquityGrowthLastYear = div(averages.totalShareholdersEquityGrowthLastYear, counter.totalShareholdersEquityGrowthLastYearCount)
        averages.totalShareholdersEquityGrowthLast3Years = div(averages.totalShareholdersEquityGrowthLast3Years, counter.totalShareholdersEquityGrowthLast3YearsCount)

        averages.currentLiabilitiesToEquityLastQuarter = div(averages.currentLiabilitiesToEquityLastQuarter, counter.currentLiabilitiesToEquityLastQuarterCount)
        averages.currentLiabilitiesToEquityLastYear = div(averages.currentLiabilitiesToEquityLastYear, counter.currentLiabilitiesToEquityLastYearCount)
        averages.currentLiabilitiesToEquityGrowthLastQuarter = div(averages.currentLiabilitiesToEquityGrowthLastQuarter, counter.currentLiabilitiesToEquityGrowthLastQuarterCount)
        averages.currentLiabilitiesToEquityGrowthLastYear = div(averages.currentLiabilitiesToEquityGrowthLastYear, counter.currentLiabilitiesToEquityGrowthLastYearCount)
        averages.currentLiabilitiesToEquityGrowthLast3Years = div(averages.currentLiabilitiesToEquityGrowthLast3Years, counter.currentLiabilitiesToEquityGrowthLast3YearsCount)

        averages.totalLiabilitiesToEquityLastQuarter = div(averages.totalLiabilitiesToEquityLastQuarter, counter.totalLiabilitiesToEquityLastQuarterCount)
        averages.totalLiabilitiesToEquityLastYear = div(averages.totalLiabilitiesToEquityLastYear, counter.totalLiabilitiesToEquityLastYearCount)
        averages.totalLiabilitiesToEquityGrowthLastQuarter = div(averages.totalLiabilitiesToEquityGrowthLastQuarter, counter.totalLiabilitiesToEquityGrowthLastQuarterCount)
        averages.totalLiabilitiesToEquityGrowthLastYear = div(averages.totalLiabilitiesToEquityGrowthLastYear, counter.totalLiabilitiesToEquityGrowthLastYearCount)
        averages.totalLiabilitiesToEquityGrowthLast3Years = div(averages.totalLiabilitiesToEquityGrowthLast3Years, counter.totalLiabilitiesToEquityGrowthLast3YearsCount)

        averages.stockRepurchasedLastQuarter = div(averages.stockRepurchasedLastQuarter, counter.stockRepurchasedLastQuarterCount)
        averages.stockRepurchasedGrowthLastQuarter = div(averages.stockRepurchasedGrowthLastQuarter, counter.stockRepurchasedGrowthLastQuarterCount)
        averages.stockRepurchasedLastYear = div(averages.stockRepurchasedLastYear, counter.stockRepurchasedLastYearCount)
        averages.stockRepurchasedGrowthLastYear = div(averages.stockRepurchasedGrowthLastYear, counter.stockRepurchasedGrowthLastYearCount)
        averages.stockRepurchasedGrowthLast3Years = div(averages.stockRepurchasedGrowthLast3Years, counter.stockRepurchasedGrowthLast3YearsCount)
        averages.stockLastQuarter = div(averages.stockLastQuarter, counter.stockLastQuarterCount)
        averages.stockGrowthLastQuarter = div(averages.stockGrowthLastQuarter, counter.stockGrowthLastQuarterCount)
        averages.stockLastYear = div(averages.stockLastYear, counter.stockLastYearCount)
        averages.stockGrowthLastYear = div(averages.stockGrowthLastYear, counter.stockGrowthLastYearCount)
        averages.stockGrowthLast3Years = div(averages.stockGrowthLast3Years, counter.stockGrowthLast3YearsCount)

        return averages
    }


}