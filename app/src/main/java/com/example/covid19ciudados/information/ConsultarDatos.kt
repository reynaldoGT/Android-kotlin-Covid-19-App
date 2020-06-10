package com.example.covid19ciudados.information

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class ConsultarDatos {


    companion object {

        fun getInfo(context: Context) {
            var URL = "https://api.covid19api.com/summary"
            val queue = Volley.newRequestQueue(context)
            val solicitud =
                StringRequest(Request.Method.GET, URL, Response.Listener { response ->
                    try {
                        //Log.d("solicitud por volley", response)
                        //usando la libreria gson para parsear
                        val gson = Gson()
                        val information = gson.fromJson(response, GlobalInfo::class.java)

                        Log.d("success", information.countries?.get(0)!!.Country)

                        /*var tvCiudad: TextView? = null
                       var tvGrados: TextView? = null
                       var tvEstatus: TextView? = null


                       tvCiudad = findViewById(R.id.tvCiudad)
                       tvGrados = findViewById(R.id.tvGrados)
                       tvEstatus = findViewById(R.id.tvEstado)

                       tvCiudad.text = ciudad.name
                       tvGrados.text = ciudad.main.temp.toString() + "°"
                       tvEstatus.text = ciudad.weather.get(0).description
   */

                    } catch (e: Exception) {
                        Log.d("error en la peticion", e.message!!)
                    }
                }, Response.ErrorListener { error ->
                    Log.d("Error en el listener", error.message!!)

                })
            queue.add(solicitud)
        }
    }
}