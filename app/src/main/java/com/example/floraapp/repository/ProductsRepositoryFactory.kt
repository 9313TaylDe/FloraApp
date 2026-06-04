package com.example.floraapp.repository

import ProductsViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.floraapp.data.DAOProduct

class ProductsRepositoryFactory(private val Factory : DAOProduct): ViewModelProvider.Factory {

	override fun <T: ViewModel> create(modelClass: Class<T>): T{
		if(modelClass.isAssignableFrom(ProductsViewModel::class.java)){
			@Suppress("UNCHECKED_CAST")
			return ProductsViewModel(Factory) as T
		}
		throw IllegalArgumentException(
			"Nenhuma view válida ou view desconhecida"
		)
	}
	
}