package com.github.dusanzahoransky.stockanalyst.model.yahoo.performance;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder( {
    "riskRating",
    "riskStatistics"
})
public class RiskOverviewStatistics {

    @JsonProperty("riskRating")
    private RiskRating riskRating;
    @JsonProperty("riskStatistics")
    private List<RiskStatistic> riskStatistics = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("riskRating")
    public RiskRating getRiskRating() {
        return riskRating;
    }

    @JsonProperty("riskRating")
    public void setRiskRating(RiskRating riskRating) {
        this.riskRating = riskRating;
    }

    @JsonProperty("riskStatistics")
    public List<RiskStatistic> getRiskStatistics() {
        return riskStatistics;
    }

    @JsonProperty("riskStatistics")
    public void setRiskStatistics(List<RiskStatistic> riskStatistics) {
        this.riskStatistics = riskStatistics;
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
