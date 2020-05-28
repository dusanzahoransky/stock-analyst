package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.mongo.Ratios
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatiosTimeline
import com.github.dusanzahoransky.stockanalyst.repository.StockRepo
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.cumulativeGrowthRate
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.minus
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.plus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class KeyRatiosAnalysisService(
    val stockRepo: StockRepo
) {

    companion object {
        const val MILLION = 1000000.0
        const val THOUSAND = 1000.0
    }

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun calcRule1(stockInfoList: List<StockInfo>, ratiosList: List<StockRatiosTimeline>) {
        ratiosList.forEach { calcRule1(it, stockInfoList) }
    }

    fun calcRule1(ratios: StockRatiosTimeline, stockInfoList: List<StockInfo>) {
        ratios.periods = ratios.periods.toSortedMap(compareByDescending { it })
        val periods = removeNonWholeYear(ratios.periods)

        val current = periodYearsBefore(periods, 0)
        val oneYBefore = periodYearsBefore(periods, 1)
        val threeYBefore = periodYearsBefore(periods, 3)
        val fiveYBefore = periodYearsBefore(periods, 5)
        val nineYBefore = periodYearsBefore(periods, 9)

        val stockInfo = stockInfoList.firstOrNull { StockTicker(it.symbol, it.exchange) == StockTicker.fromSymbolAndMic(ratios.symbol, ratios.mic) }

        if(stockInfo == null){
            log.debug("Failed to compute Rule1, stock is missing ${ratios.symbol}, ${ratios.mic}")
            return
        }

        stockInfo.revenue1Y = cumulativeGrowthRate(current?.revenue, oneYBefore?.revenue, 1, "revenue1Y", MILLION)
        stockInfo.revenue3Y = cumulativeGrowthRate(current?.revenue, threeYBefore?.revenue, 3, "revenue3Y", MILLION)
        stockInfo.revenue5Y = cumulativeGrowthRate(current?.revenue, fiveYBefore?.revenue, 5, "revenue5Y", MILLION)
        stockInfo.revenue9Y = cumulativeGrowthRate(current?.revenue, nineYBefore?.revenue, 9, "revenue9Y", MILLION)

        stockInfo.eps1Y = cumulativeGrowthRate(current?.earningsPerShare, oneYBefore?.earningsPerShare, 1, "eps1Y", 0.1)
        stockInfo.eps3Y = cumulativeGrowthRate(current?.earningsPerShare, threeYBefore?.earningsPerShare, 3, "eps3Y", 0.1)
        stockInfo.eps5Y = cumulativeGrowthRate(current?.earningsPerShare, fiveYBefore?.earningsPerShare, 5, "eps5Y", 0.1)
        stockInfo.eps9Y = cumulativeGrowthRate(current?.earningsPerShare, nineYBefore?.earningsPerShare, 9, "eps9Y", 0.1)


        stockInfo.bps1Y = cumulativeGrowthRate(current?.bookValuePerShare, oneYBefore?.bookValuePerShare, 1, "bps1Y", 0.1)
        stockInfo.bps3Y = cumulativeGrowthRate(current?.bookValuePerShare, threeYBefore?.bookValuePerShare, 3, "bps3Y", 0.1)
        stockInfo.bps5Y = cumulativeGrowthRate(current?.bookValuePerShare, fiveYBefore?.bookValuePerShare, 5, "bps5Y", 0.1)
        stockInfo.bps9Y = cumulativeGrowthRate(current?.bookValuePerShare, nineYBefore?.bookValuePerShare, 9, "bps9Y", 0.1)


        stockInfo.cash1Y = cumulativeGrowthRate(current?.freeCashFlow, oneYBefore?.freeCashFlow, 1, "cash1Y", THOUSAND)
        stockInfo.cash3Y = cumulativeGrowthRate(current?.freeCashFlow, threeYBefore?.freeCashFlow, 3, "cash3Y", THOUSAND)
        stockInfo.cash5Y = cumulativeGrowthRate(current?.freeCashFlow, fiveYBefore?.freeCashFlow, 5, "cash5Y", THOUSAND)
        stockInfo.cash9Y = cumulativeGrowthRate(current?.freeCashFlow, nineYBefore?.freeCashFlow, 9, "cash9Y", THOUSAND)

        val roicCurrent = div(
            minus(current?.netIncome, current?.dividends),
            plus(stockInfo.totalLiabilitiesLastYear?.toDouble(), stockInfo.totalShareholdersEquityLastYear?.toDouble())
        )
        val roic1YBefore = div(
            minus(oneYBefore?.netIncome, oneYBefore?.dividends),
            plus(stockInfo.totalLiabilities2YearsAgo?.toDouble(), stockInfo.totalShareholdersEquity2YearsAgo?.toDouble())
        )
        val roic3YBefore = div(
            minus(oneYBefore?.netIncome, oneYBefore?.dividends),
            plus(stockInfo.totalLiabilities4YearsAgo?.toDouble(), stockInfo.totalShareholdersEquity4YearsAgo?.toDouble())
        )
        stockInfo.roic1Y = cumulativeGrowthRate(roicCurrent, roic1YBefore, 1, "roic1Y", 0.01)
        stockInfo.roic3Y = cumulativeGrowthRate(roicCurrent, roic3YBefore, 3, "roic3Y", 0.01)
    }

    private fun removeNonWholeYear(periods: SortedMap<LocalDate, Ratios>): SortedMap<LocalDate, Ratios> {
        val present = periods.firstKey()
        return periods.filterKeys { ChronoUnit.MONTHS.between(it, present) % 12 == 0L }.toSortedMap( compareByDescending { it })
    }

    private fun periodYearsBefore(periods: SortedMap<LocalDate, Ratios>, yearsBeforePresent: Int): Ratios? {
        val present = periods.firstKey()
        if(yearsBeforePresent == 0){
            return periods[present]
        }
        val yearsBeforeKey = periods.keys.firstOrNull { ChronoUnit.YEARS.between(it, present) == yearsBeforePresent.toLong() }

        return yearsBeforeKey.let { periods[yearsBeforeKey] }
    }

}