package com.example.ecoauditpro.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoauditpro.data.AuditReport
import com.example.ecoauditpro.util.AuditDatabase
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ResultScreen(
    score: Double,
    empresa: String,
    setor: String,
    onBackToHome: () -> Unit
) {
    var isSynced by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Resultado da Auditoria",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D32)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Card de Pontuação
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Pontuação ESG", fontSize = 18.sp)
                Text(
                    text = "${score.toInt()}%",
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Black,
                    color = if (score >= 70) Color(0xFF2E7D32) else Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Botão de Sincronização
        Button(
            onClick = {
                val currentData = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
                val newReport = AuditReport(
                    empresa = empresa,
                    setor = setor,
                    data = currentData,
                    score = score,
                    detalhes = "Auditoria realizada com sucesso."
                )

                AuditDatabase.saveReport(newReport)
                isSynced = true
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isSynced, // Desabilita após salvar
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSynced) Color.Gray else Color(0xFF2E7D32)
            )
        ) {
            Text(if (isSynced) "Relatório Sincronizado" else "Sincronizar Relatório")
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