package com.currencyapp.crypto.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencyapp.crypto.data.repository.CurrencyRepository
import com.currencyapp.crypto.domain.model.CurrencyInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CurrencyListState(
    val items: List<CurrencyInfo> = emptyList(),
    val loading: Boolean = false,
    val query: String = ""
)

class CurrencyListViewModel(
    private val repo: CurrencyRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CurrencyListState())
    val state: StateFlow<CurrencyListState> = _state

    fun loadCrypto() = viewModelScope.launch {
        _state.update { it.copy(loading = true) }
        val data = repo.getByType("CRYPTO")
        _state.update { it.copy(items = data, loading = false) }
    }

    fun loadFiat() = viewModelScope.launch {
        _state.update { it.copy(loading = true) }
        val data = repo.getByType("FIAT")
        _state.update { it.copy(items = data, loading = false) }
    }

    fun loadPurchasables() = viewModelScope.launch {
        _state.update { it.copy(loading = true) }
        val data = repo.getPurchasables()
        _state.update { it.copy(items = data, loading = false) }
    }

    fun clearDb() = viewModelScope.launch {
        repo.clearAll()
        _state.update { it.copy(items = emptyList()) }
    }

    fun insertSeeds() = viewModelScope.launch {
        repo.insertSeeds()
        // Optionally refresh after seeding
        _state.update { it.copy(items = repo.getPurchasables()) }
    }

    fun updateSearchQuery(query: String) = viewModelScope.launch {
        _state.update { it.copy(query = query) }

        val allItems = repo.getByType("CRYPTO") + repo.getByType("FIAT")
        val filtered = if (query.isBlank()) allItems
        else allItems.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.symbol.contains(query, ignoreCase = true)
        }

        _state.update { it.copy(items = filtered) }
    }
}
