package org.test.testmultiplatform.contacts.presentation

import androidx.compose.runtime.Composable

expect class ImagePicker {
    @Composable
    fun registerPicker(onImagePicked: (ByteArray) -> Unit)

    fun pickImage()
}