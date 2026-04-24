package com.example.gerenciamentodeviagens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gerenciamentodeviagens.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UsuarioRepository) : ViewModel() {

    private val _loginSucesso = MutableStateFlow(false)
    val loginSucesso: StateFlow<Boolean> = _loginSucesso

    private val _erroMensagem = MutableStateFlow<String?>(null)
    val erroMensagem: StateFlow<String?> = _erroMensagem

    fun efetuarLogin(email: String, senha: String) {
        viewModelScope.launch {
            _erroMensagem.value = null
            val result = repository.login(email, senha)
            if (result.isSuccess) {
                _loginSucesso.value = true
            } else {
                _loginSucesso.value = false
                _erroMensagem.value = "E-mail ou senha incorretos"
            }
        }
    }

    fun resetarErro() {
        _erroMensagem.value = null
    }
}