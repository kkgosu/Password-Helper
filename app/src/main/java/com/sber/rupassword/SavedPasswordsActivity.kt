package com.sber.rupassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_saved_passwords.*

class SavedPasswordsActivity : AppCompatActivity(), AddPasswordDialog.Listener {

    private lateinit var passwordsAdapter: PasswordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_passwords)

        passwordsAdapter = PasswordsAdapter()
        saved_passwords.adapter = passwordsAdapter
        passwordsAdapter.submitList(PasswordPref.getAllPasswords())

        fab.setOnClickListener {
            AddPasswordDialog().show(supportFragmentManager, "dialog")
        }
    }

    override fun onClick(password: Password) {
        PasswordPref.addAndSavePassword(password)
    }
}
