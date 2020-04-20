
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
    "ytd",
    "oneMonth",
    "threeMonth",
    "oneYear",
    "threeYear",
    "fiveYear",
    "tenYear"
})
public class TrailingReturnsNav {

    @JsonProperty("ytd")
    private Ytd ytd;
    @JsonProperty("oneMonth")
    private OneMonth oneMonth;
    @JsonProperty("threeMonth")
    private ThreeMonth threeMonth;
    @JsonProperty("oneYear")
    private OneYear oneYear;
    @JsonProperty("threeYear")
    private ThreeYear threeYear;
    @JsonProperty("fiveYear")
    private FiveYear fiveYear;
    @JsonProperty("tenYear")
    private TenYear tenYear;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ytd")
    public Ytd getYtd() {
        return ytd;
    }

    @JsonProperty("ytd")
    public void setYtd(Ytd ytd) {
        this.ytd = ytd;
    }

    @JsonProperty("oneMonth")
    public OneMonth getOneMonth() {
        return oneMonth;
    }

    @JsonProperty("oneMonth")
    public void setOneMonth(OneMonth oneMonth) {
        this.oneMonth = oneMonth;
    }

    @JsonProperty("threeMonth")
    public ThreeMonth getThreeMonth() {
        return threeMonth;
    }

    @JsonProperty("threeMonth")
    public void setThreeMonth(ThreeMonth threeMonth) {
        this.threeMonth = threeMonth;
    }

    @JsonProperty("oneYear")
    public OneYear getOneYear() {
        return oneYear;
    }

    @JsonProperty("oneYear")
    public void setOneYear(OneYear oneYear) {
        this.oneYear = oneYear;
    }

    @JsonProperty("threeYear")
    public ThreeYear getThreeYear() {
        return threeYear;
    }

    @JsonProperty("threeYear")
    public void setThreeYear(ThreeYear threeYear) {
        this.threeYear = threeYear;
    }

    @JsonProperty("fiveYear")
    public FiveYear getFiveYear() {
        return fiveYear;
    }

    @JsonProperty("fiveYear")
    public void setFiveYear(FiveYear fiveYear) {
        this.fiveYear = fiveYear;
    }

    @JsonProperty("tenYear")
    public TenYear getTenYear() {
        return tenYear;
    }

    @JsonProperty("tenYear")
    public void setTenYear(TenYear tenYear) {
        this.tenYear = tenYear;
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
