package com.chocoholic.project.threads

import com.chocoholic.project.utils.ChocoholicConstants
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import org.koin.core.KoinComponent

class StatusThread(val jda: JDA): Thread("Status Thread") {

    override fun run() {
        jda.presence.setPresence(
            OnlineStatus.ONLINE,
            Activity.watching("❤ ${jda.getGuildById(ChocoholicConstants.GUILD_ID)!!.memberCount} membros no meu servidor!"
        ))

        sleep(8000)
        run()
    }

}