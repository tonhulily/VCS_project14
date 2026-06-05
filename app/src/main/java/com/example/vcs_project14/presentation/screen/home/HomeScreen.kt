package com.example.vcs_project14.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Quản lý thu chi",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate("category")
            }
        ) {
            Text("Quản lý Category")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigate("search")
            }
        ) {
            Text("Tìm kiếm")
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(10) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Ăn uống")
                            Text("Hôm nay")
                        }
                        Text("-120.000đ")
                    }
                }
            }
        }
    }
}