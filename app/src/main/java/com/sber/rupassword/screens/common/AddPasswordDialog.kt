package com.sber.rupassword.screens.common

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.sber.rupassword.R
import com.sber.rupassword.models.Password
import com.sber.rupassword.utils.setError
import com.sber.rupassword.utils.toStringOrNull

class AddPasswordDialog : DialogFragment() {

    private lateinit var mListener: Listener

    interface Listener {
        fun onClick(password: Password)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as Listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = activity!!.layoutInflater.inflate(
                R.layout.password_details, null)

        val siteInput = view.findViewById<TextInputEditText>(
                R.id.site_input)
        val loginInput = view.findViewById<TextInputEditText>(
                R.id.login_input)
        val passwordInput = view.findViewById<TextInputEditText>(
                R.id.password_input)

        siteInput.setError()
        loginInput.setError()
        passwordInput.setError()

        return AlertDialog.Builder(context!!,
                R.style.MaterialAlertDialog)
                .setView(view)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    val site = siteInput.text?.toStringOrNull()
                    val login = loginInput.text?.toStringOrNull()
                    val password = passwordInput.text?.toStringOrNull()
                    if (site != null && login != null && password != null) {
                        mListener.onClick(Password(
                                site = site,
                                login = login,
                                password = password))
                    }
                }
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    dismiss()
                }
                .setTitle(R.string.please_enter_a_password)
                .create()
    }
}