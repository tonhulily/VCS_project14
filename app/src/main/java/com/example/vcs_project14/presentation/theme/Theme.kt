package com.example.vcs_project14.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AppColors = darkColorScheme(
    background = Background,
    primary = Primary,
    secondary = Accent,
    surface = CardColor,
    onBackground = TextWhite,
    onSurface = TextWhite
)
@Composable
fun FinanceTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColors,
        content = content
    )
}