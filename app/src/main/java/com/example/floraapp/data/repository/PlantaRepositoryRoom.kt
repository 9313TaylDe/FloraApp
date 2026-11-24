package com.example.floraapp.data.repository

import com.example.floraapp.data.dao.PlantaDao
import com.example.floraapp.data.entitys.Plantas
import com.example.floraapp.data.remote.mapper.toDomain
import com.example.floraapp.domain.model.PlantasModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlantaRepositoryRoom(private val daoProduct: PlantaDao) {
        suspend fun observeAll(): Flow<List<PlantasModel>> = daoProduct.pegarTodos().map { list -> list.map { it.toDomain() } }
}