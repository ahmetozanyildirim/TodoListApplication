package com.example.todolistapplication.uix.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolistapplication.data.entity.Todos
import com.example.todolistapplication.uix.viewmodel.AddTodosViewModel
import com.example.todolistapplication.uix.viewmodel.DetailViewModel
import com.example.todolistapplication.uix.viewmodel.HomeViewModel
import com.google.gson.Gson

@Composable
fun PageTransitions(
    homeViewModel: HomeViewModel,
    addTodosViewModel: AddTodosViewModel,
    detailViewModel: DetailViewModel
) {

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "homeScreen") {

        composable("homeScreen") {
            HomeScreen(navController, homeViewModel)
        }

        composable("addTodosScreen") {
            AddTodosScreen(navController, addTodosViewModel)
        }

        composable(
            "detailScreen/{todos}",
            arguments = listOf(
                navArgument("todos") {
                    type = NavType.StringType
                }
            )
        ) {

            val json = it.arguments?.getString("todos")
            val todosObject = Gson().fromJson(json, Todos::class.java)


            DetailScreen(navController, detailViewModel, todosObject)
        }
    }
}