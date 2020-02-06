package com.sber.rupassword.screens.createmasterpassword

import com.sber.rupassword.domain.ICreateMasterPasswordContract
import com.sber.rupassword.repository.auth.Account
import com.sber.rupassword.repository.auth.AccountManager

class CreateMasterPasswordPresenter(
        private var view: ICreateMasterPasswordContract.View?) : ICreateMasterPasswordContract.Presenter {

    private var repository: ICreateMasterPasswordContract.Repository = AccountManager(
            (view as CreateMasterPasswordActivity).applicationContext)

    override fun onNextClicked(password1: String?, password2: String?) {
        if (password1 != null && password1 == password2) {
            repository.addAccount(Account("userName", password2), "token")
            view?.startMasterPasswordActivity()
        } else {
            view?.showMessage()
        }
    }

    override fun onBackClicked() {
        view?.onBackClicked()
    }

    override fun onDestroy() {
        view = null
    }
}