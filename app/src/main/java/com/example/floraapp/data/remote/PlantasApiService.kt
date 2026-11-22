package com.example.floraapp.data.remote

import com.example.floraapp.data.dto.PlantaDetalheDto
import com.example.floraapp.data.dto.PlantaDto
import com.example.floraapp.data.dto.PlantasResponseDto
import com.example.floraapp.data.entitys.Plantas
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlantasApiService {

    @GET("plants")
    suspend fun getPlants(
        @Query("token") token: String,
        @Query("page") page: Int = 1
    ): PlantasResponseDto

    @GET("plants/{id}")
    suspend fun getPlantById(
        @Path("id") id: Int,
        @Query("token")token:String
    ): PlantaDetalheDto

    @GET("plants/search")
    suspend fun searchPlants(
        @Query("token") token: String,
        @Query("q") query: String,
        @Query("page") page: Int = 1
    ): PlantasResponseDto

}
