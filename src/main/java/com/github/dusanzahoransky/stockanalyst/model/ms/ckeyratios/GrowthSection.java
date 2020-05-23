
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
    "revenueGrowthSection",
    "operatingIncomeGrowthSection",
    "netIncomeGrowthSection",
    "earningsPerShareGrowthSection"
})
public class GrowthSection {

    @JsonProperty("revenueGrowthSection")
    private RevenueGrowthSection revenueGrowthSection;
    @JsonProperty("operatingIncomeGrowthSection")
    private OperatingIncomeGrowthSection operatingIncomeGrowthSection;
    @JsonProperty("netIncomeGrowthSection")
    private NetIncomeGrowthSection netIncomeGrowthSection;
    @JsonProperty("earningsPerShareGrowthSection")
    private EarningsPerShareGrowthSection earningsPerShareGrowthSection;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("revenueGrowthSection")
    public RevenueGrowthSection getRevenueGrowthSection() {
        return revenueGrowthSection;
    }

    @JsonProperty("revenueGrowthSection")
    public void setRevenueGrowthSection(RevenueGrowthSection revenueGrowthSection) {
        this.revenueGrowthSection = revenueGrowthSection;
    }

    @JsonProperty("operatingIncomeGrowthSection")
    public OperatingIncomeGrowthSection getOperatingIncomeGrowthSection() {
        return operatingIncomeGrowthSection;
    }

    @JsonProperty("operatingIncomeGrowthSection")
    public void setOperatingIncomeGrowthSection(OperatingIncomeGrowthSection operatingIncomeGrowthSection) {
        this.operatingIncomeGrowthSection = operatingIncomeGrowthSection;
    }

    @JsonProperty("netIncomeGrowthSection")
    public NetIncomeGrowthSection getNetIncomeGrowthSection() {
        return netIncomeGrowthSection;
    }

    @JsonProperty("netIncomeGrowthSection")
    public void setNetIncomeGrowthSection(NetIncomeGrowthSection netIncomeGrowthSection) {
        this.netIncomeGrowthSection = netIncomeGrowthSection;
    }

    @JsonProperty("earningsPerShareGrowthSection")
    public EarningsPerShareGrowthSection getEarningsPerShareGrowthSection() {
        return earningsPerShareGrowthSection;
    }

    @JsonProperty("earningsPerShareGrowthSection")
    public void setEarningsPerShareGrowthSection(EarningsPerShareGrowthSection earningsPerShareGrowthSection) {
        this.earningsPerShareGrowthSection = earningsPerShareGrowthSection;
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
        sb.append(GrowthSection.class.getName()).append('@').append(Long.toHexString(System.identityHashCode(this))).append('[');
        sb.append("revenueGrowthSection");
        sb.append('=');
        sb.append(((this.revenueGrowthSection == null)?"<null>":this.revenueGrowthSection));
        sb.append(',');
        sb.append("operatingIncomeGrowthSection");
        sb.append('=');
        sb.append(((this.operatingIncomeGrowthSection == null)?"<null>":this.operatingIncomeGrowthSection));
        sb.append(',');
        sb.append("netIncomeGrowthSection");
        sb.append('=');
        sb.append(((this.netIncomeGrowthSection == null)?"<null>":this.netIncomeGrowthSection));
        sb.append(',');
        sb.append("earningsPerShareGrowthSection");
        sb.append('=');
        sb.append(((this.earningsPerShareGrowthSection == null)?"<null>":this.earningsPerShareGrowthSection));
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
        result = ((result* 31)+((this.earningsPerShareGrowthSection == null)? 0 :this.earningsPerShareGrowthSection.hashCode()));
        result = ((result* 31)+((this.netIncomeGrowthSection == null)? 0 :this.netIncomeGrowthSection.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.revenueGrowthSection == null)? 0 :this.revenueGrowthSection.hashCode()));
        result = ((result* 31)+((this.operatingIncomeGrowthSection == null)? 0 :this.operatingIncomeGrowthSection.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GrowthSection) == false) {
            return false;
        }
        GrowthSection rhs = ((GrowthSection) other);
        return ((((((this.earningsPerShareGrowthSection == rhs.earningsPerShareGrowthSection)||((this.earningsPerShareGrowthSection!= null)&&this.earningsPerShareGrowthSection.equals(rhs.earningsPerShareGrowthSection)))&&((this.netIncomeGrowthSection == rhs.netIncomeGrowthSection)||((this.netIncomeGrowthSection!= null)&&this.netIncomeGrowthSection.equals(rhs.netIncomeGrowthSection))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.revenueGrowthSection == rhs.revenueGrowthSection)||((this.revenueGrowthSection!= null)&&this.revenueGrowthSection.equals(rhs.revenueGrowthSection))))&&((this.operatingIncomeGrowthSection == rhs.operatingIncomeGrowthSection)||((this.operatingIncomeGrowthSection!= null)&&this.operatingIncomeGrowthSection.equals(rhs.operatingIncomeGrowthSection))));
    }

}
