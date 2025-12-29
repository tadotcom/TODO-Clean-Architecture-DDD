package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.data.repository.TodoRepositoryImpl
import com.example.todoapp.domain.usecase.GetTodosUseCase
import com.example.todoapp.presentation.TodoListScreen
import com.example.todoapp.presentation.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    val viewModel: TodoListViewModel = hiltViewModel()
                    TodoListScreen(viewModel = viewModel)
                }
            }
        }
    }
}