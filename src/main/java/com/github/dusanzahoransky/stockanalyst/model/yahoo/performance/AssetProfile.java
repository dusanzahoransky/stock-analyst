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
    "longBusinessSummary",
    "companyOfficers",
    "maxAge"
})
public class AssetProfile {

    @JsonProperty("longBusinessSummary")
    private String longBusinessSummary;
    @JsonProperty("companyOfficers")
    private List<Object> companyOfficers = null;
    @JsonProperty("maxAge")
    private Integer maxAge;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("longBusinessSummary")
    public String getLongBusinessSummary() {
        return longBusinessSummary;
    }

    @JsonProperty("longBusinessSummary")
    public void setLongBusinessSummary(String longBusinessSummary) {
        this.longBusinessSummary = longBusinessSummary;
    }

    @JsonProperty("companyOfficers")
    public List<Object> getCompanyOfficers() {
        return companyOfficers;
    }

    @JsonProperty("companyOfficers")
    public void setCompanyOfficers(List<Object> companyOfficers) {
        this.companyOfficers = companyOfficers;
    }

    @JsonProperty("maxAge")
    public Integer getMaxAge() {
        return maxAge;
    }

    @JsonProperty("maxAge")
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
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
