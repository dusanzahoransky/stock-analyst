
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
    "managerName",
    "managerBio",
    "startdate"
})
public class ManagementInfo {

    @JsonProperty("managerName")
    private Object managerName;
    @JsonProperty("managerBio")
    private Object managerBio;
    @JsonProperty("startdate")
    private Startdate startdate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("managerName")
    public Object getManagerName() {
        return managerName;
    }

    @JsonProperty("managerName")
    public void setManagerName(Object managerName) {
        this.managerName = managerName;
    }

    @JsonProperty("managerBio")
    public Object getManagerBio() {
        return managerBio;
    }

    @JsonProperty("managerBio")
    public void setManagerBio(Object managerBio) {
        this.managerBio = managerBio;
    }

    @JsonProperty("startdate")
    public Startdate getStartdate() {
        return startdate;
    }

    @JsonProperty("startdate")
    public void setStartdate(Startdate startdate) {
        this.startdate = startdate;
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
