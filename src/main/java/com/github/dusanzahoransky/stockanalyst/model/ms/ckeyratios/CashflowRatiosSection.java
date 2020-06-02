
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
    "capExAsAPercentOfSales",
    "freeCashFlowGrowthPercentYoy",
    "freeCashFlowOrNetIncome",
    "freeCashFlowOrSalesPercent",
    "operatingCashFlowGrowthPercentYoy"
})
public class CashflowRatiosSection {

    @JsonProperty("capExAsAPercentOfSales")
    private Double capExAsAPercentOfSales;
    @JsonProperty("freeCashFlowGrowthPercentYoy")
    private Double freeCashFlowGrowthPercentYoy;
    @JsonProperty("freeCashFlowOrNetIncome")
    private Double freeCashFlowOrNetIncome;
    @JsonProperty("freeCashFlowOrSalesPercent")
    private Double freeCashFlowOrSalesPercent;
    @JsonProperty("operatingCashFlowGrowthPercentYoy")
    private Double operatingCashFlowGrowthPercentYoy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("capExAsAPercentOfSales")
    public Double getCapExAsAPercentOfSales() {
        return capExAsAPercentOfSales;
    }

    @JsonProperty("capExAsAPercentOfSales")
    public void setCapExAsAPercentOfSales(Double capExAsAPercentOfSales) {
        this.capExAsAPercentOfSales = capExAsAPercentOfSales;
    }

    @JsonProperty("freeCashFlowGrowthPercentYoy")
    public Double getFreeCashFlowGrowthPercentYoy() {
        return freeCashFlowGrowthPercentYoy;
    }

    @JsonProperty("freeCashFlowGrowthPercentYoy")
    public void setFreeCashFlowGrowthPercentYoy(Double freeCashFlowGrowthPercentYoy) {
        this.freeCashFlowGrowthPercentYoy = freeCashFlowGrowthPercentYoy;
    }

    @JsonProperty("freeCashFlowOrNetIncome")
    public Double getFreeCashFlowOrNetIncome() {
        return freeCashFlowOrNetIncome;
    }

    @JsonProperty("freeCashFlowOrNetIncome")
    public void setFreeCashFlowOrNetIncome(Double freeCashFlowOrNetIncome) {
        this.freeCashFlowOrNetIncome = freeCashFlowOrNetIncome;
    }

    @JsonProperty("freeCashFlowOrSalesPercent")
    public Double getFreeCashFlowOrSalesPercent() {
        return freeCashFlowOrSalesPercent;
    }

    @JsonProperty("freeCashFlowOrSalesPercent")
    public void setFreeCashFlowOrSalesPercent(Double freeCashFlowOrSalesPercent) {
        this.freeCashFlowOrSalesPercent = freeCashFlowOrSalesPercent;
    }

    @JsonProperty("operatingCashFlowGrowthPercentYoy")
    public Double getOperatingCashFlowGrowthPercentYoy() {
        return operatingCashFlowGrowthPercentYoy;
    }

    @JsonProperty("operatingCashFlowGrowthPercentYoy")
    public void setOperatingCashFlowGrowthPercentYoy(Double operatingCashFlowGrowthPercentYoy) {
        this.operatingCashFlowGrowthPercentYoy = operatingCashFlowGrowthPercentYoy;
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
        sb.append(CashflowRatiosSection.class.getName()).append('@').append(Long.toHexString(System.identityHashCode(this))).append('[');
        sb.append("capExAsAPercentOfSales");
        sb.append('=');
        sb.append(((this.capExAsAPercentOfSales == null)?"<null>":this.capExAsAPercentOfSales));
        sb.append(',');
        sb.append("freeCashFlowGrowthPercentYoy");
        sb.append('=');
        sb.append(((this.freeCashFlowGrowthPercentYoy == null)?"<null>":this.freeCashFlowGrowthPercentYoy));
        sb.append(',');
        sb.append("freeCashFlowOrNetIncome");
        sb.append('=');
        sb.append(((this.freeCashFlowOrNetIncome == null)?"<null>":this.freeCashFlowOrNetIncome));
        sb.append(',');
        sb.append("freeCashFlowOrSalesPercent");
        sb.append('=');
        sb.append(((this.freeCashFlowOrSalesPercent == null)?"<null>":this.freeCashFlowOrSalesPercent));
        sb.append(',');
        sb.append("operatingCashFlowGrowthPercentYoy");
        sb.append('=');
        sb.append(((this.operatingCashFlowGrowthPercentYoy == null)?"<null>":this.operatingCashFlowGrowthPercentYoy));
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
        result = ((result* 31)+((this.operatingCashFlowGrowthPercentYoy == null)? 0 :this.operatingCashFlowGrowthPercentYoy.hashCode()));
        result = ((result* 31)+((this.freeCashFlowGrowthPercentYoy == null)? 0 :this.freeCashFlowGrowthPercentYoy.hashCode()));
        result = ((result* 31)+((this.freeCashFlowOrNetIncome == null)? 0 :this.freeCashFlowOrNetIncome.hashCode()));
        result = ((result* 31)+((this.freeCashFlowOrSalesPercent == null)? 0 :this.freeCashFlowOrSalesPercent.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.capExAsAPercentOfSales == null)? 0 :this.capExAsAPercentOfSales.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CashflowRatiosSection) == false) {
            return false;
        }
        CashflowRatiosSection rhs = ((CashflowRatiosSection) other);
        return (((((((this.operatingCashFlowGrowthPercentYoy == rhs.operatingCashFlowGrowthPercentYoy)||((this.operatingCashFlowGrowthPercentYoy!= null)&&this.operatingCashFlowGrowthPercentYoy.equals(rhs.operatingCashFlowGrowthPercentYoy)))&&((this.freeCashFlowGrowthPercentYoy == rhs.freeCashFlowGrowthPercentYoy)||((this.freeCashFlowGrowthPercentYoy!= null)&&this.freeCashFlowGrowthPercentYoy.equals(rhs.freeCashFlowGrowthPercentYoy))))&&((this.freeCashFlowOrNetIncome == rhs.freeCashFlowOrNetIncome)||((this.freeCashFlowOrNetIncome!= null)&&this.freeCashFlowOrNetIncome.equals(rhs.freeCashFlowOrNetIncome))))&&((this.freeCashFlowOrSalesPercent == rhs.freeCashFlowOrSalesPercent)||((this.freeCashFlowOrSalesPercent!= null)&&this.freeCashFlowOrSalesPercent.equals(rhs.freeCashFlowOrSalesPercent))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.capExAsAPercentOfSales == rhs.capExAsAPercentOfSales)||((this.capExAsAPercentOfSales!= null)&&this.capExAsAPercentOfSales.equals(rhs.capExAsAPercentOfSales))));
    }

}
