package com.sber.rupassword.screens.resetmasterpassword

import android.app.Activity
import com.sber.rupassword.domain.IResetMasterPasswordContract
import com.sber.rupassword.repository.PasswordPref
import com.sber.rupassword.repository.auth.AccountManager

class ResetMasterPasswordPresenter(
        private var view: IResetMasterPasswordContract.View?) : IResetMasterPasswordContract.Presenter {

    private var repository: IResetMasterPasswordContract.Repository = AccountManager(
            (view as ResetMasterPasswordActivity).applicationContext)

    override fun resetAccount(activity: Activity) {
        repository.removeAccount(activity)
        PasswordPref.deleteAllPasswords()
        view?.startCreateMasterPasswordActivity()
    }

    override fun onDestroy() {
        view = null
    }
}