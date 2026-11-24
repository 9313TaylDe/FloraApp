package com.example.floraapp.data.remote.dto

import com.example.floraapp.data.dto.PlantaDto

data class PlantasResponseDto(
    val data: List<PlantaDto>,
    val links: LinksDto,
    val meta: MetaDto
)
