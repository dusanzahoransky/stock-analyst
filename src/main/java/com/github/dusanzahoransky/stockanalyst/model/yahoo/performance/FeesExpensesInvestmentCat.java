
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
    "annualReportExpenseRatio",
    "frontEndSalesLoad",
    "deferredSalesLoad",
    "twelveBOne",
    "annualHoldingsTurnover",
    "totalNetAssets",
    "projectionValuesCat"
})
public class FeesExpensesInvestmentCat {

    @JsonProperty("annualReportExpenseRatio")
    private AnnualReportExpenseRatio annualReportExpenseRatio;
    @JsonProperty("frontEndSalesLoad")
    private FrontEndSalesLoad frontEndSalesLoad;
    @JsonProperty("deferredSalesLoad")
    private DeferredSalesLoad deferredSalesLoad;
    @JsonProperty("twelveBOne")
    private TwelveBOne twelveBOne;
    @JsonProperty("annualHoldingsTurnover")
    private AnnualHoldingsTurnover annualHoldingsTurnover;
    @JsonProperty("totalNetAssets")
    private TotalNetAssets totalNetAssets;
    @JsonProperty("projectionValuesCat")
    private ProjectionValuesCat projectionValuesCat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("annualReportExpenseRatio")
    public AnnualReportExpenseRatio getAnnualReportExpenseRatio() {
        return annualReportExpenseRatio;
    }

    @JsonProperty("annualReportExpenseRatio")
    public void setAnnualReportExpenseRatio(AnnualReportExpenseRatio annualReportExpenseRatio) {
        this.annualReportExpenseRatio = annualReportExpenseRatio;
    }

    @JsonProperty("frontEndSalesLoad")
    public FrontEndSalesLoad getFrontEndSalesLoad() {
        return frontEndSalesLoad;
    }

    @JsonProperty("frontEndSalesLoad")
    public void setFrontEndSalesLoad(FrontEndSalesLoad frontEndSalesLoad) {
        this.frontEndSalesLoad = frontEndSalesLoad;
    }

    @JsonProperty("deferredSalesLoad")
    public DeferredSalesLoad getDeferredSalesLoad() {
        return deferredSalesLoad;
    }

    @JsonProperty("deferredSalesLoad")
    public void setDeferredSalesLoad(DeferredSalesLoad deferredSalesLoad) {
        this.deferredSalesLoad = deferredSalesLoad;
    }

    @JsonProperty("twelveBOne")
    public TwelveBOne getTwelveBOne() {
        return twelveBOne;
    }

    @JsonProperty("twelveBOne")
    public void setTwelveBOne(TwelveBOne twelveBOne) {
        this.twelveBOne = twelveBOne;
    }

    @JsonProperty("annualHoldingsTurnover")
    public AnnualHoldingsTurnover getAnnualHoldingsTurnover() {
        return annualHoldingsTurnover;
    }

    @JsonProperty("annualHoldingsTurnover")
    public void setAnnualHoldingsTurnover(AnnualHoldingsTurnover annualHoldingsTurnover) {
        this.annualHoldingsTurnover = annualHoldingsTurnover;
    }

    @JsonProperty("totalNetAssets")
    public TotalNetAssets getTotalNetAssets() {
        return totalNetAssets;
    }

    @JsonProperty("totalNetAssets")
    public void setTotalNetAssets(TotalNetAssets totalNetAssets) {
        this.totalNetAssets = totalNetAssets;
    }

    @JsonProperty("projectionValuesCat")
    public ProjectionValuesCat getProjectionValuesCat() {
        return projectionValuesCat;
    }

    @JsonProperty("projectionValuesCat")
    public void setProjectionValuesCat(ProjectionValuesCat projectionValuesCat) {
        this.projectionValuesCat = projectionValuesCat;
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
