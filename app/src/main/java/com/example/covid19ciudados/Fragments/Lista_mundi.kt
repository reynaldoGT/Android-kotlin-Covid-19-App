package com.example.covid19ciudados.Fragments

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid19ciudados.R
import com.example.covid19ciudados.information.AdapterMundiData
import com.example.covid19ciudados.information.GlobalInformation
import com.google.gson.Gson
import com.example.covid19ciudados.databinding.ActivityListaMundiBinding



class Lista_mundi : AppCompatActivity() {


    var listCountries: RecyclerView? = null
    var adapterMundi: AdapterMundiData? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    private lateinit var binding: ActivityListaMundiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaMundiBinding.inflate(layoutInflater)
        setContentView(binding.root)

         binding.toolbarListarPaises.title = "Buscar pais ..."
        setSupportActionBar( binding.toolbarListarPaises)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.progressCircular.visibility = View.VISIBLE
        consultData()
    }


    private fun consultData() {

        val url =
            "https://api.covid19api.com/summary"
        val queue = Volley.newRequestQueue(this)

        val request =
            StringRequest(Request.Method.GET, url, { response ->
                try {

                    //usando la libreria gson para parsear
                    val gson = Gson()
                    val c19 = gson.fromJson(response, GlobalInformation::class.java)

                    val sortedList = c19.Countries.sortedByDescending { it.TotalConfirmed }

                    listCountries?.setHasFixedSize(true)
                    adapterMundi = AdapterMundiData(ArrayList(sortedList))
                    layoutManager = LinearLayoutManager(this)

                    listCountries = findViewById(R.id.recyclerViewMundi)
                    listCountries?.layoutManager = layoutManager

                    listCountries?.adapter = adapterMundi

                    // para detener el circular progress bar

                    //view?.findViewById<ProgressBar>(R.id.progressBar)!!.visibility = View.GONE
                    binding.progressCircular.visibility = View.GONE
                } catch (e: Exception) {
                    Log.d("error en la peticion", e.message.toString())
                }
            }, { error ->
                Log.d("Error en el listener", error.message.toString())

            })
        queue.add(request)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mundial, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val itemBusqueda = menu?.findItem(R.id.search_view)
        val searchView = itemBusqueda?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Buscar pais por nombre ..."

        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            //Preparar datos
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {

                adapterMundi?.filter!!.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                //Filtrar
                return false
            }
        })


        return super.onCreateOptionsMenu(menu)
    }


}