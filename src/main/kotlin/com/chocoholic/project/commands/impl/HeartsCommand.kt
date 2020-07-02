package com.chocoholic.project.commands.impl

import com.chocoholic.project.commands.CommandContext
import com.chocoholic.project.commands.CommandHost
import com.chocoholic.project.storage.tables.ChocoholicProfiles

class HeartsCommand: CommandHost(
    name = "hearts"
) {

    override fun handle(context: CommandContext) {
        val target = context.getUser(0) ?: context.user

        val entity = ChocoholicProfiles.find(target.idLong)
        val message = if (target == context.user) {
            "<:wn_panda_love:704152782671839283> **➸** Você tem um total de **${entity.hearts}** corações!"
        } else {
            "<:wn_panda_love:704152782671839283> **➸** O usuário ${target.asMention} tem um total de **${entity.hearts}** corações!"
        }

        context.channel.sendMessage(message).queue()
    }

}