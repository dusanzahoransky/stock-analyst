package com.github.dusanzahoransky.stockanalyst.model;

import com.github.dusanzahoransky.stockanalyst.model.mongo.StockDto;

public class StocksAveragesCounter {

    public StocksAveragesCounter() {
    }

    public StocksAveragesCounter(StockDto averages) {
        this.averages = averages;
    }

    private StockDto averages;

    private Integer changeCount = 0;
    private Integer enterpriseValueCount = 0;

    private Integer totalCashPerShareCount = 0;
    private Integer totalCashPerSharePercentCount = 0;
    private Integer totalDebtEquityCount = 0;

    private Integer trailingPECount = 0;
    private Integer forwardPECount = 0;
    private Integer priceToSalesTrailing12MonthsCount = 0;
    private Integer priceBookCount = 0;
    private Integer enterpriseValueRevenueCount = 0;
    private Integer enterpriseValueEBITDACount = 0;

    private Integer priceEarningGrowthCount = 0;
    private Integer trailingPriceEarningGrowthCount = 0;


    private Integer week52ChangeCount = 0;
    private Integer week52LowCount = 0;
    private Integer week52AboveLowPercentCount = 0;
    private Integer week52HighCount = 0;
    private Integer week52BelowHighPercentCount = 0;


    private Integer targetLowPriceCount = 0;
    private Integer belowTargetLowPricePercentCount = 0;
    private Integer targetMedianPriceCount = 0;
    private Integer belowTargetMedianPricePercentCount = 0;


    private Integer heldByInsidersCount = 0;
    private Integer heldByInstitutionsCount = 0;
    private Integer buyPercentInsiderSharesCount = 0;
    private Integer sellPercentInsiderSharesCount = 0;
    private Integer shortToFloatCount = 0;
    private Integer sharesShortPrevMonthCompareCount = 0;


    private Integer exDividendDateCount = 0;
    private Integer fiveYearAvgDividendYieldCount = 0;
    private Integer trailingAnnualDividendYieldCount = 0;
    private Integer payoutRatioCount = 0;


    private Integer revenueLastQuarterCount = 0;
    private Integer revenue2QuartersAgoCount = 0;
    private Integer revenue3QuartersAgoCount = 0;
    private Integer revenueLastYearCount = 0;
    private Integer revenue2YearsAgoCount = 0;
    private Integer revenue4YearsAgoCount = 0;

    private Integer revenueGrowthLastQuarterCount = 0;
    private Integer revenueGrowthLast2QuartersCount = 0;
    private Integer revenueGrowthLastYearCount = 0;
    private Integer revenueGrowthLast4YearsCount = 0;

//
// Integer    private grossIncomeLastQuarterCount = 0;
// Integer    private grossIncome2QuartersAgoCount = 0;
// Integer    private grossIncome3QuartersAgoCount = 0;
// Integer    private grossIncomeLastYearCount = 0;
// Integer    private grossIncome2YearsAgoCount = 0;
// Integer    private grossIncome4YearsAgoCount = 0;
//
// Integer    private grossIncomeGrowthLastQuarterCount = 0;
// Integer    private grossIncomeGrowthLast2QuartersCount = 0;
// Integer    private grossIncomeGrowthLastYearCount = 0;
// Integer    private grossIncomeGrowthLast4YearsCount = 0;


    private Integer ebitLastQuarterCount = 0;
    private Integer ebit2QuartersAgoCount = 0;
    private Integer ebit3QuartersAgoCount = 0;
    private Integer ebitLastYearCount = 0;
    private Integer ebit2YearsAgoCount = 0;
    private Integer ebit4YearsAgoCount = 0;

    private Integer ebitGrowthLastQuarterCount = 0;
    private Integer ebitGrowthLast2QuartersCount = 0;
    private Integer ebitGrowthLastYearCount = 0;
    private Integer ebitGrowthLast4YearsCount = 0;


    private Integer netIncomeLastQuarterCount = 0;
    private Integer netIncome2QuartersAgoCount = 0;
    private Integer netIncome3QuartersAgoCount = 0;
    private Integer netIncomeLastYearCount = 0;
    private Integer netIncome2YearsAgoCount = 0;
    private Integer netIncome4YearsAgoCount = 0;

    private Integer netIncomeGrowthLastQuarterCount = 0;
    private Integer netIncomeGrowthLast2QuartersCount = 0;
    private Integer netIncomeGrowthLastYearCount = 0;
    private Integer netIncomeGrowthLast4YearsCount = 0;


    private Integer freeCashFlowLastQuarterCount = 0;
    private Integer freeCashFlow2QuartersAgoCount = 0;
    private Integer freeCashFlow3QuartersAgoCount = 0;
    private Integer freeCashFlowLastYearCount = 0;
    private Integer freeCashFlow2YearsAgoCount = 0;
    private Integer freeCashFlow4YearsAgoCount = 0;

    private Integer freeCashFlowGrowthLastQuarterCount = 0;
    private Integer freeCashFlowGrowthLast2QuartersCount = 0;
    private Integer freeCashFlowGrowthLastYearCount = 0;
    private Integer freeCashFlowGrowthLast4YearsCount = 0;


    private Integer cashLastQuarterCount = 0;
    private Integer cash2QuartersAgoCount = 0;
    private Integer cash3QuartersAgoCount = 0;
    private Integer cashLastYearCount = 0;
    private Integer cash2YearsAgoCount = 0;
    private Integer cash4YearsAgoCount = 0;

    private Integer cashGrowthLastQuarterCount = 0;
    private Integer cashGrowthLast2QuartersCount = 0;
    private Integer cashGrowthLastYearCount = 0;
    private Integer cashGrowthLast4YearsCount = 0;

    private Integer inventoryLastQuarterCount = 0;
    private Integer inventory2QuartersAgoCount = 0;
    private Integer inventory3QuartersAgoCount = 0;
    private Integer inventoryLastYearCount = 0;
    private Integer inventory2YearsAgoCount = 0;
    private Integer inventory4YearsAgoCount = 0;

    private Integer inventoryGrowthLastQuarterCount = 0;
    private Integer inventoryGrowthLast2QuartersCount = 0;
    private Integer inventoryGrowthLastYearCount = 0;
    private Integer inventoryGrowthLast4YearsCount = 0;


    private Integer totalLiabilitiesLastQuarterCount = 0;
    private Integer totalLiabilities2QuartersAgoCount = 0;
    private Integer totalLiabilities3QuartersAgoCount = 0;
    private Integer totalLiabilitiesLastYearCount = 0;
    private Integer totalLiabilities2YearsAgoCount = 0;
    private Integer totalLiabilities4YearsAgoCount = 0;

    private Integer totalLiabilitiesGrowthLastQuarterCount = 0;
    private Integer totalLiabilitiesGrowthLast2QuartersCount = 0;
    private Integer totalLiabilitiesGrowthLastYearCount = 0;
    private Integer totalLiabilitiesGrowthLast4YearsCount = 0;


    private Integer totalShareholdersEquityLastQuarterCount = 0;
    private Integer totalShareholdersEquity2QuartersAgoCount = 0;
    private Integer totalShareholdersEquity3QuartersAgoCount = 0;
    private Integer totalShareholdersEquityLastYearCount = 0;
    private Integer totalShareholdersEquity2YearsAgoCount = 0;
    private Integer totalShareholdersEquity4YearsAgoCount = 0;

    private Integer totalShareholdersEquityGrowthLastQuarterCount = 0;
    private Integer totalShareholdersEquityGrowthLast2QuartersCount = 0;
    private Integer totalShareholdersEquityGrowthLastYearCount = 0;
    private Integer totalShareholdersEquityGrowthLast4YearsCount = 0;


    private Integer totalLiabilitiesToEquityLastQuarterCount = 0;
    private Integer totalLiabilitiesToEquity2QuartersAgoCount = 0;
    private Integer totalLiabilitiesToEquity3QuartersAgoCount = 0;
    private Integer totalLiabilitiesToEquityLastYearCount = 0;
    private Integer totalLiabilitiesToEquity2YearsAgoCount = 0;
    private Integer totalLiabilitiesToEquity4YearsAgoCount = 0;

    private Integer totalLiabilitiesToEquityGrowthLastQuarterCount = 0;
    private Integer totalLiabilitiesToEquityGrowthLast2QuartersCount = 0;
    private Integer totalLiabilitiesToEquityGrowthLastYearCount = 0;
    private Integer totalLiabilitiesToEquityGrowthLast4YearsCount = 0;


    private Integer stockRepurchasedLastQuarterCount = 0;
    private Integer stockRepurchased2QuartersAgoCount = 0;
    private Integer stockRepurchased3QuartersAgoCount = 0;
    private Integer stockRepurchasedLastYearCount = 0;
    private Integer stockRepurchased2YearsAgoCount = 0;
    private Integer stockRepurchased4YearsAgoCount = 0;

    private Integer stockRepurchasedGrowthLastQuarterCount = 0;
    private Integer stockRepurchasedGrowthLast2QuartersCount = 0;
    private Integer stockRepurchasedGrowthLastYearCount = 0;
    private Integer stockRepurchasedGrowthLast4YearsCount = 0;


    private Integer stockLastQuarterCount = 0;
    private Integer stock2QuartersAgoCount = 0;
    private Integer stock3QuartersAgoCount = 0;
    private Integer stockLastYearCount = 0;
    private Integer stock2YearsAgoCount = 0;
    private Integer stock4YearsAgoCount = 0;

    private Integer stockGrowthLastQuarterCount = 0;
    private Integer stockGrowthLast2QuartersCount = 0;
    private Integer stockGrowthLastYearCount = 0;
    private Integer stockGrowthLast4YearsCount = 0;


    private Integer epsCurrentQuarterEstimateCount = 0;
    private Integer epsLastQuarterCount = 0;
    private Integer eps2QuartersAgoCount = 0;
    private Integer eps3QuartersAgoCount = 0;
    private Integer eps4QuartersAgoCount = 0;
    private Integer epsLastYearCount = 0;
    private Integer eps2YearsAgoCount = 0;
    private Integer eps3YearsAgoCount = 0;
    private Integer eps4YearsAgoCount = 0;

    private Integer epsGrowthLastQuarterCount = 0;
    private Integer epsGrowthLast2QuartersCount = 0;
    private Integer epsGrowthLastYearCount = 0;
    private Integer epsGrowthLast4YearsCount = 0;

// Integer    private priceLastQuarterCount = 0;
// Integer    private price2QuartersAgoCount = 0;
// Integer    private price3QuartersAgoCount = 0;
// Integer    private price4QuartersAgoCount = 0;
// Integer    private priceLastYearCount = 0;
// Integer    private price2YearsAgoCount = 0;
// Integer    private price3YearsAgoCount = 0;
// Integer    private price4YearsAgoCount = 0;
//
// Integer    private priceGrowthLastQuarterCount = 0;
// Integer    private priceGrowthLast2QuartersCount = 0;
// Integer    private priceGrowthLastYearCount = 0;
// Integer    private priceGrowthLast4YearsCount = 0;

    private Integer peLastQuarterCount = 0;
    private Integer pe2QuartersAgoCount = 0;
    private Integer pe3QuartersAgoCount = 0;
    private Integer pe4QuartersAgoCount = 0;
    private Integer peLastYearCount = 0;
    private Integer pe2YearsAgoCount = 0;
    private Integer pe3YearsAgoCount = 0;
    private Integer pe4YearsAgoCount = 0;

    private Integer peGrowthLastQuarterCount = 0;
    private Integer peGrowthLast2QuartersCount = 0;
    private Integer peGrowthLastYearCount = 0;
    private Integer peGrowthLast4YearsCount = 0;

    private Integer growthEstimate5yCount = 0;

    private Integer roic1YCount = 0;
    private Integer roic3YCount = 0;

    private Integer revenue1YCount = 0;
    private Integer revenue3YCount = 0;
    private Integer revenue5YCount = 0;
    private Integer revenue9YCount = 0;

    private Integer eps1YCount = 0;
    private Integer eps3YCount = 0;
    private Integer eps5YCount = 0;
    private Integer eps9YCount = 0;

