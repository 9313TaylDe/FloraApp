package com.example.floraapp.domain.model

data class PlantasModel(
    val id: Int = 0,
    val trefleId: Int? = null,
    val title: String,
    val scientificName: String,
    val family: String? = null,
    val genus: String? = null,
    val description: String = "",
    val imageUrl: String? = null,
    val favorite: Boolean = false,
    val isCustom: Boolean = false
)