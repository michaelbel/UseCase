package org.michaelbel.usecase.sample03_Ktor.intent

import org.michaelbel.usecase.shared.mvi.Intent

sealed interface Sample03Intent: Intent {
    data object LoadImage: Sample03Intent
}
