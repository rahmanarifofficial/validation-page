package com.rahmanarifofficial.validationpage

import android.text.TextUtils
import android.widget.EditText

fun EditText.isRequired(message: String): Boolean{
    if (TextUtils.isEmpty(this.text)){
        this.setError(message)
        return false
    }
    return true
}