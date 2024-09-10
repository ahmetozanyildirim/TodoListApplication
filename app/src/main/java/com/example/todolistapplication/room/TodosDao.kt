package com.example.todolistapplication.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolistapplication.data.entity.Todos

@Dao
interface TodosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun instertTodos(todos: Todos)

    @Update
    suspend fun updateTodos(todos: Todos)

    @Delete
    suspend fun deleteTodos(todos: Todos)

    @Query ("SELECT * FROM todos ORDER BY id DESC")
    fun getAllTodos(): List<Todos>

    @Query("SELECT * FROM todos WHERE id = :todosId")
    suspend fun getTodosById(todosId: Int): Todos

    @Query("SELECT * FROM todos WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchTodos(query: String): List<Todos>

}