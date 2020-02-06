package com.sber.rupassword.screens.main

import com.sber.rupassword.domain.IMainContract
import com.sber.rupassword.repository.PasswordHelper

class MainPresenter(private var view: IMainContract.View?) : IMainContract.Presenter {

    private lateinit var helper: PasswordHelper

    override fun initPasswordHelper(ru: Array<String>, en: Array<String>) {
        helper = PasswordHelper(ru, en)
    }

    override fun onDestroy() {
        view = null
    }

    override fun convert(text: CharSequence?): String = helper.convert(text)

    override fun calculateStrength(password: String): Int = helper.calculateStrength(password)

    override fun generatePassword(count: Int, caps: Boolean, symbols: Boolean,
            numbers: Boolean): String = helper.generatePassword(count, caps, symbols, numbers)
}