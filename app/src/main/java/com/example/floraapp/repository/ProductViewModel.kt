package com.example.floraapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.floraapp.data.DAOProduct
import com.example.floraapp.data.ProducstList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(private val prod: DAOProduct) : ViewModel() {
	val pegarTodosProdutos = prod.pegarTodos().stateIn(
		scope = viewModelScope,
		started = SharingStarted.WhileSubscribed(5000),
		initialValue = emptyList<ProducstList>()
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
}