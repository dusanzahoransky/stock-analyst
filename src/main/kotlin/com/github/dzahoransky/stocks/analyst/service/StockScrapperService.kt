package com.github.dzahoransky.stocks.analyst.service

import com.github.dzahoransky.stocks.analyst.model.Exchange.*
import com.github.dzahoransky.stocks.analyst.model.StockInfo
import com.github.dzahoransky.stocks.analyst.model.StockTicker
import com.github.dzahoransky.stocks.analyst.model.yahoo.PeriodMeasure
import com.github.dzahoransky.stocks.analyst.model.yahoo.Statistics
import org.openqa.selenium.*
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.slf4j.LoggerFactory
import java.io.File
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class StockScrapperService : AutoCloseable {
    companion object {
        const val MILLION = 1000L * 1000L
        const val BILLION = MILLION * 1000L
        const val TRILLION = BILLION * 1000L
    }

    private val driver: RemoteWebDriver
    private val logger = LoggerFactory.getLogger(this::class.java)

    init {
        System.setProperty("webdriver.chrome.driver", File("src/main/resources/chromedriver").absolutePath)
        val options = ChromeOptions()
        //To solve an issue with: Timed out receiving message from renderer: 0.100 https://stackoverflow.com/questions/48450594/selenium-timed-out-receiving-message-from-renderer
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL)
        options.addArguments("--headless")

        driver = ChromeDriver(options)
//        System.setProperty("webdriver.gecko.driver", File("src/main/resources/geckodriver").absolutePath)
//        driver = FirefoxDriver()
    }

    /**
     * @ticker a combination of exchange and stock name such as ASX:VTS, NASDAQ:MSFT
     */
    fun scrape(tickers: List<String>): List<StockInfo> {
        val stockInfo = mutableListOf<StockInfo>()
        for (ticker in tickers) {
            try {
                logger.debug("Scraping ticker $ticker")
                stockInfo.add(scrapeYahoo(StockTicker.fromString(ticker)))
            } catch (e: Exception) {
                logger.error("Failed to process ${ticker}: ${e.message}")
            }
            Thread.sleep(7000)   //avoid hitting hit rate limit
        }
        return stockInfo
    }

    fun toYahooTickerFormat(ticker: StockTicker): String {
        val (symbol, exchange) = ticker
        return when (exchange) {
            ASX -> "$symbol.AX"
            DAX -> "$symbol.DE"
            SIX -> "$symbol.SW"
            FTSE -> "$symbol.L"
            else -> symbol
        }
    }

    fun scrapeYahoo(ticker: StockTicker): StockInfo {
        val yahooTickerFormat = toYahooTickerFormat(ticker)
        val statistics = parseStatistics(yahooTickerFormat)
        val timestamp = ZonedDateTime.now()
        return StockInfo(null,
            timestamp,
            ticker.symbol,
            ticker.exchange,
            statistics.companyName,
            statistics)
    }

    private fun parseStatistics(ticker: String): Statistics {
        val statisticsUrl = "https://finance.yahoo.com/quote/${ticker}/key-statistics?p=${ticker}"
        logger.debug("Fetching statistics: $statisticsUrl")
        driver.get(statisticsUrl)

        waitToLoadJs()

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

    /**
     * Avoid https://www.selenium.dev/exceptions/#stale_element_reference.html
     */
    private fun waitToLoadJs() {
        val wait = WebDriverWait(driver, 10)
        Thread.sleep(3000)
        wait.until {
            fun apply(driver: WebDriver): Boolean {
                return (driver as JavascriptExecutor).executeScript("return document.readyState") == "complete"
            }
        }
    }

    private fun parseValuationMeasures(mainDiv: WebElement): MutableMap<String, PeriodMeasure> {
        val statisticsValuationMeasuresTable = mainDiv.findElements(By.xpath(".//*[h2/span='Valuation Measures']//table")).first()
        val headers = statisticsValuationMeasuresTable.findElements(By.xpath(".//thead/tr/th"))  // [empty]	Current	12/31/2019	9/30/2019	6/30/2019	3/31/2019
        val valueRows = statisticsValuationMeasuresTable.findElements(By.xpath(".//tbody/tr"))

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