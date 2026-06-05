package com.example.vcs_project14.data.local.dao

import androidx.room.*
import com.example.vcs_project14.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: TransactionEntity)
    @Delete
    suspend fun delete(transaction: TransactionEntity)
    @Update
    suspend fun update(transaction: TransactionEntity)
    @Query("""
        SELECT * FROM transactions
        ORDER BY date DESC
    """)
    fun getAll(): Flow<List<TransactionEntity>>
    @Query("""
        SELECT * FROM transactions
        WHERE title LIKE '%' || :query || '%'
    """)
    fun search(query: String): Flow<List<TransactionEntity>>
    @Query("""
        SELECT * FROM transactions
        WHERE date BETWEEN :startDate AND :endDate
    """)
    fun filterByDate(
        startDate: Long,
        endDate: Long
    ): Flow<List<TransactionEntity>>
}