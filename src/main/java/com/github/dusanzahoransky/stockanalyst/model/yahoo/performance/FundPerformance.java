package com.github.dusanzahoransky.stockanalyst.model.yahoo.performance;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder( {
    "trailingReturns",
    "performanceOverview",
    "riskOverviewStatistics",
    "riskOverviewStatisticsCat",
    "performanceOverviewCat",
    "trailingReturnsCat",
    "maxAge",
    "pastQuarterlyReturns",
    "trailingReturnsNav",
    "annualTotalReturns"
})
public class FundPerformance {

    @JsonProperty("trailingReturns")
    private TrailingReturns trailingReturns;
    @JsonProperty("performanceOverview")
    private PerformanceOverview performanceOverview;
    @JsonProperty("riskOverviewStatistics")
    private RiskOverviewStatistics riskOverviewStatistics;
    @JsonProperty("riskOverviewStatisticsCat")
    private RiskOverviewStatisticsCat riskOverviewStatisticsCat;
    @JsonProperty("performanceOverviewCat")
    private PerformanceOverviewCat performanceOverviewCat;
    @JsonProperty("trailingReturnsCat")
    private TrailingReturnsCat trailingReturnsCat;
    @JsonProperty("maxAge")
    private Integer maxAge;
    @JsonProperty("pastQuarterlyReturns")
    private PastQuarterlyReturns pastQuarterlyReturns;
    @JsonProperty("trailingReturnsNav")
    private TrailingReturnsNav trailingReturnsNav;
    @JsonProperty("annualTotalReturns")
    private AnnualTotalReturns annualTotalReturns;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("trailingReturns")
    public TrailingReturns getTrailingReturns() {
        return trailingReturns;
    }

    @JsonProperty("trailingReturns")
    public void setTrailingReturns(TrailingReturns trailingReturns) {
        this.trailingReturns = trailingReturns;
    }

    @JsonProperty("performanceOverview")
    public PerformanceOverview getPerformanceOverview() {
        return performanceOverview;
    }

    @JsonProperty("performanceOverview")
    public void setPerformanceOverview(PerformanceOverview performanceOverview) {
        this.performanceOverview = performanceOverview;
    }

    @JsonProperty("riskOverviewStatistics")
    public RiskOverviewStatistics getRiskOverviewStatistics() {
        return riskOverviewStatistics;
    }

    @JsonProperty("riskOverviewStatistics")
    public void setRiskOverviewStatistics(RiskOverviewStatistics riskOverviewStatistics) {
        this.riskOverviewStatistics = riskOverviewStatistics;
    }

    @JsonProperty("riskOverviewStatisticsCat")
    public RiskOverviewStatisticsCat getRiskOverviewStatisticsCat() {
        return riskOverviewStatisticsCat;
    }

    @JsonProperty("riskOverviewStatisticsCat")
    public void setRiskOverviewStatisticsCat(RiskOverviewStatisticsCat riskOverviewStatisticsCat) {
        this.riskOverviewStatisticsCat = riskOverviewStatisticsCat;
    }

    @JsonProperty("performanceOverviewCat")
    public PerformanceOverviewCat getPerformanceOverviewCat() {
        return performanceOverviewCat;
    }

    @JsonProperty("performanceOverviewCat")
    public void setPerformanceOverviewCat(PerformanceOverviewCat performanceOverviewCat) {
        this.performanceOverviewCat = performanceOverviewCat;
    }

    @JsonProperty("trailingReturnsCat")
    public TrailingReturnsCat getTrailingReturnsCat() {
        return trailingReturnsCat;
    }

    @JsonProperty("trailingReturnsCat")
    public void setTrailingReturnsCat(TrailingReturnsCat trailingReturnsCat) {
        this.trailingReturnsCat = trailingReturnsCat;
    }

    @JsonProperty("maxAge")
    public Integer getMaxAge() {
        return maxAge;
    }

    @JsonProperty("maxAge")
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    @JsonProperty("pastQuarterlyReturns")
    public PastQuarterlyReturns getPastQuarterlyReturns() {
        return pastQuarterlyReturns;
    }

    @JsonProperty("pastQuarterlyReturns")
    public void setPastQuarterlyReturns(PastQuarterlyReturns pastQuarterlyReturns) {
        this.pastQuarterlyReturns = pastQuarterlyReturns;
    }

    @JsonProperty("trailingReturnsNav")
    public TrailingReturnsNav getTrailingReturnsNav() {
        return trailingReturnsNav;
    }

    @JsonProperty("trailingReturnsNav")
    public void setTrailingReturnsNav(TrailingReturnsNav trailingReturnsNav) {
        this.trailingReturnsNav = trailingReturnsNav;
    }

    @JsonProperty("annualTotalReturns")
    public AnnualTotalReturns getAnnualTotalReturns() {
        return annualTotalReturns;
    }

    @JsonProperty("annualTotalReturns")
    public void setAnnualTotalReturns(AnnualTotalReturns annualTotalReturns) {
        this.annualTotalReturns = annualTotalReturns;
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
