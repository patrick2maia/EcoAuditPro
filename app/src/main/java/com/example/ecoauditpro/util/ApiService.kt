package com.example.ecoauditpro.util

import android.util.Log
import kotlinx.coroutines.delay

object ApiService {
    suspend fun sendReport(empresa: String, score: Double): Boolean {
        // NÓ DE LOG (Padrão Jack)
        Log.i("API_LOG", "Iniciando POST para /api/v1/compliance")

        return try {
            delay(2000) // Simula espera do servidor
            Log.i("API_LOG", "Sucesso: Dados da $empresa processados.")
            true
        } catch (e: Exception) {
            Log.e("API_LOG", "Erro: ${e.message}")
            false
        }
    }
}