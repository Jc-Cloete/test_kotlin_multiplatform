package org.test.testmultiplatform.contacts.data

import org.test.testmultiplatform.contacts.domain.Contact
import org.test.testmultiplatform.db.ContactEntity

fun ContactEntity.toContact(): Contact {
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = null
    )
}