package org.test.testmultiplatform.contacts.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.test.testmultiplatform.contacts.domain.Contact
import org.test.testmultiplatform.contacts.domain.ContactDataSource
import org.test.testmultiplatform.contacts.domain.ContactValidator

class ContactListViewModel(
    private val contactDataSource: ContactDataSource
): ViewModel() {



    private val _state = MutableStateFlow(ContactListState())
    val state = combine(_state, contactDataSource.getContacts(), contactDataSource.getRecentContacts(20)){
        state, contacts, recentContacts ->
        state.copy(
            contacts = contacts,
            recentlyAddedContacts = recentContacts
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ContactListState())

    var newContact: Contact? by mutableStateOf(null)

    fun onEvent(event: ContactListEvent) {
        when(event) {
            ContactListEvent.DeleteContact -> {
                viewModelScope.launch {
                    state.value.selectedContact?.id?.let { id ->
                        _state.update { it.copy(
                            isContactSheetOpen = false,
                        ) }
                        contactDataSource.deleteContact(id)
                        delay(300L)
                        _state.update { it.copy(
                            selectedContact = null,
                        ) }
                    }
                }
            }

            ContactListEvent.DissmissContact -> viewModelScope.launch {
                _state.update { it.copy(
                    isContactSheetOpen = false,
                    isSelectedContactSheetOpen = false,
                    firstNameError = null,
                    lastNameError = null,
                    emailError = null,
                    phoneNumberError = null,
                ) }
                delay(300L)
                newContact = null
                _state.update { it.copy(
                    selectedContact = null,
                ) }
            }

            is ContactListEvent.EditContact -> {
                _state.update { it.copy(
                    selectedContact = null,
                    isContactSheetOpen = true,
                    isSelectedContactSheetOpen = false,

                )}
                newContact = event.contact
            }

            ContactListEvent.OnAddNewContactClick -> {
                _state.update { it.copy(
                    isContactSheetOpen = true,
                    isSelectedContactSheetOpen = false,
                )}
                newContact = Contact(
                    id = null,
                    firstName = "",
                    lastName = "",
                    email = "",
                    phoneNumber = "",
                    photoBytes = null,
                )
            }


            is ContactListEvent.OnEmailChanged -> {
                newContact = newContact?.copy(email = event.value)
            }

            is ContactListEvent.OnFirstNameChanged -> {
                newContact = newContact?.copy(firstName = event.value)
            }

            is ContactListEvent.OnLastNameChanged -> {
                newContact = newContact?.copy(lastName = event.value)
            }

            is ContactListEvent.OnPhoneNumberChanged -> {
                newContact = newContact?.copy(phoneNumber = event.value)
            }

            is ContactListEvent.OnPhotoPicked -> {
                newContact = newContact?.copy(photoBytes = event.bytes)
            }

            ContactListEvent.SaveContact -> {
                newContact?.let { contact ->
                    val result = ContactValidator.validateContact(contact)
                    val errors = listOfNotNull(
                        result.firstNameError,
                        result.lastNameError,
                        result.emailError,
                        result.phoneNumberError
                    )
                    if (errors.isEmpty()) {
                        _state.update { it.copy(
                            isContactSheetOpen = false,
                            firstNameError = null,
                            lastNameError = null,
                            emailError = null,
                            phoneNumberError = null,
                        )}
                        viewModelScope.launch {
                            contactDataSource.insertContact(contact)
                            delay(300L)
                            newContact = null
                        }
                    } else {
                        _state.update { it.copy(
                            firstNameError = result.firstNameError,
                            lastNameError = result.lastNameError,
                            emailError = result.emailError,
                            phoneNumberError = result.phoneNumberError,
                        )}
                    }
                }
            }

            is ContactListEvent.SelectContact -> {
                _state.update { it.copy(
                    selectedContact = event.contact,
                    isContactSheetOpen = false,
                    isSelectedContactSheetOpen = true,
                )}
            }

            else -> Unit
        }
    }
}
