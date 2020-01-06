package com.rahmanarifofficial.validationpage

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

interface ErrorHandling {
    fun showSnackbar(message: String)
}

fun EditText.wasFilled(callback: ErrorHandling, message: String): Boolean {
    if (TextUtils.isEmpty(this.text)) {
        callback.showSnackbar(message)
        return false
    }
    return true
}

fun EditText.wasEnough(callback: ErrorHandling, message: String): Boolean {
    if (this.text.toString().length < 8) {
        callback.showSnackbar(message)
        return false
    }
    return true
}

fun EditText.checkMinChar() {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable) {

        }

        override fun beforeTextChanged(
            s: CharSequence, start: Int,
            count: Int, after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            if (s.length < 8) {
                this@checkMinChar.setError(resources.getString(R.string.min_char_rekening))
            }
        }
    })
}

fun TextView.wasFilled(callback: ErrorHandling, message: String): Boolean {
    if (TextUtils.isEmpty(this.text)) {
        callback.showSnackbar(message)
        return false
    }
    return true
}

fun Spinner.wasChoosen(callback: ErrorHandling, message: String): Boolean {
    if (this.selectedItemPosition == 0) {
        callback.showSnackbar(message)
        return false
    }
    return true
}