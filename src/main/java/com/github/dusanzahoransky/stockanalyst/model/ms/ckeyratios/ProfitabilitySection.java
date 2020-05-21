
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
    "assetTurnoverOnAverage",
    "financialLeverageOnAverage",
    "interestCoverage",
    "netMarginPercent",
    "returnOnAssetsPercent",
    "returnOnEquityPercent",
    "returnOnInvestedCapitalPercent",
    "taxRatePercent"
})
public class ProfitabilitySection {

    @JsonProperty("assetTurnoverOnAverage")
    private Double assetTurnoverOnAverage;
    @JsonProperty("financialLeverageOnAverage")
    private Double financialLeverageOnAverage;
    @JsonProperty("interestCoverage")
    private Double interestCoverage;
    @JsonProperty("netMarginPercent")
    private Double netMarginPercent;
    @JsonProperty("returnOnAssetsPercent")
    private Double returnOnAssetsPercent;
    @JsonProperty("returnOnEquityPercent")
    private Double returnOnEquityPercent;
    @JsonProperty("returnOnInvestedCapitalPercent")
    private Double returnOnInvestedCapitalPercent;
    @JsonProperty("taxRatePercent")
    private Double taxRatePercent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("assetTurnoverOnAverage")
    public Double getAssetTurnoverOnAverage() {
        return assetTurnoverOnAverage;
    }

    @JsonProperty("assetTurnoverOnAverage")
    public void setAssetTurnoverOnAverage(Double assetTurnoverOnAverage) {
        this.assetTurnoverOnAverage = assetTurnoverOnAverage;
    }

    @JsonProperty("financialLeverageOnAverage")
    public Double getFinancialLeverageOnAverage() {
        return financialLeverageOnAverage;
    }

    @JsonProperty("financialLeverageOnAverage")
    public void setFinancialLeverageOnAverage(Double financialLeverageOnAverage) {
        this.financialLeverageOnAverage = financialLeverageOnAverage;
    }

    @JsonProperty("interestCoverage")
    public Double getInterestCoverage() {
        return interestCoverage;
    }

    @JsonProperty("interestCoverage")
    public void setInterestCoverage(Double interestCoverage) {
        this.interestCoverage = interestCoverage;
    }

    @JsonProperty("netMarginPercent")
    public Double getNetMarginPercent() {
        return netMarginPercent;
    }

    @JsonProperty("netMarginPercent")
    public void setNetMarginPercent(Double netMarginPercent) {
        this.netMarginPercent = netMarginPercent;
    }

    @JsonProperty("returnOnAssetsPercent")
    public Double getReturnOnAssetsPercent() {
        return returnOnAssetsPercent;
    }

    @JsonProperty("returnOnAssetsPercent")
    public void setReturnOnAssetsPercent(Double returnOnAssetsPercent) {
        this.returnOnAssetsPercent = returnOnAssetsPercent;
    }

    @JsonProperty("returnOnEquityPercent")
    public Double getReturnOnEquityPercent() {
        return returnOnEquityPercent;
    }

    @JsonProperty("returnOnEquityPercent")
    public void setReturnOnEquityPercent(Double returnOnEquityPercent) {
        this.returnOnEquityPercent = returnOnEquityPercent;
    }

    @JsonProperty("returnOnInvestedCapitalPercent")
    public Double getReturnOnInvestedCapitalPercent() {
        return returnOnInvestedCapitalPercent;
    }

    @JsonProperty("returnOnInvestedCapitalPercent")
    public void setReturnOnInvestedCapitalPercent(Double returnOnInvestedCapitalPercent) {
        this.returnOnInvestedCapitalPercent = returnOnInvestedCapitalPercent;
    }

    @JsonProperty("taxRatePercent")
    public Double getTaxRatePercent() {
        return taxRatePercent;
    }

