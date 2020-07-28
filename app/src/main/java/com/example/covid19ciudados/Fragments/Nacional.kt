package com.example.covid19ciudados.Fragments

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
import com.example.covid19ciudados.departamentos.DepartamentosInfo
import com.example.covid19ciudados.R
import com.example.covid19ciudados.departamentos.AdaptadorDepartamento
import com.example.covid19ciudados.information.GlobalInfomation
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_nacional.*

import java.text.DecimalFormat

import kotlin.collections.ArrayList


class Nacional : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_nacional, container, false)
        consultarDatos()

        consultarDatosDepartamentos()
        return view


    }

    private fun consultarDatos() {

        val URL =
            "https://api.covid19api.com/summary"
        val queue = Volley.newRequestQueue(activity?.applicationContext)

        val solicitud =
            StringRequest(Request.Method.GET, URL, Response.Listener<String> { response ->
                try {
                    val gson = Gson()
                    val c19 = gson.fromJson(response, GlobalInfomation::class.java)

/*Log.d("Pais", c19.Countries[20].Country)*/

                    val dec = DecimalFormat("#,###")
                    var cards = ArrayList<Card>()
                    //c19.Global.TotalConfirmed.toString()
                    cards.add(Card("Casos Confirmados", dec.format(c19.Countries[20].TotalConfirmed)) )
                    cards.add(Card("Casos Recuperados", dec.format(c19.Countries[20].TotalRecovered)))
                    cards.add(Card("Muertes", dec.format(c19.Countries[20].TotalDeaths )))
                    cards.add(Card("Nuevos Confirmados", dec.format(c19.Countries[20].NewConfirmed)))

                    var grid = view?.findViewById<GridView>(R.id.gridInfo)
                    var tvFecha = view?.findViewById<TextView>(R.id.tvFecha)

                    //tvFecha?.text = c19.Date
                    tvFecha?.text = "Datos en las últimas 24 horas"


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

    private fun consultarDatosDepartamentos() {

        val URL =
            "https://mauforonda.github.io/covid19-bolivia/data.json"
        val queue = Volley.newRequestQueue(activity?.applicationContext)

        val solicitud =
            StringRequest(Request.Method.GET, URL, Response.Listener<String> { response ->
                try {
//                    Log.d("solicitud por volley", response)
                    //usando la libreria gson para parsear
                    val gson = Gson()
                    val departamentosInfo = gson.fromJson(response, DepartamentosInfo::class.java)


                    //Log.d("la paz","en al paz hay lapaz ${departamentosInfo.confirmados[1].dep.potosí}")

                    var cards = ArrayList<Card>()
                    //c19.Global.TotalConfirmed.toString()
                    cards.add(Card("La paz", departamentosInfo.confirmados[0].dep.la_paz.toString()) )
                    cards.add(Card("Oruro", departamentosInfo.confirmados[0].dep.oruro.toString()))
                    cards.add(Card("Potosi", departamentosInfo.confirmados[0].dep.potosí.toString()))

                    cards.add(Card("Cochabamba", departamentosInfo.confirmados[0].dep.cochabamba.toString()) )
                    cards.add(Card("Tarija", departamentosInfo.confirmados[0].dep.tarija.toString()))
                    cards.add(Card("Chuquisaca", departamentosInfo.confirmados[0].dep.chuquisaca.toString()))

                    cards.add(Card("Santa Cruz", departamentosInfo.confirmados[0].dep.santa_cruz.toString()) )
                    cards.add(Card("Beni", departamentosInfo.confirmados[0].dep.beni.toString()))
                    cards.add(Card("Pando", departamentosInfo.confirmados[0].dep.pando.toString()))


                    val adaptador = AdaptadorDepartamento(activity!!.applicationContext, cards)

                    lvDepartamentos.adapter = adaptador


                } catch (e: Exception) {
                    Log.d("error en la peticion", e.message)
                }
            }, Response.ErrorListener { error ->
                Log.d("Error en el listener", error.message.toString())

            })
        queue.add(solicitud)

    }
}