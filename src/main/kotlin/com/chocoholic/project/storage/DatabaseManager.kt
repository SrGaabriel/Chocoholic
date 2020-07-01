package com.chocoholic.project.storage

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.cdimascio.dotenv.Dotenv
import org.jetbrains.exposed.sql.Database
import org.koin.core.KoinComponent
import org.koin.core.inject

object DatabaseManager: KoinComponent {

    private val env by inject<Dotenv>()

    fun connect() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://${env["DB-HOST"]}:${env["DB-PORT"]}/?useTimezone=true&serverTimezone=UTC"
            driverClassName = "com.mysql.cj.jdbc.Driver"
            username = env["DB-USER"]
            password = env["DB-PASSWORD"]
            maximumPoolSize = 32
        }
        Database.connect(HikariDataSource(config))
    }

}