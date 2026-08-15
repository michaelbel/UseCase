package org.michaelbel.usecase.sample03_Ktor.view

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import org.michaelbel.usecase.sample03_Ktor.FetchRandomDogImageUseCase
import org.michaelbel.usecase.sample03_Ktor.intent.Sample03Intent
import org.michaelbel.usecase.sample03_Ktor.model.Sample03Model
import org.michaelbel.usecase.shared.mvi.MviViewModel

@HiltViewModel
class Sample03ViewModel @Inject constructor(
    private val fetchRandomDogImageUseCase: FetchRandomDogImageUseCase
): MviViewModel<Sample03Intent, Sample03Model>(Sample03Model()) {

    override fun dispatch(intent: Sample03Intent) {
        when (intent) {
            is Sample03Intent.LoadImage -> {
                if (stateFlow.value.isLoading) return

                val job = launch {
                    fetchRandomDogImageUseCase(Unit)
                        .onSuccess { url ->
                            reduce { it.copy(imageUrl = url, errorMessage = null) }
                        }
                        .onFailure { throwable ->
                            reduce { it.copy(errorMessage = throwable.message, imageUrl = null) }
                        }
                }.also { launchedJob ->
                    launchedJob.invokeOnCompletion { reduce { it.copy(loadJob = null) } }
                }
                reduce { it.copy(loadJob = job) }
            }
        }
    }
}
