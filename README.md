# Stock Analyst

Stock Analyst is a stock data scraper and data mining service in order to calculate correlation between various financial statistics and stock price trend.

Scrapped data are stored in MongoDB indexed by the `timestamp` when they been scrapped and `StockInfo -> StockTicker -> Symbol`

```
{
    timestamp: ZonedDateTime
    info: StockInfo
} 
```