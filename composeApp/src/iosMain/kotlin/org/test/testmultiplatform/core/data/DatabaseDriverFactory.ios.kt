package org.test.testmultiplatform.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.test.testmultiplatform.database.ContactDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(ContactDatabase.Schema, "contact.db")
    }
}