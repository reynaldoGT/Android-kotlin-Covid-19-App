package com.example.covid19ciudados.information

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

class VerRed {

    companion object{
        fun hayRed(activity: AppCompatActivity):Boolean{
            val conectivytiManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netWorkInfo = conectivytiManager.activeNetworkInfo
            return netWorkInfo!=null && netWorkInfo.isConnected

        }
    }
}