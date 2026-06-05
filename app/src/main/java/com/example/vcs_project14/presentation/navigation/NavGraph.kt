package com.example.vcs_project14.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.vcs_project14.presentation.component.BottomBar
import com.example.vcs_project14.presentation.screen.add.AddTransactionScreen
import com.example.vcs_project14.presentation.screen.category.CategoryScreen
import com.example.vcs_project14.presentation.screen.home.HomeScreen
import com.example.vcs_project14.presentation.screen.search.SearchScreen
import com.example.vcs_project14.presentation.screen.statistics.StatisticsScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(
                navController
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier =
                Modifier.padding(
                    paddingValues
                )
        ) {
            composable("home") {
                HomeScreen(
                    navController
                )
            }
            composable("search") {
                SearchScreen(
                    navController
                )
            }
            composable("statistics") {
                StatisticsScreen(
                    navController
                )
            }
            composable("category") {
                CategoryScreen(
                    navController
                )
            }
            composable("add") {
                AddTransactionScreen(
                    navController
                )
            }
        }
    }
}