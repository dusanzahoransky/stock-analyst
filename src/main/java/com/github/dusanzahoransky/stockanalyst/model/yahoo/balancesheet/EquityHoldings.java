
package com.github.dusanzahoransky.stockanalyst.model.yahoo.balancesheet;

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
    "priceToCashflow",
    "priceToSales",
    "priceToBookCat",
    "priceToEarningsCat",
    "medianMarketCapCat",
    "threeYearEarningsGrowthCat",
    "threeYearEarningsGrowth",
    "medianMarketCap",
    "priceToEarnings",
    "priceToBook",
    "priceToSalesCat",
    "priceToCashflowCat"
})
public class EquityHoldings {

    @JsonProperty("priceToCashflow")
    private PriceToCashflow priceToCashflow;
    @JsonProperty("priceToSales")
    private PriceToSales priceToSales;
    @JsonProperty("priceToBookCat")
    private PriceToBookCat priceToBookCat;
    @JsonProperty("priceToEarningsCat")
    private PriceToEarningsCat priceToEarningsCat;
    @JsonProperty("medianMarketCapCat")
    private MedianMarketCapCat medianMarketCapCat;
    @JsonProperty("threeYearEarningsGrowthCat")
    private ThreeYearEarningsGrowthCat threeYearEarningsGrowthCat;
    @JsonProperty("threeYearEarningsGrowth")
    private ThreeYearEarningsGrowth threeYearEarningsGrowth;
    @JsonProperty("medianMarketCap")
    private MedianMarketCap medianMarketCap;
    @JsonProperty("priceToEarnings")
    private PriceToEarnings priceToEarnings;
    @JsonProperty("priceToBook")
    private PriceToBook priceToBook;
    @JsonProperty("priceToSalesCat")
    private PriceToSalesCat priceToSalesCat;
    @JsonProperty("priceToCashflowCat")
    private PriceToCashflowCat priceToCashflowCat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("priceToCashflow")
    public PriceToCashflow getPriceToCashflow() {
        return priceToCashflow;
    }

    @JsonProperty("priceToCashflow")
    public void setPriceToCashflow(PriceToCashflow priceToCashflow) {
        this.priceToCashflow = priceToCashflow;
    }

    @JsonProperty("priceToSales")
    public PriceToSales getPriceToSales() {
        return priceToSales;
    }

    @JsonProperty("priceToSales")
    public void setPriceToSales(PriceToSales priceToSales) {
        this.priceToSales = priceToSales;
    }

    @JsonProperty("priceToBookCat")
    public PriceToBookCat getPriceToBookCat() {
        return priceToBookCat;
    }

    @JsonProperty("priceToBookCat")
    public void setPriceToBookCat(PriceToBookCat priceToBookCat) {
        this.priceToBookCat = priceToBookCat;
    }

    @JsonProperty("priceToEarningsCat")
    public PriceToEarningsCat getPriceToEarningsCat() {
        return priceToEarningsCat;
    }

    @JsonProperty("priceToEarningsCat")
    public void setPriceToEarningsCat(PriceToEarningsCat priceToEarningsCat) {
        this.priceToEarningsCat = priceToEarningsCat;
    }

    @JsonProperty("medianMarketCapCat")
    public MedianMarketCapCat getMedianMarketCapCat() {
        return medianMarketCapCat;
    }

    @JsonProperty("medianMarketCapCat")
    public void setMedianMarketCapCat(MedianMarketCapCat medianMarketCapCat) {
        this.medianMarketCapCat = medianMarketCapCat;
    }

    @JsonProperty("threeYearEarningsGrowthCat")
    public ThreeYearEarningsGrowthCat getThreeYearEarningsGrowthCat() {
        return threeYearEarningsGrowthCat;
    }

    @JsonProperty("threeYearEarningsGrowthCat")
    public void setThreeYearEarningsGrowthCat(ThreeYearEarningsGrowthCat threeYearEarningsGrowthCat) {
        this.threeYearEarningsGrowthCat = threeYearEarningsGrowthCat;
    }

    @JsonProperty("threeYearEarningsGrowth")
    public ThreeYearEarningsGrowth getThreeYearEarningsGrowth() {
        return threeYearEarningsGrowth;
    }

    @JsonProperty("threeYearEarningsGrowth")
    public void setThreeYearEarningsGrowth(ThreeYearEarningsGrowth threeYearEarningsGrowth) {
        this.threeYearEarningsGrowth = threeYearEarningsGrowth;
    }

    @JsonProperty("medianMarketCap")
    public MedianMarketCap getMedianMarketCap() {
        return medianMarketCap;
    }

    @JsonProperty("medianMarketCap")
    public void setMedianMarketCap(MedianMarketCap medianMarketCap) {
        this.medianMarketCap = medianMarketCap;
    }

    @JsonProperty("priceToEarnings")
    public PriceToEarnings getPriceToEarnings() {
        return priceToEarnings;
    }

    @JsonProperty("priceToEarnings")
    public void setPriceToEarnings(PriceToEarnings priceToEarnings) {
        this.priceToEarnings = priceToEarnings;
    }

    @JsonProperty("priceToBook")
    public PriceToBook getPriceToBook() {
        return priceToBook;
    }

    @JsonProperty("priceToBook")
    public void setPriceToBook(PriceToBook priceToBook) {
        this.priceToBook = priceToBook;
    }

    @JsonProperty("priceToSalesCat")
    public PriceToSalesCat getPriceToSalesCat() {
        return priceToSalesCat;
    }

    @JsonProperty("priceToSalesCat")
    public void setPriceToSalesCat(PriceToSalesCat priceToSalesCat) {
        this.priceToSalesCat = priceToSalesCat;
    }

    @JsonProperty("priceToCashflowCat")
    public PriceToCashflowCat getPriceToCashflowCat() {
        return priceToCashflowCat;
    }

    @JsonProperty("priceToCashflowCat")
    public void setPriceToCashflowCat(PriceToCashflowCat priceToCashflowCat) {
        this.priceToCashflowCat = priceToCashflowCat;
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
