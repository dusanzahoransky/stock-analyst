package com.github.dusanzahoransky.stockanalyst.model.exchangerates

import com.fasterxml.jackson.annotation.JsonAnySetter
import java.util.*

class Rates (
    @JsonAnySetter val values: Map<String, Double> = HashMap()
)