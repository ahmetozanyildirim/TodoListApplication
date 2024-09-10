package com.example.todolistapplication.di

import android.content.Context
import androidx.room.Room
import com.example.todolistapplication.data.datasource.TodosDataSource
import com.example.todolistapplication.data.repository.TodosRepository
import com.example.todolistapplication.room.TodosDao
import com.example.todolistapplication.room.TodosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodosDataSource(tdao:TodosDao):TodosDataSource{
        return TodosDataSource(tdao)
    }


    @Provides
    @Singleton
    fun provideTodosRepository(tds:TodosDataSource):TodosRepository{
        return TodosRepository(tds)
    }

    @Provides
    @Singleton
    fun proivideTodosDao(@ApplicationContext appContext:Context):TodosDao{
        val vt =Room
            .databaseBuilder(
                context =appContext,
                TodosDatabase::class.java,
                "todos.sqlite")
            .createFromAsset("todos.sqlite")
            .build()
        return vt.todosDao()
    }

}