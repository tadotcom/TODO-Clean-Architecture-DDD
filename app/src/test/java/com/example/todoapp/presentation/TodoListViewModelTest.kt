package com.example.todoapp.presentation

import com.example.todoapp.MainDispatcherRule
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.usecase.GetTodosUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class TodoListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getTodosUseCase: GetTodosUseCase = mockk()

    @Test
    fun `init loads todos and updates state`() = runTest {

        val expectedTodos = listOf(Todo("1", "Test", false))
        coEvery { getTodosUseCase() } returns expectedTodos
        val viewModel = TodoListViewModel(getTodosUseCase)

        val currentState = viewModel.state.value
        assertThat(currentState.isLoading).isFalse()
        assertThat(currentState.todos).isEqualTo(expectedTodos)
        assertThat(currentState.error).isNull()
    }

    @Test
    fun `init sets error state when useCase throws exception`() = runTest {

        coEvery { getTodosUseCase() } throws RuntimeException("Network Error")
        val viewModel = TodoListViewModel(getTodosUseCase)

        val currentState = viewModel.state.value
        assertThat(currentState.isLoading).isFalse()
        assertThat(currentState.todos).isEmpty()
        assertThat(currentState.error).isEqualTo("Network Error")
    }
}