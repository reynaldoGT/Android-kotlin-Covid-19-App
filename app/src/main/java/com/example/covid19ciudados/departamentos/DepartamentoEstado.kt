package com.example.covid19ciudados.departamentos

class CardDepartamento(title: String, cantidad: Int, cantidad_por_dia: Int) {
    var title: String = ""
    var cantidad: Int = 0
    var cantidad_por_dia: Int = 0

    init {
        this.title = title
        this.cantidad = cantidad
        this.cantidad_por_dia = cantidad_por_dia
    }
}