
package com.github.dusanzahoransky.stockanalyst.model.yahoo.balancesheet;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "annualHoldingsTurnover",
    "enterpriseToRevenue",
    "beta3Year",
    "profitMargins",
    "enterpriseToEbitda",
    "52WeekChange",
    "morningStarRiskRating",
    "forwardEps",
    "revenueQuarterlyGrowth",
    "fundInceptionDate",
    "annualReportExpenseRatio",
    "totalAssets",
    "bookValue",
    "fundFamily",
    "lastFiscalYearEnd",
    "netIncomeToCommon",
    "trailingEps",
    "lastDividendValue",
    "SandP52WeekChange",
    "priceToBook",
    "nextFiscalYearEnd",
    "yield",
    "mostRecentQuarter",
    "enterpriseValue",
    "priceHint",
    "threeYearAverageReturn",
    "lastSplitDate",
    "lastSplitFactor",
    "legalType",
    "morningStarOverallRating",
    "earningsQuarterlyGrowth",
    "priceToSalesTrailing12Months",
    "pegRatio",
    "ytdReturn",
    "forwardPE",
    "maxAge",
    "lastCapGain",
    "category",
    "fiveYearAverageReturn"
})
public class DefaultKeyStatistics {

    @JsonProperty("annualHoldingsTurnover")
    private AnnualHoldingsTurnover annualHoldingsTurnover;
    @JsonProperty("enterpriseToRevenue")
    private EnterpriseToRevenue enterpriseToRevenue;
    @JsonProperty("beta3Year")
    private Beta3Year beta3Year;
    @JsonProperty("profitMargins")
    private ProfitMargins profitMargins;
    @JsonProperty("enterpriseToEbitda")
    private EnterpriseToEbitda enterpriseToEbitda;
    @JsonProperty("52WeekChange")
    private _52WeekChange _52WeekChange;
    @JsonProperty("morningStarRiskRating")
    private MorningStarRiskRating morningStarRiskRating;
    @JsonProperty("forwardEps")
    private ForwardEps forwardEps;
    @JsonProperty("revenueQuarterlyGrowth")
    private RevenueQuarterlyGrowth revenueQuarterlyGrowth;
    @JsonProperty("fundInceptionDate")
    private FundInceptionDate fundInceptionDate;
    @JsonProperty("annualReportExpenseRatio")
    private AnnualReportExpenseRatio annualReportExpenseRatio;
    @JsonProperty("totalAssets")
    private TotalAssets totalAssets;
    @JsonProperty("bookValue")
    private BookValue bookValue;
    @JsonProperty("fundFamily")
    private String fundFamily;
    @JsonProperty("lastFiscalYearEnd")
    private LastFiscalYearEnd lastFiscalYearEnd;
    @JsonProperty("netIncomeToCommon")
    private NetIncomeToCommon netIncomeToCommon;
    @JsonProperty("trailingEps")
    private TrailingEps trailingEps;
    @JsonProperty("lastDividendValue")
    private LastDividendValue lastDividendValue;
    @JsonProperty("SandP52WeekChange")
    private SandP52WeekChange sandP52WeekChange;
    @JsonProperty("priceToBook")
    private PriceToBook priceToBook;
    @JsonProperty("nextFiscalYearEnd")
    private NextFiscalYearEnd nextFiscalYearEnd;
    @JsonProperty("yield")
    private Yield yield;
    @JsonProperty("mostRecentQuarter")
    private MostRecentQuarter mostRecentQuarter;
    @JsonProperty("enterpriseValue")
    private EnterpriseValue enterpriseValue;
    @JsonProperty("priceHint")
    private PriceHint priceHint;
    @JsonProperty("threeYearAverageReturn")
    private ThreeYearAverageReturn threeYearAverageReturn;
    @JsonProperty("lastSplitDate")
    private LastSplitDate lastSplitDate;
    @JsonProperty("lastSplitFactor")
    private Object lastSplitFactor;
    @JsonProperty("legalType")
    private String legalType;
    @JsonProperty("morningStarOverallRating")
    private MorningStarOverallRating morningStarOverallRating;
    @JsonProperty("earningsQuarterlyGrowth")
    private EarningsQuarterlyGrowth earningsQuarterlyGrowth;
    @JsonProperty("priceToSalesTrailing12Months")
    private PriceToSalesTrailing12Months priceToSalesTrailing12Months;
    @JsonProperty("pegRatio")
    private PegRatio pegRatio;
    @JsonProperty("ytdReturn")
    private YtdReturn ytdReturn;
    @JsonProperty("forwardPE")
    private ForwardPE forwardPE;
    @JsonProperty("maxAge")
    private Integer maxAge;
    @JsonProperty("lastCapGain")
    private LastCapGain lastCapGain;
    @JsonProperty("category")
    private Object category;
    @JsonProperty("fiveYearAverageReturn")
    private FiveYearAverageReturn fiveYearAverageReturn;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("annualHoldingsTurnover")
    public AnnualHoldingsTurnover getAnnualHoldingsTurnover() {
        return annualHoldingsTurnover;
    }

