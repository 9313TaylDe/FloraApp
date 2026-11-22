package com.example.floraapp.data.dto

data class PlantasResponseDto(
    val data: List<PlantaDto>,
    val links: LinksDto,
    val meta: MetaDto
)
