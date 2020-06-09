package com.example.covid19ciudados.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.covid19ciudados.R
import com.example.covid19ciudados.SliderApapter
import com.example.covid19ciudados.SliderItem

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
        return view
    }


}