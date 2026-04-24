package com.example.gerenciamentodeviagens.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gerenciamentodeviagens.model.Usuario
import com.example.gerenciamentodeviagens.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class RegistroUiState {
    object Idle : RegistroUiState()
    object Loading : RegistroUiState()
    object Sucesso : RegistroUiState()
    data class Erro(val mensagem: String) : RegistroUiState()
}

class RegistroViewModel(private val repository: UsuarioRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<RegistroUiState>(RegistroUiState.Idle)
    val uiState: StateFlow<RegistroUiState> = _uiState

    fun registrar(nome: String, email: String, senha: String, confirmaSenha: String) {
        if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
            _uiState.value = RegistroUiState.Erro("Preencha todos os campos."); return
        }
        if (senha != confirmaSenha) {
            _uiState.value = RegistroUiState.Erro("As senhas não coincidem."); return
        }
        if (senha.length < 6) {
            _uiState.value = RegistroUiState.Erro("A senha deve ter pelo menos 6 caracteres."); return
        }
        viewModelScope.launch {
            _uiState.value = RegistroUiState.Loading
            val result = repository.registrar(Usuario(nome = nome, email = email, senha = senha))
            _uiState.value = result.fold(
                onSuccess = { RegistroUiState.Sucesso },
                onFailure = { RegistroUiState.Erro(it.message ?: "Erro desconhecido.") }
            )
        }
    }

    fun resetarEstado() { _uiState.value = RegistroUiState.Idle }
}
