package com.example.pruebas1.CRUD

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAOstc {

    @Query("SELECT * FROM Players")
    suspend fun GetPlayers(): MutableList<Player>

    @Query("SELECT * FROM Players WHERE id = :id")
    suspend fun GetUser(id: Int): Player

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun InsertPlayer(player: Player)

    @Query("UPDATE Players SET nombre=:nombre, username =:username, victorias=:victorias, derrotas=:derrotas WHERE id=:id")
    suspend fun UpdatePlayer(id:Int, nombre:String, username:String, victorias:Int, derrotas:Int)

    @Query("DELETE FROM Players WHERE id = :id")
    suspend fun DeletePlayer(id: Int)

}