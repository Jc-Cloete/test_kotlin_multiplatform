package org.test.testmultiplatform

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.*
import org.test.testmultiplatform.core.presentation.ImagePickerFactory
import org.test.testmultiplatform.di.AppModule

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TestMultiplatform",
        state = rememberWindowState(
            placement = WindowPlacement.Floating,
            position = WindowPosition(Alignment.Center),
        )
    ) {

        App(
            darkTheme = false,
            dynamicColor = false,
            appModule = AppModule(),
            imagePicker = ImagePickerFactory().createPicker()
        )
    }
}