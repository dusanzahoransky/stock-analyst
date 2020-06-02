
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
    "accountsPayable",
    "accountsReceivable",
    "accruedLiabilities",
    "cashAndShortTermInvestments",
    "intangibles",
    "inventory",
    "longTermDebt",
    "netPpAndE",
    "otherCurrentAssets",
    "otherLongTermAssets",
    "otherLongTermLiabilities",
    "otherShortTermLiabilities",
    "shortTermDebt",
    "taxesPayable",
    "totalAssets",
    "totalCurrentAssets",
    "totalCurrentLiabilities",
    "totalLiabilities",
    "totalLiabilitiesAndEquity",
    "totalStockholdersEquity"
})
public class FinancialHealthBalanceSheetSection {

    @JsonProperty("accountsPayable")
    private Double accountsPayable;
    @JsonProperty("accountsReceivable")
    private Double accountsReceivable;
    @JsonProperty("accruedLiabilities")
    private Long accruedLiabilities;
    @JsonProperty("cashAndShortTermInvestments")
    private Double cashAndShortTermInvestments;
    @JsonProperty("intangibles")
    private Double intangibles;
    @JsonProperty("inventory")
    private Double inventory;
    @JsonProperty("longTermDebt")
    private Double longTermDebt;
    @JsonProperty("netPpAndE")
    private Double netPpAndE;
    @JsonProperty("otherCurrentAssets")
    private Double otherCurrentAssets;
    @JsonProperty("otherLongTermAssets")
    private Double otherLongTermAssets;
    @JsonProperty("otherLongTermLiabilities")
    private Double otherLongTermLiabilities;
    @JsonProperty("otherShortTermLiabilities")
    private Double otherShortTermLiabilities;
    @JsonProperty("shortTermDebt")
    private Double shortTermDebt;
    @JsonProperty("taxesPayable")
    private Double taxesPayable;
    @JsonProperty("totalAssets")
    private Double totalAssets;
    @JsonProperty("totalCurrentAssets")
    private Double totalCurrentAssets;
    @JsonProperty("totalCurrentLiabilities")
    private Double totalCurrentLiabilities;
    @JsonProperty("totalLiabilities")
    private Double totalLiabilities;
    @JsonProperty("totalLiabilitiesAndEquity")
    private Double totalLiabilitiesAndEquity;
    @JsonProperty("totalStockholdersEquity")
    private Double totalStockholdersEquity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("accountsPayable")
    public Double getAccountsPayable() {
        return accountsPayable;
    }

    @JsonProperty("accountsPayable")
    public void setAccountsPayable(Double accountsPayable) {
        this.accountsPayable = accountsPayable;
    }

    @JsonProperty("accountsReceivable")
    public Double getAccountsReceivable() {
        return accountsReceivable;
    }

    @JsonProperty("accountsReceivable")
    public void setAccountsReceivable(Double accountsReceivable) {
        this.accountsReceivable = accountsReceivable;
    }

    @JsonProperty("accruedLiabilities")
    public Long getAccruedLiabilities() {
        return accruedLiabilities;
    }

    @JsonProperty("accruedLiabilities")
    public void setAccruedLiabilities(Long accruedLiabilities) {
        this.accruedLiabilities = accruedLiabilities;
    }

    @JsonProperty("cashAndShortTermInvestments")
    public Double getCashAndShortTermInvestments() {
        return cashAndShortTermInvestments;
    }

    @JsonProperty("cashAndShortTermInvestments")
    public void setCashAndShortTermInvestments(Double cashAndShortTermInvestments) {
        this.cashAndShortTermInvestments = cashAndShortTermInvestments;
    }

    @JsonProperty("intangibles")
    public Double getIntangibles() {
        return intangibles;
    }

    @JsonProperty("intangibles")
    public void setIntangibles(Double intangibles) {
        this.intangibles = intangibles;
    }

    @JsonProperty("inventory")
    public Double getInventory() {
        return inventory;
    }

    @JsonProperty("inventory")
    public void setInventory(Double inventory) {
        this.inventory = inventory;
    }

    @JsonProperty("longTermDebt")
    public Double getLongTermDebt() {
        return longTermDebt;
    }

    @JsonProperty("longTermDebt")
    public void setLongTermDebt(Double longTermDebt) {
        this.longTermDebt = longTermDebt;
    }

    @JsonProperty("netPpAndE")
    public Double getNetPpAndE() {
        return netPpAndE;
    }

    @JsonProperty("netPpAndE")
    public void setNetPpAndE(Double netPpAndE) {
        this.netPpAndE = netPpAndE;
    }

