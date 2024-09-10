package com.example.todolistapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolistapplication.ui.theme.TodoListApplicationTheme
import com.example.todolistapplication.uix.view.PageTransitions
import com.example.todolistapplication.uix.viewmodel.AddTodosViewModel
import com.example.todolistapplication.uix.viewmodel.DetailViewModel
import com.example.todolistapplication.uix.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val homeViewModel: HomeViewModel by viewModels()
    val addTodosViewModel : AddTodosViewModel by viewModels()
    val detailViewModel : DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListApplicationTheme {
                PageTransitions(homeViewModel,addTodosViewModel,detailViewModel)

            }
        }
    }
}
