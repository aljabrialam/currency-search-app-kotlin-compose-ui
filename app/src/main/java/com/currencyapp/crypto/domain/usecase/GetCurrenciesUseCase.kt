package com.currencyapp.crypto.domain.usecase

import com.currencyapp.crypto.data.repository.CurrencyRepository
import com.currencyapp.crypto.domain.model.CurrencyInfo

class GetCurrenciesUseCase(
    private val repo: CurrencyRepository
) {
    suspend fun byType(type: String): List<CurrencyInfo> = repo.getByType(type)
    suspend fun purchasables(): List<CurrencyInfo> = repo.getPurchasables()
    suspend fun clearAll() = repo.clearAll()
    suspend fun insertSeeds() = repo.insertSeeds()
}
