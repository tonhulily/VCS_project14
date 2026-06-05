package com.example.vcs_project14.presentation.screen.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vcs_project14.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StatisticsViewModel(
    private val repository:
    TransactionRepository
) : ViewModel() {
    private val _chartData =
        MutableStateFlow<Map<String, Double>>(
            emptyMap()
        )
    val chartData = _chartData.asStateFlow()
    private val _totalExpense = MutableStateFlow(0.0)
    val totalExpense = _totalExpense.asStateFlow()
    init {
        loadStatistics()
    }
    private fun loadStatistics() {
        viewModelScope.launch {
            repository
                .getAll()
                .collect {
                    transactions ->
                    val expenses = transactions.filter {
                        it.type == "Expense"
                    }
                    _totalExpense.value =
                        expenses.sumOf {
                            it.amount
                        }
                    _chartData.value =
                        expenses.groupBy {
                            it.category
                        }
                            .mapValues {
                                entry ->
                                entry.value.sumOf {
                                    it.amount
                                }
                            }
                }
        }
    }
}