package com.example.floraapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.floraapp.data.dao.PlantaDao
import com.example.floraapp.data.entitys.Plantas
import com.example.floraapp.data.repository.PlantaRepositoryRemote
import com.example.floraapp.domain.repository.PlantaRepositoryDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlantaViewModel(val plantaRepositoryDomain: PlantaRepositoryDomain) : ViewModel() {

    val pegarTodasAsPlantas = plantaRepositoryDomain.observeAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    suspend fun pegarDetalhePlanta(id: String): Flow<Plantas?> {
        return plantaRepositoryDomain.observeById(id = id)
    }

    suspend fun pesquisarPlanta(search: String): List<Plantas> {
        return plantaRepositoryDomain.searchPlantas(search)
    }

    suspend fun refresh() {
        plantaRepositoryDomain.refresh()
    }


    fun Deletar(producstList: Plantas) {
        viewModelScope.launch {
            prod.deletar(producstList)
        }
    }

    fun Atualizar(producstList: Plantas) {
        viewModelScope.launch {
            prod.atualizar(producstList)
        }
    }

    fun Inserir(producstList: Plantas) {
        viewModelScope.launch {
            prod.inserir(producstList)
        }
    }

    fun pegarPoriD(id: Int): Flow<Plantas?> {
        return prod.pegarPorId(id)
    }

    fun atualiarFavoritos(producstList: Plantas) {
        viewModelScope.launch {
            prod.updateFavorite(producstList.id, !producstList.favorite)
        }
    }
}