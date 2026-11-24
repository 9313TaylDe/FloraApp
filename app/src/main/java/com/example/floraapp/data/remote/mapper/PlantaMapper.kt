package com.example.floraapp.data.remote.mapper

import com.example.floraapp.data.dto.PlantaDto
import com.example.floraapp.data.entitys.Plantas as PlantasEntity
import com.example.floraapp.domain.model.PlantasModel as PlantasModel


fun PlantaDto.toDomain(): PlantasModel {
    return PlantasModel(
        trefleId = id,
        title = commonName ?: scientificName,
        genus = genus,
        family = family,
        scientificName = scientificName,
        description = "",
        imageUrl = imageUrl,
        isCustom = false
    )
}

fun PlantasModel.toEntity(): PlantasEntity {
    return PlantasEntity(
        id = id,
        trefleId = trefleId,
        title = title,
        scientificName = scientificName,
        genus = genus,
        family = family,
        imageUrl = imageUrl,
        favorite = favorite,
        isCustom = isCustom

    )
}


fun PlantasEntity.toDomain(): PlantasModel {
    return PlantasModel(
        id = id,
        trefleId = trefleId,
        title = title,
        scientificName = scientificName,
        genus = genus,
        family = family,
        imageUrl = imageUrl,
        favorite = favorite,
        isCustom = isCustom
    )
}