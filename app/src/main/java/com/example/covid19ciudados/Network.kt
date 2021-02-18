package com.example.covid19ciudados

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class Network(var activity: AppCompatActivity) {
    fun hayRed(): Boolean {
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }

    fun httpGetRequest(context: Context, url: String, httpResponse: HttpResponse) {
        Log.d("Consulta", "haciendo la consulta2")

        if (hayRed()) {
            val queue = Volley.newRequestQueue(context)

            val request = StringRequest(Request.Method.GET, url, { response ->
                httpResponse.httpResponseSuccess(response)

            }, { error ->
                Log.d("HTTP REQUEST", error.message.toString())

            })
            queue.add(request)
        } else {
            Log.d("HTTP REQUEST", "Hubo un error en la consulta")
        }
    }
}
