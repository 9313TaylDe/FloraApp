package com.example.floraapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.floraapp.data.dao.PlantaDao

class PlantaFactory(private val factory: PlantaDao) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(PlantaViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return PlantaViewModel(factory) as T
		}
		throw IllegalArgumentException(
			"ViewModel desconhecido"
		)
	}
}