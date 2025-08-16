package com.currencyapp.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.currencyapp.crypto.ui.list.CurrencyListScreen
import com.currencyapp.crypto.ui.search.CurrencySearchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNav()
            }
        }
    }
}

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "list") {
        composable("list") {
            CurrencyListScreen(
                onOpenSearch = { navController.navigate("search") }
            )
        }
        composable("search") {
            CurrencySearchScreen(onBack = { navController.popBackStack() })
        }
    }
}
