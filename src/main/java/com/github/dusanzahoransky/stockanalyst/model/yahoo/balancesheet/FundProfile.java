
package com.github.dusanzahoransky.stockanalyst.model.yahoo.balancesheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "initInvestment",
    "family",
    "categoryName",
    "initAipInvestment",
    "subseqIraInvestment",
    "brokerages",
    "managementInfo",
    "subseqInvestment",
    "legalType",
    "styleBoxUrl",
    "feesExpensesInvestment",
    "maxAge",
    "feesExpensesInvestmentCat",
    "initIraInvestment",
    "subseqAipInvestment"
})
public class FundProfile {

    @JsonProperty("initInvestment")
    private InitInvestment initInvestment;
    @JsonProperty("family")
    private String family;
    @JsonProperty("categoryName")
    private Object categoryName;
    @JsonProperty("initAipInvestment")
    private InitAipInvestment initAipInvestment;
    @JsonProperty("subseqIraInvestment")
    private SubseqIraInvestment subseqIraInvestment;
    @JsonProperty("brokerages")
    private List<Object> brokerages = null;
    @JsonProperty("managementInfo")
    private ManagementInfo managementInfo;
    @JsonProperty("subseqInvestment")
    private SubseqInvestment subseqInvestment;
    @JsonProperty("legalType")
    private String legalType;
    @JsonProperty("styleBoxUrl")
    private String styleBoxUrl;
    @JsonProperty("feesExpensesInvestment")
    private FeesExpensesInvestment feesExpensesInvestment;
    @JsonProperty("maxAge")
    private Integer maxAge;
    @JsonProperty("feesExpensesInvestmentCat")
    private FeesExpensesInvestmentCat feesExpensesInvestmentCat;
    @JsonProperty("initIraInvestment")
    private InitIraInvestment initIraInvestment;
    @JsonProperty("subseqAipInvestment")
    private SubseqAipInvestment subseqAipInvestment;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("initInvestment")
    public InitInvestment getInitInvestment() {
        return initInvestment;
    }

    @JsonProperty("initInvestment")
    public void setInitInvestment(InitInvestment initInvestment) {
        this.initInvestment = initInvestment;
    }

    @JsonProperty("family")
    public String getFamily() {
        return family;
    }

    @JsonProperty("family")
    public void setFamily(String family) {
        this.family = family;
    }

    @JsonProperty("categoryName")
    public Object getCategoryName() {
        return categoryName;
    }

    @JsonProperty("categoryName")
    public void setCategoryName(Object categoryName) {
        this.categoryName = categoryName;
    }

    @JsonProperty("initAipInvestment")
    public InitAipInvestment getInitAipInvestment() {
        return initAipInvestment;
    }

    @JsonProperty("initAipInvestment")
    public void setInitAipInvestment(InitAipInvestment initAipInvestment) {
        this.initAipInvestment = initAipInvestment;
    }

    @JsonProperty("subseqIraInvestment")
    public SubseqIraInvestment getSubseqIraInvestment() {
        return subseqIraInvestment;
    }

    @JsonProperty("subseqIraInvestment")
    public void setSubseqIraInvestment(SubseqIraInvestment subseqIraInvestment) {
        this.subseqIraInvestment = subseqIraInvestment;
    }

    @JsonProperty("brokerages")
    public List<Object> getBrokerages() {
        return brokerages;
    }

    @JsonProperty("brokerages")
    public void setBrokerages(List<Object> brokerages) {
        this.brokerages = brokerages;
    }

    @JsonProperty("managementInfo")
    public ManagementInfo getManagementInfo() {
        return managementInfo;
    }

    @JsonProperty("managementInfo")
    public void setManagementInfo(ManagementInfo managementInfo) {
        this.managementInfo = managementInfo;
    }

    @JsonProperty("subseqInvestment")
    public SubseqInvestment getSubseqInvestment() {
        return subseqInvestment;
    }

    @JsonProperty("subseqInvestment")
    public void setSubseqInvestment(SubseqInvestment subseqInvestment) {
        this.subseqInvestment = subseqInvestment;
    }

    @JsonProperty("legalType")
    public String getLegalType() {
        return legalType;
    }

    @JsonProperty("legalType")
    public void setLegalType(String legalType) {
        this.legalType = legalType;
    }

    @JsonProperty("styleBoxUrl")
    public String getStyleBoxUrl() {
        return styleBoxUrl;
    }

    @JsonProperty("styleBoxUrl")
    public void setStyleBoxUrl(String styleBoxUrl) {
        this.styleBoxUrl = styleBoxUrl;
    }

    @JsonProperty("feesExpensesInvestment")
    public FeesExpensesInvestment getFeesExpensesInvestment() {
        return feesExpensesInvestment;
    }

    @JsonProperty("feesExpensesInvestment")
    public void setFeesExpensesInvestment(FeesExpensesInvestment feesExpensesInvestment) {
        this.feesExpensesInvestment = feesExpensesInvestment;
    }

    @JsonProperty("maxAge")
    public Integer getMaxAge() {
        return maxAge;
    }

    @JsonProperty("maxAge")
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    @JsonProperty("feesExpensesInvestmentCat")
    public FeesExpensesInvestmentCat getFeesExpensesInvestmentCat() {
        return feesExpensesInvestmentCat;
    }

    @JsonProperty("feesExpensesInvestmentCat")
    public void setFeesExpensesInvestmentCat(FeesExpensesInvestmentCat feesExpensesInvestmentCat) {
        this.feesExpensesInvestmentCat = feesExpensesInvestmentCat;
    }

    @JsonProperty("initIraInvestment")
    public InitIraInvestment getInitIraInvestment() {
        return initIraInvestment;
    }

    @JsonProperty("initIraInvestment")
    public void setInitIraInvestment(InitIraInvestment initIraInvestment) {
        this.initIraInvestment = initIraInvestment;
    }

    @JsonProperty("subseqAipInvestment")
    public SubseqAipInvestment getSubseqAipInvestment() {
        return subseqAipInvestment;
    }

    @JsonProperty("subseqAipInvestment")
    public void setSubseqAipInvestment(SubseqAipInvestment subseqAipInvestment) {
        this.subseqAipInvestment = subseqAipInvestment;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
