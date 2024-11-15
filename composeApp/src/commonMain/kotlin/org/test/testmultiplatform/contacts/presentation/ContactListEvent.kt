package org.test.testmultiplatform.contacts.presentation

import org.test.testmultiplatform.contacts.domain.Contact

sealed interface ContactListEvent {
    object OnAddNewContactClick: ContactListEvent
    object DissmissContact: ContactListEvent
    data class OnFirstNameChanged(val value: String): ContactListEvent
    data class OnLastNameChanged(val value: String): ContactListEvent
    data class OnEmailChanged(val value: String): ContactListEvent
    data class OnPhoneNumberChanged(val value: String): ContactListEvent
    data class OnPhotoPicked(val bytes: ByteArray): ContactListEvent
    object OnAddPhotoClicked: ContactListEvent
    object SaveContact: ContactListEvent
    data class SelectContact(val contact: Contact): ContactListEvent
    data class EditContact(val contact: Contact): ContactListEvent
    object DeleteContact: ContactListEvent
}