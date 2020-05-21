
package com.github.dusanzahoransky.stockanalyst.model.ms.ckeyratios;

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
    "keyRatioFinancialsSection",
    "keyRatioStatisticsSection",
    "startDate",
    "endDate"
})
public class Result {

    @JsonProperty("keyRatioFinancialsSection")
    private KeyRatioFinancialsSection keyRatioFinancialsSection;
    @JsonProperty("keyRatioStatisticsSection")
    private KeyRatioStatisticsSection keyRatioStatisticsSection;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("keyRatioFinancialsSection")
    public KeyRatioFinancialsSection getKeyRatioFinancialsSection() {
        return keyRatioFinancialsSection;
    }

    @JsonProperty("keyRatioFinancialsSection")
    public void setKeyRatioFinancialsSection(KeyRatioFinancialsSection keyRatioFinancialsSection) {
        this.keyRatioFinancialsSection = keyRatioFinancialsSection;
    }

    @JsonProperty("keyRatioStatisticsSection")
    public KeyRatioStatisticsSection getKeyRatioStatisticsSection() {
        return keyRatioStatisticsSection;
    }

    @JsonProperty("keyRatioStatisticsSection")
    public void setKeyRatioStatisticsSection(KeyRatioStatisticsSection keyRatioStatisticsSection) {
        this.keyRatioStatisticsSection = keyRatioStatisticsSection;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Result.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("keyRatioFinancialsSection");
        sb.append('=');
        sb.append(((this.keyRatioFinancialsSection == null)?"<null>":this.keyRatioFinancialsSection));
        sb.append(',');
        sb.append("keyRatioStatisticsSection");
        sb.append('=');
        sb.append(((this.keyRatioStatisticsSection == null)?"<null>":this.keyRatioStatisticsSection));
        sb.append(',');
        sb.append("startDate");
        sb.append('=');
        sb.append(((this.startDate == null)?"<null>":this.startDate));
        sb.append(',');
        sb.append("endDate");
        sb.append('=');
        sb.append(((this.endDate == null)?"<null>":this.endDate));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.endDate == null)? 0 :this.endDate.hashCode()));
        result = ((result* 31)+((this.startDate == null)? 0 :this.startDate.hashCode()));
        result = ((result* 31)+((this.keyRatioFinancialsSection == null)? 0 :this.keyRatioFinancialsSection.hashCode()));
        result = ((result* 31)+((this.keyRatioStatisticsSection == null)? 0 :this.keyRatioStatisticsSection.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return ((((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.endDate == rhs.endDate)||((this.endDate!= null)&&this.endDate.equals(rhs.endDate))))&&((this.startDate == rhs.startDate)||((this.startDate!= null)&&this.startDate.equals(rhs.startDate))))&&((this.keyRatioFinancialsSection == rhs.keyRatioFinancialsSection)||((this.keyRatioFinancialsSection!= null)&&this.keyRatioFinancialsSection.equals(rhs.keyRatioFinancialsSection))))&&((this.keyRatioStatisticsSection == rhs.keyRatioStatisticsSection)||((this.keyRatioStatisticsSection!= null)&&this.keyRatioStatisticsSection.equals(rhs.keyRatioStatisticsSection))));
    }

}
