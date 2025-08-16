package com.currencyapp.crypto.domain.model

data class CurrencyInfo(
    val id: String,
    val name: String,
    val symbol: String,
    val code: String? = null,
    val type: String, // "CRYPTO" | "FIAT"
    val isPurchasable: Boolean
)
