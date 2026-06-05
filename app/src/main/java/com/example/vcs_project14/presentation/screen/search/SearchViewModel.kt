package com.example.vcs_project14.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vcs_project14.data.local.entity.TransactionEntity
import com.example.vcs_project14.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository:
    TransactionRepository
) : ViewModel() {
    private val _allTransactions =
        MutableStateFlow<List<TransactionEntity>>(
            emptyList()
        )
    private val _transactions =
        MutableStateFlow<List<TransactionEntity>>(
            emptyList()
        )
    val transactions = _transactions.asStateFlow()
    var currentQuery = ""
    var currentType = "All"
    var startDate: Long? = null
    var endDate: Long? = null
    init {
        loadAll()
    }
    fun loadAll() {
        viewModelScope.launch {
            repository
                .getAll()
                .collect {
                    _allTransactions.value = it
                    applyFilters()
                }
        }
    }
    fun updateQuery(
        query: String
    ) {
        currentQuery = query
        applyFilters()
    }
    fun updateType(
        type: String
    ) {
        currentType = type
        applyFilters()
    }
    fun updateDateRange(
        start: Long?,
        end: Long?
    ) {
        startDate = start
        endDate = end
        applyFilters()
    }
    private fun applyFilters() {
        var filtered = _allTransactions.value
        if (currentQuery.isNotBlank()) {
            filtered = filtered.filter {
                it.title.contains(currentQuery, ignoreCase = true) ||
                        it.category.contains(currentQuery, ignoreCase = true)
            }
        }
        if (currentType != "All") {
            filtered = filtered.filter {
                it.type == currentType
            }
        }
        if (startDate != null && endDate != null) {
            filtered = filtered.filter {
                it.date in startDate!!..endDate!!
            }
        }
        _transactions.value = filtered
    }
}