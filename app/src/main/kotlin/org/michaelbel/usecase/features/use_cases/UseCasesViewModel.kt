package org.michaelbel.usecase.features.use_cases

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.michaelbel.usecase.features.use_cases.event.UseCasesEvent
import org.michaelbel.usecase.features.use_cases.intent.UseCasesIntent
import org.michaelbel.usecase.features.use_cases.model.UseCasesModel
import org.michaelbel.usecase.shared.mvi.MviViewModel
import org.michaelbel.usecase.shared.domain.usecase.AddTaskUseCase
import org.michaelbel.usecase.shared.domain.usecase.DemoTasksFlowUseCase
import org.michaelbel.usecase.shared.domain.usecase.ToggleTaskUseCase

@HiltViewModel
class UseCasesViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val demoTasksFlowUseCase: DemoTasksFlowUseCase,
    private val toggleTaskUseCase: ToggleTaskUseCase
): MviViewModel<UseCasesIntent, UseCasesModel, UseCasesEvent>(UseCasesModel()) {

    init {
        dispatch(UseCasesIntent.CollectTasks)
    }

    override fun dispatch(intent: UseCasesIntent) {
        when (intent) {
            is UseCasesIntent.CollectTasks -> {
                launch {
                    demoTasksFlowUseCase(Unit).collectLatest { tasks ->
                        reduce { it.copy(tasks = tasks) }
                    }
                }
            }

            is UseCasesIntent.AddTask -> {
                if (stateFlow.value.addTaskJob?.isActive == true) return

                val job = launch {
                    val task = addTaskUseCase(Unit).getOrThrow()
                    send(UseCasesEvent.TaskAdded(task.id))
                }
                reduce { it.copy(addTaskJob = job) }
                job.invokeOnCompletion {
                    reduce { model ->
                        when (model.addTaskJob) {
                            job -> model.copy(addTaskJob = null)
                            else -> model
                        }
                    }
                }
            }

            is UseCasesIntent.ToggleTask -> {
                launch {
                    toggleTaskUseCase(intent.taskId).getOrThrow()
                }
            }
        }
    }

    override fun catch(throwable: Throwable) {
        reduce { it.copy(addTaskJob = null) }
        launch { send(UseCasesEvent.UseCaseFailed) }
        super.catch(throwable)
    }
}

