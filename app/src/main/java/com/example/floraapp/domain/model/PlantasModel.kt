package com.example.floraapp.domain.model

data class PlantasModel(
    val id: Int = 0,
    val trefleId: Int? = null,

    val title: String,
    val scientificName: String,
    val family: String?,
    val genus: String?,
    val description: String = "",

    val favorite: Boolean = false,
    val isCustom: Boolean = false
)
