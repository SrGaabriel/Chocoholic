package com.chocoholic.project.scopes

import club.minnced.jda.reactor.onMessage
import com.chocoholic.project.utils.ChocoholicConstants.projectScope
import kotlinx.coroutines.launch
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.koin.core.KoinComponent

interface ResponseScope {

    val user: User
    val member: Member?
    val message: Message

    val invokedMessage: Message

    fun exit() {
        answered.add(invokedMessage)
    }

    companion object: KoinComponent {

        private val answered = hashSetOf<Message>().toMutableSet()

        suspend fun onResponse(target: User, message: Message, channel: MessageChannel = message.channel, work: suspend ResponseScope.() -> Unit) { channel.onMessage()
                .filter { it.author.idLong == target.idLong }
                .filter { it.channel == channel }
                .subscribe {
                    if (answered.contains(message)) return@subscribe
                    projectScope.launch {
                        work(dsl(it, message))
                    }
                }
        }

        private fun dsl(event: MessageReceivedEvent, invokedMessage: Message): ResponseScope {
            return object: ResponseScope {
                override val user: User = event.author
                override val member: Member? = event.member
                override val message: Message = event.message
                override val invokedMessage: Message = invokedMessage
            }
        }
    }

}