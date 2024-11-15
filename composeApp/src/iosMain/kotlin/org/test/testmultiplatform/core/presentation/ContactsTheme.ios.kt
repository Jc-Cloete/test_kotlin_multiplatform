package org.test.testmultiplatform.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.test.testmultiplatform.ui.theme.DarkColorScheme
import org.test.testmultiplatform.ui.theme.LightColorScheme
import org.test.testmultiplatform.ui.theme.Typography

@Composable
actual fun ContactsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}