package com.sber.rupassword

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_saved_passwords.*

class SavedPasswordsActivity : AppCompatActivity(), AddPasswordDialog.Listener, PasswordsAdapter.Interaction {

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
        passwordsAdapter.submitList(PasswordPref.getAllPasswords())
    }

    override fun onItemSelected(position: Int, item: Password) {
        val intent = Intent(this, PasswordDetails::class.java)
        intent.putExtra("password", item)
        startActivity(intent)
    }
}
