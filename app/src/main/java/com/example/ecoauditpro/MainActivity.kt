package com.example.ecoauditpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ecoauditpro.telas.*
import com.example.ecoauditpro.ui.theme.EcoAuditProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoAuditProTheme {
                val navController = rememberNavController()

                // Persistência de estado durante a sessão da auditoria
                var empresaNome by rememberSaveable { mutableStateOf("") }
                var setorNome by rememberSaveable { mutableStateOf("") }

                NavHost(navController = navController, startDestination = "dashboard") {

                    // TELA INICIAL
                    composable("dashboard") {
                        DashboardScreen(
                            onStartClick = { navController.navigate("setup") },
                            onNewAuditClick = { navController.navigate("setup") },
                            onViewHistoryClick = { navController.navigate("history") }
                        )
                    }

                    // TELA DE HISTÓRICO (CADASTRADA NO PADRÃO)
                    composable("history") {
                        HistoryScreen(
                            onBack = { navController.popBackStack() }
                        )
                    }

                    // CONFIGURAÇÃO DA AUDITORIA
                    composable("setup") {
                        SetupScreen(
                            onStartClick = { empresa, setor ->
                                empresaNome = empresa
                                setorNome = setor
                                navController.navigate("checklist")
                            },
                            onNext = { navController.navigate("checklist") }
                        )
                    }

                    // EXECUÇÃO DO CHECKLIST
                    composable("checklist") {
                        ChecklistScreen(
                            onFinishAudit = { scoreCalculado ->
                                navController.navigate("resultado/${scoreCalculado.toString()}")
                            }
                        )
                    }

                    // RESULTADO FINAL E PERSISTÊNCIA NO BANCO
                    composable(
                        route = "resultado/{score}",
                        arguments = listOf(navArgument("score") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val scoreStr = backStackEntry.arguments?.getString("score") ?: "0.0"
                        val scoreDouble = scoreStr.toDoubleOrNull() ?: 0.0

                        ResultScreen(
                            score = scoreDouble,
                            empresa = empresaNome,
                            setor = setorNome,
                            onBackToHome = {
                                navController.navigate("dashboard") {
                                    popUpTo("dashboard") { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}