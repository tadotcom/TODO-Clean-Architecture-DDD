package com.example.todoapp.domain.usecase

import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetTodosUseCaseTest {

    private val repository: TodoRepository = mockk()
    private val useCase = GetTodosUseCase(repository)

    @Test
    fun `invoke returns sorted list by completion status`() = runTest {

        val todo1 = Todo("1", "Task 1", true)
        val todo2 = Todo("2", "Task 2", false)

        coEvery { repository.getTodos() } returns listOf(todo1, todo2)

        val result = useCase()


        assertThat(result).hasSize(2)
        assertThat(result[0]).isEqualTo(todo2)
        assertThat(result[1]).isEqualTo(todo1)
    }
}