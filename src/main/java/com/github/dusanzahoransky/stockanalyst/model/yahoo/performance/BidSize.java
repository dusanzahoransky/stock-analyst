
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
    "raw",
    "fmt",
    "longFmt"
})
public class BidSize {

    @JsonProperty("raw")
    private Integer raw;
    @JsonProperty("fmt")
    private Object fmt;
    @JsonProperty("longFmt")
    private String longFmt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("raw")
    public Integer getRaw() {
        return raw;
    }

    @JsonProperty("raw")
    public void setRaw(Integer raw) {
        this.raw = raw;
    }

    @JsonProperty("fmt")
    public Object getFmt() {
        return fmt;
    }

    @JsonProperty("fmt")
    public void setFmt(Object fmt) {
        this.fmt = fmt;
    }

    @JsonProperty("longFmt")
    public String getLongFmt() {
        return longFmt;
    }

    @JsonProperty("longFmt")
    public void setLongFmt(String longFmt) {
        this.longFmt = longFmt;
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
