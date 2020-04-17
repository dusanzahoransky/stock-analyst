package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.YahooFinanceClient
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import com.github.dusanzahoransky.stockanalyst.repository.StockRepo
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.minus
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.multiply
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StockService @Autowired constructor(
    val watchlistRepo: WatchlistRepo,
    val stockRepo: StockRepo,
    val yahooFinanceClient: YahooFinanceClient
) {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun getWatchlistStocks(watchlist: Watchlist, forceRefresh: Boolean = false): List<StockInfo> {
        val watchlistTickers = watchlistRepo.getWatchlist(watchlist)
        return watchlistTickers.mapNotNull { ticker -> findOrLoad(ticker, forceRefresh) }
    }

    private fun findOrLoad(ticker: StockTicker, forceRefreshCache: Boolean = false): StockInfo? {
        var stock = stockRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        if (!forceRefreshCache && stock != null) {
            log.debug("Retrieving stock info from cache: $ticker")
            return stock
        }

        //load from yahoo
        stock = StockInfo(symbol = ticker.symbol, exchange = ticker.exchange)

        val response = yahooFinanceClient.getStatistics(ticker)

        if (response == null) {
            log.error("Failed to retrieve stock from Yahoo $ticker")
            return null
        }

        val financialData = response.financialData
        val price = response.price
        val defaultKeyStatistics = response.defaultKeyStatistics
        val summaryDetail = response.summaryDetail
        val calendarEvents = response.calendarEvents

        stock.companyName = response.quoteType?.shortName
        stock.price = price?.regularMarketPrice?.raw
        stock.change = price?.regularMarketChange?.raw
        stock.enterpriseValue = defaultKeyStatistics.enterpriseValue?.raw

        stock.totalCashPerShare = financialData.totalCashPerShare?.raw
        stock.totalCashPerSharePercent = multiply(div(stock.totalCashPerShare, stock.price), 100.0)
        stock.totalDebtEquity = financialData.debtToEquity?.raw

        stock.trailingPE = summaryDetail.trailingPE?.raw
        stock.forwardPE = summaryDetail.forwardPE?.raw
        stock.priceToSalesTrailing12Months = defaultKeyStatistics.priceToSalesTrailing12Months?.raw
        stock.priceBook = defaultKeyStatistics.priceToBook?.raw
        stock.enterpriseValueRevenue = defaultKeyStatistics.enterpriseToRevenue?.raw
        stock.enterpriseValueEBITDA = defaultKeyStatistics.enterpriseToEbitda?.raw

        stock.quarterlyRevenueGrowth = defaultKeyStatistics.revenueQuarterlyGrowth?.raw
        stock.quarterlyEarningsGrowth = defaultKeyStatistics.earningsQuarterlyGrowth?.raw
        stock.priceEarningGrowth = defaultKeyStatistics.pegRatio?.raw
        stock.trailingPriceEarningGrowth = div(stock.trailingPE, defaultKeyStatistics.trailingEps?.raw)

        stock.week52Change = defaultKeyStatistics.get52WeekChange()?.raw
        stock.week52Low = summaryDetail.fiftyTwoWeekLow?.raw
        stock.week52AboveLowPercent = multiply(div(minus(stock.price, stock.week52Low), stock.price), 100.0)    //(price - low) / price * 100
        stock.week52High = summaryDetail.fiftyTwoWeekHigh?.raw
        stock.week52BelowHighPercent = multiply(div(minus(stock.week52High, stock.price), stock.price), 100.0) //(high - price) / price * 100

        stock.exDividendDate = calendarEvents.exDividendDate?.fmt
        stock.fiveYearAvgDividendYield = summaryDetail.fiveYearAvgDividendYield?.raw
        stock.trailingAnnualDividendYield = summaryDetail.trailingAnnualDividendYield?.raw

        stockRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { stockRepo.delete(it) }
        log.debug("Saving $ticker into DB")
        return stockRepo.insert(stock)
    }

    fun deleteCompany(companyName: String) {
        stockRepo.findByCompanyName(companyName).forEach { stockRepo.delete(it) }
    }
}