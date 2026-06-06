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
        "Trang chủ",
        Icons.Default.Home
    )
    object Search : BottomNavItem(
        "search",
        "Tìm kiếm",
        Icons.Default.Search
    )
    object Statistics : BottomNavItem(
        "statistics",
        "Thống kê",
        Icons.Default.BarChart
    )
    object Category : BottomNavItem(
        "category",
        "Danh mục",
        Icons.Default.Info
    )
}