package com.github.dzahoransky.stocks.analyst.service

import com.github.dzahoransky.stocks.analyst.model.StockInfo
import com.github.dzahoransky.stocks.analyst.model.StockTicker
import com.github.dzahoransky.stocks.analyst.model.yahoo.PeriodMeasure
import com.github.dzahoransky.stocks.analyst.model.yahoo.Statistics
import org.openqa.selenium.By
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
            Thread.sleep(10000)   //avoid hitting hit rate limit
        }
        return stockInfo
    }

    fun scrapeYahoo(ticker: StockTicker): StockInfo {
        val statistics = parseStatistics(ticker)
        return StockInfo(statistics.companyName, ticker, statistics)
    }

    private fun parseStatistics(ticker: StockTicker): Statistics {
        driver.get("https://finance.yahoo.com/quote/${ticker.symbol}/key-statistics?p=${ticker.symbol}")

        val mainDiv = driver.findElement(By.id("Main"))
        val measures = parseValuationMeasures(mainDiv)

        val headerInfo = driver.findElement(By.id("quote-header-info"))
        val companyName = headerInfo.findElement(By.xpath(".//h1")).text
        val price = headerInfo.findElement(By.xpath("./div[3]//span[1]")).text.toDoubleOrNull()
        val change = headerInfo.findElement(By.xpath("./div[3]//span[2]")).text

        val balanceSheet = mainDiv.findElement(By.xpath(".//div[h3/span='Balance Sheet']"))
        val totalCashPerShare = balanceSheet.findElement(By.xpath(".//tbody/tr[2]/td[2]")).text.toDoubleOrNull()
        val totalDebtEquity = balanceSheet.findElement(By.xpath(".//tbody/tr[4]/td[2]")).text.toDoubleOrNull()

        val incomeStatement = mainDiv.findElement(By.xpath(".//div[h3/span='Income Statement']"))
        val dilutedEPS = incomeStatement.findElement(By.xpath(".//tbody/tr[7]/td[2]")).text.toDoubleOrNull()
        val quarterlyRevenueGrowth = incomeStatement.findElement(By.xpath(".//tbody/tr[3]/td[2]")).text.let { parsePercentage(it) }
        val quarterlyEarningsGrowth = incomeStatement.findElement(By.xpath(".//tbody/tr[8]/td[2]")).text.let { parsePercentage(it) }

        val stockPriceHistory = mainDiv.findElement(By.xpath(".//div[h3/span='Stock Price History']"))

        val week52Change = stockPriceHistory.findElement(By.xpath(".//tbody/tr[2]/td[2]")).text.let { parsePercentage(it) }
        val week52Low = stockPriceHistory.findElement(By.xpath(".//tbody/tr[5]/td[2]")).text.toDoubleOrNull()
        val week52High = stockPriceHistory.findElement(By.xpath(".//tbody/tr[4]/td[2]")).text.toDoubleOrNull()

        return Statistics(
            companyName,
            price,
            change,
            totalCashPerShare,
            totalDebtEquity,
            quarterlyRevenueGrowth,
            quarterlyEarningsGrowth,
            dilutedEPS,
            week52Change,
            week52Low,
            week52High,
            measures
        )
    }

    private fun parseValuationMeasures(mainDiv: WebElement): MutableMap<String, PeriodMeasure> {
        val valuationMeasuresTable = mainDiv.findElements(By.xpath(".//*[h2/span='Valuation Measures']//table")).first()

        val headers = valuationMeasuresTable.findElements(By.xpath(".//thead/tr/th"))  // [empty]	Current	12/31/2019	9/30/2019	6/30/2019	3/31/2019
        val valueRows = valuationMeasuresTable.findElements(By.xpath(".//tbody/tr"))

        val measures = mutableMapOf<String, PeriodMeasure>()

        for (i in headers.indices) {
            if (i == 0) continue //header column

            var rowIndex = 0
            val columnIndex = i + 1 //xpath indexing starts at 1
            val period = headers[i].text.let { parseDate(it) }
            measures.put(period, PeriodMeasure(
                period = period,
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
        return measures
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

    /**
     * Converts 1.56T into a long 1 560 000 000 000
     */
    private fun parsePercentage(percentageNumber: String): Double? {
        return percentageNumber.dropLast(1).toDoubleOrNull()
    }

    /**
     * Converts 10/31/2019 into a string in ISO local date 2019-10-31
     */
    private fun parseDate(yahooDate: String): String {
        if (yahooDate == "Current") {
            return LocalDate.now().toString()
        }
        return LocalDate.parse(yahooDate, DateTimeFormatter.ofPattern("M/d/yyyy")).toString()
    }

    override fun close() {
        driver.close()
    }
}