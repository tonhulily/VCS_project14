package com.example.vcs_project14.data.local.dao

import androidx.room.*
import com.example.vcs_project14.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: CategoryEntity)
    @Update
    suspend fun update(category: CategoryEntity)
    @Delete
    suspend fun delete(category: CategoryEntity)
    @Query("SELECT * FROM categories ORDER BY id DESC")
    fun getAll(): Flow<List<CategoryEntity>>
}