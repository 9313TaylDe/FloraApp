package com.example.floraapp.di

import PlantasApiService
import  android.content.Context
import androidx.room.Room
import com.example.floraapp.data.AppsDataBases
import com.example.floraapp.data.PlantaApi

class AppContainer(
    context: Context
) {
    val databaseapp: AppsDataBases = Room.databaseBuilder(
        context.applicationContext,
        AppsDataBases::class.java,
        DATABASE_NAME
    ).build()

    val plantasApiService: PlantasApiService by lazy {
        PlantaApi.retrofitApis
    }

val plan
    companion object {
        val DATABASE_NAME = "db_plantas"
    }

}