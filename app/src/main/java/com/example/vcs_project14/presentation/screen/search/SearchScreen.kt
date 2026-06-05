package com.example.vcs_project14.presentation.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.vcs_project14.data.local.AppDatabase
import com.example.vcs_project14.data.repository.TransactionRepositoryImpl
import com.example.vcs_project14.presentation.component.*
import com.example.vcs_project14.presentation.theme.*
import com.example.vcs_project14.presentation.utils.DateUtils
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
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
    val viewModel: SearchViewModel =
        viewModel(
            factory =
                SearchViewModelFactory(
                    repository
                )
        )
    val transactions by viewModel.transactions.collectAsState()
    var query by remember {
        mutableStateOf("")
    }
    var selectedFilter by remember {
        mutableStateOf("All")
    }
    var startDate by remember {
        mutableStateOf("")
    }
    var endDate by remember {
        mutableStateOf("")
    }
    var errorMessage by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Tìm kiếm"
                    )
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            FinanceTextField(
                value = query,
                onValueChange = {
                    query = it
                    viewModel.updateQuery(it)
                },
                label = "Tìm giao dịch",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Text(
                text = "Loại giao dịch",
                style =
                    MaterialTheme
                        .typography
                        .titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FilterChip(
                    selected =
                        selectedFilter == "Expense",
                    onClick = {
                        selectedFilter = "Expense"
                        viewModel.updateType(
                            "Expense"
                        )
                    },
                    colors =
                        FilterChipDefaults
                            .filterChipColors(
                                selectedContainerColor = Primary,
                                selectedLabelColor = Color.White
                            ),
                    label = {
                        Text("Chi")
                    }
                )
                FilterChip(
                    selected =
                        selectedFilter == "Income",
                    onClick = {
                        selectedFilter = "Income"
                        viewModel.updateType(
                            "Income"
                        )
                    },
                    colors =
                        FilterChipDefaults
                            .filterChipColors(
                                selectedContainerColor = Primary,
                                selectedLabelColor = Color.White
                            ),
                    label = {
                        Text("Thu")
                    }
                )
                FilterChip(
                    selected =
                        selectedFilter == "All",
                    onClick = {
                        selectedFilter = "All"
                        viewModel.updateType(
                            "All"
                        )
                    },
                    colors =
                        FilterChipDefaults
                            .filterChipColors(
                                selectedContainerColor = Primary,
                                selectedLabelColor = Color.White
                            ),
                    label = {
                        Text("Tất cả")
                    }
                )
            }
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Text(
                text = "Khoảng ngày",
                style =
                    MaterialTheme
                        .typography
                        .titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            FinanceTextField(
                value = startDate,
                onValueChange = {
                    startDate = it
                },
                label = "Từ ngày (dd/MM/yyyy)"
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            FinanceTextField(
                value = endDate,
                onValueChange = {
                    endDate = it
                },
                label = "Đến ngày (dd/MM/yyyy)"
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            FinanceButton(
                text = "Lọc theo ngày",
                onClick = {
                    errorMessage = ""
                    val start =
                        DateUtils.parseDate(
                            startDate
                        )
                    val end =
                        DateUtils.parseDate(
                            endDate
                        )
                    if (start == null || end == null) {
                        errorMessage = "Ngày không đúng định dạng dd/MM/yyyy"
                        return@FinanceButton
                    }
                    if (start > end) {
                        errorMessage = "Ngày bắt đầu phải nhỏ hơn ngày kết thúc"
                        return@FinanceButton
                    }
                    viewModel.updateDateRange(
                        start,
                        end
                    )
                }
            )
            if (errorMessage.isNotEmpty()) {
                Spacer(
                    modifier = Modifier.height(12.dp)
                )
                Text(
                    text = errorMessage,
                    color = ExpenseRed
                )
            }
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            if (transactions.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(
                        text = "Không có giao dịch",
                        style =
                            MaterialTheme
                                .typography
                                .titleMedium,
                        color = GrayText
                    )
                }
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
                    ) {
                        TransactionItem(it)
                    }
                }
            }
        }
    }
}