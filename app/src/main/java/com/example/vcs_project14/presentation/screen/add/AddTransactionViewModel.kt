package com.example.vcs_project14.presentation.screen.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vcs_project14.data.local.entity.CategoryEntity
import com.example.vcs_project14.data.local.entity.TransactionEntity
import com.example.vcs_project14.domain.repository.CategoryRepository
import com.example.vcs_project14.domain.repository.TransactionRepository
import com.example.vcs_project14.presentation.utils.DateUtils
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
class AddTransactionViewModel(
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _categories =
        MutableStateFlow<List<CategoryEntity>>(
            emptyList()
        )
    val categories = _categories.asStateFlow()
    init {
        loadCategories()
    }
    private fun loadCategories() {
        viewModelScope.launch {
            categoryRepository
                .getAll()
                .collect {
                    _categories.value = it
                }
        }
    }
    fun addTransaction(
        title: String,
        amount: Double,
        type: String,
        category: String,
        note: String,
        date: String
    ) {
        viewModelScope.launch {
            val parsedDate =
                DateUtils.parseDate(date) ?: System.currentTimeMillis()
            transactionRepository.insert(
                TransactionEntity(
                    title = title,
                    amount = amount,
                    type = type,
                    category = category,
                    note = note,
                    date = parsedDate
                )
            )
        }
    }
}