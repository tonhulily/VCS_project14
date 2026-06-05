package com.example.vcs_project14.presentation.screen.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.vcs_project14.data.local.AppDatabase
import com.example.vcs_project14.data.repository.CategoryRepositoryImpl
import com.example.vcs_project14.data.repository.TransactionRepositoryImpl
import com.example.vcs_project14.presentation.component.FinanceButton
import com.example.vcs_project14.presentation.component.FinanceTextField
import com.example.vcs_project14.presentation.theme.*
import com.example.vcs_project14.presentation.utils.DateUtils
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val database = remember {
        AppDatabase.getDatabase(context)
    }
    val transactionRepository = remember {
        TransactionRepositoryImpl(
            database.transactionDao()
        )
    }
    val categoryRepository = remember {
        CategoryRepositoryImpl(
            database.categoryDao()
        )
    }
    val viewModel: AddTransactionViewModel =
        viewModel(
            factory =
                AddTransactionViewModelFactory(
                    transactionRepository,
                    categoryRepository
                )
        )
    val categories by viewModel.categories.collectAsState()
    var title by remember {
        mutableStateOf("")
    }
    var amount by remember {
        mutableStateOf("")
    }
    var note by remember {
        mutableStateOf("")
    }
    var date by remember {
        mutableStateOf("")
    }
    var selectedType by remember {
        mutableStateOf("Expense")
    }
    var selectedCategory by remember {
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
                        text = "Thêm giao dịch"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController
                                .popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
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
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            FinanceTextField(
                value = title,
                onValueChange = {
                    title = it
                },
                label = "Tên giao dịch",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            FinanceTextField(
                value = amount,
                onValueChange = {
                    amount = it.filter { char ->
                        char.isDigit() || char == '.'
                    }
                },
                label = "Số tiền",
                keyboardType = KeyboardType.Number,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            FinanceTextField(
                value = date,
                onValueChange = {
                    date = it
                },
                label = "Ngày (dd/MM/yyyy)",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            FinanceTextField(
                value = note,
                onValueChange = {
                    note = it
                },
                label = "Ghi chú",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(24.dp)
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
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FilterChip(
                    selected =
                        selectedType == "Expense",
                    onClick = {
                        selectedType = "Expense"
                    },
                    label = {
                        Text("Chi")
                    }
                )
                FilterChip(
                    selected =
                        selectedType == "Income",
                    onClick = {
                        selectedType = "Income"
                    },
                    label = {
                        Text("Thu")
                    }
                )
            }
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Text(
                text = "Danh mục",
                style =
                    MaterialTheme
                        .typography
                        .titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            if (categories.isEmpty()) {
                Text(
                    text = "Chưa có category nào",
                    color = ExpenseRed
                )
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    categories.forEach {
                        category ->
                        FilterChip(
                            selected =
                                selectedCategory == category.name,
                            onClick = {
                                selectedCategory = category.name
                            },
                            label = {
                                Text(
                                    text = category.name
                                )
                            }
                        )
                    }
                }
            }
            if (errorMessage.isNotEmpty()) {
                Spacer(
                    modifier = Modifier.height(18.dp)
                )
                Text(
                    text = errorMessage,
                    color = ExpenseRed
                )
            }
            Spacer(
                modifier = Modifier.height(32.dp)
            )
            FinanceButton(
                text = "Lưu giao dịch",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    errorMessage = ""
                    val amountValue = amount.toDoubleOrNull()
                    val parsedDate =
                        DateUtils.parseDate(
                            date
                        )
                    when {
                        title.trim().isBlank() -> {
                            errorMessage = "Vui lòng nhập tên giao dịch"
                        }
                        amountValue == null || amountValue <= 0 -> {
                            errorMessage = "Số tiền không hợp lệ"
                        }
                        selectedCategory.isBlank() -> {
                            errorMessage = "Vui lòng chọn category"
                        }
                        parsedDate == null -> {
                            errorMessage = "Ngày phải đúng định dạng dd/MM/yyyy"
                        }
                        else -> {
                            viewModel
                                .addTransaction(
                                    title = title.trim(),
                                    amount = amountValue,
                                    type = selectedType,
                                    category = selectedCategory,
                                    note = note.trim(),
                                    date = date
                                )
                            navController.popBackStack()
                        }
                    }
                }
            )
            Spacer(
                modifier = Modifier.height(120.dp)
            )
        }
    }
}