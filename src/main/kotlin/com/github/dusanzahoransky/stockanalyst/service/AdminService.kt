package com.github.dusanzahoransky.stockanalyst.service

import com.github.dusanzahoransky.stockanalyst.repository.StockRepo
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.div
import com.github.dusanzahoransky.stockanalyst.util.CalcUtils.Companion.percent
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminService @Autowired constructor(
        val stockRepo: StockRepo
) {
    val log = LoggerFactory.getLogger(this::class.java)!!

    fun runDi() {
        fixInterestExpense()
        interestExpenseToOperativeIncome()
    }

    private fun interestExpenseToOperativeIncome() {
        val allStocks = stockRepo.findAll()
        for (stock in allStocks) {
            log.debug("Applying DI on $stock")
            for (entry in stock.interestExpenseToOperativeIncomeP) {
                stock.interestExpenseToOperativeIncomeP[entry.key] = percent(div(stock.interestExpense[entry.key], stock.operatingIncome[entry.key]))
            }
            for (entry in stock.interestExpenseToOperativeIncomePQ) {
                stock.interestExpenseToOperativeIncomePQ[entry.key] = percent(div(stock.interestExpenseQ[entry.key], stock.operatingIncomeQ[entry.key]))
            }
            stockRepo.save(stock)
        }
    }
    private fun fixInterestExpense() {
        val allStocks = stockRepo.findAll()
        for (stock in allStocks) {
            log.debug("Applying DI on $stock")
            for (entry in stock.interestExpense) {
                val interestExpense = stock.interestExpense[entry.key]
                if(interestExpense != null && interestExpense < 0) {
                    stock.interestExpense[entry.key] = entry.value?.let { it * -1 }
                }
            }
            for (entry in stock.interestExpenseQ) {
                val interestExpenseQ = stock.interestExpenseQ[entry.key]
                if(interestExpenseQ != null && interestExpenseQ < 0) {
                    stock.interestExpenseQ[entry.key] = entry.value?.let { it * -1 }
                }
            }
            stockRepo.save(stock)
        }
    }


}