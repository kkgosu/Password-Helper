package com.sber.rupassword.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sber.rupassword.R
import com.sber.rupassword.domain.Encrypt
import com.sber.rupassword.domain.models.Password
import com.sber.rupassword.repository.PasswordPref
import com.sber.rupassword.screens.common.AddPasswordDialog
import com.sber.rupassword.screens.common.PasswordsAdapter
import kotlinx.android.synthetic.main.activity_saved_passwords.*

class SavedPasswordsActivity : AppCompatActivity(), AddPasswordDialog.Listener, PasswordsAdapter.Interaction {

    private lateinit var passwordsAdapter: PasswordsAdapter
    private lateinit var encrypt: Encrypt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_passwords)

        encrypt = Encrypt()
        passwordsAdapter = PasswordsAdapter(
                this)
        saved_passwords.adapter = passwordsAdapter
        passwordsAdapter.submitList(
                PasswordPref.getAllPasswords())

        fab.setOnClickListener {
            AddPasswordDialog()
                    .show(supportFragmentManager, "dialog")
        }
    }

    override fun onClick(password: Password) {
        val alias = password.site + "," + password.login
        PasswordPref.setPassword(alias,
                encrypt.encryptText(alias, password.password))
        PasswordPref.addAndSavePassword(
                password.copy(password = ""))
        passwordsAdapter.submitList(
                PasswordPref.getAllPasswords())
    }

    override fun onItemSelected(position: Int, item: Password) {
        val intent = Intent(this, PasswordDetailsActivity::class.java)
        intent.putExtra("password_site", item.site)
        intent.putExtra("password_login", item.login)
        startActivity(intent)
    }

    companion object {
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
    }
}