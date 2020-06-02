
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
    "bookValuePerShare",
    "capSpending",
    "dividends",
    "earningsPerShare",
    "freeCashFlow",
    "freeCashFlowPerShare",
    "grossMarginPercent",
    "netIncome",
    "operatingCashFlow",
    "operatingIncome",
    "operatingMarginPercent",
    "payoutRatioPercent",
    "revenue",
    "shares",
    "workingCapital"
})
public class KeyRatioFinancialsSection {

    @JsonProperty("bookValuePerShare")
    private Double bookValuePerShare;
    @JsonProperty("capSpending")
    private Long capSpending;
    @JsonProperty("dividends")
    private Double dividends;
    @JsonProperty("earningsPerShare")
    private Double earningsPerShare;
    @JsonProperty("freeCashFlow")
    private Long freeCashFlow;
    @JsonProperty("freeCashFlowPerShare")
    private Double freeCashFlowPerShare;
    @JsonProperty("grossMarginPercent")
    private Double grossMarginPercent;
    @JsonProperty("netIncome")
    private Long netIncome;
    @JsonProperty("operatingCashFlow")
    private Long operatingCashFlow;
    @JsonProperty("operatingIncome")
    private Long operatingIncome;
    @JsonProperty("operatingMarginPercent")
    private Double operatingMarginPercent;
    @JsonProperty("payoutRatioPercent")
    private Double payoutRatioPercent;
    @JsonProperty("revenue")
    private Long revenue;
    @JsonProperty("shares")
    private Long shares;
    @JsonProperty("workingCapital")
    private Long workingCapital;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bookValuePerShare")
    public Double getBookValuePerShare() {
        return bookValuePerShare;
    }

    @JsonProperty("bookValuePerShare")
    public void setBookValuePerShare(Double bookValuePerShare) {
        this.bookValuePerShare = bookValuePerShare;
    }

    @JsonProperty("capSpending")
    public Long getCapSpending() {
        return capSpending;
    }

    @JsonProperty("capSpending")
    public void setCapSpending(Long capSpending) {
        this.capSpending = capSpending;
    }

    @JsonProperty("dividends")
    public Double getDividends() {
        return dividends;
    }

    @JsonProperty("dividends")
    public void setDividends(Double dividends) {
        this.dividends = dividends;
    }

    @JsonProperty("earningsPerShare")
    public Double getEarningsPerShare() {
        return earningsPerShare;
    }

    @JsonProperty("earningsPerShare")
    public void setEarningsPerShare(Double earningsPerShare) {
        this.earningsPerShare = earningsPerShare;
    }

    @JsonProperty("freeCashFlow")
    public Long getFreeCashFlow() {
        return freeCashFlow;
    }

    @JsonProperty("freeCashFlow")
    public void setFreeCashFlow(Long freeCashFlow) {
        this.freeCashFlow = freeCashFlow;
    }

    @JsonProperty("freeCashFlowPerShare")
    public Double getFreeCashFlowPerShare() {
        return freeCashFlowPerShare;
    }

    @JsonProperty("freeCashFlowPerShare")
    public void setFreeCashFlowPerShare(Double freeCashFlowPerShare) {
        this.freeCashFlowPerShare = freeCashFlowPerShare;
    }

    @JsonProperty("grossMarginPercent")
    public Double getGrossMarginPercent() {
        return grossMarginPercent;
    }

    @JsonProperty("grossMarginPercent")
    public void setGrossMarginPercent(Double grossMarginPercent) {
        this.grossMarginPercent = grossMarginPercent;
    }

    @JsonProperty("netIncome")
    public Long getNetIncome() {
        return netIncome;
    }

    @JsonProperty("netIncome")
    public void setNetIncome(Long netIncome) {
        this.netIncome = netIncome;
    }

    @JsonProperty("operatingCashFlow")
    public Long getOperatingCashFlow() {
        return operatingCashFlow;
    }

    @JsonProperty("operatingCashFlow")
    public void setOperatingCashFlow(Long operatingCashFlow) {
        this.operatingCashFlow = operatingCashFlow;
    }

