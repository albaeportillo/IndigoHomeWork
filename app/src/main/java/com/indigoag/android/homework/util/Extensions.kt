package com.indigoag.android.homework.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun Boolean.isVisible(): Int {
    return if (this) View.VISIBLE else View.GONE
}

fun Boolean.isNotVisible(): Int {
    return if (this) View.GONE else View.VISIBLE
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}