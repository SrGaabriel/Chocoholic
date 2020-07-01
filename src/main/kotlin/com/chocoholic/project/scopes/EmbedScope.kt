package com.chocoholic.project.scopes

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.User
import java.awt.Color

class EmbedScope(private val title: String?) {

    var icon: User? = null
    var description: String? = null
    var thumbnail: String? = null
    var image: String? = null
    var color: Color? = null
    var footer: String? = null
    val fields: MutableList<MessageEmbed.Field> = mutableListOf()

    fun field(name: Any, value: Any, inline: Boolean) {
        fields.add(MessageEmbed.Field(name.toString(), value.toString(), inline))
    }

    fun build(): MessageEmbed {
        return EmbedBuilder().apply {
            if (icon != null) setAuthor(icon!!.name, null, icon!!.avatarUrl)
            setTitle(title)
            setDescription(description)
            setThumbnail(thumbnail)
            setImage(image)
            setColor(color)
            setFooter(footer)
            this@EmbedScope.fields.forEach {
                this.addField(it)
            }
        }.build()
    }

}