    @JsonProperty("operatingIncome")
    public Long getOperatingIncome() {
        return operatingIncome;
    }

    @JsonProperty("operatingIncome")
    public void setOperatingIncome(Long operatingIncome) {
        this.operatingIncome = operatingIncome;
    }

    @JsonProperty("operatingMarginPercent")
    public Double getOperatingMarginPercent() {
        return operatingMarginPercent;
    }

    @JsonProperty("operatingMarginPercent")
    public void setOperatingMarginPercent(Double operatingMarginPercent) {
        this.operatingMarginPercent = operatingMarginPercent;
    }

    @JsonProperty("payoutRatioPercent")
    public Double getPayoutRatioPercent() {
        return payoutRatioPercent;
    }

    @JsonProperty("payoutRatioPercent")
    public void setPayoutRatioPercent(Double payoutRatioPercent) {
        this.payoutRatioPercent = payoutRatioPercent;
    }

    @JsonProperty("revenue")
    public Long getRevenue() {
        return revenue;
    }

    @JsonProperty("revenue")
    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    @JsonProperty("shares")
    public Long getShares() {
        return shares;
    }

    @JsonProperty("shares")
    public void setShares(Long shares) {
        this.shares = shares;
    }

    @JsonProperty("workingCapital")
    public Long getWorkingCapital() {
        return workingCapital;
    }

