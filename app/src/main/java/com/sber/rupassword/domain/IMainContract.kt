package com.sber.rupassword.domain

interface IMainContract {
    interface View 

    interface Presenter {
        fun onDestroy()
        fun initPasswordHelper(ru: Array<String>, en: Array<String>)
        fun convert(text: CharSequence?): String
        fun calculateStrength(password: String): Int
        fun generatePassword(count: Int, caps: Boolean, symbols: Boolean, numbers: Boolean): String
    }
}