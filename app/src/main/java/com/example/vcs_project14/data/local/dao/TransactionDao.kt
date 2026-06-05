package com.example.vcs_project14.data.local.dao

import androidx.room.*
import com.example.vcs_project14.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        transaction: TransactionEntity
    )
    @Update
    suspend fun update(
        transaction: TransactionEntity
    )
    @Delete
    suspend fun delete(
        transaction: TransactionEntity
    )
    @Query(
        "SELECT * FROM transactions ORDER BY date DESC"
    )
    fun getAll(): Flow<List<TransactionEntity>>
    @Query(
        """
        SELECT * FROM transactions
        WHERE title LIKE '%' || :query || '%'
        ORDER BY date DESC
        """
    )
    fun search(
        query: String
    ): Flow<List<TransactionEntity>>
    @Query(
        """
        SELECT * FROM transactions
        WHERE type = :type
        ORDER BY date DESC
        """
    )
    fun filterByType(
        type: String
    ): Flow<List<TransactionEntity>>
    @Query(
        """
        SELECT * FROM transactions
        WHERE date BETWEEN :startDate
        AND :endDate
        ORDER BY date DESC
        """
    )
    fun filterByDate(
        startDate: Long,
        endDate: Long
    ): Flow<List<TransactionEntity>>
}