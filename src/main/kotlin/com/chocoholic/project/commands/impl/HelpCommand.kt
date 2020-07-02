package com.chocoholic.project.commands.impl

import com.chocoholic.project.commands.CommandContext
import com.chocoholic.project.commands.CommandHost
import com.chocoholic.project.extensions.embed

class HelpCommand: CommandHost(
    name = "help"
) {

    override suspend fun handle(context: CommandContext) {
        context.channel.sendMessage(embed("titulo") {
            field("o ranni eh viado", "sim", false)
        }).queue()
    }

}