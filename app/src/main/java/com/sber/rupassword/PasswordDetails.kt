package com.sber.rupassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_password_dialog.*

class PasswordDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_password_dialog)

        val password = intent.getParcelableExtra<Password>("password")
        password?.let {
            site_input.setText(it.site)
            login_input.setText(it.login)
            password_input.setText(it.password)
        }

        site_input.isEnabled = false
        login_input.isEnabled = false
        password_input.isEnabled = false
    }
}
