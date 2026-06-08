package com.example.vcs_project14.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vcs_project14.data.local.entity.TransactionEntity
import com.example.vcs_project14.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository:
    TransactionRepository
) : ViewModel() {
    private val _transactions =
        MutableStateFlow<List<TransactionEntity>>(
            emptyList()
        )
    val transactions = _transactions.asStateFlow()
    init {
        loadTransactions()
    }
    private fun loadTransactions() {
        viewModelScope.launch {
            repository.getAll()
                .collect {
                    _transactions.value = it
                }
        }
    }
    fun deleteTransaction(
        transaction: TransactionEntity
    ) {
        viewModelScope.launch {
            repository.delete(transaction)
        }
    }
    fun totalIncome(): Double {
        return transactions.value
            .filter {
                it.type == "Income" && !it.isCategoryDeleted
            }
            .sumOf { it.amount }
    }
    fun totalExpense(): Double {
        return transactions.value
            .filter {
                it.type == "Expense" && !it.isCategoryDeleted
            }
            .sumOf { it.amount }
    }
    fun balance(): Double {
        return totalIncome() - totalExpense()
    }
    fun updateTransaction(
        transaction: TransactionEntity
    ) {
        viewModelScope.launch {
            repository.update(transaction)
        }
    }
}