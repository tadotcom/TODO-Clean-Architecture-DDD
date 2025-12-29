package com.example.todoapp.di

import com.example.todoapp.data.repository.TodoRepositoryImpl
import com.example.todoapp.domain.repository.TodoRepository
import com.example.todoapp.domain.usecase.GetTodosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoRepository(): TodoRepository {
        return TodoRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideGetTodosUseCase(repository: TodoRepository): GetTodosUseCase {
        return GetTodosUseCase(repository)
    }
}