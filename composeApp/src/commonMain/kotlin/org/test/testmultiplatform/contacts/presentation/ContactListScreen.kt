package org.test.testmultiplatform.contacts.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.test.testmultiplatform.contacts.domain.Contact
import org.test.testmultiplatform.contacts.presentation.components.AddContactSheet
import org.test.testmultiplatform.contacts.presentation.components.ContactListItem

@Composable
fun ContactListScreen(
    state: ContactListState,
    newContact: Contact?,
    onEvent: (ContactListEvent) -> Unit,
    imagePicker: ImagePicker
) {
    imagePicker.registerPicker {imageBytes ->
        onEvent(ContactListEvent.OnPhotoPicked(imageBytes))
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(ContactListEvent.OnAddNewContactClick)
                },
                shape = RoundedCornerShape(20.dp)
            ){
                Icon(
                    imageVector = Icons.Rounded.PersonAdd,
                    contentDescription = "Add contact"
                )
            }
        }
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            item {
                Text(
                    text = "My contacts ${state.contacts.size}",
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            items(state.contacts){ contact ->
                ContactListItem(
                    contact = contact,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(ContactListEvent.SelectContact(contact))
                        }
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }

    AddContactSheet(
        state,
        newContact,
        state.isContactSheetOpen,
        { event ->
            if(event is ContactListEvent.OnAddPhotoClicked){
                imagePicker.pickImage()
            }
            onEvent(event)

        }
    )
}