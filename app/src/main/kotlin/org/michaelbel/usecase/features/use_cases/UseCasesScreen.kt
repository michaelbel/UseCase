@file:OptIn(ExperimentalMaterial3Api::class)

package org.michaelbel.usecase.features.use_cases

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.PreviewWrapper
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.michaelbel.usecase.features.use_cases.event.UseCasesEvent
import org.michaelbel.usecase.features.use_cases.intent.UseCasesIntent
import org.michaelbel.usecase.features.use_cases.model.UseCasesModel
import org.michaelbel.usecase.shared.mvi.ObserveAsEvents
import org.michaelbel.usecase.ui.preview.FontScaleHightPreviews
import org.michaelbel.usecase.ui.preview.ThemePreviewWrapper
import org.michaelbel.usecase.ui.rememberAppStrings
import org.michaelbel.usecase.shared.domain.model.DemoTask

@Composable
fun UseCasesScreen(
    viewModel: UseCasesViewModel = hiltViewModel()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    ObserveAsEvents(
        flow = viewModel.eventFlow
    ) { event ->
        snackbarHostState.currentSnackbarData?.dismiss()
        scope.launch {
            snackbarHostState.showSnackbar(
                message = when (event) {
                    is UseCasesEvent.UseCaseFailed -> "UseCase завершился с ошибкой"
                    is UseCasesEvent.TaskAdded -> "UseCase добавил задачу #${event.taskId}"
                }
            )
        }
    }

    UseCasesScreenContent(
        state = state,
        snackbarHostState = snackbarHostState,
        dispatch = viewModel::dispatch
    )
}

@Composable
private fun UseCasesScreenContent(
    state: UseCasesModel,
    snackbarHostState: SnackbarHostState,
    dispatch: (UseCasesIntent) -> Unit
) {
    val strings = rememberAppStrings()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = strings.appName
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = "Выполнить UseCase"
                    )
                },
                icon = {
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                onClick = { dispatch(UseCasesIntent.AddTask) },
                modifier = Modifier.padding(horizontal = 16.dp),
                expanded = !state.isAdding
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        when {
            state.isInitialLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            space = 16.dp,
                            alignment = Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()

                        Text(
                            text = "Подключаем FlowUseCase…",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = innerPadding + PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 96.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        ListItem(
                            headlineContent = {
                                Text(
                                    text = "UseCase<Unit, DemoTask>",
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            supportingContent = {
                                Text(
                                    text = "Одноразовая suspend-операция. Кнопка запускает invoke(Unit), результат извлекается через getOrThrow()."
                                )
                            },
                            trailingContent = {
                                when {
                                    state.isAdding -> CircularProgressIndicator()
                                    else -> Text(text = "ГОТОВ")
                                }
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                headlineColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                supportingColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                trailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                    item {
                        ListItem(
                            headlineContent = {
                                Text(
                                    text = "FlowUseCase<Unit, List<DemoTask>>",
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            supportingContent = {
                                Text(
                                    text = "Поток собирается через collectLatest и автоматически обновляет MVI-модель."
                                )
                            },
                            trailingContent = {
                                Text(
                                    text = "FLOW"
                                )
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                headlineColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                supportingColor = MaterialTheme.colorScheme.onSecondaryContainer,
                                trailingIconColor = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        )
                    }
                    item {
                        HorizontalDivider()

                        ListItem(
                            headlineContent = {
                                Text(
                                    text = "Задачи: ${state.tasks.size} · выполнено: ${state.completedCount}",
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            supportingContent = {
                                Text(
                                    text = "Поток собирается через collectLatest и автоматически обновляет MVI-модель."
                                )
                            }
                        )
                    }
                    items(
                        items = state.tasks,
                        key = { task -> task.id }
                    ) { task ->
                        ListItem(
                            headlineContent = {
                                Text(
                                    text = "Демо-задача #${task.id}",
                                    fontWeight = FontWeight.SemiBold
                                )
                            },
                            modifier = Modifier.clickable {
                                dispatch(UseCasesIntent.ToggleTask(task.id))
                            },
                            supportingContent = {
                                val status = when (task.completed) {
                                    true -> "выполнена"
                                    false -> "ожидает выполнения"
                                }
                                Text(
                                    text = "Поколение ${task.generation} · $status"
                                )
                            },
                            trailingContent = {
                                Checkbox(
                                    checked = task.completed,
                                    onCheckedChange = {
                                        dispatch(UseCasesIntent.ToggleTask(task.id))
                                    }
                                )
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainer
                            )
                        )
                    }
                }
            }
        }
    }
}

@PreviewWrapper(ThemePreviewWrapper::class)
@FontScaleHightPreviews
@Composable
private fun UseCasesScreenContentPreview(
    @PreviewParameter(UseCasesModelPreviewParameterProvider::class)
    state: UseCasesModel
) {
    UseCasesScreenContent(
        state = state,
        snackbarHostState = remember { SnackbarHostState() },
        dispatch = {}
    )
}

private class UseCasesModelPreviewParameterProvider: PreviewParameterProvider<UseCasesModel> {

    override val values: Sequence<UseCasesModel> = sequenceOf(
        UseCasesModel(),
        UseCasesModel(
            tasks = List(6) { index ->
                DemoTask(
                    id = index + 1,
                    generation = index / 3,
                    completed = index == 1
                )
            }
        ),
        UseCasesModel(
            tasks = List(6) { index ->
                DemoTask(
                    id = index + 1,
                    generation = index / 3,
                    completed = index == 1
                )
            },
            addTaskJob = Job()
        )
    )
}
