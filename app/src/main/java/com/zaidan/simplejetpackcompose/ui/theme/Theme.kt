package com.zaidan.simplejetpackcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Blue700,
    primaryVariant = Blue500,
    onPrimary = White,
    secondary = Black1,
    onSecondary = White,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Black2,
    onBackground = White,
    surface = Black1,
    onSurface= White
)

private val LightColorPalette = lightColors(
    primary = Blue600,
    primaryVariant = Blue400,
    onPrimary = Black2,
    secondary = White,
    secondaryVariant = Teal300,
    onSecondary = Black2,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Grey1,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Black2,
)

@Composable
fun SimpleJetpackComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    MaterialTheme(colors = if (darkTheme) DarkColorPalette else LightColorPalette) {
        content()
    }
}