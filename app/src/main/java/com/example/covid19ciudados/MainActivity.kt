package com.example.covid19ciudados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid19ciudados.Fragments.Inicio
import com.example.covid19ciudados.Fragments.Mundial
import com.example.covid19ciudados.information.ConsultarDatos
import com.example.covid19ciudados.information.GlobalInfo
import com.example.covid19ciudados.information.GlobalInfomation
import com.example.covid19ciudados.information.VerRed.Companion.hayRed
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_mundial.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Obtener los datos

        /*if(hayRed(this)){
            consultarDatos()
        }else{
            Log.d("Error","No hay internet")
        }*/

        val botonNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        botonNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.page_1 -> {
                    showselecttFragmet(Inicio())
                    true
                }
                R.id.page_2 -> {
                    showselecttFragmet(Mundial())
                    true
                }
                else -> false
            }
        }
    }

    fun showselecttFragmet(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

    }


}