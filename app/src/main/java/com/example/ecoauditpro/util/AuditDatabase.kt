package com.example.ecoauditpro.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecoauditpro.data.AuditReport
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AuditDao {
    @Insert
    suspend fun saveReport(report: AuditReport)

    @Query("SELECT * FROM audit_reports ORDER BY id DESC")
    suspend fun getAllReports(): List<AuditReport>
}

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
            ).build()
            INSTANCE = instance
            instance
        }
    }
}