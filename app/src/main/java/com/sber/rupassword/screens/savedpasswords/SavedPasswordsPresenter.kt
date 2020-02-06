package com.sber.rupassword.screens.savedpasswords

import com.sber.rupassword.domain.Encrypt
import com.sber.rupassword.domain.ISavedPasswordsContract
import com.sber.rupassword.domain.models.Password
import com.sber.rupassword.repository.PasswordPref

class SavedPasswordsPresenter(
        private var view: ISavedPasswordsContract.View?) : ISavedPasswordsContract.Presenter {

    private val encrypt = Encrypt()

    override fun savePassword(password: Password) {
        val alias = password.site + "," + password.login
        PasswordPref.setPassword(alias, encrypt.encryptText(alias, password.password))
        PasswordPref.addAndSavePassword(password.copy(password = ""))
        view?.submitList(PasswordPref.getAllPasswords())
    }

    override fun onDestroy() {
        view = null
    }


}