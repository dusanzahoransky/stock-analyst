
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
    "year",
    "alpha",
    "beta",
    "meanAnnualReturn",
    "rSquared",
    "stdDev",
    "sharpeRatio",
    "treynorRatio"
})
public class RiskStatistic {

    @JsonProperty("year")
    private String year;
    @JsonProperty("alpha")
    private Alpha alpha;
    @JsonProperty("beta")
    private Beta beta;
    @JsonProperty("meanAnnualReturn")
    private MeanAnnualReturn meanAnnualReturn;
    @JsonProperty("rSquared")
    private RSquared rSquared;
    @JsonProperty("stdDev")
    private StdDev stdDev;
    @JsonProperty("sharpeRatio")
    private SharpeRatio sharpeRatio;
    @JsonProperty("treynorRatio")
    private TreynorRatio treynorRatio;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonProperty("alpha")
    public Alpha getAlpha() {
        return alpha;
    }

    @JsonProperty("alpha")
    public void setAlpha(Alpha alpha) {
        this.alpha = alpha;
    }

    @JsonProperty("beta")
    public Beta getBeta() {
        return beta;
    }

    @JsonProperty("beta")
    public void setBeta(Beta beta) {
        this.beta = beta;
    }

    @JsonProperty("meanAnnualReturn")
    public MeanAnnualReturn getMeanAnnualReturn() {
        return meanAnnualReturn;
    }

    @JsonProperty("meanAnnualReturn")
    public void setMeanAnnualReturn(MeanAnnualReturn meanAnnualReturn) {
        this.meanAnnualReturn = meanAnnualReturn;
    }

    @JsonProperty("rSquared")
    public RSquared getRSquared() {
        return rSquared;
    }

    @JsonProperty("rSquared")
    public void setRSquared(RSquared rSquared) {
        this.rSquared = rSquared;
    }

    @JsonProperty("stdDev")
    public StdDev getStdDev() {
        return stdDev;
    }

    @JsonProperty("stdDev")
    public void setStdDev(StdDev stdDev) {
        this.stdDev = stdDev;
    }

    @JsonProperty("sharpeRatio")
    public SharpeRatio getSharpeRatio() {
        return sharpeRatio;
    }

    @JsonProperty("sharpeRatio")
    public void setSharpeRatio(SharpeRatio sharpeRatio) {
        this.sharpeRatio = sharpeRatio;
    }

    @JsonProperty("treynorRatio")
    public TreynorRatio getTreynorRatio() {
        return treynorRatio;
    }

    @JsonProperty("treynorRatio")
    public void setTreynorRatio(TreynorRatio treynorRatio) {
        this.treynorRatio = treynorRatio;
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
