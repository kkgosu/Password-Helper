package com.sber.rupassword.repository.auth

import android.app.Activity
import android.content.Context
import android.os.*
import androidx.annotation.RequiresApi
import com.sber.rupassword.domain.ICreateMasterPasswordContract
import com.sber.rupassword.domain.IMasterPasswordContract
import com.sber.rupassword.domain.IResetMasterPasswordContract


private const val TOKEN_KEY = "token"

class AccountManager(
        context: Context) :
        IMasterPasswordContract.Repository,
        ICreateMasterPasswordContract.Repository,
        IResetMasterPasswordContract.Repository {
    private val manager = android.accounts.AccountManager.get(context)
    private val handler = Handler(
            HandlerThread(AccountManager::class.java.simpleName,
                    Process.THREAD_PRIORITY_BACKGROUND
                         ).apply {
                start()
            }.looper)
    private var _account: Account? = null
    private val current: Account?
        get() {
            val accounts = manager.getAccountsByType(Authenticator.ACCOUNT_TYPE)
            val appAccount = accounts.lastOrNull() ?: return _account

            val email = appAccount.name
            val password = manager.getPassword(appAccount)

            val current = Account(email, password)
            if (current != _account) {
                _account = current
                return current
            }
            return _account
        }

    override fun getAccount(): Account? = current

    override fun addAccount(account: Account, token: String) {
        val bundle = Bundle()
        bundle.putString(TOKEN_KEY, token)
        manager.addAccountExplicitly(account.androidAccount, account.password, bundle)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun removeAccount(activity: Activity) {
        val account = current?.androidAccount ?: return
        if (manager.removeAccountExplicitly(account)) {
            _account = null
        } else {
            manager.removeAccount(account, activity, {
                try {
                    if (it.isDone) {
                        _account = null
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, handler)
        }
    }
}

private val Account.androidAccount
    get() = android.accounts.Account(userName, Authenticator.ACCOUNT_TYPE)