    @JsonProperty("otherCurrentAssets")
    public Double getOtherCurrentAssets() {
        return otherCurrentAssets;
    }

    @JsonProperty("otherCurrentAssets")
    public void setOtherCurrentAssets(Double otherCurrentAssets) {
        this.otherCurrentAssets = otherCurrentAssets;
    }

    @JsonProperty("otherLongTermAssets")
    public Double getOtherLongTermAssets() {
        return otherLongTermAssets;
    }

    @JsonProperty("otherLongTermAssets")
    public void setOtherLongTermAssets(Double otherLongTermAssets) {
        this.otherLongTermAssets = otherLongTermAssets;
    }

    @JsonProperty("otherLongTermLiabilities")
    public Double getOtherLongTermLiabilities() {
        return otherLongTermLiabilities;
    }

    @JsonProperty("otherLongTermLiabilities")
    public void setOtherLongTermLiabilities(Double otherLongTermLiabilities) {
        this.otherLongTermLiabilities = otherLongTermLiabilities;
    }

    @JsonProperty("otherShortTermLiabilities")
    public Double getOtherShortTermLiabilities() {
        return otherShortTermLiabilities;
    }

    @JsonProperty("otherShortTermLiabilities")
    public void setOtherShortTermLiabilities(Double otherShortTermLiabilities) {
        this.otherShortTermLiabilities = otherShortTermLiabilities;
    }

    @JsonProperty("shortTermDebt")
    public Double getShortTermDebt() {
        return shortTermDebt;
    }

    @JsonProperty("shortTermDebt")
    public void setShortTermDebt(Double shortTermDebt) {
        this.shortTermDebt = shortTermDebt;
    }

    @JsonProperty("taxesPayable")
    public Double getTaxesPayable() {
        return taxesPayable;
    }

    @JsonProperty("taxesPayable")
    public void setTaxesPayable(Double taxesPayable) {
        this.taxesPayable = taxesPayable;
    }

    @JsonProperty("totalAssets")
    public Double getTotalAssets() {
        return totalAssets;
    }

    @JsonProperty("totalAssets")
    public void setTotalAssets(Double totalAssets) {
        this.totalAssets = totalAssets;
    }

    @JsonProperty("totalCurrentAssets")
    public Double getTotalCurrentAssets() {
        return totalCurrentAssets;
    }

    @JsonProperty("totalCurrentAssets")
    public void setTotalCurrentAssets(Double totalCurrentAssets) {
        this.totalCurrentAssets = totalCurrentAssets;
    }

    @JsonProperty("totalCurrentLiabilities")
    public Double getTotalCurrentLiabilities() {
        return totalCurrentLiabilities;
    }

    @JsonProperty("totalCurrentLiabilities")
    public void setTotalCurrentLiabilities(Double totalCurrentLiabilities) {
        this.totalCurrentLiabilities = totalCurrentLiabilities;
    }

    @JsonProperty("totalLiabilities")
    public Double getTotalLiabilities() {
        return totalLiabilities;
    }

