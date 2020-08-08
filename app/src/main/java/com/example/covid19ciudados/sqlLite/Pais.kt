package com.example.covid19ciudados.sqlLite

class Pais(
    id: Int,
    casos_totales: Long,
    casos_recuperados: Long,
    muertes: Long,
    nuevos_casos: Long,
    nombre_pais: String,
    porcentaje_recuperados: Int
) {

    var id: Int = 0
    var casos_totales_confirmados: Long = 0
    var casos_total_recuperados: Long = 0
    var total_muertes: Long = 0
    var nuevos_casos_confirmados: Long = 0
    var nombre_pais: String = ""
    var porcentaje_recuperados: Int = 0

    init {
        this.id = id
        this.casos_totales_confirmados = casos_totales
        this.casos_total_recuperados = casos_recuperados
        this.total_muertes = muertes
        this.nuevos_casos_confirmados = nuevos_casos
        this.nombre_pais = nombre_pais
        this.porcentaje_recuperados = porcentaje_recuperados
    }
}