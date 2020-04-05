package com.github.dzahoransky.stocks.analyst.service

import com.github.dzahoransky.stocks.analyst.model.StockInfo
import com.github.dzahoransky.stocks.analyst.model.StockTicker
import com.github.dzahoransky.stocks.analyst.model.yahoo.PeriodMeasure
import com.github.dzahoransky.stocks.analyst.model.yahoo.Statistics
import org.openqa.selenium.By
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import kotlin.random.Random

class StockScrapperService : AutoCloseable {
    companion object {
        const val MILLION = 1000L * 1000L
        const val BILLION = MILLION * 1000L
        const val TRILLION = BILLION * 1000L
    }

    private val driver: ChromeDriver

    init {
        System.setProperty("webdriver.chrome.driver", File(this.javaClass.getResource("/chromedriver").toURI()).absolutePath)
        val options = ChromeOptions()
        //To solve an issue with: Timed out receiving message from renderer: 0.100 https://stackoverflow.com/questions/48450594/selenium-timed-out-receiving-message-from-renderer
        options.setPageLoadStrategy(PageLoadStrategy.EAGER)
        options.addArguments("--headless") // only if you are ACTUALLY running headless

        driver = ChromeDriver(options)
//        driver.manage().timeouts().setScriptTimeout(3000, TimeUnit.SECONDS)
    }

    fun scrape(tickers: List<StockTicker>): List<StockInfo> {
        val stockInfo = mutableListOf<StockInfo>()
        for (ticker in tickers) {
            try {
                println("Scraping ticker $ticker")
                stockInfo.add(scrapeYahoo(ticker))
            } catch (e: Exception) {
                println("Failed to process ${ticker}: ${e.message}")
            }
            Thread.sleep(5000)   //avoid hitting hit rate limit
        }
        return stockInfo
    }

    fun scrapeYahoo(ticker: StockTicker): StockInfo {
        val statistics = parseStatistics(ticker)
        return StockInfo(ticker, statistics)
    }

    private fun parseStatistics(ticker: StockTicker): Statistics {
        driver.get("https://finance.yahoo.com/quote/${ticker.symbol}/key-statistics?p=${ticker.symbol}")

        val mainDiv = driver.findElements(By.xpath("//div[@id='Main']")).first()
        val valuationMeasuresTable = mainDiv.findElements(By.xpath(".//*[h2/span='Valuation Measures']//table")).first()

        val headers = valuationMeasuresTable.findElements(By.xpath(".//thead/tr/th"))  // [empty]	Current	12/31/2019	9/30/2019	6/30/2019	3/31/2019
        val valueRows = valuationMeasuresTable.findElements(By.xpath(".//tbody/tr"))

        val measures = mutableListOf<PeriodMeasure>()

        for (i in headers.indices) {
            if (i == 0) continue //header column

            var rowIndex = 0
            val columnIndex = i + 1 //xpath indexing starts at 1
            measures.add(PeriodMeasure(
                period = headers[i].text,
                marketCap = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.let { parseLong(it) },
                enterpriseValue = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.let { parseLong(it) },
                trailingPE = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toDoubleOrNull(),
                forwardPE = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toDoubleOrNull(),
                priceEarningGrowth = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toDoubleOrNull(),
                priceSales = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toDoubleOrNull(),
                priceBook = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toDoubleOrNull(),
                enterpriseValueRevenue = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toDoubleOrNull(),
                enterpriseValueEBITDA = valueRows[rowIndex].findElement(By.xpath("./td[$columnIndex]")).text.toDoubleOrNull()
            ))
        }

        return Statistics(measures)
    }

    /**
     * Converts 1.56T into a long 1 560 000 000 000
     */
    private fun parseLong(yahooNumber: String): Long? {
        val convertedNumber: Float?
        when (yahooNumber.last()) {
            'M' -> convertedNumber = yahooNumber.dropLast(1).toFloat() * MILLION
            'B' -> convertedNumber = yahooNumber.dropLast(1).toFloat() * BILLION
            'T' -> convertedNumber = yahooNumber.dropLast(1).toFloat() * TRILLION
            else -> convertedNumber = yahooNumber.toFloatOrNull()
        }
        return convertedNumber.let { it?.toLong() }
    }

    override fun close() {
        driver.close()
    }
}