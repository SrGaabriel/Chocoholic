package com.chocoholic.project.commands.impl

import com.chocoholic.project.commands.CommandContext
import com.chocoholic.project.commands.CommandHost
import com.chocoholic.project.storage.tables.ChocoholicProfiles

class HeartsCommand: CommandHost(
    name = "hearts"
) {

    override suspend fun handle(context: CommandContext) {
        val target = context.getUser(0) ?: context.user
        val entity = ChocoholicProfiles.find(target.idLong)

        if (target == context.user) {
            context.reply("<:WL_heart:728038755927195659>", "${context.user.asMention}, você tem um total de **${entity.hearts}** corações!")
        } else {
            context.reply("<:WL_heart:728038755927195659>", "${context.user.asMention}, o usuário ${target.asMention} tem um total de **${entity.hearts}** corações!")
        }
    }

}