package org.michaelbel.usecase.sample02_DataStore.intent

import org.michaelbel.usecase.shared.mvi.Intent

sealed interface Sample02Intent: Intent {
    data object LoadSettings: Sample02Intent
    data class NotificationsEnabledChanged(val enabled: Boolean): Sample02Intent
    data class DarkThemeEnabledChanged(val enabled: Boolean): Sample02Intent
    data class AutoSyncEnabledChanged(val enabled: Boolean): Sample02Intent
}
