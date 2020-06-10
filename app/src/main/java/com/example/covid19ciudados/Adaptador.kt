package com.example.covid19ciudados

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class Adaptador(var context: Context, items: ArrayList<Card>) : BaseAdapter() {
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
                    vista?.setBackgroundResource(R.drawable.border_no_color)
            }
            1->{
                vista?.setBackgroundColor(Color.parseColor("#ff5959"))
            }
            2->{
                vista?.setBackgroundColor(Color.parseColor("#4cd97b"))
            }
            3->{
                vista?.setBackgroundColor(Color.parseColor("#4cb5ff"))
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