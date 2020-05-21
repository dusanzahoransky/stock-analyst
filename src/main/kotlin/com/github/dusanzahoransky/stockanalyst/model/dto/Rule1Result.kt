package com.github.dusanzahoransky.stockanalyst.model.dto

import com.github.dusanzahoransky.stockanalyst.model.mongo.KeyRatiosFinancials

data class Rule1Result(
    var keyRatiosFinancials: List<KeyRatiosFinancials>
)