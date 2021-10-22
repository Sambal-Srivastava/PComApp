package com.apps.pcomapp.util

import android.content.Context
import android.content.SharedPreferences

object MyPreferences {

    private val sharedPrefFile = "sharedpreference"

    fun setValue(context: Context, key: String, value: String?) {

        //   val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }


    fun getValue(context: Context, key: String): String {
        //    val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")!!

    }
}