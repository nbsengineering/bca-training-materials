package com.nbs.composemigration.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

internal val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    background = DarkPurple, // Maps to `android:colorBackground`
    surface = DarkPurple, // Often set to the same as background in simple dark schemes
    primaryContainer = MediumPurple,
    onPrimaryContainer = Color.White,
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = AppTypography,
        content = content,
    )
}