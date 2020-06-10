package com.example.covid19ciudados.information

class Countries(
    Country: String,
    NewConfirmed: Int,
    TotalConfirmed: String,
    TotalDeaths: Int,
    TotalRecovered: Int
) {
    /*"Countries":[
    {
        "Country":"Afghanistan",
        "CountryCode":"AF",
        "Slug":"afghanistan",
        "NewConfirmed":575,
        "TotalConfirmed":20917,
        "NewDeaths":12,
        "TotalDeaths":369,
        "NewRecovered":296,
        "TotalRecovered":2171,
        "Date":"2020-06-09T16:42:03Z"
    },*/
    var Country: String = ""
    var NewConfirmed: Int = 0
    var TotalConfirmed: String = ""
    var TotalDeaths: Int = 0
    var TotalRecovered: Int = 0

    init {
        this.Country = Country
        this.NewConfirmed = NewConfirmed
        this.TotalConfirmed = TotalConfirmed
        this.TotalDeaths = TotalDeaths
        this.TotalRecovered = TotalRecovered
    }
}