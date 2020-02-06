package com.sber.rupassword.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sber.rupassword.R
import com.sber.rupassword.domain.Decrypt
import com.sber.rupassword.domain.PasswordPref
import com.sber.rupassword.utils.copyToClipboard
import kotlinx.android.synthetic.main.password_details.*

class PasswordDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_details)

        val decrypt = Decrypt()

        val site = intent.getStringExtra("password_site")
        val login = intent.getStringExtra("password_login")
        val alias = "$site,$login"
        site_input.setText(site)
        login_input.setText(login)

        PasswordPref.getPassword(alias)?.let { pass ->
            password_input.setText(decrypt.decryptData(alias, pass))
        }

        copy_login.setOnClickListener {
            copyToClipboard(this, login_input)
        }

        copy_password.setOnClickListener {
            copyToClipboard(this, password_input)
        }

        site_input.isEnabled = false
        site_input.isFocusable = false
        login_input.isEnabled = false
        login_input.isFocusable = false
        password_input.isEnabled = false
        password_input.isFocusable = false
    }
}
