package com.example.todolistapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistapplication.data.entity.Todos

@Database(entities = [Todos::class], version = 1)
abstract class TodosDatabase : RoomDatabase() {
    abstract fun todosDao(): TodosDao
}