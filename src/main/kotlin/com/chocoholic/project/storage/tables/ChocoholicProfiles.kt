package com.chocoholic.project.storage.tables

import com.chocoholic.project.storage.entities.ChocoholicProfile
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.transactions.transaction

object ChocoholicProfiles: LongIdTable(name = "chocoProfilesTable", columnName = "ID") {

    val hearts = long("hearts")
    val likes = integer("likes")

    fun find(id: Long): ChocoholicProfile = transaction {
        ChocoholicProfile.findById(id) ?: ChocoholicProfile.new(id) {
            this.hearts = 0L
            this.likes = 0
        }
    }

}