package org.michaelbel.usecase.features.use_cases.intent

import org.michaelbel.usecase.shared.mvi.Intent

sealed interface UseCasesIntent: Intent {
    data object CollectTasks: UseCasesIntent
    data object AddTask: UseCasesIntent
    data class ToggleTask(val taskId: Int): UseCasesIntent
}

