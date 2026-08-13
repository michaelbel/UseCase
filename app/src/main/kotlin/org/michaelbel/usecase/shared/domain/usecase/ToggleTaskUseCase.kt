@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package org.michaelbel.usecase.shared.domain.usecase

import javax.inject.Inject
import org.michaelbel.usecase.shared.data.InMemoryTaskDataSource
import org.michaelbel.usecase.shared.coroutines.SharedDispatchers
import org.michaelbel.usecase.shared.domain.usecase.UseCase

class ToggleTaskUseCase @Inject constructor(
    private val taskDataSource: InMemoryTaskDataSource,
    dispatchers: SharedDispatchers
): UseCase<Int, Unit>(dispatchers.io) {

    override suspend fun execute(taskId: Int) {
        taskDataSource.toggleTask(taskId)
    }
}

