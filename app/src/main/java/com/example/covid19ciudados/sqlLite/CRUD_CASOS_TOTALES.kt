package com.example.covid19ciudados.sqlLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class CRUD_CASOS_TOTALES(context: Context) {

    private var helper: DatabaseHelper? = null

    init {
        helper = DatabaseHelper(context)
    }

    fun addRow(item_pais: Pais) {
        //Funcion que nso permite escribir en una base de datos
        val db: SQLiteDatabase = helper?.writableDatabase!!
        //Mapeo con los datos a insertar
        val values = ContentValues()
        values.put(CasosC19Contract.Companion.Entrada.COLUMNA_ID, 0)
        values.put(
            CasosC19Contract.Companion.Entrada.COLUMNA_CASOS_TOTALES,
            item_pais.casos_totales_confirmados
        )
        values.put(
            CasosC19Contract.Companion.Entrada.COLUMNA_CASOS_RECUPERADOS,
            item_pais.casos_total_recuperados
        )
        values.put(CasosC19Contract.Companion.Entrada.COLUMNA_MUERTES, item_pais.total_muertes)
        values.put(CasosC19Contract.Companion.Entrada.COLUMNA_NUEVOS_CASOS, item_pais.nuevos_casos_confirmados)
        values.put(CasosC19Contract.Companion.Entrada.COLUMNA_NOMBRE_PAIS, item_pais.nombre_pais)
        values.put(
            CasosC19Contract.Companion.Entrada.COLUMNA_PORCENTAJE_RECUPERADOS,
            item_pais.porcentaje_recuperados
        )

        //Insertamos una fila en la tabla
        val newRowId = db.insert(CasosC19Contract.Companion.Entrada.NOMBRE_TABLA, null, values)
        db.close()
    }


    fun getPaises(): ArrayList<Pais> {
        val items: ArrayList<Pais> = ArrayList()

        // Abrir la cbase de datos en mode de lectura
        val db: SQLiteDatabase = helper?.readableDatabase!!

        //Especifiar columnas que quiero consultar
        val columnas = arrayOf(
            CasosC19Contract.Companion.Entrada.COLUMNA_ID,
            CasosC19Contract.Companion.Entrada.COLUMNA_CASOS_TOTALES,
            CasosC19Contract.Companion.Entrada.COLUMNA_CASOS_RECUPERADOS,
            CasosC19Contract.Companion.Entrada.COLUMNA_MUERTES,
            CasosC19Contract.Companion.Entrada.COLUMNA_NUEVOS_CASOS,
            CasosC19Contract.Companion.Entrada.COLUMNA_NOMBRE_PAIS,
            CasosC19Contract.Companion.Entrada.COLUMNA_PORCENTAJE_RECUPERADOS
        )

        //Crear un cros para recorre la tabla
        val c: Cursor = db.query(
            CasosC19Contract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null
        )
        // Hacer un recorrido del cursor en la tabla
        while (c.moveToNext()) {
            items.add(
                Pais(
                    c.getInt((c.getColumnIndexOrThrow(CasosC19Contract.Companion.Entrada.COLUMNA_ID)))
                    ,
                    c.getLong((c.getColumnIndexOrThrow(CasosC19Contract.Companion.Entrada.COLUMNA_CASOS_TOTALES)))
                    ,
                    c.getLong((c.getColumnIndexOrThrow(CasosC19Contract.Companion.Entrada.COLUMNA_CASOS_RECUPERADOS)))
                    ,
                    c.getLong((c.getColumnIndexOrThrow(CasosC19Contract.Companion.Entrada.COLUMNA_MUERTES)))
                    ,
                    c.getLong((c.getColumnIndexOrThrow(CasosC19Contract.Companion.Entrada.COLUMNA_NUEVOS_CASOS)))
                    ,
                    c.getString((c.getColumnIndexOrThrow(CasosC19Contract.Companion.Entrada.COLUMNA_NOMBRE_PAIS))),
                    c.getInt((c.getColumnIndexOrThrow(CasosC19Contract.Companion.Entrada.COLUMNA_PORCENTAJE_RECUPERADOS)))
                )
            )
        }
        //Cerrar DB
        db.close()
        return items
    }
}