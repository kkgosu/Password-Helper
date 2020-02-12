package com.sber.rupassword.repository

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sber.rupassword.PasswordApp
import com.sber.rupassword.domain.models.Password

object PasswordPref {
    private lateinit var prefs: SharedPreferences

    private fun initializePrefs() {
        if (!PasswordPref::prefs.isInitialized) {
            prefs = PreferenceManager.getDefaultSharedPreferences(
                    PasswordApp.getContext())
        }
    }

    fun getPassword(alias: String): String? {
        initializePrefs()
        if (PasswordPref::prefs.isInitialized) {
            return prefs.getString(alias, null)
        }
        return null
    }

    fun setPassword(alias: String, encryptedValue: String) {
        initializePrefs()
        if (PasswordPref::prefs.isInitialized) {
            prefs.edit().putString(alias, encryptedValue).apply()
        }
    }

    fun deleteAllPasswords() {
        initializePrefs()
        if (PasswordPref::prefs.isInitialized) {
            prefs.edit().clear().apply()
        }
    }

    fun addAndSavePassword(password: Password) {
        val allPasswords = getAllPasswords()
        getAllPasswords()
                .toMutableList().add(password)
        allPasswords.let {
            val list = it.toMutableList()
            list.add(password)
            saveAllPasswords(
                    list.toList())
        }
    }

    private fun saveAllPasswords(passwords: List<Password>) {
        initializePrefs()
        if (PasswordPref::prefs.isInitialized) {
            val jsonPasswords = Gson().toJson(passwords)
            println(jsonPasswords)
            prefs.edit().putString("passwords", jsonPasswords).apply()
        }
    }

    fun getAllPasswords(): List<Password> {
        initializePrefs()
        if (PasswordPref::prefs.isInitialized) {
            val emptyList = Gson().toJson(ArrayList<Password>())
            return Gson().fromJson(
                    prefs.getString("passwords", emptyList),
                    object : TypeToken<ArrayList<Password>>() {}.type)
        }
        return emptyList()
    }
}