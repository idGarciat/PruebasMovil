package com.example.pruebas1.bdroom


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LugarBDRoom::class], version = 1, exportSchema = false)
abstract class BaseDatosBDRoom: RoomDatabase() {
    abstract fun daoLugar(): DAOLugarBDRoom
}
