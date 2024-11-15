package org.test.testmultiplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.test.testmultiplatform.core.presentation.ImagePickerFactory
import org.test.testmultiplatform.di.AppModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true,
                appModule = AppModule(LocalContext.current.applicationContext),
                imagePicker = ImagePickerFactory().createPicker()
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(
        darkTheme = false,
        dynamicColor = false,
        appModule = AppModule(LocalContext.current.applicationContext),
        imagePicker = ImagePickerFactory().createPicker()
    )
}