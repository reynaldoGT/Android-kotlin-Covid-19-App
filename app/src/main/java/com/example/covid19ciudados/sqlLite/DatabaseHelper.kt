package com.example.covid19ciudados.sqlLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    CasosC19Contract.Companion.Entrada.NOMBRE_TABLA,
    null,
    CasosC19Contract.VERSION
) {

    companion object {
        val CREATE_TABLE_CASOS_TOTALES =
            "CREATE TABLE  ${CasosC19Contract.Companion.Entrada.NOMBRE_TABLA}(" +
                    "${CasosC19Contract.Companion.Entrada.COLUMNA_ID}  INTEGER PRIMARY KEY AUTOINCREMENT,," +
                    "${CasosC19Contract.Companion.Entrada.COLUMNA_CASOS_TOTALES} INTEGER," +
                    "${CasosC19Contract.Companion.Entrada.COLUMNA_CASOS_RECUPERADOS} INTEGER," +
                    "${CasosC19Contract.Companion.Entrada.COLUMNA_MUERTES} INTEGER," +
                    "${CasosC19Contract.Companion.Entrada.COLUMNA_NUEVOS_CASOS} INTEGER," +
                    "${CasosC19Contract.Companion.Entrada.COLUMNA_NOMBRE_PAIS} TEXT," +
                    "${CasosC19Contract.Companion.Entrada.COLUMNA_PORCENTAJE_RECUPERADOS} INTEGER)"


        val REMOVE_CASOS_TOTALES =
            "DROP TABLE IF EXISTS " + CasosC19Contract.Companion.Entrada.NOMBRE_TABLA
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_CASOS_TOTALES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(REMOVE_CASOS_TOTALES)
        onCreate(db)
    }

}