package com.github.dusanzahoransky.stockanalyst.model.mongo;

import com.github.dusanzahoransky.stockanalyst.model.enums.Exchange;
import java.time.LocalDate;

public class StockDto {

    public StockDto(String symbol, Exchange exchange) {
        this.symbol = symbol;
        this.exchange = exchange;
    }

    private LocalDate date = LocalDate.now();
    private LocalDate lastReportedQuarter = null;
    private String symbol;
    private Exchange exchange;
    private String companyName = null;
    private Double price = null;
    private Double change = null;
    private Double enterpriseValue = null;

    private Double totalCashPerSharePercent = null;
    private Double totalDebtEquity = null;

    private Double trailingPE = null;
    private Double forwardPE = null;
    private Double priceToSalesTrailing12Months = null;
    private Double priceBook = null;
    private Double enterpriseValueRevenue = null;
    private Double enterpriseValueEBITDA = null;

    private Double priceEarningGrowth = null;
    private Double trailingPriceEarningGrowth = null;


    private Double week52Low = null;
    private Double week52AboveLowPercent = null;
    private Double week52High = null;
    private Double week52BelowHighPercent = null;


    private Double targetLowPrice = null;
    private Double belowTargetLowPricePercent = null;
    private Double targetMedianPrice = null;
    private Double belowTargetMedianPricePercent = null;


    private Double heldByInsiders = null;
    private Double heldByInstitutions = null;
    private Double buyPercentInsiderShares = null;
    private Double sellPercentInsiderShares = null;
    private Double shortToFloat = null;
    private Double sharesShortPrevMonthCompare = null;


    private String exDividendDate = null;
    private Double fiveYearAvgDividendYield = null;
    private Double trailingAnnualDividendYield = null;
    private Double payoutRatio = null;


    private Long revenueLastQuarter = null;
    private Long revenue2QuartersAgo = null;
    private Long revenue3QuartersAgo = null;
    private Long revenueLastYear = null;
    private Long revenue2YearsAgo = null;
    private Long revenue4YearsAgo = null;

    private Double revenueGrowthLastQuarter = null;
    private Double revenueGrowthLast2Quarters = null;
    private Double revenueGrowthLastYear = null;
    private Double revenueGrowthLast4Years = null;

    private Long grossIncomeLastQuarter = null;
    private Long grossIncome2QuartersAgo = null;
    private Long grossIncome3QuartersAgo = null;
    private Long grossIncomeLastYear = null;
    private Long grossIncome2YearsAgo = null;
    private Long grossIncome4YearsAgo = null;

    private Double grossIncomeGrowthLastQuarter = null;
    private Double grossIncomeGrowthLast2Quarters = null;
    private Double grossIncomeGrowthLastYear = null;
    private Double grossIncomeGrowthLast4Years = null;


    private Long ebitLastQuarter = null;
    private Long ebit2QuartersAgo = null;
    private Long ebit3QuartersAgo = null;
    private Long ebitLastYear = null;
    private Long ebit2YearsAgo = null;
    private Long ebit4YearsAgo = null;

    private Double ebitGrowthLastQuarter = null;
    private Double ebitGrowthLast2Quarters = null;
    private Double ebitGrowthLastYear = null;
    private Double ebitGrowthLast4Years = null;


    private Long netIncomeLastQuarter = null;
    private Long netIncome2QuartersAgo = null;
    private Long netIncome3QuartersAgo = null;
    private Long netIncomeLastYear = null;
    private Long netIncome2YearsAgo = null;
    private Long netIncome4YearsAgo = null;

    private Double netIncomeGrowthLastQuarter = null;
    private Double netIncomeGrowthLast2Quarters = null;
    private Double netIncomeGrowthLastYear = null;
    private Double netIncomeGrowthLast4Years = null;

    private Long profitMarginLastQuarter = null;
    private Long profitMargin2QuartersAgo = null;
    private Long profitMargin3QuartersAgo = null;
    private Long profitMarginLastYear = null;
    private Long profitMargin2YearsAgo = null;
    private Long profitMargin4YearsAgo = null;

    private Double profitMarginGrowthLastQuarter = null;
    private Double profitMarginGrowthLast2Quarters = null;
    private Double profitMarginGrowthLastYear = null;
    private Double profitMarginGrowthLast4Years = null;

    private Long freeCashFlowLastQuarter = null;
    private Long freeCashFlow2QuartersAgo = null;
    private Long freeCashFlow3QuartersAgo = null;
    private Long freeCashFlowLastYear = null;
    private Long freeCashFlow2YearsAgo = null;
    private Long freeCashFlow4YearsAgo = null;

    private Double freeCashFlowGrowthLastQuarter = null;
    private Double freeCashFlowGrowthLast2Quarters = null;
    private Double freeCashFlowGrowthLastYear = null;
    private Double freeCashFlowGrowthLast4Years = null;


    private Long cashLastQuarter = null;
    private Long cash2QuartersAgo = null;
    private Long cash3QuartersAgo = null;
    private Long cashLastYear = null;
    private Long cash2YearsAgo = null;
    private Long cash4YearsAgo = null;

    private Double cashGrowthLastQuarter = null;
    private Double cashGrowthLast2Quarters = null;
    private Double cashGrowthLastYear = null;
    private Double cashGrowthLast4Years = null;

    private Long inventoryLastQuarter = null;
    private Long inventory2QuartersAgo = null;
    private Long inventory3QuartersAgo = null;
    private Long inventoryLastYear = null;
    private Long inventory2YearsAgo = null;
    private Long inventory4YearsAgo = null;

    private Double inventoryGrowthLastQuarter = null;
    private Double inventoryGrowthLast2Quarters = null;
    private Double inventoryGrowthLastYear = null;
    private Double inventoryGrowthLast4Years = null;

    private Long currentAssetsLastQuarter = null;
    private Long currentAssets2QuartersAgo = null;
    private Long currentAssets3QuartersAgo = null;
    private Long currentAssetsLastYear = null;
    private Long currentAssets2YearsAgo = null;
    private Long currentAssets4YearsAgo = null;

    private Long currentLiabilitiesLastQuarter = null;
    private Long currentLiabilities2QuartersAgo = null;
    private Long currentLiabilities3QuartersAgo = null;
    private Long currentLiabilitiesLastYear = null;
    private Long currentLiabilities2YearsAgo = null;
    private Long currentLiabilities4YearsAgo = null;

    private Double currentRatioLastQuarter = null;
    private Double currentRatio2QuartersAgo = null;
    private Double currentRatio3QuartersAgo = null;
    private Double currentRatioLastYear = null;
    private Double currentRatio2YearsAgo = null;
    private Double currentRatio4YearsAgo = null;

    private Double currentRatioGrowthLastQuarter = null;
    private Double currentRatioGrowthLast2Quarters = null;
    private Double currentRatioGrowthLastYear = null;
    private Double currentRatioGrowthLast4Years = null;

    private Long totalLiabilitiesLastQuarter = null;
    private Long totalLiabilities2QuartersAgo = null;
    private Long totalLiabilities3QuartersAgo = null;
    private Long totalLiabilitiesLastYear = null;
    private Long totalLiabilities2YearsAgo = null;
    private Long totalLiabilities4YearsAgo = null;

    private Double totalLiabilitiesGrowthLastQuarter = null;
    private Double totalLiabilitiesGrowthLast2Quarters = null;
    private Double totalLiabilitiesGrowthLastYear = null;
    private Double totalLiabilitiesGrowthLast4Years = null;

    private Long totalAssetsLastQuarter = null;
    private Long totalAssets2QuartersAgo = null;
    private Long totalAssets3QuartersAgo = null;
    private Long totalAssetsLastYear = null;
    private Long totalAssets2YearsAgo = null;
    private Long totalAssets4YearsAgo = null;

    private Double totalAssetsGrowthLastQuarter = null;
    private Double totalAssetsGrowthLast2Quarters = null;
    private Double totalAssetsGrowthLastYear = null;
    private Double totalAssetsGrowthLast4Years = null;


    private Long totalShareholdersEquityLastQuarter = null;
    private Long totalShareholdersEquity2QuartersAgo = null;
    private Long totalShareholdersEquity3QuartersAgo = null;
    private Long totalShareholdersEquityLastYear = null;
    private Long totalShareholdersEquity2YearsAgo = null;
    private Long totalShareholdersEquity4YearsAgo = null;

    private Double totalShareholdersEquityGrowthLastQuarter = null;
    private Double totalShareholdersEquityGrowthLast2Quarters = null;
    private Double totalShareholdersEquityGrowthLastYear = null;
    private Double totalShareholdersEquityGrowthLast4Years = null;


    private Double totalLiabilitiesToEquityLastQuarter = null;
    private Double totalLiabilitiesToEquity2QuartersAgo = null;
    private Double totalLiabilitiesToEquity3QuartersAgo = null;
    private Double totalLiabilitiesToEquityLastYear = null;
    private Double totalLiabilitiesToEquity2YearsAgo = null;
    private Double totalLiabilitiesToEquity4YearsAgo = null;

    private Double totalLiabilitiesToEquityGrowthLastQuarter = null;
    private Double totalLiabilitiesToEquityGrowthLast2Quarters = null;
    private Double totalLiabilitiesToEquityGrowthLastYear = null;
    private Double totalLiabilitiesToEquityGrowthLast4Years = null;


    private Long stockRepurchasedLastQuarter = null;
    private Long stockRepurchased2QuartersAgo = null;
    private Long stockRepurchased3QuartersAgo = null;
    private Long stockRepurchasedLastYear = null;
    private Long stockRepurchased2YearsAgo = null;
    private Long stockRepurchased4YearsAgo = null;

    private Double stockRepurchasedGrowthLastQuarter = null;
    private Double stockRepurchasedGrowthLast2Quarters = null;
    private Double stockRepurchasedGrowthLastYear = null;
    private Double stockRepurchasedGrowthLast4Years = null;

    private Double stockLastQuarter = null;
    private Double stockLastYear = null;
    private Double stock2YearsAgo = null;
    private Double stock4YearsAgo = null;

    private Double stockGrowthLastQuarter = null;
    private Double stockGrowthLastYear = null;
    private Double stockGrowthLast4Years = null;

    private Double epsLastQuarter = null;
    private Double eps2QuartersAgo = null;
    private Double eps3QuartersAgo = null;
    private Double eps4QuartersAgo = null;
    private Double epsLastYear = null;
    private Double eps2YearsAgo = null;
    private Double eps3YearsAgo = null;
    private Double eps4YearsAgo = null;

