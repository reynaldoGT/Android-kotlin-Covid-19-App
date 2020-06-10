package com.example.covid19ciudados.Fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid19ciudados.Adaptador
import com.example.covid19ciudados.Card
import com.example.covid19ciudados.R
import com.example.covid19ciudados.information.GlobalInfo
import com.example.covid19ciudados.information.GlobalInfomation
import com.google.gson.Gson
import java.text.DecimalFormat


class Mundial : Fragment() {

    var txtTotal: TextView? = null
    var txtRecuperados: TextView? = null
    var txtDead: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mundial, container, false)

        txtTotal = view.findViewById<TextView>(R.id.tvc19title)

        consultarDatos()



        return view
    }

    private fun consultarDatos() {

        val URL =
            "https://api.covid19api.com/summary"
        val queue = Volley.newRequestQueue(activity?.applicationContext)

        val solicitud =
            StringRequest(Request.Method.GET, URL, Response.Listener<String> { response ->
                try {
//                    Log.d("solicitud por volley", response)
                    //usando la libreria gson para parsear
                    val gson = Gson()
                    val c19 = gson.fromJson(response, GlobalInfomation::class.java)

                    Log.d("Ciudad", c19.Global.TotalConfirmed.toString())
                    val dec = DecimalFormat("#,###")
                    var cards = ArrayList<Card>()
                    //c19.Global.TotalConfirmed.toString()
                    cards.add(Card("Casos Totales", dec.format(c19.Global.TotalConfirmed)) )
                    cards.add(Card("Casos Recuperados", dec.format(c19.Global.TotalRecovered)))
                    cards.add(Card("Muertes", dec.format(c19.Global.TotalDeaths )))
                    cards.add(Card("Nuevos Confirmados", dec.format(c19.Global.NewConfirmed)))

                    var grid = view?.findViewById<GridView>(R.id.gridInfo)

                    //var adaptador = ArrayAdapter<Fruta>(this, android.R.layout.simple_list_item_1, frutas)
                    val adaptor = Adaptador(activity!!.applicationContext, cards)
                    grid?.adapter = adaptor


                } catch (e: Exception) {
                    Log.d("error en la peticion", e.message)
                }
            }, Response.ErrorListener { error ->
                Log.d("Error en el listener", error.message.toString())

            })
        queue.add(solicitud)

    }

}