package com.sber.rupassword.screens.masterpassword

import com.sber.rupassword.domain.IMasterPasswordContract
import com.sber.rupassword.repository.auth.AccountManager

class MasterPasswordPresenter(
        private var view: IMasterPasswordContract.View?) : IMasterPasswordContract.Presenter {

    private var repository: IMasterPasswordContract.Repository = AccountManager(
            (view as MasterPasswordActivity).applicationContext)

    init {
        if (repository.getAccount() == null) {
            view?.startCreateMasterPasswordActivity()
        }
    }


    override fun onNextClicked(inputPassword: String) {
        repository.getAccount()?.let {
            if (it.password == inputPassword) {
                view?.startMainActivity()
            } else {
                view?.showMessage()
            }
        }
    }

    override fun onForgotPasswordClicked() {
        view?.startForgotPasswordActivity()
    }

    override fun onDestroy() {
        view = null
    }
}