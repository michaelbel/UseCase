package org.michaelbel.usecase.ui

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalResources
import org.michaelbel.usecase.R

class AppStrings(
    private val resources: Resources
) {

    val appName: String
        get() = resources.getString(R.string.app_name)

    val useCaseTitle: String
        get() = resources.getString(R.string.use_case_title)

    val useCaseDescription: String
        get() = resources.getString(R.string.use_case_description)

    val flowUseCaseTitle: String
        get() = resources.getString(R.string.flow_use_case_title)

    val flowUseCaseDescription: String
        get() = resources.getString(R.string.flow_use_case_description)

    val ready: String
        get() = resources.getString(R.string.ready)

    val observing: String
        get() = resources.getString(R.string.observing)

    val runUseCase: String
        get() = resources.getString(R.string.run_use_case)

    val initialLoading: String
        get() = resources.getString(R.string.initial_loading)

    val useCaseFailed: String
        get() = resources.getString(R.string.use_case_failed)

    fun summary(total: Int, completed: Int): String {
        return resources.getString(R.string.summary, total, completed)
    }

    fun taskTitle(taskId: Int): String {
        return resources.getString(R.string.task_title, taskId)
    }

    fun taskSubtitle(generation: Int, completed: Boolean): String {
        val status = when (completed) {
            true -> resources.getString(R.string.task_status_completed)
            false -> resources.getString(R.string.task_status_pending)
        }
        return resources.getString(R.string.task_subtitle, generation, status)
    }

    fun taskAdded(taskId: Int): String {
        return resources.getString(R.string.task_added, taskId)
    }
}

@Composable
fun rememberAppStrings(): AppStrings {
    val resources = LocalResources.current
    return remember(resources) { AppStrings(resources) }
}
