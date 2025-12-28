package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.todoapp.data.repository.TodoRepositoryImpl
import com.example.todoapp.domain.usecase.GetTodosUseCase
import com.example.todoapp.presentation.TodoListScreen
import com.example.todoapp.presentation.TodoListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = TodoRepositoryImpl()
        val getTodosUseCase = GetTodosUseCase(repository)
        val viewModel = TodoListViewModel(getTodosUseCase)

        setContent {
            MaterialTheme {
                Surface {
                    TodoListScreen(viewModel = viewModel)
                }
            }
        }
    }
}