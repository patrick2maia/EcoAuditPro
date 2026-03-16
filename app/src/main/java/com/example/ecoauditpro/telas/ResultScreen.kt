package com.example.ecoauditpro.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ecoauditpro.data.AuditReport
import com.example.ecoauditpro.util.AuditDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ResultScreen(
    score: Double,
    empresa: String,
    setor: String,
    onBackToHome: () -> Unit
) {
    // 1. Definições de ambiente (DENTRO da função, não nos parâmetros)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var isSynced by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Resultado da Auditoria", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Pontuação: $score%", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(48.dp))

        // Botão de Sincronização / Salvar
        Button(
            onClick = {
                val newReport = AuditReport(
                    codigoRelatorio = "RV 001235",
                    empresa = empresa,
                    setor = setor,
                    data = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date()),
                    score = score,
                    detalhes = "Auditoria realizada com sucesso"
                )

                scope.launch {
                    try {
                        val db = AuditDatabase.getDatabase(context)
                        db.auditDao().saveReport(newReport)
                        isSynced = true
                    } catch (e: Exception) {
                        android.util.Log.e("JACK_LOG", "Erro ao salvar: ${e.message}")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isSynced,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSynced) Color.Gray else MaterialTheme.colorScheme.primary
            )
        ) {
            Text(if (isSynced) "Sincronizado com Sucesso" else "Salvar no Banco (RV 001235)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onBackToHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar ao Início")
        }
    }
}