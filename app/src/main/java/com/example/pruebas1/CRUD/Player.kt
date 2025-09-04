package com.example.pruebas1.CRUD

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Players")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String = "",
    val username: String = "",
    val victorias: Int = 0,
    val derrotas: Int = 0
)

