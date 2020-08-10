package com.example.covid19ciudados.departamentos

data class DepartamentosInfo (
    val confirmados: List<Confirmado>,
    val decesos: List<Confirmado>,
    val recuperados: List<Confirmado>,
    val sospechosos: List<Confirmado>,
    val descartados: List<Confirmado>
)

data class Confirmado (
    val fecha: String,
    val dep: Dep
)

data class Dep (
    val la_paz: Int,
    val cochabamba: Int,
    val santa_cruz: Int,
    val oruro: Int,
    val potosí: Int,
    val tarija: Int,
    val chuquisaca: Int,
    val beni: Int,
    val pando: Int
)

