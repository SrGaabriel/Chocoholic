package com.chocoholic.project.scopes

import club.minnced.jda.reactor.on
import com.chocoholic.project.utils.ChocoholicConstants.projectScope
import kotlinx.coroutines.launch
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageReaction
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent
import org.koin.core.KoinComponent
import org.koin.core.inject

interface ReactionScope {

    val user: User
    val member: Member?
    val reaction: MessageReaction

    fun cancel() {
        reaction.removeReaction(user).queue()
    }

    fun exit() {
        answered.add(reaction.messageIdLong)
    }

    companion object: KoinComponent {

        private val jda by inject<JDA>()
        private val answered = hashSetOf<Long>().toMutableSet()

        suspend fun onReaction(message: Message, work: suspend ReactionScope.() -> (Unit)) {
            jda.on<MessageReactionAddEvent>().filter { it.messageIdLong == message.idLong && it.userIdLong != message.jda.selfUser.idLong  }.subscribe {
                projectScope.launch {
                    if (answered.contains(message.idLong)) {
                        return@launch
                    }
                    work(dsl(it))
                }
            }
        }

        private fun dsl(event: MessageReactionAddEvent): ReactionScope {
            return object: ReactionScope {
                override val user: User = event.user!!
                override val member: Member? = event.member
                override val reaction: MessageReaction = event.reaction
            }
        }
    }

}