    @JsonProperty("annualHoldingsTurnover")
    public void setAnnualHoldingsTurnover(AnnualHoldingsTurnover annualHoldingsTurnover) {
        this.annualHoldingsTurnover = annualHoldingsTurnover;
    }

    @JsonProperty("enterpriseToRevenue")
    public EnterpriseToRevenue getEnterpriseToRevenue() {
        return enterpriseToRevenue;
    }

    @JsonProperty("enterpriseToRevenue")
    public void setEnterpriseToRevenue(EnterpriseToRevenue enterpriseToRevenue) {
        this.enterpriseToRevenue = enterpriseToRevenue;
    }

    @JsonProperty("beta3Year")
    public Beta3Year getBeta3Year() {
        return beta3Year;
    }

    @JsonProperty("beta3Year")
    public void setBeta3Year(Beta3Year beta3Year) {
        this.beta3Year = beta3Year;
    }

    @JsonProperty("profitMargins")
    public ProfitMargins getProfitMargins() {
        return profitMargins;
    }

    @JsonProperty("profitMargins")
    public void setProfitMargins(ProfitMargins profitMargins) {
        this.profitMargins = profitMargins;
    }

    @JsonProperty("enterpriseToEbitda")
    public EnterpriseToEbitda getEnterpriseToEbitda() {
        return enterpriseToEbitda;
    }

    @JsonProperty("enterpriseToEbitda")
    public void setEnterpriseToEbitda(EnterpriseToEbitda enterpriseToEbitda) {
        this.enterpriseToEbitda = enterpriseToEbitda;
    }

    @JsonProperty("52WeekChange")
    public _52WeekChange get52WeekChange() {
        return _52WeekChange;
    }

    @JsonProperty("52WeekChange")
    public void set52WeekChange(_52WeekChange _52WeekChange) {
        this._52WeekChange = _52WeekChange;
    }

    @JsonProperty("morningStarRiskRating")
    public MorningStarRiskRating getMorningStarRiskRating() {
        return morningStarRiskRating;
    }

    @JsonProperty("morningStarRiskRating")
    public void setMorningStarRiskRating(MorningStarRiskRating morningStarRiskRating) {
        this.morningStarRiskRating = morningStarRiskRating;
    }

    @JsonProperty("forwardEps")
    public ForwardEps getForwardEps() {
        return forwardEps;
    }

    @JsonProperty("forwardEps")
    public void setForwardEps(ForwardEps forwardEps) {
        this.forwardEps = forwardEps;
    }

    @JsonProperty("revenueQuarterlyGrowth")
    public RevenueQuarterlyGrowth getRevenueQuarterlyGrowth() {
        return revenueQuarterlyGrowth;
    }

    @JsonProperty("revenueQuarterlyGrowth")
    public void setRevenueQuarterlyGrowth(RevenueQuarterlyGrowth revenueQuarterlyGrowth) {
        this.revenueQuarterlyGrowth = revenueQuarterlyGrowth;
    }

    @JsonProperty("fundInceptionDate")
    public FundInceptionDate getFundInceptionDate() {
        return fundInceptionDate;
    }

    @JsonProperty("fundInceptionDate")
    public void setFundInceptionDate(FundInceptionDate fundInceptionDate) {
        this.fundInceptionDate = fundInceptionDate;
    }

    @JsonProperty("annualReportExpenseRatio")
    public AnnualReportExpenseRatio getAnnualReportExpenseRatio() {
        return annualReportExpenseRatio;
    }

