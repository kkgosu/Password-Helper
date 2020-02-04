package com.sber.rupassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_password_dialog.*

class PasswordDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_password_dialog)

        val decrypt = Decrypt()

        val site = intent.getStringExtra("password_site")
        val login = intent.getStringExtra("password_login")
        val alias = "$site,$login"
        site_input.setText(site)
        login_input.setText(login)
        PasswordPref.getPassword(alias)?.let { pass ->
            password_input.setText(decrypt.decryptData(alias, pass))
        }

        site_input.isEnabled = false
        login_input.isEnabled = false
        password_input.isEnabled = false
    }
}
