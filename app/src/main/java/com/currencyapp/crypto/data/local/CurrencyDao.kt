package com.currencyapp.crypto.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<CurrencyEntity>)

    @Query("DELETE FROM currencies")
    suspend fun clearAll()

    @Query("SELECT * FROM currencies WHERE type = :type ORDER BY name COLLATE NOCASE ASC")
    suspend fun getByType(type: String): List<CurrencyEntity>

    @Query("SELECT * FROM currencies WHERE isPurchasable = 1 ORDER BY type, name COLLATE NOCASE ASC")
    suspend fun getPurchasables(): List<CurrencyEntity>

    @Query("SELECT COUNT(*) FROM currencies")
    suspend fun count(): Int
}
