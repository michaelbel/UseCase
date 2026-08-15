package org.michaelbel.usecase.sample01_Room.view

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.michaelbel.usecase.sample01_Room.DeleteNoteUseCase
import org.michaelbel.usecase.sample01_Room.InsertNoteUseCase
import org.michaelbel.usecase.sample01_Room.NoteEntitiesFlowUseCase
import org.michaelbel.usecase.sample01_Room.intent.Sample01Intent
import org.michaelbel.usecase.sample01_Room.model.Sample01Model
import org.michaelbel.usecase.shared.mvi.MviViewModel

@HiltViewModel
class Sample01ViewModel @Inject constructor(
    private val noteEntitiesFlowUseCase: NoteEntitiesFlowUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): MviViewModel<Sample01Intent, Sample01Model>(Sample01Model()) {

    init {
        dispatch(Sample01Intent.LoadNotes)
    }

    override fun dispatch(intent: Sample01Intent) {
        when (intent) {
            is Sample01Intent.LoadNotes -> {
                launch {
                    noteEntitiesFlowUseCase(Unit).collectLatest { noteEntities ->
                        reduce { it.copy(noteEntities = noteEntities) }
                    }
                }
            }
            is Sample01Intent.TitleInputChanged -> reduce { it.copy(titleInput = intent.title) }
            is Sample01Intent.BodyInputChanged -> reduce { it.copy(bodyInput = intent.body) }
            is Sample01Intent.SaveNote -> {
                launch {
                    insertNoteUseCase(
                        InsertNoteUseCase.Params(
                            title = stateFlow.value.titleInput,
                            body = stateFlow.value.bodyInput
                        )
                    )
                    reduce { it.copy(titleInput = "", bodyInput = "") }
                }
            }
            is Sample01Intent.DeleteNote -> launch { deleteNoteUseCase(intent.id) }
        }
    }
}
