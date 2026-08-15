package org.michaelbel.usecase.sample02_DataStore

import javax.inject.Inject
import org.michaelbel.usecase.shared.coroutines.AppDispatchers
import org.michaelbel.usecase.shared.datastore.PreferenceKey
import org.michaelbel.usecase.shared.datastore.SettingsDataStore
import org.michaelbel.usecase.shared.usecase.UseCase

class ToggleSettingUseCase @Inject constructor(
    private val settingsDataStore: SettingsDataStore,
    dispatchers: AppDispatchers
): UseCase<ToggleSettingUseCase.Params, Unit>(dispatchers.io) {

    override suspend fun execute(params: Params) {
        settingsDataStore.setValue(params.key, params.enabled)
    }

    data class Params(
        val key: PreferenceKey<Boolean>,
        val enabled: Boolean
    )
}
