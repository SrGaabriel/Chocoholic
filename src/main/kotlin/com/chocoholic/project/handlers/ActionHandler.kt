package com.chocoholic.project.handlers

import com.chocoholic.project.commands.CommandHost
import com.chocoholic.project.commands.CommandRepository
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.reflections.Reflections
import kotlin.reflect.KClass

class ActionHandler(val jda: JDA) {

    fun loadCommands() {
        val commands = Reflections("com.chocoholic.project.commands.impl").subTypes<CommandHost>()

        for (command in commands) {
            val instance = kotlin.runCatching {
                command.getConstructor().newInstance()
            }.getOrNull() ?: continue

            println(command.simpleName)
            CommandRepository.list.add(instance)
        }
    }

    fun loadListeners() {
        val listeners = Reflections("com.chocoholic.project.listeners").subTypes<ListenerAdapter>()

        for (listener in listeners) {
            val instance = kotlin.runCatching {
                listener.getConstructor().newInstance()
            }.getOrNull() ?: continue

            jda.addEventListener(instance)
        }
    }

    private inline fun <reified T> Reflections.subTypes(): MutableSet<Class<out T>> = getSubTypesOf(T::class.java)

}