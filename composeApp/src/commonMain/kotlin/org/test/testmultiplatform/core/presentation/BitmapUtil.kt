package org.test.testmultiplatform.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

@OptIn(ExperimentalResourceApi::class)
@Composable
fun rememberBitmapFromBytes(bytes: ByteArray?): ImageBitmap? {
    return remember(bytes){
        bytes?.decodeToImageBitmap()
    }
}