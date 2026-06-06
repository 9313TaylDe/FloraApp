package com.example.floraapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.floraapp.data.DAOProduct

class ProductFactory(private val factory: DAOProduct) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return ProductViewModel(factory) as T
		}
		throw IllegalArgumentException(
			"ViewModel desconhecido"
		)
	}
}