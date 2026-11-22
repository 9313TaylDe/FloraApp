package com.example.floraapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.floraapp.data.dao.PlantaDao
import com.example.floraapp.domain.repository.PlantaRepositoryDomain

class PlantaFactory(private val plantaRepositoryDomain: PlantaRepositoryDomain) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(PlantaViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return PlantaViewModel(plantaRepositoryDomain) as T
		}
		throw IllegalArgumentException(
			"ViewModel desconhecido"
		)
	}
}