package com.example.floraapp.di

import  android.content.Context
import androidx.room.Room
import com.example.floraapp.data.AppsDataBases
import com.example.floraapp.data.PlantaApi
import com.example.floraapp.data.remote.PlantasApiService
import com.example.floraapp.data.repository.PlantaRepositoryRemote
import com.example.floraapp.domain.repository.PlantaRepositoryDomain

class AppContainer(
    context: Context
) {
    val databaseapp: AppsDataBases = Room.databaseBuilder(
        context.applicationContext,
        AppsDataBases::class.java,
        DATABASE_NAME
    ).build()

    val PlantaDaoDatabase = databaseapp.getAll()

    val plantasApiService: PlantasApiService by lazy {
        PlantaApi.retrofitApis
    }

    val plantaRepositoryDomain: PlantaRepositoryDomain by lazy {
        PlantaRepositoryRemote(
            plantasApiService, PlantaDaoDatabase
        )
    }

    companion object {
        const val DATABASE_NAME = "db_plantas"
    }

}