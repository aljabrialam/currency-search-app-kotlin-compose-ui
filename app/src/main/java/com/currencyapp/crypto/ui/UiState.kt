package com.currencyapp.crypto.ui

import com.currencyapp.crypto.data.local.CurrencyEntity


data class UiState(
    val currencies: List<CurrencyEntity> = emptyList(),
    val selectedCurrency: CurrencyEntity? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)