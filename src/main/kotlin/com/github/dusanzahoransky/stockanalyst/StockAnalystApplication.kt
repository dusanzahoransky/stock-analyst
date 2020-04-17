package com.github.dusanzahoransky.stockanalyst

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication(
	scanBasePackages = [
		"com.github.dusanzahoransky.stockanalyst.repository",
		"com.github.dusanzahoransky.stockanalyst.controller",
		"com.github.dusanzahoransky.stockanalyst.service",
		"com.github.dusanzahoransky.stockanalyst.client"
	]
)
class StockAnalystApplication

fun main(args: Array<String>) {
	runApplication<StockAnalystApplication>(*args)
}
