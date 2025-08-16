package com.currencyapp.crypto.data

import com.currencyapp.crypto.data.local.CurrencyEntity

object Seeds {
    val crypto: List<CurrencyEntity> = listOf(
        CurrencyEntity("BTC", "Bitcoin", "BTC", null, "CRYPTO", true),
        CurrencyEntity("ETH", "Ethereum", "ETH", null, "CRYPTO", true),
        CurrencyEntity("XRP", "XRP", "XRP", null, "CRYPTO", true),
        CurrencyEntity("BCH", "Bitcoin Cash", "BCH", null, "CRYPTO", true),
        CurrencyEntity("LTC", "Litecoin", "LTC", null, "CRYPTO", true),
        CurrencyEntity("EOS", "EOS", "EOS", null, "CRYPTO", true),
        CurrencyEntity("BNB", "Binance Coin", "BNB", null, "CRYPTO", true),
        CurrencyEntity("LINK", "Chainlink", "LINK", null, "CRYPTO", true),
        CurrencyEntity("NEO", "NEO", "NEO", null, "CRYPTO", true),
        CurrencyEntity("ETC", "Ethereum Classic", "ETC", null, "CRYPTO", true),
        CurrencyEntity("ONT", "Ontology", "ONT", null, "CRYPTO", true),
        CurrencyEntity("CRO", "Crypto.com Chain", "CRO", null, "CRYPTO", true),
        CurrencyEntity("CUC", "Cucumber", "CUC", null, "CRYPTO", false),
        CurrencyEntity("USDC", "USD Coin", "USDC", null, "CRYPTO", true),
    )

    val fiat: List<CurrencyEntity> = listOf(
        CurrencyEntity("SGD", "Singapore Dollar", "$", "SGD", "FIAT", true),
        CurrencyEntity("EUR", "Euro", "€", "EUR", "FIAT", true),
        CurrencyEntity("GBP", "British Pound", "£", "GBP", "FIAT", true),
        CurrencyEntity("HKD", "Hong Kong Dollar", "$", "HKD", "FIAT", true),
        CurrencyEntity("JPY", "Japanese Yen", "¥", "JPY", "FIAT", true),
        CurrencyEntity("AUD", "Australian Dollar", "$", "AUD", "FIAT", true),
        CurrencyEntity("USD", "United States Dollar", "$", "USD", "FIAT", true),
    )
}
