package org.michaelbel.usecase.sample01_Room

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import org.michaelbel.usecase.shared.coroutines.AppDispatchers
import org.michaelbel.usecase.shared.room.dao.NoteDao
import org.michaelbel.usecase.shared.room.entity.NoteEntity
import org.michaelbel.usecase.shared.usecase.FlowUseCase

class NoteEntitiesFlowUseCase @Inject constructor(
    private val noteDao: NoteDao,
    dispatchers: AppDispatchers
): FlowUseCase<Unit, List<NoteEntity>>(dispatchers.io) {

    override fun execute(params: Unit): Flow<List<NoteEntity>> {
        return noteDao.selectAllFlow()
    }
}
