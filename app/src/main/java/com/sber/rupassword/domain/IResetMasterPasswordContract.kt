package com.sber.rupassword.domain

import android.app.Activity

interface IResetMasterPasswordContract {

    interface Repository {
        fun removeAccount(activity: Activity)
    }

    interface Presenter {
        fun onDestroy()
        fun resetAccount(activity: Activity)
    }

    interface View {
        fun startCreateMasterPasswordActivity()
    }
}