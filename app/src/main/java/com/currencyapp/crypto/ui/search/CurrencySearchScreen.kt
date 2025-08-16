package com.currencyapp.crypto.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.currencyapp.crypto.domain.model.CurrencyInfo
import com.currencyapp.crypto.ui.CurrencyRow
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrencySearchScreen(
    onBack: () -> Unit,
    vm: CurrencySearchViewModel = koinViewModel()
) {
    val state by vm.state.collectAsState()
    var query by remember { mutableStateOf(state.query) }

    Column(modifier = Modifier.fillMaxSize()) {

        // Search bar like React Native
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            TextButton(onClick = {
                query = ""
                vm.updateQuery("")
                onBack()
            }) { Text("←") } // Back

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                    vm.updateQuery(it)
                },
                placeholder = { Text("Search by name or symbol...") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            TextButton(onClick = {
                query = ""
                vm.updateQuery("")
            }) { Text("✕") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filtered list
        val filteredItems = state.filtered

        if (state.loading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (filteredItems.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(if (query.isBlank()) "No Data" else "No Results \nTry 'BTC'")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredItems) { currency: CurrencyInfo ->
                    CurrencyRow(currency)
                    Divider()
                }
            }
        }
    }
}
