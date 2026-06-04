package com.example.floraapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Database(
	exportSchema = false,
	entities = [ProductList::class],
	version = 1
)
class AppdataBase(private val prod: ProducttDAO) : ViewModel() {
	val product = prod.getAll().stateIn(
		scope = viewModelScope,
		started = SharingStarted.WhileSubscribed(5000),
		initialValue = emptyList<ProductList>()
	)
	
	fun Deletar(productList: ProductList){
		viewModelScope.launch {
			prod.deletar(productList)
		}
	}
	fun Atualizar(productList: ProductList){
		viewModelScope.launch {
			prod.atualizar(productList)
		}
	}
	fun Inserir(productList: ProductList){
		viewModelScope.launch {
			prod.inserir(productList)
		}
	}
}