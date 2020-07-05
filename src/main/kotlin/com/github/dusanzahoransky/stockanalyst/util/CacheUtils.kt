package com.github.dusanzahoransky.stockanalyst.util

import com.github.dusanzahoransky.stockanalyst.model.LastRefreshDate
import com.github.dusanzahoransky.stockanalyst.service.StockService
import java.time.LocalDate

class CacheUtils {

    data class CacheContext(
        val refreshDynamicData: Boolean,
        val refreshFinancials: Boolean,
        val mockData: Boolean,
        val refreshOlderThan: LocalDate
    )

    companion object {
        fun useCache(forceRefreshCache: Boolean, refreshableEntity: LastRefreshDate?, forceRefreshDate: LocalDate): Boolean {
            if (refreshableEntity == null) return false

            if (!forceRefreshCache) return true

            val lastRefreshDate = refreshableEntity.getLastRefreshDate()

            return !lastRefreshDate.isBefore(forceRefreshDate)
        }

        fun useCacheDynamicData(cacheCtx: CacheContext, refreshableEntity: LastRefreshDate?): Boolean {
            if (refreshableEntity == null) return false

            if (!cacheCtx.refreshDynamicData) return true

            return !refreshableEntity.getLastRefreshDate().isBefore(cacheCtx.refreshOlderThan)
        }

        fun useCacheFinancialsData(cacheCtx: CacheContext, refreshableEntity: LastRefreshDate?): Boolean {
            if (refreshableEntity == null) return false

            if (!cacheCtx.refreshFinancials || !cacheCtx.refreshDynamicData) return true

            return !refreshableEntity.getLastRefreshDate().isBefore(cacheCtx.refreshOlderThan)
        }
    }
}
