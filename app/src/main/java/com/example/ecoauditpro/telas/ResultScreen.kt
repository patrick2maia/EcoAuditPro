package com.example.ecoauditpro.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isSaved by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val report = AuditReport(
            codigoRelatorio = "RV 001235",
            empresa = empresa,
            setor = setor,
            data = dateFormat.format(Date()),
            score = score,
            detalhes = "Auditoria finalizada com sucesso"
        )
        scope.launch {
            try {
                val db = AuditDatabase.getDatabase(context)
                db.auditDao().saveReport(report)
                isSaved = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Resultado: ${"%.2f".format(score)}%", fontSize = 32.sp, fontWeight = FontWeight.Bold)

        if (isSaved) {
            Text(text = "✅ Sincronizado no Samsung", color = Color(0xFF4CAF50), modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = onBackToHome, modifier = Modifier.fillMaxWidth().height(56.dp)) {
            Text("Voltar ao Dashboard")
        }
    }
}