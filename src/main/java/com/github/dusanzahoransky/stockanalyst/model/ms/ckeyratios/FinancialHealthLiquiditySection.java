
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
    "currentRatio",
    "debtOrEquity",
    "financialLeverage",
    "quickRatio"
})
public class FinancialHealthLiquiditySection {

    @JsonProperty("currentRatio")
    private Double currentRatio;
    @JsonProperty("debtOrEquity")
    private Double debtOrEquity;
    @JsonProperty("financialLeverage")
    private Double financialLeverage;
    @JsonProperty("quickRatio")
    private Double quickRatio;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("currentRatio")
    public Double getCurrentRatio() {
        return currentRatio;
    }

    @JsonProperty("currentRatio")
    public void setCurrentRatio(Double currentRatio) {
        this.currentRatio = currentRatio;
    }

    @JsonProperty("debtOrEquity")
    public Double getDebtOrEquity() {
        return debtOrEquity;
    }

    @JsonProperty("debtOrEquity")
    public void setDebtOrEquity(Double debtOrEquity) {
        this.debtOrEquity = debtOrEquity;
    }

    @JsonProperty("financialLeverage")
    public Double getFinancialLeverage() {
        return financialLeverage;
    }

    @JsonProperty("financialLeverage")
    public void setFinancialLeverage(Double financialLeverage) {
        this.financialLeverage = financialLeverage;
    }

    @JsonProperty("quickRatio")
    public Double getQuickRatio() {
        return quickRatio;
    }

    @JsonProperty("quickRatio")
    public void setQuickRatio(Double quickRatio) {
        this.quickRatio = quickRatio;
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
        sb.append(FinancialHealthLiquiditySection.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currentRatio");
        sb.append('=');
        sb.append(((this.currentRatio == null)?"<null>":this.currentRatio));
        sb.append(',');
        sb.append("debtOrEquity");
        sb.append('=');
        sb.append(((this.debtOrEquity == null)?"<null>":this.debtOrEquity));
        sb.append(',');
        sb.append("financialLeverage");
        sb.append('=');
        sb.append(((this.financialLeverage == null)?"<null>":this.financialLeverage));
        sb.append(',');
        sb.append("quickRatio");
        sb.append('=');
        sb.append(((this.quickRatio == null)?"<null>":this.quickRatio));
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
        result = ((result* 31)+((this.quickRatio == null)? 0 :this.quickRatio.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.currentRatio == null)? 0 :this.currentRatio.hashCode()));
        result = ((result* 31)+((this.debtOrEquity == null)? 0 :this.debtOrEquity.hashCode()));
        result = ((result* 31)+((this.financialLeverage == null)? 0 :this.financialLeverage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FinancialHealthLiquiditySection) == false) {
            return false;
        }
        FinancialHealthLiquiditySection rhs = ((FinancialHealthLiquiditySection) other);
        return ((((((this.quickRatio == rhs.quickRatio)||((this.quickRatio!= null)&&this.quickRatio.equals(rhs.quickRatio)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.currentRatio == rhs.currentRatio)||((this.currentRatio!= null)&&this.currentRatio.equals(rhs.currentRatio))))&&((this.debtOrEquity == rhs.debtOrEquity)||((this.debtOrEquity!= null)&&this.debtOrEquity.equals(rhs.debtOrEquity))))&&((this.financialLeverage == rhs.financialLeverage)||((this.financialLeverage!= null)&&this.financialLeverage.equals(rhs.financialLeverage))));
    }

}
