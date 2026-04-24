package com.example.gerenciamentodeviagens.data.repository

import com.example.gerenciamentodeviagens.data.UserDao
import com.example.gerenciamentodeviagens.model.Usuario

class UsuarioRepository(private val dao: UserDao) {
    suspend fun registrar(usuario: Usuario): Result<Unit> = try {
        if (dao.getUserByEmail(usuario.email) != null)
            Result.failure(Exception("E-mail ja cadastrado."))
        else { dao.inserir(usuario); Result.success(Unit) }
    } catch (e: Exception) { Result.failure(e) }

    suspend fun login(email: String, senha: String): Result<Usuario> = try {
        val u = dao.getUserByEmail(email)
        if (u != null && u.senha == senha) Result.success(u) 
        else Result.failure(Exception("E-mail ou senha incorretos."))
    } catch (e: Exception) { Result.failure(e) }
}
