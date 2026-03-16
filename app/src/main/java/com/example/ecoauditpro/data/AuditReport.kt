package com.example.ecoauditpro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audit_reports")
data class AuditReport(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val codigoRelatorio: String, // Aqui vai o RV 001235
    val empresa: String,
    val setor: String,
    val data: String,
    val score: Double,
    val detalhes: String
)