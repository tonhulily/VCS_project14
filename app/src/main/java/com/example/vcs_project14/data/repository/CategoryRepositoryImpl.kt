package com.example.vcs_project14.data.repository

import com.example.vcs_project14.data.local.dao.CategoryDao
import com.example.vcs_project14.data.local.entity.CategoryEntity
import com.example.vcs_project14.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val dao: CategoryDao
) : CategoryRepository {
    override suspend fun insert(
        category: CategoryEntity
    ) {
        dao.insert(category)
    }
    override suspend fun update(
        category: CategoryEntity
    ) {
        dao.update(category)
    }
    override suspend fun delete(
        category: CategoryEntity
    ) {
        dao.delete(category)
    }
    override fun getAll() = dao.getAll()
}