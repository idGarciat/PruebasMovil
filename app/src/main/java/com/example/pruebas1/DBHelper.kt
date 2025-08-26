package com.example.pruebas1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createtable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NAME TEXT, "
                + "$COLUMN_DESCRIPCION TEXT, "
                + "$COLUMN_LATITUD REAL, "
                + "$COLUMN_LONGITUD REAL)")
        db?.execSQL(createtable

        )



    }

    override fun onUpgrade(
        p0: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")

    }

    fun insertarLugar(lugar: Lugar) : Long
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, lugar.nombre)
        values.put(COLUMN_DESCRIPCION, lugar.descripcion)
        values.put(COLUMN_LATITUD, lugar.latitud)
        values.put(COLUMN_LONGITUD, lugar.longitud)
        return db.insert(TABLE_NAME, null, values)

    }

    fun mostrarLugares(): List<Lugar> {
        val lugares = mutableListOf<Lugar>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION))
                val latitud = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LATITUD))
                val longitud = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LONGITUD))
                val lugar = Lugar(id, nombre, descripcion, latitud, longitud)
                lugares.add(lugar)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lugares
    }

    fun actualizarLugar(lugar: Lugar): Int {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, lugar.nombre)
        values.put(COLUMN_DESCRIPCION, lugar.descripcion)
        values.put(COLUMN_LATITUD, lugar.latitud)
        values.put(COLUMN_LONGITUD, lugar.longitud)
        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(lugar.id.toString()))


    }

    fun borrarLugar(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    companion object{
        const val DATABASE_NAME = "lugares.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "lugares"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "nombre"
        const val COLUMN_DESCRIPCION = "descripcion"
        const val COLUMN_LATITUD = "lstitud"
        const val COLUMN_LONGITUD = "longitud"
    }

}