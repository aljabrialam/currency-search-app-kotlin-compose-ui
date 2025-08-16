package com.currencyapp.crypto

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.currencyapp.crypto.data.local.CurrencyDao
import com.currencyapp.crypto.data.local.CurrencyEntity
import com.currencyapp.crypto.data.repository.CurrencyRepository
import com.currencyapp.crypto.ui.list.CurrencyListScreen
import com.currencyapp.crypto.ui.list.CurrencyListViewModel
import org.junit.Rule
import org.junit.Test

class FakeCurrencyRepository : CurrencyRepository(
    dao = object : CurrencyDao {
        override suspend fun clearAll() {}
        override suspend fun insertAll(items: List<CurrencyEntity>) {}
        override suspend fun count(): Int = 3
        override suspend fun getByType(type: String): List<CurrencyEntity> {
            return when(type) {
                "CRYPTO" -> listOf(
                    CurrencyEntity("1", "Bitcoin", "BTC", "BTC", "CRYPTO", true),
                    CurrencyEntity("2", "Ethereum", "ETH", "ETH", "CRYPTO", true)
                )
                "FIAT" -> listOf(
                    CurrencyEntity("3", "US Dollar", "USD", "USD", "FIAT", true)
                )
                else -> emptyList()
            }
        }
        override suspend fun getPurchasables(): List<CurrencyEntity> {
            return listOf(
                CurrencyEntity("1", "Bitcoin", "BTC", "BTC", "CRYPTO", true),
                CurrencyEntity("2", "Ethereum", "ETH", "ETH", "CRYPTO", true),
                CurrencyEntity("3", "US Dollar", "USD", "USD", "FIAT", true)
            )
        }
    }
)


class CurrencyListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTitleUpdatesOnButtonClicks() {
        val fakeVm = CurrencyListViewModel(repo = FakeCurrencyRepository())

        composeTestRule.setContent {
            CurrencyListScreen(
                onOpenSearch = { /* no-op for test */ },
                vm = fakeVm
            )
        }

        composeTestRule.onNodeWithText("Purchasable (A + B)").assertIsDisplayed()

        composeTestRule.onNodeWithText("3) Show A (Crypto)").performClick()
        composeTestRule.onNodeWithText("Currency List A - Crypto").assertIsDisplayed()

        composeTestRule.onNodeWithText("4) Show B (Fiat)").performClick()
        composeTestRule.onNodeWithText("Currency List B - Fiat").assertIsDisplayed()

        composeTestRule.onNodeWithText("5) Purchasable").performClick()
        composeTestRule.onNodeWithText("Purchasable (A + B)").assertIsDisplayed()
    }
}
