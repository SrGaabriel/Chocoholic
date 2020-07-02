package com.chocoholic.project.storage

import com.chocoholic.project.storage.tables.ChocoholicProfiles
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.cdimascio.dotenv.Dotenv
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.inject
import org.koin.dsl.module

object DatabaseManager: KoinComponent {

    private val env by inject<Dotenv>()

    fun connect() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://${env["DB-HOST"]}:${env["DB-PORT"]}/${env["DB-NAME"]}?useTimezone=true&serverTimezone=UTC"
            driverClassName = "com.mysql.cj.jdbc.Driver"
            username = env["DB-USER"]
            password = env["DB-PASSWORD"]
            maximumPoolSize = 32
        }
        val database = Database.connect(HikariDataSource(config))

        loadKoinModules(module {
            single { database }
        })

        transaction(database) {
            SchemaUtils.createMissingTablesAndColumns(ChocoholicProfiles)
        }
    }

}