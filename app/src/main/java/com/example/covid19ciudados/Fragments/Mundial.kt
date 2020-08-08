package com.example.covid19ciudados.Fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.covid19ciudados.information.GlobalInfomation
import com.example.covid19ciudados.information.SharedCode.Companion.datoProcesado
import com.example.covid19ciudados.information.SharedCode.Companion.new_cases
import com.example.covid19ciudados.information.SharedCode.Companion.total_cases
import com.example.covid19ciudados.information.SharedCode.Companion.total_death
import com.example.covid19ciudados.information.SharedCode.Companion.total_recovered
import com.example.covid19ciudados.sqlLite.CRUD_CASOS_TOTALES
import com.example.covid19ciudados.sqlLite.Pais
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_mundial.*


class Mundial : Fragment() {

    var crud: CRUD_CASOS_TOTALES? = null

    var listaCountries: RecyclerView? = null
    var adaptadorMundi: AdapterMundiData? = null
    var layout_Manager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mundial, container, false)

        //inicializando la clase del crud
        crud = CRUD_CASOS_TOTALES(view!!.context)


        if (isNetworkConnected()) {
            consultData()
            Log.d("hay internet", "true")
        } else {
            Log.d("No hay internet", "true")
            queryDatabaseData()

        }

        return view
    }

    private fun consultData() {

        val url =
            "https://api.covid19api.com/summary"
        val queue = Volley.newRequestQueue(activity?.applicationContext)

        val request =
            StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                try {
//                    Log.d("solicitud por volley", response)
                    //usando la libreria gson para parsear
                    val gson = Gson()
                    val c19 = gson.fromJson(response, GlobalInfomation::class.java)

                    //LLenando la base de datos
                    for (c19Contry in c19.Countries) {
                        Log.d("registro Añadido", "true")
                        crud?.addRow(
                            Pais(
                                0,
                                casos_totales = c19Contry.TotalConfirmed.toLong(),
                                casos_recuperados = c19Contry.TotalRecovered.toLong(),
                                muertes = c19Contry.TotalDeaths.toLong(),
                                nombre_pais = c19Contry.Country,
                                nuevos_casos = c19Contry.NewConfirmed.toLong(),
                                porcentaje_recuperados = 0
                            )
                        )
                    }
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
        queue.add(request)

    }

    fun isNetworkConnected(): Boolean {
        val conectivytiManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netWorkInfo = conectivytiManager.activeNetworkInfo
        return netWorkInfo != null && netWorkInfo.isConnected
    }


    fun queryDatabaseData() {
        val paises = crud?.getPaises()

        for (pais in paises!!) {
            Log.d("pais", pais.nombre_pais)
        }

        listaCountries?.setHasFixedSize(true)
        //adaptadorMundi = AdapterMundiData(ArrayList(c19.Countries))
        layout_Manager = LinearLayoutManager(view?.context)

        listaCountries = view?.findViewById(R.id.recyclerViewMundi)
        listaCountries?.layoutManager = layout_Manager

        listaCountries?.adapter = adaptadorMundi
    }

}