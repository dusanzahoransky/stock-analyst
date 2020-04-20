
package com.github.dusanzahoransky.stockanalyst.model.yahoo.balancesheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "returns",
    "returnsCat"
})
public class AnnualTotalReturns {

    @JsonProperty("returns")
    private List<Return> returns = null;
    @JsonProperty("returnsCat")
    private List<Object> returnsCat = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("returns")
    public List<Return> getReturns() {
        return returns;
    }

    @JsonProperty("returns")
    public void setReturns(List<Return> returns) {
        this.returns = returns;
    }

    @JsonProperty("returnsCat")
    public List<Object> getReturnsCat() {
        return returnsCat;
    }

    @JsonProperty("returnsCat")
    public void setReturnsCat(List<Object> returnsCat) {
        this.returnsCat = returnsCat;
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
