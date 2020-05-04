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
    "bestThreeYrTotalReturn",
    "morningStarReturnRating",
    "ytdReturnPct",
    "fiveYrAvgReturnPct",
    "numYearsUp",
    "worstOneYrTotalReturn",
    "oneYearTotalReturn",
    "numYearsDown",
    "asOfDate",
    "worstThreeYrTotalReturn",
    "bestOneYrTotalReturn",
    "threeYearTotalReturn"
})
public class PerformanceOverview {

    @JsonProperty("bestThreeYrTotalReturn")
    private BestThreeYrTotalReturn bestThreeYrTotalReturn;
    @JsonProperty("morningStarReturnRating")
    private MorningStarReturnRating morningStarReturnRating;
    @JsonProperty("ytdReturnPct")
    private YtdReturnPct ytdReturnPct;
    @JsonProperty("fiveYrAvgReturnPct")
    private FiveYrAvgReturnPct fiveYrAvgReturnPct;
    @JsonProperty("numYearsUp")
    private NumYearsUp numYearsUp;
    @JsonProperty("worstOneYrTotalReturn")
    private WorstOneYrTotalReturn worstOneYrTotalReturn;
    @JsonProperty("oneYearTotalReturn")
    private OneYearTotalReturn oneYearTotalReturn;
    @JsonProperty("numYearsDown")
    private NumYearsDown numYearsDown;
    @JsonProperty("asOfDate")
    private AsOfDate asOfDate;
    @JsonProperty("worstThreeYrTotalReturn")
    private WorstThreeYrTotalReturn worstThreeYrTotalReturn;
    @JsonProperty("bestOneYrTotalReturn")
    private BestOneYrTotalReturn bestOneYrTotalReturn;
    @JsonProperty("threeYearTotalReturn")
    private ThreeYearTotalReturn threeYearTotalReturn;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bestThreeYrTotalReturn")
    public BestThreeYrTotalReturn getBestThreeYrTotalReturn() {
        return bestThreeYrTotalReturn;
    }

    @JsonProperty("bestThreeYrTotalReturn")
    public void setBestThreeYrTotalReturn(BestThreeYrTotalReturn bestThreeYrTotalReturn) {
        this.bestThreeYrTotalReturn = bestThreeYrTotalReturn;
    }

    @JsonProperty("morningStarReturnRating")
    public MorningStarReturnRating getMorningStarReturnRating() {
        return morningStarReturnRating;
    }

    @JsonProperty("morningStarReturnRating")
    public void setMorningStarReturnRating(MorningStarReturnRating morningStarReturnRating) {
        this.morningStarReturnRating = morningStarReturnRating;
    }

    @JsonProperty("ytdReturnPct")
    public YtdReturnPct getYtdReturnPct() {
        return ytdReturnPct;
    }

    @JsonProperty("ytdReturnPct")
    public void setYtdReturnPct(YtdReturnPct ytdReturnPct) {
        this.ytdReturnPct = ytdReturnPct;
    }

    @JsonProperty("fiveYrAvgReturnPct")
    public FiveYrAvgReturnPct getFiveYrAvgReturnPct() {
        return fiveYrAvgReturnPct;
    }

    @JsonProperty("fiveYrAvgReturnPct")
    public void setFiveYrAvgReturnPct(FiveYrAvgReturnPct fiveYrAvgReturnPct) {
        this.fiveYrAvgReturnPct = fiveYrAvgReturnPct;
    }

    @JsonProperty("numYearsUp")
    public NumYearsUp getNumYearsUp() {
        return numYearsUp;
    }

    @JsonProperty("numYearsUp")
    public void setNumYearsUp(NumYearsUp numYearsUp) {
        this.numYearsUp = numYearsUp;
    }

    @JsonProperty("worstOneYrTotalReturn")
    public WorstOneYrTotalReturn getWorstOneYrTotalReturn() {
        return worstOneYrTotalReturn;
    }

    @JsonProperty("worstOneYrTotalReturn")
    public void setWorstOneYrTotalReturn(WorstOneYrTotalReturn worstOneYrTotalReturn) {
        this.worstOneYrTotalReturn = worstOneYrTotalReturn;
    }

    @JsonProperty("oneYearTotalReturn")
    public OneYearTotalReturn getOneYearTotalReturn() {
        return oneYearTotalReturn;
    }

    @JsonProperty("oneYearTotalReturn")
    public void setOneYearTotalReturn(OneYearTotalReturn oneYearTotalReturn) {
        this.oneYearTotalReturn = oneYearTotalReturn;
    }

    @JsonProperty("numYearsDown")
    public NumYearsDown getNumYearsDown() {
        return numYearsDown;
    }

    @JsonProperty("numYearsDown")
    public void setNumYearsDown(NumYearsDown numYearsDown) {
        this.numYearsDown = numYearsDown;
    }

    @JsonProperty("asOfDate")
    public AsOfDate getAsOfDate() {
        return asOfDate;
    }

    @JsonProperty("asOfDate")
    public void setAsOfDate(AsOfDate asOfDate) {
        this.asOfDate = asOfDate;
    }

    @JsonProperty("worstThreeYrTotalReturn")
    public WorstThreeYrTotalReturn getWorstThreeYrTotalReturn() {
        return worstThreeYrTotalReturn;
    }

    @JsonProperty("worstThreeYrTotalReturn")
    public void setWorstThreeYrTotalReturn(WorstThreeYrTotalReturn worstThreeYrTotalReturn) {
        this.worstThreeYrTotalReturn = worstThreeYrTotalReturn;
    }

    @JsonProperty("bestOneYrTotalReturn")
    public BestOneYrTotalReturn getBestOneYrTotalReturn() {
        return bestOneYrTotalReturn;
    }

    @JsonProperty("bestOneYrTotalReturn")
    public void setBestOneYrTotalReturn(BestOneYrTotalReturn bestOneYrTotalReturn) {
        this.bestOneYrTotalReturn = bestOneYrTotalReturn;
    }

    @JsonProperty("threeYearTotalReturn")
    public ThreeYearTotalReturn getThreeYearTotalReturn() {
        return threeYearTotalReturn;
    }

    @JsonProperty("threeYearTotalReturn")
    public void setThreeYearTotalReturn(ThreeYearTotalReturn threeYearTotalReturn) {
        this.threeYearTotalReturn = threeYearTotalReturn;
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
