package com.example.ecoauditpro.util

import com.example.ecoauditpro.data.AuditReport

object AuditDatabase {
    private val reports = mutableListOf<AuditReport>()

    fun saveReport(report: AuditReport) {
        reports.add(report)
        // Log de sucesso para o Jack acompanhar
        android.util.Log.d("JACK_LOG", "Relatório salvo: ${report.empresa}")
    }

    fun getAllReports(): List<AuditReport> {
        return reports.toList()
    }
}