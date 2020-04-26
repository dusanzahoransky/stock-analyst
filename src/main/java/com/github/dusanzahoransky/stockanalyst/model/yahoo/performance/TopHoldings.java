
package com.github.dusanzahoransky.stockanalyst.model.yahoo.performance;

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
    "preferredPosition",
    "bondPosition",
    "convertiblePosition",
    "sectorWeightings",
    "holdings",
    "bondHoldings",
    "bondRatings",
    "equityHoldings",
    "otherPosition",
    "maxAge",
    "cashPosition",
    "stockPosition"
})
public class TopHoldings {

    @JsonProperty("preferredPosition")
    private PreferredPosition preferredPosition;
    @JsonProperty("bondPosition")
    private BondPosition bondPosition;
    @JsonProperty("convertiblePosition")
    private ConvertiblePosition convertiblePosition;
    @JsonProperty("sectorWeightings")
    private List<SectorWeighting> sectorWeightings = null;
    @JsonProperty("holdings")
    private List<Holding> holdings = null;
    @JsonProperty("bondHoldings")
    private BondHoldings bondHoldings;
    @JsonProperty("bondRatings")
    private List<BondRating> bondRatings = null;
    @JsonProperty("equityHoldings")
    private EquityHoldings equityHoldings;
    @JsonProperty("otherPosition")
    private OtherPosition otherPosition;
    @JsonProperty("maxAge")
    private Integer maxAge;
    @JsonProperty("cashPosition")
    private CashPosition cashPosition;
    @JsonProperty("stockPosition")
    private StockPosition stockPosition;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("preferredPosition")
    public PreferredPosition getPreferredPosition() {
        return preferredPosition;
    }

    @JsonProperty("preferredPosition")
    public void setPreferredPosition(PreferredPosition preferredPosition) {
        this.preferredPosition = preferredPosition;
    }

    @JsonProperty("bondPosition")
    public BondPosition getBondPosition() {
        return bondPosition;
    }

    @JsonProperty("bondPosition")
    public void setBondPosition(BondPosition bondPosition) {
        this.bondPosition = bondPosition;
    }

    @JsonProperty("convertiblePosition")
    public ConvertiblePosition getConvertiblePosition() {
        return convertiblePosition;
    }

    @JsonProperty("convertiblePosition")
    public void setConvertiblePosition(ConvertiblePosition convertiblePosition) {
        this.convertiblePosition = convertiblePosition;
    }

    @JsonProperty("sectorWeightings")
    public List<SectorWeighting> getSectorWeightings() {
        return sectorWeightings;
    }

    @JsonProperty("sectorWeightings")
    public void setSectorWeightings(List<SectorWeighting> sectorWeightings) {
        this.sectorWeightings = sectorWeightings;
    }

    @JsonProperty("holdings")
    public List<Holding> getHoldings() {
        return holdings;
    }

    @JsonProperty("holdings")
    public void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

    @JsonProperty("bondHoldings")
    public BondHoldings getBondHoldings() {
        return bondHoldings;
    }

    @JsonProperty("bondHoldings")
    public void setBondHoldings(BondHoldings bondHoldings) {
        this.bondHoldings = bondHoldings;
    }

    @JsonProperty("bondRatings")
    public List<BondRating> getBondRatings() {
        return bondRatings;
    }

    @JsonProperty("bondRatings")
    public void setBondRatings(List<BondRating> bondRatings) {
        this.bondRatings = bondRatings;
    }

    @JsonProperty("equityHoldings")
    public EquityHoldings getEquityHoldings() {
        return equityHoldings;
    }

    @JsonProperty("equityHoldings")
    public void setEquityHoldings(EquityHoldings equityHoldings) {
        this.equityHoldings = equityHoldings;
    }

    @JsonProperty("otherPosition")
    public OtherPosition getOtherPosition() {
        return otherPosition;
    }

    @JsonProperty("otherPosition")
    public void setOtherPosition(OtherPosition otherPosition) {
        this.otherPosition = otherPosition;
    }

    @JsonProperty("maxAge")
    public Integer getMaxAge() {
        return maxAge;
    }

    @JsonProperty("maxAge")
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    @JsonProperty("cashPosition")
    public CashPosition getCashPosition() {
        return cashPosition;
    }

    @JsonProperty("cashPosition")
    public void setCashPosition(CashPosition cashPosition) {
        this.cashPosition = cashPosition;
    }

    @JsonProperty("stockPosition")
    public StockPosition getStockPosition() {
        return stockPosition;
    }

    @JsonProperty("stockPosition")
    public void setStockPosition(StockPosition stockPosition) {
        this.stockPosition = stockPosition;
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
