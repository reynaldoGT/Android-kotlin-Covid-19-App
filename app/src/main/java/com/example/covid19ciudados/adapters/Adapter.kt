package com.example.covid19ciudados.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.covid19ciudados.models.Card
import com.example.covid19ciudados.R

class Adapter(var context: Context, items: ArrayList<Card>) : BaseAdapter() {
    var items: ArrayList<Card>? = null

    init {
        this.items = items
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var vista = convertView
        var holder: ViewHolder? = null
        if (vista == null) {
            vista = LayoutInflater.from(context).inflate(R.layout.griditem, null)
            holder = ViewHolder(vista)
            vista.tag = holder
        } else {
            holder = vista.tag as? ViewHolder
        }
        var item = items?.get(position) as? Card
        holder?.title?.text = item?.title
        holder?.cantidad?.text = item?.cantidad


        /*if (position % 2 == 1) {
            vista?.setBackgroundColor(Color.parseColor("#19205E"))
        }*/
        when(position){
            0->{
                vista?.setBackgroundColor(Color.parseColor("#ffb259"))
                    vista?.setBackgroundResource(R.drawable.card_cyan)
            }
            1->{
                vista?.setBackgroundResource(R.drawable.card_green)

            }
            2->{
                vista?.setBackgroundResource(R.drawable.card_red)

            }
            3->{
                vista?.setBackgroundResource(R.drawable.card_orange)
            }
        }

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
        var title: TextView? = null
        var cantidad: TextView? = null

        init {
            title = vista.findViewById(R.id.tvTitleCard)
            cantidad = vista.findViewById(R.id.tvTotalCard)
        }
    }
}