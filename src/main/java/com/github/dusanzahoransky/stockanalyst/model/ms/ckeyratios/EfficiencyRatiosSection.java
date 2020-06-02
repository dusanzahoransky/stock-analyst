
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
    "assetTurnover",
    "cashConversionCycle",
    "daysInventory",
    "daysSalesOutstanding",
    "fixedAssetsTurnover",
    "inventoryTurnover",
    "payablesPeriod",
    "receivablesTurnover"
})
public class EfficiencyRatiosSection {

    @JsonProperty("assetTurnover")
    private Double assetTurnover;
    @JsonProperty("cashConversionCycle")
    private Double cashConversionCycle;
    @JsonProperty("daysInventory")
    private Double daysInventory;
    @JsonProperty("daysSalesOutstanding")
    private Double daysSalesOutstanding;
    @JsonProperty("fixedAssetsTurnover")
    private Double fixedAssetsTurnover;
    @JsonProperty("inventoryTurnover")
    private Double inventoryTurnover;
    @JsonProperty("payablesPeriod")
    private Double payablesPeriod;
    @JsonProperty("receivablesTurnover")
    private Double receivablesTurnover;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("assetTurnover")
    public Double getAssetTurnover() {
        return assetTurnover;
    }

    @JsonProperty("assetTurnover")
    public void setAssetTurnover(Double assetTurnover) {
        this.assetTurnover = assetTurnover;
    }

    @JsonProperty("cashConversionCycle")
    public Double getCashConversionCycle() {
        return cashConversionCycle;
    }

    @JsonProperty("cashConversionCycle")
    public void setCashConversionCycle(Double cashConversionCycle) {
        this.cashConversionCycle = cashConversionCycle;
    }

    @JsonProperty("daysInventory")
    public Double getDaysInventory() {
        return daysInventory;
    }

    @JsonProperty("daysInventory")
    public void setDaysInventory(Double daysInventory) {
        this.daysInventory = daysInventory;
    }

    @JsonProperty("daysSalesOutstanding")
    public Double getDaysSalesOutstanding() {
        return daysSalesOutstanding;
    }

    @JsonProperty("daysSalesOutstanding")
    public void setDaysSalesOutstanding(Double daysSalesOutstanding) {
        this.daysSalesOutstanding = daysSalesOutstanding;
    }

    @JsonProperty("fixedAssetsTurnover")
    public Double getFixedAssetsTurnover() {
        return fixedAssetsTurnover;
    }

    @JsonProperty("fixedAssetsTurnover")
    public void setFixedAssetsTurnover(Double fixedAssetsTurnover) {
        this.fixedAssetsTurnover = fixedAssetsTurnover;
    }

    @JsonProperty("inventoryTurnover")
    public Double getInventoryTurnover() {
        return inventoryTurnover;
    }

    @JsonProperty("inventoryTurnover")
    public void setInventoryTurnover(Double inventoryTurnover) {
        this.inventoryTurnover = inventoryTurnover;
    }

    @JsonProperty("payablesPeriod")
    public Double getPayablesPeriod() {
        return payablesPeriod;
    }

    @JsonProperty("payablesPeriod")
    public void setPayablesPeriod(Double payablesPeriod) {
        this.payablesPeriod = payablesPeriod;
    }

    @JsonProperty("receivablesTurnover")
    public Double getReceivablesTurnover() {
        return receivablesTurnover;
    }

    @JsonProperty("receivablesTurnover")
    public void setReceivablesTurnover(Double receivablesTurnover) {
        this.receivablesTurnover = receivablesTurnover;
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
        sb.append(EfficiencyRatiosSection.class.getName()).append('@').append(Long.toHexString(System.identityHashCode(this))).append('[');
        sb.append("assetTurnover");
        sb.append('=');
        sb.append(((this.assetTurnover == null)?"<null>":this.assetTurnover));
        sb.append(',');
        sb.append("cashConversionCycle");
        sb.append('=');
        sb.append(((this.cashConversionCycle == null)?"<null>":this.cashConversionCycle));
        sb.append(',');
        sb.append("daysInventory");
        sb.append('=');
        sb.append(((this.daysInventory == null)?"<null>":this.daysInventory));
        sb.append(',');
        sb.append("daysSalesOutstanding");
        sb.append('=');
        sb.append(((this.daysSalesOutstanding == null)?"<null>":this.daysSalesOutstanding));
        sb.append(',');
        sb.append("fixedAssetsTurnover");
        sb.append('=');
        sb.append(((this.fixedAssetsTurnover == null)?"<null>":this.fixedAssetsTurnover));
        sb.append(',');
        sb.append("inventoryTurnover");
        sb.append('=');
        sb.append(((this.inventoryTurnover == null)?"<null>":this.inventoryTurnover));
        sb.append(',');
        sb.append("payablesPeriod");
        sb.append('=');
        sb.append(((this.payablesPeriod == null)?"<null>":this.payablesPeriod));
        sb.append(',');
        sb.append("receivablesTurnover");
        sb.append('=');
        sb.append(((this.receivablesTurnover == null)?"<null>":this.receivablesTurnover));
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
        result = ((result* 31)+((this.assetTurnover == null)? 0 :this.assetTurnover.hashCode()));
        result = ((result* 31)+((this.fixedAssetsTurnover == null)? 0 :this.fixedAssetsTurnover.hashCode()));
        result = ((result* 31)+((this.daysInventory == null)? 0 :this.daysInventory.hashCode()));
        result = ((result* 31)+((this.cashConversionCycle == null)? 0 :this.cashConversionCycle.hashCode()));
        result = ((result* 31)+((this.payablesPeriod == null)? 0 :this.payablesPeriod.hashCode()));
        result = ((result* 31)+((this.inventoryTurnover == null)? 0 :this.inventoryTurnover.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.receivablesTurnover == null)? 0 :this.receivablesTurnover.hashCode()));
        result = ((result* 31)+((this.daysSalesOutstanding == null)? 0 :this.daysSalesOutstanding.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EfficiencyRatiosSection) == false) {
            return false;
        }
        EfficiencyRatiosSection rhs = ((EfficiencyRatiosSection) other);
        return ((((((((((this.assetTurnover == rhs.assetTurnover)||((this.assetTurnover!= null)&&this.assetTurnover.equals(rhs.assetTurnover)))&&((this.fixedAssetsTurnover == rhs.fixedAssetsTurnover)||((this.fixedAssetsTurnover!= null)&&this.fixedAssetsTurnover.equals(rhs.fixedAssetsTurnover))))&&((this.daysInventory == rhs.daysInventory)||((this.daysInventory!= null)&&this.daysInventory.equals(rhs.daysInventory))))&&((this.cashConversionCycle == rhs.cashConversionCycle)||((this.cashConversionCycle!= null)&&this.cashConversionCycle.equals(rhs.cashConversionCycle))))&&((this.payablesPeriod == rhs.payablesPeriod)||((this.payablesPeriod!= null)&&this.payablesPeriod.equals(rhs.payablesPeriod))))&&((this.inventoryTurnover == rhs.inventoryTurnover)||((this.inventoryTurnover!= null)&&this.inventoryTurnover.equals(rhs.inventoryTurnover))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.receivablesTurnover == rhs.receivablesTurnover)||((this.receivablesTurnover!= null)&&this.receivablesTurnover.equals(rhs.receivablesTurnover))))&&((this.daysSalesOutstanding == rhs.daysSalesOutstanding)||((this.daysSalesOutstanding!= null)&&this.daysSalesOutstanding.equals(rhs.daysSalesOutstanding))));
    }

}