    @JsonProperty("annualReportExpenseRatio")
    public void setAnnualReportExpenseRatio(AnnualReportExpenseRatio annualReportExpenseRatio) {
        this.annualReportExpenseRatio = annualReportExpenseRatio;
    }

    @JsonProperty("totalAssets")
    public TotalAssets getTotalAssets() {
        return totalAssets;
    }

    @JsonProperty("totalAssets")
    public void setTotalAssets(TotalAssets totalAssets) {
        this.totalAssets = totalAssets;
    }

    @JsonProperty("bookValue")
    public BookValue getBookValue() {
        return bookValue;
    }

    @JsonProperty("bookValue")
    public void setBookValue(BookValue bookValue) {
        this.bookValue = bookValue;
    }

    @JsonProperty("fundFamily")
    public String getFundFamily() {
        return fundFamily;
    }

    @JsonProperty("fundFamily")
    public void setFundFamily(String fundFamily) {
        this.fundFamily = fundFamily;
    }

    @JsonProperty("lastFiscalYearEnd")
    public LastFiscalYearEnd getLastFiscalYearEnd() {
        return lastFiscalYearEnd;
    }

    @JsonProperty("lastFiscalYearEnd")
    public void setLastFiscalYearEnd(LastFiscalYearEnd lastFiscalYearEnd) {
        this.lastFiscalYearEnd = lastFiscalYearEnd;
    }

    @JsonProperty("netIncomeToCommon")
    public NetIncomeToCommon getNetIncomeToCommon() {
        return netIncomeToCommon;
    }

    @JsonProperty("netIncomeToCommon")
    public void setNetIncomeToCommon(NetIncomeToCommon netIncomeToCommon) {
        this.netIncomeToCommon = netIncomeToCommon;
    }

    @JsonProperty("trailingEps")
    public TrailingEps getTrailingEps() {
        return trailingEps;
    }

    @JsonProperty("trailingEps")
    public void setTrailingEps(TrailingEps trailingEps) {
        this.trailingEps = trailingEps;
    }

    @JsonProperty("lastDividendValue")
    public LastDividendValue getLastDividendValue() {
        return lastDividendValue;
    }

    @JsonProperty("lastDividendValue")
    public void setLastDividendValue(LastDividendValue lastDividendValue) {
        this.lastDividendValue = lastDividendValue;
    }

    @JsonProperty("SandP52WeekChange")
    public SandP52WeekChange getSandP52WeekChange() {
        return sandP52WeekChange;
    }

    @JsonProperty("SandP52WeekChange")
    public void setSandP52WeekChange(SandP52WeekChange sandP52WeekChange) {
        this.sandP52WeekChange = sandP52WeekChange;
    }

    @JsonProperty("priceToBook")
    public PriceToBook getPriceToBook() {
        return priceToBook;
    }

    @JsonProperty("priceToBook")
    public void setPriceToBook(PriceToBook priceToBook) {
        this.priceToBook = priceToBook;
    }

    @JsonProperty("nextFiscalYearEnd")
    public NextFiscalYearEnd getNextFiscalYearEnd() {
        return nextFiscalYearEnd;
    }

    @JsonProperty("nextFiscalYearEnd")
    public void setNextFiscalYearEnd(NextFiscalYearEnd nextFiscalYearEnd) {
        this.nextFiscalYearEnd = nextFiscalYearEnd;
    }

    @JsonProperty("yield")
    public Yield getYield() {
        return yield;
    }

    @JsonProperty("yield")
    public void setYield(Yield yield) {
        this.yield = yield;
    }

    @JsonProperty("mostRecentQuarter")
    public MostRecentQuarter getMostRecentQuarter() {
        return mostRecentQuarter;
    }

    @JsonProperty("mostRecentQuarter")
    public void setMostRecentQuarter(MostRecentQuarter mostRecentQuarter) {
        this.mostRecentQuarter = mostRecentQuarter;
    }

    @JsonProperty("enterpriseValue")
    public EnterpriseValue getEnterpriseValue() {
        return enterpriseValue;
    }

    @JsonProperty("enterpriseValue")
    public void setEnterpriseValue(EnterpriseValue enterpriseValue) {
        this.enterpriseValue = enterpriseValue;
    }

    @JsonProperty("priceHint")
    public PriceHint getPriceHint() {
        return priceHint;
    }

