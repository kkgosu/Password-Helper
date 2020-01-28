package com.sber.rupassword

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.sber.rupassword.auth.Account
import com.sber.rupassword.auth.AccountManager
import kotlinx.android.synthetic.main.activity_create_master_password.*

class CreateMasterPassword : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_master_password)

        proceed.setOnClickListener {
            if (new_master_password.text.toString() == confirm_new_master_password.text.toString()) {
                AccountManager(this).apply {
                    addAccount(Account("userName", confirm_new_master_password.text.toString()),
                            "token")
                }
                startActivity(Intent(this, MasterPasswordActivity::class.java))
            }
        }
    }
}
