package com.example.floraapp.data.repository

import com.example.floraapp.data.dao.PlantaDao
import com.example.floraapp.data.entitys.Plantas
import com.example.floraapp.data.mapper.toDomain
import com.example.floraapp.data.remote.PlantasApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.floraapp.BuildConfig
import com.example.floraapp.data.mapper.toEntity
import com.example.floraapp.domain.model.PlantasModel
import com.example.floraapp.domain.repository.PlantaRepositoryDomain

class PlantaRepositoryRemote(
    private val apiService: PlantasApiService,
    private val daoProduct: PlantaDao
) : PlantaRepositoryDomain {

    private val apiToken = BuildConfig.TREFLE_API_TOKEN

    override suspend fun observeAll(): Flow<List<PlantasModel>> =
        daoProduct.pegarTodos().map { list -> list.map { it.toDomain() } }

    override suspend fun searchPlantas(search: String): List<PlantasModel> {
        val idServidor = apiService.searchPlants(
            token = apiToken,
            query = search
        )
        return idServidor.data.map { plantadto ->
            plantadto.toDomain()
        }
    }

    override suspend fun refresh() = Unit


//    ROOM

    suspend fun observeById(id: Int): Flow<PlantasModel?> {
        return daoProduct.pegarPorId(id).map { planta ->
            planta?.toDomain()
        }
    }

    suspend fun refreshRoom(){
        val apiResponse = apiService.getPlants(token = apiToken)


    }
    override suspend fun insertPlanta(plantas: PlantasModel) {
        daoProduct.inserir(plantas.toEntity())
    }

    override suspend fun deletePLanta(plantas: PlantasModel) {
        daoProduct.deletar(plantas.toEntity())
    }

    override suspend fun updatePlanta(plantas: PlantasModel) {
        daoProduct.atualizar(plantas.toEntity())
    }
}