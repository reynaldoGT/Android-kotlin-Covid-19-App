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
    val la_paz: Long,
    val cochabamba: Long,
    val santa_cruz: Long,
    val oruro: Long,
    val potosí: Long,
    val tarija: Long,
    val chuquisaca: Long,
    val beni: Long,
    val pando: Long
)

