package com.chocoholic.project.extensions

import com.chocoholic.project.scopes.EmbedScope
import com.chocoholic.project.scopes.ReactionScope
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed

fun embed(title: String?, work: EmbedScope.() -> Unit): MessageEmbed {
    val embed = EmbedScope(title); work(embed)

    return embed.build()
}

suspend fun Message.onReaction(work: suspend ReactionScope.() -> Unit) {
    ReactionScope.onReaction(this, work)
}