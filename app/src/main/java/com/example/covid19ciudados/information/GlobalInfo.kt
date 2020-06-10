package com.example.covid19ciudados.information

class GlobalInfo(global: Global, countries: ArrayList<Countries>) {
    var global: Global? = null
    var countries: ArrayList<Countries>? = null

    init {
        this.global = global
        this.countries = countries
    }
}