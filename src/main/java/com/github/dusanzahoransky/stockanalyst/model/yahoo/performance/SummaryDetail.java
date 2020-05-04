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
    "previousClose",
    "regularMarketOpen",
    "twoHundredDayAverage",
    "trailingAnnualDividendYield",
    "payoutRatio",
    "volume24Hr",
    "regularMarketDayHigh",
    "navPrice",
    "averageDailyVolume10Day",
    "totalAssets",
    "regularMarketPreviousClose",
    "fiftyDayAverage",
    "trailingAnnualDividendRate",
    "open",
    "toCurrency",
    "averageVolume10days",
    "expireDate",
    "yield",
    "algorithm",
    "dividendRate",
    "exDividendDate",
    "beta",
    "circulatingSupply",
    "startDate",
    "regularMarketDayLow",
    "priceHint",
    "currency",
    "regularMarketVolume",
    "lastMarket",
    "maxSupply",
    "openInterest",
    "marketCap",
    "volumeAllCurrencies",
    "strikePrice",
    "averageVolume",
    "priceToSalesTrailing12Months",
    "dayLow",
    "ask",
    "ytdReturn",
    "askSize",
    "volume",
    "fiftyTwoWeekHigh",
    "forwardPE",
    "maxAge",
    "fromCurrency",
    "fiveYearAvgDividendYield",
    "fiftyTwoWeekLow",
    "bid",
    "tradeable",
    "dividendYield",
    "bidSize",
    "dayHigh"
})
public class SummaryDetail {

    @JsonProperty("previousClose")
    private PreviousClose previousClose;
    @JsonProperty("regularMarketOpen")
    private RegularMarketOpen regularMarketOpen;
    @JsonProperty("twoHundredDayAverage")
    private TwoHundredDayAverage twoHundredDayAverage;
    @JsonProperty("trailingAnnualDividendYield")
    private TrailingAnnualDividendYield trailingAnnualDividendYield;
    @JsonProperty("payoutRatio")
    private PayoutRatio payoutRatio;
    @JsonProperty("volume24Hr")
    private Volume24Hr volume24Hr;
    @JsonProperty("regularMarketDayHigh")
    private RegularMarketDayHigh regularMarketDayHigh;
    @JsonProperty("navPrice")
    private NavPrice navPrice;
    @JsonProperty("averageDailyVolume10Day")
    private AverageDailyVolume10Day averageDailyVolume10Day;
    @JsonProperty("totalAssets")
    private TotalAssets totalAssets;
    @JsonProperty("regularMarketPreviousClose")
    private RegularMarketPreviousClose regularMarketPreviousClose;
    @JsonProperty("fiftyDayAverage")
    private FiftyDayAverage fiftyDayAverage;
    @JsonProperty("trailingAnnualDividendRate")
    private TrailingAnnualDividendRate trailingAnnualDividendRate;
    @JsonProperty("open")
    private Open open;
    @JsonProperty("toCurrency")
    private Object toCurrency;
    @JsonProperty("averageVolume10days")
    private AverageVolume10days averageVolume10days;
    @JsonProperty("expireDate")
    private ExpireDate expireDate;
    @JsonProperty("yield")
    private Yield yield;
    @JsonProperty("algorithm")
    private Object algorithm;
    @JsonProperty("dividendRate")
    private DividendRate dividendRate;
    @JsonProperty("exDividendDate")
    private ExDividendDate exDividendDate;
    @JsonProperty("beta")
    private Beta beta;
    @JsonProperty("circulatingSupply")
    private CirculatingSupply circulatingSupply;
    @JsonProperty("startDate")
    private StartDate startDate;
    @JsonProperty("regularMarketDayLow")
    private RegularMarketDayLow regularMarketDayLow;
    @JsonProperty("priceHint")
    private PriceHint priceHint;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("regularMarketVolume")
    private RegularMarketVolume regularMarketVolume;
    @JsonProperty("lastMarket")
    private Object lastMarket;
    @JsonProperty("maxSupply")
    private MaxSupply maxSupply;
    @JsonProperty("openInterest")
    private OpenInterest openInterest;
    @JsonProperty("marketCap")
    private MarketCap marketCap;
    @JsonProperty("volumeAllCurrencies")
    private VolumeAllCurrencies volumeAllCurrencies;
    @JsonProperty("strikePrice")
    private StrikePrice strikePrice;
    @JsonProperty("averageVolume")
    private AverageVolume averageVolume;
    @JsonProperty("priceToSalesTrailing12Months")
    private PriceToSalesTrailing12Months priceToSalesTrailing12Months;
    @JsonProperty("dayLow")
    private DayLow dayLow;
    @JsonProperty("ask")
    private Ask ask;
    @JsonProperty("ytdReturn")
    private YtdReturn ytdReturn;
    @JsonProperty("askSize")
    private AskSize askSize;
    @JsonProperty("volume")
    private Volume volume;
    @JsonProperty("fiftyTwoWeekHigh")
    private FiftyTwoWeekHigh fiftyTwoWeekHigh;
    @JsonProperty("forwardPE")
    private ForwardPE forwardPE;
    @JsonProperty("maxAge")
    private Integer maxAge;
    @JsonProperty("fromCurrency")
    private Object fromCurrency;
    @JsonProperty("fiveYearAvgDividendYield")
    private FiveYearAvgDividendYield fiveYearAvgDividendYield;
    @JsonProperty("fiftyTwoWeekLow")
    private FiftyTwoWeekLow fiftyTwoWeekLow;
    @JsonProperty("bid")
    private Bid bid;
    @JsonProperty("tradeable")
    private Boolean tradeable;
    @JsonProperty("dividendYield")
    private DividendYield dividendYield;
    @JsonProperty("bidSize")
    private BidSize bidSize;
    @JsonProperty("dayHigh")
    private DayHigh dayHigh;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("previousClose")
    public PreviousClose getPreviousClose() {
        return previousClose;
    }

