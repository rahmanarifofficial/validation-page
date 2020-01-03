package com.rahmanarifofficial.filmcatalog.network

import com.rahmanarifofficial.validationpage.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Aby Fajar(www.androidtinker.com)
 * abyfajar@gmail.com
 * on 26 November 2018
 */
class DataSource {
    companion object {

        val TIME_OUT_REQUEST = 900000
        val private: Retrofit by lazy {
            val client = OkHttpClient.Builder()
            client.connectTimeout(TIME_OUT_REQUEST.toLong(), TimeUnit.MILLISECONDS)
            client
                .readTimeout(TIME_OUT_REQUEST.toLong(), TimeUnit.MILLISECONDS)
                .writeTimeout(TIME_OUT_REQUEST.toLong(), TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(false)

            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
        }

    }

    open class Private {
        companion object {
            val service: ApiService by lazy {
                private.create(ApiService::class.java)
            }
        }
    }
}