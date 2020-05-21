
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
    "marginsPercentageOfSalesSection",
    "profitabilitySection",
    "growthSection",
    "cashflowRatiosSection",
    "financialHealthBalanceSheetSection",
    "financialHealthLiquiditySection",
    "efficiencyRatiosSection"
})
public class KeyRatioStatisticsSection {

    @JsonProperty("marginsPercentageOfSalesSection")
    private MarginsPercentageOfSalesSection marginsPercentageOfSalesSection;
    @JsonProperty("profitabilitySection")
    private ProfitabilitySection profitabilitySection;
    @JsonProperty("growthSection")
    private GrowthSection growthSection;
    @JsonProperty("cashflowRatiosSection")
    private CashflowRatiosSection cashflowRatiosSection;
    @JsonProperty("financialHealthBalanceSheetSection")
    private FinancialHealthBalanceSheetSection financialHealthBalanceSheetSection;
    @JsonProperty("financialHealthLiquiditySection")
    private FinancialHealthLiquiditySection financialHealthLiquiditySection;
    @JsonProperty("efficiencyRatiosSection")
    private EfficiencyRatiosSection efficiencyRatiosSection;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("marginsPercentageOfSalesSection")
    public MarginsPercentageOfSalesSection getMarginsPercentageOfSalesSection() {
        return marginsPercentageOfSalesSection;
    }

    @JsonProperty("marginsPercentageOfSalesSection")
    public void setMarginsPercentageOfSalesSection(MarginsPercentageOfSalesSection marginsPercentageOfSalesSection) {
        this.marginsPercentageOfSalesSection = marginsPercentageOfSalesSection;
    }

    @JsonProperty("profitabilitySection")
    public ProfitabilitySection getProfitabilitySection() {
        return profitabilitySection;
    }

    @JsonProperty("profitabilitySection")
    public void setProfitabilitySection(ProfitabilitySection profitabilitySection) {
        this.profitabilitySection = profitabilitySection;
    }

    @JsonProperty("growthSection")
    public GrowthSection getGrowthSection() {
        return growthSection;
    }

    @JsonProperty("growthSection")
    public void setGrowthSection(GrowthSection growthSection) {
        this.growthSection = growthSection;
    }

    @JsonProperty("cashflowRatiosSection")
    public CashflowRatiosSection getCashflowRatiosSection() {
        return cashflowRatiosSection;
    }

    @JsonProperty("cashflowRatiosSection")
    public void setCashflowRatiosSection(CashflowRatiosSection cashflowRatiosSection) {
        this.cashflowRatiosSection = cashflowRatiosSection;
    }

    @JsonProperty("financialHealthBalanceSheetSection")
    public FinancialHealthBalanceSheetSection getFinancialHealthBalanceSheetSection() {
        return financialHealthBalanceSheetSection;
    }

    @JsonProperty("financialHealthBalanceSheetSection")
    public void setFinancialHealthBalanceSheetSection(FinancialHealthBalanceSheetSection financialHealthBalanceSheetSection) {
        this.financialHealthBalanceSheetSection = financialHealthBalanceSheetSection;
    }

    @JsonProperty("financialHealthLiquiditySection")
    public FinancialHealthLiquiditySection getFinancialHealthLiquiditySection() {
        return financialHealthLiquiditySection;
    }

    @JsonProperty("financialHealthLiquiditySection")
    public void setFinancialHealthLiquiditySection(FinancialHealthLiquiditySection financialHealthLiquiditySection) {
        this.financialHealthLiquiditySection = financialHealthLiquiditySection;
    }

    @JsonProperty("efficiencyRatiosSection")
    public EfficiencyRatiosSection getEfficiencyRatiosSection() {
        return efficiencyRatiosSection;
    }

