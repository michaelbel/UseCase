package org.michaelbel.usecase.shared.data

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.michaelbel.usecase.shared.domain.model.DemoTask

@Singleton
class InMemoryTaskDataSource @Inject constructor() {

    private val taskFlow = MutableStateFlow(
        List(5) { index ->
            DemoTask(
                id = index + 1,
                generation = 0,
                completed = index == 1
            )
        }
    )

    fun tasks(): Flow<List<DemoTask>> {
        return taskFlow.asStateFlow()
    }

    suspend fun addTask(): DemoTask {
        delay(900L)
        val generation = taskFlow.value.maxOfOrNull { it.generation }?.plus(1) ?: 1
        val task = DemoTask(
            id = taskFlow.value.maxOfOrNull { it.id }?.plus(1) ?: 1,
            generation = generation,
            completed = false
        )
        taskFlow.update { tasks -> tasks + task }
        return task
    }

    fun toggleTask(taskId: Int) {
        taskFlow.update { tasks ->
            tasks.map { task ->
                when (task.id) {
                    taskId -> task.copy(completed = !task.completed)
                    else -> task
                }
            }
        }
    }
}

