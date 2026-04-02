@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gerenciamentoviagens.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.gerenciamentoviagens.viewmodel.AuthViewModel

// ---------------------- LOGIN ----------------------

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text("Login") }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                visualTransformation = if (showPassword)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.8f),
                trailingIcon = {
                    TextButton(onClick = { showPassword = !showPassword }) {
                        Text(if (showPassword) "Ocultar" else "Mostrar")
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (viewModel.login(email, senha)) {
                    navController.navigate("menu")
                }
            }) {
                Text("Entrar")
            }

            TextButton(onClick = { navController.navigate("register") }) {
                Text("Novo usuário")
            }

            TextButton(onClick = { navController.navigate("forgot") }) {
                Text("Esqueci minha senha")
            }
        }
    }
}

// ---------------------- REGISTER ----------------------

@Composable
fun RegisterScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmar by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text("Cadastro") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(nome, { nome = it }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth(0.8f))
            OutlinedTextField(email, { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth(0.8f))
            OutlinedTextField(telefone, { telefone = it }, label = { Text("Telefone") }, modifier = Modifier.fillMaxWidth(0.8f))
            OutlinedTextField(senha, { senha = it }, label = { Text("Senha") }, modifier = Modifier.fillMaxWidth(0.8f))
            OutlinedTextField(confirmar, { confirmar = it }, label = { Text("Confirmar Senha") }, modifier = Modifier.fillMaxWidth(0.8f))

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                mensagem = viewModel.register(nome, email, telefone, senha, confirmar)
                if (mensagem == "Sucesso") {
                    navController.navigate("login")
                }
            }) {
                Text("Cadastrar")
            }

            Text(mensagem)
        }
    }
}

// ---------------------- FORGOT PASSWORD ----------------------

@Composable
fun ForgotPasswordScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {

    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text("Recuperar Senha") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Digite seu e-mail") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (viewModel.forgotPassword(email)) {
                    navController.navigate("login")
                }
            }) {
                Text("Enviar")
            }
        }
    }
}

// ---------------------- MENU ----------------------

@Composable
fun MenuScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text("Menu") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
            }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Menu Principal", style = MaterialTheme.typography.headlineMedium)
        }
    }
}