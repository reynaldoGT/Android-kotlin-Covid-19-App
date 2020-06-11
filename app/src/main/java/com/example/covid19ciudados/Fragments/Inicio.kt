package com.example.covid19ciudados.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid19ciudados.ClickListener
import com.example.covid19ciudados.R
import com.example.covid19ciudados.SliderApapter
import com.example.covid19ciudados.SliderItem
import com.example.covid19ciudados.information.GlobalInfo
import com.google.gson.Gson
import java.lang.Math.abs

class Inicio : Fragment() {

    private var viewPager2: ViewPager2? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_inicio, container, false)
        viewPager2 = view.findViewById(R.id.viewPagerImageSlider)

        var sliderItem: ArrayList<SliderItem> = ArrayList()

        sliderItem.add(SliderItem(R.drawable.stayhome,"Quédate en casa"))
        sliderItem.add(SliderItem(R.drawable.cough,"Tapate al toser"))
        sliderItem.add(SliderItem(R.drawable.antibacterialgel,"Usa gel antibacterial"))
        sliderItem.add(SliderItem(R.drawable.distance,"Toma distancia social"))
        sliderItem.add(SliderItem(R.drawable.handshake,"No saludes con la mano"))
        sliderItem.add(SliderItem(R.drawable.greeting,"Trata de saluda con los codos"))
        sliderItem.add(SliderItem(R.drawable.facemask,"Usa barbijo o mascarilla"))
        sliderItem.add(SliderItem(R.drawable.washinghands,"Lavate las manos frecuentemente"))
        sliderItem.add(SliderItem(R.drawable.hearse,"Si te sientes mal pide ayuda"))
        sliderItem.add(SliderItem(R.drawable.mask,"Sal siempre con barbijo o mascarilla"))

        viewPager2?.adapter = SliderApapter(sliderItem, viewPager2!!,object :ClickListener{
            override fun onclick(vista: View, index: Int) {
                Toast.makeText(activity?.applicationContext, sliderItem[index].desc, Toast.LENGTH_LONG)
                    .show()
            }

        })

        viewPager2?.clipToPadding = false
        viewPager2?.clipChildren = false
        viewPager2?.offscreenPageLimit = 3
        viewPager2?.getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS

        var compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer
        { page, position ->
            var r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        })

        viewPager2?.setPageTransformer(compositePageTransformer)
        return view
    }


}