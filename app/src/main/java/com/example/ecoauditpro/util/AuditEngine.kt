package com.example.ecoauditpro.util

import android.util.Log
import com.example.ecoauditpro.data.ComplianceItem

object AuditEngine {
    private const val TAG = "JACK_LOG_AUDIT"

    fun calculateRiskScore(questions: List<ComplianceItem>): Double {

        Log.i(TAG, "Iniciando processamento de auditoria - Operador: Patrick")

        return try {

            val totalWeight = questions.sumOf { it.weight }
            val checkedWeight = questions.filter { it.isChecked }.sumOf { it.weight }

            if (totalWeight == 0) return 0.0

            (checkedWeight.toDouble() / totalWeight.toDouble()) * 100

        } catch (e: Exception) {
            Log.e(TAG, "Erro ao calcular risco: ${e.message}")
            -1.0 // Retorno de erro seguro
        }
    }
}