package org.test.testmultiplatform.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.test.testmultiplatform.contacts.presentation.ImagePicker

actual class ImagePickerFactory(
    private val rootController: UIViewController
) {
    @Composable
    actual fun createPicker(): ImagePicker {
        return remember {
            ImagePicker(rootController)
        }
    }
}