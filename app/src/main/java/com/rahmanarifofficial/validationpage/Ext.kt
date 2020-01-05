package com.rahmanarifofficial.validationpage

import android.text.TextUtils
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

fun EditText.wasFilled(message: String): Boolean{
    if (TextUtils.isEmpty(this.text)){
        rootView?.let {

        }
        return false
    }
    return true
}