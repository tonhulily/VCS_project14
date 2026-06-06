package com.example.vcs_project14.presentation.screen.category

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import com.example.vcs_project14.data.local.AppDatabase
import com.example.vcs_project14.data.local.entity.CategoryEntity
import com.example.vcs_project14.data.repository.CategoryRepositoryImpl
import com.example.vcs_project14.presentation.component.FinanceTextField
import com.example.vcs_project14.presentation.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val repository =
        remember {
            CategoryRepositoryImpl(
                database.categoryDao()
            )
        }
    val viewModel: CategoryViewModel =
        viewModel(
            factory =
                CategoryViewModelFactory(
                    repository
                )
        )
    val categories by viewModel.categories.collectAsState()
    var categoryName by remember {
        mutableStateOf("")
    }
    var editingCategory by remember {
        mutableStateOf<CategoryEntity?>(null)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Quản lý Category",
                        fontWeight =
                            FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
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
                .background(
                    Color(0xFFF6F7FB)
                )
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(28.dp),
                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Color.White
                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(28.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    FinanceTextField(
                        value = categoryName,
                        onValueChange = {
                            categoryName = it
                        },
                        label = "Nhập category",
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    Button(
                        onClick = {
                            if (categoryName.isNotBlank()) {
                                viewModel
                                    .addCategory(
                                        categoryName
                                    )
                                categoryName = ""
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Primary
                            )
                    ) {
                        Text(
                            "Thêm category",
                            style =
                                MaterialTheme
                                    .typography
                                    .titleMedium
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding =
                    PaddingValues(
                        bottom = 120.dp
                    )
            ) {
                items(
                    categories,
                    key = { it.id }
                ) { category ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        shape = RoundedCornerShape(28.dp),
                        colors =
                            CardDefaults.cardColors(
                                containerColor =
                                    Color.White
                            ),
                        border = BorderStroke(
                            1.dp,
                            BorderColor
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(52.dp)
                                        .background(
                                            brush =
                                                Brush.linearGradient(
                                                    listOf(
                                                        Color(
                                                            0xFF6C63FF
                                                        ),
                                                        Color(
                                                            0xFF8B5CF6
                                                        )
                                                    )
                                                ),
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Category,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                                Spacer(
                                    modifier = Modifier.width(16.dp)
                                )
                                Column {
                                    Text(
                                        text = category.name,
                                        style =
                                            MaterialTheme
                                                .typography
                                                .titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = TextDark
                                    )
                                    Spacer(
                                        modifier = Modifier.height(4.dp)
                                    )
                                    Text(
                                        text = "Danh mục chi tiêu",
                                        color = GrayText,
                                        style =
                                            MaterialTheme
                                                .typography
                                                .bodySmall
                                    )
                                }
                            }
                            Row {
                                IconButton(
                                    onClick = {
                                        editingCategory = category
                                    }
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(42.dp)
                                            .background(
                                                Color(0xFFEDE9FE),
                                                CircleShape
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = null,
                                            tint = Color(0xFF7C3AED)
                                        )
                                    }
                                }
                                Spacer(
                                    modifier = Modifier.width(8.dp)
                                )
                                IconButton(
                                    onClick = {
                                        viewModel
                                            .deleteCategory(
                                                category
                                            )
                                    }
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(42.dp)
                                            .background(
                                                Color(0xFFFFE4E6),
                                                CircleShape
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = null,
                                            tint = Color(0xFFE11D48)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    editingCategory?.let { category ->
        var updatedName by remember {
            mutableStateOf(category.name)
        }
        AlertDialog(
            onDismissRequest = {
                editingCategory = null
            },
            title = {
                Text("Sửa Category")
            },
            text = {
                FinanceTextField(
                    value = updatedName,
                    onValueChange = {
                        updatedName = it
                    },
                    label = "Tên category"
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.updateCategory(
                            category.copy(
                                name = updatedName
                            )
                        )
                        editingCategory = null
                    }
                ) {
                    Text("Lưu")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        editingCategory = null
                    }
                ) {
                    Text("Huỷ")
                }
            }
        )
    }
}