    private Integer bps1YCount = 0;
    private Integer bps3YCount = 0;
    private Integer bps5YCount = 0;
    private Integer bps9YCount = 0;

    private Integer cash1YCount = 0;
    private Integer cash3YCount = 0;
    private Integer cash5YCount = 0;
    private Integer cash9YCount = 0;

    private Integer pe1YCount = 0;
    private Integer pe3YCount = 0;
    private Integer pe5YCount = 0;
    private Integer pe9YCount = 0;

    private Integer rule1GrowthRateCount = 0;
    private Integer defaultPECount = 0;
    private Integer historicalPECount = 0;
    private Integer rule1PECount = 0;
    private Integer currentEpsCount = 0;
    private Integer futureEPS10YearsCount = 0;
    private Integer futurePrice10YearsCount = 0;

    private Integer stickerPrice15pcGrowthCount = 0;
    // Integer    private stickerPrice10pcGrowthCount = 0;
    private Integer stickerPrice5pcGrowthCount = 0;

    private Integer belowStickerPrice15pcCount = 0;
    // Integer    private belowStickerPrice10pcCount = 0;
    private Integer belowStickerPrice5pcCount = 0;

    private Integer currentLiabilitiesLastYearCount = 0;
    private Integer currentRatio2YearsAgoCount = 0;
    private Integer currentRatio3QuartersAgoCount = 0;
    private Integer currentRatio4YearsAgoCount = 0;
    private Integer currentRatioGrowthLast2QuartersCount = 0;
    private Integer currentRatioGrowthLast4YearsCount = 0;
    private Integer currentRatioGrowthLastYearCount = 0;
    private Integer currentRatioLastQuarterCount = 0;
    private Integer currentRatioLastYearCount = 0;
    private Integer profitMargin2QuartersAgoCount = 0;
    private Integer profitMargin2YearsAgoCount = 0;
    private Integer profitMargin3QuartersAgoCount = 0;
    private Integer profitMargin4YearsAgoCount = 0;
    private Integer profitMarginLastQuarterCount = 0;
    private Integer profitMarginLastYearCount = 0;
    private Integer currentAssets2QuartersAgoCount = 0;
    private Integer currentAssets2YearsAgoCount = 0;
    private Integer currentAssets3QuartersAgoCount = 0;
    private Integer currentAssets4YearsAgoCount = 0;
    private Integer currentAssetsLastQuarterCount = 0;
    private Integer currentAssetsLastYearCount = 0;
    private Integer currentLiabilities2QuartersAgoCount = 0;
    private Integer currentLiabilities2YearsAgoCount = 0;
    private Integer currentLiabilities3QuartersAgoCount = 0;
    private Integer currentLiabilities4YearsAgoCount = 0;
    private Integer currentLiabilitiesLastQuarterCount = 0;
    private Integer currentRatio2QuartersAgoCount = 0;
    private Integer currentRatioGrowthLastQuarterCount = 0;
    private Integer profitMarginGrowthLast2QuartersCount = 0;
    private Integer profitMarginGrowthLast4YearsCount = 0;
    private Integer profitMarginGrowthLastQuarterCount = 0;
    private Integer profitMarginGrowthLastYearCount = 0;
    private Integer roicLast2YearsAgoCount = 0;
    private Integer roicLast4YearsAgoCount = 0;
    private Integer roicLastYearCount = 0;

    private Integer grossIncomeLastQuarterCount = 0;
    private Integer grossIncome2QuartersAgoCount = 0;
    private Integer grossIncome3QuartersAgoCount = 0;
    private Integer grossIncomeLastYearCount = 0;
    private Integer grossIncome2YearsAgoCount = 0;
    private Integer grossIncome4YearsAgoCount = 0;
    private Integer grossIncomeGrowthLastQuarterCount = 0;
    private Integer grossIncomeGrowthLast2QuartersCount = 0;
    private Integer grossIncomeGrowthLastYearCount = 0;
    private Integer grossIncomeGrowthLast4YearsCount = 0;
    private Integer totalAssetsLastQuarterCount = 0;
    private Integer totalAssets2QuartersAgoCount = 0;
    private Integer totalAssets3QuartersAgoCount = 0;
    private Integer totalAssetsLastYearCount = 0;
    private Integer totalAssets2YearsAgoCount = 0;
    private Integer totalAssets4YearsAgoCount = 0;
    private Integer totalAssetsGrowthLastQuarterCount = 0;
    private Integer totalAssetsGrowthLast2QuartersCount = 0;
    private Integer totalAssetsGrowthLastYearCount = 0;
    private Integer totalAssetsGrowthLast4YearsCount = 0;
    private Integer stickerPrice10pcGrowthCount = 0;
    private Integer belowStickerPrice10pcCount = 0;

    public Integer getGrossIncomeLastQuarterCount() {
        return grossIncomeLastQuarterCount;
    }

    public StocksAveragesCounter setGrossIncomeLastQuarterCount(Integer grossIncomeLastQuarterCount) {
        this.grossIncomeLastQuarterCount = grossIncomeLastQuarterCount;
        return this;
    }

    public Integer getGrossIncome2QuartersAgoCount() {
        return grossIncome2QuartersAgoCount;
    }

    public StocksAveragesCounter setGrossIncome2QuartersAgoCount(Integer grossIncome2QuartersAgoCount) {
        this.grossIncome2QuartersAgoCount = grossIncome2QuartersAgoCount;
        return this;
    }

    public Integer getGrossIncome3QuartersAgoCount() {
        return grossIncome3QuartersAgoCount;
    }

    public StocksAveragesCounter setGrossIncome3QuartersAgoCount(Integer grossIncome3QuartersAgoCount) {
        this.grossIncome3QuartersAgoCount = grossIncome3QuartersAgoCount;
        return this;
    }

    public Integer getGrossIncomeLastYearCount() {
        return grossIncomeLastYearCount;
    }

    public StocksAveragesCounter setGrossIncomeLastYearCount(Integer grossIncomeLastYearCount) {
        this.grossIncomeLastYearCount = grossIncomeLastYearCount;
        return this;
    }

    public Integer getGrossIncome2YearsAgoCount() {
        return grossIncome2YearsAgoCount;
    }

    public StocksAveragesCounter setGrossIncome2YearsAgoCount(Integer grossIncome2YearsAgoCount) {
        this.grossIncome2YearsAgoCount = grossIncome2YearsAgoCount;
        return this;
    }

    public Integer getGrossIncome4YearsAgoCount() {
        return grossIncome4YearsAgoCount;
    }

    public StocksAveragesCounter setGrossIncome4YearsAgoCount(Integer grossIncome4YearsAgoCount) {
        this.grossIncome4YearsAgoCount = grossIncome4YearsAgoCount;
        return this;
    }

