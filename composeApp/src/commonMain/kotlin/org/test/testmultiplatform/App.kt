package org.test.testmultiplatform

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.test.testmultiplatform.contacts.presentation.ContactListScreen
import org.test.testmultiplatform.contacts.presentation.ContactListViewModel
import org.test.testmultiplatform.contacts.presentation.ImagePicker
import org.test.testmultiplatform.core.presentation.ContactsTheme
import org.test.testmultiplatform.di.AppModule

@Composable
@Preview
fun App(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    appModule: AppModule,
    imagePicker: ImagePicker
) {
    ContactsTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        val viewModel = viewModel { ContactListViewModel(
            appModule.contactDataSource
        ) }
        val state by viewModel.state.collectAsState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            ContactListScreen(
                state = state,
                newContact = viewModel.newContact,
                onEvent = viewModel::onEvent,
                imagePicker = imagePicker,
            )
        }
    }
}