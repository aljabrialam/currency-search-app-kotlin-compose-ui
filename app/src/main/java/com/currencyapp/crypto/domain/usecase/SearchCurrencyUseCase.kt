package com.currencyapp.crypto.domain.usecase

import com.currencyapp.crypto.domain.model.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

/**
 * Match rules:
 * 1) name startsWith(query)
 * 2) name contains(" " + query)  (space-prefixed partial)
 * 3) symbol startsWith(query)
 */
class SearchCurrencyUseCase {
    suspend operator fun invoke(
        items: List<CurrencyInfo>,
        query: String
    ): List<CurrencyInfo> = withContext(Dispatchers.Default) {
        if (query.isBlank()) return@withContext items
        val q = query.trim().lowercase(Locale.getDefault())

        items.filter { c ->
            val n = c.name.lowercase(Locale.getDefault())
            val s = c.symbol.lowercase(Locale.getDefault())
            n.startsWith(q) ||
                    n.contains(" $q") ||
                    s.startsWith(q)
        }
    }
}
