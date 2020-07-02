package com.chocoholic.project.commands.impl

import com.chocoholic.project.commands.CommandContext
import com.chocoholic.project.commands.CommandHost
import com.chocoholic.project.extensions.onReaction
import kotlinx.coroutines.future.await

class TestCommand: CommandHost(
    name = "test"
) {

    override suspend fun handle(context: CommandContext) {
        val message = context.channel.sendMessage("Reaja nessa mensagem com :white_check_mark:!").submit().await()

        message.onReaction {
            if (!reaction.reactionEmote.isEmoji || reaction.reactionEmote.asCodepoints != "U+2705") return@onReaction cancel()

            cancel()
            exit()
            message.editMessage("${user.asMention}, obrigado por clicar!").queue()
        }
    }

}