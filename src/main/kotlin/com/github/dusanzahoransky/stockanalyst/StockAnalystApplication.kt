package com.github.dusanzahoransky.stockanalyst

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.github.dusanzahoransky.stockanalyst"
    ]
)
class StockAnalystApplication

fun main(args: Array<String>) {
    runApplication<StockAnalystApplication>(*args)
}

