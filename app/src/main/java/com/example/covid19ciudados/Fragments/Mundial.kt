package com.example.covid19ciudados.Fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*


import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19ciudados.*
import com.example.covid19ciudados.information.AdapterMundiData
import com.example.covid19ciudados.information.GlobalInformation
import com.example.covid19ciudados.information.SharedCode.Companion.dataProcessed
import com.example.covid19ciudados.information.SharedCode.Companion.new_cases
import com.example.covid19ciudados.information.SharedCode.Companion.total_cases
import com.example.covid19ciudados.information.SharedCode.Companion.total_death
import com.example.covid19ciudados.information.SharedCode.Companion.total_recovered
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_mundial.*


class Mundial : Fragment() {

    private var network: Network? = null


    var listCountries: RecyclerView? = null
    var adapterMundiData: AdapterMundiData? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view: View? = null

        network = Network(activity as AppCompatActivity)


        if (!network!!.hayRed()) {


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
        network?.httpGetRequest(activity!!.applicationContext, url, object : HttpResponse {
            override fun httpResponseSuccess(response: String) {
                //usando la libreria gson para parsear
                val gson = Gson()
                val c19 = gson.fromJson(response, GlobalInformation::class.java)

                // Log.d("Ciudad", c19.Global.TotalConfirmed.toString())
                val cards = ArrayList<Card>()
                //c19.Global.TotalConfirmed.toString()
                cards.add(
                    Card(
                        total_cases,
                        dataProcessed(c19.Global.TotalConfirmed)
                    )
                )
                cards.add(
                    Card(
                        total_recovered,
                        dataProcessed(c19.Global.TotalRecovered)
                    )
                )
                cards.add(
                    Card(
                        total_death,
                        dataProcessed(c19.Global.TotalDeaths)
                    )
                )
                cards.add(
                    Card(
                        new_cases,
                        dataProcessed(c19.Global.NewConfirmed)
                    )
                )
                //val grid = view?.findViewById<GridView>(R.id.gridInfo)
                //tvFecha?.text = c19.Date
                tvFecha?.text = "Datos en las últimas 24 horas"

                val adaptor = Adaptador(activity!!.applicationContext, cards)

                val sortedList = c19.Countries.sortedByDescending { it.TotalConfirmed }


                gridInfo?.adapter = adaptor

                listCountries?.setHasFixedSize(true)
                adapterMundiData = AdapterMundiData(ArrayList(sortedList))
                layoutManager = LinearLayoutManager(view?.context)

                listCountries = view?.findViewById(R.id.recyclerViewMundi)
                listCountries?.layoutManager = layoutManager

                listCountries?.adapter = adapterMundiData

                // para detener el circular progress bar

                view?.findViewById<ProgressBar>(R.id.progressBar)!!.visibility = View.GONE


            }

        })

    }


    private fun isNetworkConnected(): Boolean {
        val conectivityManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netWorkInfo = conectivityManager.activeNetworkInfo
        return netWorkInfo != null && netWorkInfo.isConnected
    }


}