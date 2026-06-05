package com.example.vcs_project14.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vcs_project14.data.local.entity.TransactionEntity
import com.example.vcs_project14.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TransactionRepository
) : ViewModel() {
    private val _transactions = MutableStateFlow<List<TransactionEntity>>(emptyList())
    val transactions = _transactions.asStateFlow()
    val totalIncome = transactions.map {
        list ->
            list.filter { it.type == "Income" }
                .sumOf { it.amount }
    }
    val totalExpense = transactions.map {
        list ->
            list.filter { it.type == "Expense" }
                .sumOf { it.amount }
    }
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
}