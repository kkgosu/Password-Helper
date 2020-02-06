package com.sber.rupassword.domain

import com.sber.rupassword.repository.auth.Account

interface ICreateMasterPasswordContract {
    interface View {
        fun startMasterPasswordActivity()
        fun showMessage()
    }

    interface Presenter {
        fun onNextClicked(password1: String?, password2: String?)
        fun onDestroy()
    }

    interface Repository {
        fun addAccount(account: Account, token: String)
    }
}