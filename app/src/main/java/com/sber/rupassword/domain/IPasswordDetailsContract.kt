package com.sber.rupassword.domain

interface IPasswordDetailsContract {
    interface Presenter {
        fun onDestroy()
        fun decrypt(alias: String, password: String): String
    }

    interface View
}