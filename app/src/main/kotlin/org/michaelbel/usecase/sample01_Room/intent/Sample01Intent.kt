package org.michaelbel.usecase.sample01_Room.intent

import org.michaelbel.usecase.shared.mvi.Intent

sealed interface Sample01Intent: Intent {
    data object LoadNotes: Sample01Intent
    data object SaveNote: Sample01Intent
    data class TitleInputChanged(val title: String): Sample01Intent
    data class BodyInputChanged(val body: String): Sample01Intent
    data class DeleteNote(val id: Int): Sample01Intent
}
