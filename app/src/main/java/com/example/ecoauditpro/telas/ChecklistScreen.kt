package com.example.ecoauditpro.telas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoauditpro.data.ComplianceItem

@Composable
fun ChecklistScreen(onFinishAudit: (Double) -> Unit) { // Jack: Agora passa o Double (nota)
    val questions = remember {
        mutableStateListOf(
            ComplianceItem(1, "Gestão de Resíduos", "A empresa possui coleta seletiva?", isChecked = false),
            ComplianceItem(2, "Diversidade", "Há políticas de inclusão no RH?", isChecked = false),
            ComplianceItem(3, "Transparência", "Relatórios financeiros são públicos?", isChecked = false)
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Checklist de Conformidade", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(questions) { item ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item.isChecked,
                        onCheckedChange = { checked ->
                            val index = questions.indexOf(item)
                            questions[index] = item.copy(isChecked = checked)
                        }
                    )
                    Column {
                        Text(item.title, fontSize = 18.sp)
                        Text(item.description, fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }
        }

        Button(
            onClick = {
                // Jack: Cálculo em tempo real para evitar o 0%
                val total = questions.size
                val marcados = questions.count { it.isChecked }
                val resultado = (marcados.toDouble() / total.toDouble()) * 100.0
                onFinishAudit(resultado)
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Finalizar e Calcular Risco")
        }
    }
}