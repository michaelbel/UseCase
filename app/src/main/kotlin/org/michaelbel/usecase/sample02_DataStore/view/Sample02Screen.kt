@file:OptIn(ExperimentalMaterial3Api::class)

package org.michaelbel.usecase.sample02_DataStore.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.michaelbel.usecase.sample02_DataStore.intent.Sample02Intent
import org.michaelbel.usecase.sample02_DataStore.model.Sample02Model

@Composable
fun Sample02Screen(
    viewModel: Sample02ViewModel = hiltViewModel()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Sample02ScreenContent(
        state = state,
        dispatch = viewModel::dispatch
    )
}

@Composable
private fun Sample02ScreenContent(
    state: Sample02Model,
    dispatch: (Sample02Intent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "DataStore") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding + PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap)
        ) {
            item {
                SegmentedListItem(
                    checked = state.notificationsEnabled,
                    onCheckedChange = { dispatch(Sample02Intent.NotificationsEnabledChanged(it)) },
                    shapes = ListItemDefaults.segmentedShapes(index = 0, count = 3),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    ),
                    trailingContent = {
                        Switch(
                            checked = state.notificationsEnabled,
                            onCheckedChange = { dispatch(Sample02Intent.NotificationsEnabledChanged(it)) }
                        )
                    },
                    content = { Text(text = "Уведомления") }
                )
            }
            item {
                SegmentedListItem(
                    checked = state.darkThemeEnabled,
                    onCheckedChange = { dispatch(Sample02Intent.DarkThemeEnabledChanged(it)) },
                    shapes = ListItemDefaults.segmentedShapes(index = 1, count = 3),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    ),
                    trailingContent = {
                        Switch(
                            checked = state.darkThemeEnabled,
                            onCheckedChange = { dispatch(Sample02Intent.DarkThemeEnabledChanged(it)) }
                        )
                    },
                    content = { Text(text = "Тёмная тема") }
                )
            }
            item {
                SegmentedListItem(
                    checked = state.autoSyncEnabled,
                    onCheckedChange = { dispatch(Sample02Intent.AutoSyncEnabledChanged(it)) },
                    shapes = ListItemDefaults.segmentedShapes(index = 2, count = 3),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    ),
                    trailingContent = {
                        Switch(
                            checked = state.autoSyncEnabled,
                            onCheckedChange = { dispatch(Sample02Intent.AutoSyncEnabledChanged(it)) }
                        )
                    },
                    content = { Text(text = "Автосинхронизация") }
                )
            }
        }
    }
}
