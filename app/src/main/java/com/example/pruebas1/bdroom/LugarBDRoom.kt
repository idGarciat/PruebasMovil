package com.example.pruebas1.bdroom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lugaresbdroom")
data class LugarBDRoom(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String = "",
    val descripcion: String = "",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0
)




