package com.example.floraapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.floraapp.data.DAOProduct
import com.example.floraapp.data.ProducstList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class ProductsRepository(private val prods: DAOProduct) : ViewModel() {
	
	val products = prods.pegarTodos().stateIn(
		initialValue = emptyList<ProducstList>(),
		started = SharingStarted.WhileSubscribed(5000),
		scope = viewModelScope
	)
	
	fun Deletar(producstList: ProducstList) {
		viewModelScope.launch {
			prods.deletar(producstList)
		}
	}
	
	
	fun Atualizar(producstList: ProducstList) {
		viewModelScope.launch {
			prods.atualizar(producstList)
		}
	}
	
	fun Inserir(producstList: ProducstList) {
		viewModelScope.launch {
			prods.inserir(producstList)
		}
	}
		
		fun pegarPoriD(id: Int) {
			viewModelScope.launch {
				prods.pegarPorId(id)
			}
			
		}
		
		
		fun PegarTodos() {
			prods.pegarTodos()
		}
	}

// VERSION 2, AUTOMATIC CODING, WITHOUT HELP FROM CHATGPT
