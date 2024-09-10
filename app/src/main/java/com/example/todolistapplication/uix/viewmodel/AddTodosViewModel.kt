package com.example.todolistapplication.uix.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import com.example.todolistapplication.data.entity.Todos
import com.example.todolistapplication.data.repository.TodosRepository


@HiltViewModel
class AddTodosViewModel @Inject constructor(var trepo: TodosRepository) :ViewModel() {


    fun instertTodos(todos:Todos){
        CoroutineScope(Dispatchers.Main).launch {
            trepo.instertTodos(todos)
        }
    }
}