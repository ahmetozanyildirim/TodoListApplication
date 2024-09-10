package com.example.todolistapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "todos")
data class Todos(


        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") @NotNull var id: Int,

        @ColumnInfo(name = "title") @NotNull var title: String,

        @ColumnInfo(name = "description") @NotNull var description: String,

){

}