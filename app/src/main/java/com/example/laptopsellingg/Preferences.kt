package com.example.laptopsellingg

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
    }

    fun saveSession(id: Int) {
        val edit  = preferences.edit()
        edit.putInt("id", id)
        edit.apply()
    }

    fun getSession(): Int {
        return preferences.getInt("id", -0)
    }

    fun deleteSession() {
        preferences.edit().clear().apply()
    }
}