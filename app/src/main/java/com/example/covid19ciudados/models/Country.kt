package com.example.covid19ciudados.models

data class Country(
    val Country: String,
    val CountryCode: String,
    val Date: String,
    val NewConfirmed: Int,
    val NewDeaths: Int,
    val NewRecovered: Int,
    val Slug: String,
    val TotalConfirmed: Int,
    val TotalDeaths: Int,
    val TotalRecovered: Int
)

data class Countries(
    val Country: String,
    val NewConfirmed: Int,
    val TotalConfirmed: String,
    val TotalDeaths: Int,
    val TotalRecovered: Int
)

data class Global(
    val TotalConfirmed: Int,
    val TotalDeaths: Int,
    val TotalRecovered: Int
)

data class GlobalInfo(
    val global: Global,
    val countries: ArrayList<Countries>
)

data class GlobalInformation(
    val Countries: List<Country>,
    val Date: String,
    val Global: GlobalX
)

data class GlobalX(
    val NewConfirmed: Int,
    val NewDeaths: Int,
    val NewRecovered: Int,
    val TotalConfirmed: Int,
    val TotalDeaths: Int,
    val TotalRecovered: Int
)