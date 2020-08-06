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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid19ciudados.Adaptador
import com.example.covid19ciudados.Card
import com.example.covid19ciudados.R
import com.example.covid19ciudados.information.AdapterMundiData
import com.example.covid19ciudados.information.GlobalInfo
import com.example.covid19ciudados.information.GlobalInfomation
import com.example.covid19ciudados.information.SharedCode.Companion.datoProcesado
import com.example.covid19ciudados.information.SharedCode.Companion.new_cases
import com.example.covid19ciudados.information.SharedCode.Companion.total_cases
import com.example.covid19ciudados.information.SharedCode.Companion.total_death
import com.example.covid19ciudados.information.SharedCode.Companion.total_recovered
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_mundial.*
import java.text.DecimalFormat


class Mundial : Fragment() {


    var listaCountries: RecyclerView? = null
    var adaptadorMundi: AdapterMundiData? = null
    var layout_Manager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mundial, container, false)
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
                    // Log.d("Ciudad", c19.Global.TotalConfirmed.toString())
                    val cards = ArrayList<Card>()
                    //c19.Global.TotalConfirmed.toString()
                    cards.add(
                        Card(
                            total_cases,
                            datoProcesado(c19.Global.TotalConfirmed)
                        )
                    )
                    cards.add(
                        Card(
                            total_recovered,
                            datoProcesado(c19.Global.TotalRecovered)
                        )
                    )
                    cards.add(
                        Card(
                            total_death,
                            datoProcesado(c19.Global.TotalDeaths)
                        )
                    )
                    cards.add(
                        Card(
                            new_cases,
                            datoProcesado(c19.Global.NewConfirmed)
                        )
                    )
                    //val grid = view?.findViewById<GridView>(R.id.gridInfo)
                    //tvFecha?.text = c19.Date
                    tvFecha?.text = "Datos en las últimas 24 horas"

                    val adaptor = Adaptador(activity!!.applicationContext, cards)

                    gridInfo?.adapter = adaptor

                    listaCountries?.setHasFixedSize(true)
                    adaptadorMundi = AdapterMundiData(ArrayList(c19.Countries))
                    layout_Manager = LinearLayoutManager(view?.context)

                    listaCountries = view?.findViewById(R.id.recyclerViewMundi)
                    listaCountries?.layoutManager = layout_Manager


                    listaCountries?.adapter = adaptadorMundi


                } catch (e: Exception) {
                    Log.d("error en la peticion", e.message.toString())
                }
            }, Response.ErrorListener { error ->
                Log.d("Error en el listener", error.message.toString())

            })
        queue.add(solicitud)

    }


}