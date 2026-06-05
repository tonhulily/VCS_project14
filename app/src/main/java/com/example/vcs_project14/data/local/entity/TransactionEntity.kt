package com.example.vcs_project14.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions"
)
data class TransactionEntity(
    @PrimaryKey(
        autoGenerate = true
    )
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val type: String,
    val category: String,
    val note: String,
    val date: Long
)