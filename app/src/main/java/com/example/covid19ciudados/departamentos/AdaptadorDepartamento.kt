package com.example.covid19ciudados.departamentos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.example.covid19ciudados.R
import com.example.covid19ciudados.information.SharedCode.Companion.dataProcessed

class AdaptadorDepartamento(var context: Context, items: ArrayList<CardDepartamento>) :
    BaseAdapter() {

    var items: ArrayList<CardDepartamento>? = null

    init {
        this.items = items
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder?
        var vista: View? = convertView

        if (vista == null) {
            vista = LayoutInflater.from(context).inflate(R.layout.departamentos_recycler_view, null)
            holder = ViewHolder(vista)
            vista.tag = holder
        } else {
            holder = vista.tag as? ViewHolder
        }
        val item = getItem(position) as CardDepartamento

        holder?.nombre?.text = item.nombre_departamento
        holder?.cantidad_total?.text = dataProcessed(item.cantidad_total)
        holder?.cantidad_dia_departamento?.text = dataProcessed(item.cantidad_por_dia)


        return vista!!
    }

    override fun getItem(position: Int): Any {
        return items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items?.count()!!
    }


    private class ViewHolder(vista: View) {
        var nombre: TextView? = null;
        var cantidad_total: TextView? = null
        var cantidad_dia_departamento: TextView? = null

        init {
            nombre = vista.findViewById(R.id.nombreDepartamento)
            cantidad_total = vista.findViewById(R.id.cantidadPorDepartamento)
            cantidad_dia_departamento = vista.findViewById(R.id.ultimosCasosDepartamento)
        }


    }
}