    @JsonProperty("priceHint")
    public void setPriceHint(PriceHint priceHint) {
        this.priceHint = priceHint;
    }

    @JsonProperty("threeYearAverageReturn")
    public ThreeYearAverageReturn getThreeYearAverageReturn() {
        return threeYearAverageReturn;
    }

    @JsonProperty("threeYearAverageReturn")
    public void setThreeYearAverageReturn(ThreeYearAverageReturn threeYearAverageReturn) {
        this.threeYearAverageReturn = threeYearAverageReturn;
    }

    @JsonProperty("lastSplitDate")
    public LastSplitDate getLastSplitDate() {
        return lastSplitDate;
    }

    @JsonProperty("lastSplitDate")
    public void setLastSplitDate(LastSplitDate lastSplitDate) {
        this.lastSplitDate = lastSplitDate;
    }

    @JsonProperty("lastSplitFactor")
    public Object getLastSplitFactor() {
        return lastSplitFactor;
    }

    @JsonProperty("lastSplitFactor")
    public void setLastSplitFactor(Object lastSplitFactor) {
        this.lastSplitFactor = lastSplitFactor;
    }

    @JsonProperty("legalType")
    public String getLegalType() {
        return legalType;
    }

    @JsonProperty("legalType")
    public void setLegalType(String legalType) {
        this.legalType = legalType;
    }

    @JsonProperty("morningStarOverallRating")
    public MorningStarOverallRating getMorningStarOverallRating() {
        return morningStarOverallRating;
    }

    @JsonProperty("morningStarOverallRating")
    public void setMorningStarOverallRating(MorningStarOverallRating morningStarOverallRating) {
        this.morningStarOverallRating = morningStarOverallRating;
    }

    @JsonProperty("earningsQuarterlyGrowth")
    public EarningsQuarterlyGrowth getEarningsQuarterlyGrowth() {
        return earningsQuarterlyGrowth;
    }

    @JsonProperty("earningsQuarterlyGrowth")
    public void setEarningsQuarterlyGrowth(EarningsQuarterlyGrowth earningsQuarterlyGrowth) {
        this.earningsQuarterlyGrowth = earningsQuarterlyGrowth;
    }

    @JsonProperty("priceToSalesTrailing12Months")
    public PriceToSalesTrailing12Months getPriceToSalesTrailing12Months() {
        return priceToSalesTrailing12Months;
    }

    @JsonProperty("priceToSalesTrailing12Months")
    public void setPriceToSalesTrailing12Months(PriceToSalesTrailing12Months priceToSalesTrailing12Months) {
        this.priceToSalesTrailing12Months = priceToSalesTrailing12Months;
    }

    @JsonProperty("pegRatio")
    public PegRatio getPegRatio() {
        return pegRatio;
    }

    @JsonProperty("pegRatio")
    public void setPegRatio(PegRatio pegRatio) {
        this.pegRatio = pegRatio;
    }

    @JsonProperty("ytdReturn")
    public YtdReturn getYtdReturn() {
        return ytdReturn;
    }

    @JsonProperty("ytdReturn")
    public void setYtdReturn(YtdReturn ytdReturn) {
        this.ytdReturn = ytdReturn;
    }

    @JsonProperty("forwardPE")
    public ForwardPE getForwardPE() {
        return forwardPE;
    }

    @JsonProperty("forwardPE")
    public void setForwardPE(ForwardPE forwardPE) {
        this.forwardPE = forwardPE;
    }

    @JsonProperty("maxAge")
    public Integer getMaxAge() {
        return maxAge;
    }

    @JsonProperty("maxAge")
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    @JsonProperty("lastCapGain")
    public LastCapGain getLastCapGain() {
        return lastCapGain;
    }

    @JsonProperty("lastCapGain")
    public void setLastCapGain(LastCapGain lastCapGain) {
        this.lastCapGain = lastCapGain;
    }

    @JsonProperty("category")
    public Object getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Object category) {
        this.category = category;
    }

    @JsonProperty("fiveYearAverageReturn")
    public FiveYearAverageReturn getFiveYearAverageReturn() {
        return fiveYearAverageReturn;
    }

    @JsonProperty("fiveYearAverageReturn")
    public void setFiveYearAverageReturn(FiveYearAverageReturn fiveYearAverageReturn) {
        this.fiveYearAverageReturn = fiveYearAverageReturn;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
