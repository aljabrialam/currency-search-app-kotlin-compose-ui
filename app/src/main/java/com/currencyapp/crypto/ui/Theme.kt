package com.currencyapp.crypto.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.currencyapp.crypto.ui.*

private val LightColors = lightColorScheme(
    primary = Black,
    onPrimary = White,
    secondary = Black,
    onSecondary = Black,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black
)

private val DarkColors = darkColorScheme(
    primary = Black,
    onPrimary = Black,
    secondary = Teal200,
    onSecondary = Black,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White
)

@Composable
fun CurrencyAppTheme(
    darkTheme: Boolean = false, // change to true to support system dark mode
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography(),
        content = content
    )
}
