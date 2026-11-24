package com.example.floraapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.floraapp.domain.model.PlantasModel
import com.example.floraapp.domain.repository.PlantaRepositoryDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlantaViewModel(val plantaRepositoryDomain: PlantaRepositoryDomain) : ViewModel() {
    private val _paginaInciial = MutableStateFlow(0)
    private val _carregando = MutableStateFlow<Boolean>(false)
    val carregando: StateFlow<Boolean> = _carregando.asStateFlow()

    val _message = MutableStateFlow<String>("")
    val message: StateFlow<String> = _message.asStateFlow()

    private val viewmodelScope = viewModelScope

    val pegarTodasAsPlantas = plantaRepositoryDomain.observeAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    suspend fun pegarDetalhePlanta(id: String): Flow<PlantasModel?> {
        return plantaRepositoryDomain.observeById(id.toInt())
    }

    suspend fun refresh() {
        viewModelScope.launch {
            try {
                carregarPagina(1)
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

    private suspend fun carregarPagina(pagina: Int) {
        if (_carregando.value) return

        _carregando.value = true

        try {
            plantaRepositoryDomain.refresh(pagina)
            _paginaInciial.value = pagina
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _carregando.value = false
        }
    }

    fun morePLants() {
        viewModelScope.launch {
            carregarPagina(_paginaInciial.value + 1)
        }
    }

    suspend fun pesquisarPlanta(search: String): List<PlantasModel> {
        return plantaRepositoryDomain.searchPlantas(search)
    }


    fun Deletar(plantasModel: PlantasModel) {
        viewModelScope.launch {
            plantaRepositoryDomain.deletePLanta(plantasModel)
        }
    }

    fun Atualizar(plantasModel: PlantasModel) {
        viewModelScope.launch {
            plantaRepositoryDomain.updatePlanta(plantasModel)
        }
    }

    fun Inserir(plantasModel: PlantasModel) {
        viewModelScope.launch {
            plantaRepositoryDomain.insertPlanta(plantasModel)
        }
    }


    fun pegarPoriD(id: Int): Flow<PlantasModel?> {
        return plantaRepositoryDomain.observeById(id)
    }

    fun atualizarFavoritos(plantasModel: PlantasModel) {
        viewModelScope.launch {
            try {
                plantaRepositoryDomain.updateFavorite(
                    plantasModel.id,
                    plantasModel.favorite
                )

                _message.value = if (plantasModel.favorite) {
                    "Adicionada aos favoritos!"
                } else {
                    "Removida dos favoritos!"
                }

            } catch (e: Exception) {
                _message.value = "Erro ao atualizar favorito: ${e.message}"
            }
        }

    }

    fun limparMessage(){
        _message.value = ""
    }
}