    private Double epsGrowthLastQuarter = null;
    private Double epsGrowthLast2Quarters = null;
    private Double epsGrowthLastYear = null;
    private Double epsGrowthLast4Years = null;


    private Double peLastQuarter = null;
    private Double pe2QuartersAgo = null;
    private Double pe3QuartersAgo = null;
    private Double pe4QuartersAgo = null;
    private Double peLastYear = null;
    private Double pe2YearsAgo = null;
    private Double pe3YearsAgo = null;
    private Double pe4YearsAgo = null;

    private Double peGrowthLastQuarter = null;
    private Double peGrowthLast2Quarters = null;
    private Double peGrowthLastYear = null;
    private Double peGrowthLast4Years = null;

    private Double growthEstimate5y = null;

    private Double roicLastYear = null;
    private Double roicLast2YearsAgo = null;
    private Double roicLast4YearsAgo = null;

    private Double roic1Y = null;
    private Double roic3Y = null;

    private Double revenue1Y = null;
    private Double revenue3Y = null;
    private Double revenue5Y = null;
    private Double revenue9Y = null;

    private Double eps1Y = null;
    private Double eps3Y = null;
    private Double eps5Y = null;
    private Double eps9Y = null;

    private Double bps1Y = null;
    private Double bps3Y = null;
    private Double bps5Y = null;
    private Double bps9Y = null;

    private Double cash1Y = null;
    private Double cash3Y = null;
    private Double cash5Y = null;
    private Double cash9Y = null;

    private Double pe1Y = null;
    private Double pe3Y = null;
    private Double pe5Y = null;
    private Double pe9Y = null;

    private Double rule1GrowthRate = null;
    private Double defaultPE = null;
    private Double historicalPE = null;
    private Double rule1PE = null;
    private Double currentEps = null;
    private Double futureEPS10Years = null;
    private Double futurePrice10Years = null;

    private Double stickerPrice15pcGrowth = null;
    private Double stickerPrice10pcGrowth = null;
    private Double stickerPrice5pcGrowth = null;

    private Double belowStickerPrice15pc = null;
    private Double belowStickerPrice10pc = null;
    private Double belowStickerPrice5pc = null;

    public LocalDate getDate() {
        return date;
    }

    public StockDto setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LocalDate getLastReportedQuarter() {
        return lastReportedQuarter;
    }

