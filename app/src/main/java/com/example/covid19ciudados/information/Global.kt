package com.example.covid19ciudados.information

class Global(TotalConfirmed:Int,
             TotalDeaths :Int,
             TotalRecovered:Int) {

    /*"TotalConfirmed":7215417,

    "TotalDeaths":414415,

    "TotalRecovered":3292916*/
    var TotalConfirmed:Int=0
    var TotalDeaths :Int= 0
    var TotalRecovered:Int =0
    init{
        this.TotalConfirmed = TotalConfirmed
        this.TotalDeaths = TotalDeaths
        this.TotalRecovered = TotalRecovered
    }
}