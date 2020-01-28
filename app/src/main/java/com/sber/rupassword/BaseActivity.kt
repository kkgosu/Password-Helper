package com.sber.rupassword

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    fun goToMasterPassword() {
        if (PasswordPref.getMasterPassword() == "") {
            startActivity(Intent(this, CreateMasterPassword::class.java))
        } else {
            startActivity(Intent(this, MasterPasswordActivity::class.java))
        }
        finish()
    }
}