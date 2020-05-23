
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
    "tenYearAverage",
    "threeYearAverage",
    "fiveYearAverage",
    "yearOverYear"
})
public class OperatingIncomeGrowthSection {

    @JsonProperty("tenYearAverage")
    private Double tenYearAverage;
    @JsonProperty("threeYearAverage")
    private Double threeYearAverage;
    @JsonProperty("fiveYearAverage")
    private Double fiveYearAverage;
    @JsonProperty("yearOverYear")
    private Double yearOverYear;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("tenYearAverage")
    public Double getTenYearAverage() {
        return tenYearAverage;
    }

    @JsonProperty("tenYearAverage")
    public void setTenYearAverage(Double tenYearAverage) {
        this.tenYearAverage = tenYearAverage;
    }

    @JsonProperty("threeYearAverage")
    public Double getThreeYearAverage() {
        return threeYearAverage;
    }

    @JsonProperty("threeYearAverage")
    public void setThreeYearAverage(Double threeYearAverage) {
        this.threeYearAverage = threeYearAverage;
    }

    @JsonProperty("fiveYearAverage")
    public Double getFiveYearAverage() {
        return fiveYearAverage;
    }

    @JsonProperty("fiveYearAverage")
    public void setFiveYearAverage(Double fiveYearAverage) {
        this.fiveYearAverage = fiveYearAverage;
    }

    @JsonProperty("yearOverYear")
    public Double getYearOverYear() {
        return yearOverYear;
    }

    @JsonProperty("yearOverYear")
    public void setYearOverYear(Double yearOverYear) {
        this.yearOverYear = yearOverYear;
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
        sb.append(OperatingIncomeGrowthSection.class.getName()).append('@').append(Long.toHexString(System.identityHashCode(this))).append('[');
        sb.append("tenYearAverage");
        sb.append('=');
        sb.append(((this.tenYearAverage == null)?"<null>":this.tenYearAverage));
        sb.append(',');
        sb.append("threeYearAverage");
        sb.append('=');
        sb.append(((this.threeYearAverage == null)?"<null>":this.threeYearAverage));
        sb.append(',');
        sb.append("fiveYearAverage");
        sb.append('=');
        sb.append(((this.fiveYearAverage == null)?"<null>":this.fiveYearAverage));
        sb.append(',');
        sb.append("yearOverYear");
        sb.append('=');
        sb.append(((this.yearOverYear == null)?"<null>":this.yearOverYear));
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
        result = ((result* 31)+((this.tenYearAverage == null)? 0 :this.tenYearAverage.hashCode()));
        result = ((result* 31)+((this.fiveYearAverage == null)? 0 :this.fiveYearAverage.hashCode()));
        result = ((result* 31)+((this.yearOverYear == null)? 0 :this.yearOverYear.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.threeYearAverage == null)? 0 :this.threeYearAverage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OperatingIncomeGrowthSection) == false) {
            return false;
        }
        OperatingIncomeGrowthSection rhs = ((OperatingIncomeGrowthSection) other);
        return ((((((this.tenYearAverage == rhs.tenYearAverage)||((this.tenYearAverage!= null)&&this.tenYearAverage.equals(rhs.tenYearAverage)))&&((this.fiveYearAverage == rhs.fiveYearAverage)||((this.fiveYearAverage!= null)&&this.fiveYearAverage.equals(rhs.fiveYearAverage))))&&((this.yearOverYear == rhs.yearOverYear)||((this.yearOverYear!= null)&&this.yearOverYear.equals(rhs.yearOverYear))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.threeYearAverage == rhs.threeYearAverage)||((this.threeYearAverage!= null)&&this.threeYearAverage.equals(rhs.threeYearAverage))));
    }

}
