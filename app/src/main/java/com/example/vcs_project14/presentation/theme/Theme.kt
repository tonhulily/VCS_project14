package com.example.vcs_project14.presentation.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val FinanceColorScheme =
    lightColorScheme(
        primary = Primary,
        secondary = Secondary,
        background = Background,
        surface = CardColor,
        onPrimary = CardColor,
        onBackground = TextDark,
        onSurface = TextDark
    )
@Composable
fun FinanceTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FinanceColorScheme,
        content = content
    )
}