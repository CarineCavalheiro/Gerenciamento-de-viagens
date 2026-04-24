package com.example.gerenciamentodeviagens.ui.screen

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.example.gerenciamentodeviagens.ui.viewmodel.RegistroViewModel
import com.example.gerenciamentodeviagens.ui.viewmodel.RegistroUiState

@Composable
fun RegistroScreen(
    viewModel: RegistroViewModel,
    onRegistroSucesso: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is RegistroUiState.Sucesso) {
            onRegistroSucesso()
            viewModel.resetarEstado()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registro", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(nome, { nome = it }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(email, { email = it }, label = { Text("E-mail") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(telefone, { telefone = it }, label = { Text("Telefone") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(senha, { senha = it }, label = { Text("Senha") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(confirmarSenha, { confirmarSenha = it }, label = { Text("Confirmar Senha") }, modifier = Modifier.fillMaxWidth())

        if (uiState is RegistroUiState.Erro) {
            Text((uiState as RegistroUiState.Erro).mensagem, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.registrar(nome, email, senha, confirmarSenha) },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState !is RegistroUiState.Loading
        ) {
            if (uiState is RegistroUiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Registrar")
            }
        }
    }
}