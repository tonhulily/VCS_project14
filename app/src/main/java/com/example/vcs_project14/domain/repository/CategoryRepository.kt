package com.example.vcs_project14.domain.repository

import com.example.vcs_project14.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun insert(category: CategoryEntity)
    suspend fun update(category: CategoryEntity)
    suspend fun delete(category: CategoryEntity)
    fun getAll(): Flow<List<CategoryEntity>>
}