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
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.covid19ciudados.R
import com.example.covid19ciudados.information.AdapterMundiData
import com.example.covid19ciudados.information.GlobalInfomation
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_lista_mundi.*


class Lista_mundi : AppCompatActivity() {

    var toolbar: Toolbar? = null

    var listaCountries: RecyclerView? = null
    var adaptadorMundi: AdapterMundiData? = null
    var layout_Manager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_mundi)

        toolbar = findViewById(R.id.toolbarListaPaises)
        toolbar?.title = "Buscar pais ..."
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progress_circular.visibility = View.VISIBLE
        consultData()
    }


    private fun consultData() {

        val url =
            "https://api.covid19api.com/summary"
        val queue = Volley.newRequestQueue(this)

        val request =
            StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                try {

                    //usando la libreria gson para parsear
                    val gson = Gson()
                    val c19 = gson.fromJson(response, GlobalInfomation::class.java)

                    val sortedList = c19.Countries.sortedByDescending { it.TotalConfirmed }

                    listaCountries?.setHasFixedSize(true)
                    adaptadorMundi = AdapterMundiData(ArrayList(sortedList))
                    layout_Manager = LinearLayoutManager(this)

                    listaCountries = findViewById(R.id.recyclerViewMundi)
                    listaCountries?.layoutManager = layout_Manager

                    listaCountries?.adapter = adaptadorMundi

                    // para detener el circular progress bar

                    //view?.findViewById<ProgressBar>(R.id.progressBar)!!.visibility = View.GONE
                    progress_circular.visibility = View.GONE
                } catch (e: Exception) {
                    Log.d("error en la peticion", e.message.toString())
                }
            }, Response.ErrorListener { error ->
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

                adaptadorMundi?.filter!!.filter(newText)
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