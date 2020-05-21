
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
    "cogs",
    "ebtMargin",
    "grossMargin",
    "netIntIncAndOther",
    "operatingMargin",
    "other",
    "rAndD",
    "revenue",
    "sgAndA"
})
public class MarginsPercentageOfSalesSection {

    @JsonProperty("cogs")
    private Double cogs;
    @JsonProperty("ebtMargin")
    private Double ebtMargin;
    @JsonProperty("grossMargin")
    private Double grossMargin;
    @JsonProperty("netIntIncAndOther")
    private Double netIntIncAndOther;
    @JsonProperty("operatingMargin")
    private Double operatingMargin;
    @JsonProperty("other")
    private Integer other;
    @JsonProperty("rAndD")
    private Double rAndD;
    @JsonProperty("revenue")
    private Double revenue;
    @JsonProperty("sgAndA")
    private Double sgAndA;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cogs")
    public Double getCogs() {
        return cogs;
    }

    @JsonProperty("cogs")
    public void setCogs(Double cogs) {
        this.cogs = cogs;
    }

    @JsonProperty("ebtMargin")
    public Double getEbtMargin() {
        return ebtMargin;
    }

    @JsonProperty("ebtMargin")
    public void setEbtMargin(Double ebtMargin) {
        this.ebtMargin = ebtMargin;
    }

    @JsonProperty("grossMargin")
    public Double getGrossMargin() {
        return grossMargin;
    }

    @JsonProperty("grossMargin")
    public void setGrossMargin(Double grossMargin) {
        this.grossMargin = grossMargin;
    }

    @JsonProperty("netIntIncAndOther")
    public Double getNetIntIncAndOther() {
        return netIntIncAndOther;
    }

    @JsonProperty("netIntIncAndOther")
    public void setNetIntIncAndOther(Double netIntIncAndOther) {
        this.netIntIncAndOther = netIntIncAndOther;
    }

    @JsonProperty("operatingMargin")
    public Double getOperatingMargin() {
        return operatingMargin;
    }

    @JsonProperty("operatingMargin")
    public void setOperatingMargin(Double operatingMargin) {
        this.operatingMargin = operatingMargin;
    }

    @JsonProperty("other")
    public Integer getOther() {
        return other;
    }

    @JsonProperty("other")
    public void setOther(Integer other) {
        this.other = other;
    }

    @JsonProperty("rAndD")
    public Double getrAndD() {
        return rAndD;
    }

    @JsonProperty("rAndD")
    public void setrAndD(Double rAndD) {
        this.rAndD = rAndD;
    }

    @JsonProperty("revenue")
    public Double getRevenue() {
        return revenue;
    }

    @JsonProperty("revenue")
    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    @JsonProperty("sgAndA")
    public Double getSgAndA() {
        return sgAndA;
    }

    @JsonProperty("sgAndA")
    public void setSgAndA(Double sgAndA) {
        this.sgAndA = sgAndA;
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
        sb.append(MarginsPercentageOfSalesSection.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("cogs");
        sb.append('=');
        sb.append(((this.cogs == null)?"<null>":this.cogs));
        sb.append(',');
        sb.append("ebtMargin");
        sb.append('=');
        sb.append(((this.ebtMargin == null)?"<null>":this.ebtMargin));
        sb.append(',');
        sb.append("grossMargin");
        sb.append('=');
        sb.append(((this.grossMargin == null)?"<null>":this.grossMargin));
        sb.append(',');
        sb.append("netIntIncAndOther");
        sb.append('=');
        sb.append(((this.netIntIncAndOther == null)?"<null>":this.netIntIncAndOther));
        sb.append(',');
        sb.append("operatingMargin");
        sb.append('=');
        sb.append(((this.operatingMargin == null)?"<null>":this.operatingMargin));
        sb.append(',');
        sb.append("other");
        sb.append('=');
        sb.append(((this.other == null)?"<null>":this.other));
        sb.append(',');
        sb.append("rAndD");
        sb.append('=');
        sb.append(((this.rAndD == null)?"<null>":this.rAndD));
        sb.append(',');
        sb.append("revenue");
        sb.append('=');
        sb.append(((this.revenue == null)?"<null>":this.revenue));
        sb.append(',');
        sb.append("sgAndA");
        sb.append('=');
        sb.append(((this.sgAndA == null)?"<null>":this.sgAndA));
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
        result = ((result* 31)+((this.ebtMargin == null)? 0 :this.ebtMargin.hashCode()));
        result = ((result* 31)+((this.operatingMargin == null)? 0 :this.operatingMargin.hashCode()));
        result = ((result* 31)+((this.other == null)? 0 :this.other.hashCode()));
        result = ((result* 31)+((this.revenue == null)? 0 :this.revenue.hashCode()));
        result = ((result* 31)+((this.cogs == null)? 0 :this.cogs.hashCode()));
        result = ((result* 31)+((this.sgAndA == null)? 0 :this.sgAndA.hashCode()));
        result = ((result* 31)+((this.rAndD == null)? 0 :this.rAndD.hashCode()));
        result = ((result* 31)+((this.grossMargin == null)? 0 :this.grossMargin.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.netIntIncAndOther == null)? 0 :this.netIntIncAndOther.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MarginsPercentageOfSalesSection) == false) {
            return false;
        }
        MarginsPercentageOfSalesSection rhs = ((MarginsPercentageOfSalesSection) other);
        return (((((((((((this.ebtMargin == rhs.ebtMargin)||((this.ebtMargin!= null)&&this.ebtMargin.equals(rhs.ebtMargin)))&&((this.operatingMargin == rhs.operatingMargin)||((this.operatingMargin!= null)&&this.operatingMargin.equals(rhs.operatingMargin))))&&((this.other == rhs.other)||((this.other!= null)&&this.other.equals(rhs.other))))&&((this.revenue == rhs.revenue)||((this.revenue!= null)&&this.revenue.equals(rhs.revenue))))&&((this.cogs == rhs.cogs)||((this.cogs!= null)&&this.cogs.equals(rhs.cogs))))&&((this.sgAndA == rhs.sgAndA)||((this.sgAndA!= null)&&this.sgAndA.equals(rhs.sgAndA))))&&((this.rAndD == rhs.rAndD)||((this.rAndD!= null)&&this.rAndD.equals(rhs.rAndD))))&&((this.grossMargin == rhs.grossMargin)||((this.grossMargin!= null)&&this.grossMargin.equals(rhs.grossMargin))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.netIntIncAndOther == rhs.netIntIncAndOther)||((this.netIntIncAndOther!= null)&&this.netIntIncAndOther.equals(rhs.netIntIncAndOther))));
    }

}
