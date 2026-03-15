package com.example.ecoauditpro.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SetupScreen(onStartClick: (String, String) -> Unit, onNext: () -> Unit) {
    var empresa by remember { mutableStateOf("") }
    var setor by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Identificação da Auditoria", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = empresa,
            onValueChange = { empresa = it },
            label = { Text("Nome da Empresa") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = setor,
            onValueChange = { setor = it },
            label = { Text("Setor/Departamento") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onStartClick(empresa, setor) },
            modifier = Modifier.fillMaxWidth(),
            enabled = empresa.isNotBlank() && setor.isNotBlank()
        ) {
            Text("Ir para Checklist")
        }
    }
}