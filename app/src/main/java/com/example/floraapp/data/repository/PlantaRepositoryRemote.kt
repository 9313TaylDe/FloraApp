package com.example.floraapp.data.repository

import com.example.floraapp.data.dao.PlantaDao
import com.example.floraapp.data.remote.mapper.toDomain
import com.example.floraapp.data.remote.PlantasApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.floraapp.BuildConfig
import com.example.floraapp.data.remote.mapper.toEntity
import com.example.floraapp.domain.model.PlantasModel
import com.example.floraapp.domain.repository.PlantaRepositoryDomain

class PlantaRepositoryRemote(
    private val apiService: PlantasApiService,
    private val daoProduct: PlantaDao
) : PlantaRepositoryDomain {

    private val apiToken = BuildConfig.TREFLE_API_TOKEN

    override fun observeAll(): Flow<List<PlantasModel>> =
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

    override suspend fun refresh(page: Int) {
        val response = apiService.getPlants(token = apiToken, page = page, per_page = 20)

        response.data.forEach { planta ->
            val plantaApi = planta.toDomain()

            val plantaExistente = plantaApi.trefleId?.let { trefleID ->
                daoProduct.pegarPorTreledId(trefleID)
            }

            val planatAtualizada = plantaApi.copy(
                id = plantaExistente?.id ?: 0,
                favorite = plantaExistente?.favorite ?: false,
                isCustom = plantaExistente?.isCustom ?: false
            )
            daoProduct.inserir(planatAtualizada.toEntity())
        }
    }


//    ROOM

    override fun observeById(id: Int): Flow<PlantasModel?> {
        return daoProduct.pegarPorId(id).map { planta ->
            planta?.toDomain()
        }
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

    override suspend fun updateFavorite(id: Int, favorite: Boolean) {
        try {
            daoProduct.updateFavorite(id, favorite)
            if (favorite) {
                print("Adicionada aos favoritos")
            } else {
                print("Removido dos favoritos favoritos")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            print("Erro ao adicionar ${e.message}")
        }
    }
}