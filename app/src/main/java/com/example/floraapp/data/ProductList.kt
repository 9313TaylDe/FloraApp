package com.example.floraapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class ProductList(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val title: String,
	val description: String,
	val img: Int,
	val price: Float
)