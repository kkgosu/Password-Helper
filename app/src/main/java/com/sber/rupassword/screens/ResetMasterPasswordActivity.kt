package com.sber.rupassword.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sber.rupassword.R
import com.sber.rupassword.repository.PasswordPref
import com.sber.rupassword.repository.auth.AccountManager
import com.sber.rupassword.screens.createmasterpassword.CreateMasterPasswordActivity
import kotlinx.android.synthetic.main.activity_reset_master_password.*

class ResetMasterPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                R.layout.activity_reset_master_password)

        reset_password.setOnClickListener {
            AccountManager(this).removeAccount(this)
            PasswordPref.deleteAllPasswords()
            startActivity(Intent(this, CreateMasterPasswordActivity::class.java))
            finishAffinity()
        }

        cancel.setOnClickListener {
            onBackPressed()
        }
    }
}
