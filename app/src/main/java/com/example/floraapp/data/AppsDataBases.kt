package com.example.floraapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.floraapp.repository.CategoryConverter

@Database(
	entities = [ProducstList::class],
	version = 1,
	exportSchema = false
)

@TypeConverters(
	CategoryConverter::class
)

abstract class AppsDataBases : RoomDatabase() {
	abstract fun getAll(): DAOProduct
}
// VERSION 2, AUTOMATIC CODING, WITHOUT HELP FROM CHATGPT