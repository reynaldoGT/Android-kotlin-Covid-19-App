package com.example.covid19ciudados.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.covid19ciudados.R
import com.example.covid19ciudados.SliderApapter
import com.example.covid19ciudados.SliderItem
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
        sliderItem.add(SliderItem(R.drawable.img1))
        sliderItem.add(SliderItem(R.drawable.img2))
        sliderItem.add(SliderItem(R.drawable.img3))
        sliderItem.add(SliderItem(R.drawable.img4))

        viewPager2?.adapter = SliderApapter(sliderItem,viewPager2!!)

        viewPager2?.clipToPadding = false
        viewPager2?.clipChildren = false
        viewPager2?.offscreenPageLimit = 3
        viewPager2?.getChildAt(0)?.overScrollMode =RecyclerView.OVER_SCROLL_ALWAYS

        var compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer
        { page, position ->
            var r = 1 - abs(position)
            page.scaleY = 0.85f +r * 0.15f
        })

        viewPager2?.setPageTransformer(compositePageTransformer)
        return view
    }


}