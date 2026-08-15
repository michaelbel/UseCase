package org.michaelbel.usecase.sample01_Room.model

import org.michaelbel.usecase.shared.room.entity.NoteEntity
import org.michaelbel.usecase.shared.mvi.Model

data class Sample01Model(
    val noteEntities: List<NoteEntity> = emptyList(),
    val titleInput: String = "",
    val bodyInput: String = ""
): Model {

    val isSaveEnabled: Boolean
        get() = titleInput.isNotBlank() && bodyInput.isNotBlank()
}
