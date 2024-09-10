package com.example.todolistapplication.uix.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import com.example.todolistapplication.data.entity.Todos
import com.example.todolistapplication.data.repository.TodosRepository

@HiltViewModel
class DetailViewModel @Inject constructor(var trepo: TodosRepository):ViewModel() {


    fun updateTodos(todos:Todos){
        CoroutineScope(Dispatchers.Main).launch {
            trepo.updateTodos(todos)
        }
    }
}