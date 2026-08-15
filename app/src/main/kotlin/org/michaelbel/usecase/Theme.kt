package org.michaelbel.usecase

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.expressiveLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val darkColorScheme = darkColorScheme().copy(
        background = Color(0xFF000000),
        secondary = Color(0xFF0D0D0D),
        surfaceContainerHighest = Color(0xFF212121)
    )
    val expressiveLightColorScheme = expressiveLightColorScheme().copy(
        background = Color(0xFFF0F0F0),
        secondary = Color(0xFFF8F8F8),
        surfaceContainerHighest = Color(0xFFFFFFFF)
    )

    MaterialExpressiveTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme else expressiveLightColorScheme,
        content = content
    )
}

private var _delete: ImageVector? = null
val Delete: ImageVector
    get() {
        _delete?.let {
            return it
        }
        val delete = ImageVector.Builder(
            name = "Delete",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(6f, 19f)
                curveToRelative(0f, 1.1f, 0.9f, 2f, 2f, 2f)
                horizontalLineToRelative(8f)
                curveToRelative(1.1f, 0f, 2f, -0.9f, 2f, -2f)
                verticalLineTo(7f)
                horizontalLineTo(6f)
                verticalLineToRelative(12f)
                close()
                moveTo(19f, 4f)
                horizontalLineToRelative(-3.5f)
                lineToRelative(-1f, -1f)
                horizontalLineToRelative(-5f)
                lineToRelative(-1f, 1f)
                horizontalLineTo(5f)
                verticalLineToRelative(2f)
                horizontalLineToRelative(14f)
                verticalLineTo(4f)
                close()
            }
        }.build()
        _delete = delete
        return delete
    }
