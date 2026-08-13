package org.michaelbel.usecase.shared.domain.usecase

import javax.inject.Inject
import org.michaelbel.usecase.shared.data.InMemoryTaskDataSource
import org.michaelbel.usecase.shared.coroutines.SharedDispatchers
import org.michaelbel.usecase.shared.domain.model.DemoTask
import org.michaelbel.usecase.shared.domain.usecase.UseCase

class AddTaskUseCase @Inject constructor(
    private val taskDataSource: InMemoryTaskDataSource,
    dispatchers: SharedDispatchers
): UseCase<Unit, DemoTask>(dispatchers.io) {

    override suspend fun execute(params: Unit): DemoTask {
        return taskDataSource.addTask()
    }
}

