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

}