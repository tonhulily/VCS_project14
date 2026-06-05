package com.example.vcs_project14.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.vcs_project14.presentation.screen.add.AddTransactionScreen
import com.example.vcs_project14.presentation.screen.category.CategoryScreen
import com.example.vcs_project14.presentation.screen.home.HomeScreen
import com.example.vcs_project14.presentation.screen.search.SearchScreen
import com.example.vcs_project14.presentation.screen.statistics.StatisticsScreen

@Composable
fun NavGraph() {

    val navController =
        rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("add") {
            AddTransactionScreen(navController)
        }
        composable("category") {
            CategoryScreen(navController)
        }
        composable("search") {
            SearchScreen(navController)
        }
        composable("statistics") {
            StatisticsScreen(navController)
        }
    }
}