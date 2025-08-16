package com.currencyapp.crypto

import android.app.Application
import com.currencyapp.crypto.data.repository.CurrencyRepository
import com.currencyapp.crypto.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class App : Application(), KoinComponent {
    private val appScope = CoroutineScope(SupervisorJob())

    private val currencyRepository: CurrencyRepository by inject()

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }

        // Seed DB in the background (idempotent)
        appScope.launch {
            currencyRepository.seedIfEmpty()
        }
    }
}
