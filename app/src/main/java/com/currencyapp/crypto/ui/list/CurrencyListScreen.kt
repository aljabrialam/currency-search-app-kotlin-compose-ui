package com.currencyapp.crypto.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.currencyapp.crypto.domain.model.CurrencyInfo
import com.currencyapp.crypto.ui.CurrencyRow
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrencyListScreen(
    onOpenSearch: () -> Unit,
    vm: CurrencyListViewModel = koinViewModel()
) {
    val state by vm.state.collectAsState()
    var query by remember { mutableStateOf("") }

    // Track the current title based on the selected list
    var title by remember { mutableStateOf("Purchasable (A + B)") }

    // Seed DB on initial load
    LaunchedEffect(Unit) {
        vm.insertSeeds()
        vm.loadPurchasables() // load initial list
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Top row: Dynamic title + Search button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            TextButton(onClick = onOpenSearch,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
            ) { Text("Search") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Currency list
        val filteredItems = if (query.isBlank()) state.items else state.items.filter {
            it.name.contains(query, ignoreCase = true) || it.symbol.contains(query, ignoreCase = true)
        }

        Box(modifier = Modifier.weight(1f)) {
            when {
                state.loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
                filteredItems.isEmpty() -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(if (query.isBlank()) "No Data" else "No Results")
                }
                else -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(filteredItems) { currency ->
                        CurrencyRow(currency)
                        Divider()
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Always visible 5 action buttons

        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {

            OutlinedButton(
                onClick = {
                    vm.clearDb()
                    title = "Purchasable (A + B)"
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                shape = RoundedCornerShape(6.dp) // slightly square
            ) { Text("1) Clear DB") }

            OutlinedButton(
                onClick = {
                    vm.insertSeeds()
                    title = "Purchasable (A + B)"
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                shape = RoundedCornerShape(6.dp)
            ) { Text("2) Insert Data") }

            OutlinedButton(
                onClick = {
                    vm.loadCrypto()
                    title = "Currency List A - Crypto"
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                shape = RoundedCornerShape(6.dp)
            ) { Text("3) Show A (Crypto)") }

            OutlinedButton(
                onClick = {
                    vm.loadFiat()
                    title = "Currency List B - Fiat"
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                shape = RoundedCornerShape(6.dp)
            ) { Text("4) Show B (Fiat)") }

            OutlinedButton(
                onClick = {
                    vm.loadPurchasables()
                    title = "Purchasable (A + B)"
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                shape = RoundedCornerShape(6.dp)
            ) { Text("5) Purchasable") }
        }

    }
}

