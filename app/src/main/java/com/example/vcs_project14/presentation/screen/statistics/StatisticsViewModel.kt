package com.example.vcs_project14.presentation.screen.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vcs_project14.domain.repository.CategoryRepository
import com.example.vcs_project14.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StatisticsViewModel(
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _chartData = MutableStateFlow<Map<String, Double>>(emptyMap())
    val chartData = _chartData.asStateFlow()
    private val _totalExpense = MutableStateFlow(0.0)
    val totalExpense = _totalExpense.asStateFlow()
    private val _validTransactionCount = MutableStateFlow(0)
    init {
        loadStatistics()
    }
    private fun loadStatistics() {
        viewModelScope.launch {
            combine(
                transactionRepository.getAll(),
                categoryRepository.getAll()
            ) { transactions, categories ->
                val validCategoryNames =
                    categories.map {
                        it.name
                    }
                transactions.filter { transaction ->
                    transaction.category in validCategoryNames
                }
            }.collect { validTransactions ->
                val expenses =
                    validTransactions.filter {
                        it.type == "Expense"
                    }
                _validTransactionCount.value =
                    validTransactions.size
                _totalExpense.value =
                    expenses.sumOf {
                        it.amount
                    }
                _chartData.value =
                    expenses
                        .groupBy {
                            it.category
                        }
                        .mapValues { entry ->
                            entry.value.sumOf {
                                it.amount
                            }
                        }
            }
        }
    }
}