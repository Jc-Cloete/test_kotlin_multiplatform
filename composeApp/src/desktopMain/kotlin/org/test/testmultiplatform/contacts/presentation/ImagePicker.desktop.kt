package org.test.testmultiplatform.contacts.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.awt.ComposeWindow
import java.awt.Dimension
import java.awt.FileDialog
import java.awt.Toolkit
import java.io.File
import javax.swing.SwingUtilities

actual class ImagePicker(
) {

    private var onImagePicked: ((ByteArray) -> Unit)? = null

    private fun FileDialog.centerOnScreen(dialogSize: Dimension) {
        val screenSize = Toolkit.getDefaultToolkit().screenSize
        val x = (screenSize.width - dialogSize.width) / 2
        val y = (screenSize.height - dialogSize.height) / 2
        SwingUtilities.invokeLater {
            this.setLocation(x, y)
        }
    }

    private fun openFileDialog(
        window: ComposeWindow,
        title: String,
        allowedExtensions: List<String> = emptyList(),
    ): File? {
        return FileDialog(window, title, FileDialog.LOAD).run {
            isMultipleMode = false
            file = allowedExtensions.joinToString(";") { "*$it" }
            setFilenameFilter { _, name -> allowedExtensions.any { name.endsWith(it) } }
            setSize(800, 600)
            centerOnScreen(size)
            isVisible = true
            files.firstOrNull()
        }
    }

    @Composable
    actual fun registerPicker(onImagePicked: (ByteArray) -> Unit) {
        this.onImagePicked = onImagePicked
    }

    actual fun pickImage() {
        val window = ComposeWindow()
        val file = openFileDialog(
            window = window,
            title = "Select image",
            allowedExtensions = listOf("png", "jpg", "jpeg"))

        file?.let{
            this.onImagePicked?.invoke(file.readBytes())
        }
    }
}