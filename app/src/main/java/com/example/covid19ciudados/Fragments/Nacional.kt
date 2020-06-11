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
import com.example.covid19ciudados.R
import com.example.covid19ciudados.information.GlobalInfomation
import com.google.gson.Gson
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Nacional.newInstance] factory method to
 * create an instance of this fragment.
 */
class Nacional : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_nacional, container, false)
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

                    Log.d("Pais", c19.Countries[20].Country)

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
}