package com.example.vcs_project14.presentation.screen.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vcs_project14.data.local.entity.CategoryEntity
import com.example.vcs_project14.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoryRepository
) : ViewModel() {
    private val _categories =
        MutableStateFlow<List<CategoryEntity>>(
            emptyList()
        )
    val categories = _categories.asStateFlow()
    init {
        loadCategories()
    }
    private fun loadCategories() {
        viewModelScope.launch {
            repository.getAll()
                .collect {
                    _categories.value = it
                }
        }
    }
    fun addCategory(
        name: String
    ) {
        if (name.isBlank()) return
        viewModelScope.launch {
            repository.insert(
                CategoryEntity(
                    name = name
                )
            )
        }
    }
    fun deleteCategory(
        category: CategoryEntity
    ) {
        viewModelScope.launch {
            repository.delete(category)
        }
    }
    fun updateCategory(
        category: CategoryEntity
    ) {
        viewModelScope.launch {
            repository.update(category)
        }
    }
}