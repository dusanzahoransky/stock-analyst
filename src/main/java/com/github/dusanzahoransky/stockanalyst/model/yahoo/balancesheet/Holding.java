
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
    "symbol",
    "holdingName",
    "holdingPercent"
})
public class Holding {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("holdingName")
    private String holdingName;
    @JsonProperty("holdingPercent")
    private HoldingPercent holdingPercent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("holdingName")
    public String getHoldingName() {
        return holdingName;
    }

    @JsonProperty("holdingName")
    public void setHoldingName(String holdingName) {
        this.holdingName = holdingName;
    }

    @JsonProperty("holdingPercent")
    public HoldingPercent getHoldingPercent() {
        return holdingPercent;
    }

    @JsonProperty("holdingPercent")
    public void setHoldingPercent(HoldingPercent holdingPercent) {
        this.holdingPercent = holdingPercent;
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
