package com.example.floraapp.domain.repository

import com.example.floraapp.domain.model.PlantasModel
import kotlinx.coroutines.flow.Flow

interface PlantaRepositoryDomain {
    fun observeAll(): Flow<List<PlantasModel>>
    suspend fun searchPlantas(search: String): List<PlantasModel>
    fun observeById(id: Int): Flow<PlantasModel?>

    suspend fun refresh()
    suspend fun insertPlanta(plantas: PlantasModel)

    suspend fun deletePLanta(plantas: PlantasModel)

    suspend fun updatePlanta(plantas: PlantasModel)

    suspend fun updateFavorite(
        id: Int,
        favorite: Boolean
    )
}