package com.example.vcs_project14.presentation.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vcs_project14.presentation.navigation.BottomNavItem
import com.example.vcs_project14.presentation.theme.*

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Statistics,
        BottomNavItem.Category
    )
    NavigationBar(
        containerColor = CardColor
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value
            ?.destination
            ?.route
        items.forEach { item ->
            NavigationBarItem(
                selected =
                    currentRoute == item.route,
                onClick = {
                    navController.navigate(
                        item.route
                    ) {
                        popUpTo(
                            navController.graph.startDestinationId
                        )
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(item.title)
                }
            )
        }
    }
}