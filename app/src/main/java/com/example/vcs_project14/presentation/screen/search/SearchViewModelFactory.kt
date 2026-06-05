package com.example.vcs_project14.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vcs_project14.domain.repository.TransactionRepository

class SearchViewModelFactory(
    private val repository:
    TransactionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel>
            create(
        modelClass: Class<T>
    ): T {
        return SearchViewModel(
            repository
        ) as T
    }
}