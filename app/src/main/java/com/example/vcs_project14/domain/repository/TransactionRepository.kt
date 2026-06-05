package com.example.vcs_project14.domain.repository

import com.example.vcs_project14.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun insert(transaction: TransactionEntity)
    suspend fun update(transaction: TransactionEntity)
    suspend fun delete(transaction: TransactionEntity)
    fun getAll(): Flow<List<TransactionEntity>>
    fun search(query: String): Flow<List<TransactionEntity>>
    fun filterByDate(
        startDate: Long,
        endDate: Long
    ): Flow<List<TransactionEntity>>
}