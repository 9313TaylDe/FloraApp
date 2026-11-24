package com.example.floraapp.data.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plantas")
data class Plantas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val trefleId: Int? = null,
    val title: String,
    val scientificName: String,
    val family: String? = null,
    val genus: String? = null,
    val imageUrl: String? = null,
    val favorite: Boolean = false,
    val isCustom: Boolean = false
)

