package org.michaelbel.usecase.sample02_DataStore.view

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.michaelbel.usecase.sample02_DataStore.SettingsFlowUseCase
import org.michaelbel.usecase.sample02_DataStore.ToggleSettingUseCase
import org.michaelbel.usecase.sample02_DataStore.intent.Sample02Intent
import org.michaelbel.usecase.sample02_DataStore.model.Sample02Model
import org.michaelbel.usecase.shared.datastore.PreferenceKey
import org.michaelbel.usecase.shared.mvi.MviViewModel

@HiltViewModel
class Sample02ViewModel @Inject constructor(
    private val settingsFlowUseCase: SettingsFlowUseCase,
    private val toggleSettingUseCase: ToggleSettingUseCase
): MviViewModel<Sample02Intent, Sample02Model>(Sample02Model()) {

    init {
        dispatch(Sample02Intent.LoadSettings)
    }

    override fun dispatch(intent: Sample02Intent) {
        when (intent) {
            is Sample02Intent.LoadSettings -> {
                launch {
                    settingsFlowUseCase(Unit).collectLatest { settings ->
                        reduce {
                            it.copy(
                                notificationsEnabled = settings.notificationsEnabled,
                                darkThemeEnabled = settings.darkThemeEnabled,
                                autoSyncEnabled = settings.autoSyncEnabled
                            )
                        }
                    }
                }
            }
            is Sample02Intent.NotificationsEnabledChanged -> {
                launch {
                    toggleSettingUseCase(
                        ToggleSettingUseCase.Params(
                            key = PreferenceKey.NotificationsEnabled,
                            enabled = intent.enabled
                        )
                    )
                }
            }
            is Sample02Intent.DarkThemeEnabledChanged -> {
                launch {
                    toggleSettingUseCase(
                        ToggleSettingUseCase.Params(
                            key = PreferenceKey.DarkThemeEnabled,
                            enabled = intent.enabled
                        )
                    )
                }
            }
            is Sample02Intent.AutoSyncEnabledChanged -> {
                launch {
                    toggleSettingUseCase(
                        ToggleSettingUseCase.Params(
                            key = PreferenceKey.AutoSyncEnabled,
                            enabled = intent.enabled
                        )
                    )
                }
            }
        }
    }
}
