package com.sber.rupassword

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.password_details.*

class PasswordDetails : AppCompatActivity() {

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
            copyToClipboard(login_input)
        }

        copy_password.setOnClickListener {
            copyToClipboard(password_input)
        }

        site_input.isEnabled = false
        site_input.isFocusable = false
        login_input.isEnabled = false
        login_input.isFocusable = false
        password_input.isEnabled = false
        password_input.isFocusable = false
    }

    private fun copyToClipboard(password: TextView) {
        val manager: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        manager.let {
            it.setPrimaryClip(
                    ClipData.newPlainText(getString(R.string.password), password.text.toString()))
            Toast.makeText(this, R.string.toast_text_copied, Toast.LENGTH_SHORT).show()
        }
    }
}
