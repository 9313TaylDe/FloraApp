package com.example.floraapp.repository

import androidx.room.TypeConverter

class CategoryConverter {
	@TypeConverter
	fun fromLista(list: List<String>): String {
		return list.joinToString(",")
	}
	
	@TypeConverter
	fun toLista(date: String): List<String> {
		return if (date.isEmpty()) emptyList()
		else date.split(",")
	}
}