    @JsonProperty("previousClose")
    public void setPreviousClose(PreviousClose previousClose) {
        this.previousClose = previousClose;
    }

    @JsonProperty("regularMarketOpen")
    public RegularMarketOpen getRegularMarketOpen() {
        return regularMarketOpen;
    }

    @JsonProperty("regularMarketOpen")
    public void setRegularMarketOpen(RegularMarketOpen regularMarketOpen) {
        this.regularMarketOpen = regularMarketOpen;
    }

    @JsonProperty("twoHundredDayAverage")
    public TwoHundredDayAverage getTwoHundredDayAverage() {
        return twoHundredDayAverage;
    }

    @JsonProperty("twoHundredDayAverage")
    public void setTwoHundredDayAverage(TwoHundredDayAverage twoHundredDayAverage) {
        this.twoHundredDayAverage = twoHundredDayAverage;
    }

    @JsonProperty("trailingAnnualDividendYield")
    public TrailingAnnualDividendYield getTrailingAnnualDividendYield() {
        return trailingAnnualDividendYield;
    }

    @JsonProperty("trailingAnnualDividendYield")
    public void setTrailingAnnualDividendYield(TrailingAnnualDividendYield trailingAnnualDividendYield) {
        this.trailingAnnualDividendYield = trailingAnnualDividendYield;
    }

    @JsonProperty("payoutRatio")
    public PayoutRatio getPayoutRatio() {
        return payoutRatio;
    }

    @JsonProperty("payoutRatio")
    public void setPayoutRatio(PayoutRatio payoutRatio) {
        this.payoutRatio = payoutRatio;
    }

    @JsonProperty("volume24Hr")
    public Volume24Hr getVolume24Hr() {
        return volume24Hr;
    }

    @JsonProperty("volume24Hr")
    public void setVolume24Hr(Volume24Hr volume24Hr) {
        this.volume24Hr = volume24Hr;
    }

