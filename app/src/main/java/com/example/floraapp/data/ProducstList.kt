package com.example.floraapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProducstList(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val title: String,
	val typeMedia: String = "",
	val price: Float,
	val description: String,
	val imgURI: String = "",
	val imgRes: Int = 0,
	val favorite: Boolean = false
)