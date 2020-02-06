package com.sber.rupassword.domain

import com.sber.rupassword.repository.auth.Account

interface IMasterPasswordContract {
    interface View {
        fun showMessage()
        fun startCreateMasterPasswordActivity()
        fun startMainActivity()
    }

    interface Presenter {
        fun onNextClicked(inputPassword: String)
        fun onForgotPasswordClicked()
        fun onDestroy()
    }

    interface Repository {
        fun getAccount(): Account?
    }
}