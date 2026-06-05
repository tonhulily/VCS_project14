package com.example.vcs_project14.presentation.screen.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vcs_project14.domain.repository.TransactionRepository

class StatisticsViewModelFactory(
    private val repository:
    TransactionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel>
            create(
        modelClass: Class<T>
    ): T {
        return StatisticsViewModel(
            repository
        ) as T
    }
}