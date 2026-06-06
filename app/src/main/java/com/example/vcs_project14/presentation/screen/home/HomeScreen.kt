package com.example.vcs_project14.presentation.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.vcs_project14.data.local.AppDatabase
import com.example.vcs_project14.data.local.entity.TransactionEntity
import com.example.vcs_project14.data.repository.TransactionRepositoryImpl
import com.example.vcs_project14.presentation.component.*
import com.example.vcs_project14.presentation.theme.*
import kotlinx.coroutines.delay
import java.text.DecimalFormat
@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val database = remember {
        AppDatabase.getDatabase(context)
    }
    val repository = remember {

        TransactionRepositoryImpl(
            database.transactionDao()
        )
    }
    val viewModel: HomeViewModel =
        viewModel(
            factory =
                HomeViewModelFactory(
                    repository
                )
        )
    val transactions by viewModel.transactions.collectAsState()
    var deletedTransaction: TransactionEntity? by remember {
        mutableStateOf(null)
    }
    var editingTransaction: TransactionEntity? by remember {
        mutableStateOf(null)
    }
    var showDeleteMessage by remember {
        mutableStateOf(false)
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        "add"
                    )
                },
                containerColor = Primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Finance Manager",
                    style =
                        MaterialTheme
                            .typography
                            .headlineMedium,
                    color = TextDark,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                BalanceCard(
                    balance = viewModel.balance()
                )
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                val formatter = DecimalFormat("#,###")
                Row {
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = IncomeGreen
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {
                            Text(
                                text = "Thu",
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )
                            Text(
                                "${formatter.format(viewModel.totalIncome())} đ"
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = ExpenseRed
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {
                            Text(
                                text = "Chi",
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )
                            Text(
                                "${formatter.format(viewModel.totalExpense())} đ"
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                Row {
                    FinanceButton(
                        text = "Danh mục",
                        modifier = Modifier.weight(1f)
                    ) {
                        navController.navigate(
                            "category"
                        )
                    }
                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                    FinanceButton(
                        text = "Tìm kiếm",
                        modifier = Modifier.weight(1f)
                    ) {
                        navController.navigate(
                            "search"
                        )
                    }
                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                    FinanceButton(
                        text = "Thống kê",
                        modifier = Modifier.weight(1f)
                    ) {
                        navController.navigate(
                            "statistics"
                        )
                    }
                }
                Spacer(
                    modifier = Modifier.height(12.dp)
                )
                if (transactions.isEmpty()) {
                    EmptyState()
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        contentPadding = PaddingValues(
                            bottom = 120.dp
                        )
                    ) {
                        items(
                            transactions,
                            key = { it.id }
                        ) { transaction ->
                            AnimatedVisibility(
                                visible = true,
                                enter = fadeIn() + slideInVertically()
                            ) {
                                SwipeToDeleteContainer(
                                    onDelete = {
                                        deletedTransaction = transaction
                                        viewModel.deleteTransaction(
                                            transaction
                                        )
                                        showDeleteMessage = true
                                    }
                                ) {
                                    Box {
                                        TransactionItem(transaction)
                                        Box(
                                            modifier = Modifier
                                                .matchParentSize()
                                                .padding(4.dp)
                                        ) {
                                            TextButton(
                                                onClick = {
                                                    editingTransaction = transaction
                                                },
                                                modifier = Modifier.matchParentSize(),
                                                colors = ButtonDefaults.textButtonColors(
                                                    containerColor = Color.Transparent
                                                )
                                            ) {
                                                Text("")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                AnimatedVisibility(
                    visible = showDeleteMessage,
                    enter = fadeIn() + slideInVertically { it / 2 },
                    exit = fadeOut() + slideOutVertically { it / 2 },
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(18.dp),
                        color = TextDark,
                        shadowElevation = 10.dp
                    ) {
                        Text(
                            text = "Đã xoá giao dịch",
                            color = Color.White,
                            modifier = Modifier.padding(
                                horizontal = 22.dp,
                                vertical = 14.dp
                            )
                        )
                    }
                }
            }
            LaunchedEffect(
                showDeleteMessage
            ) {
                if (showDeleteMessage) {
                    delay(2000)
                    showDeleteMessage = false
                }
            }
            editingTransaction?.let {
                EditTransactionDialog(
                    transaction = it,
                    onDismiss = {
                        editingTransaction = null
                    },
                    onSave = { updated ->
                        viewModel.updateTransaction(updated)
                        editingTransaction = null
                    }
                )
            }
        }
    }
}