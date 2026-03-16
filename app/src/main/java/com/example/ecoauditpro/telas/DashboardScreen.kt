package com.example.ecoauditpro.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen(
    onStartClick: () -> Unit,
    onNewAuditClick: () -> Unit,
    onViewHistoryClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "EcoAudit Pro",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold ,
                color = Color(0xFF1B5E20)
            )

            Text(
                text = "Governance e Compliance",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Text(
                text = "RM562748 - Patrick Maia",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Text(
                text = "FIAP - 2026",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(64.dp))

            // Botão Principal
            Button(
                onClick = onNewAuditClick,
                modifier = Modifier.fillMaxWidth().height(60.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("▶ Nova Auditoria", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão de Histórico (RV 001235)
            OutlinedButton(
                onClick = onViewHistoryClick,
                modifier = Modifier.fillMaxWidth().height(60.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("📋 Ver Histórico", fontSize = 16.sp)
            }
        }
    }
}