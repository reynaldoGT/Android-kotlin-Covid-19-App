package com.example.covid19ciudados

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.covid19ciudados.Fragments.Inicio
import com.example.covid19ciudados.Fragments.Mundial
import com.example.covid19ciudados.Fragments.Nacional
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // para mostra el fragmento de inicio de una
        showselecttFragmet(Inicio())

        toolbar = findViewById(R.id.toolba)
        toolbar?.title="Prevenciones Covid-19 "
        setSupportActionBar(toolbar)




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
                R.id.page_3 -> {
                    showselecttFragmet(Nacional())
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