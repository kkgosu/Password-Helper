package com.sber.rupassword

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_saved_passwords.*

class SavedPasswordsActivity : Activity() {

    private lateinit var passwordsAdapter: PasswordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_passwords)

        passwordsAdapter = PasswordsAdapter()
        saved_passwords.adapter = passwordsAdapter
        passwordsAdapter.submitList(PasswordPref.getAllPasswords())

    }
}
