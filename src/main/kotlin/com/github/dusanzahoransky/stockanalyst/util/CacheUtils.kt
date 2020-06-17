package com.github.dusanzahoransky.stockanalyst.util

import com.github.dusanzahoransky.stockanalyst.model.LastRefreshDate
import java.time.LocalDate

class CacheUtils {
    companion object {
        fun useCache(forceRefreshCache: Boolean, refreshableEntity: LastRefreshDate?, forceRefreshDate: LocalDate): Boolean {
            if (refreshableEntity == null) return false

            if (!forceRefreshCache) return true

            val lastRefreshDate = refreshableEntity.getLastRefreshDate()

            return !lastRefreshDate.isBefore(forceRefreshDate)
        }
    }
}
