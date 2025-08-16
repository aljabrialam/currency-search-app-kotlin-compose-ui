package com.currencyapp.crypto.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val code: String? = null,
    val type: String, // "CRYPTO" | "FIAT"
    val isPurchasable: Boolean
)
