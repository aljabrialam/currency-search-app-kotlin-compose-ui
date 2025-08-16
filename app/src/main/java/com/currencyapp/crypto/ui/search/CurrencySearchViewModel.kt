package com.currencyapp.crypto.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencyapp.crypto.domain.model.CurrencyInfo
import com.currencyapp.crypto.domain.usecase.GetCurrenciesUseCase
import com.currencyapp.crypto.domain.usecase.SearchCurrencyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class SearchUiState(
    val all: List<CurrencyInfo> = emptyList(),
    val query: String = "",
    val filtered: List<CurrencyInfo> = emptyList(),
    val loading: Boolean = true
)

class CurrencySearchViewModel(
    private val getCurrencies: GetCurrenciesUseCase,
    private val search: SearchCurrencyUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state: StateFlow<SearchUiState> = _state

    init {
        // Load everything (Crypto + Fiat) to match across both lists
        viewModelScope.launch {
            val crypto = getCurrencies.byType("CRYPTO")
            val fiat = getCurrencies.byType("FIAT")
            val all = crypto + fiat
            _state.value = _state.value.copy(all = all, filtered = all, loading = false)
        }
    }

    fun updateQuery(q: String) = viewModelScope.launch {
        _state.value = _state.value.copy(query = q)
        val results = search(_state.value.all, q)
        _state.value = _state.value.copy(filtered = results)
    }
}
