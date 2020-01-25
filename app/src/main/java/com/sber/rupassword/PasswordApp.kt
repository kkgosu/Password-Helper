package com.sber.rupassword

import android.app.Application
import android.content.Context

class PasswordApp : Application() {


    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        private lateinit var context: Context
        fun getContext() = context
    }
}