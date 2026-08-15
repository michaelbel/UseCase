package org.michaelbel.usecase

import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable
import org.michaelbel.usecase.sample01_Room.view.Sample01Screen
import org.michaelbel.usecase.sample02_DataStore.view.Sample02Screen
import org.michaelbel.usecase.sample03_Ktor.view.Sample03Screen

@Serializable private data object HomeRoute: NavKey
@Serializable private data object RoomRoute: NavKey
@Serializable private data object DataStoreRoute: NavKey
@Serializable private data object KtorRoute: NavKey

@Composable
fun MainActivityContent() {
    val backStack = remember { mutableStateListOf<NavKey>(HomeRoute) }

    NavDisplay(
        backStack = backStack,
        modifier = Modifier.fillMaxSize(),
        onBack = { backStack.removeLastOrNull() },
        popTransitionSpec = { fadeIn() togetherWith fadeOut() using SizeTransform(clip = false) },
        predictivePopTransitionSpec = { fadeIn() togetherWith fadeOut() using SizeTransform(clip = false) },
        entryProvider = entryProvider {
            entry<HomeRoute> {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(R.string.app_name)) },
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
                                onClick = { backStack.add(RoomRoute) },
                                shapes = ListItemDefaults.segmentedShapes(index = 0, count = 3),
                                overlineContent = { Text(text = "Sample 01") },
                                colors = ListItemDefaults.segmentedColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                                ),
                                content = { Text(text = "Room") }
                            )
                        }
                        item {
                            SegmentedListItem(
                                onClick = { backStack.add(DataStoreRoute) },
                                shapes = ListItemDefaults.segmentedShapes(index = 1, count = 3),
                                overlineContent = { Text(text = "Sample 02") },
                                colors = ListItemDefaults.segmentedColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                                ),
                                content = { Text(text = "DataStore") }
                            )
                        }
                        item {
                            SegmentedListItem(
                                onClick = { backStack.add(KtorRoute) },
                                shapes = ListItemDefaults.segmentedShapes(index = 2, count = 3),
                                overlineContent = { Text(text = "Sample 03") },
                                colors = ListItemDefaults.segmentedColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                                ),
                                content = { Text(text = "Ktor") }
                            )
                        }
                    }
                }
            }
            entry<RoomRoute> { Sample01Screen() }
            entry<DataStoreRoute> { Sample02Screen() }
            entry<KtorRoute> { Sample03Screen() }
        }
    )
}
