package com.sber.rupassword

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

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
        val view: View = activity!!.layoutInflater.inflate(R.layout.password_details, null)
        return AlertDialog.Builder(context!!, R.style.MaterialAlertDialog)
                .setView(view)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    mListener.onClick(Password(
                            site = view.findViewById<EditText>(R.id.site_input).text.toString(),
                            login = view.findViewById<EditText>(R.id.login_input).text.toString(),
                            password = view.findViewById<EditText>(
                                    R.id.password_input).text.toString()))
                }
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    dismiss()
                }
                .setTitle(R.string.please_enter_a_password)
                .create()
    }
}