package com.example.covid19ciudados.sqlLite

import android.provider.BaseColumns

class CasosC19Contract {

    companion object {
        val VERSION = 1
        class Entrada : BaseColumns {
            companion object {
                val NOMBRE_TABLA = "casos_totales"

                val COLUMNA_ID = "id"
                val COLUMNA_CASOS_TOTALES = "casos_totales"
                val COLUMNA_CASOS_RECUPERADOS = "casos_recuperados"
                val COLUMNA_MUERTES = "muertes"
                val COLUMNA_NUEVOS_CASOS = "nuevos_casos"
                val COLUMNA_NOMBRE_PAIS = "nombre_pais"
                val COLUMNA_PORCENTAJE_RECUPERADOS = "porcentaje_recuperados"
            }
        }
    }


}