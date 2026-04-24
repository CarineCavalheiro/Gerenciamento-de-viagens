package com.example.gerenciamentodeviagens.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gerenciamentodeviagens.data.dao.UsuarioDao
import com.example.gerenciamentodeviagens.data.model.Usuario

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "gerenciamento_viagens.db")
                    .build().also { INSTANCE = it }
            }
    }
}
