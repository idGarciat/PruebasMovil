package com.example.pruebas1.bdroom


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAOLugarBDRoom {

    @Query("SELECT * FROM lugaresbdroom")
    suspend fun obtenerLugares(): MutableList<LugarBDRoom>

    @Query("SELECT * FROM lugaresbdroom WHERE id = :id")
    suspend fun obtenerLugar(id: Int): LugarBDRoom

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarLugar(lugar: LugarBDRoom)

    @Query("UPDATE lugaresbdroom SET nombre=:nombre, descripcion=:descripcion, latitud=:latitud, longitud=:longitud WHERE id=:id")
    suspend fun actualizarLugar(id:Int, nombre:String, descripcion:String, latitud:Double, longitud:Double)

    @Query("DELETE FROM lugaresbdroom WHERE id = :id")
    suspend fun eliminarLugar(id: Int)
}