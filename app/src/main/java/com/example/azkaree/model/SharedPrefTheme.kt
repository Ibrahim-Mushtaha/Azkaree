package com.example.azkaree.model

import android.content.Context
import android.content.SharedPreferences


class SharedPrefTheme (context: Context){
    var mySharedPref: SharedPreferences? = null


   init{
        mySharedPref = context.getSharedPreferences("NightModeTheme", Context.MODE_PRIVATE)
    }

    // this method will save the nightMode State : True or False
    fun setNightModeState(state: Boolean?) {
        val editor = mySharedPref!!.edit()
        editor.putBoolean("NightMode", state!!)
        editor.commit()
    }

    // this method will load the Night Mode State
    fun loadNightModeState(): Boolean? {
        return mySharedPref!!.getBoolean("NightMode", false)
    }


}