    @JsonProperty("workingCapital")
    public void setWorkingCapital(Long workingCapital) {
        this.workingCapital = workingCapital;
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
        sb.append(KeyRatioFinancialsSection.class.getName()).append('@').append(Long.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bookValuePerShare");
        sb.append('=');
        sb.append(((this.bookValuePerShare == null)?"<null>":this.bookValuePerShare));
        sb.append(',');
        sb.append("capSpending");
        sb.append('=');
        sb.append(((this.capSpending == null)?"<null>":this.capSpending));
        sb.append(',');
        sb.append("dividends");
        sb.append('=');
        sb.append(((this.dividends == null)?"<null>":this.dividends));
        sb.append(',');
        sb.append("earningsPerShare");
        sb.append('=');
        sb.append(((this.earningsPerShare == null)?"<null>":this.earningsPerShare));
        sb.append(',');
        sb.append("freeCashFlow");
        sb.append('=');
        sb.append(((this.freeCashFlow == null)?"<null>":this.freeCashFlow));
        sb.append(',');
        sb.append("freeCashFlowPerShare");
        sb.append('=');
        sb.append(((this.freeCashFlowPerShare == null)?"<null>":this.freeCashFlowPerShare));
        sb.append(',');
        sb.append("grossMarginPercent");
        sb.append('=');
        sb.append(((this.grossMarginPercent == null)?"<null>":this.grossMarginPercent));
        sb.append(',');
        sb.append("netIncome");
        sb.append('=');
        sb.append(((this.netIncome == null)?"<null>":this.netIncome));
        sb.append(',');
        sb.append("operatingCashFlow");
        sb.append('=');
        sb.append(((this.operatingCashFlow == null)?"<null>":this.operatingCashFlow));
        sb.append(',');
        sb.append("operatingIncome");
        sb.append('=');
        sb.append(((this.operatingIncome == null)?"<null>":this.operatingIncome));
        sb.append(',');
        sb.append("operatingMarginPercent");
        sb.append('=');
        sb.append(((this.operatingMarginPercent == null)?"<null>":this.operatingMarginPercent));
        sb.append(',');
        sb.append("payoutRatioPercent");
        sb.append('=');
        sb.append(((this.payoutRatioPercent == null)?"<null>":this.payoutRatioPercent));
        sb.append(',');
        sb.append("revenue");
        sb.append('=');
        sb.append(((this.revenue == null)?"<null>":this.revenue));
        sb.append(',');
        sb.append("shares");
        sb.append('=');
        sb.append(((this.shares == null)?"<null>":this.shares));
        sb.append(',');
        sb.append("workingCapital");
        sb.append('=');
        sb.append(((this.workingCapital == null)?"<null>":this.workingCapital));
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
        result = ((result* 31)+((this.operatingIncome == null)? 0 :this.operatingIncome.hashCode()));
        result = ((result* 31)+((this.earningsPerShare == null)? 0 :this.earningsPerShare.hashCode()));
        result = ((result* 31)+((this.freeCashFlowPerShare == null)? 0 :this.freeCashFlowPerShare.hashCode()));
        result = ((result* 31)+((this.workingCapital == null)? 0 :this.workingCapital.hashCode()));
        result = ((result* 31)+((this.grossMarginPercent == null)? 0 :this.grossMarginPercent.hashCode()));
        result = ((result* 31)+((this.payoutRatioPercent == null)? 0 :this.payoutRatioPercent.hashCode()));
        result = ((result* 31)+((this.bookValuePerShare == null)? 0 :this.bookValuePerShare.hashCode()));
        result = ((result* 31)+((this.shares == null)? 0 :this.shares.hashCode()));
        result = ((result* 31)+((this.capSpending == null)? 0 :this.capSpending.hashCode()));
        result = ((result* 31)+((this.freeCashFlow == null)? 0 :this.freeCashFlow.hashCode()));
        result = ((result* 31)+((this.revenue == null)? 0 :this.revenue.hashCode()));
        result = ((result* 31)+((this.dividends == null)? 0 :this.dividends.hashCode()));
        result = ((result* 31)+((this.netIncome == null)? 0 :this.netIncome.hashCode()));
        result = ((result* 31)+((this.operatingCashFlow == null)? 0 :this.operatingCashFlow.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.operatingMarginPercent == null)? 0 :this.operatingMarginPercent.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof KeyRatioFinancialsSection) == false) {
            return false;
        }
        KeyRatioFinancialsSection rhs = ((KeyRatioFinancialsSection) other);
        return (((((((((((((((((this.operatingIncome == rhs.operatingIncome)||((this.operatingIncome!= null)&&this.operatingIncome.equals(rhs.operatingIncome)))&&((this.earningsPerShare == rhs.earningsPerShare)||((this.earningsPerShare!= null)&&this.earningsPerShare.equals(rhs.earningsPerShare))))&&((this.freeCashFlowPerShare == rhs.freeCashFlowPerShare)||((this.freeCashFlowPerShare!= null)&&this.freeCashFlowPerShare.equals(rhs.freeCashFlowPerShare))))&&((this.workingCapital == rhs.workingCapital)||((this.workingCapital!= null)&&this.workingCapital.equals(rhs.workingCapital))))&&((this.grossMarginPercent == rhs.grossMarginPercent)||((this.grossMarginPercent!= null)&&this.grossMarginPercent.equals(rhs.grossMarginPercent))))&&((this.payoutRatioPercent == rhs.payoutRatioPercent)||((this.payoutRatioPercent!= null)&&this.payoutRatioPercent.equals(rhs.payoutRatioPercent))))&&((this.bookValuePerShare == rhs.bookValuePerShare)||((this.bookValuePerShare!= null)&&this.bookValuePerShare.equals(rhs.bookValuePerShare))))&&((this.shares == rhs.shares)||((this.shares!= null)&&this.shares.equals(rhs.shares))))&&((this.capSpending == rhs.capSpending)||((this.capSpending!= null)&&this.capSpending.equals(rhs.capSpending))))&&((this.freeCashFlow == rhs.freeCashFlow)||((this.freeCashFlow!= null)&&this.freeCashFlow.equals(rhs.freeCashFlow))))&&((this.revenue == rhs.revenue)||((this.revenue!= null)&&this.revenue.equals(rhs.revenue))))&&((this.dividends == rhs.dividends)||((this.dividends!= null)&&this.dividends.equals(rhs.dividends))))&&((this.netIncome == rhs.netIncome)||((this.netIncome!= null)&&this.netIncome.equals(rhs.netIncome))))&&((this.operatingCashFlow == rhs.operatingCashFlow)||((this.operatingCashFlow!= null)&&this.operatingCashFlow.equals(rhs.operatingCashFlow))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.operatingMarginPercent == rhs.operatingMarginPercent)||((this.operatingMarginPercent!= null)&&this.operatingMarginPercent.equals(rhs.operatingMarginPercent))));
    }

}
