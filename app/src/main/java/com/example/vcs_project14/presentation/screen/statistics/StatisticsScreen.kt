package com.example.vcs_project14.presentation.screen.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vcs_project14.presentation.component.PieChartView
import com.example.vcs_project14.presentation.theme.Background
import com.example.vcs_project14.presentation.theme.CardColor
import com.example.vcs_project14.presentation.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Thống kê chi tiêu",
                        color = TextWhite
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = TextWhite
                        )
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = CardColor
                    )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(paddingValues)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                Text(
                    text = "Tổng quan tháng này",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextWhite
                )
                Spacer(
                    modifier = Modifier.height(30.dp)
                )
                PieChartView()
                Spacer(
                    modifier = Modifier.height(30.dp)
                )
                Card(
                    colors =
                        CardDefaults.cardColors(
                            containerColor = CardColor
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            "Ăn uống: 40%",
                            color = TextWhite
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Text(
                            "Mua sắm: 25%",
                            color = TextWhite
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Text(
                            "Di chuyển: 20%",
                            color = TextWhite
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Text(
                            "Giải trí: 15%",
                            color = TextWhite
                        )
                    }
                }
            }
        }
    }
}