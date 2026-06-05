package com.example.vcs_project14.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vcs_project14.domain.repository.TransactionRepository

class HomeViewModelFactory(
    private val repository:
    TransactionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel>
            create(
        modelClass: Class<T>
    ): T {
        return HomeViewModel(
            repository
        ) as T
    }
}