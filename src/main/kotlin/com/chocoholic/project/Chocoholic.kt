package com.chocoholic.project

import club.minnced.jda.reactor.ReactiveEventManager
import club.minnced.jda.reactor.on
import com.chocoholic.project.storage.DatabaseManager
import com.chocoholic.project.utils.ChocoholicConstants.projectScope
import io.github.cdimascio.dotenv.dotenv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.setMain
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.ReadyEvent
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.slf4j.LoggerFactory

class Chocoholic {

    fun bootstrap() {
        val logger = LoggerFactory.getLogger(Chocoholic::class.java)
        val env = dotenv()
        val jda = JDABuilder.createDefault(env["TOKEN"])
            .setEventManager(ReactiveEventManager())
            .build()

        Dispatchers.setMain(Dispatchers.Unconfined)

        startKoin {
            modules(module {
                single { logger }
                single { env }
                single { jda }
            })
        }

        DatabaseManager.connect()
        projectScope.launch {
            jda.on<ReadyEvent>().subscribe {
                jda.presence.setPresence(OnlineStatus.ONLINE, Activity.watching("as hortas do meu servidor!"))
            }
        }

    }

}

fun main() {
    Chocoholic().bootstrap()
}