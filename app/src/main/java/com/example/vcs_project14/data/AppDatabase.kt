package com.example.vcs_project14.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vcs_project14.data.local.dao.CategoryDao
import com.example.vcs_project14.data.local.dao.TransactionDao
import com.example.vcs_project14.data.local.entity.CategoryEntity
import com.example.vcs_project14.data.local.entity.TransactionEntity
@Database(
    entities = [
        CategoryEntity::class,
        TransactionEntity::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "finance_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}