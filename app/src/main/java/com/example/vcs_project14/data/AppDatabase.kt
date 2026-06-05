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
        TransactionEntity::class,
        CategoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase :
    RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    companion object {
        @Volatile
        private var INSTANCE:
                AppDatabase? = null
        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "finance_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}