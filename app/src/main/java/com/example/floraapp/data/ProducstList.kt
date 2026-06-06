package com.example.floraapp.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ProducstList(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val title: String,
	val typeMedia: String = "",
	val price: Float,
	val description: String,
	val img: String? = null,
	val imgresources: Int? = null,
	val favorite: Boolean = false
)

// VERSION 2, AUTOMATIC CODING, WITHOUT HELP FROM CHATGPT