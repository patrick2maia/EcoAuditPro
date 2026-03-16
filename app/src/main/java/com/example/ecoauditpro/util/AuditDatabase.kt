package com.example.ecoauditpro.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecoauditpro.data.AuditReport

@Database(entities = [AuditReport::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun auditDao(): AuditDao
}

object AuditDatabase {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "eco_audit_db"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }
}