package com.example.vcs_project14.presentation.screen.category

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
@Composable
fun CategoryScreen(
    navController: NavHostController
) {
    var categoryName by remember {
        mutableStateOf("")
    }
    val categories = listOf(
        "Ăn uống",
        "Mua sắm",
        "Di chuyển"
    )
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
            value = categoryName,
            onValueChange = {
                categoryName = it
            },
            label = {
                Text("Tên category")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {}
        ) {
            Text("Thêm category")
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(categories) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(it)
                        TextButton(
                            onClick = {}
                        ) {
                            Text("Xóa")
                        }
                    }
                }
            }
        }
    }
}