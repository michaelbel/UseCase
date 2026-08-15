package org.michaelbel.usecase.sample01_Room

import javax.inject.Inject
import org.michaelbel.usecase.shared.coroutines.AppDispatchers
import org.michaelbel.usecase.shared.room.dao.NoteDao
import org.michaelbel.usecase.shared.room.entity.NoteEntity
import org.michaelbel.usecase.shared.usecase.UseCase

class InsertNoteUseCase @Inject constructor(
    private val noteDao: NoteDao,
    dispatchers: AppDispatchers
): UseCase<InsertNoteUseCase.Params, Unit>(dispatchers.io) {

    override suspend fun execute(params: Params) {
        val noteEntity = NoteEntity(
            title = params.title,
            body = params.body
        )
        noteDao.upsert(noteEntity)
    }

    data class Params(
        val title: String,
        val body: String
    )
}
