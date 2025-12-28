package com.example.todoapp.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.usecase.GetTodosUseCase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class TodoListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val getTodosUseCase: GetTodosUseCase = mockk()

    @Test
    fun showLoadingIndicator_when_loading() {
        coEvery { getTodosUseCase() } returns emptyList()
        val viewModel = TodoListViewModel(getTodosUseCase)

        composeTestRule.setContent {
            TodoListScreen(viewModel = viewModel)
        }
    }

    @Test
    fun showTodoList_after_loading() {
        val todos = listOf(
            Todo("1", "Buy Milk", false),
            Todo("2", "Walk Dog", true)
        )
        coEvery { getTodosUseCase() } returns todos
        val viewModel = TodoListViewModel(getTodosUseCase)
        
        composeTestRule.setContent {
            TodoListScreen(viewModel = viewModel)
        }
        
        composeTestRule.onNodeWithTag("TodoList").assertIsDisplayed()
        composeTestRule.onNodeWithText("Buy Milk").assertIsDisplayed()
        composeTestRule.onNodeWithText("Walk Dog").assertIsDisplayed()
    }
}