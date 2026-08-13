package org.michaelbel.usecase.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapperProvider
import org.michaelbel.usecase.ui.AppTheme

@Preview(name = "Default", showBackground = true)
@Preview(name = "Large font", showBackground = true, fontScale = 1.5f)
annotation class FontScaleHightPreviews

class ThemePreviewWrapper: PreviewWrapperProvider {

    @Composable
    override fun Wrap(content: @Composable () -> Unit) {
        AppTheme(content = content)
    }
}

