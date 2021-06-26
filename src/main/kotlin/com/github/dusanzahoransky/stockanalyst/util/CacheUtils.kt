package com.github.dusanzahoransky.stockanalyst.util

import com.github.dusanzahoransky.stockanalyst.model.LastRefreshDate
import com.github.dusanzahoransky.stockanalyst.service.StockService
import java.time.LocalDate

class CacheUtils {

    data class CacheContext(
        val refreshDynamicData: Boolean = false,
        val refreshFinancials: Boolean = false,
        val mockData: Boolean,
        val refreshOlderThan: LocalDate
    )

    companion object {

        fun useCacheDynamicData(cacheCtx: CacheContext, refreshableEntity: LastRefreshDate?): Boolean {
            if (refreshableEntity == null) return false

            if (!cacheCtx.refreshDynamicData && !cacheCtx.refreshFinancials) return true

            return !refreshableEntity.getLastRefreshDate().isBefore(cacheCtx.refreshOlderThan)
        }

        fun useCacheFinancialsData(cacheCtx: CacheContext, refreshableEntity: LastRefreshDate?): Boolean {
            if (refreshableEntity == null) return false

            if (!cacheCtx.refreshFinancials) return true

            return !refreshableEntity.getLastRefreshDate().isBefore(cacheCtx.refreshOlderThan)
        }
    }
}
