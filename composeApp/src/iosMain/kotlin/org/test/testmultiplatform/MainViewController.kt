package org.test.testmultiplatform

import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import org.test.testmultiplatform.core.presentation.ImagePickerFactory
import org.test.testmultiplatform.di.AppModule

fun MainViewController() = ComposeUIViewController {
//    val isDarkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
    val isDarkTheme = false
    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
        appModule = AppModule(),
        imagePicker = ImagePickerFactory(LocalUIViewController.current).createPicker()
    )
}