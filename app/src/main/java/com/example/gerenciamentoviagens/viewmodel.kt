package com.example.gerenciamentoviagens.viewmodel

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    fun login(email: String, senha: String): Boolean {
        return email.isNotEmpty() && senha.isNotEmpty()
    }

    fun register(nome: String, email: String, telefone: String, senha: String, confirmar: String): String {
        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty() || confirmar.isEmpty()) {
            return "Preencha todos os campos"
        }
        if (senha != confirmar) {
            return "As senhas não coincidem"
        }
        return "Sucesso"
    }

    fun forgotPassword(email: String): Boolean {
        return email.isNotEmpty()
    }
}