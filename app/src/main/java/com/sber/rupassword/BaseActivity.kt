package com.sber.rupassword

import android.app.Activity
import android.os.Bundle
import com.sber.rupassword.auth.AccountManager

abstract class BaseActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val manager = AccountManager(this)
        
    }
}