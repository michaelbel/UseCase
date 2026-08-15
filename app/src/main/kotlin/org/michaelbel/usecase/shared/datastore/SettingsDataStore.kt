package org.michaelbel.usecase.shared.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    fun <T> getValueFlow(key: PreferenceKey<T>): Flow<T?> {
        return dataStore.data.map { preferences -> preferences[key.preferenceKey] }
    }

    suspend fun <T> setValue(key: PreferenceKey<T>, value: T) {
        dataStore.edit { preferences -> preferences[key.preferenceKey] = value }
    }
}
