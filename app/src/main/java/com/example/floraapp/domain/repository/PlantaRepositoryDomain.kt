package com.example.floraapp.domain.repository

import com.example.floraapp.domain.model.PlantasModel
import kotlinx.coroutines.flow.Flow

interface PlantaRepositoryDomain {
    suspend fun observeAll(): Flow<List<PlantasModel>>
    suspend fun searchPlantas(search: String): List<PlantasModel>
    suspend fun observeById(id: String): Flow<PlantasModel?>

    suspend fun refresh() = Unit

    suspend fun insertPlanta(plantas: PlantasModel)

    suspend fun deletePLanta(plantas: PlantasModel)

    suspend fun updatePlanta(plantas: PlantasModel)

    suspend fun updateFavorite(id: Int, favorite: Boolean)
}