    @JsonProperty("taxRatePercent")
    public void setTaxRatePercent(Double taxRatePercent) {
        this.taxRatePercent = taxRatePercent;
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
        sb.append(ProfitabilitySection.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("assetTurnoverOnAverage");
        sb.append('=');
        sb.append(((this.assetTurnoverOnAverage == null)?"<null>":this.assetTurnoverOnAverage));
        sb.append(',');
        sb.append("financialLeverageOnAverage");
        sb.append('=');
        sb.append(((this.financialLeverageOnAverage == null)?"<null>":this.financialLeverageOnAverage));
        sb.append(',');
        sb.append("interestCoverage");
        sb.append('=');
        sb.append(((this.interestCoverage == null)?"<null>":this.interestCoverage));
        sb.append(',');
        sb.append("netMarginPercent");
        sb.append('=');
        sb.append(((this.netMarginPercent == null)?"<null>":this.netMarginPercent));
        sb.append(',');
        sb.append("returnOnAssetsPercent");
        sb.append('=');
        sb.append(((this.returnOnAssetsPercent == null)?"<null>":this.returnOnAssetsPercent));
        sb.append(',');
        sb.append("returnOnEquityPercent");
        sb.append('=');
        sb.append(((this.returnOnEquityPercent == null)?"<null>":this.returnOnEquityPercent));
        sb.append(',');
        sb.append("returnOnInvestedCapitalPercent");
        sb.append('=');
        sb.append(((this.returnOnInvestedCapitalPercent == null)?"<null>":this.returnOnInvestedCapitalPercent));
        sb.append(',');
        sb.append("taxRatePercent");
        sb.append('=');
        sb.append(((this.taxRatePercent == null)?"<null>":this.taxRatePercent));
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
        result = ((result* 31)+((this.taxRatePercent == null)? 0 :this.taxRatePercent.hashCode()));
        result = ((result* 31)+((this.netMarginPercent == null)? 0 :this.netMarginPercent.hashCode()));
        result = ((result* 31)+((this.interestCoverage == null)? 0 :this.interestCoverage.hashCode()));
        result = ((result* 31)+((this.assetTurnoverOnAverage == null)? 0 :this.assetTurnoverOnAverage.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.returnOnEquityPercent == null)? 0 :this.returnOnEquityPercent.hashCode()));
        result = ((result* 31)+((this.returnOnAssetsPercent == null)? 0 :this.returnOnAssetsPercent.hashCode()));
        result = ((result* 31)+((this.returnOnInvestedCapitalPercent == null)? 0 :this.returnOnInvestedCapitalPercent.hashCode()));
        result = ((result* 31)+((this.financialLeverageOnAverage == null)? 0 :this.financialLeverageOnAverage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProfitabilitySection) == false) {
            return false;
        }
        ProfitabilitySection rhs = ((ProfitabilitySection) other);
        return ((((((((((this.taxRatePercent == rhs.taxRatePercent)||((this.taxRatePercent!= null)&&this.taxRatePercent.equals(rhs.taxRatePercent)))&&((this.netMarginPercent == rhs.netMarginPercent)||((this.netMarginPercent!= null)&&this.netMarginPercent.equals(rhs.netMarginPercent))))&&((this.interestCoverage == rhs.interestCoverage)||((this.interestCoverage!= null)&&this.interestCoverage.equals(rhs.interestCoverage))))&&((this.assetTurnoverOnAverage == rhs.assetTurnoverOnAverage)||((this.assetTurnoverOnAverage!= null)&&this.assetTurnoverOnAverage.equals(rhs.assetTurnoverOnAverage))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.returnOnEquityPercent == rhs.returnOnEquityPercent)||((this.returnOnEquityPercent!= null)&&this.returnOnEquityPercent.equals(rhs.returnOnEquityPercent))))&&((this.returnOnAssetsPercent == rhs.returnOnAssetsPercent)||((this.returnOnAssetsPercent!= null)&&this.returnOnAssetsPercent.equals(rhs.returnOnAssetsPercent))))&&((this.returnOnInvestedCapitalPercent == rhs.returnOnInvestedCapitalPercent)||((this.returnOnInvestedCapitalPercent!= null)&&this.returnOnInvestedCapitalPercent.equals(rhs.returnOnInvestedCapitalPercent))))&&((this.financialLeverageOnAverage == rhs.financialLeverageOnAverage)||((this.financialLeverageOnAverage!= null)&&this.financialLeverageOnAverage.equals(rhs.financialLeverageOnAverage))));
    }

}
