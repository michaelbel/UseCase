package org.michaelbel.usecase.features.use_cases.event

import org.michaelbel.usecase.shared.mvi.Event

sealed interface UseCasesEvent: Event {
    data object UseCaseFailed: UseCasesEvent
    data class TaskAdded(val taskId: Int): UseCasesEvent
}

