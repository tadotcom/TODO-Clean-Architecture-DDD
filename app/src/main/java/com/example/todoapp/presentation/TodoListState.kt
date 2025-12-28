package com.example.todoapp.presentation

import com.example.todoapp.domain.model.Todo

data class TodoListState(
    val isLoading: Boolean = false,
    val todos: List<Todo> = emptyList(),
    val error: String? = null
)