    @JsonProperty("regularMarketDayHigh")
    public RegularMarketDayHigh getRegularMarketDayHigh() {
        return regularMarketDayHigh;
    }

    @JsonProperty("regularMarketDayHigh")
    public void setRegularMarketDayHigh(RegularMarketDayHigh regularMarketDayHigh) {
        this.regularMarketDayHigh = regularMarketDayHigh;
    }

    @JsonProperty("navPrice")
    public NavPrice getNavPrice() {
        return navPrice;
    }

    @JsonProperty("navPrice")
    public void setNavPrice(NavPrice navPrice) {
        this.navPrice = navPrice;
    }

    @JsonProperty("averageDailyVolume10Day")
    public AverageDailyVolume10Day getAverageDailyVolume10Day() {
        return averageDailyVolume10Day;
    }

    @JsonProperty("averageDailyVolume10Day")
    public void setAverageDailyVolume10Day(AverageDailyVolume10Day averageDailyVolume10Day) {
        this.averageDailyVolume10Day = averageDailyVolume10Day;
    }

    @JsonProperty("totalAssets")
    public TotalAssets getTotalAssets() {
        return totalAssets;
    }

    @JsonProperty("totalAssets")
    public void setTotalAssets(TotalAssets totalAssets) {
        this.totalAssets = totalAssets;
    }

    @JsonProperty("regularMarketPreviousClose")
    public RegularMarketPreviousClose getRegularMarketPreviousClose() {
        return regularMarketPreviousClose;
    }

    @JsonProperty("regularMarketPreviousClose")
    public void setRegularMarketPreviousClose(RegularMarketPreviousClose regularMarketPreviousClose) {
        this.regularMarketPreviousClose = regularMarketPreviousClose;
    }

    @JsonProperty("fiftyDayAverage")
    public FiftyDayAverage getFiftyDayAverage() {
        return fiftyDayAverage;
    }

    @JsonProperty("fiftyDayAverage")
    public void setFiftyDayAverage(FiftyDayAverage fiftyDayAverage) {
        this.fiftyDayAverage = fiftyDayAverage;
    }

    @JsonProperty("trailingAnnualDividendRate")
    public TrailingAnnualDividendRate getTrailingAnnualDividendRate() {
        return trailingAnnualDividendRate;
    }

    @JsonProperty("trailingAnnualDividendRate")
    public void setTrailingAnnualDividendRate(TrailingAnnualDividendRate trailingAnnualDividendRate) {
        this.trailingAnnualDividendRate = trailingAnnualDividendRate;
    }

    @JsonProperty("open")
    public Open getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(Open open) {
        this.open = open;
    }

    @JsonProperty("toCurrency")
    public Object getToCurrency() {
        return toCurrency;
    }

    @JsonProperty("toCurrency")
    public void setToCurrency(Object toCurrency) {
        this.toCurrency = toCurrency;
    }

    @JsonProperty("averageVolume10days")
    public AverageVolume10days getAverageVolume10days() {
        return averageVolume10days;
    }

    @JsonProperty("averageVolume10days")
    public void setAverageVolume10days(AverageVolume10days averageVolume10days) {
        this.averageVolume10days = averageVolume10days;
    }

    @JsonProperty("expireDate")
    public ExpireDate getExpireDate() {
        return expireDate;
    }

    @JsonProperty("expireDate")
    public void setExpireDate(ExpireDate expireDate) {
        this.expireDate = expireDate;
    }

    @JsonProperty("yield")
    public Yield getYield() {
        return yield;
    }

    @JsonProperty("yield")
    public void setYield(Yield yield) {
        this.yield = yield;
    }

    @JsonProperty("algorithm")
    public Object getAlgorithm() {
        return algorithm;
    }

    @JsonProperty("algorithm")
    public void setAlgorithm(Object algorithm) {
        this.algorithm = algorithm;
    }

    @JsonProperty("dividendRate")
    public DividendRate getDividendRate() {
        return dividendRate;
    }

    @JsonProperty("dividendRate")
    public void setDividendRate(DividendRate dividendRate) {
        this.dividendRate = dividendRate;
    }

