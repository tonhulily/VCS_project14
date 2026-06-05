package com.example.vcs_project14.presentation.screen.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import com.example.vcs_project14.data.local.AppDatabase
import com.example.vcs_project14.data.repository.TransactionRepositoryImpl
import com.example.vcs_project14.presentation.component.PieChartView
import com.example.vcs_project14.presentation.theme.*
import java.text.DecimalFormat
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val repository =
        remember {
            TransactionRepositoryImpl(
                database.transactionDao()
            )
        }
    val viewModel: StatisticsViewModel =
        viewModel(
            factory =
                StatisticsViewModelFactory(
                    repository
                )
        )
    val chartData by viewModel.chartData.collectAsState()
    val totalExpense by viewModel.totalExpense.collectAsState()
    val formatter = DecimalFormat("#,###")
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Thống kê")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding =
                PaddingValues(
                    bottom = 120.dp
                )
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    colors =
                        CardDefaults.cardColors(
                            containerColor =
                                CardColor
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Biểu đồ chi tiêu",
                            style =
                                MaterialTheme
                                    .typography
                                    .titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(
                            modifier = Modifier.height(24.dp)
                        )
                        PieChartView(
                            chartData
                        )
                    }
                }
            }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(30.dp),
                    colors =
                        CardDefaults.cardColors(
                            containerColor =
                                CardColor
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Tổng chi tiêu",
                            color = GrayText
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Text(
                            text = "${formatter.format(totalExpense)} đ",
                            style =
                                MaterialTheme
                                    .typography
                                    .headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = ExpenseRed
                        )
                    }
                }
            }
        }
    }
}