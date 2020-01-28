package com.sber.rupassword.auth

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AuthenticatorService : Service() {

    private lateinit var authenticator: Authenticator

    override fun onCreate() {
        super.onCreate()
        authenticator = Authenticator(this)
    }

    override fun onBind(intent: Intent?): IBinder? = authenticator.iBinder
}