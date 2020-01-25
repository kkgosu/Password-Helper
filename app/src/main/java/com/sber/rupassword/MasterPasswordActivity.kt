package com.sber.rupassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MasterPasswordActivity : AppCompatActivity() {

    private lateinit var passwordHelper: PasswordHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_password)

        passwordHelper = PasswordHelper()
        if (!passwordHelper.isMasterPasswordExists) {
            //TODO("show fragment with create new master password")
        }
        //TODO("show fragment with master password input, then check it and proceed to main activity")
        
    }
}
