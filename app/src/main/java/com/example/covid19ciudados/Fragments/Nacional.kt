package com.example.covid19ciudados.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid19ciudados.Adaptador
import com.example.covid19ciudados.Card
import com.example.covid19ciudados.departamentos.DepartamentosInfo
import com.example.covid19ciudados.R
import com.example.covid19ciudados.departamentos.AdaptadorDepartamento
import com.example.covid19ciudados.departamentos.CardDepartamento
import com.example.covid19ciudados.information.GlobalInfomation
import com.example.covid19ciudados.information.SharedCode.Companion.datoProcesado
import com.example.covid19ciudados.information.SharedCode.Companion.new_cases
import com.example.covid19ciudados.information.SharedCode.Companion.total_cases
import com.example.covid19ciudados.information.SharedCode.Companion.total_death
import com.example.covid19ciudados.information.SharedCode.Companion.total_recovered
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_nacional.*

import kotlin.collections.ArrayList


class Nacional : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_nacional, container, false)
        consultarDatos()

        consultarDatosDepartamentos()
        return view

    }

    private fun consultarDatos() {

        val url =
            "https://api.covid19api.com/summary"
        val queue = Volley.newRequestQueue(activity?.applicationContext)

        val request =
            StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                try {
                    val gson = Gson()
                    val c19 = gson.fromJson(response, GlobalInfomation::class.java)

                    val cards = ArrayList<Card>()

                    cards.add(
                        Card(
                            total_cases,
                            datoProcesado(c19.Countries[20].TotalConfirmed)
                        )
                    )
                    cards.add(
                        Card(
                            total_recovered,

                            datoProcesado(c19.Countries[20].TotalRecovered)
                        )
                    )
                    cards.add(
                        Card(
                            total_death,
                            datoProcesado(c19.Countries[20].TotalDeaths)

                        )
                    )
                    cards.add(
                        Card(
                            new_cases,
                            datoProcesado(c19.Countries[20].NewConfirmed)
                        )
                    )

                    //var grid = view?.findViewById<GridView>(R.id.gridInfo)
                    //var tvFecha = view?.findViewById<TextView>(R.id.tvFecha)

                    //tvFecha?.text = c19.Date
                    tvFecha.text = "Datos en las últimas 24 horas"


                    val adaptor = Adaptador(activity!!.applicationContext, cards)
                    //? nuevos casos registrados

                    gridInfo.adapter = adaptor


                } catch (e: Exception) {
                    Log.d("error en la peticion", e.message.toString())
                }
            }, Response.ErrorListener { error ->
                Log.d("Error en el listener", error.message.toString())

            })
        queue.add(request)

    }

    private fun consultarDatosDepartamentos() {
        val url =
            "https://mauforonda.github.io/covid19-bolivia/data.json"
        val queue = Volley.newRequestQueue(activity?.applicationContext)

        val request =
            StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                try {

                    val gson = Gson()
                    val departamentosInfo = gson.fromJson(response, DepartamentosInfo::class.java)

                    val cardsDepartaments = ArrayList<CardDepartamento>()
                    //c19.Global.TotalConfirmed.toString()
                    cardsDepartaments.add(
                        CardDepartamento(
                            "La Paz",
                            departamentosInfo.confirmados[0].dep.la_paz,
                            (departamentosInfo.confirmados[0].dep.la_paz - departamentosInfo.confirmados[2].dep.la_paz)
                        )
                    )
                    cardsDepartaments.add(
                        CardDepartamento(
                            "Oruro",
                            departamentosInfo.confirmados[0].dep.oruro,
                            (departamentosInfo.confirmados[0].dep.oruro - departamentosInfo.confirmados[2].dep.oruro)
                        )
                    )
                    cardsDepartaments.add(
                        CardDepartamento(
                            "Potosi",
                            departamentosInfo.confirmados[0].dep.potosí,
                            (departamentosInfo.confirmados[0].dep.potosí - departamentosInfo.confirmados[2].dep.potosí)
                        )
                    )
                    cardsDepartaments.add(
                        CardDepartamento(
                            "Cochabamba",
                            departamentosInfo.confirmados[0].dep.cochabamba,
                            (departamentosInfo.confirmados[0].dep.cochabamba - departamentosInfo.confirmados[2].dep.cochabamba)
                        )
                    )
                    cardsDepartaments.add(
                        CardDepartamento(
                            "Tarija",
                            departamentosInfo.confirmados[0].dep.tarija,
                            departamentosInfo.confirmados[0].dep.tarija - departamentosInfo.confirmados[2].dep.tarija
                        )
                    )
                    cardsDepartaments.add(
                        CardDepartamento(
                            "Chuquisaca",
                            departamentosInfo.confirmados[0].dep.chuquisaca,
                            (departamentosInfo.confirmados[0].dep.chuquisaca - departamentosInfo.confirmados[2].dep.chuquisaca)
                        )
                    )

                    cardsDepartaments.add(
                        CardDepartamento(
                            "Santa Cruz",
                            departamentosInfo.confirmados[0].dep.santa_cruz,
                            (departamentosInfo.confirmados[0].dep.santa_cruz - departamentosInfo.confirmados[2].dep.santa_cruz)
                        )
                    )
                    cardsDepartaments.add(
                        CardDepartamento(
                            "Beni",
                            departamentosInfo.confirmados[0].dep.beni,
                            (departamentosInfo.confirmados[0].dep.beni - departamentosInfo.confirmados[2].dep.beni)
                        )
                    )
                    cardsDepartaments.add(
                        CardDepartamento(
                            "Pando",
                            departamentosInfo.confirmados[0].dep.pando,
                            (departamentosInfo.confirmados[0].dep.pando - departamentosInfo.confirmados[2].dep.pando)
                        )
                    )
                    // Ordenando la lista para poder ver la cifra mas alta por dia
                    val sortedList = cardsDepartaments.sortedByDescending { it.cantidad_por_dia }
                    // aqui es donde se toma el array de departamentos par mostrarlos
                    val adapter =
                        AdaptadorDepartamento(
                            activity!!.applicationContext,
                            ArrayList(sortedList)
                        )

                    lvDepartamentos.adapter = adapter


                } catch (e: Exception) {
                    Log.d("error en la peticion", e.message.toString())
                }
            }, Response.ErrorListener { error ->
                Log.d("Error en el listener", error.message.toString())

            })
        queue.add(request)
    }


}


