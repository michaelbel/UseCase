package org.michaelbel.usecase.shared.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

sealed class PreferenceKey<T>(
    val preferenceKey: Preferences.Key<T>
) {
    data object NotificationsEnabled: PreferenceKey<Boolean>(booleanPreferencesKey("notifications_enabled"))
    data object DarkThemeEnabled: PreferenceKey<Boolean>(booleanPreferencesKey("dark_theme_enabled"))
    data object AutoSyncEnabled: PreferenceKey<Boolean>(booleanPreferencesKey("auto_sync_enabled"))
}
