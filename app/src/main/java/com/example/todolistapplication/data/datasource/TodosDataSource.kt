package com.example.todolistapplication.data.datasource

import com.example.todolistapplication.data.entity.Todos
import com.example.todolistapplication.room.TodosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodosDataSource(val tds:TodosDao) {


    suspend fun updateTodos(todos: Todos){
        tds.updateTodos(todos)
    }

    suspend fun instertTodos(todos: Todos){
        tds.instertTodos(todos)
    }

    suspend fun getAllTodos():List<Todos> = withContext(Dispatchers.IO){
        return@withContext tds.getAllTodos()
    }

    suspend fun deleteTodos(todos: Todos){
        tds.deleteTodos(todos)
    }

    suspend fun searchTodos(query: String): List<Todos> = withContext(Dispatchers.IO){
        return@withContext tds.searchTodos(query)
    }

}