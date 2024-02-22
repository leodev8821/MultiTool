package com.example.multitool.zodiapp.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context) {

    companion object{
        val FAVORITE_ZODIAC = "FAVORITE_ZODIAC"
    }

    private var sharedPref : SharedPreferences? = null

    init{
        sharedPref = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)
    }

    // Propiedades que se desean guardar en la Sesion
    fun setFavoriteZodiac (id : String){
        val editor = sharedPref?.edit()
        if(editor != null){
            editor.putString(FAVORITE_ZODIAC, id)
            editor.apply()
        }
    }

    fun getFavoriteZodiac () : String? {
        return sharedPref?.getString(FAVORITE_ZODIAC, null)
    }
}