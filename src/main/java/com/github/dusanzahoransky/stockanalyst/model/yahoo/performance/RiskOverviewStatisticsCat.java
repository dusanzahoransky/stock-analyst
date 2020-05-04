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
    "riskStatisticsCat"
})
public class RiskOverviewStatisticsCat {

    @JsonProperty("riskStatisticsCat")
    private List<Object> riskStatisticsCat = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("riskStatisticsCat")
    public List<Object> getRiskStatisticsCat() {
        return riskStatisticsCat;
    }

    @JsonProperty("riskStatisticsCat")
    public void setRiskStatisticsCat(List<Object> riskStatisticsCat) {
        this.riskStatisticsCat = riskStatisticsCat;
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
