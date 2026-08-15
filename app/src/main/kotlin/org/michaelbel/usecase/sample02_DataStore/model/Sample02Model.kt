package org.michaelbel.usecase.sample02_DataStore.model

import org.michaelbel.usecase.shared.mvi.Model

data class Sample02Model(
    val notificationsEnabled: Boolean = false,
    val darkThemeEnabled: Boolean = false,
    val autoSyncEnabled: Boolean = false
): Model
