package org.test.testmultiplatform.core.presentation

import androidx.compose.runtime.Composable
import org.test.testmultiplatform.contacts.presentation.ImagePicker

expect class ImagePickerFactory {
    @Composable
    fun createPicker(): ImagePicker
}