package com.example.floraapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.floraapp.data.dao.PlantaDao
import com.example.floraapp.data.entitys.Plantas
import com.example.floraapp.repository.PlantaCategoryConverter

@Database(
	entities = [Plantas::class],
	version = 1,
	exportSchema = false
)

@TypeConverters(
	PlantaCategoryConverter::class
)

abstract class AppsDataBases : RoomDatabase() {
	abstract fun getAll(): PlantaDao
}
