package com.chocoholic.project.commands

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User

class CommandContext(
    val user: User,
    val sender: Member,
    val label: String,
    val args: Array<String>,
    val rowedArgs: Array<String>,
    val message: Message,
    val command: CommandHost
) {

    val channel = message.textChannel

    fun getUser(argument: Int): User? {
        if (rowedArgs.size-1 < argument) return null

        val arg = rowedArgs[argument]

        if (arg.toLongOrNull() != null) {
            return message.jda.getUserById(arg)
        }
        return message.mentionedUsers.firstOrNull { it.asMention == arg.replace("!", "") }
    }

}