package com.example.covid19ciudados.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.covid19ciudados.ClickListener
import com.example.covid19ciudados.R
import com.example.covid19ciudados.SliderApapter
import com.example.covid19ciudados.SliderItem
import com.google.firebase.iid.FirebaseInstanceId
import java.lang.Math.abs


class Inicio : Fragment(), View.OnClickListener {

    private var viewPager2: ViewPager2? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        notification()

        val view = inflater.inflate(R.layout.fragment_inicio, container, false)
        //Funciones para que los botones funcionen en un fragment
        buttons_functions(view)

        viewPager2 = view.findViewById(R.id.viewPagerImageSlider)

        val sliderItem: ArrayList<SliderItem> = ArrayList()

        sliderItem.add(SliderItem(R.drawable.stayhome, getString(R.string.stay_home)))
        sliderItem.add(
            SliderItem(
                R.drawable.cough,
                getString(R.string.cover_yourself_when_coughing)
            )
        )
        sliderItem.add(
            SliderItem(
                R.drawable.antibacterialgel,
                getString(R.string.use_antibacterial_gel)
            )
        )
        sliderItem.add(SliderItem(R.drawable.distance, getString(R.string.take_social_distance)))
        sliderItem.add(SliderItem(R.drawable.handshake, getString(R.string.don_t_wave)))
        try {
            sliderItem.add(
                SliderItem(
                    R.drawable.greeting,
                    getString(R.string.try_to_wave_your_elbows)
                )
            )
        } catch (e: Exception) {
        }
        sliderItem.add(SliderItem(R.drawable.facemask, getString(R.string.wear_a_or_mask)))
        sliderItem.add(
            SliderItem(
                R.drawable.washinghands,
                getString(R.string.wash_your_hands_frequently)
            )
        )
        sliderItem.add(
            SliderItem(
                R.drawable.hearse,
                getString(R.string.If_you_feel_bad_ask_for_help)
            )
        )
        sliderItem.add(
            SliderItem(
                R.drawable.mask,
                getString(R.string.always_go_out_with_a_chinstrap_or_mask)
            )
        )

        viewPager2?.adapter = SliderApapter(sliderItem, viewPager2!!, object : ClickListener {
            override fun onclick(vista: View, index: Int) {
                Toast.makeText(
                    activity?.applicationContext,
                    sliderItem[index].desc,
                    Toast.LENGTH_LONG
                )
                    .show()
            }

        })

        viewPager2?.clipToPadding = false
        viewPager2?.clipChildren = false
        viewPager2?.offscreenPageLimit = 3
        viewPager2?.getChildAt(0)?.overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer
        { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        })

        viewPager2?.setPageTransformer(compositePageTransformer)
        return view
    }

    fun buttons_functions(view: View) {
        val btnCall =
            view.findViewById(R.id.btnCall) as Button
        btnCall.setOnClickListener(this)
        val btnMessage =
            view.findViewById(R.id.btnMessage) as Button
        btnMessage.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnCall -> {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:800101104")
                }
                startActivity(intent)
            }
            R.id.btnMessage -> {

                val number = "2443885";  // The number on which you want to send SMS

                val uri = Uri.parse("smsto:$number")
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                intent.putExtra("sms_body", "Necesito una ambulancia em ")
                startActivity(intent)
            }
        }
    }

    private fun notification() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            it.result?.token.let {
                println("This is the id phone ${it}")
            }
        }
    }

}