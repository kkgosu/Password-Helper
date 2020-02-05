package com.sber.rupassword

import android.text.Editable
import android.view.View
import android.view.ViewParent
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun Editable.toStringOrNull(): String? {
    val str = toString().trim()
    return if (str.isEmpty()) null else str
}

fun TextInputEditText.setError() {
    doOnTextChanged { text, _, _, _ ->
        if (getTextInputLayout() is TextInputLayout) {
            error = if (text?.length == 0) {
                context.getString(
                        R.string.field_must_not_be_empty)
            } else {
                null
            }
        }
    }
}

private fun TextInputEditText.getTextInputLayout(): TextInputLayout? {
    var parent: ViewParent = parent
    while (parent is View) {
        if (parent is TextInputLayout) {
            return parent
        }
        parent = parent.getParent()
    }
    return null
}