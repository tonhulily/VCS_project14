package com.example.vcs_project14.presentation.screen.add

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AddTransactionScreen(
    navController: NavHostController
) {
    var title by remember {
        mutableStateOf("")
    }
    var amount by remember {
        mutableStateOf("")
    }
    var note by remember {
        mutableStateOf("")
    }
    var type by remember {
        mutableStateOf("Expense")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            "Thêm giao dịch",
            style =
                MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            },
            label = {
                Text("Tiêu đề")
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = amount,
            onValueChange = {
                amount = it
            },
            label = {
                Text("Số tiền")
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
            }
        ) {
            Text("Lưu giao dịch")
        }
    }
}