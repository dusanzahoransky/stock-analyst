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
    "bb",
    "aa",
    "aaa",
    "a",
    "other",
    "b",
    "bbb",
    "below_b",
    "us_government"
})
public class BondRating {

    @JsonProperty("bb")
    private Bb bb;
    @JsonProperty("aa")
    private Aa aa;
    @JsonProperty("aaa")
    private Aaa aaa;
    @JsonProperty("a")
    private A a;
    @JsonProperty("other")
    private Other other;
    @JsonProperty("b")
    private B b;
    @JsonProperty("bbb")
    private Bbb bbb;
    @JsonProperty("below_b")
    private BelowB belowB;
    @JsonProperty("us_government")
    private UsGovernment usGovernment;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bb")
    public Bb getBb() {
        return bb;
    }

    @JsonProperty("bb")
    public void setBb(Bb bb) {
        this.bb = bb;
    }

    @JsonProperty("aa")
    public Aa getAa() {
        return aa;
    }

    @JsonProperty("aa")
    public void setAa(Aa aa) {
        this.aa = aa;
    }

    @JsonProperty("aaa")
    public Aaa getAaa() {
        return aaa;
    }

    @JsonProperty("aaa")
    public void setAaa(Aaa aaa) {
        this.aaa = aaa;
    }

    @JsonProperty("a")
    public A getA() {
        return a;
    }

    @JsonProperty("a")
    public void setA(A a) {
        this.a = a;
    }

    @JsonProperty("other")
    public Other getOther() {
        return other;
    }

    @JsonProperty("other")
    public void setOther(Other other) {
        this.other = other;
    }

    @JsonProperty("b")
    public B getB() {
        return b;
    }

    @JsonProperty("b")
    public void setB(B b) {
        this.b = b;
    }

    @JsonProperty("bbb")
    public Bbb getBbb() {
        return bbb;
    }

    @JsonProperty("bbb")
    public void setBbb(Bbb bbb) {
        this.bbb = bbb;
    }

    @JsonProperty("below_b")
    public BelowB getBelowB() {
        return belowB;
    }

    @JsonProperty("below_b")
    public void setBelowB(BelowB belowB) {
        this.belowB = belowB;
    }

    @JsonProperty("us_government")
    public UsGovernment getUsGovernment() {
        return usGovernment;
    }

    @JsonProperty("us_government")
    public void setUsGovernment(UsGovernment usGovernment) {
        this.usGovernment = usGovernment;
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
