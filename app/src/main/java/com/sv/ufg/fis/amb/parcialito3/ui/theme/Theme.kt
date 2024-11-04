package com.sv.ufg.fis.amb.parcialito3.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = LightBlue,
    secondary = Teal,
    background = Color.Black,
    onPrimary = White,
    onSecondary = Color.Black,
    onBackground = LightGray
)

private val LightColorScheme = lightColorScheme(
    primary = DarkBlue,
    secondary = LightBlue,
    background = LightGray,
    onPrimary = White,
    onSecondary = Color.White,
    onBackground = Color.Black
)

@Composable
fun Parcialito3Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
