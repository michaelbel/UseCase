package org.michaelbel.usecase.shared.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import org.michaelbel.usecase.shared.data.InMemoryTaskDataSource
import org.michaelbel.usecase.shared.coroutines.SharedDispatchers
import org.michaelbel.usecase.shared.domain.model.DemoTask
import org.michaelbel.usecase.shared.domain.usecase.FlowUseCase

class DemoTasksFlowUseCase @Inject constructor(
    private val taskDataSource: InMemoryTaskDataSource,
    dispatchers: SharedDispatchers
): FlowUseCase<Unit, List<DemoTask>>(dispatchers.io) {

    override fun execute(params: Unit): Flow<List<DemoTask>> {
        return taskDataSource.tasks()
    }
}

