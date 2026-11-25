package com.example.floraapp.data.repository

import com.example.floraapp.data.dao.PlantaDao
import com.example.floraapp.data.entitys.Plantas
import com.example.floraapp.data.remote.mapper.toDomain
import com.example.floraapp.domain.model.PlantasModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlantaRepositoryRoom(private val daoProduct: PlantaDao) {
    fun observeAll(): Flow<List<PlantasModel>> =
        daoProduct.pegarTodos().map { list -> list.map { it.toDomain() } }

    fun obserById(id: Int): Flow<PlantasModel?> = daoProduct.pegarPorId(id).map { it?.toDomain() }

    suspend fun getByTrefleId(trefleId: Int): Plantas? = daoProduct.pegarPorTreledId(trefleId)

    suspend fun save(plantas: Plantas): Plantas {
        daoProduct.inserir(plantas)
        return plantas
    }

    suspend fun delete(plantas: Plantas) = daoProduct.deletar(plantas)

    suspend fun refresh() = Unit
}