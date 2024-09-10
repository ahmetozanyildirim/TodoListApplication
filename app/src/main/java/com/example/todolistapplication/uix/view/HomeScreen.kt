package com.example.todolistapplication.uix.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolistapplication.data.entity.Todos
import com.example.todolistapplication.ui.theme.BackgroundColor
import com.example.todolistapplication.ui.theme.ButtonColor
import com.example.todolistapplication.ui.theme.CardBackgroundColor
import com.example.todolistapplication.ui.theme.PrimaryColor
import com.example.todolistapplication.ui.theme.RobotoFamily
import com.example.todolistapplication.ui.theme.TextColor1
import com.example.todolistapplication.uix.viewmodel.HomeViewModel
import com.google.gson.Gson
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {

    val todosList by homeViewModel.todosList.observeAsState(listOf())

    val searchText = remember { mutableStateOf("") }
    val isSearching = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        homeViewModel.loadTodos()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Todos",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF2196F3),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        isSearching.value = !isSearching.value
                        if (!isSearching.value) {
                            searchText.value = ""
                            homeViewModel.loadTodos()
                        }
                    }) {
                        Icon(
                            imageVector = if (isSearching.value) Icons.Default.Close else Icons.Default.Search,
                            contentDescription = if (isSearching.value) "Close" else "Search",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addTodosScreen") },
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Todo", tint = Color.White)
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF0F0F0))
                    .padding(paddingValues)
            ) {
                if (isSearching.value) {
                    TextField(
                        value = searchText.value,
                        onValueChange = {
                            searchText.value = it
                            homeViewModel.searchTodos(it)
                        },
                        placeholder = {
                            Text(text = "Search a Todo", color = Color.Gray)
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color(0xFF2196F3),
                            unfocusedIndicatorColor = Color.Gray,
                            cursorColor = Color(0xFF2196F3)
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                TodosList(
                    todosList = todosList,
                    onTodosClick = { todos ->
                        val todosJson = Gson().toJson(todos)
                        navController.navigate("detailScreen/$todosJson")
                    },
                    onTodosDelete = { todos -> homeViewModel.deleteTodos(todos) }
                )
            }
        }
    )
}

@Composable
fun TodosList(
    todosList: List<Todos>,
    onTodosClick: (Todos) -> Unit,
    onTodosDelete: (Todos) -> Unit
) {
    if (todosList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "There is no todo!",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(todosList) { todos ->
                TodosItem(
                    todos = todos,
                    onTodosClick = { onTodosClick(todos) },
                    onTodosDelete = { onTodosDelete(todos) }
                )
            }
        }
    }
}

