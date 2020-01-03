package com.rahmanarifofficial.validationpage.repository

import androidx.lifecycle.MutableLiveData
import com.rahmanarifofficial.filmcatalog.network.DataSource
import com.rahmanarifofficial.validationpage.model.Alamat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvinsiRepository {
    private val service = DataSource.Private.service

    fun getListProvince(): MutableLiveData<Alamat.ResponseProvinsi> {
        val listProvinsi = MutableLiveData<Alamat.ResponseProvinsi>()
        service.getProvinsi().enqueue(object : Callback<Alamat.ResponseProvinsi> {
            override fun onFailure(call: Call<Alamat.ResponseProvinsi>, t: Throwable) {
                listProvinsi.value = null
            }

            override fun onResponse(
                call: Call<Alamat.ResponseProvinsi>,
                response: Response<Alamat.ResponseProvinsi>
            ) {
                listProvinsi.value = response.body()
            }
        })
        return listProvinsi
    }
}