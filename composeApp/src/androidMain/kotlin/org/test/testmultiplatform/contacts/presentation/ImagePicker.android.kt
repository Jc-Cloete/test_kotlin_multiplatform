package org.test.testmultiplatform.contacts.presentation

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

actual class ImagePicker(
    private val activity: ComponentActivity
) {

    private lateinit var getContent: ActivityResultLauncher<String>

    @Composable
    actual fun registerPicker(onImagePicked: (ByteArray) -> Unit) {
        getContent = activity.registerForActivityResult(
            contract = ActivityResultContracts.GetContent(),
        ) { uri ->
            uri?.let {
                activity.contentResolver.openInputStream(it)?.readBytes()?.let { bytes ->
                    onImagePicked(bytes)
                }
            }
        }
    }

    actual fun pickImage() {
        getContent.launch("image/*")
    }

}