    @JsonProperty("exDividendDate")
    public ExDividendDate getExDividendDate() {
        return exDividendDate;
    }

    @JsonProperty("exDividendDate")
    public void setExDividendDate(ExDividendDate exDividendDate) {
        this.exDividendDate = exDividendDate;
    }

    @JsonProperty("beta")
    public Beta getBeta() {
        return beta;
    }

    @JsonProperty("beta")
    public void setBeta(Beta beta) {
        this.beta = beta;
    }

    @JsonProperty("circulatingSupply")
    public CirculatingSupply getCirculatingSupply() {
        return circulatingSupply;
    }

    @JsonProperty("circulatingSupply")
    public void setCirculatingSupply(CirculatingSupply circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    @JsonProperty("startDate")
    public StartDate getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(StartDate startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("regularMarketDayLow")
    public RegularMarketDayLow getRegularMarketDayLow() {
        return regularMarketDayLow;
    }

    @JsonProperty("regularMarketDayLow")
    public void setRegularMarketDayLow(RegularMarketDayLow regularMarketDayLow) {
        this.regularMarketDayLow = regularMarketDayLow;
    }

    @JsonProperty("priceHint")
    public PriceHint getPriceHint() {
        return priceHint;
    }

    @JsonProperty("priceHint")
    public void setPriceHint(PriceHint priceHint) {
        this.priceHint = priceHint;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("regularMarketVolume")
    public RegularMarketVolume getRegularMarketVolume() {
        return regularMarketVolume;
    }

    @JsonProperty("regularMarketVolume")
    public void setRegularMarketVolume(RegularMarketVolume regularMarketVolume) {
        this.regularMarketVolume = regularMarketVolume;
    }

    @JsonProperty("lastMarket")
    public Object getLastMarket() {
        return lastMarket;
    }

    @JsonProperty("lastMarket")
    public void setLastMarket(Object lastMarket) {
        this.lastMarket = lastMarket;
    }

    @JsonProperty("maxSupply")
    public MaxSupply getMaxSupply() {
        return maxSupply;
    }

    @JsonProperty("maxSupply")
    public void setMaxSupply(MaxSupply maxSupply) {
        this.maxSupply = maxSupply;
    }

    @JsonProperty("openInterest")
    public OpenInterest getOpenInterest() {
        return openInterest;
    }

    @JsonProperty("openInterest")
    public void setOpenInterest(OpenInterest openInterest) {
        this.openInterest = openInterest;
    }

    @JsonProperty("marketCap")
    public MarketCap getMarketCap() {
        return marketCap;
    }

    @JsonProperty("marketCap")
    public void setMarketCap(MarketCap marketCap) {
        this.marketCap = marketCap;
    }

    @JsonProperty("volumeAllCurrencies")
    public VolumeAllCurrencies getVolumeAllCurrencies() {
        return volumeAllCurrencies;
    }

    @JsonProperty("volumeAllCurrencies")
    public void setVolumeAllCurrencies(VolumeAllCurrencies volumeAllCurrencies) {
        this.volumeAllCurrencies = volumeAllCurrencies;
    }

    @JsonProperty("strikePrice")
    public StrikePrice getStrikePrice() {
        return strikePrice;
    }

    @JsonProperty("strikePrice")
    public void setStrikePrice(StrikePrice strikePrice) {
        this.strikePrice = strikePrice;
    }

    @JsonProperty("averageVolume")
    public AverageVolume getAverageVolume() {
        return averageVolume;
    }

    @JsonProperty("averageVolume")
    public void setAverageVolume(AverageVolume averageVolume) {
        this.averageVolume = averageVolume;
    }

    @JsonProperty("priceToSalesTrailing12Months")
    public PriceToSalesTrailing12Months getPriceToSalesTrailing12Months() {
        return priceToSalesTrailing12Months;
    }

    @JsonProperty("priceToSalesTrailing12Months")
    public void setPriceToSalesTrailing12Months(PriceToSalesTrailing12Months priceToSalesTrailing12Months) {
        this.priceToSalesTrailing12Months = priceToSalesTrailing12Months;
    }

    @JsonProperty("dayLow")
    public DayLow getDayLow() {
        return dayLow;
    }

    @JsonProperty("dayLow")
    public void setDayLow(DayLow dayLow) {
        this.dayLow = dayLow;
    }

    @JsonProperty("ask")
    public Ask getAsk() {
        return ask;
    }

    @JsonProperty("ask")
    public void setAsk(Ask ask) {
        this.ask = ask;
    }

    @JsonProperty("ytdReturn")
    public YtdReturn getYtdReturn() {
        return ytdReturn;
    }

    @JsonProperty("ytdReturn")
    public void setYtdReturn(YtdReturn ytdReturn) {
        this.ytdReturn = ytdReturn;
    }

    @JsonProperty("askSize")
    public AskSize getAskSize() {
        return askSize;
    }

    @JsonProperty("askSize")
    public void setAskSize(AskSize askSize) {
        this.askSize = askSize;
    }

    @JsonProperty("volume")
    public Volume getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    @JsonProperty("fiftyTwoWeekHigh")
    public FiftyTwoWeekHigh getFiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    @JsonProperty("fiftyTwoWeekHigh")
    public void setFiftyTwoWeekHigh(FiftyTwoWeekHigh fiftyTwoWeekHigh) {
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
    }

    @JsonProperty("forwardPE")
    public ForwardPE getForwardPE() {
        return forwardPE;
    }

    @JsonProperty("forwardPE")
    public void setForwardPE(ForwardPE forwardPE) {
        this.forwardPE = forwardPE;
    }

    @JsonProperty("maxAge")
    public Integer getMaxAge() {
        return maxAge;
    }

    @JsonProperty("maxAge")
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    @JsonProperty("fromCurrency")
    public Object getFromCurrency() {
        return fromCurrency;
    }

    @JsonProperty("fromCurrency")
    public void setFromCurrency(Object fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    @JsonProperty("fiveYearAvgDividendYield")
    public FiveYearAvgDividendYield getFiveYearAvgDividendYield() {
        return fiveYearAvgDividendYield;
    }

    @JsonProperty("fiveYearAvgDividendYield")
    public void setFiveYearAvgDividendYield(FiveYearAvgDividendYield fiveYearAvgDividendYield) {
        this.fiveYearAvgDividendYield = fiveYearAvgDividendYield;
    }

    @JsonProperty("fiftyTwoWeekLow")
    public FiftyTwoWeekLow getFiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    @JsonProperty("fiftyTwoWeekLow")
    public void setFiftyTwoWeekLow(FiftyTwoWeekLow fiftyTwoWeekLow) {
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
    }

    @JsonProperty("bid")
    public Bid getBid() {
        return bid;
    }

    @JsonProperty("bid")
    public void setBid(Bid bid) {
        this.bid = bid;
    }

    @JsonProperty("tradeable")
    public Boolean getTradeable() {
        return tradeable;
    }

    @JsonProperty("tradeable")
    public void setTradeable(Boolean tradeable) {
        this.tradeable = tradeable;
    }

    @JsonProperty("dividendYield")
    public DividendYield getDividendYield() {
        return dividendYield;
    }

    @JsonProperty("dividendYield")
    public void setDividendYield(DividendYield dividendYield) {
        this.dividendYield = dividendYield;
    }

    @JsonProperty("bidSize")
    public BidSize getBidSize() {
        return bidSize;
    }

    @JsonProperty("bidSize")
    public void setBidSize(BidSize bidSize) {
        this.bidSize = bidSize;
    }

    @JsonProperty("dayHigh")
    public DayHigh getDayHigh() {
        return dayHigh;
    }

    @JsonProperty("dayHigh")
    public void setDayHigh(DayHigh dayHigh) {
        this.dayHigh = dayHigh;
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
