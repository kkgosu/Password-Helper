package com.sber.rupassword.screens.passworddetails

import com.sber.rupassword.domain.Decrypt
import com.sber.rupassword.domain.IPasswordDetailsContract

class PasswordDetailsPresenter(private var view: IPasswordDetailsContract.View?) :
        IPasswordDetailsContract.Presenter {

    private val decrypt = Decrypt()

    override fun decrypt(alias: String, password: String): String =
        decrypt.decryptData(alias, password)
    

    override fun onDestroy() {
        view = null
    }
}