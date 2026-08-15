@file:OptIn(ExperimentalMaterial3Api::class)

package org.michaelbel.usecase.sample01_Room.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.michaelbel.usecase.Delete
import org.michaelbel.usecase.sample01_Room.intent.Sample01Intent
import org.michaelbel.usecase.sample01_Room.model.Sample01Model

@Composable
fun Sample01Screen(
    viewModel: Sample01ViewModel = hiltViewModel()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    Sample01ScreenContent(
        state = state,
        dispatch = viewModel::dispatch
    )
}

@Composable
private fun Sample01ScreenContent(
    state: Sample01Model,
    dispatch: (Sample01Intent) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Room") },
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
                    shapes = ListItemDefaults.segmentedShapes(index = 0, count = 3),
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 4.dp),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    ),
                    content = {
                        TextField(
                            value = state.titleInput,
                            onValueChange = { dispatch(Sample01Intent.TitleInputChanged(it)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(TextFieldDefaults.MinHeight),
                            label = { Text(text = "Заголовок") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Next) }
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                errorIndicatorColor = Color.Transparent
                            )
                        )
                    }
                )
            }
            item {
                SegmentedListItem(
                    shapes = ListItemDefaults.segmentedShapes(index = 1, count = 3),
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 4.dp),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    ),
                    content = {
                        TextField(
                            value = state.bodyInput,
                            onValueChange = { dispatch(Sample01Intent.BodyInputChanged(it)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(TextFieldDefaults.MinHeight),
                            label = { Text(text = "Текст заметки") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus() }
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                errorIndicatorColor = Color.Transparent
                            )
                        )
                    }
                )
            }
            item {
                SegmentedListItem(
                    onClick = { dispatch(Sample01Intent.SaveNote) },
                    shapes = ListItemDefaults.segmentedShapes(index = 2, count = 3),
                    enabled = state.isSaveEnabled,
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    ),
                    content = { Text(text = "Сохранить") }
                )
            }
            item {
                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
            itemsIndexed(state.noteEntities) { index, noteEntity ->
                SegmentedListItem(
                    shapes = ListItemDefaults.segmentedShapes(index = index, count = state.noteEntities.size),
                    overlineContent = { Text(text = noteEntity.title) },
                    trailingContent = {
                        IconButton(
                            onClick = { dispatch(Sample01Intent.DeleteNote(noteEntity.id)) }
                        ) {
                            Icon(
                                imageVector = Delete,
                                contentDescription = null
                            )
                        }
                    },
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    ),
                    content = { Text(text = noteEntity.body) }
                )
            }
        }
    }
}
