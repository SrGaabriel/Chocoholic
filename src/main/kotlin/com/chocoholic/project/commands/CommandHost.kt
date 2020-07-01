package com.chocoholic.project.commands

abstract class CommandHost(
    val name: String,
    val internal: Boolean = false
) {

    abstract fun handle(context: CommandContext)

}