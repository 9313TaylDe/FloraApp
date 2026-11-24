package com.example.floraapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.floraapp.data.entitys.Plantas
import com.example.floraapp.domain.model.PlantasModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(planta: Plantas)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirTodas(plantas: List<Plantas>)

    @Delete
    suspend fun deletar(planta: Plantas)

    @Update
    suspend fun atualizar(planta: Plantas)

    @Query("SELECT * FROM Plantas")
    fun pegarTodos(): Flow<List<Plantas>>

    @Query("SELECT * FROM plantas WHERE id = :id")
    fun pegarPorId(id: Int): Flow<Plantas?>

    @Query("SELECT * FROM plantas WHERE trefleId = :trefledId LIMIT 1")
    suspend fun pegarPorTreledId(trefledId: Int): Plantas?

    @Query("SELECT * FROM plantas WHERE title LIKE '%' || :title || '%' ")
     fun searchPlantas(title: String): Flow<List<Plantas>>

    @Query("UPDATE plantas SET favorite = :favorite WHERE id = :id")
    suspend fun updateFavorite(id: Int, favorite: Boolean)
}


// VERSION 2, AUTOMATIC CODING, WITHOUT HELP FROM CHATGPT