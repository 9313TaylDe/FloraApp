package com.example.floraapp.data

import com.example.floraapp.data.remote.PlantasApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlantaApi {
    private  const val URL_BASE = "https://trefle.io/api/v1/"

    val retrofitApis: PlantasApiService by lazy {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlantasApiService::class.java)

    }
}