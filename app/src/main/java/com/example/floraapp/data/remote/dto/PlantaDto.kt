
package com.example.floraapp.data.dto

import com.google.gson.annotations.SerializedName

data class PlantaDto(
    val id: Int,

    @SerializedName("common_name")
    val commonName: String?,

    @SerializedName("scientific_name")
    val scientificName: String,

    val family: String?,

    val genus: String?,

    val rank: String?,

    val status: String?,

    val year: Int?,

    val slug: String?,

    @SerializedName("image_url")
    val imageUrl: String?
)