    @JsonProperty("efficiencyRatiosSection")
    public void setEfficiencyRatiosSection(EfficiencyRatiosSection efficiencyRatiosSection) {
        this.efficiencyRatiosSection = efficiencyRatiosSection;
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
        sb.append(KeyRatioStatisticsSection.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("marginsPercentageOfSalesSection");
        sb.append('=');
        sb.append(((this.marginsPercentageOfSalesSection == null)?"<null>":this.marginsPercentageOfSalesSection));
        sb.append(',');
        sb.append("profitabilitySection");
        sb.append('=');
        sb.append(((this.profitabilitySection == null)?"<null>":this.profitabilitySection));
        sb.append(',');
        sb.append("growthSection");
        sb.append('=');
        sb.append(((this.growthSection == null)?"<null>":this.growthSection));
        sb.append(',');
        sb.append("cashflowRatiosSection");
        sb.append('=');
        sb.append(((this.cashflowRatiosSection == null)?"<null>":this.cashflowRatiosSection));
        sb.append(',');
        sb.append("financialHealthBalanceSheetSection");
        sb.append('=');
        sb.append(((this.financialHealthBalanceSheetSection == null)?"<null>":this.financialHealthBalanceSheetSection));
        sb.append(',');
        sb.append("financialHealthLiquiditySection");
        sb.append('=');
        sb.append(((this.financialHealthLiquiditySection == null)?"<null>":this.financialHealthLiquiditySection));
        sb.append(',');
        sb.append("efficiencyRatiosSection");
        sb.append('=');
        sb.append(((this.efficiencyRatiosSection == null)?"<null>":this.efficiencyRatiosSection));
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
        result = ((result* 31)+((this.profitabilitySection == null)? 0 :this.profitabilitySection.hashCode()));
        result = ((result* 31)+((this.cashflowRatiosSection == null)? 0 :this.cashflowRatiosSection.hashCode()));
        result = ((result* 31)+((this.marginsPercentageOfSalesSection == null)? 0 :this.marginsPercentageOfSalesSection.hashCode()));
        result = ((result* 31)+((this.growthSection == null)? 0 :this.growthSection.hashCode()));
        result = ((result* 31)+((this.financialHealthLiquiditySection == null)? 0 :this.financialHealthLiquiditySection.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.financialHealthBalanceSheetSection == null)? 0 :this.financialHealthBalanceSheetSection.hashCode()));
        result = ((result* 31)+((this.efficiencyRatiosSection == null)? 0 :this.efficiencyRatiosSection.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof KeyRatioStatisticsSection) == false) {
            return false;
        }
        KeyRatioStatisticsSection rhs = ((KeyRatioStatisticsSection) other);
        return (((((((((this.profitabilitySection == rhs.profitabilitySection)||((this.profitabilitySection!= null)&&this.profitabilitySection.equals(rhs.profitabilitySection)))&&((this.cashflowRatiosSection == rhs.cashflowRatiosSection)||((this.cashflowRatiosSection!= null)&&this.cashflowRatiosSection.equals(rhs.cashflowRatiosSection))))&&((this.marginsPercentageOfSalesSection == rhs.marginsPercentageOfSalesSection)||((this.marginsPercentageOfSalesSection!= null)&&this.marginsPercentageOfSalesSection.equals(rhs.marginsPercentageOfSalesSection))))&&((this.growthSection == rhs.growthSection)||((this.growthSection!= null)&&this.growthSection.equals(rhs.growthSection))))&&((this.financialHealthLiquiditySection == rhs.financialHealthLiquiditySection)||((this.financialHealthLiquiditySection!= null)&&this.financialHealthLiquiditySection.equals(rhs.financialHealthLiquiditySection))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.financialHealthBalanceSheetSection == rhs.financialHealthBalanceSheetSection)||((this.financialHealthBalanceSheetSection!= null)&&this.financialHealthBalanceSheetSection.equals(rhs.financialHealthBalanceSheetSection))))&&((this.efficiencyRatiosSection == rhs.efficiencyRatiosSection)||((this.efficiencyRatiosSection!= null)&&this.efficiencyRatiosSection.equals(rhs.efficiencyRatiosSection))));
    }

}
