package com.rahmanarifofficial.filmcatalog.network

import com.rahmanarifofficial.validationpage.model.Alamat
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("daerahindonesia/provinsi")
    fun getProvinsi(): Call<Alamat.ResponseProvinsi>
}