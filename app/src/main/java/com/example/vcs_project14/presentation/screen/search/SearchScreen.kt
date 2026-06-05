package com.example.vcs_project14.presentation.screen.search

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
@Composable
fun SearchScreen(
    navController: NavHostController
) {
    var query by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Quay lại")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Tìm kiếm")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = {}) {
                Text("Income")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {}) {
                Text("Expense")
            }
        }
    }
}