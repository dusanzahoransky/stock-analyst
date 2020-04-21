
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
    "threeMonth",
    "oneYear",
    "ytd",
    "lastBearMkt",
    "lastBullMkt",
    "fiveYear",
    "tenYear",
    "oneMonth",
    "threeYear"
})
public class TrailingReturnsCat {

    @JsonProperty("threeMonth")
    private ThreeMonth threeMonth;
    @JsonProperty("oneYear")
    private OneYear oneYear;
    @JsonProperty("ytd")
    private Ytd ytd;
    @JsonProperty("lastBearMkt")
    private LastBearMkt lastBearMkt;
    @JsonProperty("lastBullMkt")
    private LastBullMkt lastBullMkt;
    @JsonProperty("fiveYear")
    private FiveYear fiveYear;
    @JsonProperty("tenYear")
    private TenYear tenYear;
    @JsonProperty("oneMonth")
    private OneMonth oneMonth;
    @JsonProperty("threeYear")
    private ThreeYear threeYear;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("ytd")
    public Ytd getYtd() {
        return ytd;
    }

    @JsonProperty("ytd")
    public void setYtd(Ytd ytd) {
        this.ytd = ytd;
    }

    @JsonProperty("lastBearMkt")
    public LastBearMkt getLastBearMkt() {
        return lastBearMkt;
    }

    @JsonProperty("lastBearMkt")
    public void setLastBearMkt(LastBearMkt lastBearMkt) {
        this.lastBearMkt = lastBearMkt;
    }

    @JsonProperty("lastBullMkt")
    public LastBullMkt getLastBullMkt() {
        return lastBullMkt;
    }

    @JsonProperty("lastBullMkt")
    public void setLastBullMkt(LastBullMkt lastBullMkt) {
        this.lastBullMkt = lastBullMkt;
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

    @JsonProperty("oneMonth")
    public OneMonth getOneMonth() {
        return oneMonth;
    }

    @JsonProperty("oneMonth")
    public void setOneMonth(OneMonth oneMonth) {
        this.oneMonth = oneMonth;
    }

    @JsonProperty("threeYear")
    public ThreeYear getThreeYear() {
        return threeYear;
    }

    @JsonProperty("threeYear")
    public void setThreeYear(ThreeYear threeYear) {
        this.threeYear = threeYear;
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
