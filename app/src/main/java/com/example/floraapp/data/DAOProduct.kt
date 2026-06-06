package com.example.floraapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DAOProduct {
	
	@Insert
	suspend fun inserir(producstList: ProducstList)
	
	@Delete
	suspend fun deletar(producstList: ProducstList)
	
	@Update
	suspend fun atualizar(producstList: ProducstList)
	
	@Query("SELECT * FROM ProducstList")
	fun pegarTodos(): Flow<List<ProducstList>>
	
}


// VERSION 2, AUTOMATIC CODING, WITHOUT HELP FROM CHATGPT