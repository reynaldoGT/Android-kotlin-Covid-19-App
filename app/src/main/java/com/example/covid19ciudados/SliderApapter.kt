package com.example.covid19ciudados

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.makeramen.roundedimageview.RoundedImageView

class SliderApapter(sliderItems:ArrayList<SliderItem>,viewPager2 : ViewPager2,var listener: ClickListener)
:RecyclerView.Adapter<SliderApapter.SliderAdapterHolder>(){

    var sliderItems: ArrayList<SliderItem>? = null
    var viewPager2 : ViewPager2 ? = null
    init{
        this.sliderItems = sliderItems
        this.viewPager2 = viewPager2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderAdapterHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.slide_item_container, parent, false)
        var viewHolder = SliderAdapterHolder(vista,listener)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return sliderItems?.size!!
    }

    override fun onBindViewHolder(holder: SliderAdapterHolder, position: Int) {
        //holder.foto?.setImageResource(item?.foto!!)
        holder.setImage(sliderItems?.get(position)!!)
    }

    class SliderAdapterHolder( vista: View,listener: ClickListener) :RecyclerView.ViewHolder(vista),View.OnClickListener{
        var vista = vista
        var listener: ClickListener? = null
        var description :TextView?=null
        var imageView: RoundedImageView? = null
        init {
            imageView = vista.findViewById(R.id.imageslide)
            description = vista.findViewById(R.id.textSlide)
            // establecer evento click
            this.listener = listener
            vista.setOnClickListener(this)
        }
        fun setImage(sliderItem:SliderItem){
            imageView?.setImageResource(sliderItem.image)
        }

        override fun onClick(v: View?) {
            this.listener?.onclick(v!!, adapterPosition)
        }


    }

}