package org.test.testmultiplatform.contacts.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.datetime.Clock
import org.test.testmultiplatform.contacts.domain.Contact
import org.test.testmultiplatform.contacts.domain.ContactDataSource
import org.test.testmultiplatform.database.ContactDatabase

class SqlContactDataSource(
    db: ContactDatabase
): ContactDataSource {
    private val queries = db.contactQueries
    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private val myContext = newSingleThreadContext("MyThread")

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getContacts(): Flow<List<Contact>> {
        return queries.getContacts().asFlow().mapToList(myContext).map {
            it.map { it.toContact() }
        }
    }

    override fun getRecentContacts(amount: Int): Flow<List<Contact>> {
        return queries.getRecentContacts(amount.toLong()).asFlow().mapToList(myContext).map {
            it.map { it.toContact() }
        }
    }

    override suspend fun insertContact(contact: Contact) {
        queries.insertContact(
            firstName = contact.firstName,
            lastName = contact.lastName,
            phoneNumber = contact.phoneNumber,
            email = contact.email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = null
        )
    }

    override suspend fun deleteContact(id: Long) {
        queries.deleteContact(id)
    }
}