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

                // Jack: rememberSaveable garante que os dados não sumam ao navegar
                var empresaNome by rememberSaveable { mutableStateOf("") }
                var setorNome by rememberSaveable { mutableStateOf("") }

                NavHost(navController = navController, startDestination = "dashboard") {

                    composable("dashboard") {
                        DashboardScreen(
                            onStartClick = { navController.navigate("setup") },
                            onNewAuditClick = { navController.navigate("setup") }
                        )
                    }

                    composable("setup") {
                        SetupScreen(
                            onStartClick = { empresa, setor ->
                                // GRAVAÇÃO DOS DADOS
                                empresaNome = empresa
                                setorNome = setor
                                navController.navigate("checklist")
                            },
                            onNext = { navController.navigate("checklist") }
                        )
                    }

                    composable("checklist") {
                        ChecklistScreen(
                            onFinishAudit = { scoreCalculado ->
                                // Jack: Passando a nota real para a próxima tela
                                navController.navigate("resultado/${scoreCalculado.toString()}")
                            }
                        )
                    }

                    composable(
                        route = "resultado/{score}",
                        arguments = listOf(navArgument("score") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val scoreStr = backStackEntry.arguments?.getString("score") ?: "0.0"
                        val scoreDouble = scoreStr.toDoubleOrNull() ?: 0.0

                        ResultScreen(
                            score = scoreDouble,
                            empresa = empresaNome, // Recupera o nome gravado
                            setor = setorNome,     // Recupera o setor gravado
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