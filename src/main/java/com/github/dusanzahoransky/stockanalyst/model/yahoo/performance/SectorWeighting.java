package com.github.dusanzahoransky.stockanalyst.model.yahoo.performance;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder( {
    "realestate",
    "consumer_cyclical",
    "basic_materials",
    "consumer_defensive",
    "technology",
    "communication_services",
    "financial_services",
    "utilities",
    "industrials",
    "energy",
    "healthcare"
})
public class SectorWeighting {

    @JsonProperty("realestate")
    private Realestate realestate;
    @JsonProperty("consumer_cyclical")
    private ConsumerCyclical consumerCyclical;
    @JsonProperty("basic_materials")
    private BasicMaterials basicMaterials;
    @JsonProperty("consumer_defensive")
    private ConsumerDefensive consumerDefensive;
    @JsonProperty("technology")
    private Technology technology;
    @JsonProperty("communication_services")
    private CommunicationServices communicationServices;
    @JsonProperty("financial_services")
    private FinancialServices financialServices;
    @JsonProperty("utilities")
    private Utilities utilities;
    @JsonProperty("industrials")
    private Industrials industrials;
    @JsonProperty("energy")
    private Energy energy;
    @JsonProperty("healthcare")
    private Healthcare healthcare;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("realestate")
    public Realestate getRealestate() {
        return realestate;
    }

    @JsonProperty("realestate")
    public void setRealestate(Realestate realestate) {
        this.realestate = realestate;
    }

    @JsonProperty("consumer_cyclical")
    public ConsumerCyclical getConsumerCyclical() {
        return consumerCyclical;
    }

    @JsonProperty("consumer_cyclical")
    public void setConsumerCyclical(ConsumerCyclical consumerCyclical) {
        this.consumerCyclical = consumerCyclical;
    }

    @JsonProperty("basic_materials")
    public BasicMaterials getBasicMaterials() {
        return basicMaterials;
    }

    @JsonProperty("basic_materials")
    public void setBasicMaterials(BasicMaterials basicMaterials) {
        this.basicMaterials = basicMaterials;
    }

    @JsonProperty("consumer_defensive")
    public ConsumerDefensive getConsumerDefensive() {
        return consumerDefensive;
    }

    @JsonProperty("consumer_defensive")
    public void setConsumerDefensive(ConsumerDefensive consumerDefensive) {
        this.consumerDefensive = consumerDefensive;
    }

    @JsonProperty("technology")
    public Technology getTechnology() {
        return technology;
    }

    @JsonProperty("technology")
    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    @JsonProperty("communication_services")
    public CommunicationServices getCommunicationServices() {
        return communicationServices;
    }

    @JsonProperty("communication_services")
    public void setCommunicationServices(CommunicationServices communicationServices) {
        this.communicationServices = communicationServices;
    }

    @JsonProperty("financial_services")
    public FinancialServices getFinancialServices() {
        return financialServices;
    }

    @JsonProperty("financial_services")
    public void setFinancialServices(FinancialServices financialServices) {
        this.financialServices = financialServices;
    }

    @JsonProperty("utilities")
    public Utilities getUtilities() {
        return utilities;
    }

    @JsonProperty("utilities")
    public void setUtilities(Utilities utilities) {
        this.utilities = utilities;
    }

    @JsonProperty("industrials")
    public Industrials getIndustrials() {
        return industrials;
    }

    @JsonProperty("industrials")
    public void setIndustrials(Industrials industrials) {
        this.industrials = industrials;
    }

    @JsonProperty("energy")
    public Energy getEnergy() {
        return energy;
    }

    @JsonProperty("energy")
    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    @JsonProperty("healthcare")
    public Healthcare getHealthcare() {
        return healthcare;
    }

    @JsonProperty("healthcare")
    public void setHealthcare(Healthcare healthcare) {
        this.healthcare = healthcare;
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
