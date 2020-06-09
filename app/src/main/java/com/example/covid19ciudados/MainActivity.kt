package com.example.covid19ciudados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.covid19ciudados.Fragments.Inicio
import com.example.covid19ciudados.Fragments.Mundial
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        botonNavigation.setOnNavigationItemSelectedListener {menuItem ->
            when(menuItem.itemId) {
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
    fun showselecttFragmet(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

    }
}