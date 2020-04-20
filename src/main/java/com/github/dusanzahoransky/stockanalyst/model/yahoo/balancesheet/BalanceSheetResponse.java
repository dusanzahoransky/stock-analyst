
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
    "defaultKeyStatistics",
    "fundProfile",
    "financialsTemplate",
    "topHoldings",
    "price",
    "fundPerformance",
    "quoteType",
    "summaryDetail",
    "symbol",
    "assetProfile",
    "esgScores",
    "pageViews"
})
public class BalanceSheetResponse {

    @JsonProperty("defaultKeyStatistics")
    private DefaultKeyStatistics defaultKeyStatistics;
    @JsonProperty("fundProfile")
    private FundProfile fundProfile;
    @JsonProperty("financialsTemplate")
    private FinancialsTemplate financialsTemplate;
    @JsonProperty("topHoldings")
    private TopHoldings topHoldings;
    @JsonProperty("price")
    private Price price;
    @JsonProperty("fundPerformance")
    private FundPerformance fundPerformance;
    @JsonProperty("quoteType")
    private QuoteType quoteType;
    @JsonProperty("summaryDetail")
    private SummaryDetail summaryDetail;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("assetProfile")
    private AssetProfile assetProfile;
    @JsonProperty("esgScores")
    private EsgScores esgScores;
    @JsonProperty("pageViews")
    private PageViews pageViews;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("defaultKeyStatistics")
    public DefaultKeyStatistics getDefaultKeyStatistics() {
        return defaultKeyStatistics;
    }

    @JsonProperty("defaultKeyStatistics")
    public void setDefaultKeyStatistics(DefaultKeyStatistics defaultKeyStatistics) {
        this.defaultKeyStatistics = defaultKeyStatistics;
    }

    @JsonProperty("fundProfile")
    public FundProfile getFundProfile() {
        return fundProfile;
    }

    @JsonProperty("fundProfile")
    public void setFundProfile(FundProfile fundProfile) {
        this.fundProfile = fundProfile;
    }

    @JsonProperty("financialsTemplate")
    public FinancialsTemplate getFinancialsTemplate() {
        return financialsTemplate;
    }

    @JsonProperty("financialsTemplate")
    public void setFinancialsTemplate(FinancialsTemplate financialsTemplate) {
        this.financialsTemplate = financialsTemplate;
    }

    @JsonProperty("topHoldings")
    public TopHoldings getTopHoldings() {
        return topHoldings;
    }

    @JsonProperty("topHoldings")
    public void setTopHoldings(TopHoldings topHoldings) {
        this.topHoldings = topHoldings;
    }

    @JsonProperty("price")
    public Price getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Price price) {
        this.price = price;
    }

    @JsonProperty("fundPerformance")
    public FundPerformance getFundPerformance() {
        return fundPerformance;
    }

    @JsonProperty("fundPerformance")
    public void setFundPerformance(FundPerformance fundPerformance) {
        this.fundPerformance = fundPerformance;
    }

    @JsonProperty("quoteType")
    public QuoteType getQuoteType() {
        return quoteType;
    }

    @JsonProperty("quoteType")
    public void setQuoteType(QuoteType quoteType) {
        this.quoteType = quoteType;
    }

    @JsonProperty("summaryDetail")
    public SummaryDetail getSummaryDetail() {
        return summaryDetail;
    }

    @JsonProperty("summaryDetail")
    public void setSummaryDetail(SummaryDetail summaryDetail) {
        this.summaryDetail = summaryDetail;
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("assetProfile")
    public AssetProfile getAssetProfile() {
        return assetProfile;
    }

    @JsonProperty("assetProfile")
    public void setAssetProfile(AssetProfile assetProfile) {
        this.assetProfile = assetProfile;
    }

    @JsonProperty("esgScores")
    public EsgScores getEsgScores() {
        return esgScores;
    }

    @JsonProperty("esgScores")
    public void setEsgScores(EsgScores esgScores) {
        this.esgScores = esgScores;
    }

    @JsonProperty("pageViews")
    public PageViews getPageViews() {
        return pageViews;
    }

    @JsonProperty("pageViews")
    public void setPageViews(PageViews pageViews) {
        this.pageViews = pageViews;
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
