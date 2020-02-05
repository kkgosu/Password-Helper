package com.sber.rupassword

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sber.rupassword.auth.Account
import com.sber.rupassword.auth.AccountManager
import kotlinx.android.synthetic.main.activity_create_master_password.*

class CreateMasterPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_master_password)

        new_master_password.setError()
        confirm_new_master_password.setError()

        proceed.setOnClickListener {
            val password = new_master_password.text?.toStringOrNull()
            val passwordConfirmed = confirm_new_master_password.text?.toStringOrNull()

            if (password != null && password == passwordConfirmed) {
                AccountManager(this).apply {
                    addAccount(Account("userName", passwordConfirmed), "token")
                }
                startActivity(Intent(this, MasterPasswordActivity::class.java))
            } else {
                Toast.makeText(this, getString(
                        R.string.passwords_not_match), Toast.LENGTH_SHORT).show()
            }
        }

        cancel.setOnClickListener {
            onBackPressed()
        }
    }
}
