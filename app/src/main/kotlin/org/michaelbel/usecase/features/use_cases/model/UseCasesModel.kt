package org.michaelbel.usecase.features.use_cases.model

import kotlinx.coroutines.Job
import org.michaelbel.usecase.shared.mvi.Model
import org.michaelbel.usecase.shared.domain.model.DemoTask

data class UseCasesModel(
    val tasks: List<DemoTask> = emptyList(),
    val addTaskJob: Job? = null
): Model {

    val isInitialLoading: Boolean
        get() = tasks.isEmpty()

    val isAdding: Boolean
        get() = addTaskJob?.isActive == true

    val completedCount: Int
        get() = tasks.count { it.completed }
}

