package com.example.gerenciamentodeviagens.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gerenciamentodeviagens.ui.screen.*
import com.example.gerenciamentodeviagens.viewmodel.*
import com.example.gerenciamentodeviagens.ui.viewmodel.RegistroViewModel
import androidx.compose.ui.platform.LocalContext
import com.example.gerenciamentodeviagens.data.AppDatabase
import com.example.gerenciamentodeviagens.data.repository.UsuarioRepository

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val dao = db.userDao()
    val repository = UsuarioRepository(dao)

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            val loginVM: LoginViewModel = viewModel(factory = LoginViewModelFactory(repository))

            LoginScreen(
                viewModel = loginVM,
                onLoginSucesso = { navController.navigate("home") },
                onIrParaRegistro = { navController.navigate("registro") },
                onEsqueciSenha = { navController.navigate("esqueci") }
            )
        }

        composable("registro") {
            val registroVM: RegistroViewModel = viewModel(factory = RegistroViewModelFactory(repository))

            RegistroScreen(
                viewModel = registroVM,
                onRegistroSucesso = { navController.popBackStack() }
            )
        }

        composable("esqueci") {
            EsqueciSenhaScreen(
                onVoltar = { navController.popBackStack() }
            )
        }

        composable("home") {
            Text("Bem-vinda à Home!")
        }
    }
}
