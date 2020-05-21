package com.github.dusanzahoransky.stockanalyst.model.mongo

import com.github.dusanzahoransky.stockanalyst.model.ms.keyratios.Result
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class KeyRatiosFinancials(
    @Id var id: String? = null,
    var results: List<Result> = ArrayList()
)