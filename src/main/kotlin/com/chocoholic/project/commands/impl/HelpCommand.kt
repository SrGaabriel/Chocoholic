package com.chocoholic.project.commands.impl

import com.chocoholic.project.commands.CommandContext
import com.chocoholic.project.commands.CommandHost
import com.chocoholic.project.extensions.embed

class HelpCommand: CommandHost(
    name = "help"
) {

    override fun handle(context: CommandContext) {
        println("DEBUG6")
        context.channel.sendMessage(embed("AJUDA - WonderLand") {
            description = ""
        }).queue()
    }

}