    public StockDto setLastReportedQuarter(LocalDate lastReportedQuarter) {
        this.lastReportedQuarter = lastReportedQuarter;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public StockDto setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public StockDto setExchange(Exchange exchange) {
        this.exchange = exchange;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public StockDto setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public StockDto setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getChange() {
        return change;
    }

    public StockDto setChange(Double change) {
        this.change = change;
        return this;
    }

    public Double getEnterpriseValue() {
        return enterpriseValue;
    }

    public StockDto setEnterpriseValue(Double enterpriseValue) {
        this.enterpriseValue = enterpriseValue;
        return this;
    }

    public Double getTotalCashPerSharePercent() {
        return totalCashPerSharePercent;
    }

    public StockDto setTotalCashPerSharePercent(Double totalCashPerSharePercent) {
        this.totalCashPerSharePercent = totalCashPerSharePercent;
        return this;
    }

    public Double getTotalDebtEquity() {
        return totalDebtEquity;
    }

    public StockDto setTotalDebtEquity(Double totalDebtEquity) {
        this.totalDebtEquity = totalDebtEquity;
        return this;
    }

    public Double getTrailingPE() {
        return trailingPE;
    }

    public StockDto setTrailingPE(Double trailingPE) {
        this.trailingPE = trailingPE;
        return this;
    }

    public Double getForwardPE() {
        return forwardPE;
    }

    public StockDto setForwardPE(Double forwardPE) {
        this.forwardPE = forwardPE;
        return this;
    }

    public Double getPriceToSalesTrailing12Months() {
        return priceToSalesTrailing12Months;
    }

    public StockDto setPriceToSalesTrailing12Months(Double priceToSalesTrailing12Months) {
        this.priceToSalesTrailing12Months = priceToSalesTrailing12Months;
        return this;
    }

    public Double getPriceBook() {
        return priceBook;
    }

    public StockDto setPriceBook(Double priceBook) {
        this.priceBook = priceBook;
        return this;
    }

    public Double getEnterpriseValueRevenue() {
        return enterpriseValueRevenue;
    }

    public StockDto setEnterpriseValueRevenue(Double enterpriseValueRevenue) {
        this.enterpriseValueRevenue = enterpriseValueRevenue;
        return this;
    }

    public Double getEnterpriseValueEBITDA() {
        return enterpriseValueEBITDA;
    }

    public StockDto setEnterpriseValueEBITDA(Double enterpriseValueEBITDA) {
        this.enterpriseValueEBITDA = enterpriseValueEBITDA;
        return this;
    }

    public Double getPriceEarningGrowth() {
        return priceEarningGrowth;
    }

    public StockDto setPriceEarningGrowth(Double priceEarningGrowth) {
        this.priceEarningGrowth = priceEarningGrowth;
        return this;
    }

    public Double getTrailingPriceEarningGrowth() {
        return trailingPriceEarningGrowth;
    }

    public StockDto setTrailingPriceEarningGrowth(Double trailingPriceEarningGrowth) {
        this.trailingPriceEarningGrowth = trailingPriceEarningGrowth;
        return this;
    }

    public Double getWeek52Low() {
        return week52Low;
    }

    public StockDto setWeek52Low(Double week52Low) {
        this.week52Low = week52Low;
        return this;
    }

    public Double getWeek52AboveLowPercent() {
        return week52AboveLowPercent;
    }

    public StockDto setWeek52AboveLowPercent(Double week52AboveLowPercent) {
        this.week52AboveLowPercent = week52AboveLowPercent;
        return this;
    }

    public Double getWeek52High() {
        return week52High;
    }

    public StockDto setWeek52High(Double week52High) {
        this.week52High = week52High;
        return this;
    }

    public Double getWeek52BelowHighPercent() {
        return week52BelowHighPercent;
    }

    public StockDto setWeek52BelowHighPercent(Double week52BelowHighPercent) {
        this.week52BelowHighPercent = week52BelowHighPercent;
        return this;
    }

    public Double getTargetLowPrice() {
        return targetLowPrice;
    }

    public StockDto setTargetLowPrice(Double targetLowPrice) {
        this.targetLowPrice = targetLowPrice;
        return this;
    }

    public Double getBelowTargetLowPricePercent() {
        return belowTargetLowPricePercent;
    }

    public StockDto setBelowTargetLowPricePercent(Double belowTargetLowPricePercent) {
        this.belowTargetLowPricePercent = belowTargetLowPricePercent;
        return this;
    }

    public Double getTargetMedianPrice() {
        return targetMedianPrice;
    }

    public StockDto setTargetMedianPrice(Double targetMedianPrice) {
        this.targetMedianPrice = targetMedianPrice;
        return this;
    }

    public Double getBelowTargetMedianPricePercent() {
        return belowTargetMedianPricePercent;
    }

    public StockDto setBelowTargetMedianPricePercent(Double belowTargetMedianPricePercent) {
        this.belowTargetMedianPricePercent = belowTargetMedianPricePercent;
        return this;
    }

    public Double getHeldByInsiders() {
        return heldByInsiders;
    }

    public StockDto setHeldByInsiders(Double heldByInsiders) {
        this.heldByInsiders = heldByInsiders;
        return this;
    }

    public Double getHeldByInstitutions() {
        return heldByInstitutions;
    }

    public StockDto setHeldByInstitutions(Double heldByInstitutions) {
        this.heldByInstitutions = heldByInstitutions;
        return this;
    }

    public Double getBuyPercentInsiderShares() {
        return buyPercentInsiderShares;
    }

    public StockDto setBuyPercentInsiderShares(Double buyPercentInsiderShares) {
        this.buyPercentInsiderShares = buyPercentInsiderShares;
        return this;
    }

    public Double getSellPercentInsiderShares() {
        return sellPercentInsiderShares;
    }

    public StockDto setSellPercentInsiderShares(Double sellPercentInsiderShares) {
        this.sellPercentInsiderShares = sellPercentInsiderShares;
        return this;
    }

    public Double getShortToFloat() {
        return shortToFloat;
    }

    public StockDto setShortToFloat(Double shortToFloat) {
        this.shortToFloat = shortToFloat;
        return this;
    }

    public Double getSharesShortPrevMonthCompare() {
        return sharesShortPrevMonthCompare;
    }

    public StockDto setSharesShortPrevMonthCompare(Double sharesShortPrevMonthCompare) {
        this.sharesShortPrevMonthCompare = sharesShortPrevMonthCompare;
        return this;
    }

    public String getExDividendDate() {
        return exDividendDate;
    }

    public StockDto setExDividendDate(String exDividendDate) {
        this.exDividendDate = exDividendDate;
        return this;
    }

    public Double getFiveYearAvgDividendYield() {
        return fiveYearAvgDividendYield;
    }

    public StockDto setFiveYearAvgDividendYield(Double fiveYearAvgDividendYield) {
        this.fiveYearAvgDividendYield = fiveYearAvgDividendYield;
        return this;
    }

    public Double getTrailingAnnualDividendYield() {
        return trailingAnnualDividendYield;
    }

    public StockDto setTrailingAnnualDividendYield(Double trailingAnnualDividendYield) {
        this.trailingAnnualDividendYield = trailingAnnualDividendYield;
        return this;
    }

    public Double getPayoutRatio() {
        return payoutRatio;
    }

    public StockDto setPayoutRatio(Double payoutRatio) {
        this.payoutRatio = payoutRatio;
        return this;
    }

    public Long getRevenueLastQuarter() {
        return revenueLastQuarter;
    }

    public StockDto setRevenueLastQuarter(Long revenueLastQuarter) {
        this.revenueLastQuarter = revenueLastQuarter;
        return this;
    }

    public Long getRevenue2QuartersAgo() {
        return revenue2QuartersAgo;
    }

    public StockDto setRevenue2QuartersAgo(Long revenue2QuartersAgo) {
        this.revenue2QuartersAgo = revenue2QuartersAgo;
        return this;
    }

    public Long getRevenue3QuartersAgo() {
        return revenue3QuartersAgo;
    }

    public StockDto setRevenue3QuartersAgo(Long revenue3QuartersAgo) {
        this.revenue3QuartersAgo = revenue3QuartersAgo;
        return this;
    }

    public Long getRevenueLastYear() {
        return revenueLastYear;
    }

    public StockDto setRevenueLastYear(Long revenueLastYear) {
        this.revenueLastYear = revenueLastYear;
        return this;
    }

    public Long getRevenue2YearsAgo() {
        return revenue2YearsAgo;
    }

    public StockDto setRevenue2YearsAgo(Long revenue2YearsAgo) {
        this.revenue2YearsAgo = revenue2YearsAgo;
        return this;
    }

    public Long getRevenue4YearsAgo() {
        return revenue4YearsAgo;
    }

    public StockDto setRevenue4YearsAgo(Long revenue4YearsAgo) {
        this.revenue4YearsAgo = revenue4YearsAgo;
        return this;
    }

    public Double getRevenueGrowthLastQuarter() {
        return revenueGrowthLastQuarter;
    }

    public StockDto setRevenueGrowthLastQuarter(Double revenueGrowthLastQuarter) {
        this.revenueGrowthLastQuarter = revenueGrowthLastQuarter;
        return this;
    }

    public Double getRevenueGrowthLast2Quarters() {
        return revenueGrowthLast2Quarters;
    }

    public StockDto setRevenueGrowthLast2Quarters(Double revenueGrowthLast2Quarters) {
        this.revenueGrowthLast2Quarters = revenueGrowthLast2Quarters;
        return this;
    }

    public Double getRevenueGrowthLastYear() {
        return revenueGrowthLastYear;
    }

    public StockDto setRevenueGrowthLastYear(Double revenueGrowthLastYear) {
        this.revenueGrowthLastYear = revenueGrowthLastYear;
        return this;
    }

    public Double getRevenueGrowthLast4Years() {
        return revenueGrowthLast4Years;
    }

    public StockDto setRevenueGrowthLast4Years(Double revenueGrowthLast4Years) {
        this.revenueGrowthLast4Years = revenueGrowthLast4Years;
        return this;
    }

    public Long getGrossIncomeLastQuarter() {
        return grossIncomeLastQuarter;
    }

    public StockDto setGrossIncomeLastQuarter(Long grossIncomeLastQuarter) {
        this.grossIncomeLastQuarter = grossIncomeLastQuarter;
        return this;
    }

    public Long getGrossIncome2QuartersAgo() {
        return grossIncome2QuartersAgo;
    }

    public StockDto setGrossIncome2QuartersAgo(Long grossIncome2QuartersAgo) {
        this.grossIncome2QuartersAgo = grossIncome2QuartersAgo;
        return this;
    }

    public Long getGrossIncome3QuartersAgo() {
        return grossIncome3QuartersAgo;
    }

    public StockDto setGrossIncome3QuartersAgo(Long grossIncome3QuartersAgo) {
        this.grossIncome3QuartersAgo = grossIncome3QuartersAgo;
        return this;
    }

    public Long getGrossIncomeLastYear() {
        return grossIncomeLastYear;
    }

    public StockDto setGrossIncomeLastYear(Long grossIncomeLastYear) {
        this.grossIncomeLastYear = grossIncomeLastYear;
        return this;
    }

    public Long getGrossIncome2YearsAgo() {
        return grossIncome2YearsAgo;
    }

    public StockDto setGrossIncome2YearsAgo(Long grossIncome2YearsAgo) {
        this.grossIncome2YearsAgo = grossIncome2YearsAgo;
        return this;
    }

    public Long getGrossIncome4YearsAgo() {
        return grossIncome4YearsAgo;
    }

    public StockDto setGrossIncome4YearsAgo(Long grossIncome4YearsAgo) {
        this.grossIncome4YearsAgo = grossIncome4YearsAgo;
        return this;
    }

    public Double getGrossIncomeGrowthLastQuarter() {
        return grossIncomeGrowthLastQuarter;
    }

    public StockDto setGrossIncomeGrowthLastQuarter(Double grossIncomeGrowthLastQuarter) {
        this.grossIncomeGrowthLastQuarter = grossIncomeGrowthLastQuarter;
        return this;
    }

    public Double getGrossIncomeGrowthLast2Quarters() {
        return grossIncomeGrowthLast2Quarters;
    }

    public StockDto setGrossIncomeGrowthLast2Quarters(Double grossIncomeGrowthLast2Quarters) {
        this.grossIncomeGrowthLast2Quarters = grossIncomeGrowthLast2Quarters;
        return this;
    }

    public Double getGrossIncomeGrowthLastYear() {
        return grossIncomeGrowthLastYear;
    }

    public StockDto setGrossIncomeGrowthLastYear(Double grossIncomeGrowthLastYear) {
        this.grossIncomeGrowthLastYear = grossIncomeGrowthLastYear;
        return this;
    }

    public Double getGrossIncomeGrowthLast4Years() {
        return grossIncomeGrowthLast4Years;
    }

    public StockDto setGrossIncomeGrowthLast4Years(Double grossIncomeGrowthLast4Years) {
        this.grossIncomeGrowthLast4Years = grossIncomeGrowthLast4Years;
        return this;
    }

    public Long getEbitLastQuarter() {
        return ebitLastQuarter;
    }

    public StockDto setEbitLastQuarter(Long ebitLastQuarter) {
        this.ebitLastQuarter = ebitLastQuarter;
        return this;
    }

    public Long getEbit2QuartersAgo() {
        return ebit2QuartersAgo;
    }

    public StockDto setEbit2QuartersAgo(Long ebit2QuartersAgo) {
        this.ebit2QuartersAgo = ebit2QuartersAgo;
        return this;
    }

    public Long getEbit3QuartersAgo() {
        return ebit3QuartersAgo;
    }

    public StockDto setEbit3QuartersAgo(Long ebit3QuartersAgo) {
        this.ebit3QuartersAgo = ebit3QuartersAgo;
        return this;
    }

    public Long getEbitLastYear() {
        return ebitLastYear;
    }

    public StockDto setEbitLastYear(Long ebitLastYear) {
        this.ebitLastYear = ebitLastYear;
        return this;
    }

    public Long getEbit2YearsAgo() {
        return ebit2YearsAgo;
    }

    public StockDto setEbit2YearsAgo(Long ebit2YearsAgo) {
        this.ebit2YearsAgo = ebit2YearsAgo;
        return this;
    }

    public Long getEbit4YearsAgo() {
        return ebit4YearsAgo;
    }

    public StockDto setEbit4YearsAgo(Long ebit4YearsAgo) {
        this.ebit4YearsAgo = ebit4YearsAgo;
        return this;
    }

    public Double getEbitGrowthLastQuarter() {
        return ebitGrowthLastQuarter;
    }

    public StockDto setEbitGrowthLastQuarter(Double ebitGrowthLastQuarter) {
        this.ebitGrowthLastQuarter = ebitGrowthLastQuarter;
        return this;
    }

    public Double getEbitGrowthLast2Quarters() {
        return ebitGrowthLast2Quarters;
    }

    public StockDto setEbitGrowthLast2Quarters(Double ebitGrowthLast2Quarters) {
        this.ebitGrowthLast2Quarters = ebitGrowthLast2Quarters;
        return this;
    }

    public Double getEbitGrowthLastYear() {
        return ebitGrowthLastYear;
    }

    public StockDto setEbitGrowthLastYear(Double ebitGrowthLastYear) {
        this.ebitGrowthLastYear = ebitGrowthLastYear;
        return this;
    }

    public Double getEbitGrowthLast4Years() {
        return ebitGrowthLast4Years;
    }

    public StockDto setEbitGrowthLast4Years(Double ebitGrowthLast4Years) {
        this.ebitGrowthLast4Years = ebitGrowthLast4Years;
        return this;
    }

    public Long getNetIncomeLastQuarter() {
        return netIncomeLastQuarter;
    }

    public StockDto setNetIncomeLastQuarter(Long netIncomeLastQuarter) {
        this.netIncomeLastQuarter = netIncomeLastQuarter;
        return this;
    }

    public Long getNetIncome2QuartersAgo() {
        return netIncome2QuartersAgo;
    }

    public StockDto setNetIncome2QuartersAgo(Long netIncome2QuartersAgo) {
        this.netIncome2QuartersAgo = netIncome2QuartersAgo;
        return this;
    }

    public Long getNetIncome3QuartersAgo() {
        return netIncome3QuartersAgo;
    }

    public StockDto setNetIncome3QuartersAgo(Long netIncome3QuartersAgo) {
        this.netIncome3QuartersAgo = netIncome3QuartersAgo;
        return this;
    }

    public Long getNetIncomeLastYear() {
        return netIncomeLastYear;
    }

    public StockDto setNetIncomeLastYear(Long netIncomeLastYear) {
        this.netIncomeLastYear = netIncomeLastYear;
        return this;
    }

    public Long getNetIncome2YearsAgo() {
        return netIncome2YearsAgo;
    }

    public StockDto setNetIncome2YearsAgo(Long netIncome2YearsAgo) {
        this.netIncome2YearsAgo = netIncome2YearsAgo;
        return this;
    }

    public Long getNetIncome4YearsAgo() {
        return netIncome4YearsAgo;
    }

    public StockDto setNetIncome4YearsAgo(Long netIncome4YearsAgo) {
        this.netIncome4YearsAgo = netIncome4YearsAgo;
        return this;
    }

    public Double getNetIncomeGrowthLastQuarter() {
        return netIncomeGrowthLastQuarter;
    }

    public StockDto setNetIncomeGrowthLastQuarter(Double netIncomeGrowthLastQuarter) {
        this.netIncomeGrowthLastQuarter = netIncomeGrowthLastQuarter;
        return this;
    }

    public Double getNetIncomeGrowthLast2Quarters() {
        return netIncomeGrowthLast2Quarters;
    }

    public StockDto setNetIncomeGrowthLast2Quarters(Double netIncomeGrowthLast2Quarters) {
        this.netIncomeGrowthLast2Quarters = netIncomeGrowthLast2Quarters;
        return this;
    }

    public Double getNetIncomeGrowthLastYear() {
        return netIncomeGrowthLastYear;
    }

    public StockDto setNetIncomeGrowthLastYear(Double netIncomeGrowthLastYear) {
        this.netIncomeGrowthLastYear = netIncomeGrowthLastYear;
        return this;
    }

    public Double getNetIncomeGrowthLast4Years() {
        return netIncomeGrowthLast4Years;
    }

    public StockDto setNetIncomeGrowthLast4Years(Double netIncomeGrowthLast4Years) {
        this.netIncomeGrowthLast4Years = netIncomeGrowthLast4Years;
        return this;
    }

    public Long getProfitMarginLastQuarter() {
        return profitMarginLastQuarter;
    }

    public StockDto setProfitMarginLastQuarter(Long profitMarginLastQuarter) {
        this.profitMarginLastQuarter = profitMarginLastQuarter;
        return this;
    }

    public Long getProfitMargin2QuartersAgo() {
        return profitMargin2QuartersAgo;
    }

    public StockDto setProfitMargin2QuartersAgo(Long profitMargin2QuartersAgo) {
        this.profitMargin2QuartersAgo = profitMargin2QuartersAgo;
        return this;
    }

    public Long getProfitMargin3QuartersAgo() {
        return profitMargin3QuartersAgo;
    }

    public StockDto setProfitMargin3QuartersAgo(Long profitMargin3QuartersAgo) {
        this.profitMargin3QuartersAgo = profitMargin3QuartersAgo;
        return this;
    }

    public Long getProfitMarginLastYear() {
        return profitMarginLastYear;
    }

    public StockDto setProfitMarginLastYear(Long profitMarginLastYear) {
        this.profitMarginLastYear = profitMarginLastYear;
        return this;
    }

    public Long getProfitMargin2YearsAgo() {
        return profitMargin2YearsAgo;
    }

    public StockDto setProfitMargin2YearsAgo(Long profitMargin2YearsAgo) {
        this.profitMargin2YearsAgo = profitMargin2YearsAgo;
        return this;
    }

    public Long getProfitMargin4YearsAgo() {
        return profitMargin4YearsAgo;
    }

    public StockDto setProfitMargin4YearsAgo(Long profitMargin4YearsAgo) {
        this.profitMargin4YearsAgo = profitMargin4YearsAgo;
        return this;
    }

    public Double getProfitMarginGrowthLastQuarter() {
        return profitMarginGrowthLastQuarter;
    }

    public StockDto setProfitMarginGrowthLastQuarter(Double profitMarginGrowthLastQuarter) {
        this.profitMarginGrowthLastQuarter = profitMarginGrowthLastQuarter;
        return this;
    }

    public Double getProfitMarginGrowthLast2Quarters() {
        return profitMarginGrowthLast2Quarters;
    }

    public StockDto setProfitMarginGrowthLast2Quarters(Double profitMarginGrowthLast2Quarters) {
        this.profitMarginGrowthLast2Quarters = profitMarginGrowthLast2Quarters;
        return this;
    }

    public Double getProfitMarginGrowthLastYear() {
        return profitMarginGrowthLastYear;
    }

    public StockDto setProfitMarginGrowthLastYear(Double profitMarginGrowthLastYear) {
        this.profitMarginGrowthLastYear = profitMarginGrowthLastYear;
        return this;
    }

    public Double getProfitMarginGrowthLast4Years() {
        return profitMarginGrowthLast4Years;
    }

    public StockDto setProfitMarginGrowthLast4Years(Double profitMarginGrowthLast4Years) {
        this.profitMarginGrowthLast4Years = profitMarginGrowthLast4Years;
        return this;
    }

    public Long getFreeCashFlowLastQuarter() {
        return freeCashFlowLastQuarter;
    }

    public StockDto setFreeCashFlowLastQuarter(Long freeCashFlowLastQuarter) {
        this.freeCashFlowLastQuarter = freeCashFlowLastQuarter;
        return this;
    }

    public Long getFreeCashFlow2QuartersAgo() {
        return freeCashFlow2QuartersAgo;
    }

    public StockDto setFreeCashFlow2QuartersAgo(Long freeCashFlow2QuartersAgo) {
        this.freeCashFlow2QuartersAgo = freeCashFlow2QuartersAgo;
        return this;
    }

    public Long getFreeCashFlow3QuartersAgo() {
        return freeCashFlow3QuartersAgo;
    }

    public StockDto setFreeCashFlow3QuartersAgo(Long freeCashFlow3QuartersAgo) {
        this.freeCashFlow3QuartersAgo = freeCashFlow3QuartersAgo;
        return this;
    }

    public Long getFreeCashFlowLastYear() {
        return freeCashFlowLastYear;
    }

    public StockDto setFreeCashFlowLastYear(Long freeCashFlowLastYear) {
        this.freeCashFlowLastYear = freeCashFlowLastYear;
        return this;
    }

    public Long getFreeCashFlow2YearsAgo() {
        return freeCashFlow2YearsAgo;
    }

    public StockDto setFreeCashFlow2YearsAgo(Long freeCashFlow2YearsAgo) {
        this.freeCashFlow2YearsAgo = freeCashFlow2YearsAgo;
        return this;
    }

    public Long getFreeCashFlow4YearsAgo() {
        return freeCashFlow4YearsAgo;
    }

    public StockDto setFreeCashFlow4YearsAgo(Long freeCashFlow4YearsAgo) {
        this.freeCashFlow4YearsAgo = freeCashFlow4YearsAgo;
        return this;
    }

    public Double getFreeCashFlowGrowthLastQuarter() {
        return freeCashFlowGrowthLastQuarter;
    }

    public StockDto setFreeCashFlowGrowthLastQuarter(Double freeCashFlowGrowthLastQuarter) {
        this.freeCashFlowGrowthLastQuarter = freeCashFlowGrowthLastQuarter;
        return this;
    }

    public Double getFreeCashFlowGrowthLast2Quarters() {
        return freeCashFlowGrowthLast2Quarters;
    }

    public StockDto setFreeCashFlowGrowthLast2Quarters(Double freeCashFlowGrowthLast2Quarters) {
        this.freeCashFlowGrowthLast2Quarters = freeCashFlowGrowthLast2Quarters;
        return this;
    }

    public Double getFreeCashFlowGrowthLastYear() {
        return freeCashFlowGrowthLastYear;
    }

    public StockDto setFreeCashFlowGrowthLastYear(Double freeCashFlowGrowthLastYear) {
        this.freeCashFlowGrowthLastYear = freeCashFlowGrowthLastYear;
        return this;
    }

    public Double getFreeCashFlowGrowthLast4Years() {
        return freeCashFlowGrowthLast4Years;
    }

    public StockDto setFreeCashFlowGrowthLast4Years(Double freeCashFlowGrowthLast4Years) {
        this.freeCashFlowGrowthLast4Years = freeCashFlowGrowthLast4Years;
        return this;
    }

    public Long getCashLastQuarter() {
        return cashLastQuarter;
    }

    public StockDto setCashLastQuarter(Long cashLastQuarter) {
        this.cashLastQuarter = cashLastQuarter;
        return this;
    }

    public Long getCash2QuartersAgo() {
        return cash2QuartersAgo;
    }

    public StockDto setCash2QuartersAgo(Long cash2QuartersAgo) {
        this.cash2QuartersAgo = cash2QuartersAgo;
        return this;
    }

    public Long getCash3QuartersAgo() {
        return cash3QuartersAgo;
    }

    public StockDto setCash3QuartersAgo(Long cash3QuartersAgo) {
        this.cash3QuartersAgo = cash3QuartersAgo;
        return this;
    }

    public Long getCashLastYear() {
        return cashLastYear;
    }

    public StockDto setCashLastYear(Long cashLastYear) {
        this.cashLastYear = cashLastYear;
        return this;
    }

    public Long getCash2YearsAgo() {
        return cash2YearsAgo;
    }

    public StockDto setCash2YearsAgo(Long cash2YearsAgo) {
        this.cash2YearsAgo = cash2YearsAgo;
        return this;
    }

    public Long getCash4YearsAgo() {
        return cash4YearsAgo;
    }

    public StockDto setCash4YearsAgo(Long cash4YearsAgo) {
        this.cash4YearsAgo = cash4YearsAgo;
        return this;
    }

    public Double getCashGrowthLastQuarter() {
        return cashGrowthLastQuarter;
    }

    public StockDto setCashGrowthLastQuarter(Double cashGrowthLastQuarter) {
        this.cashGrowthLastQuarter = cashGrowthLastQuarter;
        return this;
    }

    public Double getCashGrowthLast2Quarters() {
        return cashGrowthLast2Quarters;
    }

    public StockDto setCashGrowthLast2Quarters(Double cashGrowthLast2Quarters) {
        this.cashGrowthLast2Quarters = cashGrowthLast2Quarters;
        return this;
    }

    public Double getCashGrowthLastYear() {
        return cashGrowthLastYear;
    }

    public StockDto setCashGrowthLastYear(Double cashGrowthLastYear) {
        this.cashGrowthLastYear = cashGrowthLastYear;
        return this;
    }

    public Double getCashGrowthLast4Years() {
        return cashGrowthLast4Years;
    }

    public StockDto setCashGrowthLast4Years(Double cashGrowthLast4Years) {
        this.cashGrowthLast4Years = cashGrowthLast4Years;
        return this;
    }

    public Long getInventoryLastQuarter() {
        return inventoryLastQuarter;
    }

    public StockDto setInventoryLastQuarter(Long inventoryLastQuarter) {
        this.inventoryLastQuarter = inventoryLastQuarter;
        return this;
    }

    public Long getInventory2QuartersAgo() {
        return inventory2QuartersAgo;
    }

    public StockDto setInventory2QuartersAgo(Long inventory2QuartersAgo) {
        this.inventory2QuartersAgo = inventory2QuartersAgo;
        return this;
    }

    public Long getInventory3QuartersAgo() {
        return inventory3QuartersAgo;
    }

    public StockDto setInventory3QuartersAgo(Long inventory3QuartersAgo) {
        this.inventory3QuartersAgo = inventory3QuartersAgo;
        return this;
    }

    public Long getInventoryLastYear() {
        return inventoryLastYear;
    }

    public StockDto setInventoryLastYear(Long inventoryLastYear) {
        this.inventoryLastYear = inventoryLastYear;
        return this;
    }

    public Long getInventory2YearsAgo() {
        return inventory2YearsAgo;
    }

    public StockDto setInventory2YearsAgo(Long inventory2YearsAgo) {
        this.inventory2YearsAgo = inventory2YearsAgo;
        return this;
    }

    public Long getInventory4YearsAgo() {
        return inventory4YearsAgo;
    }

    public StockDto setInventory4YearsAgo(Long inventory4YearsAgo) {
        this.inventory4YearsAgo = inventory4YearsAgo;
        return this;
    }

    public Double getInventoryGrowthLastQuarter() {
        return inventoryGrowthLastQuarter;
    }

    public StockDto setInventoryGrowthLastQuarter(Double inventoryGrowthLastQuarter) {
        this.inventoryGrowthLastQuarter = inventoryGrowthLastQuarter;
        return this;
    }

    public Double getInventoryGrowthLast2Quarters() {
        return inventoryGrowthLast2Quarters;
    }

    public StockDto setInventoryGrowthLast2Quarters(Double inventoryGrowthLast2Quarters) {
        this.inventoryGrowthLast2Quarters = inventoryGrowthLast2Quarters;
        return this;
    }

    public Double getInventoryGrowthLastYear() {
        return inventoryGrowthLastYear;
    }

    public StockDto setInventoryGrowthLastYear(Double inventoryGrowthLastYear) {
        this.inventoryGrowthLastYear = inventoryGrowthLastYear;
        return this;
    }

    public Double getInventoryGrowthLast4Years() {
        return inventoryGrowthLast4Years;
    }

    public StockDto setInventoryGrowthLast4Years(Double inventoryGrowthLast4Years) {
        this.inventoryGrowthLast4Years = inventoryGrowthLast4Years;
        return this;
    }

    public Long getCurrentAssetsLastQuarter() {
        return currentAssetsLastQuarter;
    }

    public StockDto setCurrentAssetsLastQuarter(Long currentAssetsLastQuarter) {
        this.currentAssetsLastQuarter = currentAssetsLastQuarter;
        return this;
    }

    public Long getCurrentAssets2QuartersAgo() {
        return currentAssets2QuartersAgo;
    }

    public StockDto setCurrentAssets2QuartersAgo(Long currentAssets2QuartersAgo) {
        this.currentAssets2QuartersAgo = currentAssets2QuartersAgo;
        return this;
    }

    public Long getCurrentAssets3QuartersAgo() {
        return currentAssets3QuartersAgo;
    }

    public StockDto setCurrentAssets3QuartersAgo(Long currentAssets3QuartersAgo) {
        this.currentAssets3QuartersAgo = currentAssets3QuartersAgo;
        return this;
    }

    public Long getCurrentAssetsLastYear() {
        return currentAssetsLastYear;
    }

    public StockDto setCurrentAssetsLastYear(Long currentAssetsLastYear) {
        this.currentAssetsLastYear = currentAssetsLastYear;
        return this;
    }

    public Long getCurrentAssets2YearsAgo() {
        return currentAssets2YearsAgo;
    }

    public StockDto setCurrentAssets2YearsAgo(Long currentAssets2YearsAgo) {
        this.currentAssets2YearsAgo = currentAssets2YearsAgo;
        return this;
    }

    public Long getCurrentAssets4YearsAgo() {
        return currentAssets4YearsAgo;
    }

    public StockDto setCurrentAssets4YearsAgo(Long currentAssets4YearsAgo) {
        this.currentAssets4YearsAgo = currentAssets4YearsAgo;
        return this;
    }

    public Long getCurrentLiabilitiesLastQuarter() {
        return currentLiabilitiesLastQuarter;
    }

    public StockDto setCurrentLiabilitiesLastQuarter(Long currentLiabilitiesLastQuarter) {
        this.currentLiabilitiesLastQuarter = currentLiabilitiesLastQuarter;
        return this;
    }

    public Long getCurrentLiabilities2QuartersAgo() {
        return currentLiabilities2QuartersAgo;
    }

    public StockDto setCurrentLiabilities2QuartersAgo(Long currentLiabilities2QuartersAgo) {
        this.currentLiabilities2QuartersAgo = currentLiabilities2QuartersAgo;
        return this;
    }

    public Long getCurrentLiabilities3QuartersAgo() {
        return currentLiabilities3QuartersAgo;
    }

    public StockDto setCurrentLiabilities3QuartersAgo(Long currentLiabilities3QuartersAgo) {
        this.currentLiabilities3QuartersAgo = currentLiabilities3QuartersAgo;
        return this;
    }

    public Long getCurrentLiabilitiesLastYear() {
        return currentLiabilitiesLastYear;
    }

    public StockDto setCurrentLiabilitiesLastYear(Long currentLiabilitiesLastYear) {
        this.currentLiabilitiesLastYear = currentLiabilitiesLastYear;
        return this;
    }

    public Long getCurrentLiabilities2YearsAgo() {
        return currentLiabilities2YearsAgo;
    }

    public StockDto setCurrentLiabilities2YearsAgo(Long currentLiabilities2YearsAgo) {
        this.currentLiabilities2YearsAgo = currentLiabilities2YearsAgo;
        return this;
    }

    public Long getCurrentLiabilities4YearsAgo() {
        return currentLiabilities4YearsAgo;
    }

    public StockDto setCurrentLiabilities4YearsAgo(Long currentLiabilities4YearsAgo) {
        this.currentLiabilities4YearsAgo = currentLiabilities4YearsAgo;
        return this;
    }

    public Double getCurrentRatioLastQuarter() {
        return currentRatioLastQuarter;
    }

    public StockDto setCurrentRatioLastQuarter(Double currentRatioLastQuarter) {
        this.currentRatioLastQuarter = currentRatioLastQuarter;
        return this;
    }

    public Double getCurrentRatio2QuartersAgo() {
        return currentRatio2QuartersAgo;
    }

    public StockDto setCurrentRatio2QuartersAgo(Double currentRatio2QuartersAgo) {
        this.currentRatio2QuartersAgo = currentRatio2QuartersAgo;
        return this;
    }

    public Double getCurrentRatio3QuartersAgo() {
        return currentRatio3QuartersAgo;
    }

    public StockDto setCurrentRatio3QuartersAgo(Double currentRatio3QuartersAgo) {
        this.currentRatio3QuartersAgo = currentRatio3QuartersAgo;
        return this;
    }

    public Double getCurrentRatioLastYear() {
        return currentRatioLastYear;
    }

    public StockDto setCurrentRatioLastYear(Double currentRatioLastYear) {
        this.currentRatioLastYear = currentRatioLastYear;
        return this;
    }

    public Double getCurrentRatio2YearsAgo() {
        return currentRatio2YearsAgo;
    }

    public StockDto setCurrentRatio2YearsAgo(Double currentRatio2YearsAgo) {
        this.currentRatio2YearsAgo = currentRatio2YearsAgo;
        return this;
    }

    public Double getCurrentRatio4YearsAgo() {
        return currentRatio4YearsAgo;
    }

    public StockDto setCurrentRatio4YearsAgo(Double currentRatio4YearsAgo) {
        this.currentRatio4YearsAgo = currentRatio4YearsAgo;
        return this;
    }

    public Double getCurrentRatioGrowthLastQuarter() {
        return currentRatioGrowthLastQuarter;
    }

    public StockDto setCurrentRatioGrowthLastQuarter(Double currentRatioGrowthLastQuarter) {
        this.currentRatioGrowthLastQuarter = currentRatioGrowthLastQuarter;
        return this;
    }

    public Double getCurrentRatioGrowthLast2Quarters() {
        return currentRatioGrowthLast2Quarters;
    }

    public StockDto setCurrentRatioGrowthLast2Quarters(Double currentRatioGrowthLast2Quarters) {
        this.currentRatioGrowthLast2Quarters = currentRatioGrowthLast2Quarters;
        return this;
    }

    public Double getCurrentRatioGrowthLastYear() {
        return currentRatioGrowthLastYear;
    }

    public StockDto setCurrentRatioGrowthLastYear(Double currentRatioGrowthLastYear) {
        this.currentRatioGrowthLastYear = currentRatioGrowthLastYear;
        return this;
    }

    public Double getCurrentRatioGrowthLast4Years() {
        return currentRatioGrowthLast4Years;
    }

    public StockDto setCurrentRatioGrowthLast4Years(Double currentRatioGrowthLast4Years) {
        this.currentRatioGrowthLast4Years = currentRatioGrowthLast4Years;
        return this;
    }

    public Long getTotalLiabilitiesLastQuarter() {
        return totalLiabilitiesLastQuarter;
    }

    public StockDto setTotalLiabilitiesLastQuarter(Long totalLiabilitiesLastQuarter) {
        this.totalLiabilitiesLastQuarter = totalLiabilitiesLastQuarter;
        return this;
    }

    public Long getTotalLiabilities2QuartersAgo() {
        return totalLiabilities2QuartersAgo;
    }

    public StockDto setTotalLiabilities2QuartersAgo(Long totalLiabilities2QuartersAgo) {
        this.totalLiabilities2QuartersAgo = totalLiabilities2QuartersAgo;
        return this;
    }

    public Long getTotalLiabilities3QuartersAgo() {
        return totalLiabilities3QuartersAgo;
    }

    public StockDto setTotalLiabilities3QuartersAgo(Long totalLiabilities3QuartersAgo) {
        this.totalLiabilities3QuartersAgo = totalLiabilities3QuartersAgo;
        return this;
    }

    public Long getTotalLiabilitiesLastYear() {
        return totalLiabilitiesLastYear;
    }

    public StockDto setTotalLiabilitiesLastYear(Long totalLiabilitiesLastYear) {
        this.totalLiabilitiesLastYear = totalLiabilitiesLastYear;
        return this;
    }

    public Long getTotalLiabilities2YearsAgo() {
        return totalLiabilities2YearsAgo;
    }

    public StockDto setTotalLiabilities2YearsAgo(Long totalLiabilities2YearsAgo) {
        this.totalLiabilities2YearsAgo = totalLiabilities2YearsAgo;
        return this;
    }

    public Long getTotalLiabilities4YearsAgo() {
        return totalLiabilities4YearsAgo;
    }

    public StockDto setTotalLiabilities4YearsAgo(Long totalLiabilities4YearsAgo) {
        this.totalLiabilities4YearsAgo = totalLiabilities4YearsAgo;
        return this;
    }

    public Double getTotalLiabilitiesGrowthLastQuarter() {
        return totalLiabilitiesGrowthLastQuarter;
    }

    public StockDto setTotalLiabilitiesGrowthLastQuarter(Double totalLiabilitiesGrowthLastQuarter) {
        this.totalLiabilitiesGrowthLastQuarter = totalLiabilitiesGrowthLastQuarter;
        return this;
    }

    public Double getTotalLiabilitiesGrowthLast2Quarters() {
        return totalLiabilitiesGrowthLast2Quarters;
    }

    public StockDto setTotalLiabilitiesGrowthLast2Quarters(Double totalLiabilitiesGrowthLast2Quarters) {
        this.totalLiabilitiesGrowthLast2Quarters = totalLiabilitiesGrowthLast2Quarters;
        return this;
    }

    public Double getTotalLiabilitiesGrowthLastYear() {
        return totalLiabilitiesGrowthLastYear;
    }

    public StockDto setTotalLiabilitiesGrowthLastYear(Double totalLiabilitiesGrowthLastYear) {
        this.totalLiabilitiesGrowthLastYear = totalLiabilitiesGrowthLastYear;
        return this;
    }

    public Double getTotalLiabilitiesGrowthLast4Years() {
        return totalLiabilitiesGrowthLast4Years;
    }

    public StockDto setTotalLiabilitiesGrowthLast4Years(Double totalLiabilitiesGrowthLast4Years) {
        this.totalLiabilitiesGrowthLast4Years = totalLiabilitiesGrowthLast4Years;
        return this;
    }

    public Long getTotalAssetsLastQuarter() {
        return totalAssetsLastQuarter;
    }

    public StockDto setTotalAssetsLastQuarter(Long totalAssetsLastQuarter) {
        this.totalAssetsLastQuarter = totalAssetsLastQuarter;
        return this;
    }

    public Long getTotalAssets2QuartersAgo() {
        return totalAssets2QuartersAgo;
    }

    public StockDto setTotalAssets2QuartersAgo(Long totalAssets2QuartersAgo) {
        this.totalAssets2QuartersAgo = totalAssets2QuartersAgo;
        return this;
    }

    public Long getTotalAssets3QuartersAgo() {
        return totalAssets3QuartersAgo;
    }

    public StockDto setTotalAssets3QuartersAgo(Long totalAssets3QuartersAgo) {
        this.totalAssets3QuartersAgo = totalAssets3QuartersAgo;
        return this;
    }

    public Long getTotalAssetsLastYear() {
        return totalAssetsLastYear;
    }

    public StockDto setTotalAssetsLastYear(Long totalAssetsLastYear) {
        this.totalAssetsLastYear = totalAssetsLastYear;
        return this;
    }

    public Long getTotalAssets2YearsAgo() {
        return totalAssets2YearsAgo;
    }

    public StockDto setTotalAssets2YearsAgo(Long totalAssets2YearsAgo) {
        this.totalAssets2YearsAgo = totalAssets2YearsAgo;
        return this;
    }

    public Long getTotalAssets4YearsAgo() {
        return totalAssets4YearsAgo;
    }

    public StockDto setTotalAssets4YearsAgo(Long totalAssets4YearsAgo) {
        this.totalAssets4YearsAgo = totalAssets4YearsAgo;
        return this;
    }

    public Double getTotalAssetsGrowthLastQuarter() {
        return totalAssetsGrowthLastQuarter;
    }

    public StockDto setTotalAssetsGrowthLastQuarter(Double totalAssetsGrowthLastQuarter) {
        this.totalAssetsGrowthLastQuarter = totalAssetsGrowthLastQuarter;
        return this;
    }

    public Double getTotalAssetsGrowthLast2Quarters() {
        return totalAssetsGrowthLast2Quarters;
    }

    public StockDto setTotalAssetsGrowthLast2Quarters(Double totalAssetsGrowthLast2Quarters) {
        this.totalAssetsGrowthLast2Quarters = totalAssetsGrowthLast2Quarters;
        return this;
    }

    public Double getTotalAssetsGrowthLastYear() {
        return totalAssetsGrowthLastYear;
    }

    public StockDto setTotalAssetsGrowthLastYear(Double totalAssetsGrowthLastYear) {
        this.totalAssetsGrowthLastYear = totalAssetsGrowthLastYear;
        return this;
    }

    public Double getTotalAssetsGrowthLast4Years() {
        return totalAssetsGrowthLast4Years;
    }

    public StockDto setTotalAssetsGrowthLast4Years(Double totalAssetsGrowthLast4Years) {
        this.totalAssetsGrowthLast4Years = totalAssetsGrowthLast4Years;
        return this;
    }

    public Long getTotalShareholdersEquityLastQuarter() {
        return totalShareholdersEquityLastQuarter;
    }

    public StockDto setTotalShareholdersEquityLastQuarter(Long totalShareholdersEquityLastQuarter) {
        this.totalShareholdersEquityLastQuarter = totalShareholdersEquityLastQuarter;
        return this;
    }

    public Long getTotalShareholdersEquity2QuartersAgo() {
        return totalShareholdersEquity2QuartersAgo;
    }

    public StockDto setTotalShareholdersEquity2QuartersAgo(Long totalShareholdersEquity2QuartersAgo) {
        this.totalShareholdersEquity2QuartersAgo = totalShareholdersEquity2QuartersAgo;
        return this;
    }

    public Long getTotalShareholdersEquity3QuartersAgo() {
        return totalShareholdersEquity3QuartersAgo;
    }

    public StockDto setTotalShareholdersEquity3QuartersAgo(Long totalShareholdersEquity3QuartersAgo) {
        this.totalShareholdersEquity3QuartersAgo = totalShareholdersEquity3QuartersAgo;
        return this;
    }

    public Long getTotalShareholdersEquityLastYear() {
        return totalShareholdersEquityLastYear;
    }

    public StockDto setTotalShareholdersEquityLastYear(Long totalShareholdersEquityLastYear) {
        this.totalShareholdersEquityLastYear = totalShareholdersEquityLastYear;
        return this;
    }

    public Long getTotalShareholdersEquity2YearsAgo() {
        return totalShareholdersEquity2YearsAgo;
    }

    public StockDto setTotalShareholdersEquity2YearsAgo(Long totalShareholdersEquity2YearsAgo) {
        this.totalShareholdersEquity2YearsAgo = totalShareholdersEquity2YearsAgo;
        return this;
    }

    public Long getTotalShareholdersEquity4YearsAgo() {
        return totalShareholdersEquity4YearsAgo;
    }

    public StockDto setTotalShareholdersEquity4YearsAgo(Long totalShareholdersEquity4YearsAgo) {
        this.totalShareholdersEquity4YearsAgo = totalShareholdersEquity4YearsAgo;
        return this;
    }

    public Double getTotalShareholdersEquityGrowthLastQuarter() {
        return totalShareholdersEquityGrowthLastQuarter;
    }

    public StockDto setTotalShareholdersEquityGrowthLastQuarter(Double totalShareholdersEquityGrowthLastQuarter) {
        this.totalShareholdersEquityGrowthLastQuarter = totalShareholdersEquityGrowthLastQuarter;
        return this;
    }

    public Double getTotalShareholdersEquityGrowthLast2Quarters() {
        return totalShareholdersEquityGrowthLast2Quarters;
    }

    public StockDto setTotalShareholdersEquityGrowthLast2Quarters(Double totalShareholdersEquityGrowthLast2Quarters) {
        this.totalShareholdersEquityGrowthLast2Quarters = totalShareholdersEquityGrowthLast2Quarters;
        return this;
    }

    public Double getTotalShareholdersEquityGrowthLastYear() {
        return totalShareholdersEquityGrowthLastYear;
    }

    public StockDto setTotalShareholdersEquityGrowthLastYear(Double totalShareholdersEquityGrowthLastYear) {
        this.totalShareholdersEquityGrowthLastYear = totalShareholdersEquityGrowthLastYear;
        return this;
    }

    public Double getTotalShareholdersEquityGrowthLast4Years() {
        return totalShareholdersEquityGrowthLast4Years;
    }

    public StockDto setTotalShareholdersEquityGrowthLast4Years(Double totalShareholdersEquityGrowthLast4Years) {
        this.totalShareholdersEquityGrowthLast4Years = totalShareholdersEquityGrowthLast4Years;
        return this;
    }

    public Double getTotalLiabilitiesToEquityLastQuarter() {
        return totalLiabilitiesToEquityLastQuarter;
    }

    public StockDto setTotalLiabilitiesToEquityLastQuarter(Double totalLiabilitiesToEquityLastQuarter) {
        this.totalLiabilitiesToEquityLastQuarter = totalLiabilitiesToEquityLastQuarter;
        return this;
    }

    public Double getTotalLiabilitiesToEquity2QuartersAgo() {
        return totalLiabilitiesToEquity2QuartersAgo;
    }

    public StockDto setTotalLiabilitiesToEquity2QuartersAgo(Double totalLiabilitiesToEquity2QuartersAgo) {
        this.totalLiabilitiesToEquity2QuartersAgo = totalLiabilitiesToEquity2QuartersAgo;
        return this;
    }

    public Double getTotalLiabilitiesToEquity3QuartersAgo() {
        return totalLiabilitiesToEquity3QuartersAgo;
    }

    public StockDto setTotalLiabilitiesToEquity3QuartersAgo(Double totalLiabilitiesToEquity3QuartersAgo) {
        this.totalLiabilitiesToEquity3QuartersAgo = totalLiabilitiesToEquity3QuartersAgo;
        return this;
    }

    public Double getTotalLiabilitiesToEquityLastYear() {
        return totalLiabilitiesToEquityLastYear;
    }

    public StockDto setTotalLiabilitiesToEquityLastYear(Double totalLiabilitiesToEquityLastYear) {
        this.totalLiabilitiesToEquityLastYear = totalLiabilitiesToEquityLastYear;
        return this;
    }

    public Double getTotalLiabilitiesToEquity2YearsAgo() {
        return totalLiabilitiesToEquity2YearsAgo;
    }

    public StockDto setTotalLiabilitiesToEquity2YearsAgo(Double totalLiabilitiesToEquity2YearsAgo) {
        this.totalLiabilitiesToEquity2YearsAgo = totalLiabilitiesToEquity2YearsAgo;
        return this;
    }

    public Double getTotalLiabilitiesToEquity4YearsAgo() {
        return totalLiabilitiesToEquity4YearsAgo;
    }

    public StockDto setTotalLiabilitiesToEquity4YearsAgo(Double totalLiabilitiesToEquity4YearsAgo) {
        this.totalLiabilitiesToEquity4YearsAgo = totalLiabilitiesToEquity4YearsAgo;
        return this;
    }

    public Double getTotalLiabilitiesToEquityGrowthLastQuarter() {
        return totalLiabilitiesToEquityGrowthLastQuarter;
    }

    public StockDto setTotalLiabilitiesToEquityGrowthLastQuarter(Double totalLiabilitiesToEquityGrowthLastQuarter) {
        this.totalLiabilitiesToEquityGrowthLastQuarter = totalLiabilitiesToEquityGrowthLastQuarter;
        return this;
    }

    public Double getTotalLiabilitiesToEquityGrowthLast2Quarters() {
        return totalLiabilitiesToEquityGrowthLast2Quarters;
    }

    public StockDto setTotalLiabilitiesToEquityGrowthLast2Quarters(Double totalLiabilitiesToEquityGrowthLast2Quarters) {
        this.totalLiabilitiesToEquityGrowthLast2Quarters = totalLiabilitiesToEquityGrowthLast2Quarters;
        return this;
    }

    public Double getTotalLiabilitiesToEquityGrowthLastYear() {
        return totalLiabilitiesToEquityGrowthLastYear;
    }

    public StockDto setTotalLiabilitiesToEquityGrowthLastYear(Double totalLiabilitiesToEquityGrowthLastYear) {
        this.totalLiabilitiesToEquityGrowthLastYear = totalLiabilitiesToEquityGrowthLastYear;
        return this;
    }

    public Double getTotalLiabilitiesToEquityGrowthLast4Years() {
        return totalLiabilitiesToEquityGrowthLast4Years;
    }

    public StockDto setTotalLiabilitiesToEquityGrowthLast4Years(Double totalLiabilitiesToEquityGrowthLast4Years) {
        this.totalLiabilitiesToEquityGrowthLast4Years = totalLiabilitiesToEquityGrowthLast4Years;
        return this;
    }

    public Long getStockRepurchasedLastQuarter() {
        return stockRepurchasedLastQuarter;
    }

    public StockDto setStockRepurchasedLastQuarter(Long stockRepurchasedLastQuarter) {
        this.stockRepurchasedLastQuarter = stockRepurchasedLastQuarter;
        return this;
    }

    public Long getStockRepurchased2QuartersAgo() {
        return stockRepurchased2QuartersAgo;
    }

    public StockDto setStockRepurchased2QuartersAgo(Long stockRepurchased2QuartersAgo) {
        this.stockRepurchased2QuartersAgo = stockRepurchased2QuartersAgo;
        return this;
    }

    public Long getStockRepurchased3QuartersAgo() {
        return stockRepurchased3QuartersAgo;
    }

    public StockDto setStockRepurchased3QuartersAgo(Long stockRepurchased3QuartersAgo) {
        this.stockRepurchased3QuartersAgo = stockRepurchased3QuartersAgo;
        return this;
    }

    public Long getStockRepurchasedLastYear() {
        return stockRepurchasedLastYear;
    }

    public StockDto setStockRepurchasedLastYear(Long stockRepurchasedLastYear) {
        this.stockRepurchasedLastYear = stockRepurchasedLastYear;
        return this;
    }

    public Long getStockRepurchased2YearsAgo() {
        return stockRepurchased2YearsAgo;
    }

    public StockDto setStockRepurchased2YearsAgo(Long stockRepurchased2YearsAgo) {
        this.stockRepurchased2YearsAgo = stockRepurchased2YearsAgo;
        return this;
    }

    public Long getStockRepurchased4YearsAgo() {
        return stockRepurchased4YearsAgo;
    }

    public StockDto setStockRepurchased4YearsAgo(Long stockRepurchased4YearsAgo) {
        this.stockRepurchased4YearsAgo = stockRepurchased4YearsAgo;
        return this;
    }

    public Double getStockRepurchasedGrowthLastQuarter() {
        return stockRepurchasedGrowthLastQuarter;
    }

    public StockDto setStockRepurchasedGrowthLastQuarter(Double stockRepurchasedGrowthLastQuarter) {
        this.stockRepurchasedGrowthLastQuarter = stockRepurchasedGrowthLastQuarter;
        return this;
    }

    public Double getStockRepurchasedGrowthLast2Quarters() {
        return stockRepurchasedGrowthLast2Quarters;
    }

    public StockDto setStockRepurchasedGrowthLast2Quarters(Double stockRepurchasedGrowthLast2Quarters) {
        this.stockRepurchasedGrowthLast2Quarters = stockRepurchasedGrowthLast2Quarters;
        return this;
    }

    public Double getStockRepurchasedGrowthLastYear() {
        return stockRepurchasedGrowthLastYear;
    }

    public StockDto setStockRepurchasedGrowthLastYear(Double stockRepurchasedGrowthLastYear) {
        this.stockRepurchasedGrowthLastYear = stockRepurchasedGrowthLastYear;
        return this;
    }

    public Double getStockRepurchasedGrowthLast4Years() {
        return stockRepurchasedGrowthLast4Years;
    }

    public StockDto setStockRepurchasedGrowthLast4Years(Double stockRepurchasedGrowthLast4Years) {
        this.stockRepurchasedGrowthLast4Years = stockRepurchasedGrowthLast4Years;
        return this;
    }

    public Double getStockLastQuarter() {
        return stockLastQuarter;
    }

    public StockDto setStockLastQuarter(Double stockLastQuarter) {
        this.stockLastQuarter = stockLastQuarter;
        return this;
    }

    public Double getStockLastYear() {
        return stockLastYear;
    }

    public StockDto setStockLastYear(Double stockLastYear) {
        this.stockLastYear = stockLastYear;
        return this;
    }

    public Double getStock2YearsAgo() {
        return stock2YearsAgo;
    }

    public StockDto setStock2YearsAgo(Double stock2YearsAgo) {
        this.stock2YearsAgo = stock2YearsAgo;
        return this;
    }

    public Double getStock4YearsAgo() {
        return stock4YearsAgo;
    }

    public StockDto setStock4YearsAgo(Double stock4YearsAgo) {
        this.stock4YearsAgo = stock4YearsAgo;
        return this;
    }

    public Double getStockGrowthLastQuarter() {
        return stockGrowthLastQuarter;
    }

    public StockDto setStockGrowthLastQuarter(Double stockGrowthLastQuarter) {
        this.stockGrowthLastQuarter = stockGrowthLastQuarter;
        return this;
    }

    public Double getStockGrowthLastYear() {
        return stockGrowthLastYear;
    }

    public StockDto setStockGrowthLastYear(Double stockGrowthLastYear) {
        this.stockGrowthLastYear = stockGrowthLastYear;
        return this;
    }

    public Double getStockGrowthLast4Years() {
        return stockGrowthLast4Years;
    }

    public StockDto setStockGrowthLast4Years(Double stockGrowthLast4Years) {
        this.stockGrowthLast4Years = stockGrowthLast4Years;
        return this;
    }

    public Double getEpsLastQuarter() {
        return epsLastQuarter;
    }

    public StockDto setEpsLastQuarter(Double epsLastQuarter) {
        this.epsLastQuarter = epsLastQuarter;
        return this;
    }

    public Double getEps2QuartersAgo() {
        return eps2QuartersAgo;
    }

    public StockDto setEps2QuartersAgo(Double eps2QuartersAgo) {
        this.eps2QuartersAgo = eps2QuartersAgo;
        return this;
    }

    public Double getEps3QuartersAgo() {
        return eps3QuartersAgo;
    }

    public StockDto setEps3QuartersAgo(Double eps3QuartersAgo) {
        this.eps3QuartersAgo = eps3QuartersAgo;
        return this;
    }

    public Double getEps4QuartersAgo() {
        return eps4QuartersAgo;
    }

    public StockDto setEps4QuartersAgo(Double eps4QuartersAgo) {
        this.eps4QuartersAgo = eps4QuartersAgo;
        return this;
    }

    public Double getEpsLastYear() {
        return epsLastYear;
    }

    public StockDto setEpsLastYear(Double epsLastYear) {
        this.epsLastYear = epsLastYear;
        return this;
    }

    public Double getEps2YearsAgo() {
        return eps2YearsAgo;
    }

    public StockDto setEps2YearsAgo(Double eps2YearsAgo) {
        this.eps2YearsAgo = eps2YearsAgo;
        return this;
    }

    public Double getEps3YearsAgo() {
        return eps3YearsAgo;
    }

    public StockDto setEps3YearsAgo(Double eps3YearsAgo) {
        this.eps3YearsAgo = eps3YearsAgo;
        return this;
    }

    public Double getEps4YearsAgo() {
        return eps4YearsAgo;
    }

    public StockDto setEps4YearsAgo(Double eps4YearsAgo) {
        this.eps4YearsAgo = eps4YearsAgo;
        return this;
    }

    public Double getEpsGrowthLastQuarter() {
        return epsGrowthLastQuarter;
    }

    public StockDto setEpsGrowthLastQuarter(Double epsGrowthLastQuarter) {
        this.epsGrowthLastQuarter = epsGrowthLastQuarter;
        return this;
    }

    public Double getEpsGrowthLast2Quarters() {
        return epsGrowthLast2Quarters;
    }

    public StockDto setEpsGrowthLast2Quarters(Double epsGrowthLast2Quarters) {
        this.epsGrowthLast2Quarters = epsGrowthLast2Quarters;
        return this;
    }

    public Double getEpsGrowthLastYear() {
        return epsGrowthLastYear;
    }

    public StockDto setEpsGrowthLastYear(Double epsGrowthLastYear) {
        this.epsGrowthLastYear = epsGrowthLastYear;
        return this;
    }

    public Double getEpsGrowthLast4Years() {
        return epsGrowthLast4Years;
    }

    public StockDto setEpsGrowthLast4Years(Double epsGrowthLast4Years) {
        this.epsGrowthLast4Years = epsGrowthLast4Years;
        return this;
    }

    public Double getPeLastQuarter() {
        return peLastQuarter;
    }

    public StockDto setPeLastQuarter(Double peLastQuarter) {
        this.peLastQuarter = peLastQuarter;
        return this;
    }

    public Double getPe2QuartersAgo() {
        return pe2QuartersAgo;
    }

    public StockDto setPe2QuartersAgo(Double pe2QuartersAgo) {
        this.pe2QuartersAgo = pe2QuartersAgo;
        return this;
    }

    public Double getPe3QuartersAgo() {
        return pe3QuartersAgo;
    }

    public StockDto setPe3QuartersAgo(Double pe3QuartersAgo) {
        this.pe3QuartersAgo = pe3QuartersAgo;
        return this;
    }

    public Double getPe4QuartersAgo() {
        return pe4QuartersAgo;
    }

    public StockDto setPe4QuartersAgo(Double pe4QuartersAgo) {
        this.pe4QuartersAgo = pe4QuartersAgo;
        return this;
    }

    public Double getPeLastYear() {
        return peLastYear;
    }

    public StockDto setPeLastYear(Double peLastYear) {
        this.peLastYear = peLastYear;
        return this;
    }

    public Double getPe2YearsAgo() {
        return pe2YearsAgo;
    }

    public StockDto setPe2YearsAgo(Double pe2YearsAgo) {
        this.pe2YearsAgo = pe2YearsAgo;
        return this;
    }

    public Double getPe3YearsAgo() {
        return pe3YearsAgo;
    }

    public StockDto setPe3YearsAgo(Double pe3YearsAgo) {
        this.pe3YearsAgo = pe3YearsAgo;
        return this;
    }

    public Double getPe4YearsAgo() {
        return pe4YearsAgo;
    }

    public StockDto setPe4YearsAgo(Double pe4YearsAgo) {
        this.pe4YearsAgo = pe4YearsAgo;
        return this;
    }

    public Double getPeGrowthLastQuarter() {
        return peGrowthLastQuarter;
    }

    public StockDto setPeGrowthLastQuarter(Double peGrowthLastQuarter) {
        this.peGrowthLastQuarter = peGrowthLastQuarter;
        return this;
    }

    public Double getPeGrowthLast2Quarters() {
        return peGrowthLast2Quarters;
    }

    public StockDto setPeGrowthLast2Quarters(Double peGrowthLast2Quarters) {
        this.peGrowthLast2Quarters = peGrowthLast2Quarters;
        return this;
    }

    public Double getPeGrowthLastYear() {
        return peGrowthLastYear;
    }

    public StockDto setPeGrowthLastYear(Double peGrowthLastYear) {
        this.peGrowthLastYear = peGrowthLastYear;
        return this;
    }

    public Double getPeGrowthLast4Years() {
        return peGrowthLast4Years;
    }

    public StockDto setPeGrowthLast4Years(Double peGrowthLast4Years) {
        this.peGrowthLast4Years = peGrowthLast4Years;
        return this;
    }

    public Double getGrowthEstimate5y() {
        return growthEstimate5y;
    }

    public StockDto setGrowthEstimate5y(Double growthEstimate5y) {
        this.growthEstimate5y = growthEstimate5y;
        return this;
    }

    public Double getRoicLastYear() {
        return roicLastYear;
    }

    public StockDto setRoicLastYear(Double roicLastYear) {
        this.roicLastYear = roicLastYear;
        return this;
    }

    public Double getRoicLast2YearsAgo() {
        return roicLast2YearsAgo;
    }

    public StockDto setRoicLast2YearsAgo(Double roicLast2YearsAgo) {
        this.roicLast2YearsAgo = roicLast2YearsAgo;
        return this;
    }

    public Double getRoicLast4YearsAgo() {
        return roicLast4YearsAgo;
    }

    public StockDto setRoicLast4YearsAgo(Double roicLast4YearsAgo) {
        this.roicLast4YearsAgo = roicLast4YearsAgo;
        return this;
    }

    public Double getRoic1Y() {
        return roic1Y;
    }

    public StockDto setRoic1Y(Double roic1Y) {
        this.roic1Y = roic1Y;
        return this;
    }

    public Double getRoic3Y() {
        return roic3Y;
    }

    public StockDto setRoic3Y(Double roic3Y) {
        this.roic3Y = roic3Y;
        return this;
    }

    public Double getRevenue1Y() {
        return revenue1Y;
    }

    public StockDto setRevenue1Y(Double revenue1Y) {
        this.revenue1Y = revenue1Y;
        return this;
    }

    public Double getRevenue3Y() {
        return revenue3Y;
    }

    public StockDto setRevenue3Y(Double revenue3Y) {
        this.revenue3Y = revenue3Y;
        return this;
    }

    public Double getRevenue5Y() {
        return revenue5Y;
    }

    public StockDto setRevenue5Y(Double revenue5Y) {
        this.revenue5Y = revenue5Y;
        return this;
    }

    public Double getRevenue9Y() {
        return revenue9Y;
    }

    public StockDto setRevenue9Y(Double revenue9Y) {
        this.revenue9Y = revenue9Y;
        return this;
    }

    public Double getEps1Y() {
        return eps1Y;
    }

    public StockDto setEps1Y(Double eps1Y) {
        this.eps1Y = eps1Y;
        return this;
    }

    public Double getEps3Y() {
        return eps3Y;
    }

    public StockDto setEps3Y(Double eps3Y) {
        this.eps3Y = eps3Y;
        return this;
    }

    public Double getEps5Y() {
        return eps5Y;
    }

    public StockDto setEps5Y(Double eps5Y) {
        this.eps5Y = eps5Y;
        return this;
    }

    public Double getEps9Y() {
        return eps9Y;
    }

    public StockDto setEps9Y(Double eps9Y) {
        this.eps9Y = eps9Y;
        return this;
    }

    public Double getBps1Y() {
        return bps1Y;
    }

    public StockDto setBps1Y(Double bps1Y) {
        this.bps1Y = bps1Y;
        return this;
    }

    public Double getBps3Y() {
        return bps3Y;
    }

    public StockDto setBps3Y(Double bps3Y) {
        this.bps3Y = bps3Y;
        return this;
    }

    public Double getBps5Y() {
        return bps5Y;
    }

    public StockDto setBps5Y(Double bps5Y) {
        this.bps5Y = bps5Y;
        return this;
    }

    public Double getBps9Y() {
        return bps9Y;
    }

    public StockDto setBps9Y(Double bps9Y) {
        this.bps9Y = bps9Y;
        return this;
    }

    public Double getCash1Y() {
        return cash1Y;
    }

    public StockDto setCash1Y(Double cash1Y) {
        this.cash1Y = cash1Y;
        return this;
    }

    public Double getCash3Y() {
        return cash3Y;
    }

    public StockDto setCash3Y(Double cash3Y) {
        this.cash3Y = cash3Y;
        return this;
    }

    public Double getCash5Y() {
        return cash5Y;
    }

    public StockDto setCash5Y(Double cash5Y) {
        this.cash5Y = cash5Y;
        return this;
    }

    public Double getCash9Y() {
        return cash9Y;
    }

    public StockDto setCash9Y(Double cash9Y) {
        this.cash9Y = cash9Y;
        return this;
    }

    public Double getPe1Y() {
        return pe1Y;
    }

    public StockDto setPe1Y(Double pe1Y) {
        this.pe1Y = pe1Y;
        return this;
    }

    public Double getPe3Y() {
        return pe3Y;
    }

    public StockDto setPe3Y(Double pe3Y) {
        this.pe3Y = pe3Y;
        return this;
    }

    public Double getPe5Y() {
        return pe5Y;
    }

    public StockDto setPe5Y(Double pe5Y) {
        this.pe5Y = pe5Y;
        return this;
    }

    public Double getPe9Y() {
        return pe9Y;
    }

    public StockDto setPe9Y(Double pe9Y) {
        this.pe9Y = pe9Y;
        return this;
    }

    public Double getRule1GrowthRate() {
        return rule1GrowthRate;
    }

    public StockDto setRule1GrowthRate(Double rule1GrowthRate) {
        this.rule1GrowthRate = rule1GrowthRate;
        return this;
    }

    public Double getDefaultPE() {
        return defaultPE;
    }

    public StockDto setDefaultPE(Double defaultPE) {
        this.defaultPE = defaultPE;
        return this;
    }

    public Double getHistoricalPE() {
        return historicalPE;
    }

    public StockDto setHistoricalPE(Double historicalPE) {
        this.historicalPE = historicalPE;
        return this;
    }

    public Double getRule1PE() {
        return rule1PE;
    }

    public StockDto setRule1PE(Double rule1PE) {
        this.rule1PE = rule1PE;
        return this;
    }

    public Double getCurrentEps() {
        return currentEps;
    }

    public StockDto setCurrentEps(Double currentEps) {
        this.currentEps = currentEps;
        return this;
    }

    public Double getFutureEPS10Years() {
        return futureEPS10Years;
    }

    public StockDto setFutureEPS10Years(Double futureEPS10Years) {
        this.futureEPS10Years = futureEPS10Years;
        return this;
    }

    public Double getFuturePrice10Years() {
        return futurePrice10Years;
    }

    public StockDto setFuturePrice10Years(Double futurePrice10Years) {
        this.futurePrice10Years = futurePrice10Years;
        return this;
    }

    public Double getStickerPrice15pcGrowth() {
        return stickerPrice15pcGrowth;
    }

    public StockDto setStickerPrice15pcGrowth(Double stickerPrice15pcGrowth) {
        this.stickerPrice15pcGrowth = stickerPrice15pcGrowth;
        return this;
    }

    public Double getStickerPrice10pcGrowth() {
        return stickerPrice10pcGrowth;
    }

    public StockDto setStickerPrice10pcGrowth(Double stickerPrice10pcGrowth) {
        this.stickerPrice10pcGrowth = stickerPrice10pcGrowth;
        return this;
    }

    public Double getStickerPrice5pcGrowth() {
        return stickerPrice5pcGrowth;
    }

    public StockDto setStickerPrice5pcGrowth(Double stickerPrice5pcGrowth) {
        this.stickerPrice5pcGrowth = stickerPrice5pcGrowth;
        return this;
    }

    public Double getBelowStickerPrice15pc() {
        return belowStickerPrice15pc;
    }

    public StockDto setBelowStickerPrice15pc(Double belowStickerPrice15pc) {
        this.belowStickerPrice15pc = belowStickerPrice15pc;
        return this;
    }

    public Double getBelowStickerPrice10pc() {
        return belowStickerPrice10pc;
    }

    public StockDto setBelowStickerPrice10pc(Double belowStickerPrice10pc) {
        this.belowStickerPrice10pc = belowStickerPrice10pc;
        return this;
    }

    public Double getBelowStickerPrice5pc() {
        return belowStickerPrice5pc;
    }

    public StockDto setBelowStickerPrice5pc(Double belowStickerPrice5pc) {
        this.belowStickerPrice5pc = belowStickerPrice5pc;
        return this;
    }
}