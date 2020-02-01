package com.sber.rupassword

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sber.rupassword.auth.AccountManager
import kotlinx.android.synthetic.main.activity_reset_master_password.*

class ResetMasterPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_master_password)

        reset_password.setOnClickListener {
            AccountManager(this).removeAccount(this)
            startActivity(Intent(this, CreateMasterPasswordActivity::class.java))
            finishAffinity()
        }

        cancel.setOnClickListener {
            onBackPressed()
        }
    }
}
