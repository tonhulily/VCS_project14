package com.example.vcs_project14.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        "home",
        "Home",
        Icons.Default.Home
    )
    object Search : BottomNavItem(
        "search",
        "Search",
        Icons.Default.Search
    )
    object Statistics : BottomNavItem(
        "statistics",
        "Stats",
        Icons.Default.Build
    )
    object Category : BottomNavItem(
        "category",
        "Category",
        Icons.Default.Info
    )
}