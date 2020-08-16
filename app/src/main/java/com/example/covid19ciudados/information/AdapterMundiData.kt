package com.example.covid19ciudados.information

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19ciudados.R
import com.example.covid19ciudados.information.SharedCode.Companion.datoProcesado
import java.util.*
import kotlin.collections.ArrayList

class AdapterMundiData(items: ArrayList<Country>) :
    RecyclerView.Adapter<AdapterMundiData.ViewHolder>(), Filterable {

    //? Properties
    var items: ArrayList<Country>? = null
    var viewHolder: ViewHolder? = null
    var copiaItems: ArrayList<Country>? = null

    // constructor
    init {
        this.items = items
        this.copiaItems = items
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
        holder.recoverPercentaje?.text =
            ((item.TotalRecovered * 100) / item.TotalConfirmed).toString() + " %"

    }


    fun filtrar(str: String) {

        items?.clear()
        if (str.isEmpty()) {
            items = copiaItems
            notifyDataSetChanged()
            return
        }
        var busqueda = str

        busqueda = busqueda.toLowerCase()
        for (item in copiaItems!!) {
            val nombre = item.Country.toLowerCase()
            if (nombre.contains(busqueda)) {
                items?.add(item)
            }
        }
        notifyDataSetChanged()
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Log.d("texto para buscar", charSearch)
                if (charSearch.isEmpty()) {
                    copiaItems = items
                } else {
                    val resultList = ArrayList<Country>()
                    for (row in items!!) {
                        if (row.Country.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    copiaItems = resultList
                }
                val filterResult = FilterResults()
                filterResult.values = copiaItems
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                copiaItems = results?.values as ArrayList<Country>
                notifyDataSetChanged()
            }

        }
    }

}