package com.example.floraapp.data.mapper

import com.example.floraapp.data.dto.PlantaDto
import com.example.floraapp.data.entitys.Plantas as PlantasEntity
import com.example.floraapp.domain.model.PlantasModel as PlantasDomain


fun PlantaDto.toDomain(): PlantasDomain {
    return PlantasDomain(
        trefleId = id,
        title = common_name ?: scientificName,
        genus = genus,
        family = family,
        scientificName = scientificName,
        description = ""
    )
}

fun PlantasDomain.toEntity(): PlantasEntity {
    return PlantasEntity(
        id = id,
        trefleId = trefleId,
        genus = genus,
        family = family,
        favorite = favorite,
        isCustom = isCustom,
        title = title,
        scientificName = scientificName

    )
}


fun PlantasEntity.toDomain(): PlantasDomain {
    return PlantasDomain(
        id = id,
        trefleId = trefleId,
        isCustom = isCustom,
        title = title,
        scientificName = scientificName,
        genus = genus,
        family = family,
        favorite = favorite
    )
}