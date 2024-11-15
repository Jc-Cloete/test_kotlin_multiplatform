package org.test.testmultiplatform.core.presentation

import androidx.compose.runtime.Composable
import org.test.testmultiplatform.contacts.presentation.ImagePicker

actual class ImagePickerFactory {
    @Composable
    actual fun createPicker(): ImagePicker {
        return ImagePicker()
    }
}