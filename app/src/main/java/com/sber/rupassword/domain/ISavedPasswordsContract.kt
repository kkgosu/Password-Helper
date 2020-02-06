package com.sber.rupassword.domain

import com.sber.rupassword.domain.models.Password

interface ISavedPasswordsContract {
    interface View {
        fun submitList(passwords: List<Password>)
    }

    interface Presenter {
        fun savePassword(password: Password)
        fun onDestroy()
    }
}