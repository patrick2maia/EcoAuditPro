package com.example.ecoauditpro.util



import androidx.room.*
import com.example.ecoauditpro.data.AuditReport

@Dao
interface AuditDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReport(report: AuditReport)

    @Query("SELECT * FROM audit_reports ORDER BY id DESC")
    suspend fun getAllReports(): List<AuditReport>

    @Delete
    suspend fun deleteReport(report: AuditReport)
}