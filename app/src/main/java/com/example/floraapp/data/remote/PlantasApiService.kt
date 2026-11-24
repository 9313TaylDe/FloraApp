package com.example.floraapp.data.remote

import com.example.floraapp.data.remote.dto.PlantaDetalheDto
import com.example.floraapp.data.remote.dto.PlantasResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlantasApiService {

    @GET("plants")
    suspend fun getPlants(
        @Query("token") token: String,
        @Query("page") page: Int = 1,
        @Query("per_page")per_page:Int = 20
    ): PlantasResponseDto

    @GET("plants/{id}")
    suspend fun getPlantById(
        @Path("id") id: String,
        @Query("token")token:String
    ): PlantaDetalheDto

    @GET("plants/search")
    suspend fun searchPlants(
        @Query("token") token: String,
        @Query("q") query: String,
        @Query("page") page: Int = 1
    ): PlantasResponseDto

}
