package com.sber.rupassword.sync

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sber.rupassword.CreateMasterPassword

class Authenticator(private val context: Context) : AbstractAccountAuthenticator(context) {
    override fun getAuthTokenLabel(authTokenType: String?): String? = null

    override fun confirmCredentials(response: AccountAuthenticatorResponse?, account: Account?,
            options: Bundle?): Bundle? = null

    override fun updateCredentials(response: AccountAuthenticatorResponse?, account: Account?,
            authTokenType: String?, options: Bundle?): Bundle {
        throw UnsupportedOperationException()
    }

    override fun getAuthToken(response: AccountAuthenticatorResponse?, account: Account?,
            authTokenType: String?, options: Bundle?): Bundle? = null

    override fun hasFeatures(response: AccountAuthenticatorResponse?, account: Account?,
            features: Array<out String>?): Bundle = Bundle(1).apply {
        putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false)
    }

    override fun editProperties(response: AccountAuthenticatorResponse?,
            accountType: String?): Bundle? = null

    override fun addAccount(response: AccountAuthenticatorResponse?, accountType: String?,
            authTokenType: String?, requiredFeatures: Array<out String>?,
            options: Bundle?): Bundle? = Bundle().apply {
        if (AccountManager.get(context).getAccountsByType(ACCOUNT_TYPE).isEmpty()) {
            val intent = Intent(context, CreateMasterPassword::class.java)
            intent.putExtra(KEY_TOKEN_TYPE, TOKEN_TYPE)
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
            putParcelable(AccountManager.KEY_INTENT, intent)
        }
    }

    companion object {
        const val ACCOUNT_TYPE = "typeOfAccount"
        const val TOKEN_TYPE = "typeOfAccount_token"

        private const val KEY_TOKEN_TYPE = "KEY_TOKEN_TYPE"
    }
}