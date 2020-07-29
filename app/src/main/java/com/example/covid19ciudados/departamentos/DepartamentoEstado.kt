package com.example.covid19ciudados.departamentos

class CardDepartamento(title: String, cantidad: String,cantidad_por_dia:String) {
    var title: String = ""
    var cantidad: String = ""
    var cantidad_por_dia :String =""

    init {
        this.title = title
        this.cantidad = cantidad
        this.cantidad_por_dia = cantidad_por_dia
    }
}