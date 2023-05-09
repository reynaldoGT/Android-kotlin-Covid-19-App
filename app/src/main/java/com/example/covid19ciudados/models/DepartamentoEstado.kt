package com.example.covid19ciudados.departamentos


class CardDepartamento(nombre_departamento: String, cantidad_total: Int, cantidad_por_dia: Int) {
    var nombre_departamento: String = ""
    var cantidad_total: Int = 0
    var cantidad_por_dia: Int = 0


    init {
        this.nombre_departamento = nombre_departamento
        this.cantidad_total = cantidad_total
        this.cantidad_por_dia = cantidad_por_dia
    }
}