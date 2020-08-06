package com.example.covid19ciudados.information

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19ciudados.Fragments.Mundial
import com.example.covid19ciudados.R
import com.example.covid19ciudados.information.SharedCode.Companion.datoProcesado

class AdapterMundiData(items: ArrayList<Country>) :
    RecyclerView.Adapter<AdapterMundiData.ViewHolder>() {

    //? Properties
    var items: ArrayList<Country>? = null
    var viewHolder: ViewHolder? = null

    // constructor
    init {
        this.items = items

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_country_layout, parent, false)
        viewHolder = ViewHolder(vista)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)

        holder.countryName?.text = item?.Country!!

        holder.totalCases?.text = datoProcesado(item.TotalConfirmed)
        holder.newCases?.text = datoProcesado(item.NewConfirmed)
        holder.allDeath?.text = datoProcesado(item.TotalDeaths)
        holder.recovery?.text = datoProcesado(item.TotalRecovered)
        holder.recoverPercentaje?.text = ((item.TotalRecovered *100)/item.TotalConfirmed).toString() + " %"

    }


    class ViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        var vista = vista
        var countryName: TextView? = null
        var totalCases: TextView? = null
        var newCases: TextView? = null
        var allDeath: TextView? = null
        var recovery: TextView? = null
        var recoverPercentaje: TextView? = null

        init {
            countryName = vista.findViewById(R.id.tvCountryName)
            totalCases = vista.findViewById(R.id.TotalCases)
            newCases = vista.findViewById(R.id.newCasesNumber)
            allDeath = vista.findViewById(R.id.totalDeathNumber)
            recovery = vista.findViewById(R.id.TotalRecoveryNumber)
            recoverPercentaje = vista.findViewById(R.id.TotalRecoveryPercentajeNumber)

            // agregando la variable para poder hacerle click

        }
    }
}