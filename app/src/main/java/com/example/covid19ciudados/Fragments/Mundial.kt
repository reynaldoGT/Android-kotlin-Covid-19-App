package com.example.covid19ciudados.Fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.ProgressBar
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
import com.example.covid19ciudados.information.GlobalInfomation
import com.example.covid19ciudados.information.SharedCode.Companion.datoProcesado
import com.example.covid19ciudados.information.SharedCode.Companion.new_cases
import com.example.covid19ciudados.information.SharedCode.Companion.total_cases
import com.example.covid19ciudados.information.SharedCode.Companion.total_death
import com.example.covid19ciudados.information.SharedCode.Companion.total_recovered
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_mundial.*


class Mundial : Fragment() {


    var listaCountries: RecyclerView? = null
    var adaptadorMundi: AdapterMundiData? = null
    var layout_Manager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view: View? = null


        if (!isNetworkConnected()) {


            view = inflater.inflate(R.layout.no_internet, container, false)

            val imageView = view.findViewById<ImageView>(R.id.noWifiImage)
            val textView = view.findViewById<TextView>(R.id.tvNoInternet)

            val anim: Animation = AlphaAnimation(0.0f, 1.0f)
            anim.duration = 1200 //You can manage the blinking time with this parameter

            anim.startOffset = 10
            anim.repeatMode = Animation.REVERSE
            anim.repeatCount = Animation.INFINITE
            imageView.startAnimation(anim)
            textView.startAnimation(anim)


        } else {
            view = inflater.inflate(R.layout.fragment_mundial, container, false)

            consultData()

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

                    val sortedList = c19.Countries.sortedByDescending { it.TotalConfirmed }


                    gridInfo?.adapter = adaptor

                    listaCountries?.setHasFixedSize(true)
                    adaptadorMundi = AdapterMundiData(ArrayList(sortedList))
                    layout_Manager = LinearLayoutManager(view?.context)

                    listaCountries = view?.findViewById(R.id.recyclerViewMundi)
                    listaCountries?.layoutManager = layout_Manager

                    listaCountries?.adapter = adaptadorMundi

                    // para detener el circular progress bar

                    view?.findViewById<ProgressBar>(R.id.progressBar)!!.visibility = View.GONE

                } catch (e: Exception) {
                    Log.d("error en la peticion", e.message.toString())
                }
            }, Response.ErrorListener { error ->
                Log.d("Error en el listener", error.message.toString())

            })
        queue.add(request)

    }

    private fun isNetworkConnected(): Boolean {
        val conectivityManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netWorkInfo = conectivityManager.activeNetworkInfo
        return netWorkInfo != null && netWorkInfo.isConnected
    }


}