    public Integer getGrossIncomeGrowthLastQuarterCount() {
        return grossIncomeGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setGrossIncomeGrowthLastQuarterCount(Integer grossIncomeGrowthLastQuarterCount) {
        this.grossIncomeGrowthLastQuarterCount = grossIncomeGrowthLastQuarterCount;
        return this;
    }

    public Integer getGrossIncomeGrowthLast2QuartersCount() {
        return grossIncomeGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setGrossIncomeGrowthLast2QuartersCount(Integer grossIncomeGrowthLast2QuartersCount) {
        this.grossIncomeGrowthLast2QuartersCount = grossIncomeGrowthLast2QuartersCount;
        return this;
    }

    public Integer getGrossIncomeGrowthLastYearCount() {
        return grossIncomeGrowthLastYearCount;
    }

    public StocksAveragesCounter setGrossIncomeGrowthLastYearCount(Integer grossIncomeGrowthLastYearCount) {
        this.grossIncomeGrowthLastYearCount = grossIncomeGrowthLastYearCount;
        return this;
    }

    public Integer getGrossIncomeGrowthLast4YearsCount() {
        return grossIncomeGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setGrossIncomeGrowthLast4YearsCount(Integer grossIncomeGrowthLast4YearsCount) {
        this.grossIncomeGrowthLast4YearsCount = grossIncomeGrowthLast4YearsCount;
        return this;
    }

    public Integer getTotalAssetsLastQuarterCount() {
        return totalAssetsLastQuarterCount;
    }

    public StocksAveragesCounter setTotalAssetsLastQuarterCount(Integer totalAssetsLastQuarterCount) {
        this.totalAssetsLastQuarterCount = totalAssetsLastQuarterCount;
        return this;
    }

    public Integer getTotalAssets2QuartersAgoCount() {
        return totalAssets2QuartersAgoCount;
    }

    public StocksAveragesCounter setTotalAssets2QuartersAgoCount(Integer totalAssets2QuartersAgoCount) {
        this.totalAssets2QuartersAgoCount = totalAssets2QuartersAgoCount;
        return this;
    }

    public Integer getTotalAssets3QuartersAgoCount() {
        return totalAssets3QuartersAgoCount;
    }

    public StocksAveragesCounter setTotalAssets3QuartersAgoCount(Integer totalAssets3QuartersAgoCount) {
        this.totalAssets3QuartersAgoCount = totalAssets3QuartersAgoCount;
        return this;
    }

    public Integer getTotalAssetsLastYearCount() {
        return totalAssetsLastYearCount;
    }

    public StocksAveragesCounter setTotalAssetsLastYearCount(Integer totalAssetsLastYearCount) {
        this.totalAssetsLastYearCount = totalAssetsLastYearCount;
        return this;
    }

    public Integer getTotalAssets2YearsAgoCount() {
        return totalAssets2YearsAgoCount;
    }

    public StocksAveragesCounter setTotalAssets2YearsAgoCount(Integer totalAssets2YearsAgoCount) {
        this.totalAssets2YearsAgoCount = totalAssets2YearsAgoCount;
        return this;
    }

    public Integer getTotalAssets4YearsAgoCount() {
        return totalAssets4YearsAgoCount;
    }

    public StocksAveragesCounter setTotalAssets4YearsAgoCount(Integer totalAssets4YearsAgoCount) {
        this.totalAssets4YearsAgoCount = totalAssets4YearsAgoCount;
        return this;
    }

    public Integer getTotalAssetsGrowthLastQuarterCount() {
        return totalAssetsGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setTotalAssetsGrowthLastQuarterCount(Integer totalAssetsGrowthLastQuarterCount) {
        this.totalAssetsGrowthLastQuarterCount = totalAssetsGrowthLastQuarterCount;
        return this;
    }

    public Integer getTotalAssetsGrowthLast2QuartersCount() {
        return totalAssetsGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setTotalAssetsGrowthLast2QuartersCount(Integer totalAssetsGrowthLast2QuartersCount) {
        this.totalAssetsGrowthLast2QuartersCount = totalAssetsGrowthLast2QuartersCount;
        return this;
    }

    public Integer getTotalAssetsGrowthLastYearCount() {
        return totalAssetsGrowthLastYearCount;
    }

    public StocksAveragesCounter setTotalAssetsGrowthLastYearCount(Integer totalAssetsGrowthLastYearCount) {
        this.totalAssetsGrowthLastYearCount = totalAssetsGrowthLastYearCount;
        return this;
    }

    public Integer getTotalAssetsGrowthLast4YearsCount() {
        return totalAssetsGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setTotalAssetsGrowthLast4YearsCount(Integer totalAssetsGrowthLast4YearsCount) {
        this.totalAssetsGrowthLast4YearsCount = totalAssetsGrowthLast4YearsCount;
        return this;
    }

    public Integer getStickerPrice10pcGrowthCount() {
        return stickerPrice10pcGrowthCount;
    }

    public StocksAveragesCounter setStickerPrice10pcGrowthCount(Integer stickerPrice10pcGrowthCount) {
        this.stickerPrice10pcGrowthCount = stickerPrice10pcGrowthCount;
        return this;
    }

    public Integer getBelowStickerPrice10pcCount() {
        return belowStickerPrice10pcCount;
    }

    public StocksAveragesCounter setBelowStickerPrice10pcCount(Integer belowStickerPrice10pcCount) {
        this.belowStickerPrice10pcCount = belowStickerPrice10pcCount;
        return this;
    }

    public StockDto getAverages() {
        return averages;
    }

    public StocksAveragesCounter setAverages(StockDto averages) {
        this.averages = averages;
        return this;
    }

    public Integer getChangeCount() {
        return changeCount;
    }

    public StocksAveragesCounter setChangeCount(Integer changeCount) {
        this.changeCount = changeCount;
        return this;
    }

    public Integer getEnterpriseValueCount() {
        return enterpriseValueCount;
    }

    public StocksAveragesCounter setEnterpriseValueCount(Integer enterpriseValueCount) {
        this.enterpriseValueCount = enterpriseValueCount;
        return this;
    }

    public Integer getTotalCashPerShareCount() {
        return totalCashPerShareCount;
    }

    public StocksAveragesCounter setTotalCashPerShareCount(Integer totalCashPerShareCount) {
        this.totalCashPerShareCount = totalCashPerShareCount;
        return this;
    }

    public Integer getTotalCashPerSharePercentCount() {
        return totalCashPerSharePercentCount;
    }

    public StocksAveragesCounter setTotalCashPerSharePercentCount(Integer totalCashPerSharePercentCount) {
        this.totalCashPerSharePercentCount = totalCashPerSharePercentCount;
        return this;
    }

    public Integer getTotalDebtEquityCount() {
        return totalDebtEquityCount;
    }

    public StocksAveragesCounter setTotalDebtEquityCount(Integer totalDebtEquityCount) {
        this.totalDebtEquityCount = totalDebtEquityCount;
        return this;
    }

    public Integer getTrailingPECount() {
        return trailingPECount;
    }

    public StocksAveragesCounter setTrailingPECount(Integer trailingPECount) {
        this.trailingPECount = trailingPECount;
        return this;
    }

    public Integer getForwardPECount() {
        return forwardPECount;
    }

    public StocksAveragesCounter setForwardPECount(Integer forwardPECount) {
        this.forwardPECount = forwardPECount;
        return this;
    }

    public Integer getPriceToSalesTrailing12MonthsCount() {
        return priceToSalesTrailing12MonthsCount;
    }

    public StocksAveragesCounter setPriceToSalesTrailing12MonthsCount(Integer priceToSalesTrailing12MonthsCount) {
        this.priceToSalesTrailing12MonthsCount = priceToSalesTrailing12MonthsCount;
        return this;
    }

    public Integer getPriceBookCount() {
        return priceBookCount;
    }

    public StocksAveragesCounter setPriceBookCount(Integer priceBookCount) {
        this.priceBookCount = priceBookCount;
        return this;
    }

    public Integer getEnterpriseValueRevenueCount() {
        return enterpriseValueRevenueCount;
    }

    public StocksAveragesCounter setEnterpriseValueRevenueCount(Integer enterpriseValueRevenueCount) {
        this.enterpriseValueRevenueCount = enterpriseValueRevenueCount;
        return this;
    }

    public Integer getEnterpriseValueEBITDACount() {
        return enterpriseValueEBITDACount;
    }

    public StocksAveragesCounter setEnterpriseValueEBITDACount(Integer enterpriseValueEBITDACount) {
        this.enterpriseValueEBITDACount = enterpriseValueEBITDACount;
        return this;
    }

    public Integer getPriceEarningGrowthCount() {
        return priceEarningGrowthCount;
    }

    public StocksAveragesCounter setPriceEarningGrowthCount(Integer priceEarningGrowthCount) {
        this.priceEarningGrowthCount = priceEarningGrowthCount;
        return this;
    }

    public Integer getTrailingPriceEarningGrowthCount() {
        return trailingPriceEarningGrowthCount;
    }

    public StocksAveragesCounter setTrailingPriceEarningGrowthCount(Integer trailingPriceEarningGrowthCount) {
        this.trailingPriceEarningGrowthCount = trailingPriceEarningGrowthCount;
        return this;
    }

    public Integer getWeek52ChangeCount() {
        return week52ChangeCount;
    }

    public StocksAveragesCounter setWeek52ChangeCount(Integer week52ChangeCount) {
        this.week52ChangeCount = week52ChangeCount;
        return this;
    }

    public Integer getWeek52LowCount() {
        return week52LowCount;
    }

    public StocksAveragesCounter setWeek52LowCount(Integer week52LowCount) {
        this.week52LowCount = week52LowCount;
        return this;
    }

    public Integer getWeek52AboveLowPercentCount() {
        return week52AboveLowPercentCount;
    }

    public StocksAveragesCounter setWeek52AboveLowPercentCount(Integer week52AboveLowPercentCount) {
        this.week52AboveLowPercentCount = week52AboveLowPercentCount;
        return this;
    }

    public Integer getWeek52HighCount() {
        return week52HighCount;
    }

    public StocksAveragesCounter setWeek52HighCount(Integer week52HighCount) {
        this.week52HighCount = week52HighCount;
        return this;
    }

    public Integer getWeek52BelowHighPercentCount() {
        return week52BelowHighPercentCount;
    }

    public StocksAveragesCounter setWeek52BelowHighPercentCount(Integer week52BelowHighPercentCount) {
        this.week52BelowHighPercentCount = week52BelowHighPercentCount;
        return this;
    }

    public Integer getTargetLowPriceCount() {
        return targetLowPriceCount;
    }

    public StocksAveragesCounter setTargetLowPriceCount(Integer targetLowPriceCount) {
        this.targetLowPriceCount = targetLowPriceCount;
        return this;
    }

    public Integer getBelowTargetLowPricePercentCount() {
        return belowTargetLowPricePercentCount;
    }

    public StocksAveragesCounter setBelowTargetLowPricePercentCount(Integer belowTargetLowPricePercentCount) {
        this.belowTargetLowPricePercentCount = belowTargetLowPricePercentCount;
        return this;
    }

    public Integer getTargetMedianPriceCount() {
        return targetMedianPriceCount;
    }

    public StocksAveragesCounter setTargetMedianPriceCount(Integer targetMedianPriceCount) {
        this.targetMedianPriceCount = targetMedianPriceCount;
        return this;
    }

    public Integer getBelowTargetMedianPricePercentCount() {
        return belowTargetMedianPricePercentCount;
    }

    public StocksAveragesCounter setBelowTargetMedianPricePercentCount(Integer belowTargetMedianPricePercentCount) {
        this.belowTargetMedianPricePercentCount = belowTargetMedianPricePercentCount;
        return this;
    }

    public Integer getHeldByInsidersCount() {
        return heldByInsidersCount;
    }

    public StocksAveragesCounter setHeldByInsidersCount(Integer heldByInsidersCount) {
        this.heldByInsidersCount = heldByInsidersCount;
        return this;
    }

    public Integer getHeldByInstitutionsCount() {
        return heldByInstitutionsCount;
    }

    public StocksAveragesCounter setHeldByInstitutionsCount(Integer heldByInstitutionsCount) {
        this.heldByInstitutionsCount = heldByInstitutionsCount;
        return this;
    }

    public Integer getBuyPercentInsiderSharesCount() {
        return buyPercentInsiderSharesCount;
    }

    public StocksAveragesCounter setBuyPercentInsiderSharesCount(Integer buyPercentInsiderSharesCount) {
        this.buyPercentInsiderSharesCount = buyPercentInsiderSharesCount;
        return this;
    }

    public Integer getSellPercentInsiderSharesCount() {
        return sellPercentInsiderSharesCount;
    }

    public StocksAveragesCounter setSellPercentInsiderSharesCount(Integer sellPercentInsiderSharesCount) {
        this.sellPercentInsiderSharesCount = sellPercentInsiderSharesCount;
        return this;
    }

    public Integer getShortToFloatCount() {
        return shortToFloatCount;
    }

    public StocksAveragesCounter setShortToFloatCount(Integer shortToFloatCount) {
        this.shortToFloatCount = shortToFloatCount;
        return this;
    }

    public Integer getSharesShortPrevMonthCompareCount() {
        return sharesShortPrevMonthCompareCount;
    }

    public StocksAveragesCounter setSharesShortPrevMonthCompareCount(Integer sharesShortPrevMonthCompareCount) {
        this.sharesShortPrevMonthCompareCount = sharesShortPrevMonthCompareCount;
        return this;
    }

    public Integer getExDividendDateCount() {
        return exDividendDateCount;
    }

    public StocksAveragesCounter setExDividendDateCount(Integer exDividendDateCount) {
        this.exDividendDateCount = exDividendDateCount;
        return this;
    }

    public Integer getFiveYearAvgDividendYieldCount() {
        return fiveYearAvgDividendYieldCount;
    }

    public StocksAveragesCounter setFiveYearAvgDividendYieldCount(Integer fiveYearAvgDividendYieldCount) {
        this.fiveYearAvgDividendYieldCount = fiveYearAvgDividendYieldCount;
        return this;
    }

    public Integer getTrailingAnnualDividendYieldCount() {
        return trailingAnnualDividendYieldCount;
    }

    public StocksAveragesCounter setTrailingAnnualDividendYieldCount(Integer trailingAnnualDividendYieldCount) {
        this.trailingAnnualDividendYieldCount = trailingAnnualDividendYieldCount;
        return this;
    }

    public Integer getPayoutRatioCount() {
        return payoutRatioCount;
    }

    public StocksAveragesCounter setPayoutRatioCount(Integer payoutRatioCount) {
        this.payoutRatioCount = payoutRatioCount;
        return this;
    }

    public Integer getRevenueLastQuarterCount() {
        return revenueLastQuarterCount;
    }

    public StocksAveragesCounter setRevenueLastQuarterCount(Integer revenueLastQuarterCount) {
        this.revenueLastQuarterCount = revenueLastQuarterCount;
        return this;
    }

    public Integer getRevenue2QuartersAgoCount() {
        return revenue2QuartersAgoCount;
    }

    public StocksAveragesCounter setRevenue2QuartersAgoCount(Integer revenue2QuartersAgoCount) {
        this.revenue2QuartersAgoCount = revenue2QuartersAgoCount;
        return this;
    }

    public Integer getRevenue3QuartersAgoCount() {
        return revenue3QuartersAgoCount;
    }

    public StocksAveragesCounter setRevenue3QuartersAgoCount(Integer revenue3QuartersAgoCount) {
        this.revenue3QuartersAgoCount = revenue3QuartersAgoCount;
        return this;
    }

    public Integer getRevenueLastYearCount() {
        return revenueLastYearCount;
    }

    public StocksAveragesCounter setRevenueLastYearCount(Integer revenueLastYearCount) {
        this.revenueLastYearCount = revenueLastYearCount;
        return this;
    }

    public Integer getRevenue2YearsAgoCount() {
        return revenue2YearsAgoCount;
    }

    public StocksAveragesCounter setRevenue2YearsAgoCount(Integer revenue2YearsAgoCount) {
        this.revenue2YearsAgoCount = revenue2YearsAgoCount;
        return this;
    }

    public Integer getRevenue4YearsAgoCount() {
        return revenue4YearsAgoCount;
    }

    public StocksAveragesCounter setRevenue4YearsAgoCount(Integer revenue4YearsAgoCount) {
        this.revenue4YearsAgoCount = revenue4YearsAgoCount;
        return this;
    }

    public Integer getRevenueGrowthLastQuarterCount() {
        return revenueGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setRevenueGrowthLastQuarterCount(Integer revenueGrowthLastQuarterCount) {
        this.revenueGrowthLastQuarterCount = revenueGrowthLastQuarterCount;
        return this;
    }

    public Integer getRevenueGrowthLast2QuartersCount() {
        return revenueGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setRevenueGrowthLast2QuartersCount(Integer revenueGrowthLast2QuartersCount) {
        this.revenueGrowthLast2QuartersCount = revenueGrowthLast2QuartersCount;
        return this;
    }

    public Integer getRevenueGrowthLastYearCount() {
        return revenueGrowthLastYearCount;
    }

    public StocksAveragesCounter setRevenueGrowthLastYearCount(Integer revenueGrowthLastYearCount) {
        this.revenueGrowthLastYearCount = revenueGrowthLastYearCount;
        return this;
    }

    public Integer getRevenueGrowthLast4YearsCount() {
        return revenueGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setRevenueGrowthLast4YearsCount(Integer revenueGrowthLast4YearsCount) {
        this.revenueGrowthLast4YearsCount = revenueGrowthLast4YearsCount;
        return this;
    }

    public Integer getEbitLastQuarterCount() {
        return ebitLastQuarterCount;
    }

    public StocksAveragesCounter setEbitLastQuarterCount(Integer ebitLastQuarterCount) {
        this.ebitLastQuarterCount = ebitLastQuarterCount;
        return this;
    }

    public Integer getEbit2QuartersAgoCount() {
        return ebit2QuartersAgoCount;
    }

    public StocksAveragesCounter setEbit2QuartersAgoCount(Integer ebit2QuartersAgoCount) {
        this.ebit2QuartersAgoCount = ebit2QuartersAgoCount;
        return this;
    }

    public Integer getEbit3QuartersAgoCount() {
        return ebit3QuartersAgoCount;
    }

    public StocksAveragesCounter setEbit3QuartersAgoCount(Integer ebit3QuartersAgoCount) {
        this.ebit3QuartersAgoCount = ebit3QuartersAgoCount;
        return this;
    }

    public Integer getEbitLastYearCount() {
        return ebitLastYearCount;
    }

    public StocksAveragesCounter setEbitLastYearCount(Integer ebitLastYearCount) {
        this.ebitLastYearCount = ebitLastYearCount;
        return this;
    }

    public Integer getEbit2YearsAgoCount() {
        return ebit2YearsAgoCount;
    }

    public StocksAveragesCounter setEbit2YearsAgoCount(Integer ebit2YearsAgoCount) {
        this.ebit2YearsAgoCount = ebit2YearsAgoCount;
        return this;
    }

    public Integer getEbit4YearsAgoCount() {
        return ebit4YearsAgoCount;
    }

    public StocksAveragesCounter setEbit4YearsAgoCount(Integer ebit4YearsAgoCount) {
        this.ebit4YearsAgoCount = ebit4YearsAgoCount;
        return this;
    }

    public Integer getEbitGrowthLastQuarterCount() {
        return ebitGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setEbitGrowthLastQuarterCount(Integer ebitGrowthLastQuarterCount) {
        this.ebitGrowthLastQuarterCount = ebitGrowthLastQuarterCount;
        return this;
    }

    public Integer getEbitGrowthLast2QuartersCount() {
        return ebitGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setEbitGrowthLast2QuartersCount(Integer ebitGrowthLast2QuartersCount) {
        this.ebitGrowthLast2QuartersCount = ebitGrowthLast2QuartersCount;
        return this;
    }

    public Integer getEbitGrowthLastYearCount() {
        return ebitGrowthLastYearCount;
    }

    public StocksAveragesCounter setEbitGrowthLastYearCount(Integer ebitGrowthLastYearCount) {
        this.ebitGrowthLastYearCount = ebitGrowthLastYearCount;
        return this;
    }

    public Integer getEbitGrowthLast4YearsCount() {
        return ebitGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setEbitGrowthLast4YearsCount(Integer ebitGrowthLast4YearsCount) {
        this.ebitGrowthLast4YearsCount = ebitGrowthLast4YearsCount;
        return this;
    }

    public Integer getNetIncomeLastQuarterCount() {
        return netIncomeLastQuarterCount;
    }

    public StocksAveragesCounter setNetIncomeLastQuarterCount(Integer netIncomeLastQuarterCount) {
        this.netIncomeLastQuarterCount = netIncomeLastQuarterCount;
        return this;
    }

    public Integer getNetIncome2QuartersAgoCount() {
        return netIncome2QuartersAgoCount;
    }

    public StocksAveragesCounter setNetIncome2QuartersAgoCount(Integer netIncome2QuartersAgoCount) {
        this.netIncome2QuartersAgoCount = netIncome2QuartersAgoCount;
        return this;
    }

    public Integer getNetIncome3QuartersAgoCount() {
        return netIncome3QuartersAgoCount;
    }

    public StocksAveragesCounter setNetIncome3QuartersAgoCount(Integer netIncome3QuartersAgoCount) {
        this.netIncome3QuartersAgoCount = netIncome3QuartersAgoCount;
        return this;
    }

    public Integer getNetIncomeLastYearCount() {
        return netIncomeLastYearCount;
    }

    public StocksAveragesCounter setNetIncomeLastYearCount(Integer netIncomeLastYearCount) {
        this.netIncomeLastYearCount = netIncomeLastYearCount;
        return this;
    }

    public Integer getNetIncome2YearsAgoCount() {
        return netIncome2YearsAgoCount;
    }

    public StocksAveragesCounter setNetIncome2YearsAgoCount(Integer netIncome2YearsAgoCount) {
        this.netIncome2YearsAgoCount = netIncome2YearsAgoCount;
        return this;
    }

    public Integer getNetIncome4YearsAgoCount() {
        return netIncome4YearsAgoCount;
    }

    public StocksAveragesCounter setNetIncome4YearsAgoCount(Integer netIncome4YearsAgoCount) {
        this.netIncome4YearsAgoCount = netIncome4YearsAgoCount;
        return this;
    }

    public Integer getNetIncomeGrowthLastQuarterCount() {
        return netIncomeGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setNetIncomeGrowthLastQuarterCount(Integer netIncomeGrowthLastQuarterCount) {
        this.netIncomeGrowthLastQuarterCount = netIncomeGrowthLastQuarterCount;
        return this;
    }

    public Integer getNetIncomeGrowthLast2QuartersCount() {
        return netIncomeGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setNetIncomeGrowthLast2QuartersCount(Integer netIncomeGrowthLast2QuartersCount) {
        this.netIncomeGrowthLast2QuartersCount = netIncomeGrowthLast2QuartersCount;
        return this;
    }

    public Integer getNetIncomeGrowthLastYearCount() {
        return netIncomeGrowthLastYearCount;
    }

    public StocksAveragesCounter setNetIncomeGrowthLastYearCount(Integer netIncomeGrowthLastYearCount) {
        this.netIncomeGrowthLastYearCount = netIncomeGrowthLastYearCount;
        return this;
    }

    public Integer getNetIncomeGrowthLast4YearsCount() {
        return netIncomeGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setNetIncomeGrowthLast4YearsCount(Integer netIncomeGrowthLast4YearsCount) {
        this.netIncomeGrowthLast4YearsCount = netIncomeGrowthLast4YearsCount;
        return this;
    }

    public Integer getFreeCashFlowLastQuarterCount() {
        return freeCashFlowLastQuarterCount;
    }

    public StocksAveragesCounter setFreeCashFlowLastQuarterCount(Integer freeCashFlowLastQuarterCount) {
        this.freeCashFlowLastQuarterCount = freeCashFlowLastQuarterCount;
        return this;
    }

    public Integer getFreeCashFlow2QuartersAgoCount() {
        return freeCashFlow2QuartersAgoCount;
    }

    public StocksAveragesCounter setFreeCashFlow2QuartersAgoCount(Integer freeCashFlow2QuartersAgoCount) {
        this.freeCashFlow2QuartersAgoCount = freeCashFlow2QuartersAgoCount;
        return this;
    }

    public Integer getFreeCashFlow3QuartersAgoCount() {
        return freeCashFlow3QuartersAgoCount;
    }

    public StocksAveragesCounter setFreeCashFlow3QuartersAgoCount(Integer freeCashFlow3QuartersAgoCount) {
        this.freeCashFlow3QuartersAgoCount = freeCashFlow3QuartersAgoCount;
        return this;
    }

    public Integer getFreeCashFlowLastYearCount() {
        return freeCashFlowLastYearCount;
    }

    public StocksAveragesCounter setFreeCashFlowLastYearCount(Integer freeCashFlowLastYearCount) {
        this.freeCashFlowLastYearCount = freeCashFlowLastYearCount;
        return this;
    }

    public Integer getFreeCashFlow2YearsAgoCount() {
        return freeCashFlow2YearsAgoCount;
    }

    public StocksAveragesCounter setFreeCashFlow2YearsAgoCount(Integer freeCashFlow2YearsAgoCount) {
        this.freeCashFlow2YearsAgoCount = freeCashFlow2YearsAgoCount;
        return this;
    }

    public Integer getFreeCashFlow4YearsAgoCount() {
        return freeCashFlow4YearsAgoCount;
    }

    public StocksAveragesCounter setFreeCashFlow4YearsAgoCount(Integer freeCashFlow4YearsAgoCount) {
        this.freeCashFlow4YearsAgoCount = freeCashFlow4YearsAgoCount;
        return this;
    }

    public Integer getFreeCashFlowGrowthLastQuarterCount() {
        return freeCashFlowGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setFreeCashFlowGrowthLastQuarterCount(Integer freeCashFlowGrowthLastQuarterCount) {
        this.freeCashFlowGrowthLastQuarterCount = freeCashFlowGrowthLastQuarterCount;
        return this;
    }

    public Integer getFreeCashFlowGrowthLast2QuartersCount() {
        return freeCashFlowGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setFreeCashFlowGrowthLast2QuartersCount(Integer freeCashFlowGrowthLast2QuartersCount) {
        this.freeCashFlowGrowthLast2QuartersCount = freeCashFlowGrowthLast2QuartersCount;
        return this;
    }

    public Integer getFreeCashFlowGrowthLastYearCount() {
        return freeCashFlowGrowthLastYearCount;
    }

    public StocksAveragesCounter setFreeCashFlowGrowthLastYearCount(Integer freeCashFlowGrowthLastYearCount) {
        this.freeCashFlowGrowthLastYearCount = freeCashFlowGrowthLastYearCount;
        return this;
    }

    public Integer getFreeCashFlowGrowthLast4YearsCount() {
        return freeCashFlowGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setFreeCashFlowGrowthLast4YearsCount(Integer freeCashFlowGrowthLast4YearsCount) {
        this.freeCashFlowGrowthLast4YearsCount = freeCashFlowGrowthLast4YearsCount;
        return this;
    }

    public Integer getCashLastQuarterCount() {
        return cashLastQuarterCount;
    }

    public StocksAveragesCounter setCashLastQuarterCount(Integer cashLastQuarterCount) {
        this.cashLastQuarterCount = cashLastQuarterCount;
        return this;
    }

    public Integer getCash2QuartersAgoCount() {
        return cash2QuartersAgoCount;
    }

    public StocksAveragesCounter setCash2QuartersAgoCount(Integer cash2QuartersAgoCount) {
        this.cash2QuartersAgoCount = cash2QuartersAgoCount;
        return this;
    }

    public Integer getCash3QuartersAgoCount() {
        return cash3QuartersAgoCount;
    }

    public StocksAveragesCounter setCash3QuartersAgoCount(Integer cash3QuartersAgoCount) {
        this.cash3QuartersAgoCount = cash3QuartersAgoCount;
        return this;
    }

    public Integer getCashLastYearCount() {
        return cashLastYearCount;
    }

    public StocksAveragesCounter setCashLastYearCount(Integer cashLastYearCount) {
        this.cashLastYearCount = cashLastYearCount;
        return this;
    }

    public Integer getCash2YearsAgoCount() {
        return cash2YearsAgoCount;
    }

    public StocksAveragesCounter setCash2YearsAgoCount(Integer cash2YearsAgoCount) {
        this.cash2YearsAgoCount = cash2YearsAgoCount;
        return this;
    }

    public Integer getCash4YearsAgoCount() {
        return cash4YearsAgoCount;
    }

    public StocksAveragesCounter setCash4YearsAgoCount(Integer cash4YearsAgoCount) {
        this.cash4YearsAgoCount = cash4YearsAgoCount;
        return this;
    }

    public Integer getCashGrowthLastQuarterCount() {
        return cashGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setCashGrowthLastQuarterCount(Integer cashGrowthLastQuarterCount) {
        this.cashGrowthLastQuarterCount = cashGrowthLastQuarterCount;
        return this;
    }

    public Integer getCashGrowthLast2QuartersCount() {
        return cashGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setCashGrowthLast2QuartersCount(Integer cashGrowthLast2QuartersCount) {
        this.cashGrowthLast2QuartersCount = cashGrowthLast2QuartersCount;
        return this;
    }

    public Integer getCashGrowthLastYearCount() {
        return cashGrowthLastYearCount;
    }

    public StocksAveragesCounter setCashGrowthLastYearCount(Integer cashGrowthLastYearCount) {
        this.cashGrowthLastYearCount = cashGrowthLastYearCount;
        return this;
    }

    public Integer getCashGrowthLast4YearsCount() {
        return cashGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setCashGrowthLast4YearsCount(Integer cashGrowthLast4YearsCount) {
        this.cashGrowthLast4YearsCount = cashGrowthLast4YearsCount;
        return this;
    }

    public Integer getInventoryLastQuarterCount() {
        return inventoryLastQuarterCount;
    }

    public StocksAveragesCounter setInventoryLastQuarterCount(Integer inventoryLastQuarterCount) {
        this.inventoryLastQuarterCount = inventoryLastQuarterCount;
        return this;
    }

    public Integer getInventory2QuartersAgoCount() {
        return inventory2QuartersAgoCount;
    }

    public StocksAveragesCounter setInventory2QuartersAgoCount(Integer inventory2QuartersAgoCount) {
        this.inventory2QuartersAgoCount = inventory2QuartersAgoCount;
        return this;
    }

    public Integer getInventory3QuartersAgoCount() {
        return inventory3QuartersAgoCount;
    }

    public StocksAveragesCounter setInventory3QuartersAgoCount(Integer inventory3QuartersAgoCount) {
        this.inventory3QuartersAgoCount = inventory3QuartersAgoCount;
        return this;
    }

    public Integer getInventoryLastYearCount() {
        return inventoryLastYearCount;
    }

    public StocksAveragesCounter setInventoryLastYearCount(Integer inventoryLastYearCount) {
        this.inventoryLastYearCount = inventoryLastYearCount;
        return this;
    }

    public Integer getInventory2YearsAgoCount() {
        return inventory2YearsAgoCount;
    }

    public StocksAveragesCounter setInventory2YearsAgoCount(Integer inventory2YearsAgoCount) {
        this.inventory2YearsAgoCount = inventory2YearsAgoCount;
        return this;
    }

    public Integer getInventory4YearsAgoCount() {
        return inventory4YearsAgoCount;
    }

    public StocksAveragesCounter setInventory4YearsAgoCount(Integer inventory4YearsAgoCount) {
        this.inventory4YearsAgoCount = inventory4YearsAgoCount;
        return this;
    }

    public Integer getInventoryGrowthLastQuarterCount() {
        return inventoryGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setInventoryGrowthLastQuarterCount(Integer inventoryGrowthLastQuarterCount) {
        this.inventoryGrowthLastQuarterCount = inventoryGrowthLastQuarterCount;
        return this;
    }

    public Integer getInventoryGrowthLast2QuartersCount() {
        return inventoryGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setInventoryGrowthLast2QuartersCount(Integer inventoryGrowthLast2QuartersCount) {
        this.inventoryGrowthLast2QuartersCount = inventoryGrowthLast2QuartersCount;
        return this;
    }

    public Integer getInventoryGrowthLastYearCount() {
        return inventoryGrowthLastYearCount;
    }

    public StocksAveragesCounter setInventoryGrowthLastYearCount(Integer inventoryGrowthLastYearCount) {
        this.inventoryGrowthLastYearCount = inventoryGrowthLastYearCount;
        return this;
    }

    public Integer getInventoryGrowthLast4YearsCount() {
        return inventoryGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setInventoryGrowthLast4YearsCount(Integer inventoryGrowthLast4YearsCount) {
        this.inventoryGrowthLast4YearsCount = inventoryGrowthLast4YearsCount;
        return this;
    }

    public Integer getTotalLiabilitiesLastQuarterCount() {
        return totalLiabilitiesLastQuarterCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesLastQuarterCount(Integer totalLiabilitiesLastQuarterCount) {
        this.totalLiabilitiesLastQuarterCount = totalLiabilitiesLastQuarterCount;
        return this;
    }

    public Integer getTotalLiabilities2QuartersAgoCount() {
        return totalLiabilities2QuartersAgoCount;
    }

    public StocksAveragesCounter setTotalLiabilities2QuartersAgoCount(Integer totalLiabilities2QuartersAgoCount) {
        this.totalLiabilities2QuartersAgoCount = totalLiabilities2QuartersAgoCount;
        return this;
    }

    public Integer getTotalLiabilities3QuartersAgoCount() {
        return totalLiabilities3QuartersAgoCount;
    }

    public StocksAveragesCounter setTotalLiabilities3QuartersAgoCount(Integer totalLiabilities3QuartersAgoCount) {
        this.totalLiabilities3QuartersAgoCount = totalLiabilities3QuartersAgoCount;
        return this;
    }

    public Integer getTotalLiabilitiesLastYearCount() {
        return totalLiabilitiesLastYearCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesLastYearCount(Integer totalLiabilitiesLastYearCount) {
        this.totalLiabilitiesLastYearCount = totalLiabilitiesLastYearCount;
        return this;
    }

    public Integer getTotalLiabilities2YearsAgoCount() {
        return totalLiabilities2YearsAgoCount;
    }

    public StocksAveragesCounter setTotalLiabilities2YearsAgoCount(Integer totalLiabilities2YearsAgoCount) {
        this.totalLiabilities2YearsAgoCount = totalLiabilities2YearsAgoCount;
        return this;
    }

    public Integer getTotalLiabilities4YearsAgoCount() {
        return totalLiabilities4YearsAgoCount;
    }

    public StocksAveragesCounter setTotalLiabilities4YearsAgoCount(Integer totalLiabilities4YearsAgoCount) {
        this.totalLiabilities4YearsAgoCount = totalLiabilities4YearsAgoCount;
        return this;
    }

    public Integer getTotalLiabilitiesGrowthLastQuarterCount() {
        return totalLiabilitiesGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesGrowthLastQuarterCount(Integer totalLiabilitiesGrowthLastQuarterCount) {
        this.totalLiabilitiesGrowthLastQuarterCount = totalLiabilitiesGrowthLastQuarterCount;
        return this;
    }

    public Integer getTotalLiabilitiesGrowthLast2QuartersCount() {
        return totalLiabilitiesGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesGrowthLast2QuartersCount(Integer totalLiabilitiesGrowthLast2QuartersCount) {
        this.totalLiabilitiesGrowthLast2QuartersCount = totalLiabilitiesGrowthLast2QuartersCount;
        return this;
    }

    public Integer getTotalLiabilitiesGrowthLastYearCount() {
        return totalLiabilitiesGrowthLastYearCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesGrowthLastYearCount(Integer totalLiabilitiesGrowthLastYearCount) {
        this.totalLiabilitiesGrowthLastYearCount = totalLiabilitiesGrowthLastYearCount;
        return this;
    }

    public Integer getTotalLiabilitiesGrowthLast4YearsCount() {
        return totalLiabilitiesGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesGrowthLast4YearsCount(Integer totalLiabilitiesGrowthLast4YearsCount) {
        this.totalLiabilitiesGrowthLast4YearsCount = totalLiabilitiesGrowthLast4YearsCount;
        return this;
    }

    public Integer getTotalShareholdersEquityLastQuarterCount() {
        return totalShareholdersEquityLastQuarterCount;
    }

    public StocksAveragesCounter setTotalShareholdersEquityLastQuarterCount(Integer totalShareholdersEquityLastQuarterCount) {
        this.totalShareholdersEquityLastQuarterCount = totalShareholdersEquityLastQuarterCount;
        return this;
    }

    public Integer getTotalShareholdersEquity2QuartersAgoCount() {
        return totalShareholdersEquity2QuartersAgoCount;
    }

    public StocksAveragesCounter setTotalShareholdersEquity2QuartersAgoCount(Integer totalShareholdersEquity2QuartersAgoCount) {
        this.totalShareholdersEquity2QuartersAgoCount = totalShareholdersEquity2QuartersAgoCount;
        return this;
    }

    public Integer getTotalShareholdersEquity3QuartersAgoCount() {
        return totalShareholdersEquity3QuartersAgoCount;
    }

    public StocksAveragesCounter setTotalShareholdersEquity3QuartersAgoCount(Integer totalShareholdersEquity3QuartersAgoCount) {
        this.totalShareholdersEquity3QuartersAgoCount = totalShareholdersEquity3QuartersAgoCount;
        return this;
    }

    public Integer getTotalShareholdersEquityLastYearCount() {
        return totalShareholdersEquityLastYearCount;
    }

    public StocksAveragesCounter setTotalShareholdersEquityLastYearCount(Integer totalShareholdersEquityLastYearCount) {
        this.totalShareholdersEquityLastYearCount = totalShareholdersEquityLastYearCount;
        return this;
    }

    public Integer getTotalShareholdersEquity2YearsAgoCount() {
        return totalShareholdersEquity2YearsAgoCount;
    }

    public StocksAveragesCounter setTotalShareholdersEquity2YearsAgoCount(Integer totalShareholdersEquity2YearsAgoCount) {
        this.totalShareholdersEquity2YearsAgoCount = totalShareholdersEquity2YearsAgoCount;
        return this;
    }

    public Integer getTotalShareholdersEquity4YearsAgoCount() {
        return totalShareholdersEquity4YearsAgoCount;
    }

    public StocksAveragesCounter setTotalShareholdersEquity4YearsAgoCount(Integer totalShareholdersEquity4YearsAgoCount) {
        this.totalShareholdersEquity4YearsAgoCount = totalShareholdersEquity4YearsAgoCount;
        return this;
    }

    public Integer getTotalShareholdersEquityGrowthLastQuarterCount() {
        return totalShareholdersEquityGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setTotalShareholdersEquityGrowthLastQuarterCount(Integer totalShareholdersEquityGrowthLastQuarterCount) {
        this.totalShareholdersEquityGrowthLastQuarterCount = totalShareholdersEquityGrowthLastQuarterCount;
        return this;
    }

    public Integer getTotalShareholdersEquityGrowthLast2QuartersCount() {
        return totalShareholdersEquityGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setTotalShareholdersEquityGrowthLast2QuartersCount(Integer totalShareholdersEquityGrowthLast2QuartersCount) {
        this.totalShareholdersEquityGrowthLast2QuartersCount = totalShareholdersEquityGrowthLast2QuartersCount;
        return this;
    }

    public Integer getTotalShareholdersEquityGrowthLastYearCount() {
        return totalShareholdersEquityGrowthLastYearCount;
    }

    public StocksAveragesCounter setTotalShareholdersEquityGrowthLastYearCount(Integer totalShareholdersEquityGrowthLastYearCount) {
        this.totalShareholdersEquityGrowthLastYearCount = totalShareholdersEquityGrowthLastYearCount;
        return this;
    }

    public Integer getTotalShareholdersEquityGrowthLast4YearsCount() {
        return totalShareholdersEquityGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setTotalShareholdersEquityGrowthLast4YearsCount(Integer totalShareholdersEquityGrowthLast4YearsCount) {
        this.totalShareholdersEquityGrowthLast4YearsCount = totalShareholdersEquityGrowthLast4YearsCount;
        return this;
    }

    public Integer getTotalLiabilitiesToEquityLastQuarterCount() {
        return totalLiabilitiesToEquityLastQuarterCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesToEquityLastQuarterCount(Integer totalLiabilitiesToEquityLastQuarterCount) {
        this.totalLiabilitiesToEquityLastQuarterCount = totalLiabilitiesToEquityLastQuarterCount;
        return this;
    }

    public Integer getTotalLiabilitiesToEquity2QuartersAgoCount() {
        return totalLiabilitiesToEquity2QuartersAgoCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesToEquity2QuartersAgoCount(Integer totalLiabilitiesToEquity2QuartersAgoCount) {
        this.totalLiabilitiesToEquity2QuartersAgoCount = totalLiabilitiesToEquity2QuartersAgoCount;
        return this;
    }

    public Integer getTotalLiabilitiesToEquity3QuartersAgoCount() {
        return totalLiabilitiesToEquity3QuartersAgoCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesToEquity3QuartersAgoCount(Integer totalLiabilitiesToEquity3QuartersAgoCount) {
        this.totalLiabilitiesToEquity3QuartersAgoCount = totalLiabilitiesToEquity3QuartersAgoCount;
        return this;
    }

    public Integer getTotalLiabilitiesToEquityLastYearCount() {
        return totalLiabilitiesToEquityLastYearCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesToEquityLastYearCount(Integer totalLiabilitiesToEquityLastYearCount) {
        this.totalLiabilitiesToEquityLastYearCount = totalLiabilitiesToEquityLastYearCount;
        return this;
    }

    public Integer getTotalLiabilitiesToEquity2YearsAgoCount() {
        return totalLiabilitiesToEquity2YearsAgoCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesToEquity2YearsAgoCount(Integer totalLiabilitiesToEquity2YearsAgoCount) {
        this.totalLiabilitiesToEquity2YearsAgoCount = totalLiabilitiesToEquity2YearsAgoCount;
        return this;
    }

    public Integer getTotalLiabilitiesToEquity4YearsAgoCount() {
        return totalLiabilitiesToEquity4YearsAgoCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesToEquity4YearsAgoCount(Integer totalLiabilitiesToEquity4YearsAgoCount) {
        this.totalLiabilitiesToEquity4YearsAgoCount = totalLiabilitiesToEquity4YearsAgoCount;
        return this;
    }

    public Integer getTotalLiabilitiesToEquityGrowthLastQuarterCount() {
        return totalLiabilitiesToEquityGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesToEquityGrowthLastQuarterCount(Integer totalLiabilitiesToEquityGrowthLastQuarterCount) {
        this.totalLiabilitiesToEquityGrowthLastQuarterCount = totalLiabilitiesToEquityGrowthLastQuarterCount;
        return this;
    }

    public Integer getTotalLiabilitiesToEquityGrowthLast2QuartersCount() {
        return totalLiabilitiesToEquityGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesToEquityGrowthLast2QuartersCount(Integer totalLiabilitiesToEquityGrowthLast2QuartersCount) {
        this.totalLiabilitiesToEquityGrowthLast2QuartersCount = totalLiabilitiesToEquityGrowthLast2QuartersCount;
        return this;
    }

    public Integer getTotalLiabilitiesToEquityGrowthLastYearCount() {
        return totalLiabilitiesToEquityGrowthLastYearCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesToEquityGrowthLastYearCount(Integer totalLiabilitiesToEquityGrowthLastYearCount) {
        this.totalLiabilitiesToEquityGrowthLastYearCount = totalLiabilitiesToEquityGrowthLastYearCount;
        return this;
    }

    public Integer getTotalLiabilitiesToEquityGrowthLast4YearsCount() {
        return totalLiabilitiesToEquityGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setTotalLiabilitiesToEquityGrowthLast4YearsCount(Integer totalLiabilitiesToEquityGrowthLast4YearsCount) {
        this.totalLiabilitiesToEquityGrowthLast4YearsCount = totalLiabilitiesToEquityGrowthLast4YearsCount;
        return this;
    }

    public Integer getStockRepurchasedLastQuarterCount() {
        return stockRepurchasedLastQuarterCount;
    }

    public StocksAveragesCounter setStockRepurchasedLastQuarterCount(Integer stockRepurchasedLastQuarterCount) {
        this.stockRepurchasedLastQuarterCount = stockRepurchasedLastQuarterCount;
        return this;
    }

    public Integer getStockRepurchased2QuartersAgoCount() {
        return stockRepurchased2QuartersAgoCount;
    }

    public StocksAveragesCounter setStockRepurchased2QuartersAgoCount(Integer stockRepurchased2QuartersAgoCount) {
        this.stockRepurchased2QuartersAgoCount = stockRepurchased2QuartersAgoCount;
        return this;
    }

    public Integer getStockRepurchased3QuartersAgoCount() {
        return stockRepurchased3QuartersAgoCount;
    }

    public StocksAveragesCounter setStockRepurchased3QuartersAgoCount(Integer stockRepurchased3QuartersAgoCount) {
        this.stockRepurchased3QuartersAgoCount = stockRepurchased3QuartersAgoCount;
        return this;
    }

    public Integer getStockRepurchasedLastYearCount() {
        return stockRepurchasedLastYearCount;
    }

    public StocksAveragesCounter setStockRepurchasedLastYearCount(Integer stockRepurchasedLastYearCount) {
        this.stockRepurchasedLastYearCount = stockRepurchasedLastYearCount;
        return this;
    }

    public Integer getStockRepurchased2YearsAgoCount() {
        return stockRepurchased2YearsAgoCount;
    }

    public StocksAveragesCounter setStockRepurchased2YearsAgoCount(Integer stockRepurchased2YearsAgoCount) {
        this.stockRepurchased2YearsAgoCount = stockRepurchased2YearsAgoCount;
        return this;
    }

    public Integer getStockRepurchased4YearsAgoCount() {
        return stockRepurchased4YearsAgoCount;
    }

    public StocksAveragesCounter setStockRepurchased4YearsAgoCount(Integer stockRepurchased4YearsAgoCount) {
        this.stockRepurchased4YearsAgoCount = stockRepurchased4YearsAgoCount;
        return this;
    }

    public Integer getStockRepurchasedGrowthLastQuarterCount() {
        return stockRepurchasedGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setStockRepurchasedGrowthLastQuarterCount(Integer stockRepurchasedGrowthLastQuarterCount) {
        this.stockRepurchasedGrowthLastQuarterCount = stockRepurchasedGrowthLastQuarterCount;
        return this;
    }

    public Integer getStockRepurchasedGrowthLast2QuartersCount() {
        return stockRepurchasedGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setStockRepurchasedGrowthLast2QuartersCount(Integer stockRepurchasedGrowthLast2QuartersCount) {
        this.stockRepurchasedGrowthLast2QuartersCount = stockRepurchasedGrowthLast2QuartersCount;
        return this;
    }

    public Integer getStockRepurchasedGrowthLastYearCount() {
        return stockRepurchasedGrowthLastYearCount;
    }

    public StocksAveragesCounter setStockRepurchasedGrowthLastYearCount(Integer stockRepurchasedGrowthLastYearCount) {
        this.stockRepurchasedGrowthLastYearCount = stockRepurchasedGrowthLastYearCount;
        return this;
    }

    public Integer getStockRepurchasedGrowthLast4YearsCount() {
        return stockRepurchasedGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setStockRepurchasedGrowthLast4YearsCount(Integer stockRepurchasedGrowthLast4YearsCount) {
        this.stockRepurchasedGrowthLast4YearsCount = stockRepurchasedGrowthLast4YearsCount;
        return this;
    }

    public Integer getStockLastQuarterCount() {
        return stockLastQuarterCount;
    }

    public StocksAveragesCounter setStockLastQuarterCount(Integer stockLastQuarterCount) {
        this.stockLastQuarterCount = stockLastQuarterCount;
        return this;
    }

    public Integer getStock2QuartersAgoCount() {
        return stock2QuartersAgoCount;
    }

    public StocksAveragesCounter setStock2QuartersAgoCount(Integer stock2QuartersAgoCount) {
        this.stock2QuartersAgoCount = stock2QuartersAgoCount;
        return this;
    }

    public Integer getStock3QuartersAgoCount() {
        return stock3QuartersAgoCount;
    }

    public StocksAveragesCounter setStock3QuartersAgoCount(Integer stock3QuartersAgoCount) {
        this.stock3QuartersAgoCount = stock3QuartersAgoCount;
        return this;
    }

    public Integer getStockLastYearCount() {
        return stockLastYearCount;
    }

    public StocksAveragesCounter setStockLastYearCount(Integer stockLastYearCount) {
        this.stockLastYearCount = stockLastYearCount;
        return this;
    }

    public Integer getStock2YearsAgoCount() {
        return stock2YearsAgoCount;
    }

    public StocksAveragesCounter setStock2YearsAgoCount(Integer stock2YearsAgoCount) {
        this.stock2YearsAgoCount = stock2YearsAgoCount;
        return this;
    }

    public Integer getStock4YearsAgoCount() {
        return stock4YearsAgoCount;
    }

    public StocksAveragesCounter setStock4YearsAgoCount(Integer stock4YearsAgoCount) {
        this.stock4YearsAgoCount = stock4YearsAgoCount;
        return this;
    }

    public Integer getStockGrowthLastQuarterCount() {
        return stockGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setStockGrowthLastQuarterCount(Integer stockGrowthLastQuarterCount) {
        this.stockGrowthLastQuarterCount = stockGrowthLastQuarterCount;
        return this;
    }

    public Integer getStockGrowthLast2QuartersCount() {
        return stockGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setStockGrowthLast2QuartersCount(Integer stockGrowthLast2QuartersCount) {
        this.stockGrowthLast2QuartersCount = stockGrowthLast2QuartersCount;
        return this;
    }

    public Integer getStockGrowthLastYearCount() {
        return stockGrowthLastYearCount;
    }

    public StocksAveragesCounter setStockGrowthLastYearCount(Integer stockGrowthLastYearCount) {
        this.stockGrowthLastYearCount = stockGrowthLastYearCount;
        return this;
    }

    public Integer getStockGrowthLast4YearsCount() {
        return stockGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setStockGrowthLast4YearsCount(Integer stockGrowthLast4YearsCount) {
        this.stockGrowthLast4YearsCount = stockGrowthLast4YearsCount;
        return this;
    }

    public Integer getEpsCurrentQuarterEstimateCount() {
        return epsCurrentQuarterEstimateCount;
    }

    public StocksAveragesCounter setEpsCurrentQuarterEstimateCount(Integer epsCurrentQuarterEstimateCount) {
        this.epsCurrentQuarterEstimateCount = epsCurrentQuarterEstimateCount;
        return this;
    }

    public Integer getEpsLastQuarterCount() {
        return epsLastQuarterCount;
    }

    public StocksAveragesCounter setEpsLastQuarterCount(Integer epsLastQuarterCount) {
        this.epsLastQuarterCount = epsLastQuarterCount;
        return this;
    }

    public Integer getEps2QuartersAgoCount() {
        return eps2QuartersAgoCount;
    }

    public StocksAveragesCounter setEps2QuartersAgoCount(Integer eps2QuartersAgoCount) {
        this.eps2QuartersAgoCount = eps2QuartersAgoCount;
        return this;
    }

    public Integer getEps3QuartersAgoCount() {
        return eps3QuartersAgoCount;
    }

    public StocksAveragesCounter setEps3QuartersAgoCount(Integer eps3QuartersAgoCount) {
        this.eps3QuartersAgoCount = eps3QuartersAgoCount;
        return this;
    }

    public Integer getEps4QuartersAgoCount() {
        return eps4QuartersAgoCount;
    }

    public StocksAveragesCounter setEps4QuartersAgoCount(Integer eps4QuartersAgoCount) {
        this.eps4QuartersAgoCount = eps4QuartersAgoCount;
        return this;
    }

    public Integer getEpsLastYearCount() {
        return epsLastYearCount;
    }

    public StocksAveragesCounter setEpsLastYearCount(Integer epsLastYearCount) {
        this.epsLastYearCount = epsLastYearCount;
        return this;
    }

    public Integer getEps2YearsAgoCount() {
        return eps2YearsAgoCount;
    }

    public StocksAveragesCounter setEps2YearsAgoCount(Integer eps2YearsAgoCount) {
        this.eps2YearsAgoCount = eps2YearsAgoCount;
        return this;
    }

    public Integer getEps3YearsAgoCount() {
        return eps3YearsAgoCount;
    }

    public StocksAveragesCounter setEps3YearsAgoCount(Integer eps3YearsAgoCount) {
        this.eps3YearsAgoCount = eps3YearsAgoCount;
        return this;
    }

    public Integer getEps4YearsAgoCount() {
        return eps4YearsAgoCount;
    }

    public StocksAveragesCounter setEps4YearsAgoCount(Integer eps4YearsAgoCount) {
        this.eps4YearsAgoCount = eps4YearsAgoCount;
        return this;
    }

    public Integer getEpsGrowthLastQuarterCount() {
        return epsGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setEpsGrowthLastQuarterCount(Integer epsGrowthLastQuarterCount) {
        this.epsGrowthLastQuarterCount = epsGrowthLastQuarterCount;
        return this;
    }

    public Integer getEpsGrowthLast2QuartersCount() {
        return epsGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setEpsGrowthLast2QuartersCount(Integer epsGrowthLast2QuartersCount) {
        this.epsGrowthLast2QuartersCount = epsGrowthLast2QuartersCount;
        return this;
    }

    public Integer getEpsGrowthLastYearCount() {
        return epsGrowthLastYearCount;
    }

    public StocksAveragesCounter setEpsGrowthLastYearCount(Integer epsGrowthLastYearCount) {
        this.epsGrowthLastYearCount = epsGrowthLastYearCount;
        return this;
    }

    public Integer getEpsGrowthLast4YearsCount() {
        return epsGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setEpsGrowthLast4YearsCount(Integer epsGrowthLast4YearsCount) {
        this.epsGrowthLast4YearsCount = epsGrowthLast4YearsCount;
        return this;
    }

    public Integer getPeLastQuarterCount() {
        return peLastQuarterCount;
    }

    public StocksAveragesCounter setPeLastQuarterCount(Integer peLastQuarterCount) {
        this.peLastQuarterCount = peLastQuarterCount;
        return this;
    }

    public Integer getPe2QuartersAgoCount() {
        return pe2QuartersAgoCount;
    }

    public StocksAveragesCounter setPe2QuartersAgoCount(Integer pe2QuartersAgoCount) {
        this.pe2QuartersAgoCount = pe2QuartersAgoCount;
        return this;
    }

    public Integer getPe3QuartersAgoCount() {
        return pe3QuartersAgoCount;
    }

    public StocksAveragesCounter setPe3QuartersAgoCount(Integer pe3QuartersAgoCount) {
        this.pe3QuartersAgoCount = pe3QuartersAgoCount;
        return this;
    }

    public Integer getPe4QuartersAgoCount() {
        return pe4QuartersAgoCount;
    }

    public StocksAveragesCounter setPe4QuartersAgoCount(Integer pe4QuartersAgoCount) {
        this.pe4QuartersAgoCount = pe4QuartersAgoCount;
        return this;
    }

    public Integer getPeLastYearCount() {
        return peLastYearCount;
    }

    public StocksAveragesCounter setPeLastYearCount(Integer peLastYearCount) {
        this.peLastYearCount = peLastYearCount;
        return this;
    }

    public Integer getPe2YearsAgoCount() {
        return pe2YearsAgoCount;
    }

    public StocksAveragesCounter setPe2YearsAgoCount(Integer pe2YearsAgoCount) {
        this.pe2YearsAgoCount = pe2YearsAgoCount;
        return this;
    }

    public Integer getPe3YearsAgoCount() {
        return pe3YearsAgoCount;
    }

    public StocksAveragesCounter setPe3YearsAgoCount(Integer pe3YearsAgoCount) {
        this.pe3YearsAgoCount = pe3YearsAgoCount;
        return this;
    }

    public Integer getPe4YearsAgoCount() {
        return pe4YearsAgoCount;
    }

    public StocksAveragesCounter setPe4YearsAgoCount(Integer pe4YearsAgoCount) {
        this.pe4YearsAgoCount = pe4YearsAgoCount;
        return this;
    }

    public Integer getPeGrowthLastQuarterCount() {
        return peGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setPeGrowthLastQuarterCount(Integer peGrowthLastQuarterCount) {
        this.peGrowthLastQuarterCount = peGrowthLastQuarterCount;
        return this;
    }

    public Integer getPeGrowthLast2QuartersCount() {
        return peGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setPeGrowthLast2QuartersCount(Integer peGrowthLast2QuartersCount) {
        this.peGrowthLast2QuartersCount = peGrowthLast2QuartersCount;
        return this;
    }

    public Integer getPeGrowthLastYearCount() {
        return peGrowthLastYearCount;
    }

    public StocksAveragesCounter setPeGrowthLastYearCount(Integer peGrowthLastYearCount) {
        this.peGrowthLastYearCount = peGrowthLastYearCount;
        return this;
    }

    public Integer getPeGrowthLast4YearsCount() {
        return peGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setPeGrowthLast4YearsCount(Integer peGrowthLast4YearsCount) {
        this.peGrowthLast4YearsCount = peGrowthLast4YearsCount;
        return this;
    }

    public Integer getGrowthEstimate5yCount() {
        return growthEstimate5yCount;
    }

    public StocksAveragesCounter setGrowthEstimate5yCount(Integer growthEstimate5yCount) {
        this.growthEstimate5yCount = growthEstimate5yCount;
        return this;
    }

    public Integer getRoic1YCount() {
        return roic1YCount;
    }

    public StocksAveragesCounter setRoic1YCount(Integer roic1YCount) {
        this.roic1YCount = roic1YCount;
        return this;
    }

    public Integer getRoic3YCount() {
        return roic3YCount;
    }

    public StocksAveragesCounter setRoic3YCount(Integer roic3YCount) {
        this.roic3YCount = roic3YCount;
        return this;
    }

    public Integer getRevenue1YCount() {
        return revenue1YCount;
    }

    public StocksAveragesCounter setRevenue1YCount(Integer revenue1YCount) {
        this.revenue1YCount = revenue1YCount;
        return this;
    }

    public Integer getRevenue3YCount() {
        return revenue3YCount;
    }

    public StocksAveragesCounter setRevenue3YCount(Integer revenue3YCount) {
        this.revenue3YCount = revenue3YCount;
        return this;
    }

    public Integer getRevenue5YCount() {
        return revenue5YCount;
    }

    public StocksAveragesCounter setRevenue5YCount(Integer revenue5YCount) {
        this.revenue5YCount = revenue5YCount;
        return this;
    }

    public Integer getRevenue9YCount() {
        return revenue9YCount;
    }

    public StocksAveragesCounter setRevenue9YCount(Integer revenue9YCount) {
        this.revenue9YCount = revenue9YCount;
        return this;
    }

    public Integer getEps1YCount() {
        return eps1YCount;
    }

    public StocksAveragesCounter setEps1YCount(Integer eps1YCount) {
        this.eps1YCount = eps1YCount;
        return this;
    }

    public Integer getEps3YCount() {
        return eps3YCount;
    }

    public StocksAveragesCounter setEps3YCount(Integer eps3YCount) {
        this.eps3YCount = eps3YCount;
        return this;
    }

    public Integer getEps5YCount() {
        return eps5YCount;
    }

    public StocksAveragesCounter setEps5YCount(Integer eps5YCount) {
        this.eps5YCount = eps5YCount;
        return this;
    }

    public Integer getEps9YCount() {
        return eps9YCount;
    }

    public StocksAveragesCounter setEps9YCount(Integer eps9YCount) {
        this.eps9YCount = eps9YCount;
        return this;
    }

    public Integer getBps1YCount() {
        return bps1YCount;
    }

    public StocksAveragesCounter setBps1YCount(Integer bps1YCount) {
        this.bps1YCount = bps1YCount;
        return this;
    }

    public Integer getBps3YCount() {
        return bps3YCount;
    }

    public StocksAveragesCounter setBps3YCount(Integer bps3YCount) {
        this.bps3YCount = bps3YCount;
        return this;
    }

    public Integer getBps5YCount() {
        return bps5YCount;
    }

    public StocksAveragesCounter setBps5YCount(Integer bps5YCount) {
        this.bps5YCount = bps5YCount;
        return this;
    }

    public Integer getBps9YCount() {
        return bps9YCount;
    }

    public StocksAveragesCounter setBps9YCount(Integer bps9YCount) {
        this.bps9YCount = bps9YCount;
        return this;
    }

    public Integer getCash1YCount() {
        return cash1YCount;
    }

    public StocksAveragesCounter setCash1YCount(Integer cash1YCount) {
        this.cash1YCount = cash1YCount;
        return this;
    }

    public Integer getCash3YCount() {
        return cash3YCount;
    }

    public StocksAveragesCounter setCash3YCount(Integer cash3YCount) {
        this.cash3YCount = cash3YCount;
        return this;
    }

    public Integer getCash5YCount() {
        return cash5YCount;
    }

    public StocksAveragesCounter setCash5YCount(Integer cash5YCount) {
        this.cash5YCount = cash5YCount;
        return this;
    }

    public Integer getCash9YCount() {
        return cash9YCount;
    }

    public StocksAveragesCounter setCash9YCount(Integer cash9YCount) {
        this.cash9YCount = cash9YCount;
        return this;
    }

    public Integer getPe1YCount() {
        return pe1YCount;
    }

    public StocksAveragesCounter setPe1YCount(Integer pe1YCount) {
        this.pe1YCount = pe1YCount;
        return this;
    }

    public Integer getPe3YCount() {
        return pe3YCount;
    }

    public StocksAveragesCounter setPe3YCount(Integer pe3YCount) {
        this.pe3YCount = pe3YCount;
        return this;
    }

    public Integer getPe5YCount() {
        return pe5YCount;
    }

    public StocksAveragesCounter setPe5YCount(Integer pe5YCount) {
        this.pe5YCount = pe5YCount;
        return this;
    }

    public Integer getPe9YCount() {
        return pe9YCount;
    }

    public StocksAveragesCounter setPe9YCount(Integer pe9YCount) {
        this.pe9YCount = pe9YCount;
        return this;
    }

    public Integer getRule1GrowthRateCount() {
        return rule1GrowthRateCount;
    }

    public StocksAveragesCounter setRule1GrowthRateCount(Integer rule1GrowthRateCount) {
        this.rule1GrowthRateCount = rule1GrowthRateCount;
        return this;
    }

    public Integer getDefaultPECount() {
        return defaultPECount;
    }

    public StocksAveragesCounter setDefaultPECount(Integer defaultPECount) {
        this.defaultPECount = defaultPECount;
        return this;
    }

    public Integer getHistoricalPECount() {
        return historicalPECount;
    }

    public StocksAveragesCounter setHistoricalPECount(Integer historicalPECount) {
        this.historicalPECount = historicalPECount;
        return this;
    }

    public Integer getRule1PECount() {
        return rule1PECount;
    }

    public StocksAveragesCounter setRule1PECount(Integer rule1PECount) {
        this.rule1PECount = rule1PECount;
        return this;
    }

    public Integer getCurrentEpsCount() {
        return currentEpsCount;
    }

    public StocksAveragesCounter setCurrentEpsCount(Integer currentEpsCount) {
        this.currentEpsCount = currentEpsCount;
        return this;
    }

    public Integer getFutureEPS10YearsCount() {
        return futureEPS10YearsCount;
    }

    public StocksAveragesCounter setFutureEPS10YearsCount(Integer futureEPS10YearsCount) {
        this.futureEPS10YearsCount = futureEPS10YearsCount;
        return this;
    }

    public Integer getFuturePrice10YearsCount() {
        return futurePrice10YearsCount;
    }

    public StocksAveragesCounter setFuturePrice10YearsCount(Integer futurePrice10YearsCount) {
        this.futurePrice10YearsCount = futurePrice10YearsCount;
        return this;
    }

    public Integer getStickerPrice15pcGrowthCount() {
        return stickerPrice15pcGrowthCount;
    }

    public StocksAveragesCounter setStickerPrice15pcGrowthCount(Integer stickerPrice15pcGrowthCount) {
        this.stickerPrice15pcGrowthCount = stickerPrice15pcGrowthCount;
        return this;
    }

    public Integer getStickerPrice5pcGrowthCount() {
        return stickerPrice5pcGrowthCount;
    }

    public StocksAveragesCounter setStickerPrice5pcGrowthCount(Integer stickerPrice5pcGrowthCount) {
        this.stickerPrice5pcGrowthCount = stickerPrice5pcGrowthCount;
        return this;
    }

    public Integer getBelowStickerPrice15pcCount() {
        return belowStickerPrice15pcCount;
    }

    public StocksAveragesCounter setBelowStickerPrice15pcCount(Integer belowStickerPrice15pcCount) {
        this.belowStickerPrice15pcCount = belowStickerPrice15pcCount;
        return this;
    }

    public Integer getBelowStickerPrice5pcCount() {
        return belowStickerPrice5pcCount;
    }

    public StocksAveragesCounter setBelowStickerPrice5pcCount(Integer belowStickerPrice5pcCount) {
        this.belowStickerPrice5pcCount = belowStickerPrice5pcCount;
        return this;
    }

    public Integer getCurrentLiabilitiesLastYearCount() {
        return currentLiabilitiesLastYearCount;
    }

    public StocksAveragesCounter setCurrentLiabilitiesLastYearCount(Integer currentLiabilitiesLastYearCount) {
        this.currentLiabilitiesLastYearCount = currentLiabilitiesLastYearCount;
        return this;
    }

    public Integer getCurrentRatio2YearsAgoCount() {
        return currentRatio2YearsAgoCount;
    }

    public StocksAveragesCounter setCurrentRatio2YearsAgoCount(Integer currentRatio2YearsAgoCount) {
        this.currentRatio2YearsAgoCount = currentRatio2YearsAgoCount;
        return this;
    }

    public Integer getCurrentRatio3QuartersAgoCount() {
        return currentRatio3QuartersAgoCount;
    }

    public StocksAveragesCounter setCurrentRatio3QuartersAgoCount(Integer currentRatio3QuartersAgoCount) {
        this.currentRatio3QuartersAgoCount = currentRatio3QuartersAgoCount;
        return this;
    }

    public Integer getCurrentRatio4YearsAgoCount() {
        return currentRatio4YearsAgoCount;
    }

    public StocksAveragesCounter setCurrentRatio4YearsAgoCount(Integer currentRatio4YearsAgoCount) {
        this.currentRatio4YearsAgoCount = currentRatio4YearsAgoCount;
        return this;
    }

    public Integer getCurrentRatioGrowthLast2QuartersCount() {
        return currentRatioGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setCurrentRatioGrowthLast2QuartersCount(Integer currentRatioGrowthLast2QuartersCount) {
        this.currentRatioGrowthLast2QuartersCount = currentRatioGrowthLast2QuartersCount;
        return this;
    }

    public Integer getCurrentRatioGrowthLast4YearsCount() {
        return currentRatioGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setCurrentRatioGrowthLast4YearsCount(Integer currentRatioGrowthLast4YearsCount) {
        this.currentRatioGrowthLast4YearsCount = currentRatioGrowthLast4YearsCount;
        return this;
    }

    public Integer getCurrentRatioGrowthLastYearCount() {
        return currentRatioGrowthLastYearCount;
    }

    public StocksAveragesCounter setCurrentRatioGrowthLastYearCount(Integer currentRatioGrowthLastYearCount) {
        this.currentRatioGrowthLastYearCount = currentRatioGrowthLastYearCount;
        return this;
    }

    public Integer getCurrentRatioLastQuarterCount() {
        return currentRatioLastQuarterCount;
    }

    public StocksAveragesCounter setCurrentRatioLastQuarterCount(Integer currentRatioLastQuarterCount) {
        this.currentRatioLastQuarterCount = currentRatioLastQuarterCount;
        return this;
    }

    public Integer getCurrentRatioLastYearCount() {
        return currentRatioLastYearCount;
    }

    public StocksAveragesCounter setCurrentRatioLastYearCount(Integer currentRatioLastYearCount) {
        this.currentRatioLastYearCount = currentRatioLastYearCount;
        return this;
    }

    public Integer getProfitMargin2QuartersAgoCount() {
        return profitMargin2QuartersAgoCount;
    }

    public StocksAveragesCounter setProfitMargin2QuartersAgoCount(Integer profitMargin2QuartersAgoCount) {
        this.profitMargin2QuartersAgoCount = profitMargin2QuartersAgoCount;
        return this;
    }

    public Integer getProfitMargin2YearsAgoCount() {
        return profitMargin2YearsAgoCount;
    }

    public StocksAveragesCounter setProfitMargin2YearsAgoCount(Integer profitMargin2YearsAgoCount) {
        this.profitMargin2YearsAgoCount = profitMargin2YearsAgoCount;
        return this;
    }

    public Integer getProfitMargin3QuartersAgoCount() {
        return profitMargin3QuartersAgoCount;
    }

    public StocksAveragesCounter setProfitMargin3QuartersAgoCount(Integer profitMargin3QuartersAgoCount) {
        this.profitMargin3QuartersAgoCount = profitMargin3QuartersAgoCount;
        return this;
    }

    public Integer getProfitMargin4YearsAgoCount() {
        return profitMargin4YearsAgoCount;
    }

    public StocksAveragesCounter setProfitMargin4YearsAgoCount(Integer profitMargin4YearsAgoCount) {
        this.profitMargin4YearsAgoCount = profitMargin4YearsAgoCount;
        return this;
    }

    public Integer getProfitMarginLastQuarterCount() {
        return profitMarginLastQuarterCount;
    }

    public StocksAveragesCounter setProfitMarginLastQuarterCount(Integer profitMarginLastQuarterCount) {
        this.profitMarginLastQuarterCount = profitMarginLastQuarterCount;
        return this;
    }

    public Integer getProfitMarginLastYearCount() {
        return profitMarginLastYearCount;
    }

    public StocksAveragesCounter setProfitMarginLastYearCount(Integer profitMarginLastYearCount) {
        this.profitMarginLastYearCount = profitMarginLastYearCount;
        return this;
    }

    public Integer getCurrentAssets2QuartersAgoCount() {
        return currentAssets2QuartersAgoCount;
    }

    public StocksAveragesCounter setCurrentAssets2QuartersAgoCount(Integer currentAssets2QuartersAgoCount) {
        this.currentAssets2QuartersAgoCount = currentAssets2QuartersAgoCount;
        return this;
    }

    public Integer getCurrentAssets2YearsAgoCount() {
        return currentAssets2YearsAgoCount;
    }

    public StocksAveragesCounter setCurrentAssets2YearsAgoCount(Integer currentAssets2YearsAgoCount) {
        this.currentAssets2YearsAgoCount = currentAssets2YearsAgoCount;
        return this;
    }

    public Integer getCurrentAssets3QuartersAgoCount() {
        return currentAssets3QuartersAgoCount;
    }

    public StocksAveragesCounter setCurrentAssets3QuartersAgoCount(Integer currentAssets3QuartersAgoCount) {
        this.currentAssets3QuartersAgoCount = currentAssets3QuartersAgoCount;
        return this;
    }

    public Integer getCurrentAssets4YearsAgoCount() {
        return currentAssets4YearsAgoCount;
    }

    public StocksAveragesCounter setCurrentAssets4YearsAgoCount(Integer currentAssets4YearsAgoCount) {
        this.currentAssets4YearsAgoCount = currentAssets4YearsAgoCount;
        return this;
    }

    public Integer getCurrentAssetsLastQuarterCount() {
        return currentAssetsLastQuarterCount;
    }

    public StocksAveragesCounter setCurrentAssetsLastQuarterCount(Integer currentAssetsLastQuarterCount) {
        this.currentAssetsLastQuarterCount = currentAssetsLastQuarterCount;
        return this;
    }

    public Integer getCurrentAssetsLastYearCount() {
        return currentAssetsLastYearCount;
    }

    public StocksAveragesCounter setCurrentAssetsLastYearCount(Integer currentAssetsLastYearCount) {
        this.currentAssetsLastYearCount = currentAssetsLastYearCount;
        return this;
    }

    public Integer getCurrentLiabilities2QuartersAgoCount() {
        return currentLiabilities2QuartersAgoCount;
    }

    public StocksAveragesCounter setCurrentLiabilities2QuartersAgoCount(Integer currentLiabilities2QuartersAgoCount) {
        this.currentLiabilities2QuartersAgoCount = currentLiabilities2QuartersAgoCount;
        return this;
    }

    public Integer getCurrentLiabilities2YearsAgoCount() {
        return currentLiabilities2YearsAgoCount;
    }

    public StocksAveragesCounter setCurrentLiabilities2YearsAgoCount(Integer currentLiabilities2YearsAgoCount) {
        this.currentLiabilities2YearsAgoCount = currentLiabilities2YearsAgoCount;
        return this;
    }

    public Integer getCurrentLiabilities3QuartersAgoCount() {
        return currentLiabilities3QuartersAgoCount;
    }

    public StocksAveragesCounter setCurrentLiabilities3QuartersAgoCount(Integer currentLiabilities3QuartersAgoCount) {
        this.currentLiabilities3QuartersAgoCount = currentLiabilities3QuartersAgoCount;
        return this;
    }

    public Integer getCurrentLiabilities4YearsAgoCount() {
        return currentLiabilities4YearsAgoCount;
    }

    public StocksAveragesCounter setCurrentLiabilities4YearsAgoCount(Integer currentLiabilities4YearsAgoCount) {
        this.currentLiabilities4YearsAgoCount = currentLiabilities4YearsAgoCount;
        return this;
    }

    public Integer getCurrentLiabilitiesLastQuarterCount() {
        return currentLiabilitiesLastQuarterCount;
    }

    public StocksAveragesCounter setCurrentLiabilitiesLastQuarterCount(Integer currentLiabilitiesLastQuarterCount) {
        this.currentLiabilitiesLastQuarterCount = currentLiabilitiesLastQuarterCount;
        return this;
    }

    public Integer getCurrentRatio2QuartersAgoCount() {
        return currentRatio2QuartersAgoCount;
    }

    public StocksAveragesCounter setCurrentRatio2QuartersAgoCount(Integer currentRatio2QuartersAgoCount) {
        this.currentRatio2QuartersAgoCount = currentRatio2QuartersAgoCount;
        return this;
    }

    public Integer getCurrentRatioGrowthLastQuarterCount() {
        return currentRatioGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setCurrentRatioGrowthLastQuarterCount(Integer currentRatioGrowthLastQuarterCount) {
        this.currentRatioGrowthLastQuarterCount = currentRatioGrowthLastQuarterCount;
        return this;
    }

    public Integer getProfitMarginGrowthLast2QuartersCount() {
        return profitMarginGrowthLast2QuartersCount;
    }

    public StocksAveragesCounter setProfitMarginGrowthLast2QuartersCount(Integer profitMarginGrowthLast2QuartersCount) {
        this.profitMarginGrowthLast2QuartersCount = profitMarginGrowthLast2QuartersCount;
        return this;
    }

    public Integer getProfitMarginGrowthLast4YearsCount() {
        return profitMarginGrowthLast4YearsCount;
    }

    public StocksAveragesCounter setProfitMarginGrowthLast4YearsCount(Integer profitMarginGrowthLast4YearsCount) {
        this.profitMarginGrowthLast4YearsCount = profitMarginGrowthLast4YearsCount;
        return this;
    }

    public Integer getProfitMarginGrowthLastQuarterCount() {
        return profitMarginGrowthLastQuarterCount;
    }

    public StocksAveragesCounter setProfitMarginGrowthLastQuarterCount(Integer profitMarginGrowthLastQuarterCount) {
        this.profitMarginGrowthLastQuarterCount = profitMarginGrowthLastQuarterCount;
        return this;
    }

    public Integer getProfitMarginGrowthLastYearCount() {
        return profitMarginGrowthLastYearCount;
    }

    public StocksAveragesCounter setProfitMarginGrowthLastYearCount(Integer profitMarginGrowthLastYearCount) {
        this.profitMarginGrowthLastYearCount = profitMarginGrowthLastYearCount;
        return this;
    }

    public Integer getRoicLast2YearsAgoCount() {
        return roicLast2YearsAgoCount;
    }

    public StocksAveragesCounter setRoicLast2YearsAgoCount(Integer roicLast2YearsAgoCount) {
        this.roicLast2YearsAgoCount = roicLast2YearsAgoCount;
        return this;
    }

    public Integer getRoicLast4YearsAgoCount() {
        return roicLast4YearsAgoCount;
    }

    public StocksAveragesCounter setRoicLast4YearsAgoCount(Integer roicLast4YearsAgoCount) {
        this.roicLast4YearsAgoCount = roicLast4YearsAgoCount;
        return this;
    }

    public Integer getRoicLastYearCount() {
        return roicLastYearCount;
    }

    public StocksAveragesCounter setRoicLastYearCount(Integer roicLastYearCount) {
        this.roicLastYearCount = roicLastYearCount;
        return this;
    }
}
