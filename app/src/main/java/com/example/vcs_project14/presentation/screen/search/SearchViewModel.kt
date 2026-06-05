package com.example.vcs_project14.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vcs_project14.data.local.entity.TransactionEntity
import com.example.vcs_project14.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: TransactionRepository
) : ViewModel() {
    private val _results = MutableStateFlow<List<TransactionEntity>>(emptyList())
    val results = _results.asStateFlow()
    fun search(query: String) {
        viewModelScope.launch {
            repository.search(query)
                .collect {
                    _results.value = it
                }
        }
    }
}