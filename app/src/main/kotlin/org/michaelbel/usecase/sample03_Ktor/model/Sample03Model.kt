package org.michaelbel.usecase.sample03_Ktor.model

import kotlinx.coroutines.Job
import org.michaelbel.usecase.shared.mvi.Model

data class Sample03Model(
    val imageUrl: String? = null,
    val errorMessage: String? = null,
    val loadJob: Job? = null
): Model {

    val isLoading: Boolean
        get() = loadJob?.isActive == true
}
