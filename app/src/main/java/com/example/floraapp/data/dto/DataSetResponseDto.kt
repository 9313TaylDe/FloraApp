package com.example.floraapp.data.dto

data class DataSetResponseDto(
    val plants_count: Int,
    val detailed_plants_count: Int,
    val detailled_plants_count: Int,
    val dataset_version: String,
    val dataset: DataSetDto
)
