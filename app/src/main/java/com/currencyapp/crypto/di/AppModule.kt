package com.currencyapp.crypto.di

import androidx.room.Room
import com.currencyapp.crypto.data.local.AppDatabase
import com.currencyapp.crypto.data.repository.CurrencyRepository
import com.currencyapp.crypto.domain.usecase.GetCurrenciesUseCase
import com.currencyapp.crypto.domain.usecase.SearchCurrencyUseCase
import com.currencyapp.crypto.ui.list.CurrencyListViewModel
import com.currencyapp.crypto.ui.search.CurrencySearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "currencies_a.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // DAO
    single { get<AppDatabase>().currencyDao() }

    // Repository
    single { CurrencyRepository(get()) }

    // Use cases
    single { GetCurrenciesUseCase(get()) }
    single { SearchCurrencyUseCase() }

    // ViewModels
    viewModel { CurrencyListViewModel(get()) }
    viewModel { CurrencySearchViewModel(get(), get()) }
}
