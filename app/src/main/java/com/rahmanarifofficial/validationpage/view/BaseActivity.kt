package com.rahmanarifofficial.validationpage.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    abstract fun initObject()
    abstract fun initUI()
    abstract fun eventUI()
}