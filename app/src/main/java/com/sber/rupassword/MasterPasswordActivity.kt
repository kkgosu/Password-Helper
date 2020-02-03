package com.sber.rupassword

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sber.rupassword.auth.AccountManager
import kotlinx.android.synthetic.main.activity_master_password.*

class MasterPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_password)

        val manager = AccountManager(this)
        if (manager.current == null) {
            startActivity(Intent(this, CreateMasterPasswordActivity::class.java))
        }

        unlock.setOnClickListener {
            manager.apply {
                if (this.current?.password == master_password_input.text.toString()) {
                    startActivity(Intent(this@MasterPasswordActivity, MainActivity::class.java))
                    finishAffinity()
                } else {
                    Toast.makeText(this@MasterPasswordActivity, getString(
                            R.string.wrong_master_password),
                            Toast.LENGTH_SHORT).show()
                }
            }
        }

        forgot_password.setOnClickListener {
            startActivity(Intent(this, ResetMasterPasswordActivity::class.java))
        }
    }
}
