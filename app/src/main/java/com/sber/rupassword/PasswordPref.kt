package com.sber.rupassword

import android.content.SharedPreferences
import androidx.preference.PreferenceManager

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
}