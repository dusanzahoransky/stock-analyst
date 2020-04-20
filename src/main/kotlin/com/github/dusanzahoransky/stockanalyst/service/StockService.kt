package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.client.ExchangeRateClient
import com.github.dusanzahoransky.stockanalyst.client.YahooFinanceClient
import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import com.github.dusanzahoransky.stockanalyst.model.yahoo.balancesheet.BalanceSheetResponse
import com.github.dusanzahoransky.stockanalyst.model.yahoo.statistics.StatisticsResponse
import com.github.dusanzahoransky.stockanalyst.repository.StockRepo
import com.github.dusanzahoransky.stockanalyst.repository.WatchlistRepo
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.multiply
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percent
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StockService @Autowired constructor(
    val watchlistRepo: WatchlistRepo,
    val stockRepo: StockRepo,
    val yahooFinanceClient: YahooFinanceClient,
    val exchangeRateClient: ExchangeRateClient
) {

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun getWatchlistStocks(watchlist: Watchlist, forceRefresh: Boolean = false): List<StockInfo> {
        val watchlistTickers = watchlistRepo.getWatchlist(watchlist)
        return watchlistTickers.mapNotNull { ticker -> findOrLoad(ticker, forceRefresh) }
    }

    private fun findOrLoad(ticker: StockTicker, forceRefreshCache: Boolean = false): StockInfo? {
        var stock = stockRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)

        //retrieve from cache
        if (!forceRefreshCache && stock != null) {
            log.debug("Retrieving stock info from cache: $ticker")
            return stock
        }

        stock = StockInfo(symbol = ticker.symbol, exchange = ticker.exchange)
        //load from yahoo
        val balSheet = yahooFinanceClient.getBalanceSheet(ticker)
        if (balSheet == null) {
            log.error("Failed to retrieve stock BalanceSheet from Yahoo $ticker")
            return null
        }
        processBalanceSheet(balSheet, stock)

        val stats = yahooFinanceClient.getStatistics(ticker)
        if (stats == null) {
            log.error("Failed to retrieve stock Statistics from Yahoo $ticker")
            return null
        }
        processStatistics(stats, stock)

        //delete previous version
        stockRepo.findBySymbolAndExchange(ticker.symbol, ticker.exchange)?.let { stockRepo.delete(it) }
        //store new version
        log.debug("Saving $ticker into DB")
        return stockRepo.insert(stock)
    }

    private fun processBalanceSheet(stats: BalanceSheetResponse, stock: StockInfo) {

    }

    private fun processStatistics(stats: StatisticsResponse, stock: StockInfo) {
        val financialData = stats.financialData
        val price = stats.price
        val defaultKeyStatistics = stats.defaultKeyStatistics
        val summaryDetail = stats.summaryDetail
        val calendarEvents = stats.calendarEvents

        stock.companyName = stats.quoteType?.shortName
        stock.price = price?.regularMarketPrice?.raw
        stock.currency = price?.currency?.let { it -> Currency.valueOf(it) }
        stock.financialCurrency = financialData?.financialCurrency?.let { it -> Currency.valueOf(it) }
        val differentCurrencies = stock.currency != stock.financialCurrency
        val financialToStockRate = if (differentCurrencies && stock.currency != null && stock.financialCurrency != null) {
            exchangeRateClient.getRate(stock.financialCurrency!!, stock.currency!!)
        } else {
            1.0
        }

        stock.change = percent(price?.regularMarketChangePercent?.raw)
        stock.enterpriseValue = defaultKeyStatistics.enterpriseValue?.raw

        stock.targetLowPrice = financialData.targetLowPrice?.raw.let { multiply(it, financialToStockRate) }
        stock.targetMedianPrice = financialData.targetMedianPrice?.raw.let { multiply(it, financialToStockRate) }

        if (stock.targetLowPrice != null && stock.price != null) {
            stock.belowTargetLowPricePercent = ((stock.targetLowPrice!! - stock.price!!) / stock.price!!) * 100.0
        }
        if (stock.targetMedianPrice != null && stock.price != null) {
            stock.belowTargetMedianPricePercent = ((stock.targetMedianPrice!! - stock.price!!) / stock.price!!) * 100.0
        }

        stock.totalCashPerShare = financialData.totalCashPerShare?.raw
        if (stock.totalCashPerShare != null && stock.price != null) {
            stock.totalCashPerSharePercent = (stock.totalCashPerShare!! / stock.price!!) * 100.0
        }
        if (!differentCurrencies) {
            stock.totalDebtEquity = financialData.debtToEquity?.raw
        }

        stock.trailingPE = summaryDetail.trailingPE?.raw
        stock.forwardPE = summaryDetail.forwardPE?.raw
        stock.priceToSalesTrailing12Months = summaryDetail.priceToSalesTrailing12Months?.raw
        if (!differentCurrencies) {
            stock.priceBook = defaultKeyStatistics.priceToBook?.raw
        } else {
            // P /B = Market Price per Share / BVPS
            //BVPS = (Total Equity − Preferred Equity) / Total Shares Outstanding
            //​	https://www.investopedia.com/terms/b/bvps.asp
        }
        stock.enterpriseValueRevenue = defaultKeyStatistics.enterpriseToRevenue?.raw
        stock.enterpriseValueEBITDA = defaultKeyStatistics.enterpriseToEbitda?.raw

        stock.quarterlyRevenueGrowth = defaultKeyStatistics.revenueQuarterlyGrowth?.raw
        stock.yoyQuarterlyRevenueGrowthPercent = percent(financialData.revenueGrowth?.raw)
        if (!differentCurrencies) {
            stock.yoyQuarterlyEarningsGrowthPercent = percent(defaultKeyStatistics.earningsQuarterlyGrowth?.raw)
            stock.earningsGrowthPercent = percent(financialData.earningsGrowth?.raw)
            stock.priceEarningGrowth = defaultKeyStatistics.pegRatio?.raw
            val trailingEps = defaultKeyStatistics.trailingEps?.raw
            if (stock.trailingPE != null && trailingEps != null) {
                stock.trailingPriceEarningGrowth = stock.trailingPE!! / trailingEps
            }
        }

        stock.week52Change = percent(defaultKeyStatistics.get52WeekChange()?.raw)
        stock.week52Low = summaryDetail.fiftyTwoWeekLow?.raw
        if (stock.price != null && stock.week52Low != null) {
            stock.week52AboveLowPercent = ((stock.price!! - stock.week52Low!!) / stock.price!!) * 100.0
        }
        stock.week52High = summaryDetail.fiftyTwoWeekHigh?.raw
        if (stock.price != null && stock.week52High != null) {
            stock.week52BelowHighPercent = ((stock.week52High!! - stock.price!!) / stock.price!!) * 100.0
        }

        stock.heldByInsiders = percent(defaultKeyStatistics.heldPercentInsiders?.raw)
        stock.heldByInstitutions = percent(defaultKeyStatistics.heldPercentInstitutions?.raw)
        stock.shortToFloat = percent(defaultKeyStatistics.shortPercentOfFloat?.raw)

        val sharesShortPriorMonth = defaultKeyStatistics.sharesShortPriorMonth?.raw
        val sharesShort = defaultKeyStatistics.sharesShort?.raw
        if (sharesShortPriorMonth != null && sharesShort != null) {
            stock.sharesShortPrevMonthCompare = percent(sharesShortPriorMonth / sharesShort)
        }

        stock.exDividendDate = calendarEvents.exDividendDate?.fmt
        stock.fiveYearAvgDividendYield = summaryDetail.fiveYearAvgDividendYield?.raw
        stock.trailingAnnualDividendYield = percent(summaryDetail.trailingAnnualDividendYield?.raw)
    }

    fun deleteSymbol(symbol: String) {
        stockRepo.findBySymbol(symbol).forEach {
            log.debug("Deleted ${it.companyName}")
            stockRepo.delete(it)
        }
    }

    fun deleteWatchlist(watchlist: Watchlist) {
        val watchlistStocks = watchlistRepo.getWatchlist(watchlist)
        watchlistStocks
            .map { stockRepo.findBySymbolAndExchange(it.symbol, it.exchange) }
            .forEach {
                if(it != null) {
                    log.debug("Deleted ${it.companyName}")
                    stockRepo.delete(it)
                }
            }
    }
}