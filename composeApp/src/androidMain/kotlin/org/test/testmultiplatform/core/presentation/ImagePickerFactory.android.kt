package org.test.testmultiplatform.core.presentation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.test.testmultiplatform.contacts.presentation.ImagePicker

actual class ImagePickerFactory {
    @Composable
    actual fun createPicker(): ImagePicker {
        val activity = LocalContext.current as ComponentActivity
        return remember(activity) {
            ImagePicker(activity)
        }
    }
}