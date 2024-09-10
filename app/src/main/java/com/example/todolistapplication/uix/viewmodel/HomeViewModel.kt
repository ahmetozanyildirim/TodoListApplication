package com.example.todolistapplication.uix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapplication.data.entity.Todos
import com.example.todolistapplication.data.repository.TodosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var trepo:TodosRepository):ViewModel() {

    var todosList= MutableLiveData<List<Todos>>()

    init{
        loadTodos()
    }

    fun loadTodos(){
        CoroutineScope(Dispatchers.Main).launch {
            todosList.value = trepo.getAllTodos()
        }
    }

    fun searchTodos(query:String) {
        CoroutineScope(Dispatchers.Main).launch {
            todosList.value = trepo.searchTodos(query)
        }
    }

    fun deleteTodos(todos: Todos){
        CoroutineScope(Dispatchers.Main).launch {
            trepo.deleteTodos(todos)
            loadTodos()
        }
    }


}