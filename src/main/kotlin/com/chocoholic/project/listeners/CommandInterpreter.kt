package com.chocoholic.project.listeners

import com.chocoholic.project.commands.CommandContext
import com.chocoholic.project.commands.CommandRepository
import com.chocoholic.project.utils.ChocoholicConstants
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class CommandInterpreter: ListenerAdapter() {

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        if (!event.message.contentDisplay.startsWith(ChocoholicConstants.PREFIX)) return

        val content = event.message.contentDisplay.substring(ChocoholicConstants.PREFIX.length)

        val command = CommandRepository.list.firstOrNull {
            it.name == content.split(" ").first()
        } ?: return

        val contentArray = content.split(" ").toTypedArray()

        command.handle(CommandContext(
            event.author,
            event.member!!,
            contentArray.first(),
            contentArray.copyOfRange(1, contentArray.size),
            event.message.contentRaw.split(" ").toTypedArray().copyOfRange(1, event.message.contentRaw.split(" ").size),
            event.message,
            command
        ))
    }

}