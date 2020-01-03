package com.rahmanarifofficial.validationpage

import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView

fun EditText.isRequired(message: String): Boolean{
    if (TextUtils.isEmpty(this.text)){
        this.setError(message)
        return false
    }
    return true
}

fun TextView.isRequired(message: String): Boolean{
    if (TextUtils.isEmpty(this.text)){
        this.setError(message)
        return false
    }
    return true
}