package com.sber.rupassword.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.TextView
import android.widget.Toast
import com.sber.rupassword.R

fun copyToClipboard(context: Context, view: TextView) {
    val manager: ClipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    manager.let {
        it.setPrimaryClip(
                ClipData.newPlainText(context.getString(
                        R.string.password), view.text.toString()))
        Toast.makeText(context, R.string.toast_text_copied, Toast.LENGTH_SHORT).show()
    }
}