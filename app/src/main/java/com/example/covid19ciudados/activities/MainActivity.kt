package com.example.covid19ciudados.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.covid19ciudados.fragments.HomeFragment
import com.example.covid19ciudados.fragments.MundialFragment
import com.example.covid19ciudados.fragments.NationalFragment
import com.example.covid19ciudados.R
import com.example.covid19ciudados.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // para mostra el fragmento de inicio de una
        showSelectedFragment(HomeFragment())

        binding.toolbar.title = getString(R.string.app_name)
        setSupportActionBar(binding.toolbar)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.page_1 -> {
                    showSelectedFragment(HomeFragment())
                    true
                }
                R.id.page_2 -> {
                    showSelectedFragment(MundialFragment())
                    true
                }
                R.id.page_3 -> {
                    showSelectedFragment(NationalFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun showSelectedFragment(fragment: Fragment) {
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
                val intent = Intent(this, ListaMundiActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}