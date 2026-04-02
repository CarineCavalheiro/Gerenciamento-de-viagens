package com.example.gerenciamentoviagens.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.gerenciamentoviagens.ui.screens.*

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "login") {

        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("forgot") { ForgotPasswordScreen(navController) }
        composable("menu") { MenuScreen(navController) }
    }
}