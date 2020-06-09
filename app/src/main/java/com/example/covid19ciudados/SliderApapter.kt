package com.example.covid19ciudados

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.makeramen.roundedimageview.RoundedImageView

class SliderApapter(sliderItems:ArrayList<SliderItem>,viewPager2 : ViewPager2)
:RecyclerView.Adapter<SliderApapter.SliderAdapterHolder>(){

    var sliderItems: ArrayList<SliderItem>? = null
    var viewPager2 : ViewPager2 ? = null
    init{
        this.sliderItems = sliderItems
        this.viewPager2 = viewPager2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderAdapterHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.slide_item_contaiener, parent, false)
        var viewHolder = SliderAdapterHolder(vista)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return sliderItems?.size!!
    }

    override fun onBindViewHolder(holder: SliderAdapterHolder, position: Int) {
        //holder.foto?.setImageResource(item?.foto!!)
        holder.setImage(sliderItems?.get(position)!!)
    }

    class SliderAdapterHolder( vista: View) :RecyclerView.ViewHolder(vista){
        var vista = vista
        var imageView: RoundedImageView? = null
        init {
            imageView = vista.findViewById(R.id.imageslide)
        }
        fun setImage(sliderItem:SliderItem){
            imageView?.setImageResource(sliderItem.image)
        }

    }
}