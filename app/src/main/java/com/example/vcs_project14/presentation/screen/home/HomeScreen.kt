package com.example.vcs_project14.presentation.screen.home

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import com.example.vcs_project14.data.local.AppDatabase
import com.example.vcs_project14.data.local.entity.TransactionEntity
import com.example.vcs_project14.data.repository.TransactionRepositoryImpl
import com.example.vcs_project14.presentation.component.*
import com.example.vcs_project14.presentation.theme.*
import java.text.DecimalFormat

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun HomeScreen(
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
    val viewModel: HomeViewModel =
        viewModel(
            factory =
                HomeViewModelFactory(
                    repository
                )
        )
    val transactions by viewModel.transactions.collectAsState()
    val snackbarHostState =
        remember {
            SnackbarHostState()
        }
    var deletedTransaction: TransactionEntity? by remember {
        mutableStateOf(null)
    }
    var editingTransaction: TransactionEntity? by remember {
        mutableStateOf(null)
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController
                        .navigate("add")
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Finance Manager",
                style =
                    MaterialTheme
                        .typography
                        .headlineMedium,
                color = TextDark
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            BalanceCard(
                balance =
                    viewModel.balance()
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            val formatter = DecimalFormat("#,###")
            Row {
                Card(
                    modifier = Modifier.weight(1f),
                    colors =
                        CardDefaults.cardColors(
                            containerColor = IncomeGreen
                        )
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {
                        Text("Thu")
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
                    colors =
                        CardDefaults.cardColors(
                            containerColor =
                                ExpenseRed
                        )
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {
                        Text("Chi")
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
                    text = "Category",
                    modifier = Modifier.weight(1f)
                ) {
                    navController
                        .navigate(
                            "category"
                        )
                }
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                FinanceButton(
                    text = "Search",
                    modifier = Modifier.weight(1f)
                ) {
                    navController
                        .navigate(
                            "search"
                        )
                }
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                FinanceButton(
                    text = "Stats",
                    modifier = Modifier.weight(1f)
                ) {
                    navController
                        .navigate(
                            "statistics"
                        )
                }
            }
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            if (
                transactions.isEmpty()
            ) {
                EmptyState()
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    contentPadding =
                        PaddingValues(
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
                                }
                            ) {
                                Box {
                                    TransactionItem(
                                        transaction
                                    )
                                    Box(
                                        modifier = Modifier
                                            .matchParentSize()
                                            .padding(4.dp)

                                    ) {
                                        TextButton(
                                            onClick = {
                                                editingTransaction =
                                                    transaction
                                            },
                                            modifier = Modifier.matchParentSize(),
                                            colors =
                                                ButtonDefaults.textButtonColors(
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
        LaunchedEffect(
            deletedTransaction
        ) {
            deletedTransaction?.let {
                val result =
                    snackbarHostState
                        .showSnackbar(
                            message = "Đã xoá giao dịch",
                            actionLabel = "UNDO"
                        )
                if (result == SnackbarResult.ActionPerformed) {
                    viewModel.updateTransaction(it)
                }
                deletedTransaction = null
            }
        }
        editingTransaction?.let {
            EditTransactionDialog(
                transaction = it,
                onDismiss = {
                    editingTransaction = null
                },
                onSave = { updated ->
                    viewModel
                        .updateTransaction(
                            updated
                        )
                    editingTransaction = null
                }
            )
        }
    }
}