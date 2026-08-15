package org.michaelbel.usecase.shared.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    val io: CoroutineDispatcher
    val immediate: CoroutineDispatcher
}
