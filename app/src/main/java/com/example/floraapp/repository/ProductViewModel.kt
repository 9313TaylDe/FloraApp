package com.example.floraapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.floraapp.data.DAOProduct
import com.example.floraapp.data.ProducstList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(private val prod: DAOProduct) : ViewModel() {
	
	val pegarTodosProdutos = prod.pegarTodos().stateIn(
		scope = viewModelScope,
		started = SharingStarted.WhileSubscribed(5000),
		initialValue = emptyList()
	)
	
	fun Deletar(producstList: ProducstList) {
		viewModelScope.launch {
			prod.deletar(producstList)
		}
	}
	
	fun Atualizar(producstList: ProducstList) {
		viewModelScope.launch {
			prod.atualizar(producstList)
		}
	}
	
	fun Inserir(producstList: ProducstList) {
		viewModelScope.launch {
			prod.inserir(producstList)
		}
	}
	
	fun pegarPoriD(id: Int): Flow<ProducstList?> {
		return prod.pegarPorId(id)
	}
	
	fun atualiarFavoritos(producstList: ProducstList) {
		viewModelScope.launch {
			prod.updateFavorite(producstList.id, !producstList.favorite)
		}
	}
}