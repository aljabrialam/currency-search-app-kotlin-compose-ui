package com.currencyapp.crypto.data.repository

import com.currencyapp.crypto.data.Seeds
import com.currencyapp.crypto.data.local.CurrencyDao
import com.currencyapp.crypto.data.local.CurrencyEntity
import com.currencyapp.crypto.domain.model.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class CurrencyRepository(
    private val dao: CurrencyDao
) {
    private fun CurrencyEntity.toDomain() = CurrencyInfo(
        id = id, name = name, symbol = symbol, code = code,
        type = type, isPurchasable = isPurchasable
    )

    suspend fun clearAll() = withContext(Dispatchers.IO) {
        dao.clearAll()
    }

    suspend fun insertSeeds() = withContext(Dispatchers.IO) {
        dao.insertAll(Seeds.crypto + Seeds.fiat)
    }

    suspend fun seedIfEmpty() = withContext(Dispatchers.IO) {
        if (dao.count() == 0) dao.insertAll(Seeds.crypto + Seeds.fiat)
    }

    suspend fun getByType(type: String): List<CurrencyInfo> = withContext(Dispatchers.IO) {
        dao.getByType(type).map { it.toDomain() }
    }

    suspend fun getPurchasables(): List<CurrencyInfo> = withContext(Dispatchers.IO) {
        dao.getPurchasables().map { it.toDomain() }
    }
}
