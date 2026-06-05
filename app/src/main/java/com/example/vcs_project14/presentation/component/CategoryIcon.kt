package com.example.vcs_project14.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun getCategoryIcon(
    category: String
): ImageVector {
    return when (category) {
        "Food" -> Icons.Default.Favorite
        "Shopping" -> Icons.Default.ShoppingCart
        "Salary" -> Icons.Default.Star
        "Travel" -> Icons.Default.Home
        else -> Icons.Default.Info
    }
}