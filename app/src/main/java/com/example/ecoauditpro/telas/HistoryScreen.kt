package com.example.ecoauditpro.telas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var reports by remember { mutableStateOf(listOf<AuditReport>()) }

    fun loadReports() {
        scope.launch {
            val db = AuditDatabase.getDatabase(context)
            reports = db.auditDao().getAllReports()
        }
    }

    LaunchedEffect(Unit) { loadReports() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Histórico de Auditorias") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Text("<", fontWeight = FontWeight.Bold) }
                }
            )
        }
    ) { paddingValues ->
        if (reports.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("Nenhum relatório encontrado.")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(reports) { report ->
                    AuditCard(report = report, onDelete = {
                        scope.launch {
                            val db = AuditDatabase.getDatabase(context)
                            db.auditDao().deleteReport(report)
                            loadReports()
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun AuditCard(report: AuditReport, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = report.codigoRelatorio, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                IconButton(onClick = onDelete) { Text("🗑️", fontSize = 18.sp) }
            }
            Text(text = "Empresa: ${report.empresa}", fontSize = 14.sp)
            Text(text = "Setor: ${report.setor}", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(progress = { (report.score / 100).toFloat() }, modifier = Modifier.fillMaxWidth())
            Text(text = "Conformidade: ${"%.2f".format(report.score)}%", fontWeight = FontWeight.Bold)
        }
    }
}