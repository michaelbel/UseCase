package org.michaelbel.usecase.sample02_DataStore

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.michaelbel.usecase.shared.coroutines.AppDispatchers
import org.michaelbel.usecase.shared.datastore.PreferenceKey
import org.michaelbel.usecase.shared.datastore.SettingsDataStore
import org.michaelbel.usecase.shared.usecase.FlowUseCase

class SettingsFlowUseCase @Inject constructor(
    private val settingsDataStore: SettingsDataStore,
    dispatchers: AppDispatchers
): FlowUseCase<Unit, SettingsFlowUseCase.Settings>(dispatchers.io) {

    override fun execute(params: Unit): Flow<Settings> {
        return combine(
            settingsDataStore.getValueFlow(PreferenceKey.NotificationsEnabled),
            settingsDataStore.getValueFlow(PreferenceKey.DarkThemeEnabled),
            settingsDataStore.getValueFlow(PreferenceKey.AutoSyncEnabled)
        ) { notificationsEnabled, darkThemeEnabled, autoSyncEnabled ->
            Settings(
                notificationsEnabled = notificationsEnabled ?: false,
                darkThemeEnabled = darkThemeEnabled ?: false,
                autoSyncEnabled = autoSyncEnabled ?: false
            )
        }
    }

    data class Settings(
        val notificationsEnabled: Boolean,
        val darkThemeEnabled: Boolean,
        val autoSyncEnabled: Boolean
    )
}
