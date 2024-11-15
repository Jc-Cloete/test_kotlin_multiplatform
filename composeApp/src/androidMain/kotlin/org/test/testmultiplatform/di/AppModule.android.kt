package org.test.testmultiplatform.di

import org.test.testmultiplatform.contacts.data.SqlContactDataSource
import org.test.testmultiplatform.contacts.domain.ContactDataSource
import org.test.testmultiplatform.core.data.DatabaseDriverFactory
import org.test.testmultiplatform.database.ContactDatabase

actual class AppModule(
    val context: android.content.Context
) {
    actual val contactDataSource: ContactDataSource by lazy {
        SqlContactDataSource(
            db = ContactDatabase(DatabaseDriverFactory(context).create())
        )
    }
}