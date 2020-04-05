package com.github.dzahoransky.stocks.analyst.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.dzahoransky.stocks.analyst.model.StockInfo
import com.github.dzahoransky.stocks.analyst.model.StockTicker
import com.github.dzahoransky.stocks.analyst.model.yahoo.PeriodMeasure
import com.github.dzahoransky.stocks.analyst.model.yahoo.Statistics
import org.openqa.selenium.By
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.random.nextInt

class StockScrapperService : AutoCloseable{

    private val driver: ChromeDriver

    init {
        System.setProperty("webdriver.chrome.driver", File(this.javaClass.getResource("/chromedriver").toURI()).absolutePath)
        val options = ChromeOptions()
        //To solve an issue with: Timed out receiving message from renderer: 0.100 https://stackoverflow.com/questions/48450594/selenium-timed-out-receiving-message-from-renderer
        options.setPageLoadStrategy(PageLoadStrategy.EAGER)
        options.addArguments("--headless") // only if you are ACTUALLY running headless

        driver = ChromeDriver(options)
        driver.manage().timeouts().setScriptTimeout(3000, TimeUnit.SECONDS)
    }

    fun scrape(tickers: List<StockTicker>): List<StockInfo> {
        val random = Random(System.currentTimeMillis())
        val stockInfo = mutableListOf<StockInfo>()
        for(ticker in tickers){
            try {
                stockInfo.add(scrapeYahoo(ticker))
            } catch (e: Exception){
                println("Failed to process ${ticker.symbol}: ${e.message}")
            }
            Thread.sleep(random.nextLong() % 1000)   //hopefully will avoid Yahoo detecting robot calls
        }
        return tickers.map { ticker -> scrapeYahoo(ticker) }
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

        for (i in headers.indices){
            if(i == 0) continue //header column

            var rowIndex = 0
            val columnIndex = i +1 //xpath indexing starts at 1
            measures.add(PeriodMeasure(
                period = headers[i].text,
                marketCap = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text,
                enterpriseValue = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text,
                trailingPE = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toFloat(),
                forwardPE = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toFloat(),
                priceEarningGrowth = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toFloat(),
                priceSales = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toFloat(),
                priceBook = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toFloat(),
                enterpriseValueRevenue = valueRows[rowIndex++].findElement(By.xpath("./td[$columnIndex]")).text.toFloat(),
                enterpriseValueEBITDA = valueRows[rowIndex].findElement(By.xpath("./td[$columnIndex]")).text.toFloat()
            ))
        }

        return Statistics(measures)
    }

    override fun close() {
        driver.close()
    }
}