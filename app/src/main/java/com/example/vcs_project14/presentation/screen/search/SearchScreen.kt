package com.example.vcs_project14.presentation.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
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
import com.example.vcs_project14.data.repository.TransactionRepositoryImpl
import com.example.vcs_project14.presentation.component.*
import com.example.vcs_project14.presentation.theme.*
import com.example.vcs_project14.presentation.utils.DateUtils
@OptIn(
    ExperimentalMaterial3Api::class
)
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
            factory = SearchViewModelFactory(repository)
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

    var showFilterSheet by remember {
        mutableStateOf(false)
    }
    if (showFilterSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showFilterSheet = false
            },
            containerColor = CardColor,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            ),
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .width(60.dp)
                        .height(6.dp)
                        .background(
                            GrayText.copy(alpha = 0.3f),
                            RoundedCornerShape(100.dp)
                        )
                )
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 12.dp,
                        bottom = 20.dp
                    ),
            ) {
                Text(
                    text = "Bộ lọc",
                    style =
                        MaterialTheme
                            .typography
                            .headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier.height(24.dp)
                )
                Text(
                    text = "Loại giao dịch",
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium
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
                FinanceTextField(
                    value = startDate,
                    onValueChange = {
                        startDate = it
                    },
                    label = "Từ ngày (dd/MM/yyyy)"
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                FinanceTextField(
                    value = endDate,
                    onValueChange = {
                        endDate = it
                    },
                    label = "Đến ngày (dd/MM/yyyy)"
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            selectedFilter = "All"
                            startDate = ""
                            endDate = ""
                            errorMessage = ""
                            viewModel.updateType(
                                "All"
                            )
                            viewModel.updateDateRange(
                                null,
                                null
                            )
                        }
                    ) {
                        Text("Xóa lọc")
                    }
                    FinanceButton(
                        text = "Áp dụng",
                        modifier = Modifier.weight(1f),
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
                            if (startDate.isNotBlank() && endDate.isNotBlank()) {
                                if (start == null || end == null) {
                                    errorMessage = "Sai định dạng ngày"
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
                            showFilterSheet = false
                        }
                    )
                }
                Spacer(
                    modifier = Modifier.height(40.dp)
                )
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Tìm kiếm")
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${transactions.size} giao dịch",
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium,
                    fontWeight = FontWeight.Bold
                )
                FilledTonalButton(
                    onClick = {
                        showFilterSheet = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = null
                    )
                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )
                    Text("Bộ lọc")
                }
            }
            Spacer(
                modifier = Modifier.height(18.dp)
            )
            if (transactions.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
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
                        TransactionItem(
                            transaction = it,
                            showDeleteSpacing = false
                        )
                    }
                }
            }
        }
    }
}