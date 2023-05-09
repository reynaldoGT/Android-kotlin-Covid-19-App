package com.example.covid19ciudados.utils

import java.text.DecimalFormat

class SharedCode {

    companion object {
        const val total_cases: String = "Casos Totales"
        const val total_recovered: String = "Casos Recuperados"
        const val total_death: String = "Muertes"
        const val new_cases: String = "Nuevos Casos"

        fun dataProcessed(into: Int): String {
            val dec = DecimalFormat("#,###")
            return (dec.format(into)).toString()
                .replace(',', '.')
        }

    }

}