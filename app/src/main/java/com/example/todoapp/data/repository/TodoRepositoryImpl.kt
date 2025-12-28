package com.example.todoapp.data.repository

import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.delay
import java.util.UUID

class TodoRepositoryImpl : TodoRepository {

    private val todos = mutableListOf(
        Todo("1", "DDDを学ぶ", false),
        Todo("2", "Composeを書く", true)
    )

    override suspend fun getTodos(): List<Todo> {
        delay(500) // 通信ラグのシミュレーション
        return todos
    }

    override suspend fun addTodo(title: String) {
        delay(300)
        todos.add(Todo(UUID.randomUUID().toString(), title, false))
    }
}