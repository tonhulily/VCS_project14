package com.example.vcs_project14.presentation.screen.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vcs_project14.domain.repository.CategoryRepository

class CategoryViewModelFactory(
    private val repository:
    CategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel>
            create(
        modelClass: Class<T>
    ): T {
        return CategoryViewModel(
            repository
        ) as T
    }
}