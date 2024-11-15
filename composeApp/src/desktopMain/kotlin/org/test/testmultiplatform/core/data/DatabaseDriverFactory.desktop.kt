package org.test.testmultiplatform.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.test.testmultiplatform.database.ContactDatabase
import java.util.*

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:contact.db", Properties(), ContactDatabase.Schema)
//        ContactDatabase.Schema.create(driver)
        return driver
    }
}