package com.example.todolistapplication.data.repository

import com.example.todolistapplication.data.datasource.TodosDataSource
import com.example.todolistapplication.data.entity.Todos
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TodosRepository @Inject constructor(var tds: TodosDataSource) {

    suspend fun instertTodos(todos: Todos) = tds.instertTodos(todos)

    suspend fun updateTodos(todos: Todos) = tds.updateTodos(todos)

    suspend fun deleteTodos(todos: Todos) = tds.deleteTodos(todos)

    suspend fun getAllTodos(): List<Todos> = tds.getAllTodos()

    suspend fun searchTodos(query: String): List<Todos> = tds.searchTodos(query)
}