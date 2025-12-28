package com.example.todoapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun TodoListScreen(viewModel: TodoListViewModel) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.testTag("LoadingIndicator"))
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize().testTag("TodoList")) {
            items(state.todos) { todo ->
                TodoItem(title = todo.title, isCompleted = todo.isCompleted)
            }
        }
    }
}

@Composable
fun TodoItem(title: String, isCompleted: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = if (isCompleted) "✅" else "⬜")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title)
        }
    }
}