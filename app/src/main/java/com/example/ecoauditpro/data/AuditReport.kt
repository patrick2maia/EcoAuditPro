package com.example.ecoauditpro.data

public data class AuditReport(
    val id: Long = System.currentTimeMillis(),
    val empresa: String,
    val setor: String,
    val data: String,
    val score: Double,
    val detalhes: String
)