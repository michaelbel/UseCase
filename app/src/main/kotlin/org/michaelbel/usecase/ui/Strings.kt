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
}

@Composable
fun rememberAppStrings(): AppStrings {
    val resources = LocalResources.current
    return remember(resources) { AppStrings(resources) }
}
