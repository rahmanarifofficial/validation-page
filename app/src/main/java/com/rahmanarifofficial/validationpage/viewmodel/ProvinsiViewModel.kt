package com.rahmanarifofficial.validationpage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rahmanarifofficial.validationpage.model.Alamat
import com.rahmanarifofficial.validationpage.repository.ProvinsiRepository

class ProvinsiViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProvinsiRepository()

    fun getListProvinsi() = repository.getListProvince()
}