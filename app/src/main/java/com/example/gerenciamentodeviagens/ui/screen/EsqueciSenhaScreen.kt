package com.example.gerenciamentodeviagens.ui.screen

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EsqueciSenhaScreen(onVoltar: () -> Unit) {
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {

        Text("Recuperar Senha")

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") }
        )

        Button(onClick = { onVoltar() }) {
            Text("Enviar")
        }
    }
}