package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.mongo.Ratios
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockInfo
import com.github.dusanzahoransky.stockanalyst.model.mongo.StockRatiosTimeline
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.average
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.cumulativeGrowthRate
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.min
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.minus
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.multiply
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percent
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.plus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.pow

@Service
class KeyRatiosAnalysisService {

    companion object {
        const val MILLION = 1000000.0
        const val THOUSAND = 1000.0
    }

    val log = LoggerFactory.getLogger(this::class.java)!!

    fun calcRule1(stockInfoList: List<StockInfo>, ratiosList: List<StockRatiosTimeline>) {
        ratiosList.forEach { calcRule1(it, stockInfoList) }
    }

    fun calcRule1(ratios: StockRatiosTimeline, stockInfoList: List<StockInfo>) {
        val periods = ratios.periods.toSortedMap(compareByDescending { it })

        val current = periodYearsBefore(periods, 0)
        val oneYBefore = periodYearsBefore(periods, 1)
        val threeYBefore = periodYearsBefore(periods, 3)
        val fiveYBefore = periodYearsBefore(periods, 5)
        val nineYBefore = periodYearsBefore(periods, 9)

        val stock = stockInfoList.firstOrNull { StockTicker(it.symbol, it.exchange) == StockTicker.fromSymbolAndMic(ratios.symbol, ratios.mic) }

        if (stock == null) {
            log.debug("Failed to compute Rule1, stock is missing ${ratios.symbol}, ${ratios.mic}")
            return
        }

        stock.revenue1Y = cumulativeGrowthRate(current?.revenue, oneYBefore?.revenue, 1, "revenue1Y", MILLION)
        stock.revenue3Y = cumulativeGrowthRate(current?.revenue, threeYBefore?.revenue, 3, "revenue3Y", MILLION)
        stock.revenue5Y = cumulativeGrowthRate(current?.revenue, fiveYBefore?.revenue, 5, "revenue5Y", MILLION)
        stock.revenue9Y = cumulativeGrowthRate(current?.revenue, nineYBefore?.revenue, 9, "revenue9Y", MILLION)

        stock.eps1Y = cumulativeGrowthRate(current?.earningsPerShare, oneYBefore?.earningsPerShare, 1, "eps1Y", 0.1)
        stock.eps3Y = cumulativeGrowthRate(current?.earningsPerShare, threeYBefore?.earningsPerShare, 3, "eps3Y", 0.1)
        stock.eps5Y = cumulativeGrowthRate(current?.earningsPerShare, fiveYBefore?.earningsPerShare, 5, "eps5Y", 0.1)
        stock.eps9Y = cumulativeGrowthRate(current?.earningsPerShare, nineYBefore?.earningsPerShare, 9, "eps9Y", 0.1)

        stock.bps1Y = cumulativeGrowthRate(current?.bookValuePerShare, oneYBefore?.bookValuePerShare, 1, "bps1Y", 0.1)
        stock.bps3Y = cumulativeGrowthRate(current?.bookValuePerShare, threeYBefore?.bookValuePerShare, 3, "bps3Y", 0.1)
        stock.bps5Y = cumulativeGrowthRate(current?.bookValuePerShare, fiveYBefore?.bookValuePerShare, 5, "bps5Y", 0.1)
        stock.bps9Y = cumulativeGrowthRate(current?.bookValuePerShare, nineYBefore?.bookValuePerShare, 9, "bps9Y", 0.1)

        stock.cash1Y = cumulativeGrowthRate(current?.freeCashFlow, oneYBefore?.freeCashFlow, 1, "cash1Y", THOUSAND)
        stock.cash3Y = cumulativeGrowthRate(current?.freeCashFlow, threeYBefore?.freeCashFlow, 3, "cash3Y", THOUSAND)
        stock.cash5Y = cumulativeGrowthRate(current?.freeCashFlow, fiveYBefore?.freeCashFlow, 5, "cash5Y", THOUSAND)
        stock.cash9Y = cumulativeGrowthRate(current?.freeCashFlow, nineYBefore?.freeCashFlow, 9, "cash9Y", THOUSAND)

        stock.pe1Y = cumulativeGrowthRate(current?.pe, oneYBefore?.pe, 1, "pe1Y", 0.1)
        stock.pe3Y = cumulativeGrowthRate(current?.pe, threeYBefore?.pe, 3, "pe3Y", 0.1)
        stock.pe5Y = cumulativeGrowthRate(current?.pe, fiveYBefore?.pe, 5, "pe5Y", 0.1)
        stock.pe9Y = cumulativeGrowthRate(current?.pe, nineYBefore?.pe, 9, "pe9Y", 0.1)


        val roicCurrent = div(
            minus(current?.netIncome, current?.dividends),
            plus(stock.totalLiabilitiesLastYear?.toDouble(), stock.totalShareholdersEquityLastYear?.toDouble())
        )
        val roic1YBefore = div(
            minus(oneYBefore?.netIncome, oneYBefore?.dividends),
            plus(stock.totalLiabilities2YearsAgo?.toDouble(), stock.totalShareholdersEquity2YearsAgo?.toDouble())
        )
        val roic3YBefore = div(
            minus(oneYBefore?.netIncome, oneYBefore?.dividends),
            plus(stock.totalLiabilities4YearsAgo?.toDouble(), stock.totalShareholdersEquity4YearsAgo?.toDouble())
        )
        stock.roic1Y = cumulativeGrowthRate(roicCurrent, roic1YBefore, 1, "roic1Y", 0.01)
        stock.roic3Y = cumulativeGrowthRate(roicCurrent, roic3YBefore, 3, "roic3Y", 0.01)

        val estimatedEpsGrowthRate = stock.bps9Y
        stock.rule1GrowthRate = min(stock.bps9Y, stock.growthEstimate5y)
        stock.defaultPE = multiply(estimatedEpsGrowthRate, 2.0)
        stock.historicalPE = average(current?.pe, oneYBefore?.pe, threeYBefore?.pe, fiveYBefore?.pe, nineYBefore?.pe)
        stock.rule1PE = if (stock.historicalPE != null && stock.historicalPE!! > 0) {
            min(stock.historicalPE, stock.defaultPE)
        } else {
            stock.defaultPE
        }
        stock.currentEps =  stock.epsLastYear ?: multiply(stock.epsLastQuarter, 4.0)
        val numberOfYear = 10.0
        val epsGrowthEstimate = plus(div(estimatedEpsGrowthRate, 100), 1.0)
        stock.futureEPS10Years = multiply(stock.currentEps, epsGrowthEstimate?.pow(numberOfYear))
        stock.futurePrice10Years = multiply(stock.rule1PE, stock.futureEPS10Years)

        stock.stickerPrice15pcGrowth = div(stock.futurePrice10Years, (1 + 0.15).pow(10))
        stock.stickerPrice10pcGrowth = div(stock.futurePrice10Years, (1 + 0.10).pow(10))
        stock.stickerPrice5pcGrowth = div(stock.futurePrice10Years, (1 + 0.05).pow(10))

        stock.belowStickerPrice15pc = percent(div(minus(stock.stickerPrice15pcGrowth, stock.price), stock.price))
        stock.belowStickerPrice10pc = percent(div(minus(stock.stickerPrice10pcGrowth, stock.price), stock.price))
        stock.belowStickerPrice5pc = percent(div(minus(stock.stickerPrice5pcGrowth, stock.price), stock.price))
    }

    private fun periodYearsBefore(periods: SortedMap<LocalDate, Ratios>, yearsBeforePresent: Int): Ratios? {
        val present = periods.firstKey()
        if (yearsBeforePresent == 0) {
            return periods[present]
        }
        val yearsBeforeKey = periods.keys.firstOrNull { ChronoUnit.YEARS.between(it, present) == yearsBeforePresent.toLong() }

        return yearsBeforeKey.let { periods[yearsBeforeKey] }
    }

}