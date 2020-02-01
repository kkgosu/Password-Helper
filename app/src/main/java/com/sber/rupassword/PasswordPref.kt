package com.sber.rupassword

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PasswordPref {
    private const val TAG = "Pref"
    private lateinit var prefs: SharedPreferences

    private const val MASTER_PASSWORD = "master-password"

    private fun initializePrefs() {
        if (!PasswordPref::prefs.isInitialized) {
            prefs = PreferenceManager.getDefaultSharedPreferences(PasswordApp.getContext())
        }
    }

    fun saveMasterPassword(masterPassword: String) {
        setString(MASTER_PASSWORD, masterPassword)
    }

    fun getMasterPassword(): String = getString(MASTER_PASSWORD, "")

    private fun getString(pref: String, def: String): String {
        initializePrefs()
        return if (PasswordPref::prefs.isInitialized) {
            prefs.getString(pref, def)!!
        } else ""
    }

    private fun setString(pref: String, str: String): Boolean {
        initializePrefs()
        if (PasswordPref::prefs.isInitialized) {
            prefs.edit()
                    .putString(pref, str)
                    .apply()
            return true
        }
        return false
    }

    fun addAndSavePassword(password: Password) {
        val allPasswords = getAllPasswords()
        allPasswords?.let {
            it.toMutableList().add(password)
            saveAllPasswords(it)
        }
    }

    fun saveAllPasswords(passwords: List<Password>) {
        initializePrefs()
        if (PasswordPref::prefs.isInitialized) {
            val jsonPasswords = Gson().toJson(passwords)
            prefs.edit().putString("passwords", jsonPasswords).apply()
        }
    }

    fun getAllPasswords(): List<Password>? {
        initializePrefs()
        if (PasswordPref::prefs.isInitialized) {
            val listTye = object : TypeToken<ArrayList<Password>>() {}.type
            return Gson().fromJson<ArrayList<Password>>(prefs.getString("passwords", ""), listTye)
        }
        return null
    }
}