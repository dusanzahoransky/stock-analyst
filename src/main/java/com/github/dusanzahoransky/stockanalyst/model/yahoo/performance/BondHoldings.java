
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
    "maturity",
    "duration",
    "creditQuality",
    "maturityCat",
    "durationCat",
    "creditQualityCat"
})
public class BondHoldings {

    @JsonProperty("maturity")
    private Maturity maturity;
    @JsonProperty("duration")
    private Duration duration;
    @JsonProperty("creditQuality")
    private CreditQuality creditQuality;
    @JsonProperty("maturityCat")
    private MaturityCat maturityCat;
    @JsonProperty("durationCat")
    private DurationCat durationCat;
    @JsonProperty("creditQualityCat")
    private CreditQualityCat creditQualityCat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("maturity")
    public Maturity getMaturity() {
        return maturity;
    }

    @JsonProperty("maturity")
    public void setMaturity(Maturity maturity) {
        this.maturity = maturity;
    }

    @JsonProperty("duration")
    public Duration getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @JsonProperty("creditQuality")
    public CreditQuality getCreditQuality() {
        return creditQuality;
    }

    @JsonProperty("creditQuality")
    public void setCreditQuality(CreditQuality creditQuality) {
        this.creditQuality = creditQuality;
    }

    @JsonProperty("maturityCat")
    public MaturityCat getMaturityCat() {
        return maturityCat;
    }

    @JsonProperty("maturityCat")
    public void setMaturityCat(MaturityCat maturityCat) {
        this.maturityCat = maturityCat;
    }

    @JsonProperty("durationCat")
    public DurationCat getDurationCat() {
        return durationCat;
    }

    @JsonProperty("durationCat")
    public void setDurationCat(DurationCat durationCat) {
        this.durationCat = durationCat;
    }

    @JsonProperty("creditQualityCat")
    public CreditQualityCat getCreditQualityCat() {
        return creditQualityCat;
    }

    @JsonProperty("creditQualityCat")
    public void setCreditQualityCat(CreditQualityCat creditQualityCat) {
        this.creditQualityCat = creditQualityCat;
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
