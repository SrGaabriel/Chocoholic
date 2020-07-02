package com.chocoholic.project.storage.entities

import com.chocoholic.project.storage.tables.ChocoholicProfiles
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ChocoholicProfile(id: EntityID<Long>): LongEntity(id) {

    companion object: LongEntityClass<ChocoholicProfile>(ChocoholicProfiles)

    var hearts by ChocoholicProfiles.hearts
    var likes by ChocoholicProfiles.likes

}