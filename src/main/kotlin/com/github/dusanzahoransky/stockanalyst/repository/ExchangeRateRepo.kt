package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.enums.Currency
import com.github.dusanzahoransky.stockanalyst.model.mongo.ExchangeRate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ExchangeRateRepo : MongoRepository<ExchangeRate, Currency> {
    fun findByTarget(target: Currency): ExchangeRate?
}