    @JsonProperty("totalLiabilities")
    public void setTotalLiabilities(Double totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    @JsonProperty("totalLiabilitiesAndEquity")
    public Double getTotalLiabilitiesAndEquity() {
        return totalLiabilitiesAndEquity;
    }

    @JsonProperty("totalLiabilitiesAndEquity")
    public void setTotalLiabilitiesAndEquity(Double totalLiabilitiesAndEquity) {
        this.totalLiabilitiesAndEquity = totalLiabilitiesAndEquity;
    }

    @JsonProperty("totalStockholdersEquity")
    public Double getTotalStockholdersEquity() {
        return totalStockholdersEquity;
    }

    @JsonProperty("totalStockholdersEquity")
    public void setTotalStockholdersEquity(Double totalStockholdersEquity) {
        this.totalStockholdersEquity = totalStockholdersEquity;
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
        sb.append(FinancialHealthBalanceSheetSection.class.getName()).append('@').append(Long.toHexString(System.identityHashCode(this))).append('[');
        sb.append("accountsPayable");
        sb.append('=');
        sb.append(((this.accountsPayable == null)?"<null>":this.accountsPayable));
        sb.append(',');
        sb.append("accountsReceivable");
        sb.append('=');
        sb.append(((this.accountsReceivable == null)?"<null>":this.accountsReceivable));
        sb.append(',');
        sb.append("accruedLiabilities");
        sb.append('=');
        sb.append(((this.accruedLiabilities == null)?"<null>":this.accruedLiabilities));
        sb.append(',');
        sb.append("cashAndShortTermInvestments");
        sb.append('=');
        sb.append(((this.cashAndShortTermInvestments == null)?"<null>":this.cashAndShortTermInvestments));
        sb.append(',');
        sb.append("intangibles");
        sb.append('=');
        sb.append(((this.intangibles == null)?"<null>":this.intangibles));
        sb.append(',');
        sb.append("inventory");
        sb.append('=');
        sb.append(((this.inventory == null)?"<null>":this.inventory));
        sb.append(',');
        sb.append("longTermDebt");
        sb.append('=');
        sb.append(((this.longTermDebt == null)?"<null>":this.longTermDebt));
        sb.append(',');
        sb.append("netPpAndE");
        sb.append('=');
        sb.append(((this.netPpAndE == null)?"<null>":this.netPpAndE));
        sb.append(',');
        sb.append("otherCurrentAssets");
        sb.append('=');
        sb.append(((this.otherCurrentAssets == null)?"<null>":this.otherCurrentAssets));
        sb.append(',');
        sb.append("otherLongTermAssets");
        sb.append('=');
        sb.append(((this.otherLongTermAssets == null)?"<null>":this.otherLongTermAssets));
        sb.append(',');
        sb.append("otherLongTermLiabilities");
        sb.append('=');
        sb.append(((this.otherLongTermLiabilities == null)?"<null>":this.otherLongTermLiabilities));
        sb.append(',');
        sb.append("otherShortTermLiabilities");
        sb.append('=');
        sb.append(((this.otherShortTermLiabilities == null)?"<null>":this.otherShortTermLiabilities));
        sb.append(',');
        sb.append("shortTermDebt");
        sb.append('=');
        sb.append(((this.shortTermDebt == null)?"<null>":this.shortTermDebt));
        sb.append(',');
        sb.append("taxesPayable");
        sb.append('=');
        sb.append(((this.taxesPayable == null)?"<null>":this.taxesPayable));
        sb.append(',');
        sb.append("totalAssets");
        sb.append('=');
        sb.append(((this.totalAssets == null)?"<null>":this.totalAssets));
        sb.append(',');
        sb.append("totalCurrentAssets");
        sb.append('=');
        sb.append(((this.totalCurrentAssets == null)?"<null>":this.totalCurrentAssets));
        sb.append(',');
        sb.append("totalCurrentLiabilities");
        sb.append('=');
        sb.append(((this.totalCurrentLiabilities == null)?"<null>":this.totalCurrentLiabilities));
        sb.append(',');
        sb.append("totalLiabilities");
        sb.append('=');
        sb.append(((this.totalLiabilities == null)?"<null>":this.totalLiabilities));
        sb.append(',');
        sb.append("totalLiabilitiesAndEquity");
        sb.append('=');
        sb.append(((this.totalLiabilitiesAndEquity == null)?"<null>":this.totalLiabilitiesAndEquity));
        sb.append(',');
        sb.append("totalStockholdersEquity");
        sb.append('=');
        sb.append(((this.totalStockholdersEquity == null)?"<null>":this.totalStockholdersEquity));
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
        result = ((result* 31)+((this.intangibles == null)? 0 :this.intangibles.hashCode()));
        result = ((result* 31)+((this.otherLongTermAssets == null)? 0 :this.otherLongTermAssets.hashCode()));
        result = ((result* 31)+((this.taxesPayable == null)? 0 :this.taxesPayable.hashCode()));
        result = ((result* 31)+((this.totalAssets == null)? 0 :this.totalAssets.hashCode()));
        result = ((result* 31)+((this.otherShortTermLiabilities == null)? 0 :this.otherShortTermLiabilities.hashCode()));
        result = ((result* 31)+((this.totalCurrentAssets == null)? 0 :this.totalCurrentAssets.hashCode()));
        result = ((result* 31)+((this.cashAndShortTermInvestments == null)? 0 :this.cashAndShortTermInvestments.hashCode()));
        result = ((result* 31)+((this.longTermDebt == null)? 0 :this.longTermDebt.hashCode()));
        result = ((result* 31)+((this.totalLiabilities == null)? 0 :this.totalLiabilities.hashCode()));
        result = ((result* 31)+((this.totalCurrentLiabilities == null)? 0 :this.totalCurrentLiabilities.hashCode()));
        result = ((result* 31)+((this.inventory == null)? 0 :this.inventory.hashCode()));
        result = ((result* 31)+((this.totalStockholdersEquity == null)? 0 :this.totalStockholdersEquity.hashCode()));
        result = ((result* 31)+((this.otherCurrentAssets == null)? 0 :this.otherCurrentAssets.hashCode()));
        result = ((result* 31)+((this.accruedLiabilities == null)? 0 :this.accruedLiabilities.hashCode()));
        result = ((result* 31)+((this.otherLongTermLiabilities == null)? 0 :this.otherLongTermLiabilities.hashCode()));
        result = ((result* 31)+((this.accountsReceivable == null)? 0 :this.accountsReceivable.hashCode()));
        result = ((result* 31)+((this.shortTermDebt == null)? 0 :this.shortTermDebt.hashCode()));
        result = ((result* 31)+((this.totalLiabilitiesAndEquity == null)? 0 :this.totalLiabilitiesAndEquity.hashCode()));
        result = ((result* 31)+((this.netPpAndE == null)? 0 :this.netPpAndE.hashCode()));
        result = ((result* 31)+((this.accountsPayable == null)? 0 :this.accountsPayable.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FinancialHealthBalanceSheetSection) == false) {
            return false;
        }
        FinancialHealthBalanceSheetSection rhs = ((FinancialHealthBalanceSheetSection) other);
        return ((((((((((((((((((((((this.intangibles == rhs.intangibles)||((this.intangibles!= null)&&this.intangibles.equals(rhs.intangibles)))&&((this.otherLongTermAssets == rhs.otherLongTermAssets)||((this.otherLongTermAssets!= null)&&this.otherLongTermAssets.equals(rhs.otherLongTermAssets))))&&((this.taxesPayable == rhs.taxesPayable)||((this.taxesPayable!= null)&&this.taxesPayable.equals(rhs.taxesPayable))))&&((this.totalAssets == rhs.totalAssets)||((this.totalAssets!= null)&&this.totalAssets.equals(rhs.totalAssets))))&&((this.otherShortTermLiabilities == rhs.otherShortTermLiabilities)||((this.otherShortTermLiabilities!= null)&&this.otherShortTermLiabilities.equals(rhs.otherShortTermLiabilities))))&&((this.totalCurrentAssets == rhs.totalCurrentAssets)||((this.totalCurrentAssets!= null)&&this.totalCurrentAssets.equals(rhs.totalCurrentAssets))))&&((this.cashAndShortTermInvestments == rhs.cashAndShortTermInvestments)||((this.cashAndShortTermInvestments!= null)&&this.cashAndShortTermInvestments.equals(rhs.cashAndShortTermInvestments))))&&((this.longTermDebt == rhs.longTermDebt)||((this.longTermDebt!= null)&&this.longTermDebt.equals(rhs.longTermDebt))))&&((this.totalLiabilities == rhs.totalLiabilities)||((this.totalLiabilities!= null)&&this.totalLiabilities.equals(rhs.totalLiabilities))))&&((this.totalCurrentLiabilities == rhs.totalCurrentLiabilities)||((this.totalCurrentLiabilities!= null)&&this.totalCurrentLiabilities.equals(rhs.totalCurrentLiabilities))))&&((this.inventory == rhs.inventory)||((this.inventory!= null)&&this.inventory.equals(rhs.inventory))))&&((this.totalStockholdersEquity == rhs.totalStockholdersEquity)||((this.totalStockholdersEquity!= null)&&this.totalStockholdersEquity.equals(rhs.totalStockholdersEquity))))&&((this.otherCurrentAssets == rhs.otherCurrentAssets)||((this.otherCurrentAssets!= null)&&this.otherCurrentAssets.equals(rhs.otherCurrentAssets))))&&((this.accruedLiabilities == rhs.accruedLiabilities)||((this.accruedLiabilities!= null)&&this.accruedLiabilities.equals(rhs.accruedLiabilities))))&&((this.otherLongTermLiabilities == rhs.otherLongTermLiabilities)||((this.otherLongTermLiabilities!= null)&&this.otherLongTermLiabilities.equals(rhs.otherLongTermLiabilities))))&&((this.accountsReceivable == rhs.accountsReceivable)||((this.accountsReceivable!= null)&&this.accountsReceivable.equals(rhs.accountsReceivable))))&&((this.shortTermDebt == rhs.shortTermDebt)||((this.shortTermDebt!= null)&&this.shortTermDebt.equals(rhs.shortTermDebt))))&&((this.totalLiabilitiesAndEquity == rhs.totalLiabilitiesAndEquity)||((this.totalLiabilitiesAndEquity!= null)&&this.totalLiabilitiesAndEquity.equals(rhs.totalLiabilitiesAndEquity))))&&((this.netPpAndE == rhs.netPpAndE)||((this.netPpAndE!= null)&&this.netPpAndE.equals(rhs.netPpAndE))))&&((this.accountsPayable == rhs.accountsPayable)||((this.accountsPayable!= null)&&this.accountsPayable.equals(rhs.accountsPayable))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
