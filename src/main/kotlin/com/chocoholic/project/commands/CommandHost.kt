package com.chocoholic.project.commands

abstract class CommandHost(
    val name: String,
    val internal: Boolean = false
) {

    abstract suspend fun handle(context: CommandContext)

}