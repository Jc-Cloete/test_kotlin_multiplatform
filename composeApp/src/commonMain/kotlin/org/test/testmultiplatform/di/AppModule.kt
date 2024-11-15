package org.test.testmultiplatform.di

import org.test.testmultiplatform.contacts.domain.ContactDataSource

expect class AppModule {
    val contactDataSource: ContactDataSource
}