package com.example.floraapp.data.dto

data class PlantaDto(
    val id:Int,
    val common_name:String?,
    val scientificName:String,
    val family:String?,
    val genus:String?,
    val rank:String?,
    val status:String?,
    val year:Int?,
    val slug:String?
)
