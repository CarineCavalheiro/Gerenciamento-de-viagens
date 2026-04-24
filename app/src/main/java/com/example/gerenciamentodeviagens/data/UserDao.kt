package com.example.gerenciamentodeviagens.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gerenciamentodeviagens.model.Usuario

@Dao
interface UserDao {
    @Insert
    suspend fun inserir(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): Usuario?
}