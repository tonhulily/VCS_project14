package com.example.vcs_project14.data.repository

import com.example.vcs_project14.data.local.dao.TransactionDao
import com.example.vcs_project14.data.local.entity.TransactionEntity
import com.example.vcs_project14.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
class TransactionRepositoryImpl(
    private val dao: TransactionDao
) : TransactionRepository {
    override suspend fun insert(
        transaction: TransactionEntity
    ) {
        dao.insert(transaction)
    }
    override suspend fun update(
        transaction: TransactionEntity
    ) {
        dao.update(transaction)
    }
    override suspend fun delete(
        transaction: TransactionEntity
    ) {
        dao.delete(transaction)
    }
    override fun getAll(): Flow<List<TransactionEntity>> {
        return dao.getAll()
    }
    override fun search(
        query: String
    ): Flow<List<TransactionEntity>> {

        return dao.search(query)
    }
    override fun filterByDate(
        startDate: Long,
        endDate: Long
    ): Flow<List<TransactionEntity>> {
        return dao.filterByDate(
            startDate,
            endDate
        )
    }
}