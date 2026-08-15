@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package org.michaelbel.usecase.sample01_Room

import javax.inject.Inject
import org.michaelbel.usecase.shared.coroutines.AppDispatchers
import org.michaelbel.usecase.shared.room.dao.NoteDao
import org.michaelbel.usecase.shared.usecase.UseCase

class DeleteNoteUseCase @Inject constructor(
    private val noteDao: NoteDao,
    dispatchers: AppDispatchers
): UseCase<Int, Unit>(dispatchers.io) {

    override suspend fun execute(noteId: Int) {
        noteDao.delete(noteId)
    }
}
