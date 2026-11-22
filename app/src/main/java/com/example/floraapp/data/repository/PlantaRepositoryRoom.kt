package com.example.floraapp.data.repository

import com.example.floraapp.data.dao.PlantaDao
import com.example.floraapp.data.entitys.Plantas
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlantaRepositoryRoom(private val daoProduct: PlantaDao) {
    suspend fun observeAll(): Flow<List<Plantas>> = daoProduct.pegarTodos().map { list ->list.map { it.toDomain() } }
}