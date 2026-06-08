package com.example.vcs_project14.data.repository

import com.example.vcs_project14.data.local.dao.TransactionDao
import com.example.vcs_project14.data.local.entity.TransactionEntity
import com.example.vcs_project14.domain.repository.TransactionRepository
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
    override fun getAll() = dao.getAll()
    override fun search(
        query: String
    ) = dao.search(query)
    override fun filterByType(
        type: String
    ) = dao.filterByType(type)
    override fun filterByDate(
        startDate: Long,
        endDate: Long
    ) = dao.filterByDate(
        startDate,
        endDate
    )
    override suspend fun markCategoryDeleted(categoryName: String) {
        dao.markCategoryDeleted(categoryName)
    }
}