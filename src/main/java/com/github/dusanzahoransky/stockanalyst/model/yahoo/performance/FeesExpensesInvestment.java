
package com.github.dusanzahoransky.stockanalyst.model.yahoo.performance;

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
    "frontEndSalesLoad",
    "annualReportExpenseRatio",
    "netExpRatio",
    "projectionValues",
    "grossExpRatio",
    "deferredSalesLoad",
    "totalNetAssets",
    "twelveBOne"
})
public class FeesExpensesInvestment {

    @JsonProperty("annualHoldingsTurnover")
    private AnnualHoldingsTurnover annualHoldingsTurnover;
    @JsonProperty("frontEndSalesLoad")
    private FrontEndSalesLoad frontEndSalesLoad;
    @JsonProperty("annualReportExpenseRatio")
    private AnnualReportExpenseRatio annualReportExpenseRatio;
    @JsonProperty("netExpRatio")
    private NetExpRatio netExpRatio;
    @JsonProperty("projectionValues")
    private ProjectionValues projectionValues;
    @JsonProperty("grossExpRatio")
    private GrossExpRatio grossExpRatio;
    @JsonProperty("deferredSalesLoad")
    private DeferredSalesLoad deferredSalesLoad;
    @JsonProperty("totalNetAssets")
    private TotalNetAssets totalNetAssets;
    @JsonProperty("twelveBOne")
    private TwelveBOne twelveBOne;
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

    @JsonProperty("frontEndSalesLoad")
    public FrontEndSalesLoad getFrontEndSalesLoad() {
        return frontEndSalesLoad;
    }

    @JsonProperty("frontEndSalesLoad")
    public void setFrontEndSalesLoad(FrontEndSalesLoad frontEndSalesLoad) {
        this.frontEndSalesLoad = frontEndSalesLoad;
    }

    @JsonProperty("annualReportExpenseRatio")
    public AnnualReportExpenseRatio getAnnualReportExpenseRatio() {
        return annualReportExpenseRatio;
    }

    @JsonProperty("annualReportExpenseRatio")
    public void setAnnualReportExpenseRatio(AnnualReportExpenseRatio annualReportExpenseRatio) {
        this.annualReportExpenseRatio = annualReportExpenseRatio;
    }

    @JsonProperty("netExpRatio")
    public NetExpRatio getNetExpRatio() {
        return netExpRatio;
    }

    @JsonProperty("netExpRatio")
    public void setNetExpRatio(NetExpRatio netExpRatio) {
        this.netExpRatio = netExpRatio;
    }

    @JsonProperty("projectionValues")
    public ProjectionValues getProjectionValues() {
        return projectionValues;
    }

    @JsonProperty("projectionValues")
    public void setProjectionValues(ProjectionValues projectionValues) {
        this.projectionValues = projectionValues;
    }

    @JsonProperty("grossExpRatio")
    public GrossExpRatio getGrossExpRatio() {
        return grossExpRatio;
    }

    @JsonProperty("grossExpRatio")
    public void setGrossExpRatio(GrossExpRatio grossExpRatio) {
        this.grossExpRatio = grossExpRatio;
    }

    @JsonProperty("deferredSalesLoad")
    public DeferredSalesLoad getDeferredSalesLoad() {
        return deferredSalesLoad;
    }

    @JsonProperty("deferredSalesLoad")
    public void setDeferredSalesLoad(DeferredSalesLoad deferredSalesLoad) {
        this.deferredSalesLoad = deferredSalesLoad;
    }

    @JsonProperty("totalNetAssets")
    public TotalNetAssets getTotalNetAssets() {
        return totalNetAssets;
    }

    @JsonProperty("totalNetAssets")
    public void setTotalNetAssets(TotalNetAssets totalNetAssets) {
        this.totalNetAssets = totalNetAssets;
    }

    @JsonProperty("twelveBOne")
    public TwelveBOne getTwelveBOne() {
        return twelveBOne;
    }

    @JsonProperty("twelveBOne")
    public void setTwelveBOne(TwelveBOne twelveBOne) {
        this.twelveBOne = twelveBOne;
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
