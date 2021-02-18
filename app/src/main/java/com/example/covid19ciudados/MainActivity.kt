package com.example.covid19ciudados

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.covid19ciudados.Fragments.Inicio
import com.example.covid19ciudados.Fragments.Lista_mundi
import com.example.covid19ciudados.Fragments.Mundial
import com.example.covid19ciudados.Fragments.National
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // para mostra el fragmento de inicio de una
        showselectedFragmet(Inicio())

        toolbar = findViewById(R.id.toolba)
        toolbar?.title = "Prevenciones Covid-19 "
        setSupportActionBar(toolbar)


        val botonNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        botonNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.page_1 -> {
                    showselectedFragmet(Inicio())
                    true
                }
                R.id.page_2 -> {
                    showselectedFragmet(Mundial())
                    true
                }
                R.id.page_3 -> {
                    showselectedFragmet(National())
                    true
                }
                else -> false
            }
        }
    }

    private fun showselectedFragmet(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_mundi -> {
                val intent = Intent(this, Lista_mundi::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}