#!/bin/bash
set -e

PKG="com/currencyapp/crypto"
BASE="app/src/main/java/$PKG"

mkdir -p "$BASE/di" \
         "$BASE/data/local" \
         "$BASE/data/repository" \
         "$BASE/domain/model" \
         "$BASE/domain/usecase" \
         "$BASE/ui/list" \
         "$BASE/ui/search"

touch settings.gradle.kts build.gradle.kts
mkdir -p app/src/main/res/values
mkdir -p app/src/main

# Kotlin files
touch "$BASE/App.kt"
touch "$BASE/di/AppModule.kt"
touch "$BASE/data/local/CurrencyEntity.kt"
touch "$BASE/data/local/CurrencyDao.kt"
touch "$BASE/data/local/AppDatabase.kt"
touch "$BASE/data/repository/CurrencyRepository.kt"
touch "$BASE/data/Seeds.kt"
touch "$BASE/domain/model/CurrencyInfo.kt"
touch "$BASE/domain/usecase/GetCurrenciesUseCase.kt"
touch "$BASE/domain/usecase/SearchCurrencyUseCase.kt"
touch "$BASE/ui/list/CurrencyListViewModel.kt"
touch "$BASE/ui/list/CurrencyListScreen.kt"
touch "$BASE/ui/search/CurrencySearchViewModel.kt"
touch "$BASE/ui/search/CurrencySearchScreen.kt"
touch "$BASE/../MainActivity.kt"

# Manifest
mkdir -p app/src/main
touch app/src/main/AndroidManifest.xml

echo "Structure created. Paste code into the created files."
