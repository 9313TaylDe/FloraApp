package com.example.floraapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProducttDAO {
	@Insert
	suspend fun inserir(productList: ProductList)
	
	@Delete
	suspend fun deletar(productList: ProductList)
	
	@Update
	suspend fun atualizar(productList: ProductList)
	
	@Query("SELECT * FROM ProductList")
	fun getAll(): Flow<List<ProductList>>
	
}