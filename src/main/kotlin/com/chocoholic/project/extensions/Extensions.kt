package com.chocoholic.project.extensions

import com.chocoholic.project.scopes.EmbedScope
import net.dv8tion.jda.api.entities.MessageEmbed

fun embed(title: String?, work: EmbedScope.() -> Unit): MessageEmbed {
    val embed = EmbedScope(title); work(embed)

    return embed.build()
}